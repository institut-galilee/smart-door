<?php

if(!defined(ACTIVED))
	exit("Not actived yet.");
	
class DatabaseQuery {
	private $link = null;
	
	private $more_queries = false;
	
	public function DatabaseQuery($link) {
		$this->link = $link;
		$this->more_queries = true;
	}
	
	public function query($query) {
		if(!$this->more_queries)
			return null;
		
		return mysql_query($query, $this->link);
	}
	
	public function queryExecute($query) {
		if(!$this->more_queries)
			return false;
	
		$execute = mysql_query($query, $this->link);
		
		if($execute)
			return true;
		else
			return false;
	}
	
	public function queryNumRows($query) {
		if(!$this->more_queries)
			return 0;
	
		return mysql_num_rows($this->query($query));
	}
	
	public function queryFetchArray($query) {
		if(!$this->more_queries)
			return array();
			
		return mysql_fetch_array($this->query($query));
	}
	
	public function flush() {
		$this->more_queries = false;
	}
}

?>