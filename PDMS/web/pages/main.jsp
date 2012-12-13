<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<HTML>
    <HEAD>
        <TITLE>PDMS</TITLE>
        <link rel="Shortcut Icon" href="../images/pixelicious_s/earth.png">
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <link rel="stylesheet" type="text/css" href="../extjs/resources/css/ext-all.css" />
        <link rel="stylesheet" type="text/css" href="../extjs/resources/css/ext-patch.css" />
        <link rel="stylesheet" type="text/css" href="../csjs/common.css" />
        <link rel="stylesheet" type="text/css" href="../csjs/common_icon.css" />

    </HEAD>
    <BODY>

        <div id="loading-mask"></div>
        <div id="loading">
            <div class="loading-indicator">
                <img src="../images/logo/extanim32.gif" width="32" height="32" style="margin-right:8px;float:left;vertical-align:top;"/><br />
                <span id="loading-msg">页面信息读取中，请等待...</span>
            </div>
        </div>

        <!--common file start-->
        <script type="text/javascript">document.getElementById('loading-msg').innerHTML = '加载核心API...';</script>
        <script type="text/javascript" src="../extjs/adapter/ext/ext-base.js"></script>
        <script type="text/javascript">document.getElementById('loading-msg').innerHTML = '加载UI组件...';</script>
        <script type="text/javascript" src="../extjs/ext-all.js"></script>
        <script type="text/javascript">document.getElementById('loading-msg').innerHTML = '模块初始化...';</script>
        <script type="text/javascript" src="../extjs/source/locale/ext-lang-zh_CN.js"></script>
        <!--common file end-->

        <!--personal file start-->
        <script type="text/javascript" src="../csjs/themeChange.js"></script>
        <script type="text/javascript" src="../csjs/miframe.js"></script>
        <script type="text/javascript" src="../csjs/tabMenu.js"></script>
        <script type="text/javascript" src="../csjs/Portal.js"></script>
        <script type="text/javascript" src="../csjs/PortalColumn.js"></script>
        <script type="text/javascript" src="../csjs/Portlet.js"></script>
        <script type="text/javascript" src="../csjs/main.js"></script>
        <script type="text/javascript">document.getElementById('loading-msg').innerHTML = '加载完毕，请稍候...';</script>
        <script type="text/javascript">
        </script>
        <!--personal file end-->

        <!--<div id="all-content">-->
        <div id="page_head">
            <table width=100%>
                <tr>
                <td align=left>
                    <img src="../images/logo/pdms.gif" /><img src="../images/logo/pdms_small.png" />
                </td>
                <td align=right>
                    <table >
                        <tr>
                            <td align=center>
                                <a onclick="addTab('../pages/search.jsp','搜索')"><img src="../images/pixelicious/zoom.png"></a>
                            </td>
                            <td align=center>
                                <a onclick="addTab('../user/A0800UserAction.action','控制面板')"><img src="../images/pixelicious/user.png"></a>
                            </td>
                            <td align=center>
                                <a href="../pages/pdms.html" target="_blank"><img src="../images/pixelicious/coffee.png"></a>
                            </td>
                            <td align=center>
                                <a href="../login/A0102LogoutAction.action"><img src="../images/pixelicious/exit.png"></a>
                            </td>
                        </tr>
                        <tr>
                            <td align=center>搜索</td>
                            <td align=center>控制面板</td>
                            <td align=center>使用帮助</td>
                            <td align=center>退出登录</td>
                        </tr>
                    </table>
                </td>
            </table>
        </div>

        <div id="link">
            欢迎 <b id="usrN"><s:property value="mainVo.userName"/></b><br>
            <!--现在时刻<br>-->
        </div>

        <div id="dmain">
            <div id="dmainShow">

                <div id="topicAnalysis">
                    <!--最新主题-->
                    <div id="l_t">
                        <ul>
                            <s:iterator value="mainVo.ltTopicVo">
                                <li>
                                    <a onclick="addTab('<s:property value="url"/>','<s:property value="title"/>')">
                                        ● <s:property value="text"/>
                                    </a>
                                </li>
                            </s:iterator>
                        </ul>
                    </div>
                    <!--最新回复-->
                    <div id="l_r">
                        <ul>
                            <s:iterator value="mainVo.lrTopicVo">
                                <li>
                                    <a onclick="addTab('<s:property value="url"/>','<s:property value="title"/>')">
                                        ● <s:property value="text"/>
                                    </a>
                                </li>
                            </s:iterator>
                        </ul>
                    </div>
                    <!--未完结主题-->
                    <div id="l_u">
                        <ul>
                            <s:iterator value="mainVo.luTopicVo">
                                <li>
                                    <a onclick="addTab('<s:property value="url"/>','<s:property value="title"/>')">
                                        ● <s:property value="text"/>
                                    </a>
                                </li>
                            </s:iterator>
                        </ul>
                    </div>

                </div>

                <hr>
                <table width="99%">
                    <th class="plogo">LOGO</th>
                    <!--<th class="name">项目名称(项目简要的前**个字)</th>-->
                    <th class="pname">项目名称(项目简要)</th>
                    <th class="pdate">开发日期</th>
                    <th class="pnumber">项目人数</th>

                    <s:iterator value="mainVo.projectVo" status="listStat">
                        <s:if test='#listStat.odd == true'>
                            <tr class="odd">
                        </s:if>
                        <s:else>
                            <tr class="even">
                            </s:else>
                            <td class="plogo">
                                <a onclick="addTab('<s:property value="url"/>','<s:property value="title"/>')">
                                    <s:if test='logo != null'>
                                        <img src="../upload/<s:property value="logo"/>"/>
                                    </s:if>
                                    <s:else>
                                        无LOGO
                                    </s:else>
                                </a>

                            </td>
                            <td class="pname">
                                <a onclick="addTab('<s:property value="url"/>','<s:property value="title"/>')">
                                    <s:property value="name"/>
                                    (<s:property value="summary"/>)
                                </a>
                            </td>
                            <td class="pdate"><s:property value="developDate"/></td>
                            <td class="pnumber"><s:property value="menberCnt"/></td>
                        </tr>
                    </s:iterator>

                </table>


            </div>
        </div>

        <div id="page_foot" style="display:none;">
            <center>
                <iframe src="../pages/main_bottom.html" scrolling="no" frameborder="0"></iframe>
            </center>
        </div>

        <div id="newtab"></div>
        <div id="username" style="display:none;"><s:property value="mainVo.userName"/></div>
        <!--</div>-->
    </BODY>
</HTML>
