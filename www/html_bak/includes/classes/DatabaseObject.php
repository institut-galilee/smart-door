<?php

if(!defined(ACTIVED))
	exit("Not actived yet.");

interface DatabaseObject {	
	public function __get($field);
	
	public function __set($field, $value);
}

?>