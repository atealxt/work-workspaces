<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
    <head>
        <TITLE>PDMS-myfile</TITLE>

        <!--common file start-->
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <link rel="stylesheet" type="text/css" href="../extjs/resources/css/ext-all.css" />
        <link rel="stylesheet" type="text/css" href="../extjs/resources/css/ext-patch.css" />
        <link rel="stylesheet" type="text/css" href="../csjs/common_icon.css" />
        <!--<link rel="stylesheet" type="text/css" href="../csjs/common.css" />-->
        <script type="text/javascript" src="../extjs/adapter/ext/ext-base.js"></script>
        <script type="text/javascript" src="../extjs/ext-all.js"></script>
        <script type="text/javascript" src="../extjs/source/locale/ext-lang-zh_CN.js"></script>
        <!--common file end-->

        <!--personal file start-->
        <script type="text/javascript" src="../csjs/myfile.js"></script>
        <script type="text/javascript">
        </script>
        <!--personal file end-->
    </head>
    <body>
        <!--
        <table width=100%>
            <tr><td>&nbsp;<td></tr>
            <tr><td width=10></td>
                <td>
                <div id="grid-div"></div></td>
            </tr>
        </table>-->
        <div id="grid-div"></div>
        <div id="mode" style="display:none;">${param.mode}</div>
    </body>
</html>
