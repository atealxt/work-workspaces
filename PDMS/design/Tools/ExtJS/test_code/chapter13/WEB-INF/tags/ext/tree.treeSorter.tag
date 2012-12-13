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
	name="caseSensitive"
	type="java.lang.Boolean"
	required="false"
	description="
(Boolean)true for case sensitive sort (defaults to false)
" %>

<%@ attribute
	name="dir"
	type="java.lang.String"
	required="false"
	description="
(String)The direction to sort (asc or desc) (defaults to asc)
" %>

<%@ attribute
	name="folderSort"
	type="java.lang.Boolean"
	required="false"
	description="
(Boolean)True to sort leaf nodes under non leaf nodes
" %>

<%@ attribute
	name="leafAttr"
	type="java.lang.String"
	required="false"
	description="
(String)The attribute used to determine leaf nodes in folder sort (defaults to 'leaf')
" %>

<%@ attribute
	name="property"
	type="java.lang.String"
	required="false"
	description="
(String)The named attribute on the node to sort by (defaults to text)
" %>

<%@ attribute
	name="sortType"
	type="java.lang.String"
	required="false"
	description="
(Function)A custom 'casting' function used to convert node values before sorting
" %>

<%-- Config params _____________________________END --%>

<%-- Events _____________________________START --%>
<%-- Events _____________________________END --%>

<%@ include file="inc/taglibs.jsp" %>
<%@ tag 
	import="org.apache.commons.beanutils.BeanUtils"
	dynamic-attributes="dynamicAttributes"
	description="Provides sorting of nodes in a TreePanel" %>
	
	<extutil:processTagAttributes 
		configVar="configMap" 
		eventsVar="eventsMap" 
		include="*" 
		tagJspContext="<%=jspContext %>"
		dynamicAttributes="${dynamicAttributes}" />	
	
	<%-- Process JSP body --%>
	
	<c:set var="parentId">
		<extutil:getParentProperty tag="<%= this %>" property="id" />
	</c:set>
	<c:if test="${parentId==''}">
		<c:set var="parentId">
			<extutil:getParentTagId tag="<%= this %>" />
		</c:set>
	</c:if>
		
	<jsp:doBody/>
		
	<c:set var="item">
		    new Ext.tree.TreeSorter(${parentId},{
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
		    });
	</c:set>
	
	<extutil:setCompExecutions>${item}</extutil:setCompExecutions>