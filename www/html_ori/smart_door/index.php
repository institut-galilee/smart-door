<?php
  //include "include/is_connected.php";
  include "include/mysql.php";
  if(isset($_POST["username"]) && isset($_POST["password"])){
    $hash = password_hash($_POST["password"], PASSWORD_DEFAULT);
    echo $hash;
    echo "<br>";
  	//Nous allons demander le hash pour cet utilisateur à notre base de données :
  	$query = $pdo->prepare('SELECT password FROM users WHERE username = :username');
  	$query->bindParam(':username', $_POST["username"]);
  	$query->execute();
  	$result = $query->fetch();
  	$hash = $result[0];

  	//Nous vérifions si le mot de passe utilisé correspond bien à ce hash à l'aide de password_verify :
  	$correctPassword = password_verify($_POST["password"], $hash);

  	if($correctPassword){
  		//Si oui nous accueillons l'utilisateur identifié
      session_start();
      $_SESSION['username'] = $_POST['username'];
      header('Location: administration/index.php');
      exit();
  	}else{
  		//Sinon nous signalons une erreur d'identifiant ou de mot de passe
  		echo "login/password incorrect";
  	}
  }
?>

<html>
  <head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
    <title>SmartDoor - administration</title>
  </head>
  <body>
    <div class="container alert alert-secondary">
      <h1 class="text-center">Administration</h1>
      <p class="text-center">Administrez votre SmartDoor avec l'interface SmartDoor admin</p>
    </div>

    <div class="container">
      <div class="row">
        <div class="col-sm-9 col-md-7 col-lg-5 mx-auto">
          <div class="card card-signin my-5">
            <div class="card-body">
              <h5 class="card-title text-center">Connexion</h5>
              <form action="index.php" method="post">
               <div class="form-group">
                 <label for="username">Pseudo:</label>
                 <input type="text" class="form-control" id="username" name="username">
               </div>
               <div class="form-group">
                 <label for="password">Mot de passe:</label>
                 <input type="password" class="form-control" id="password" name="password">
               </div>
               <button type="submit" class="btn btn-primary">Submit</button>
              </form>
              </h5>
            </div>
          </div>
        </div>
      </div>
    </div>
  </body>
</html>
