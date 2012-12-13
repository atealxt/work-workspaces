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

<%-- Added attriutes _________________START --%> 
 <%@ attribute
	name="items"
	type="java.lang.String"
	required="false"
	description="(String) items container, used by tag only"
 %>

<%@ attribute
	name="fields"
	type="java.lang.String"
	required="false"
	description="(String) Fields definition, generated automatically."
 %>
 
 <%@ attribute
	name="colSize"
	type="java.lang.Integer"
	required="false"
	description="(Integer) Column size, generated automatically."
 %>
 
  <%@ attribute
	name="sm"
	type="java.lang.String"
	required="false"
	description="(String) Panel Selection model, generated automatically."
 %>

<%-- Added attriutes _________________END --%> 
<%@ include file="inc/taglibs.jsp" %>

<%@ tag 
	import="org.apache.commons.beanutils.BeanUtils"
	dynamic-attributes="dynamicAttributes"
	description="Simple tag collectiong col models" %>

	<%
		BeanUtils.setProperty(this,"colSize",0);
	%>
	<%-- Process JSP body --%>
	<jsp:doBody />
	
	<extutil:setParentProperties 
		tag="<%= this %>" 
		removeComma="true"
		fields="<%= BeanUtils.getProperty(this,"fields")%>" 
		colModel="<%= BeanUtils.getProperty(this,"items")%>"
		sm="<%= BeanUtils.getProperty(this,"sm")%>"/>
