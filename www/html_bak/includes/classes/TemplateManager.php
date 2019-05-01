<?php

##############################################
// Aun no se ha inciado el nucleo
if(!defined(ACTIVED))
	exit();

class TemplateManager {
	private $templates_path = "";
	private $templates_extension = ".tpl";
	private $templates_page_id = false;
	
	private $templates_prefix = "{";
	private $templates_suffix = "}";
	
	private $templates = array();
	private $templates_buffer = "";
	private $templates_replacements = array();

	public function TemplateManager($templates_path, $templates_extension = "") {
		$this->templates_path = (substr($templates_path, -1) == "/" || substr($templates_path, -1) == DIRECTORY_SEPARATOR) ? $templates_path : $templates_path . DIRECTORY_SEPARATOR;
		
		if(!empty($templates_extension))
			$this->templates_extension = (substr($templates_extension, 0, 1) == ".") ? $templates_extension : '.' . $templates_extension;
	}
	
	public function setPageId($page_id)
	{
		$this->templates_page_id = (substr($page_id, -1) == "/" || substr($page_id, -1) == DIRECTORY_SEPARATOR) ? $page_id : $page_id . DIRECTORY_SEPARATOR;
	}
	
	public function setPrefix($templates_prefix) {
		$this->templates_prefix = $templates_prefix;
	}
	
	public function setSuffix($templates_suffix) {
		$this->templates_suffix = $templates_suffix;
	}
	
	public function addParameter($parameter, $replacement) {
		$this->templates_replacements[$parameter] = $replacement;
		$this->templates_replacements = array_unique($this->templates_replacements);
	}
	
	public function removeParameter($parameter) {
		unset($this->templates_replacements[$parameter]);
	}
	
	public function addTemplate($template_name) {
		if(!file_exists($this->templates_path . (($this->templates_page_id == false) ? "" : $this->templates_page_id) . $template_name . $this->templates_extension))
			return;
	
		$templates[$template_name] = new Template($this->templates_path . (($this->templates_page_id == false) ? "" : $this->templates_page_id) . $template_name . $this->templates_extension);
		
		if($this->templates_prefix != "{")
			$templates[$template_name]->setPrefix($this->templates_suffix);
		
		if($this->templates_suffix != "}")
			$templates[$template_name]->setSuffix($this->templates_suffix);
		
		return $templates[$template_name];
	}
	
	public function addTemplateBuffer(Template $template)
	{
		$this->templates_buffer .= $template->getBuffer();
	}
	
	private function replacemetParameters() {
		if(count($this->templates_replacements) === 0)
			return;
	
		foreach($this->templates_replacements as $parameter => $replacement)
		{
			$this->templates_buffer = str_replace($this->templates_prefix . $parameter . $this->templates_suffix, $replacement, $this->templates_buffer);
			unset($this->templates_replacements[$parameter]);
		}
	}
	
	public function printBuffer() {
		$this->replacemetParameters();
		
		echo $this->templates_buffer;
	}
	
	public function flush()
	{
		unset($this->templates_path, $this->templates_extension, $this->templates_page_id, $this->templates_prefix, $this->templates_suffix, $this->templates_buffer);
	
		$this->templates_path = "";
		$this->templates_extension = ".tpl";
		$this->templates_page_id = false;
	
		$this->templates_prefix = "{";
		$this->templates_suffix = "}";
	
		if(count($this->templates) !== 0)
		{
			foreach($this->templates as $template => $instance)
			{
				$this->templates[$template]->flush();
				unset($this->templates[$template]);
			}
		}
		
		$this->templates_buffer = "";
		
		if(count($this->templates_replacements) !== 0)
		{
			foreach($this->templates_replacements as $parameter => $replacement)
				unset($this->templates_replacements[$parameter]);
		}
	}
}

?>