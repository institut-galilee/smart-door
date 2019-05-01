<?php

if(!defined(ACTIVED))
	exit("Actived not yet.");
	
switch($page_id) {
	case 'index' : {
		$lang['author_name'] = 'Nombre';
		break;
	}
	case 'register' : {
		$lang['author_name'] = 'Sebastian';
		break;
	}
	case 'security_check' : {
		$lang['error_user_set'] = 'Debe especificar un usuario, sea el nombre o el email';
		$lang['error_user_exists'] = 'El usuario no existe';
		$lang['error_password_set'] = 'Debe especificar una contrase&ntilde;a';
		$lang['error_password_incorrect'] = 'La contraseña es incorrecta';
		break;
	}
	case 'default' :
	default :
		$lang['error_login'] = 'No ha iniciado una sesi&oacute;n';
		break;
}
	
?>