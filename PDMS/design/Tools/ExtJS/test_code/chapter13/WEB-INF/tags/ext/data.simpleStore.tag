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
	name="autoLoad"
	type="java.lang.Object"
	required="false"
	description="
(Boolean/Object)If passed, this store's load method is automatically called after creation with the autoLoad object
" %>

<%@ attribute
	name="baseParams"
	type="java.lang.Object"
	required="false"
	description="
(Object)An object containing properties which are to be sent as parameters on any HTTP request
" %>

<%@ attribute
	name="data"
	type="java.lang.String"
	required="false"
	description="
(Array)The multi-dimensional array of data
" %>

<%@ attribute
	name="fields"
	type="java.lang.String"
	required="false"
	description="
(Array)An array of field definition objects, or field name strings.
" %>

<%@ attribute
	name="id"
	type="java.lang.Integer"
	required="false"
	description="
(Number)The array index of the record id. Leave blank to auto generate ids.
" %>

<%@ attribute
	name="listeners"
	type="java.lang.Object"
	required="false"
	description="
(Object)A config object containing one or more event handlers to be added to this object during initialization. This should be a valid listeners config object as specified in the addListener example for attaching multiple handlers at once.
" %>

<%@ attribute
	name="proxy"
	type="java.lang.String"
	required="false"
	description="
(Ext.data.DataProxy)The Proxy object which provides access to a data object.
" %>

<%@ attribute
	name="pruneModifiedRecords"
	type="java.lang.String"
	required="false"
	description="
(boolean)True to clear all modified record information each time the store is loaded or when a record is removed. (defaults to false).
" %>

<%@ attribute
	name="reader"
	type="java.lang.String"
	required="false"
	description="
(Ext.data.Reader)The Reader object which processes the data object and returns an Array of Ext.data.record objects which are cached keyed by their id property.
" %>

<%@ attribute
	name="remoteSort"
	type="java.lang.String"
	required="false"
	description="
(boolean)True if sorting is to be handled by requesting the Proxy to provide a refreshed version of the data object in sorted order, as opposed to sorting the Record cache in place (defaults to false). 

If remote sorting is specified, then clicking on a column header causes the current page to be requested from the server with the addition of the following two parameters:

    * sort : String

      The name (as specified in the Record's Field definition) of the field to sort on.
    * dir : String

      The direction of the sort, 'ASC' or 'DESC'.


" %>

<%@ attribute
	name="sortInfo"
	type="java.lang.Object"
	required="false"
	description="
(Object)A config object in the format: {field: 'fieldName', direction: 'ASC|DESC'}
" %>

<%@ attribute
	name="storeId"
	type="java.lang.String"
	required="false"
	description="
(String)If passed, the id to use to register with the StoreMgr
" %>

<%@ attribute
	name="url"
	type="java.lang.String"
	required="false"
	description="
(String)If passed, an HttpProxy is created for the passed URL
" %>

<%-- Config params _____________________________END --%>

<%-- Events _____________________________START --%>

<%@ attribute
	name="onAdd"
	type="java.lang.Integer"
	required="false"
	description="
( Store this, Ext.data.Record[] records, Number index )Fires when Records have been added to the Store
" %>

<%@ attribute
	name="onBeforeload"
	type="java.lang.Object"
	required="false"
	description="
( Store this, Object options )Fires before a request is made for a new data object.If the beforeload handler returns falsethe load action will be canceled.
" %>

<%@ attribute
	name="onClear"
	type="java.lang.String"
	required="false"
	description="
( Store this )Fires when the data cache has been cleared.
" %>

<%@ attribute
	name="onDatachanged"
	type="java.lang.String"
	required="false"
	description="
( Store this )Fires when the data cache has changed, and a widget which is using this Storeas a Record cache should refresh its view.
" %>

<%@ attribute
	name="onLoad"
	type="java.lang.Object"
	required="false"
	description="
( Store this, Ext.data.Record[] records, Object options )Fires after a new set of Records has been loaded.
" %>

<%@ attribute
	name="onLoadexception"
	type="java.lang.String"
	required="false"
	description="
()Fires if an exception occurs in the Proxy during loading.Called with the signature of the Proxy's 'loadexception' event.
" %>

<%@ attribute
	name="onMetachange"
	type="java.lang.Object"
	required="false"
	description="
( Store this, Object meta )Fires when this store's reader provides new metadata (fields). This is currently only supported for JsonReaders.
" %>

<%@ attribute
	name="onRemove"
	type="java.lang.Integer"
	required="false"
	description="
( Store this, Ext.data.Record record, Number index )Fires when a Record has been removed from the Store
" %>

<%@ attribute
	name="onUpdate"
	type="java.lang.String"
	required="false"
	description="
( Store this, Ext.data.Record record, String operation )Fires when a Record has been updated
" %>

<%-- Events _____________________________END --%>


<%@ include file="inc/taglibs.jsp" %>
<%@ tag 
	import="org.apache.commons.beanutils.BeanUtils"
	dynamic-attributes="dynamicAttributes"
	description=" The Store class encapsulates a client side cache of Record objects which provide input data for Components such as the GridPanel, the ComboBox, or the DataView. A Store object uses its configured implementation of DataProxy to access a data object unless you call loadData directly and pass in your data. A Store object has no knowledge of the format of the data returned by the Proxy. A Store object uses its configured implementation of DataReader to create Record instances from the data object. These Records are cached and made available through accessor functions." %>
	
	<extutil:processTagAttributes 
		configVar="configMap" 
		eventsVar="eventsMap" 
		include="*" 
		javaScript="baseParams,data,proxy,reader,sortInfo"
		tagJspContext="<%=jspContext %>"
		dynamicAttributes="${dynamicAttributes}" />	
	
	<%-- Process JSP body --%>
	
	<jsp:doBody/>
	<c:set var="id">
		<extutil:getTagId tag="<%=this %>"></extutil:getTagId>
	</c:set>
		
	<c:set var="item">
		   ${id} = new Ext.data.SimpleStore({
		  		<c:forEach items="${configMap}" var="config">
		  			${config.key}:${config.value},
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
		    });
		    
	</c:set>
	
	<extutil:setExtCommons>${item}</extutil:setExtCommons>
	
	<extutil:setParentProperties 
		tag="<%= this %>"
		store="${id}"
	 />
