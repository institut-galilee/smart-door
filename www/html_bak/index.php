<?php

include 'global.php';

if($habbo_mgr->isLogin()) {
	header("Location: http://" . $SITE['URL'] . "/me");
}

$lang_mgr->setPageId("index");
$templates_mgr->setPageId("index");

$templates_mgr->addTemplateBuffer($templates_mgr->addTemplate("head"));
$templates_mgr->addTemplateBuffer($templates_mgr->addTemplate("form"));

$templates_mgr->printBuffer();

?>