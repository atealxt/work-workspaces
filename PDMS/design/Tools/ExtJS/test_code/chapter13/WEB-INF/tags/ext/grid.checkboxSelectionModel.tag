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
	description="(String)Any valid text or HTML fragment to display in the header cell for the checkbox column (defaults to '<div class='x-grid3-hd-checker'> </div>'). The default CSS class of 'x-grid3-hd-checker' displays a checkbox in the header and provides support for automatic check all/none behavior on header click. This string can be replaced by any valid HTML fragment, including a simple text string (e.g., 'Select Rows'), but the automatic check all/none behavior will only work if the 'x-grid3-hd-checker' class is supplied."
 %>

<%@ attribute
	name="singleSelect"
	type="java.lang.Boolean"
	required="false"
	description="(Boolean)True to allow selection of only one row at a time (defaults to false)"
 %>

<%@ attribute
	name="sortable"
	type="java.lang.Boolean"
	required="false"
	description="(Boolean)True if the checkbox column is sortable (defaults to false)."
 %>

<%@ attribute
	name="width"
	type="java.lang.Integer"
	required="false"
	description="(Number)The default width in pixels of the checkbox column (defaults to 20)."
 %>

<%-- Config params _____________________________END --%>



<%-- Events _____________________________START --%>

<%@ attribute
	name="onBeforerowselect"
	type="java.lang.Integer"
	required="false"
	description="
( SelectionModel this, Number rowIndex, Boolean keepExisting, Record record )Fires when a row is being selected, return false to cancel.
Listeners will be called with the following arguments:

    * this : SelectionModel
    * rowIndex : Number
      The index to be selected
    * keepExisting : Boolean
      False if other selections will be cleared
    * record : Record
      The record to be selected

" %>

<%@ attribute
	name="onRowdeselect"
	type="java.lang.Integer"
	required="false"
	description="
( SelectionModel this, Number rowIndex, Record record )Fires when a row is deselected.
Listeners will be called with the following arguments:

    * this : SelectionModel
    * rowIndex : Number
    * record : Record

" %>

<%@ attribute
	name="onRowselect"
	type="java.lang.Integer"
	required="false"
	description="
( SelectionModel this, Number rowIndex, Ext.data.Record r )Fires when a row is selected.
Listeners will be called with the following arguments:

    * this : SelectionModel
    * rowIndex : Number
      The selected index
    * r : Ext.data.Record
      The selected record

" %>

<%@ attribute
	name="onSelectionchange"
	type="java.lang.String"
	required="false"
	description="
( SelectionModel this )Fires when the selection changes
Listeners will be called with the following arguments:

    * this : SelectionModel

" %>

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
		new Ext.grid.CheckboxSelectionModel({
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
		items="${item}"
    	sm="${item}"/>