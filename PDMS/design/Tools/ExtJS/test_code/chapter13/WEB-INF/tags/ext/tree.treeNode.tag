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
	name="allowChildren"
	type="java.lang.Boolean"
	required="false"
	description="
(Boolean)False to not allow this node to have child nodes (defaults to true)
" %>

<%@ attribute
	name="allowDrag"
	type="java.lang.Boolean"
	required="false"
	description="
(Boolean)False to make this node undraggable if draggable = true (defaults to true)
" %>

<%@ attribute
	name="allowDrop"
	type="java.lang.Boolean"
	required="false"
	description="
(Boolean)False if this node cannot have child nodes dropped on it (defaults to true)
" %>

<%@ attribute
	name="checked"
	type="java.lang.Boolean"
	required="false"
	description="
(Boolean)True to render a checked checkbox for this node, false to render an unchecked checkbox (defaults to undefined with no checkbox rendered)
" %>

<%@ attribute
	name="cls"
	type="java.lang.String"
	required="false"
	description="
(String)A css class to be added to the node
" %>

<%@ attribute
	name="disabled"
	type="java.lang.Boolean"
	required="false"
	description="
(Boolean)true to start the node disabled
" %>

<%@ attribute
	name="draggable"
	type="java.lang.Boolean"
	required="false"
	description="
(Boolean)True to make this node draggable (defaults to false)
" %>

<%@ attribute
	name="expandable"
	type="java.lang.Boolean"
	required="false"
	description="
(Boolean)If set to true, the node will always show a plus/minus icon, even when empty
" %>

<%@ attribute
	name="expanded"
	type="java.lang.Boolean"
	required="false"
	description="
(Boolean)true to start the node expanded
" %>

<%@ attribute
	name="href"
	type="java.lang.String"
	required="false"
	description="
(String)URL of the link used for the node (defaults to #)
" %>

<%@ attribute
	name="hrefTarget"
	type="java.lang.String"
	required="false"
	description="
(String)target frame for the link
" %>

<%@ attribute
	name="icon"
	type="java.lang.String"
	required="false"
	description="
(String)The path to an icon for the node. The preferred way to do this is to use the cls or iconCls attributes and add the icon via a CSS background image.
" %>

<%@ attribute
	name="iconCls"
	type="java.lang.String"
	required="false"
	description="
(String)A css class to be added to the nodes icon element for applying css background images
" %>

<%@ attribute
	name="id"
	type="java.lang.String"
	required="false"
	description="
(String)The id for this node. If one is not specified, one is generated.
" %>

<%@ attribute
	name="isTarget"
	type="java.lang.Boolean"
	required="false"
	description="
(Boolean)False to not allow this node to act as a drop target (defaults to true)
" %>

<%@ attribute
	name="leaf"
	type="java.lang.Boolean"
	required="false"
	description="
(Boolean)true if this node is a leaf and does not have children
" %>

<%@ attribute
	name="listeners"
	type="java.lang.Object"
	required="false"
	description="
(Object)A config object containing one or more event handlers to be added to this object during initialization. This should be a valid listeners config object as specified in the addListener example for attaching multiple handlers at once.
" %>

<%@ attribute
	name="qtip"
	type="java.lang.String"
	required="false"
	description="
(String)An Ext QuickTip for the node
" %>

<%@ attribute
	name="qtipCfg"
	type="java.lang.String"
	required="false"
	description="
(String)An Ext QuickTip config for the node (used instead of qtip)
" %>

<%@ attribute
	name="singleClickExpand"
	type="java.lang.Boolean"
	required="false"
	description="
(Boolean)True for single click expand on this node
" %>

<%@ attribute
	name="text"
	type="java.lang.String"
	required="false"
	description="
(String)The text for this node
" %>

<%@ attribute
	name="uiProvider"
	type="java.lang.String"
	required="false"
	description="
(Function)A UI class to use for this node (defaults to Ext.tree.TreeNodeUI)
" %>

<%-- Config params _____________________________END --%>

<%-- Events _____________________________START --%>

<%@ attribute
	name="onAppend"
	type="java.lang.Integer"
	required="false"
	description="
( Tree tree, Node this, Node node, Number index )Fires when a new child node is appended
" %>

<%@ attribute
	name="onBeforeappend"
	type="java.lang.String"
	required="false"
	description="
( Tree tree, Node this, Node node )Fires before a new child is appended, return false to cancel the append.
" %>

<%@ attribute
	name="onBeforechildrenrendered"
	type="java.lang.String"
	required="false"
	description="
( Node this )Fires right before the child nodes for this node are rendered
" %>

<%@ attribute
	name="onBeforeclick"
	type="java.lang.Object"
	required="false"
	description="
( Node this, Ext.EventObject e )Fires before click processing. Return false to cancel the default action.
" %>

<%@ attribute
	name="onBeforecollapse"
	type="java.lang.Boolean"
	required="false"
	description="
( Node this, Boolean deep, Boolean anim )Fires before this node is collapsed, return false to cancel.
" %>

<%@ attribute
	name="onBeforeexpand"
	type="java.lang.Boolean"
	required="false"
	description="
( Node this, Boolean deep, Boolean anim )Fires before this node is expanded, return false to cancel.
" %>

<%@ attribute
	name="onBeforeinsert"
	type="java.lang.String"
	required="false"
	description="
( Tree tree, Node this, Node node, Node refNode )Fires before a new child is inserted, return false to cancel the insert.
" %>

<%@ attribute
	name="onBeforemove"
	type="java.lang.Integer"
	required="false"
	description="
( Tree tree, Node this, Node oldParent, Node newParent, Number index )Fires before this node is moved to a new location in the tree. Return false to cancel the move.
" %>

<%@ attribute
	name="onBeforeremove"
	type="java.lang.String"
	required="false"
	description="
( Tree tree, Node this, Node node )Fires before a child is removed, return false to cancel the remove.
" %>

<%@ attribute
	name="onCheckchange"
	type="java.lang.Boolean"
	required="false"
	description="
( Node this, Boolean checked )Fires when a node with a checkbox's checked property changes
" %>

<%@ attribute
	name="onClick"
	type="java.lang.Object"
	required="false"
	description="
( Node this, Ext.EventObject e )Fires when this node is clicked
" %>

<%@ attribute
	name="onCollapse"
	type="java.lang.String"
	required="false"
	description="
( Node this )Fires when this node is collapsed
" %>

<%@ attribute
	name="onContextmenu"
	type="java.lang.Object"
	required="false"
	description="
( Node this, Ext.EventObject e )Fires when this node is right clicked
" %>

<%@ attribute
	name="onDblclick"
	type="java.lang.Object"
	required="false"
	description="
( Node this, Ext.EventObject e )Fires when this node is double clicked
" %>

<%@ attribute
	name="onDisabledchange"
	type="java.lang.Boolean"
	required="false"
	description="
( Node this, Boolean disabled )Fires when the disabled status of this node changes
" %>

<%@ attribute
	name="onExpand"
	type="java.lang.String"
	required="false"
	description="
( Node this )Fires when this node is expanded
" %>

<%@ attribute
	name="onInsert"
	type="java.lang.String"
	required="false"
	description="
( Tree tree, Node this, Node node, Node refNode )Fires when a new child node is inserted.
" %>

<%@ attribute
	name="onMove"
	type="java.lang.Integer"
	required="false"
	description="
( Tree tree, Node this, Node oldParent, Node newParent, Number index )Fires when this node is moved to a new location in the tree
" %>

<%@ attribute
	name="onRemove"
	type="java.lang.String"
	required="false"
	description="
( Tree tree, Node this, Node node )Fires when a child node is removed
" %>

<%@ attribute
	name="onTextchange"
	type="java.lang.String"
	required="false"
	description="
( Node this, String text, String oldText )Fires when the text for this node is changed
" %>

<%-- Events _____________________________END --%>

<%-- Added attribs _____________________________START --%>

 <%@ attribute
	name="items"
	type="java.lang.String"
	required="false"
	description="Items container (automatically generated by tag) "
 %>
<%-- Added attribs _____________________________END --%>


<%@ include file="inc/taglibs.jsp" %>
<%@ tag 
	import="org.apache.commons.beanutils.BeanUtils"
	dynamic-attributes="dynamicAttributes"
	description="" %>
	
	<extutil:processTagAttributes 
		configVar="configMap" 
		eventsVar="eventsMap" 
		include="*" 
		tagJspContext="<%=jspContext %>"
		dynamicAttributes="${dynamicAttributes}" />	
	
	<%-- Process JSP body --%>
	
	<jsp:doBody/>
		
	<c:set var="item">
		    {
		  		<c:forEach items="${configMap}" var="config">
		  			${config.key}:${config.value},
		  		</c:forEach>
		  		<c:if test="<%=BeanUtils.getProperty(this,"items")!=null %>">
					children:[<% 
							String items = BeanUtils.getProperty(this,"items");
							jspContext.getOut().write(items.substring(0,items.length()-1));
						%>],
		  		</c:if>
		  		<c:if test="<%=BeanUtils.getProperty(this,"items")==null %>">
					leaf:true,
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
		    },
	</c:set>
	
	<extutil:setParentProperties tag="<%=this%>" items="${item}" />
		