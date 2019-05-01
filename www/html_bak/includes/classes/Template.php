<?php

if(!defined(ACTIVED))
	exit();

class Template {
	private $filename = "";
	private $extension = "";
	
	private $prefix = "{";
	private $suffix = "}";
	
	private $buffer = "";
	private $replacements = array();
	
	public function Template($filename) {
		$this->filename = $filename;
		
		$temp = explode("." ,basename($filename));
		unset($temp[0]);
		$this->extension = '.' . implode(".", $temp);
		
		$this->filename = str_replace($this->extension, "", $this->filename);
	}
	
	public function setPrefix($prefix) {
		$this->prefix = $prefix;
	}
	
	public function setSuffix($suffix) {
		$this->suffix = $suffix;
	}
	
	public function addParameter($parameter, $replacement) {
		$this->replacements[$parameter] = $replacement;
		$this->replacements = array_unique($this->replacements);
	}
	
	public function removeParameter($parameter) {
		unset($this->replacements[$parameter]);
	}
	
	public function getName() {
		return basename($this->filename, $this->extension);
	}
	
	private function replacemetParameters() {
		if(count($this->replacements) === 0)
			return;
	
		foreach($this->replacements as $parameter => $replacement)
		{
			$this->buffer = str_replace($this->prefix . $parameter . $this->suffix, $replacement, $this->buffer);
			unset($this->replacements[$parameter]);
		}
	}
	
	public function getBuffer() {
		if(!@ob_start("ob_gzhandler"))
			@ob_start();
		include $this->filename . $this->extension;
		$this->buffer = ob_get_clean();
		
		$this->replacemetParameters();
		
		return $this->buffer;
	}
	
	public function flush() {
		unset($this->filename, $this->extension, $this->prefix, $this->suffix, $this->buffer);
	
		$this->filename = "";
		$this->extension = "";
		
		$this->prefix = "{";
		$this->suffix = "}";
		
		$this->buffer = "";
		
		if(count($this->replacements) !== 0)
		{
			foreach($this->replacements as $parameter => $replacement)
				unset($this->replacements[$parameter]);
		}
	}
}

?>