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
	name="allowOtherMenus"
	type="java.lang.Boolean"
	required="false"
	description="
(Boolean)True to allow multiple menus to be displayed at the same time (defaults to false)
" %>

<%@ attribute
	name="defaultAlign"
	type="java.lang.String"
	required="false"
	description="
(String)The default {@link Ext.Element#alignTo) anchor position value for this menu relative to its element of origin (defaults to 'tl-bl?')
" %>

<%@ attribute
	name="defaults"
	type="java.lang.Object"
	required="false"
	description="
(Object)A config object that will be applied to all items added to this container either via the items config or via the add method. The defaults config can contain any number of name/value property pairs to be added to each item, and should be valid for the types of items being added to the menu.
" %>

<%@ attribute
	name="items"
	type="java.lang.String"
	required="false"
	description="
(Mixed)An array of items to be added to this menu. See add for a list of valid item types.
" %>

<%@ attribute
	name="listeners"
	type="java.lang.Object"
	required="false"
	description="
(Object)A config object containing one or more event handlers to be added to this object during initialization. This should be a valid listeners config object as specified in the addListener example for attaching multiple handlers at once.
" %>

<%@ attribute
	name="minWidth"
	type="java.lang.Integer"
	required="false"
	description="
(Number)The minimum width of the menu in pixels (defaults to 120)
" %>

<%@ attribute
	name="shadow"
	type="java.lang.String"
	required="false"
	description="
(Boolean/String)True or 'sides' for the default effect, 'frame' for 4-way shadow, and 'drop' for bottom-right shadow (defaults to 'sides')
" %>

<%@ attribute
	name="subMenuAlign"
	type="java.lang.String"
	required="false"
	description="
(String)The Ext.Element.alignTo anchor position value to use for submenus of this menu (defaults to 'tl-tr?')
" %>

<%-- Config params _____________________________END --%>

<%-- Events _____________________________START --%>

<%@ attribute
	name="onBeforehide"
	type="java.lang.String"
	required="false"
	description="
( Ext.menu.Menu this )Fires before this menu is hidden
" %>

<%@ attribute
	name="onBeforeshow"
	type="java.lang.String"
	required="false"
	description="
( Ext.menu.Menu this )Fires before this menu is displayed
" %>

<%@ attribute
	name="onClick"
	type="java.lang.Object"
	required="false"
	description="
( Ext.menu.Menu this, Ext.menu.Item menuItem, Ext.EventObject e )Fires when this menu is clicked (or when the enter key is pressed while it is active)
" %>

<%@ attribute
	name="onHide"
	type="java.lang.String"
	required="false"
	description="
( Ext.menu.Menu this )Fires after this menu is hidden
" %>

<%@ attribute
	name="onItemclick"
	type="java.lang.Object"
	required="false"
	description="
( Ext.menu.BaseItem baseItem, Ext.EventObject e )Fires when a menu item contained in this menu is clicked
" %>

<%@ attribute
	name="onMouseout"
	type="java.lang.Object"
	required="false"
	description="
( Ext.menu.Menu this, Ext.EventObject e, Ext.menu.Item menuItem )Fires when the mouse exits this menu
" %>

<%@ attribute
	name="onMouseover"
	type="java.lang.Object"
	required="false"
	description="
( Ext.menu.Menu this, Ext.EventObject e, Ext.menu.Item menuItem )Fires when the mouse is hovering over this menu
" %>

<%@ attribute
	name="onSelect"
	type="java.lang.String"
	required="false"
	description="
( DatePicker picker, Date date )
" %>

<%@ attribute
	name="onShow"
	type="java.lang.String"
	required="false"
	description="
( Ext.menu.Menu this )Fires after this menu is displayed
" %>

<%-- Events _____________________________END --%>

<%@ include file="inc/taglibs.jsp" %>
<%@ tag 
	import="org.apache.commons.beanutils.BeanUtils"
	dynamic-attributes="dynamicAttributes"
	description="A menu containing a Ext.menu.DateItem component (which provides a date picker)." %>
	
	<extutil:processTagAttributes 
		configVar="configMap" 
		eventsVar="eventsMap" 
		include="*" 
		tagJspContext="<%=jspContext %>"
		dynamicAttributes="${dynamicAttributes}" />	
	
	<%-- Process JSP body --%>
		
	<jsp:doBody/>
		
	<c:set var="menu">
		    new Ext.menu.DateMenu({
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
		    })
	</c:set>
	
	<extutil:setParentProperties 
		tag="<%=this%>" 
		menu="${menu}" />