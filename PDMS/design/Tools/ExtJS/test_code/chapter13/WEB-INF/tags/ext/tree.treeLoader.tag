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
	name="baseAttrs"
	type="java.lang.Object"
	required="false"
	description="
(Object)(optional) An object containing attributes to be added to all nodes created by this loader. If the attributes sent by the server have an attribute in this object, they take priority.
" %>

<%@ attribute
	name="baseParams"
	type="java.lang.Object"
	required="false"
	description="
(Object)(optional) An object containing properties which specify HTTP parameters to be passed to each request for child nodes.
" %>

<%@ attribute
	name="clearOnLoad"
	type="java.lang.Boolean"
	required="false"
	description="
(Boolean)(optional) Default to true. Remove previously existing child nodes before loading.
" %>

<%@ attribute
	name="dataUrl"
	type="java.lang.String"
	required="false"
	description="
(String)The URL from which to request a Json string which specifies an array of node definition objects representing the child nodes to be loaded.
" %>

<%@ attribute
	name="listeners"
	type="java.lang.Object"
	required="false"
	description="
(Object)A config object containing one or more event handlers to be added to this object during initialization. This should be a valid listeners config object as specified in the addListener example for attaching multiple handlers at once.
" %>

<%@ attribute
	name="preloadChildren"
	type="java.lang.Boolean"
	required="false"
	description="
(Boolean)If set to true, the loader recursively loads 'children' attributes when doing the first load on nodes.
" %>

<%@ attribute
	name="requestMethod"
	type="java.lang.String"
	required="false"
	description="
(String)The HTTP request method for loading data (defaults to 'POST').
" %>

<%@ attribute
	name="uiProviders"
	type="java.lang.Object"
	required="false"
	description="
(Object)(optional) An object containing properties which specify custom Ext.tree.TreeNodeUI implementations. If the optional uiProvider attribute of a returned child node is a string rather than a reference to a TreeNodeUI implementation, this that string value is used as a property name in the uiProviders object.
" %>

<%@ attribute
	name="url"
	type="java.lang.String"
	required="false"
	description="
(String)Equivalent to dataUrl.
" %>

<%-- Config params _____________________________END --%>

<%-- Events _____________________________START --%>

<%@ attribute
	name="onBeforeload"
	type="java.lang.Object"
	required="false"
	description="
( Object This, Object node, Object callback )Fires before a network request is made to retrieve the Json text which specifies a node's children.
" %>

<%@ attribute
	name="onLoad"
	type="java.lang.Object"
	required="false"
	description="
( Object This, Object node, Object response )Fires when the node has been successfuly loaded.
" %>

<%@ attribute
	name="onLoadexception"
	type="java.lang.Object"
	required="false"
	description="
( Object This, Object node, Object response )Fires if the network request failed.
" %>

<%-- Events _____________________________END --%>


<%@ include file="inc/taglibs.jsp" %>
<%@ tag 
	import="org.apache.commons.beanutils.BeanUtils"
	dynamic-attributes="dynamicAttributes"
	description="A TreeLoader provides for lazy loading of an Ext.tree.TreeNode's child nodes from a specified URL. The response must be a JavaScript Array definition whose elements are node definition objects. A server request is sent, and child nodes are loaded only when a node is expanded. The loading node's id is passed to the server under the parameter name 'node' to enable the server to produce the correct child nodes.To pass extra parameters, an event handler may be attached to the 'beforeload' event, and the parameters specified in the TreeLoader's baseParams property. This would pass an HTTP parameter called 'category' to the server containing the value of the Node's 'category' attribute." %>
	
	<extutil:processTagAttributes 
		configVar="configMap" 
		eventsVar="eventsMap" 
		include="*" 
		tagJspContext="<%=jspContext %>"
		dynamicAttributes="${dynamicAttributes}" />	
	
	<%-- Process JSP body --%>
	
	<jsp:doBody/>
		
	<c:set var="item">
		    new Ext.tree.TreeLoader({
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
		tag="<%= this %>"
		loader="${item}"
	 />
