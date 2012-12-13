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
	name="allQuery"
	type="java.lang.String"
	required="false"
	description="
(String)The text query to send to the server to return all records for the list with no filtering (defaults to '')
" %>

<%@ attribute
	name="allowBlank"
	type="java.lang.Boolean"
	required="false"
	description="
(Boolean)False to validate that the value length > 0 (defaults to true)
" %>

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
	name="autoCreate"
	type="java.lang.Object"
	required="false"
	description="
(Boolean/Object)A DomHelper element spec, or true for a default element spec (defaults to: {tag: 'input', type: 'text', size: '24', autocomplete: 'off'})
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
	name="blankText"
	type="java.lang.String"
	required="false"
	description="
(String)Error text to display if the allow blank validation fails (defaults to 'This field is required')
" %>

<%@ attribute
	name="clearCls"
	type="java.lang.String"
	required="false"
	description="
(String)The CSS class used to provide field clearing (defaults to 'x-form-clear-left')
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
	name="disableKeyFilter"
	type="java.lang.Boolean"
	required="false"
	description="
(Boolean)True to disable input keystroke filtering (defaults to false)
" %>

<%@ attribute
	name="disabled"
	type="java.lang.Boolean"
	required="false"
	description="
(Boolean)True to disable the field (defaults to false).
" %>

<%@ attribute
	name="disabledClass"
	type="java.lang.String"
	required="false"
	description="
(String)CSS class added to the component when it is disabled (defaults to 'x-item-disabled').
" %>

<%@ attribute
	name="displayField"
	type="java.lang.String"
	required="false"
	description="
(String)The underlying data field name to bind to this ComboBox (defaults to undefined if mode = 'remote' or 'text' if transforming a select)
" %>

<%@ attribute
	name="editable"
	type="java.lang.Boolean"
	required="false"
	description="
(Boolean)False to prevent the user from typing text directly into the field, just like a traditional select (defaults to true)
" %>

<%@ attribute
	name="emptyClass"
	type="java.lang.String"
	required="false"
	description="
(String)The CSS class to apply to an empty field to style the emptyText (defaults to 'x-form-empty-field'). This class is automatically added and removed as needed depending on the current field value.
" %>

<%@ attribute
	name="emptyText"
	type="java.lang.String"
	required="false"
	description="
(String)The default text to display in an empty field (defaults to null).
" %>

<%@ attribute
	name="fieldClass"
	type="java.lang.String"
	required="false"
	description="
(String)The default CSS class for the field (defaults to 'x-form-field')
" %>

<%@ attribute
	name="fieldLabel"
	type="java.lang.String"
	required="false"
	description="
(String)The label text to display next to this field (defaults to '')
" %>

<%@ attribute
	name="focusClass"
	type="java.lang.String"
	required="false"
	description="
(String)The CSS class to use when the field receives focus (defaults to 'x-form-focus')
" %>

<%@ attribute
	name="forceSelection"
	type="java.lang.Boolean"
	required="false"
	description="
(Boolean)True to restrict the selected value to one of the values in the list, false to allow the user to set arbitrary text into the field (defaults to false)
" %>

<%@ attribute
	name="handleHeight"
	type="java.lang.Integer"
	required="false"
	description="
(Number)The height in pixels of the dropdown list resize handle if resizable = true (defaults to 8)
" %>

<%@ attribute
	name="height"
	type="java.lang.Integer"
	required="false"
	description="
(Number)The height of this component in pixels (defaults to auto).
" %>

<%@ attribute
	name="hiddenName"
	type="java.lang.String"
	required="false"
	description="
(String)If specified, a hidden form field with this name is dynamically generated to store the field's data value (defaults to the underlying DOM element's name). Required for the combo's value to automatically post during a form submission.
" %>

<%@ attribute
	name="hideLabel"
	type="java.lang.Boolean"
	required="false"
	description="
(Boolean)True to completely hide the label element (defaults to false)
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
	name="hideTrigger"
	type="java.lang.Boolean"
	required="false"
	description="
(Boolean)True to hide the trigger element and display only the base text field (defaults to false)
" %>

<%@ attribute
	name="id"
	type="java.lang.String"
	required="false"
	description="
(String)The unique id of this component (defaults to an auto-assigned id).
" %>

<%@ attribute
	name="inputType"
	type="java.lang.String"
	required="false"
	description="
(String)The type attribute for input fields -- e.g. radio, text, password (defaults to 'text').
" %>

<%@ attribute
	name="invalidClass"
	type="java.lang.String"
	required="false"
	description="
(String)The CSS class to use when marking a field invalid (defaults to 'x-form-invalid')
" %>

<%@ attribute
	name="invalidText"
	type="java.lang.String"
	required="false"
	description="
(String)The error text to use when marking a field invalid and no message is provided (defaults to 'The value in this field is invalid')
" %>

<%@ attribute
	name="itemCls"
	type="java.lang.String"
	required="false"
	description="
(String)An additional CSS class to apply to this field (defaults to the container's itemCls value if set, or '')
" %>

<%@ attribute
	name="labelSeparator"
	type="java.lang.String"
	required="false"
	description="
(String)The standard separator to display after the text of each form label (defaults to the value of Ext.layout.FormLayout.labelSeparator, which is a colon ':' by default). To display no separator for this field's label specify empty string ''.
" %>

<%@ attribute
	name="labelStyle"
	type="java.lang.String"
	required="false"
	description="
(String)A CSS style specification to apply directly to this field's label (defaults to the container's labelStyle value if set, or ''). For example, labelStyle: 'font-weight:bold;'.
" %>

<%@ attribute
	name="lazyInit"
	type="java.lang.Boolean"
	required="false"
	description="
(Boolean)True to not initialize the list for this combo until the field is focused. (defaults to true)
" %>

<%@ attribute
	name="lazyRender"
	type="java.lang.Boolean"
	required="false"
	description="
(Boolean)True to prevent the ComboBox from rendering until requested (should always be used when rendering into an Ext.Editor, defaults to false)
" %>

<%@ attribute
	name="listAlign"
	type="java.lang.String"
	required="false"
	description="
(String)A valid anchor position value. See Ext.Element.alignTo for details on supported anchor positions (defaults to 'tl-bl')
" %>

<%@ attribute
	name="listClass"
	type="java.lang.String"
	required="false"
	description="
(String)CSS class to apply to the dropdown list element (defaults to '')
" %>

<%@ attribute
	name="listWidth"
	type="java.lang.Integer"
	required="false"
	description="
(Number)The width in pixels of the dropdown list (defaults to the width of the ComboBox field)
" %>

<%@ attribute
	name="listeners"
	type="java.lang.Object"
	required="false"
	description="
(Object)A config object containing one or more event handlers to be added to this object during initialization. This should be a valid listeners config object as specified in the addListener example for attaching multiple handlers at once.
" %>

<%@ attribute
	name="loadingText"
	type="java.lang.String"
	required="false"
	description="
(String)The text to display in the dropdown list while data is loading. Only applies when mode = 'remote' (defaults to 'Loading...')
" %>

<%@ attribute
	name="maskRe"
	type="java.lang.String"
	required="false"
	description="
(RegExp)An input mask regular expression that will be used to filter keystrokes that don't match (defaults to null)
" %>

<%@ attribute
	name="maxHeight"
	type="java.lang.Integer"
	required="false"
	description="
(Number)The maximum height in pixels of the dropdown list before scrollbars are shown (defaults to 300)
" %>

<%@ attribute
	name="maxLength"
	type="java.lang.Integer"
	required="false"
	description="
(Number)Maximum input field length allowed (defaults to Number.MAX_VALUE)
" %>

<%@ attribute
	name="maxLengthText"
	type="java.lang.String"
	required="false"
	description="
(String)Error text to display if the maximum length validation fails (defaults to 'The maximum length for this field is {maxLength}')
" %>

<%@ attribute
	name="minChars"
	type="java.lang.Integer"
	required="false"
	description="
(Number)The minimum number of characters the user must type before autocomplete and typeahead activate (defaults to 4 if remote or 0 if local, does not apply if editable = false)
" %>

<%@ attribute
	name="minLength"
	type="java.lang.Integer"
	required="false"
	description="
(Number)Minimum input field length required (defaults to 0)
" %>

<%@ attribute
	name="minLengthText"
	type="java.lang.String"
	required="false"
	description="
(String)Error text to display if the minimum length validation fails (defaults to 'The minimum length for this field is {minLength}')
" %>

<%@ attribute
	name="minListWidth"
	type="java.lang.Integer"
	required="false"
	description="
(Number)The minimum width of the dropdown list in pixels (defaults to 70, will be ignored if listWidth has a higher value)
" %>

<%@ attribute
	name="mode"
	type="java.lang.String"
	required="false"
	description="
(String)Set to 'local' if the ComboBox loads local data (defaults to 'remote' which loads from the server)
" %>

<%@ attribute
	name="msgFx"
	type="java.lang.String"
	required="false"
	description="
(String)Experimental The effect used when displaying a validation message under the field (defaults to 'normal').
" %>

<%@ attribute
	name="msgTarget"
	type="java.lang.String"
	required="false"
	description="
(String)The location where error text should display. Should be one of the following values (defaults to 'qtip'): 

Value Description----------- ----------------------------------------------------------------------qtipDisplay a quick tip when the user hovers over the fieldtitle Display a default browser title attribute popupunder Add a block div beneath the field containing the error textsideAdd an error icon to the right of the field with a popup on hover[element id]Add the error text directly to the innerHTML of the specified element


" %>

<%@ attribute
	name="name"
	type="java.lang.String"
	required="false"
	description="
(String)The field's HTML name attribute.
" %>

<%@ attribute
	name="pageSize"
	type="java.lang.Integer"
	required="false"
	description="
(Number)If greater than 0, a paging toolbar is displayed in the footer of the dropdown list and the filter queries will execute with page start and limit parameters. Only applies when mode = 'remote' (defaults to 0)
" %>

<%@ attribute
	name="plugins"
	type="java.lang.Object"
	required="false"
	description="
(Object/Array)An object or array of objects that will provide custom functionality for this component. The only requirement for a valid plugin is that it contain an init method that accepts a reference of type Ext.Component. When a component is created, if any plugins are available, the component will call the init method on each plugin, passing a reference to itself. Each plugin can then call methods or respond to events on the component as needed to provide its functionality.
" %>

<%@ attribute
	name="queryDelay"
	type="java.lang.Integer"
	required="false"
	description="
(Number)The length of time in milliseconds to delay between the start of typing and sending the query to filter the dropdown list (defaults to 500 if mode = 'remote' or 10 if mode = 'local')
" %>

<%@ attribute
	name="queryParam"
	type="java.lang.String"
	required="false"
	description="
(String)Name of the query as it will be passed on the querystring (defaults to 'query')
" %>

<%@ attribute
	name="readOnly"
	type="java.lang.Boolean"
	required="false"
	description="
(Boolean)True to mark the field as readOnly in HTML (defaults to false) -- Note: this only sets the element's readOnly DOM attribute.
" %>

<%@ attribute
	name="regex"
	type="java.lang.String"
	required="false"
	description="
(RegExp)A JavaScript RegExp object to be tested against the field value during validation (defaults to null). If available, this regex will be evaluated only after the basic validators all return true, and will be passed the current field value. If the test fails, the field will be marked invalid using regexText.
" %>

<%@ attribute
	name="regexText"
	type="java.lang.String"
	required="false"
	description="
(String)The error text to display if regex is used and the test fails during validation (defaults to '')
" %>

<%@ attribute
	name="renderTo"
	type="java.lang.String"
	required="false"
	description="
(Mixed)The id of the node, a DOM node or an existing Element that will be the container to render this component into. Using this config, a call to render() is not required.
" %>

<%@ attribute
	name="resizable"
	type="java.lang.Boolean"
	required="false"
	description="
(Boolean)True to add a resize handle to the bottom of the dropdown list (defaults to false)
" %>

<%@ attribute
	name="selectOnFocus"
	type="java.lang.Boolean"
	required="false"
	description="
(Boolean)True to select any existing text in the field immediately on focus. Only applies when editable = true (defaults to false)
" %>

<%@ attribute
	name="selectedClass"
	type="java.lang.String"
	required="false"
	description="
(String)CSS class to apply to the selected item in the dropdown list (defaults to 'x-combo-selected')
" %>

<%@ attribute
	name="shadow"
	type="java.lang.String"
	required="false"
	description="
(Boolean/String)True or 'sides' for the default effect, 'frame' for 4-way shadow, and 'drop' for bottom-right
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
(Ext.data.Store)The data store to which this combo is bound (defaults to undefined)
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
(Number)The tabIndex for this field. Note this only applies to fields that are rendered, not those which are built via applyTo (defaults to undefined).
" %>

<%@ attribute
	name="title"
	type="java.lang.String"
	required="false"
	description="
(String)If supplied, a header element is created containing this text and added into the top of the dropdown list (defaults to undefined, with no header element)
" %>

<%@ attribute
	name="tpl"
	type="java.lang.String"
	required="false"
	description="
(String/Ext.XTemplate)The template string, or Ext.XTemplate instance to use to display each item in the dropdown list. Use this to create custom UI layouts for items in the list. 

If you wish to preserve the default visual look of list items, add the CSS class name

x-combo-list-item

 to the template's container element. 

The template must contain one or more substitution parameters using field names from the Combo's Store. An example of a custom template would be adding an

ext:qtip

 attribute which might display other fields from the Store. 

The dropdown list is displayed in a DataView. See Ext.DataView for details.
" %>

<%@ attribute
	name="transform"
	type="java.lang.String"
	required="false"
	description="
(Mixed)The id, DOM node or element of an existing select to convert to a ComboBox
" %>

<%@ attribute
	name="triggerAction"
	type="java.lang.String"
	required="false"
	description="
(String)The action to execute when the trigger field is activated. Use 'all' to run the query specified by the allQuery config option (defaults to 'query')
" %>

<%@ attribute
	name="triggerClass"
	type="java.lang.String"
	required="false"
	description="
(String)An additional CSS class used to style the trigger button. The trigger will always get the class 'x-form-trigger' and triggerClass will be appended if specified (defaults to 'x-form-arrow-trigger' which displays a downward arrow icon).
" %>

<%@ attribute
	name="typeAhead"
	type="java.lang.Boolean"
	required="false"
	description="
(Boolean)True to populate and autoselect the remainder of the text being typed after a configurable delay (typeAheadDelay) if it matches a known value (defaults to false)
" %>

<%@ attribute
	name="typeAheadDelay"
	type="java.lang.Integer"
	required="false"
	description="
(Number)The length of time in milliseconds to wait until the typeahead text is displayed if typeAhead = true (defaults to 250)
" %>

<%@ attribute
	name="validateOnBlur"
	type="java.lang.Boolean"
	required="false"
	description="
(Boolean)Whether the field should validate when it loses focus (defaults to true).
" %>

<%@ attribute
	name="validationDelay"
	type="java.lang.Integer"
	required="false"
	description="
(Number)The length of time in milliseconds after user input begins until validation is initiated (defaults to 250)
" %>

<%@ attribute
	name="validationEvent"
	type="java.lang.String"
	required="false"
	description="
(String/Boolean)The event that should initiate field validation. Set to false to disable automatic validation (defaults to 'keyup').
" %>

<%@ attribute
	name="validator"
	type="java.lang.String"
	required="false"
	description="
(Function)A custom validation function to be called during field validation (defaults to null). If available, this function will be called only after the basic validators all return true, and will be passed the current field value and expected to return boolean true if the value is valid or a string error message if invalid.
" %>

<%@ attribute
	name="value"
	type="java.lang.String"
	required="false"
	description="
(Mixed)A value to initialize this field with.
" %>

<%@ attribute
	name="valueField"
	type="java.lang.String"
	required="false"
	description="
(String)The underlying data value name to bind to this ComboBox (defaults to undefined if mode = 'remote' or 'value' if transforming a select) Note: use of a valueField requires the user to make a selection in order for a value to be mapped.
" %>

<%@ attribute
	name="valueNotFoundText"
	type="java.lang.String"
	required="false"
	description="
(String)When using a name/value combo, if the value passed to setValue is not found in the store, valueNotFoundText will be displayed as the field text if defined (defaults to undefined)
" %>

<%@ attribute
	name="vtype"
	type="java.lang.String"
	required="false"
	description="
(String)A validation type name as defined in Ext.form.VTypes (defaults to null)
" %>

<%@ attribute
	name="vtypeText"
	type="java.lang.String"
	required="false"
	description="
(String)A custom error message to display in place of the default message provided for the vtype currently set for this field (defaults to ''). Only applies if vtype is set, else ignored.
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
	name="onAutosize"
	type="java.lang.Integer"
	required="false"
	description="
( Ext.form.Field this, Number width )Fires when the autosize function is triggered.The field may or may not have actually changed sizeaccording to the default logic, but this event provides a hook for the developer to apply additionallogic at runtime to resize the field if needed.
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
	name="onBeforequery"
	type="java.lang.Object"
	required="false"
	description="
( Object queryEvent )Fires before all queries are processed. Return false to cancel the query or set the queryEvent'scancel property to true.
" %>

<%@ attribute
	name="onBeforerender"
	type="java.lang.String"
	required="false"
	description="
( Ext.Component this )Fires before the component is rendered. Return false to stop the render.
" %>

<%@ attribute
	name="onBeforeselect"
	type="java.lang.Integer"
	required="false"
	description="
( Ext.form.ComboBox combo, Ext.data.Record record, Number index )Fires before a list item is selected. Return false to cancel the selection.
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
	name="onBlur"
	type="java.lang.String"
	required="false"
	description="
( Ext.form.Field this )Fires when this field loses input focus.
" %>

<%@ attribute
	name="onChange"
	type="java.lang.String"
	required="false"
	description="
( Ext.form.Field this, Mixed newValue, Mixed oldValue )Fires just before the field blurs if the field value has changed.
" %>

<%@ attribute
	name="onCollapse"
	type="java.lang.String"
	required="false"
	description="
( Ext.form.ComboBox combo )Fires when the dropdown list is collapsed
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
	name="onExpand"
	type="java.lang.String"
	required="false"
	description="
( Ext.form.ComboBox combo )Fires when the dropdown list is expanded
" %>

<%@ attribute
	name="onFocus"
	type="java.lang.String"
	required="false"
	description="
( Ext.form.Field this )Fires when this field receives input focus.
" %>

<%@ attribute
	name="onHide"
	type="java.lang.String"
	required="false"
	description="
( Ext.Component this )Fires after the component is hidden.
" %>

<%@ attribute
	name="onInvalid"
	type="java.lang.String"
	required="false"
	description="
( Ext.form.Field this, String msg )Fires after the field has been marked as invalid.
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
	name="onSelect"
	type="java.lang.Integer"
	required="false"
	description="
( Ext.form.ComboBox combo, Ext.data.Record record, Number index )Fires when a list item is selected
" %>

<%@ attribute
	name="onShow"
	type="java.lang.String"
	required="false"
	description="
( Ext.Component this )Fires after the component is shown.
" %>

<%@ attribute
	name="onSpecialkey"
	type="java.lang.Object"
	required="false"
	description="
( Ext.form.Field this, Ext.EventObject e )Fires when any key related to navigation (arrows, tab, enter, esc, etc.) is pressed.You can checkExt.EventObject.getKey to determine which key was pressed.
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
	name="onValid"
	type="java.lang.String"
	required="false"
	description="
( Ext.form.Field this )Fires after the field has been validated with no errors.
" %>

<%-- Events _____________________________END --%>


<%-- Added params _____________________________START --%>

<%@ attribute
	name="items"
	type="java.lang.String"
	required="false"
	description="(String) Local attribute used for handling items from formOption tags"
 %>
<%-- Added params _____________________________END --%>


<%@ include file="inc/taglibs.jsp" %>
<%@ tag 
	import="org.apache.commons.beanutils.BeanUtils"
	dynamic-attributes="dynamicAttributes"
	description="A combobox control with support for autocomplete, remote-loading, paging and many other features." %>
	
	<jsp:doBody />
	
	<extutil:processTagAttributes 
		configVar="configMap" 
		eventsVar="eventsMap" 
		include="*" 
		exclude="items"
		tagJspContext="<%=jspContext %>"
		dynamicAttributes="${dynamicAttributes}" />	
	
	<%-- Process JSP body --%>
	<c:set var="item">
		    new Ext.form.ComboBox({
		  		<c:forEach items="${configMap}" var="config">
		  			${config.key}:${config.value},
		  		</c:forEach>
		  		<c:if test="<%= BeanUtils.getProperty(this,"items")!=null %>">
			  		mode:'local',
			  		displayField:'text',
			  		valueField:'value',
				    triggerAction: 'all',
			  		store:new Ext.data.SimpleStore({
						fields: ['value', 'text'],
						data: [<%= BeanUtils.getProperty(this,"items").substring(0,items.length()-1) %>]
			  		}),
			  	</c:if>
			  	<c:if test="<%= BeanUtils.getProperty(this,"store")!=null %>">
			  		store: <%= BeanUtils.getProperty(this,"store") %>,
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