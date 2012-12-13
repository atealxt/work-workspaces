<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
    <head>
        <title>PDMS-topic</title>

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
        <style type="text/css">
            .quotecontent {
                margin: 0 5px 10px 5px;
                padding: 5px;
                border-color: #dbba75;
                border-width: 1px 1px 1px 1px;
                border-style: solid;
                font-weight: normal;
                font-size: 1em;
                line-height: 140%;
                font-family: "Lucida Grande", "Trebuchet MS", Helvetica, Arial, sans-serif;
                background-color: #FAFAFA;
                color: #444444;
            }
        </style>
        <script type="text/javascript" src="../csjs/topic.js"></script>
        <script type="text/javascript">
        </script>
        <!--personal file end-->

    </head>
    <body>

        <div id="t_body">

            <div id="t_reply">
            </div>

        </div>

        <div id="t_id" style="display:none;">${param.id}</div>
        <div id="t_mode" style="display:none;">${param.mode}</div>

    </body>
</html>
