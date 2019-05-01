<?php
  include "include/config.php";
  include $_SERVER["DOCUMENT_ROOT"]."/".$folder_path."/include/mysql.php";
  include $_SERVER["DOCUMENT_ROOT"]."/".$folder_path."/include/global.php";
  $query = $pdo->prepare('UPDATE users SET notification = :nb_notif WHERE username = :username');
  $query->bindParam(':username', $user["username"]);
  $query->bindParam(':nb_notif', $_GET["nb_notification"]);
  $query->execute();
?>
