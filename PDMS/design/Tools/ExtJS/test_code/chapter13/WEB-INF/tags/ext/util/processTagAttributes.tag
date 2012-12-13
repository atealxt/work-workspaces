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

<%@ attribute name="configVar" required="true" rtexprvalue="false" type="java.lang.String" %>
<%@ attribute name="eventsVar" required="true" rtexprvalue="false" type="java.lang.String" %>
<%@ attribute name="tagJspContext" required="true" type="javax.servlet.jsp.JspContext" %>
<%@ attribute name="include" required="true" type="java.lang.String" %>
<%@ attribute name="exclude" required="false" type="java.lang.String" %>
<%@ attribute name="javaScript" required="false" type="java.lang.String" %>
<%@ attribute name="dynamicAttributes" required="true" type="java.util.HashMap" %>

<%@ variable alias="configMap" name-from-attribute="configVar" variable-class="java.util.LinkedHashMap" scope="AT_BEGIN" %>
<%@ variable alias="eventsMap" name-from-attribute="eventsVar" variable-class="java.util.LinkedHashMap" scope="AT_BEGIN" %>

<%@ include file="../inc/taglibs.jsp" %>
<%@ tag  
	import="
		javax.servlet.jsp.tagext.JspTag, 
		javax.servlet.jsp.tagext.SimpleTagSupport,
		java.util.Enumeration, 
		java.util.HashMap, 
		java.util.LinkedHashMap,
		org.apache.commons.beanutils.BeanUtils"%>

	<%
		LinkedHashMap<String,String> configMap = new LinkedHashMap<String,String>();
		LinkedHashMap<String,String> eventsMap = new LinkedHashMap<String,String>();

		String[] includes = ((String)jspContext.getAttribute("include")).split(",");
		String exclude = ((String)jspContext.getAttribute("exclude"));
		String javaScript = ((String)jspContext.getAttribute("javaScript"));
		
		
		// goes through all parent attributes
		for (Enumeration attributesEn = tagJspContext.getAttributeNamesInScope(1); attributesEn.hasMoreElements();) {
			String key = attributesEn.nextElement().toString();
			boolean isInclude = false;
			boolean isExclude = false;
			boolean isJavaScript = false;
			boolean isEvent = false;
			
			// check if include
			for (String incAttr:includes){
				if (key.indexOf(incAttr)>-1 || "*".equals(incAttr)) {
					isInclude = true;
					break;
				}
			}

			// check if exclude
			// default dynamicAttributes
			if (key.equals("dynamicAttributes") || key.equals("jspBody")){
				isExclude = true;
			// other attributes from exclude 	
			} else if (exclude !=null){
				for (String excAttr:exclude.split(",")){
					if (key.indexOf(excAttr)>-1) {
						isExclude = true;
						break;
					}
				}
			}
			
			// check if JS
			// default JS attributes
			if (key.equals("handler") 
					|| key.equals("plugins") 
					|| key.equals("stateEvents")
					|| key.equals("defaults")
					|| key.equals("bufferResize")
					|| key.equals("scope")
					|| key.equals("applyTo")
					|| key.equals("keys")
					|| key.equals("tools")
					|| key.equals("buttons")
					|| key.equals("loader")
					|| key.equals("renderer")
					|| key.equals("store")
					|| key.equals("width")
					|| key.equals("layoutConfig")){
				
				isJavaScript = true;

			// check if isJavaScript code
			} else if (javaScript !=null){
				for (String jsAttr:javaScript.split(",")){
					if (key.indexOf(jsAttr)>-1) {
						isJavaScript = true;
						break;
					}
				}
			}

			java.util.regex.Pattern pattern = java.util.regex.Pattern.compile("on\\p{Upper}");
			java.util.regex.Matcher preFinder = pattern.matcher(key);
			
			if(preFinder.find()){
				isEvent = true;
			}
			
			// adds to var map if not exlude
			if (isInclude && !isExclude && !isEvent){
				String value = tagJspContext.getAttribute(key).toString();
				String simpleClassName = tagJspContext.getAttribute(key).getClass().getSimpleName();
				
				if (key.indexOf("Str")>-1){
					key = key.replace("Str","");
				}
				
				// String attribute
				if (!"true".equals(value)&& !"false".equals(value) && !isJavaScript && !"Integer".equals(simpleClassName) && !"Float".equals(simpleClassName)){
					value = "'"+value+"'";
				}
				configMap.put(key.toString(),value);
				
			// in case of event, adds js to ext_component_events request string	
			} else if (isEvent){
				String value = tagJspContext.getAttribute(key).toString();
				String eventName = key.toLowerCase().replaceFirst("on","");
				eventsMap.put(eventName,value);			
			}
		}
		
		// dynamic attributes
		for (Object key:dynamicAttributes.keySet()){
			String value = (String)dynamicAttributes.get(key);
			
			// String attribute
			if (!"true".equals(value)&& !"false".equals(value)){
				value = "'"+value+"'";
			}
			
			configMap.put(key.toString(),value);
		}
		
		
		// declare global IDs
		if (tagJspContext.getAttribute("id")!=null){
			String comDecStr = (String)request.getAttribute("ext_component_declarations");
			String comId = (String)tagJspContext.getAttribute("id");
			comDecStr += comId+" = Ext.getCmp('"+comId+"');\n";
			request.setAttribute("ext_component_declarations",comDecStr);
		}
		
		jspContext.setAttribute("configMap",configMap);
		jspContext.setAttribute("eventsMap",eventsMap);
	%>