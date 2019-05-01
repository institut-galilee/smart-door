<?php

include 'global.php';

$lang_mgr->setPageId("default");

if(!$habbo_mgr->isLogin()) {
	//$_SESSION['error'] = array();
	$_SESSION['error'] = $lang_mgr->__get("error_login");
	header("Location: http://" . $SITE['URL'] . "/");
}

?>
<!DOCTYPE html>
<html>
<head>
	<title>Nova - Home</title>
	<style>
		
	</style>
</head>
<body>
	<center>
		<header>
			<h1><?php echo $habbo_mgr->__get("name"); ?></h1>
		</header>
		<aside>
			&Eacute;ste eres t&uacute;:<br/>
			
			<?php /* -------------------- */ ?>
					<h1>NO DISPONIBLE</h1>
			<?php /* -------------------- */ ?>
			
			<br/><br/>
			<input type="button" onclick="javascript:openWindow('http://<?php echo $SITE['URL']; ?>/client');" value="ENTRAR AL HOTEL" />
		</aside>
	</center>
	<script>
		/*Object.prototype.size = function() {
			var size = 0;
			for(var property in this)
				size++;
			return size;
		}
	
		Object.prototype.length = Object.size;*/
		function count(obj) {
			var length = 0;
			for(var property in obj)
				length++;
			return length;
		}
	
		function extend(target, obj) {
			for(var property in obj)
				target[property] = obj[property];
		}
	
		var windowsOpened = 0;
		var defaultOpts = new Object();
		defaultOpts.windowFeatures = {
			"toolbar" : 0,
			"menubar" : 0,
			"resizable" : 0,
			"scrollbars" : 0,
			"width" : 730,
			"height" : 550
		};
		
		function openWindow(url) {
			var opts = "";
			var i = 0;
			
			for(var property in defaultOpts.windowFeatures) {				
				opts += property + "=" + defaultOpts.windowFeatures[property];
				if(i < count(defaultOpts.windowFeatures)-1)
					opts += ",";
				i++;
			}
			
			window.open(url, "Window" + windowsOpened, opts);
			windowsOpened++;
		}
		
		/*function openWindow(url, opts) {		
			window.open(url, "Window" + windowsOpened, defaultOpts);
			windowsOpened++;
		}
		
		function openWindow(url, name, opts) {
			window.open(url);
		}*/
	</script>
</body>
</html>