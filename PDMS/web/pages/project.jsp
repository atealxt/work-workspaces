<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<html>
    <head>
        <TITLE>PDMS-project</TITLE>

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
            a{color: #0000CD;}
            a:active {
                color: #0044B6;
                cursor:pointer;
            }
            a:hover {
                color: #FF0000;
                cursor:pointer;
            }
        </style>
        <script type="text/javascript" src="../csjs/project.js"></script>
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
                    <div id="p_body">

                        <div id="p_an">
                            <s:property value="projectVo.announcement" escape="false"/>
                        </div>

                        <div id="p_topic">
                        </div>

                    </div>
                </td>
            </tr>
        </table>-->
        <div id="p_body">

            <div id="p_an">
                <s:property value="projectVo.announcement" escape="false"/>
            </div>

            <div id="p_topic">
            </div>

        </div>

        <!--
            <div id="p_name" style="display:none;">项目公告(<s:property value="projectVo.name"/>)</div>
        -->
        <div id="p_name" style="display:none;">公告</div>
        <div id="p_id" style="display:none;"><s:property value="projectVo.id"/></div>

    </body>
</html>
