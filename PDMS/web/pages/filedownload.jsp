<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<html>
    <head>
        <TITLE>PDMS-file-download</TITLE>

        <!--common file start-->
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <link rel="stylesheet" type="text/css" href="../extjs/resources/css/ext-all.css" />
        <link rel="stylesheet" type="text/css" href="../extjs/resources/css/ext-patch.css" />
        <link rel="stylesheet" type="text/css" href="../extjs/resources/css/xtheme-midnight.css" />
        <script type="text/javascript" src="../extjs/adapter/ext/ext-base.js"></script>
        <script type="text/javascript" src="../extjs/ext-all.js"></script>
        <script type="text/javascript" src="../extjs/source/locale/ext-lang-zh_CN.js"></script>
        <!--common file end-->

    </head>
    <body>
        <script type="text/javascript">
            <s:if test='downloadMessage != null'>
                Ext.onReady(function(){
                    Ext.MessageBox.alert('提示','<s:property value="downloadMessage"/>',callBack);
                    function callBack(){
                        window.close();
                    }
                });
            </s:if>
            <s:else>

            </s:else>
        </script>
    </body>
</html>
