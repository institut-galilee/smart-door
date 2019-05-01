<?php

###################### PROTECCIÓN ######################
// Si se accede a éste archivo se cierra la conección
if(preg_match("/fix_url_string.php/" ,$_SERVER['REQUEST_URI']))
	exit("This file is not visible.");

if(isset($_G) && isset($_G['url_data_string'])) {
	
}	

?>