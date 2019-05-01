<?php

###################### PROTECCIÓN ######################
// Si se accede a éste archivo se cierra la conección
if(preg_match("/global.php/" ,$_SERVER['REQUEST_URI']))
	exit("This file is not visible.");

###################### DIRECTORIOS PRINCIPALES ######################
// Directorios necesarios para acceder a los subdirectorios de forma rapida
define("ROOT_PATH", dirname(__FILE__) . DIRECTORY_SEPARATOR);
define("INCLUDES_PATH", ROOT_PATH . "includes" . DIRECTORY_SEPARATOR);

###################### DIRECTORIOS SECUNDARIOS ######################
// Directorios que contienen clases y archivos necesarios para las clases
define("CLASS_PATH", INCLUDES_PATH . "classes" . DIRECTORY_SEPARATOR);
define("LANGS_PATH", INCLUDES_PATH . "langs" . DIRECTORY_SEPARATOR);
define("TEMPLATES_PATH", INCLUDES_PATH . "templates" . DIRECTORY_SEPARATOR);
define("ACTIVED", "TRUE");

###################### INICIANDO SESIONES Y HABILITANDO COMPRESIÓN GZIP ######################
// Necesario para manejas las variables de sesiones, se optimiza la web con gzip
session_start();
if(!ob_start("ob_gzhandler"))
	ob_start();

###################### INCLUYENDO NUCLEO ######################
// Funciones principales de todo el sistema
include INCLUDES_PATH . 'config.php';
include INCLUDES_PATH . 'core.php';

###################### INICIANDO CLASES ######################
// Inicia las clases que se utilizaran en el sistema
$mysql_mgr = new DatabaseConnection($config['mysql']['host'], $config['mysql']['user'], $config['mysql']['password'], $config['mysql']['db']);
$query_mgr = $mysql_mgr->getDatabaseQuery();
$templates_mgr = new TemplateManager(TEMPLATES_PATH);
$lang_mgr = new LangManager(LANGS_PATH);
$lang_mgr->setLang("es_co");
$habbo_mgr = new HabboManager();

###################### INICIANDO VARIABLES GLOBALES ######################
// Variables necesarias como la dirección del sitio
$SITE['URL'] = "localhost";

?>