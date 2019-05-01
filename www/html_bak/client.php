<?php

	include 'global.php';
	
	if(!$habbo_mgr->isLogin())
		header("Location: http://localhost/");
	
	//localhost
	define("URL", "http://localhost");
	define("PATH", "http://localhost/r18_20071120_0509_3514_057608660d344f70a14839f120f50f51");
	define("DCR", PATH . "/habbo.dcr");
	define("VARS", PATH . "/external_vars.php?" . time());
	define("TEXTS", PATH . "/external_texts.php");
	define("RELOAD", "http://localhost/client.php");
	define("IP", gethostbyname("localhost"));
	define("PORT", 30000);
	define("MPORT", 30001);
	define("SSO_ACTIVE", TRUE);
	if(SSO_ACTIVE)
		define("SSO", "use.sso.ticket=1;sso.ticket=" . $habbo_mgr->__get("sso"));
	else
		define("SSO", "use.sso.ticket=0");
	//;sso.ticket=ef46e570-ba3e-e99c-4ab4-4600bec1b30e
?>
<!DOCTYPE html />
<html>
	<head>
		<title>Nova - Client</title>
		<style>
			html, body
			{
				margin: 0;
				padding: 0;
				width: 100%;
				height: 100%;
			}
			
			body
			{
				background-color: #000;			
			}
		</style>
	</head>
	<body>
		<center>
		<object classid="clsid:166B1BCA-3F9C-11CF-8075-444553540000" codebase="http://download.macromedia.com/pub/shockwave/cabs/director/sw.cab#version=8,5,1,0" id="habbo" width="720" height="540">
			<param name="src" value="<?php echo DCR; ?>">
			<param name="swRemote" value="swSaveEnabled='true' swVolume='true' swRestart='false' swPausePlay='false' swFastForward='false' swTitle='Habbo Hotel' swContextMenu='true' ">
			<param name="swStretchStyle" value="none">
			<param name="swText" value="">
			<param name="bgColor" value="#000000">
			<param name="sw6" value="<?php echo SSO; ?>">
			<param name="sw2" value="connection.info.host=<?php echo IP; ?>;connection.info.port=<?php echo PORT; ?>">
			<param name="sw4" value="connection.mus.host=<?php echo IP; ?>;connection.mus.port=<?php echo MPORT; ?>">
			<param name="sw3" value="client.reload.url=<?php echo RELOAD; ?>">
			<param name="sw1" value="site.url=<?php echo URL; ?>;url.prefix=<?php echo URL; ?>">
			<param name="sw5"value="external.variables.txt=<?php echo VARS; ?>;external.texts.txt=<?php echo TEXTS; ?>">
			<embed AllowScriptAccess="never" src="<?php echo DCR; ?>" bgColor="#000000" width="720" height="540" swRemote="swSaveEnabled='true' swVolume='true' swRestart='false' swPausePlay='false' swFastForward='false' swTitle='Habbo Hotel' swContextMenu='true'" swStretchStyle="none" swText="" type="application/x-director" pluginspage="http://www.macromedia.com/shockwave/download/" 
			sw6="<?php echo SSO; ?>" sw2="connection.info.host=<?php echo IP; ?>;connection.info.port=<?php echo PORT; ?>" sw4="connection.mus.host=<?php echo IP; ?>;connection.mus.port=<?php echo MPORT; ?>" sw3="client.reload.url=<?php echo RELOAD; ?>" sw1="site.url=<?php echo URL; ?>;url.prefix=<?php echo URL; ?>" sw5="external.variables.txt=<?php echo VARS; ?>;external.texts.txt=<?php echo TEXTS; ?>"></embed>
		</object>
		</center>
		<script>
			window.onrezise = function() {
				return false;
			}
		</script>
	</body>
</html>