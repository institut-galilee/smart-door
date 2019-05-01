<?php

include 'global.php';

$lang_mgr->setPageId("security_check");

$user = isset($_POST['user']) ? $_POST['user'] : false;
$password = isset($_POST['password']) ? $_POST['password'] : false;

$_SESSION['error'] = array();

if(!$user)
	$_SESSION['error']['user_set'] = $lang_mgr->__get("error_user_set");
else if(!$habbo_mgr->existsName($user) && !$habbo_mgr->existsEmail($user))
	$_SESSION['error']['user_exists'] = $lang_mgr->__get("error_user_exists");
	
if(!$password)
	$_SESSION['error']['password_set'] = $lang_mgr->__get("error_password_set");
	
if(count($_SESSION['error']) == 0) {
	$password = md5($password);

	if(!$habbo_mgr->isPassword($user, $password))
		$_SESSION['error']['password_incorrect'] = $lang_mgr->__get("error_password_incorrect");
}

if(count($_SESSION['error']) <= 0) {
	if($habbo_mgr->existsName($user)) {
		$habbo_mgr->loadByName($user);
		$habbo_mgr->__set("sso", generate_sso());
		$habbo_mgr->usageLogin("name");
		$habbo_mgr->login();
	} else {
		$habbo_mgr->loadByEmail($user);
		$habbo_mgr->__set("sso", generate_sso());
		$habbo_mgr->usageLogin("email");
		$habbo_mgr->login();
	}
	
	header("Location: http://" . $SITE['URL'] . "/me");
} else {
	header("Location: http://" . $SITE['URL'] . "/");
}

?>