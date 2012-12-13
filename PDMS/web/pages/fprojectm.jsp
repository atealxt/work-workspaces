<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<html>
    <head>
        <TITLE>PDMS-followed-project-manage</TITLE>

        <!--common file start-->
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <link rel="stylesheet" type="text/css" href="../extjs/resources/css/ext-all.css" />
        <link rel="stylesheet" type="text/css" href="../extjs/resources/css/ext-patch.css" />
        <link rel="stylesheet" type="text/css" href="../csjs/common_icon.css" />
        <script type="text/javascript" src="../extjs/adapter/ext/ext-base.js"></script>
        <script type="text/javascript" src="../extjs/ext-all.js"></script>
        <script type="text/javascript" src="../extjs/source/locale/ext-lang-zh_CN.js"></script>
        <!--common file end-->

        <!--personal file start-->
        <script type="text/javascript" src="../csjs/fprojectm.js"></script>
        <script type="text/javascript" src="../csjs/celltooltips.js"></script>
        <script type="text/javascript">
        </script>
        <!--personal file end-->
    </head>
    <body>

        <div id='panel'></div>
        <div id="grid-div-mis"></div>
        <!--<div id="grid-div-usr"></div>-->

        <div id ="id" style="display:none"><s:property value="project.id"/></div>
        <div id ="logo">
            <s:if test='httpPath == true'>
                <img src="<s:property value='project.logo'/>">
            </s:if>
            <s:else>
                <img src="../upload/<s:property value='project.logo'/>">
            </s:else>
        </div>
        <div id ="announcement"><s:property value="project.announcement" escape="false"/></div>
        <div id ="prjUsrs"><s:property value="prjUsrs"/></div>
    </body>
</html>
