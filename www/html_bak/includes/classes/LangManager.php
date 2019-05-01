<?php

if(!defined(ACTIVED))
	exit("Actived not yet.");

class LangManager {
	private $lang_text = array();
	private $lang_path = "";
	private $lang_file = "";
	private $lang_page_id = false;

	public function LangManager($lang_path) {
		$this->lang_path = (substr($lang_path, -1) == "/" || substr($lang_path, -1) == DIRECTORY_SEPARATOR) ? $lang_path : $lang_path . DIRECTORY_SEPARATOR;
	}
	
	public function setLang($lang_file) {			
		$this->lang_file = $lang_file;
	}
	
	private function includeLang() {
		$page_id = $this->lang_page_id;
		include $this->lang_path . ((substr($this->lang_file, -4) == ".php") ? $this->lang_file : $this->lang_file . '.php');
		$this->lang_text = array_merge($this->lang_text, $lang);
	}
	
	public function setPageId($page_id) {
		$this->lang_page_id = $page_id;
		
		$this->includeLang();
	}
	
	public function __get($text) {
		if(!isset($this->lang_text[$text]))
			return null;
		
		return $this->lang_text[$text];
	}
}

?>