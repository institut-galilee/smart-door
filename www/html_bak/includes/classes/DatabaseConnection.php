<?php

if(!defined(ACTIVED))
	exit("Not actived yet.");

class DatabaseConnection {
	private $host = "localhost";
	private $username = "root";
	private $password = "";
	private $database = "novadb";
	
	private $link = null;
	
	private $database_query = null;

	public function DatabaseConnection($host, $username, $password, $database) {
		if(!empty($host))
			$this->host = $host;
		if(!empty($username))
			$this->username = $username;
		if(!empty($password))
			$this->password = $password;
		if(!empty($database))
			$this->database = $database;
			
		$this->link = mysql_connect($host, $username, $password, true, MYSQL_CLIENT_COMPRESS);
		mysql_select_db($database, $this->link);
		
		$this->database_query = new DatabaseQuery($this->link);
	}
	
	public function getDatabaseQuery() {
		return $this->database_query;
	}
	
	public function flush() {
		mysql_close($this->link);
	}
}

?>