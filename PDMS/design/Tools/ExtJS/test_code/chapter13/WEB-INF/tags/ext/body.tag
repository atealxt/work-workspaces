<%-- 
	ExtJS Tag Library (ExtTLD)
    Copyright (C) 2008  Jaroslav Benc <jaroslav.benc@gmail.com>

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
	
	===========================================================================
	BY USING THIS LIBRARY YOU CONFIRM THAT YOU HAVE READ, UNDERSTOOD AND ACCEPT
	OUR ETHICAL CRITERIA LISTED ON THE EXTTLD WEBSITE (WWW.EXTTLD.COM)
	===========================================================================
--%>
  
<%@tag import="java.io.StringReader,java.io.BufferedReader"%>

<%@ attribute
	name="adapter"
	type="java.lang.String"
	required="false"
	description="
(String) Includes one of the ExtJS adapters (jquery, prototype, yui) default is ext-base.
" %>

<%@ attribute
	name="debug"
	type="java.lang.Boolean"
	required="false"
	description="
(Boolean) Includes ext-all-debug.js library.
" %>

<%@ attribute
	name="extLocation"
	type="java.lang.String"
	required="false"
	description="
(String) Overwrites ExtJS default location (js/ext-2.0).
" %>

<%@ attribute
	name="theme"
	type="java.lang.String"
	required="false"
	description="
(String) Includes xtheme css.
" %>

<%@ attribute
	name="locale"
	type="java.lang.String"
	required="false"
	description="
(String) Includes xtheme css.
" %>

<%@ attribute
	name="items"
	type="java.lang.String"
	required="false"
	description="
(String) Items container, generated automatically.
" %>

<%@ attribute
	name="msgTarget"
	type="java.lang.String"
	required="false"
	description="
(String) The location where error text should display. Should be one of the following values (defaults to 'qtip'): qtip, title, under, side.
" %>

<%@ attribute
	name="smProvider"
	type="java.lang.String"
	required="false"
	description="
(String) Configures the default state provider for your application (default is Ext.state.CookieProvider()).
" %>

<%@ attribute
	name="loadingMask"
	type="java.lang.String"
	required="false"
	description="
(Boolean) Enables default ExtJS loading mask.
" %>

<%@ include file="inc/taglibs.jsp" %>
<%@ tag 
	import="org.apache.commons.beanutils.BeanUtils,java.util.Scanner"
	dynamic-attributes="dynamicAttributes"
	description="" %>
	
	<c:set var="extLocation">
		${empty(extLocation)?"js/ext-2.0":extLocation}
	</c:set>
	
	<link rel="stylesheet" type="text/css" href="${extLocation}/resources/css/ext-all.css" />
	
	<c:if test="${!empty(theme)}">
		<link rel="stylesheet" type="text/css" href="${extLocation}/resources/css/xtheme-${theme}.css" />	
	</c:if>

	<script type="text/javascript" src="${extLocation}/adapter/ext/ext-base.js"></script>
	
	<c:if test="${adapter == 'jquery'}">
		<script type="text/javascript" src="${extLocation}/adapter/jquery/jquery.js"></script>
		<script type="text/javascript" src="${extLocation}/adapter/jquery/jquery-plugins.js"></script>
		<script type="text/javascript" src="${extLocation}/adapter/jquery/ext-jquery-adapter.js"></script>
	</c:if>
	
	<c:if test="${adapter == 'prototype'}">
		<script type="text/javascript" src="${extLocation}/adapter/prototype/prototype.js"></script>
		<script type="text/javascript" src="${extLocation}/adapter/prototype/scriptaculous.js"></script>
		<script type="text/javascript" src="${extLocation}/adapter/prototype/effects.js"></script>
		<script type="text/javascript" src="${extLocation}/adapter/prototype/ext-prototype.adapter.js"></script>
	</c:if>
	
	<c:if test="${adapter == 'yui'}">
		<script type="text/javascript" src="${extLocation}/adapter/yui/yui-utilities.js"></script>
		<script type="text/javascript" src="${extLocation}/adapter/yui/ext-yui-adapter.js"></script>
	</c:if>

	<script type="text/javascript" src="${extLocation}/ext-all${debug?"-debug":""}.js"></script>
	
	<c:if test="${!empty(locale)}">
  		<script type="text/javascript" src="${extLocation}/source/locale/ext-lang-${locale}.js"></script>
  	</c:if>
	
	<script>
		// sets default ExtJS blank image
		Ext.BLANK_IMAGE_URL = "${extLocation}/resources/images/default/s.gif";
	</script>
	
	<c:set var="ext_component_declarations" scope="request"/>
	<c:set var="ext_component_executions" scope="request"/>
	
	<script>
		Ext.QuickTips.init();
		
		<c:if test="${!empty(msgTarget)}">
			Ext.form.Field.prototype.msgTarget = '${msgTarget}';
		</c:if>
		
		Ext.state.Manager.setProvider(new ${empty(smProvider)?"Ext.state.CookieProvider()":smProvider});
	</script>

	<jsp:doBody var="pageHTML" />
	<%
		String pageHTML = (String)jspContext.getAttribute("pageHTML");
		String stripPageHtml = "";
		String pageLine = "";
		

		BufferedReader br = new BufferedReader(new StringReader(pageHTML));
        while((pageLine=br.readLine())!=null) {
            if (pageLine.trim().length()>0) stripPageHtml+=pageLine+"\n";
        }
		
		jspContext.setAttribute("pageHTML",stripPageHtml);
	%>
	<script>
		Ext.onReady(function(){
			${ext_component_commons}
		})
	</script>
	
	${pageHTML}
		
	<script>
		Ext.onReady(function(){
			try {		
				${ext_component_declarations};
				${ext_component_executions};
			} catch(e){};
			
			<c:if test="<%= BeanUtils.getProperty(this,"items")!=null %>">
				var bodyContainer = new Ext.Container({
					items:<%= BeanUtils.getProperty(this,"items") %>
				})
			</c:if>
		});
	</script>
	
	<c:if test="${loadingMask}">
		<style>
			#loading-mask{
				position:absolute;
				left:0;
				top:0;
			    width:100%;
			    height:100%;
			    z-index:20000;
			    background-color:white;
			}
			#loading{
				position:absolute;
				left:45%;
				top:40%;
				padding:2px;
				z-index:20001;
			    height:auto;
			}
			#loading img {
			    margin-bottom:5px;
			}
			#loading .loading-indicator{
				background:white;
				color:#555;
				font:bold 13px tahoma,arial,helvetica;
				padding:10px;
				margin:0;
			    text-align:center;
			    height:auto;
			}
			
		</style>
		
		<div id="loading-mask"></div>
		<div id="loading">
			<div class="loading-indicator"><img src="${extLocation}/docs/resources/extanim32.gif" width="32" height="32" style="margin-right:8px;" align="absmiddle"/>Loading...</div>
		</div>
		
		<script>
			Ext.onReady(function(){				
				setTimeout(function(){
			        Ext.get('loading').remove();
			        Ext.get('loading-mask').fadeOut({remove:true});
			    }, 250);
	
			});
		</script>
	</c:if>