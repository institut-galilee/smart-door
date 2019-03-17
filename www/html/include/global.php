<?php
session_start();
if (!isset($_SESSION['username']))
{
	header ('Location: /index.php');
	exit();
}

$pseudo = $_SESSION['username'];

$query=$pdo->prepare("SELECT * FROM users WHERE username = '".$pseudo."' LIMIT 1");
$query->execute();
$row = $query->rowCount();
if($row > 0)
{
	$user = $query->fetch(PDO::FETCH_ASSOC); PDO::FETCH_ASSOC;
}
else
{
	//si on est dans ce cas, cela veut dire qu'il y a un bug, donc on détruit la session de l'utilisateur pour qu'il puisse utilisé le site en tant que non connecté
	session_unset();
	session_destroy();
	header('Location: /index.php');
	exit();
}
?>
