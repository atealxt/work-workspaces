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

<%-- Added params (Panel/tools docs) _____________________________START --%>

<%@ attribute
	name="type"
	type="java.lang.String"
	required="true"
	description="
(String)Required. The type of tool to create. Values may be

    * toggle (Created by default when collapsible is true)
    * close
    * minimize
    * maximize
    * restore
    * gear
    * pin
    * unpin
    * right
    * left
    * up
    * down
    * refresh
    * minus
    * plus
    * help
    * search
    * save

" %>

<%@ attribute
	name="handler"
	type="java.lang.String"
	required="true"
	description="Function, Required. The function to call when clicked. Arguments passed are:
    * event : Ext.EventObject
      The click event.
    * toolEl : Ext.Element
      The tool Element.
    * Panel : Ext.Panel
      The host Panel
" %>

<%@ attribute
	name="scope"
	type="java.lang.String"
	required="false"
	description="Object, the scope in which to call the handler.
" %>

<%@ attribute
	name="qtip"
	type="java.lang.String"
	required="false"
	description="String/Object, A tip string, or a config argument to Ext.QuickTip.register
" %>

<%@ attribute
	name="hidden"
	type="java.lang.Boolean"
	required="false"
	description="Boolean, True to initially render hidden.
" %>

<%@ attribute
	name="on"
	type="java.lang.String"
	required="false"
	description="Function, A listener config object specifiying event listeners in the format of an argument to addListener
" %>

<%-- Added params (Panel/tools docs) _____________________________END --%>

<%@ include file="inc/taglibs.jsp" %>
<%@ tag 
	import="org.apache.commons.beanutils.BeanUtils"
	dynamic-attributes="dynamicAttributes"
	description="A button that renders into a tools." %>
	
	<extutil:processTagAttributes 
		configVar="configMap" 
		eventsVar="eventsMap" 
		javaScript="on,handler"
		exclude="type"
		include="*" 
		tagJspContext="<%=jspContext %>"
		dynamicAttributes="${dynamicAttributes}" />	
	
	<%-- Process JSP body --%>

	<jsp:doBody/>
				
	<c:set var="item">
		    {
		  		<c:forEach items="${configMap}" var="config">
	  				${config.key}:${config.value},
		  		</c:forEach>
  				id:'${type}'
		    },
	</c:set>
	
	<extutil:setParentProperties 
		tag="<%=this%>" 
		items="${item}" />