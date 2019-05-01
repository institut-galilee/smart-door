<?php

###################### PROTECCIÓN ######################
// Una constante que pasa el archivo global, si no se 
// encuentra definida entonces cierra la conexión
if(!defined(ACTIVED))
	exit("Not actived yet.");

###################### FUNCIONES ESCENCIALES ######################
// Funciones necesarias para un correcto funcionamiento del sistema

/**
 * __autoload($class_name)
 * Carga las clases automaticamente en el momento en el que son invocadas
 * @param $class_name - Nombre de la clase
 * @usage $class = new class_name();
 */
function __autoload($class_name) {
	if(file_exists(CLASS_PATH . $class_name . '.php'))
		include CLASS_PATH . $class_name . '.php';
}

register_shutdown_function("__shutdown");
/**
 * __shutdown()
 * Cierra todos los objetos y finaliza todas las variables
 * @usage register_shutdown_function("__shutdown");
 */
function __shutdown()
{	
	foreach($GLOBALS as $global => $value)
	{
		if(is_object($GLOBALS[$global]))
		{
			if(method_exists($GLOBALS[$global], "flush") || method_exists($GLOBALS[$global], "__flush"))
				(method_exists($GLOBALS[$global], "flush")) ? $GLOBALS[$global]->flush() : $GLOBALS[$global]->__flush();
		}
		unset($global);
	}
}

###################### FUNCIONES EXTRA ######################
function generate_random($length, $letters = true, $numbers = true) {
	$possible = "";
		
	if($numbers)
		$possible .= "0123456789";
		
	if($letters)
		$possible .= "abcdef";
		
	$random = "";
	
	for($i = 0; $i < $length; $i++) {
		$random .= substr($possible, mt_rand(0, strlen($possible)-1), 1);
	}
	
	return $random;
}

function generate_sso() {
	$sso = generate_random(8) . '-' . generate_random(4) . '-' . generate_random(4) . '-' . generate_random(4) . '-' . generate_random(12);
	
	if($GLOBALS['habbo_mgr']->existsSSO($sso))
		$sso = generate_sso();
	
	return $sso;
}

function anti_html_injection($text) {
	$text = strip_tags(trim($text));
	
	return $text;
}

function anti_sql_injection($text) {
	$text = mysql_real_escape_string(trim($text));
	
	return $text;
}

function secure_method($_METHOD) {
	$_M = array();

	foreach($_METHOD as $method => $value) {
		if(is_array($value))
			$_M[$method] = secure_method($value);
		else
			$_M[$method] = anti_html_injection(anti_sql_injection($value));
			
		unset($_METHOD[$method]);
	}
	
	return $_M;
}

###################### METODO ANTI-INYECCIÓN ######################
if(count($_REQUEST) > 0) {
	$_REQUEST = secure_method($_REQUEST);
}

if(count($_GET) > 0) {
	/*$_G = array();

	foreach($_GET as $get => $value) {
		$_G[$get] = trim($value);
		unset($_GET[$get]);
	}*/
	$_GET = secure_method($_GET);
}

if(count($_POST) > 0) {
	/*$_P = array();

	foreach($_POST as $post => $value) {
		$_P[$post] = trim($value);
		unset($_POST[$post]);
	}*/
	$_POST = secure_method($_POST);
}

if(count($_COOKIE) > 0) {
	/*$_C = array();

	foreach($_COOKIE as $cookie => $value) {
		$_C[$cookie] = trim($value);
		unset($_COOKIE[$cookie]);
	}*/
	
	$_COOKIE = secure_method($_COOKIE);
}

if(count($_SESSION) > 0) {
	/*$_S = array();
	
	foreach($_SESSION as $session => $value) {
		$_S[$session] = trim($value);
		unset($_SESSION[$session]);
	}*/
	
	$_SESSION = secure_method($_SESSION);
}

?>