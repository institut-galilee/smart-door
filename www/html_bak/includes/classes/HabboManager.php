<?php

if(!defined(ACTIVED))
	exit("Not actived yet.");

class HabboManager implements DatabaseObject {
	private $data = array();
	
	private $usage_login = "";
	
	private $loaded = false;
	private $login = false;

	public function __construct() {
		$logout = true;
	
		//$_COOKIE = isset($GLOBALS['_COOKIE']) ? $GLOBALS['_COOKIE'] : array();
		
		if(count($_COOKIE) > 1) {
			$name = isset($_COOKIE['name']) ? $_COOKIE['name'] : false;
			$email = isset($_COOKIE['email']) ? $_COOKIE['email'] : false;
			$sso = isset($_COOKIE['sso']) ? $_COOKIE['sso'] : false;
			
			if(($name !== false && $this->existsName($name)) && 
				($sso !== false && $this->isSSO($name, $sso))) {
				$this->usage_login = 'name';
				$this->loadByName($_COOKIE['name']);
				$this->login("cookie", true);
				$logout = false;
			} else if(($email !== false && $this->existsEmail($email)) && 
				($sso !== false && $this->isSSO($email, $sso))) {
				$this->usage_login = 'email';
				$this->loadByEmail($_COOKIE['email']);
				$this->login("cookie", true);
				$logout = false;
			}
		}
		
		//$_SESSION = isset($GLOBALS['_SESSION']) ? $GLOBALS['_SESSION'] : array();
		
		if(count($_SESSION) > 1) {
			$name = isset($_SESSION['name']) ? $_SESSION['name'] : false;
			$email = isset($_SESSION['email']) ? $_SESSION['email'] : false;
			$sso = isset($_SESSION['sso']) ? $_SESSION['sso'] : false;
			
			if(($name !== false && $this->existsName($name)) && 
				($sso !== false && $this->isSSO($name, $sso))) {
				$this->usage_login = 'name';
				$this->loadByName($name);
				$this->login("session", true);
				$logout = false;
			} else if(($email !== false && $this->existsEmail($email)) && 
				($sso !== false && $this->isSSO($email, $sso))) {
				$this->usage_login = 'email';
				$this->loadByEmail($email);
				$this->login("session", true);
				$logout = false;
			}
		}
		
		if($logout && (count($_SESSION) > 1 || count($_COOKIE) > 1)) {
			$this->logout();
		}
	}
	
	public function __get($field) {
		if(!array_key_exists($field, $this->data)) {
			echo "null<br>" + $field;
			return null;
		}
			
		return $this->data[$field];
	}
	
	public function __set($field, $value) {
		if(!$this->loaded)
			return;
	
		$this->data[$field] = $value;
		$GLOBALS['query_mgr']->queryExecute("UPDATE sys_users SET " . $field . " = '" . anti_sql_injection($value) . "' WHERE id = " . $this->data['id'] . " LIMIT 1");
	}
	
	public function existsName($name) {
		return ($GLOBALS['query_mgr']->queryNumRows("SELECT name FROM sys_users WHERE name = '" . $name . "' LIMIT 1") > 0) ? true : false;
	}
	
	public function existsEmail($email) {
		return ($GLOBALS['query_mgr']->queryNumRows("SELECT email FROM sys_users WHERE email = '" . $email . "' LIMIT 1") > 0) ? true : false;
	}
	
	public function existsSSO($sso) {
		return ($GLOBALS['query_mgr']->queryNumRows("SELECT sso FROM sys_users WHERE sso = '" . $sso . "' LIMIT 1") > 0) ? true : false;
	}
	
	public function loadByName($name) {
		if(!$this->existsName($name) || $this->loaded)
			return;
		
		$this->data = $GLOBALS['query_mgr']->queryFetchArray("SELECT * FROM sys_users WHERE name = '" . $name . "' LIMIT 1");
		$this->loaded = true;
	}
	
	public function loadByEmail($email) {
		if(!$this->existsEmail($email) || $this->loaded)
			return;
		
		$this->data = $GLOBALS['query_mgr']->queryFetchArray("SELECT * FROM sys_users WHERE email = '" . $email . "' LIMIT 1");
		$this->loaded = true;
	}
	
	public function loadBySSO($sso) {
		if(!$this->existsSSO($sso) || $loaded)
			return;
		
		$this->data = $GLOBALS['query_mgr']->queryFetchArray("SELECT * FROM sys_users WHERE sso = '" . $sso . "' LIMIT 1");
		$this->loaded = true;
	}
	
	public function usageLogin($type) {
		$this->usage_login = $type;
	}
	
	public function isSSO($user, $sso) {
		if(!$this->existsSSO($sso))
			return false;
	
		return ($GLOBALS['query_mgr']->queryNumRows("SELECT name, email, sso FROM sys_users WHERE (name = '" . $user . "' || email = '" . $user . "') && sso = '" . $sso . "' LIMIT 1") > 0) ? true : false;
	}
	
	public function isPassword($user, $password) {
		return ($GLOBALS['query_mgr']->queryNumRows("SELECT name, email, password FROM sys_users WHERE (name = '" . $user . "' || email = '" . $user . "') && password = '" . $password . "' LIMIT 1") > 0) ? true : false;
	}
	
	public function isLogin() {
		return $this->login;
	}
	
	public function login($method = "", $reload = false) {
		if($this->login || !$this->loaded)
			return;
			
		/*if(!isset($method) || empty($method))
			$method = 'default';*/
		
		if(!$reload) {
			switch($method) {
				case 'cookie' : {
					if($this->usage_login == "name")
						setcookie("name", $this->__get("name"), time()+60*60*24*7);
					else
						setcookie("email", $this->__get("email"), time()+60*60*24*7);
					setcookie("sso", $this->__get("sso"), time()+60*60*24*7);
					$this->login = true;
					break;
				}
				case 'session' : {
					if($this->usage_login == "name")
						$_SESSION['name'] = $this->__get("name");
					else
						$_SESSION['email'] = $this->__get("email");
					$_SESSION['sso'] = $this->__get("sso");
					$this->login = true;
					break;
				}
				case 'default' : {
					if($this->usage_login == "name")
						$_SESSION['name'] = $this->__get("name");
					else
						$_SESSION['email'] = $this->__get("email");
					$_SESSION['sso'] = $this->__get("sso");
					$this->login = true;
					break;
				}				
				default : {
					if($this->usage_login == "name")
						$_SESSION['name'] = $this->__get("name");
					else
						$_SESSION['email'] = $this->__get("email");
					$_SESSION['sso'] = $this->__get("sso");
					$this->login = true;
					break;
				}
			}
		} else
			$this->login = true;
	}
	
	public function logout() {
		setcookie("name", $this->__get("name"), time()-60*60*24*7+1);
		setcookie("email", $this->__get("email"), time()-60*60*24*7+1);
		setcookie("sso", $this->__get("sso"), time()-60*60*24*7+1);
		session_destroy();
	}
	
	//public function flush() {}
}

?>