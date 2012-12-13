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

<%-- Added params _____________________________START --%>

<%@ attribute
	name="autoHide"
	type="java.lang.Boolean"
	required="false"
	description="
(Boolean)True to automatically hide the tooltip after the mouse exits the target element or after the dismissDelay has expired if set (defaults to true). If closable = true a close tool button will be rendered into the tooltip header.
" %>

<%@ attribute
	name="cls"
	type="java.lang.String"
	required="false"
	description="
(String)An optional extra CSS class that will be added to this component's Element (defaults to ''). This can be useful for adding customized styles to the component or any of its children using standard CSS rules.
" %>

<%@ attribute
	name="dismissDelay"
	type="java.lang.Integer"
	required="false"
	description="
(Number)Delay in milliseconds before the tooltip automatically hides (defaults to 5000). To disable automatic hiding, set dismissDelay = 0.
" %>

<%@ attribute
	name="target"
	type="java.lang.String"
	required="true"
	description="
(Mixed)The target HTMLElement, Ext.Element or id to associate with this quicktip (defaults to the document).
" %>

<%@ attribute
	name="text"
	type="java.lang.String"
	required="true"
	description="
(String)Quick tip text.
" %>

<%@ attribute
	name="title"
	type="java.lang.String"
	required="false"
	description="
(String)The title text to display in the panel header (defaults to ''). When a title is specified the header element will automatically be created and displayed unless header is explicitly set to false. If you don't want to specify a title at config time, but you may want one later, you must either specify a non-empty title (a blank space ' ' will do) or header:true so that the container element will get created.
" %>

<%@ attribute
	name="width"
	type="java.lang.Integer"
	required="false"
	description="
(Number)The width of this component in pixels (defaults to auto).
" %>

<%-- Added params _____________________________END --%>

<%@ include file="inc/taglibs.jsp" %>
<%@ tag 
	import="org.apache.commons.beanutils.BeanUtils"
	dynamic-attributes="dynamicAttributes"
	description="
	A specialized tooltip class for tooltips that can be specified in markup and automatically managed by the global Ext.QuickTips instance. See the QuickTips class header for additional usage details and examples." %>
	
	<extutil:processTagAttributes 
		configVar="configMap" 
		eventsVar="eventsMap" 
		include="*" 
		tagJspContext="<%=jspContext %>"
		dynamicAttributes="${dynamicAttributes}" />	
	
	<jsp:doBody/>
	<%-- Process JSP body --%>
		
	<c:set var="item">
		    {
		  		<c:forEach items="${configMap}" var="config" varStatus="status">
		  			${config.key}:${config.value}<c:if test="${!status.last}">,</c:if>
		  		</c:forEach>
		    }
	</c:set>

	<extutil:setCompExecutions>
		 Ext.QuickTips.register(${item});
	</extutil:setCompExecutions>
