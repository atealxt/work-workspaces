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
	name="callbackParam"
	type="java.lang.String"
	required="false"
	description="
(String)(Optional) The name of the parameter to pass to the server which tells the server the name of the callback function set up by the load call to process the returned data object. Defaults to 'callback'.

The server-side processing must read this parameter value, and generate javascript output which calls this named function passing the data object as its only parameter.
" %>

<%@ attribute
	name="listeners"
	type="java.lang.Object"
	required="false"
	description="
(Object)A config object containing one or more event handlers to be added to this object during initialization. This should be a valid listeners config object as specified in the addListener example for attaching multiple handlers at once.
" %>

<%@ attribute
	name="nocache"
	type="java.lang.Boolean"
	required="false"
	description="
(Boolean)(Optional) Defaults to true. Disable cacheing by adding a unique parameter name to the request.
" %>

<%@ attribute
	name="timeout"
	type="java.lang.Integer"
	required="false"
	description="
(Number)(Optional) The number of milliseconds to wait for a response. Defaults to 30 seconds.
" %>

<%@ attribute
	name="url"
	type="java.lang.String"
	required="false"
	description="
(String)The URL from which to request the data object.
" %>

<%-- Config params _____________________________END --%>

<%-- Events _____________________________START --%>

<%@ attribute
	name="onBeforeload"
	type="java.lang.Object"
	required="false"
	description="
( Object This, Object params )Fires before a network request is made to retrieve a data object.
" %>

<%@ attribute
	name="onLoad"
	type="java.lang.Object"
	required="false"
	description="
( Object This, Object o, Object arg )Fires before the load method's callback is called.
" %>

<%@ attribute
	name="onLoadexception"
	type="java.lang.Object"
	required="false"
	description="
( Object This, Object o, Object arg, Object e )Fires if an Exception occurs during data retrieval.
" %>

<%-- Events _____________________________END --%>


<%-- Config params Connection _____________________________START --%>

<%@ attribute
	name="autoAbort"
	type="java.lang.Boolean"
	required="false"
	description="
(Boolean)(Optional) Whether this request should abort any pending requests. (defaults to false)
" %>

<%@ attribute
	name="defaultHeaders"
	type="java.lang.Object"
	required="false"
	description="
(Object)(Optional) An object containing request headers which are added to each request made by this object. (defaults to undefined)
" %>

<%@ attribute
	name="disableCaching"
	type="java.lang.Boolean"
	required="false"
	description="
(Boolean)(Optional) True to add a unique cache-buster param to GET requests. (defaults to true)
" %>

<%@ attribute
	name="extraParams"
	type="java.lang.Object"
	required="false"
	description="
(Object)(Optional) An object containing properties which are used as extra parameters to each request made by this object. (defaults to undefined)
" %>

<%@ attribute
	name="method"
	type="java.lang.String"
	required="false"
	description="
(String)(Optional) The default HTTP method to be used for requests. (defaults to undefined; if not set but parms are present will use POST, otherwise GET)
" %>


<%-- Config params Connection _____________________________END --%>


<%-- Events Connection _____________________________START --%>

<%@ attribute
	name="onBeforerequest"
	type="java.lang.Object"
	required="false"
	description="
( Connection conn, Object options )Fires before a network request is made to retrieve a data object.
" %>

<%@ attribute
	name="onRequestcomplete"
	type="java.lang.Object"
	required="false"
	description="
( Connection conn, Object response, Object options )Fires if the request was successfully completed.
" %>

<%@ attribute
	name="onRequestexception"
	type="java.lang.Object"
	required="false"
	description="
( Connection conn, Object response, Object options )Fires if an error HTTP status was returned from the server.See HTTP Status Code Definitionsfor details of HTTP status codes.
" %>

<%-- Events Connection _____________________________END --%>


<%@ include file="inc/taglibs.jsp" %>
<%@ tag 
	import="org.apache.commons.beanutils.BeanUtils"
	dynamic-attributes="dynamicAttributes"
	description=" The Store class encapsulates a client side cache of Record objects which provide input data for Components such as the GridPanel, the ComboBox, or the DataView. A Store object uses its configured implementation of DataProxy to access a data object unless you call loadData directly and pass in your data. A Store object has no knowledge of the format of the data returned by the Proxy. A Store object uses its configured implementation of DataReader to create Record instances from the data object. These Records are cached and made available through accessor functions." %>
	
	<extutil:processTagAttributes 
		configVar="configMap" 
		eventsVar="eventsMap" 
		include="*" 
		javaScript="defaultHeaders,extraParams"
		tagJspContext="<%=jspContext %>"
		dynamicAttributes="${dynamicAttributes}" />	
	
	<%-- Process JSP body --%>
	
	<jsp:doBody/>
		
	<c:set var="item">
		    new Ext.data.ScriptTagProxy({
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
		proxy="${item}"
	 />
