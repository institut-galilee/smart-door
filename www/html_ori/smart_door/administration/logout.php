<?php
include "include/config.php";
session_start();
session_unset();
session_destroy();
header('Location: /'.$folder_name.'/index.php');
exit();
?>
