<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<html>
    <head>
        <TITLE>PDMS-my</TITLE>

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
        <script type="text/javascript" src="../csjs/my.js"></script>
        <script type="text/javascript">
        </script>
        <!--personal file end-->
    </head>
    <body>
        <div id="grid-div"></div>
        <div id='panel'></div>

        <div id="topicAnalysis">
            <div id="l_t"><!--我的主题-->
                <ul>
                    <s:iterator value="userVo.ltTopicVo">
                        <li>
                            <a onclick="parent.addTab('<s:property value="url"/>','<s:property value="title"/>')">
                                ● <s:property value="text"/>
                            </a>
                        </li>
                    </s:iterator>
                </ul>
            </div>
            <div id="l_r"><!--我的回复-->
                <ul>
                    <s:iterator value="userVo.lrTopicVo">
                        <li>
                            <a onclick="parent.addTab('<s:property value="url"/>','<s:property value="title"/>')">
                                ● <s:property value="text"/>
                            </a>
                        </li>
                    </s:iterator>
                </ul>
            </div>
        </div>

        <div id ="loginId" style="display:none"><s:property value="userVo.usrId"/></div>
        <div id ="name" style="display:none"><s:property value="userVo.usrName"/></div>
        <div id ="identity" style="display:none"><s:property value="userVo.usrIdent"/></div>
        <div id ="fprj" style="display:none"><s:property value="userVo.fPrj"/></div>

    </body>
</html>
