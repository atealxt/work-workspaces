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

<%-- Config params _____________________________START --%>

<%@ attribute
	name="type"
	type="java.lang.String"
	required="false"
	description="(String)(Optional) The data type for conversion to displayable value. Possible values are: 
	auto (Default, implies no conversion)
    , string
    , int
    , float
    , boolean
    , date"
 %>

<%@ attribute
	name="dateFormat"
	type="java.lang.String"
	required="false"
	description="(Optional) A format String for the Date.parseDate function."
 %>

<%@ attribute
	name="name"
	type="java.lang.String"
	required="true"
	description="(String, required). Property name."
 %>
 
 <%@ attribute
	name="items"
	type="java.lang.String"
	required="false"
	description="(String) Container for other components."
 %>
 
 <%@ attribute
	name="value"
	type="java.lang.String"
	required="false"
	description="(Optional) A format String for the Date.parseDate function."
 %>
 
 
<%@ include file="inc/taglibs.jsp" %>

<%@ tag 
	import="org.apache.commons.beanutils.BeanUtils"
	description="PropertyGrid Item" %>
	
	<c:if test="${empty(value)}">		
		<jsp:doBody var="value" scope="page" />
	</c:if>
	
	<c:set var="item">
		<c:choose>
			<c:when test="${empty(type) or type=='string'}">
				<c:set var="value">"${value}"</c:set>
			</c:when>
			<c:when test="${type=='date'}">
				<c:set var="value">new Date(Date.parse('${value}'))</c:set>
			</c:when>
		</c:choose>
		<c:if test="<%= BeanUtils.getProperty(this,"items")!=null %>">
			<c:set var="value"><%= BeanUtils.getProperty(this,"items") %></c:set>
		</c:if>

		"${name}":${value},
	</c:set>
	
	<extutil:setParentProperties 
		tag="<%= this %>"
		source="${item}" />
