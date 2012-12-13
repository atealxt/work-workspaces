<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<html>
    <head>
        <TITLE>PDMS-chart</TITLE>

        <!--common file start-->
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <!--
        <link rel="stylesheet" type="text/css" href="../extjs/resources/css/ext-all.css" />
        <link rel="stylesheet" type="text/css" href="../extjs/resources/css/ext-patch.css" />
        <link rel="stylesheet" type="text/css" href="../csjs/common_icon.css" />
        <script type="text/javascript" src="../extjs/adapter/ext/ext-base.js"></script>
        <script type="text/javascript" src="../extjs/ext-all.js"></script>
        <script type="text/javascript" src="../extjs/source/locale/ext-lang-zh_CN.js"></script>
        -->
        <!--common file end-->

        <!--personal file start-->
        <script type="text/javascript" src="../amcharts/ampie/swfobject.js"></script>
        <!--<script type="text/javascript" src="../csjs/chart.js"></script>-->
        <script type="text/javascript">
        </script>
        <!--personal file end-->
    </head>
    <body>

        <span id="flashcontent1">
            <strong>您的浏览器不支持此Flash插件</strong>
        </span>
        <span id="export"><!--<input type="button" id="exportImage" value="导出图片" onclick="exportImage()" />--></span>
        <hr noshade size="1">

        <script type="text/javascript">
            // <![CDATA[
            var so = new SWFObject("../amcharts/ampie/ampie.swf", "ampie", "530", "300", "8", "#FFFFFF");
            so.addVariable("path", "../amcharts/ampie/");
            so.addVariable("settings_file", encodeURIComponent("../amcharts/ampie/ampie_settings.xml"));
            so.addVariable("data_file", encodeURIComponent("<s:property value="pieDataFilePath"/>"));
            so.write("flashcontent1");
            // ]]>
        </script>

        <span id="flashcontent2">
            <!--<strong>您的浏览器不支持此Flash插件，请尝试升级Flash Player版本</strong>-->
        </span>

        <script type="text/javascript">
            // <![CDATA[
            var so = new SWFObject("../amcharts/amcolumn/amcolumn.swf", "amcolumn", "905", "400", "8", "#000000");
            so.addVariable("path", "../amcharts/amcolumn/");
            so.addVariable("settings_file", encodeURIComponent("../amcharts/amcolumn/amcolumn_settings.xml"));
            so.addVariable("data_file", encodeURIComponent("<s:property value="columnDataFilePath"/>"));
            so.addVariable("preloader_color", "#ffffff");
            so.write("flashcontent2");
            // ]]>
        </script>

    </body>
</html>
