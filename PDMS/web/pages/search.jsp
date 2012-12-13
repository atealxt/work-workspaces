<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<html>
    <head>
        <TITLE>PDMS-search</TITLE>

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
        <script type="text/javascript" src="../csjs/search.js"></script>
        <script type="text/javascript">
        </script>
        <!--personal file end-->
    </head>
    <body onload="setup()">

        <div id="condition">

            <li>必须输入搜索条件 </li>
            <li>只能添加一个一级分类条件</li>
            <li>只能添加一个二级分类条件(搜索任务除外)</li>
            <li>可以添加多个三级分类条件(无关键字条件除外)</li>
            <li>不能重复添加相同分类条件</li>
            <hr>
            <table>
                <tr>
                    <td>
                        搜索条件<br>
                        <select id="s1"></select>
                        <select id="s2"></select>
                        <select id="s3"></select>
                    </td>
                    <td width="20"></td>
                    <td>
                        关键字：<br>
                        <input type="text" id="text1" value=""/>
                    </td>
                </tr>
            </table>

            <hr>
            <div>已经添加的搜索条件：</div>
            <div id="condition_code" style="display:none;">${param.cc}</div>
            <div id="condition_zh"></div>

        </div>

        <div id='panel'></div>

    </body>
</html>
