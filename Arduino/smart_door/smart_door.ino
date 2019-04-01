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
}

void loop() {
  int user_status;
  row_values *row = NULL;

  //ici on aura l'algorithme de reconnaissance facial
  //celui-ci n'étant pas prêt, nous admettrons pour l'instant que par défaut, notre
  //algorithme reconnait en continue l'utilisateur 1
  user_id = 1;

  //on suppose que si l'algorithme ne reconnais pas de visage, user_id est initialisé à 0
  if(user_id == 0)
    return;

  ///////////////////////////////////////////////
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

  if(user_status > 0) //on ouvre la porte
  {
    Serial.println("Ouverture de la porte");
    output26State = "on";
    digitalWrite(output26, HIGH);
    for(int i=0;i<5;i++)
    {
      Serial.print(".");
      delay(1000);
    }
    Serial.println(".");
    output26State = "off";
    digitalWrite(output26, LOW);
    Serial.println("Fermeture de la porte");
    Serial.println("");
  }
  delay(2000);
}
