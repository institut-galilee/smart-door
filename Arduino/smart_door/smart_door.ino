// Load Wi-Fi library
#include <MySQL_Connection.h>
#include <MySQL_Cursor.h>
#include <WiFi.h>
#include <WiFiClient.h>

// Network credentials
const char *ssid = "Lenovo2205";
const char *password = "andre77270";
byte mac_addr[] = { 0xDE, 0xAD, 0xBE, 0xEF, 0xFE, 0xED };

//Wifi configuration
WiFiClient client;
MySQL_Connection conn((Client *)&client);

//MySQL
//192.168.43.85
IPAddress server_addr(192,168,43,85); // MySQL server IP
char sql_user[] = "remote_client";     // MySQL user
char sql_password[] = "test"; // MySQL password

MySQL_Cursor cur = MySQL_Cursor(&conn);

char query[255];
int user_id;

// Assign output variables to GPIO pins
const int output26 = 26;
const int output27 = 27;

struct previous_user
{
  int user_id;
  int detail; //indique si l'utilisateur a été autorisé ou non
};
typedef struct previous_user previous_user;

previous_user p_user = {-1, -1};

void setup() {
  Serial.begin(115200);
  // Initialize the output variables as outputs
  pinMode(output26, OUTPUT);
  pinMode(output27, OUTPUT);
  // Set outputs to LOW
  digitalWrite(output26, LOW);
  digitalWrite(output27, LOW);

  //Wifi connection
  Serial.print("Connecting to ");
  Serial.println(ssid);
  WiFi.begin(ssid, password);
  while (WiFi.status() != WL_CONNECTED) {
    delay(500);
    Serial.print(".");
  }
  Serial.println("");
  Serial.println("WiFi Connected");
  IPAddress ip = WiFi.localIP();
  Serial.println(ip);

  //SQL connection
  Serial.println("");
  Serial.print("Connecting to database ");
  Serial.println(server_addr);
  while (conn.connect(server_addr, 3306, sql_user, sql_password) != true) {
    delay(200);
    Serial.print ( "." );
  }
  Serial.println("Connected to SQL Server!");
  Serial.println("");
  Serial.println("Lancement de SmartDoor...");
}

void loop() {
  int user_status;
  row_values *row = NULL;

  delay(3000);

  //ici on aura l'algorithme de reconnaissance facial
  //celui-ci n'étant pas prêt, nous admettrons pour l'instant que par défaut, notre
  //algorithme reconnait en continue l'utilisateur donnée dans la bdd
  user_id = get_user_via_mysql();

  //le cas où aucun visage n'est reconnu
  if(user_id == 0)
  {
    if(p_user.user_id != 0) //si à la boucle d'avant, un utilisateur a été reconnu
    {
      if(p_user.detail == 0)
      {
        digitalWrite(output26, LOW);
        digitalWrite(output27, LOW);//pour éviter tout problème

        //On laisse à l'utilisateur 6 secondes pour ouvrir la porte une fois qu'il n'est plus détécté
        for(int i=0;i<2;i++)
        {
          Serial.print(".");
          delay(3000);
        }
        Serial.println("");
        Serial.println("Fermeture de la porte");
        Serial.println("");
      }
      if(p_user.detail == 1)
      {
        digitalWrite(output27, LOW);
        digitalWrite(output26, LOW); //pour éviter tout problème
        Serial.println("");
        Serial.println("l'utilisateur est parti");
        Serial.println("");
      }
      p_user.detail = -1; //Pour anticiper des futurs bugs
    }
    return;
  }

  ///////////////////////////////////////////////
  //Requete MySQL pour savoir si l'utilisateur reconnu a le droit d'entrer
  sprintf(query, "SELECT status FROM smart_door.allowed_people WHERE id = %d", user_id);
  MySQL_Cursor *cur_mem = new MySQL_Cursor(&conn);
  // Execute the query
  cur_mem->execute(query);
  // Fetch the columns (required) but we don't use them.
  column_names *columns = cur_mem->get_columns();
  // Read the row (we are only expecting the one)
  do {
    row = cur_mem->get_next_row();
    if (row != NULL) {
      user_status = atoi(row->values[0]);
    }
  } while (row != NULL);
  // Deleting the cursor also frees up memory used
  delete cur_mem;
  //////////////////////////////////////////////

  if(user_id == p_user.user_id) //Evite d'envoyer un message à la bdd a chaque loop quand un utilisateurs reste devant la caméra
  {
    Serial.print(".");
    return;
  }

  Serial.println("");

  if(user_status > 0) //on ouvre la porte
  {
    Serial.println("Ouverture de la porte");
    digitalWrite(output26, HIGH);
    digitalWrite(output27, LOW); //pour éviter tout problème
    p_user.detail = 0;

    MySQL_Cursor *cur_mem = new MySQL_Cursor(&conn);
    sprintf(query, "INSERT INTO smart_door.logs (text) VALUES (\"L'utilisateur %d a ouvert la porte\")");
    cur_mem->execute(query);
    delete cur_mem;
  }

  if(user_status == 0) //on indique à l'utilisateur qu'il n'est pas autorisé a rentrer
  {
    Serial.println("Un utilisateur banni tente de rentrer");
    digitalWrite(output27, HIGH);
    digitalWrite(output26, LOW); //pour éviter tout problème
    p_user.detail = 1;

    MySQL_Cursor *cur_mem = new MySQL_Cursor(&conn);
    sprintf(query, "INSERT INTO smart_door.logs (text) VALUES (\"L'utilisateur %d a tenté d'ouvrir la porte\")");
    cur_mem->execute(query);
    delete cur_mem;
  }
  p_user.user_id = user_id;
}

int get_user_via_mysql(void) //fonction temporaire simulant l'lgo de reconnaissance faciale
{
  int res;
  row_values *row = NULL;
  sprintf(query, "SELECT user FROM smart_door.test_user LIMIT 1");
  MySQL_Cursor *cur_mem = new MySQL_Cursor(&conn);
  // Execute the query
  cur_mem->execute(query);
  // Fetch the columns (required) but we don't use them.
  column_names *columns = cur_mem->get_columns();
  // Read the row (we are only expecting the one)
  do {
    row = cur_mem->get_next_row();
    if (row != NULL) {
      res = atoi(row->values[0]);
    }
  } while (row != NULL);
  // Deleting the cursor also frees up memory used
  delete cur_mem;
  return res;
}
