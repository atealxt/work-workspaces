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
	name="arrowHandler"
	type="java.lang.String"
	required="false"
	description="
(Function)A function called when the arrow button is clicked (can be used instead of click event)
" %>

<%@ attribute
	name="arrowTooltip"
	type="java.lang.String"
	required="false"
	description="
(String)The title attribute of the arrow
" %>

<%@ attribute
	name="autoShow"
	type="java.lang.Boolean"
	required="false"
	description="
(Boolean)True if the component should check for hidden classes (e.g. 'x-hidden' or 'x-hide-display') and remove them on render (defaults to false).
" %>

<%@ attribute
	name="clickEvent"
	type="java.lang.String"
	required="false"
	description="
(String)The type of event to map to the button's event handler (defaults to 'click')
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
	name="disabled"
	type="java.lang.Boolean"
	required="false"
	description="
(Boolean)True to start disabled (defaults to false)
" %>

<%@ attribute
	name="disabledClass"
	type="java.lang.String"
	required="false"
	description="
(String)CSS class added to the component when it is disabled (defaults to 'x-item-disabled').
" %>

<%@ attribute
	name="enableToggle"
	type="java.lang.Boolean"
	required="false"
	description="
(Boolean)True to enable pressed/not pressed toggling (defaults to false)
" %>

<%@ attribute
	name="handleMouseEvents"
	type="java.lang.Boolean"
	required="false"
	description="
(Boolean)False to disable visual cues on mouseover, mouseout and mousedown (defaults to true)
" %>

<%@ attribute
	name="handler"
	type="java.lang.String"
	required="false"
	description="
(Function)A function called when the button is clicked (can be used instead of click event)
" %>

<%@ attribute
	name="hidden"
	type="java.lang.Boolean"
	required="false"
	description="
(Boolean)True to start hidden (defaults to false)
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
	name="icon"
	type="java.lang.String"
	required="false"
	description="
(String)The path to an image to display in the button (the image will be set as the background-image CSS property of the button by default, so if you want a mixed icon/text button, set cls:'x-btn-text-icon')
" %>

<%@ attribute
	name="iconCls"
	type="java.lang.String"
	required="false"
	description="
(String)A css class which sets a background image to be used as the icon for this button
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
	name="menu"
	type="java.lang.String"
	required="false"
	description="
(Mixed)Standard menu attribute consisting of a reference to a menu object, a menu id or a menu config blob (defaults to undefined).
" %>

<%@ attribute
	name="menuAlign"
	type="java.lang.String"
	required="false"
	description="
(String)The position to align the menu to (see Ext.Element.alignTo for more details, defaults to 'tl-bl?').
" %>

<%@ attribute
	name="minWidth"
	type="java.lang.Integer"
	required="false"
	description="
(Number)The minimum width for this button (used to give a set of buttons a common width)
" %>

<%@ attribute
	name="plugins"
	type="java.lang.Object"
	required="false"
	description="
(Object/Array)An object or array of objects that will provide custom functionality for this component. The only requirement for a valid plugin is that it contain an init method that accepts a reference of type Ext.Component. When a component is created, if any plugins are available, the component will call the init method on each plugin, passing a reference to itself. Each plugin can then call methods or respond to events on the component as needed to provide its functionality.
" %>

<%@ attribute
	name="pressed"
	type="java.lang.Boolean"
	required="false"
	description="
(Boolean)True to start pressed (only if enableToggle = true)
" %>

<%@ attribute
	name="renderTo"
	type="java.lang.String"
	required="false"
	description="
(Mixed)The id of the node, a DOM node or an existing Element that will be the container to render this component into. Using this config, a call to render() is not required.
" %>

<%@ attribute
	name="repeat"
	type="java.lang.Object"
	required="false"
	description="
(Boolean/Object)True to repeat fire the click event while the mouse is down. This can also be an Ext.util.ClickRepeater config object (defaults to false).
" %>

<%@ attribute
	name="scope"
	type="java.lang.Object"
	required="false"
	description="
(Object)The scope of the handler
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
	name="style"
	type="java.lang.String"
	required="false"
	description="
(String)A custom style specification to be applied to this component's Element. Should be a valid argument to Ext.Element.applyStyles.
" %>

<%@ attribute
	name="tabIndex"
	type="java.lang.Integer"
	required="false"
	description="
(Number)Set a DOM tabIndex for this button (defaults to undefined)
" %>

<%@ attribute
	name="text"
	type="java.lang.String"
	required="false"
	description="
(String)The button text
" %>

<%@ attribute
	name="toggleGroup"
	type="java.lang.String"
	required="false"
	description="
(String)The group this toggle button is a member of (only 1 per group can be pressed, only applies if enableToggle = true)
" %>

<%@ attribute
	name="tooltip"
	type="java.lang.String"
	required="false"
	description="
(String/Object)The tooltip for the button - can be a string or QuickTips config object
" %>

<%@ attribute
	name="tooltipType"
	type="java.lang.String"
	required="false"
	description="
(String)The type of tooltip to use. Either 'qtip' (default) for QuickTips or 'title' for title attribute.
" %>

<%@ attribute
	name="type"
	type="java.lang.String"
	required="false"
	description="
(String)submit, reset or button - defaults to 'button'
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
	name="onArrowclick"
	type="java.lang.Object"
	required="false"
	description="
( MenuButton this, EventObject e )Fires when this button's arrow is clicked
" %>

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
	name="onClick"
	type="java.lang.Object"
	required="false"
	description="
( Button this, EventObject e )Fires when this button is clicked
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
	name="onMenuhide"
	type="java.lang.String"
	required="false"
	description="
( Button this, Menu menu )If this button has a menu, this event fires when it is hidden
" %>

<%@ attribute
	name="onMenushow"
	type="java.lang.String"
	required="false"
	description="
( Button this, Menu menu )If this button has a menu, this event fires when it is shown
" %>

<%@ attribute
	name="onMenutriggerout"
	type="java.lang.Object"
	required="false"
	description="
( Button this, Menu menu, EventObject e )If this button has a menu, this event fires when the mouse leaves the menu triggering element
" %>

<%@ attribute
	name="onMenutriggerover"
	type="java.lang.Object"
	required="false"
	description="
( Button this, Menu menu, EventObject e )If this button has a menu, this event fires when the mouse enters the menu triggering element
" %>

<%@ attribute
	name="onMouseout"
	type="java.lang.String"
	required="false"
	description="
( Button this, Event e )Fires when the mouse exits the button
" %>

<%@ attribute
	name="onMouseover"
	type="java.lang.String"
	required="false"
	description="
( Button this, Event e )Fires when the mouse hovers over the button
" %>

<%@ attribute
	name="onRender"
	type="java.lang.String"
	required="false"
	description="
( Ext.Component this )Fires after the component is rendered.
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

<%@ attribute
	name="onToggle"
	type="java.lang.Boolean"
	required="false"
	description="
( Button this, Boolean pressed )Fires when the 'pressed' state of this button changes (only if enableToggle = true)
" %>

<%-- Events _____________________________END --%>

<%@ include file="inc/taglibs.jsp" %>
<%@ tag 
	import="org.apache.commons.beanutils.BeanUtils"
	dynamic-attributes="dynamicAttributes"
	description="A menu button that renders into a toolbar." %>
	
	<extutil:processTagAttributes 
		configVar="configMap" 
		eventsVar="eventsMap" 
		include="*" 
		tagJspContext="<%=jspContext %>"
		dynamicAttributes="${dynamicAttributes}" />	
	
	<%-- Process JSP body --%>

	<jsp:doBody/>
				
	<c:set var="item">
		    new Ext.Toolbar.Button({
		  		<c:forEach items="${configMap}" var="config">
		  			${config.key}:${config.value},
		  		</c:forEach>
		  		<c:if test="<%=BeanUtils.getProperty(this,"menu")!=null %>">
					menu:<%=BeanUtils.getProperty(this,"menu")%>,
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
		    }),
	</c:set>
	
	<extutil:setParentProperties 
		tag="<%=this%>" 
		items="${item}" />
