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
  
<%-- Added properties _____________________________START --%>
<%@ attribute
	name="fields"
	type="java.lang.String"
	required="false"
	description="(String) Fields definition, generated automatically. You can also insert string array of fileds manualy ('field1','field2',..)"
 %>
<%-- Added properties _____________________________END --%>

<%@ include file="inc/taglibs.jsp" %>
<%@ tag 
	import="org.apache.commons.beanutils.BeanUtils"
	description="Fields container" %>
	
	<%-- Process JSP body --%>
	
	<jsp:doBody/>
		
	<extutil:setParentProperties 
		removeComma="true"
		tag="<%= this %>" 
		fields="<%= BeanUtils.getProperty(this,"fields") %>" />
