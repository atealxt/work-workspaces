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

<%@ attribute name="tag" required="true" type="javax.servlet.jsp.tagext.SimpleTagSupport" %>
<%@ attribute name="removeComma" required="false" type="java.lang.Boolean" %>

<%@ tag 
	import="
		org.apache.commons.beanutils.BeanUtils,
		javax.servlet.jsp.tagext.JspTag,
		java.util.HashMap,
		javax.servlet.jsp.tagext.SimpleTagSupport,
		javax.servlet.jsp.tagext.SimpleTag,
		javax.servlet.jsp.tagext.TagSupport,
		javax.servlet.jsp.tagext.TagAdapter"

	dynamic-attributes="dynamicAttributes"
	description="Simple tag collecting row cells" %>

	<%
	
		HashMap dynamicAttributes = (HashMap)jspContext.getAttribute("dynamicAttributes");	
		SimpleTagSupport targetTag = (SimpleTagSupport)this.findAncestorWithClass(tag,SimpleTag.class);
		

		for (Object key:dynamicAttributes.keySet()){
			String value = (String)dynamicAttributes.get(key);
			
			if (value!=null){
			String parentValue = (String)BeanUtils.getProperty(targetTag,key.toString());
				if (parentValue!=null) value = parentValue + value;
				
				if (removeComma!=null && removeComma){
					String lastChar = value.substring(value.length()-1,value.length());
					if (",".equals(lastChar)){
						value = value.substring(0,value.length()-1);
					}					
				}
				
				BeanUtils.setProperty(targetTag,key.toString(),value);
			}
		}
	%>