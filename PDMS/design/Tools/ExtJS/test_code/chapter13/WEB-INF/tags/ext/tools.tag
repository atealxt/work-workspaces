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

<%@ attribute
	name="items"
	type="java.lang.String"
	required="false"
	description="(String) items container, used by tag only"
 %>
 
<%@ include file="inc/taglibs.jsp" %>
<%@ tag 
	import="org.apache.commons.beanutils.BeanUtils,javax.servlet.jsp.tagext.JspTag"
	dynamic-attributes="dynamicAttributes"
	description="An array of tool button configs to be added to the header tool area." %>
	
	
	<jsp:doBody />
	
	<c:set var="items">
		<%= BeanUtils.getProperty(this,"items") %>
	</c:set>
	
	<extutil:setParentProperties 
		tag="<%= this %>"  
		tools="${items}" />
