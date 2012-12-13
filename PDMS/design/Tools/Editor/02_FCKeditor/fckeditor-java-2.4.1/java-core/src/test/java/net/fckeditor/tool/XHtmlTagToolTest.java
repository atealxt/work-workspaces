/*
 * FCKeditor - The text editor for Internet - http://www.fckeditor.net
 * Copyright (C) 2003-2008 Frederico Caldeira Knabben
 * 
 * == BEGIN LICENSE ==
 * 
 * Licensed under the terms of any of the following licenses at your
 * choice:
 * 
 *  - GNU General Public License Version 2 or later (the "GPL")
 *    http://www.gnu.org/licenses/gpl.html
 * 
 *  - GNU Lesser General Public License Version 2.1 or later (the "LGPL")
 *    http://www.gnu.org/licenses/lgpl.html
 * 
 *  - Mozilla Public License Version 1.1 or later (the "MPL")
 *    http://www.mozilla.org/MPL/MPL-1.1.html
 * 
 * == END LICENSE ==
 */
package net.fckeditor.tool;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests for {@link XHtmlTagTool}.
 *
 * @version $Id: XHtmlTagToolTest.java 1679 2008-03-04 13:12:31Z mosipov $
 */
public class XHtmlTagToolTest {

	@Test
	public void testClosingTag01() throws Exception {
	    XHtmlTagTool tag = new XHtmlTagTool("test");
	    assertEquals("<test />", tag.toString());
    }
	
	@Test
	public void testClosingTag02() throws Exception {
	    XHtmlTagTool tag = new XHtmlTagTool("test", "");
	    assertEquals("<test />", tag.toString());
    }

	@Test
	public void testClosingTag03() throws Exception {
	    XHtmlTagTool tag = new XHtmlTagTool("test", "val");
	    assertEquals("<test>val</test>", tag.toString());
    }
	
	@Test
	public void testClosingTag04() throws Exception {
	    XHtmlTagTool tag = new XHtmlTagTool("test", XHtmlTagTool.SPACE);
	    assertEquals("<test> </test>", tag.toString());
    }
	
	@Test
	public void deepEquals01() throws Exception {
		XHtmlTagTool expected = new XHtmlTagTool("tag", "some text");
		XHtmlTagTool actual = new XHtmlTagTool("tag", "some text");
		assertEquals(expected, actual);
	}
	
	@Test
	public void deepEquals02() throws Exception {
		XHtmlTagTool unexpected = new XHtmlTagTool("tag", "");
		XHtmlTagTool actual = new XHtmlTagTool("tag", XHtmlTagTool.SPACE);
		assertNotSame(unexpected, actual);
	}
	
	@Test
	public void deepEquals03() throws Exception {
		XHtmlTagTool expected = new XHtmlTagTool("tag", "some text");
		expected.addAttribute("id", "some_tag_id");
		expected.addAttribute("class", "grayShadow");
		expected.addAttribute("style", "color: red");
		
		XHtmlTagTool actual = new XHtmlTagTool("tag", "some text");
		
		actual.addAttribute("style", "color: red");
		actual.addAttribute("id", "some_tag_id");
		actual.addAttribute("class", "grayShadow");
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void deepEquals04() throws Exception {
		XHtmlTagTool unexpected = new XHtmlTagTool("tag");
		unexpected.addAttribute("id", "some_tag_id");
		unexpected.addAttribute("class", "grayShadow");
		unexpected.addAttribute("style", "color: red");
		
		XHtmlTagTool actual = new XHtmlTagTool("tag");
		
		actual.addAttribute("id", "some_tag_id");
		actual.addAttribute("class", "grayShadow");
		actual.addAttribute("style", "color: blue");
		
		assertNotSame(unexpected, actual);
	}
}
