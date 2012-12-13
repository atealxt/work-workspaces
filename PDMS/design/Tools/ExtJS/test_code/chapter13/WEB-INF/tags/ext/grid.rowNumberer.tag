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
	name="header"
	type="java.lang.String"
	required="false"
	description="
(String)Any valid text or HTML fragment to display in the header cell for the row number column (defaults to '').
" %>

<%@ attribute
	name="sortable"
	type="java.lang.Boolean"
	required="false"
	description="
(Boolean)True if the row number column is sortable (defaults to false).
" %>

<%@ attribute
	name="width"
	type="java.lang.Integer"
	required="false"
	description="
(Number)The default width in pixels of the row number column (defaults to 23).
" %>

<%-- Config params _____________________________END --%>

<%-- Events _____________________________START --%>
<%-- Events _____________________________END --%>

<%@ include file="inc/taglibs.jsp" %>
<%@ tag 
	import="org.apache.commons.beanutils.BeanUtils"
	dynamic-attributes="dynamicAttributes"
	description="A custom selection model that renders a column of checkboxes that can be toggled to select or deselect rows." %>
	
	<extutil:processTagAttributes 
		configVar="configMap" 
		eventsVar="eventsMap" 
		include="*" 
		exclude="items"
		tagJspContext="<%=jspContext %>"
		dynamicAttributes="${dynamicAttributes}" />	
        
	<%-- Process JSP body --%>
	<c:set var="item">
		new Ext.grid.RowNumberer({
			<c:forEach items="${configMap}" var="config">
	  			${config.key}:${config.value},
	  		</c:forEach>
	  		listeners:{
		  		<c:forEach items="${eventsMap}" var="event" varStatus="status">
		  			<c:if test="${fn:indexOf(event.value,'function(')==-1}">
			  			${event.key}:function(){${event.value}}			  			
		  			</c:if>
	  				<c:if test="${fn:indexOf(event.value,'function(')>-1}">
			  			${event.key}:${event.value}
		  			</c:if>
		  			${status.last?'':','}
		  		</c:forEach>
	  		}
	    }),
    </c:set>
    
			
	<extutil:setParentProperties 
		tag="<%=this%>" 
		items="${item}" />