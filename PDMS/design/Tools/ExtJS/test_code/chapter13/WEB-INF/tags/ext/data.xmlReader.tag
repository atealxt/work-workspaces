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
	name="id"
	type="java.lang.String"
	required="false"
	description="
(String)The DomQuery path relative from the record element to the element that contains a record identifier value.
" %>

<%@ attribute
	name="record"
	type="java.lang.String"
	required="false"
	description="
(String)The DomQuery path to the repeated element which contains record information.
" %>

<%@ attribute
	name="success"
	type="java.lang.String"
	required="false"
	description="
(String)The DomQuery path to the success attribute used by forms.
" %>

<%@ attribute
	name="totalRecords"
	type="java.lang.String"
	required="false"
	description="
(String)The DomQuery path from which to retrieve the total number of records in the dataset. This is only needed if the whole dataset is not passed in one go, but is being paged from the remote server.
" %>

<%-- Config params _____________________________END --%>

<%-- Events _____________________________START --%>
<%-- Events _____________________________END --%>

<%-- Added properties _____________________________START --%>
<%@ attribute
	name="fields"
	type="java.lang.String"
	required="false"
	description="(String) Fields definition, generated automatically."
 %>
<%-- Added properties _____________________________END --%>


<%@ include file="inc/taglibs.jsp" %>
<%@ tag 
	import="org.apache.commons.beanutils.BeanUtils"
	dynamic-attributes="dynamicAttributes"
	description=" The Store class encapsulates a client side cache of Record objects which provide input data for Components such as the GridPanel, the ComboBox, or the DataView. A Store object uses its configured implementation of DataProxy to access a data object unless you call loadData directly and pass in your data. A Store object has no knowledge of the format of the data returned by the Proxy. A Store object uses its configured implementation of DataReader to create Record instances from the data object. These Records are cached and made available through accessor functions." %>
	
	<extutil:processTagAttributes 
		configVar="configMap" 
		eventsVar="eventsMap" 
		include="*" 
		javaScript="fields"
		tagJspContext="<%=jspContext %>"
		dynamicAttributes="${dynamicAttributes}" />	
	
	<%-- Process JSP body --%>
	
	<jsp:doBody/>
		
	<c:set var="item">
		    new Ext.data.XmlReader({
		  		<c:forEach items="${configMap}" var="config">
		  			<c:if test="${config.key!='fields'}">
		  				${config.key}:${config.value},
		  			</c:if>
		  		</c:forEach>
		  		<c:if test="<%= BeanUtils.getProperty(this,"fields")!=null %>">
			        fields:[<%= BeanUtils.getProperty(this,"fields") %>],
			    </c:if>
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
		    })
	</c:set>
	
	<extutil:setParentProperties 
		tag="<%= this %>"
		reader="${item}"
	 />
