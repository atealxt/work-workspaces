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
	name="allowDomMove"
	type="java.lang.Boolean"
	required="false"
	description="
(Boolean)Whether the component can move the Dom node when rendering (defaults to true).
" %>

<%@ attribute
	name="applyTo"
	type="java.lang.String"
	required="false"
	description="
(Mixed)The id of the node, a DOM node or an existing Element corresponding to a DIV that is already present in the document that specifies some structural markup for this component. When applyTo is used, constituent parts of the component can also be specified by id or CSS class name within the main element, and the component being created may attempt to create its subcomponents from that markup if applicable. Using this config, a call to render() is not required. If applyTo is specified, any value passed for renderTo will be ignored and the target element's parent node will automatically be used as the component's container.
" %>

<%@ attribute
	name="autoHeight"
	type="java.lang.Boolean"
	required="false"
	description="
(Boolean)True to use height:'auto', false to use fixed height (defaults to false).
" %>

<%@ attribute
	name="autoShow"
	type="java.lang.Boolean"
	required="false"
	description="
(Boolean)True if the component should check for hidden classes (e.g. 'x-hidden' or 'x-hide-display') and remove them on render (defaults to false).
" %>

<%@ attribute
	name="autoWidth"
	type="java.lang.Boolean"
	required="false"
	description="
(Boolean)True to use width:'auto', false to use fixed width (defaults to false).
" %>

<%@ attribute
	name="cls"
	type="java.lang.String"
	required="false"
	description="
(String)An optional extra CSS class that will be added to this component's Element (defaults to ''). This can be useful for adding customized styles to the component or any of its children using standard CSS rules.
" %>

<%@ attribute
	name="ctCls"
	type="java.lang.String"
	required="false"
	description="
(String)An optional extra CSS class that will be added to this component's container (defaults to ''). This can be useful for adding customized styles to the container or any of its children using standard CSS rules.
" %>

<%@ attribute
	name="disabledClass"
	type="java.lang.String"
	required="false"
	description="
(String)CSS class added to the component when it is disabled (defaults to 'x-item-disabled').
" %>

<%@ attribute
	name="displayInfo"
	type="java.lang.Boolean"
	required="false"
	description="
(Boolean)True to display the displayMsg (defaults to false)
" %>

<%@ attribute
	name="displayMsg"
	type="java.lang.String"
	required="false"
	description="
(String)The paging status message to display (defaults to 'Displaying {0} - {1} of {2}'). Note that this string is formatted using the braced numbers 0-2 as tokens that are replaced by the values for start, end and total respectively. These tokens should be preserved when overriding this string if showing those values is desired.
" %>

<%@ attribute
	name="emptyMsg"
	type="java.lang.String"
	required="false"
	description="
(String)The message to display when no records are found (defaults to 'No data to display')
" %>

<%@ attribute
	name="height"
	type="java.lang.Integer"
	required="false"
	description="
(Number)The height of this component in pixels (defaults to auto).
" %>

<%@ attribute
	name="hideMode"
	type="java.lang.String"
	required="false"
	description="
(String)How this component should hidden. Supported values are 'visibility' (css visibility), 'offsets' (negative offset position) and 'display' (css display) - defaults to 'display'.
" %>

<%@ attribute
	name="hideParent"
	type="java.lang.Boolean"
	required="false"
	description="
(Boolean)True to hide and show the component's container when hide/show is called on the component, false to hide and show the component itself (defaults to false). For example, this can be used as a shortcut for a hide button on a window by setting hide:true on the button when adding it to its parent container.
" %>

<%@ attribute
	name="id"
	type="java.lang.String"
	required="false"
	description="
(String)The unique id of this component (defaults to an auto-assigned id).
" %>

<%@ attribute
	name="listeners"
	type="java.lang.Object"
	required="false"
	description="
(Object)A config object containing one or more event handlers to be added to this object during initialization. This should be a valid listeners config object as specified in the addListener example for attaching multiple handlers at once.
" %>

<%@ attribute
	name="pageSize"
	type="java.lang.Integer"
	required="false"
	description="
(Number)The number of records to display per page (defaults to 20)
" %>

<%@ attribute
	name="plugins"
	type="java.lang.Object"
	required="false"
	description="
(Object/Array)An object or array of objects that will provide custom functionality for this component. The only requirement for a valid plugin is that it contain an init method that accepts a reference of type Ext.Component. When a component is created, if any plugins are available, the component will call the init method on each plugin, passing a reference to itself. Each plugin can then call methods or respond to events on the component as needed to provide its functionality.
" %>

<%@ attribute
	name="renderTo"
	type="java.lang.String"
	required="false"
	description="
(Mixed)The id of the node, a DOM node or an existing Element that will be the container to render this component into. Using this config, a call to render() is not required.
" %>

<%@ attribute
	name="stateEvents"
	type="java.lang.String"
	required="false"
	description="
(Array)An array of events that, when fired, should trigger this component to save its state (defaults to none). These can be any types of events supported by this component, including browser or custom events (e.g., ['click', 'customerchange']).
" %>

<%@ attribute
	name="stateId"
	type="java.lang.String"
	required="false"
	description="
(String)The unique id for this component to use for state management purposes (defaults to the component id).
" %>

<%@ attribute
	name="store"
	type="java.lang.String"
	required="false"
	description="
(Ext.data.Store)The Ext.data.Store the paging toolbar should use as its data source (required).
" %>

<%@ attribute
	name="style"
	type="java.lang.String"
	required="false"
	description="
(String)A custom style specification to be applied to this component's Element. Should be a valid argument to Ext.Element.applyStyles.
" %>

<%@ attribute
	name="width"
	type="java.lang.Integer"
	required="false"
	description="
(Number)The width of this component in pixels (defaults to auto).
" %>

<%@ attribute
	name="xtype"
	type="java.lang.String"
	required="false"
	description="
(String)The registered xtype to create. This config option is not used when passing a config object into a constructor. This config option is used only when lazy instantiation is being used, and a child item of a Container is being specified not as a fully instantiated Component, but as a Component config object. The xtype will be looked up at render time up to determine what type of child Component to create.

 The predefined xtypes are listed here. 

 If you subclass Components to create your own Components, you may register them using Ext.ComponentMgr.registerType in order to be able to take advantage of lazy instantiation and rendering.
" %>

<%-- Config params _____________________________END --%>

<%-- Events _____________________________START --%>

<%@ attribute
	name="onBeforedestroy"
	type="java.lang.String"
	required="false"
	description="
( Ext.Component this )Fires before the component is destroyed. Return false to stop the destroy.
" %>

<%@ attribute
	name="onBeforehide"
	type="java.lang.String"
	required="false"
	description="
( Ext.Component this )Fires before the component is hidden. Return false to stop the hide.
" %>

<%@ attribute
	name="onBeforerender"
	type="java.lang.String"
	required="false"
	description="
( Ext.Component this )Fires before the component is rendered. Return false to stop the render.
" %>

<%@ attribute
	name="onBeforeshow"
	type="java.lang.String"
	required="false"
	description="
( Ext.Component this )Fires before the component is shown. Return false to stop the show.
" %>

<%@ attribute
	name="onBeforestaterestore"
	type="java.lang.Object"
	required="false"
	description="
( Ext.Component this, Object state )Fires before the state of the component is restored. Return false to stop the restore.
" %>

<%@ attribute
	name="onBeforestatesave"
	type="java.lang.Object"
	required="false"
	description="
( Ext.Component this, Object state )Fires before the state of the component is saved to the configured state provider. Return false to stop the save.
" %>

<%@ attribute
	name="onDestroy"
	type="java.lang.String"
	required="false"
	description="
( Ext.Component this )Fires after the component is destroyed.
" %>

<%@ attribute
	name="onDisable"
	type="java.lang.String"
	required="false"
	description="
( Ext.Component this )Fires after the component is disabled.
" %>

<%@ attribute
	name="onEnable"
	type="java.lang.String"
	required="false"
	description="
( Ext.Component this )Fires after the component is enabled.
" %>

<%@ attribute
	name="onHide"
	type="java.lang.String"
	required="false"
	description="
( Ext.Component this )Fires after the component is hidden.
" %>

<%@ attribute
	name="onMove"
	type="java.lang.Integer"
	required="false"
	description="
( Ext.Component this, Number x, Number y )Fires after the component is moved.
" %>

<%@ attribute
	name="onRender"
	type="java.lang.String"
	required="false"
	description="
( Ext.Component this )Fires after the component is rendered.
" %>

<%@ attribute
	name="onResize"
	type="java.lang.Integer"
	required="false"
	description="
( Ext.Component this, Number adjWidth, Number adjHeight, Number rawWidth, Number rawHeight )Fires after the component is resized.
" %>

<%@ attribute
	name="onShow"
	type="java.lang.String"
	required="false"
	description="
( Ext.Component this )Fires after the component is shown.
" %>

<%@ attribute
	name="onStaterestore"
	type="java.lang.Object"
	required="false"
	description="
( Ext.Component this, Object state )Fires after the state of the component is restored.
" %>

<%@ attribute
	name="onStatesave"
	type="java.lang.Object"
	required="false"
	description="
( Ext.Component this, Object state )Fires after the state of the component is saved to the configured state provider.
" %>

<%-- Events _____________________________END --%>

<%-- Added attribs _____________________________START --%>
 <%@ attribute
	name="items"
	type="java.lang.String"
	required="false"
	description="(Mixed) A single item, or an array of items to be added to this container. Each item can be any type of object based on Ext.Component, or a valid config object for such an item. If a single item is being passed, it should be passed directly as an object reference (e.g., items: {...}). Multiple items should be passed as an array of objects (e.g., items: [{...}, {...}]). "
 %>
 
  <%@ attribute
	name="toolbarType"
	type="java.lang.String"
	required="false"
	description="(String) Type of toolbar ('tbar','bbar','body'), default is 'body'."
 %>
 
 <%-- Added attribs _____________________________END --%>

<%@ include file="inc/taglibs.jsp" %>
<%@ tag 
	import="org.apache.commons.beanutils.BeanUtils"
	dynamic-attributes="dynamicAttributes"
	description="Basic Toolbar tag." %>
	
	<extutil:processTagAttributes 
		configVar="configMap" 
		eventsVar="eventsMap" 
		include="*" 
		exclude="items,toolbarType"
		tagJspContext="<%=jspContext %>"
		dynamicAttributes="${dynamicAttributes}" />	
	
	<jsp:doBody/>
	<%-- Process JSP body --%>
		
		
	<c:set var="store">
		<extutil:getParentProperty property="store" tag="<%=this %>"></extutil:getParentProperty>
	</c:set>	
	<c:set var="item">
		    new Ext.PagingToolbar({
		  		<c:forEach items="${configMap}" var="config">
		  			${config.key}:${config.value},
		  		</c:forEach>
				items:[<% 
							String items = BeanUtils.getProperty(this,"items");
							if (items!=null){
								jspContext.getOut().write(items.substring(0,items.length()-1));
							}
					%>],
				store:${store},	
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
	
	<%	

	
		String toolbarType = (String) jspContext.getAttribute("toolbarType");
		String item = (String)jspContext.getAttribute("item");
		if (toolbarType==null || "body".equals(toolbarType)) toolbarType= "items";
	
	%>
		
	<extutil:setParentProperty 
		tag="<%= this %>" 
		property="<%= toolbarType %>"
		value="${item}" />
					
	