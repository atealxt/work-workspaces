/*
 * FCKeditor - The text editor for Internet - http://www.fckeditor.net
 * Copyright (C) 2003-2007 Frederico Caldeira Knabben
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
package net.fckeditor.handlers;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Tests for {@link ExtensionsHandler}.
 *
 * @version $Id: ExtensionsHandlerTest.java 2168 2008-07-03 21:47:29Z mosipov $
 */
public class ExtensionsHandlerTest {
	
	@Test
	public void testIsAllowed01() {
		ResourceTypeHandler type = ResourceTypeHandler.FILE;
		ExtensionsHandler.setExtensionsAllowed(type, "a");
		ExtensionsHandler.setExtensionsDenied(type, "b");
		assertTrue(ExtensionsHandler.getExtensionsAllowed(type).isEmpty());
		assertTrue(ExtensionsHandler.getExtensionsDenied(type).contains("b"));
		assertFalse(ExtensionsHandler.isAllowed(type, "b"));
		assertTrue(ExtensionsHandler.isAllowed(type, "a"));
		assertTrue(ExtensionsHandler.isAllowed(type, "c"));
	}
	
	@Test
	public void testIsAllowed02() {
		ResourceTypeHandler type = ResourceTypeHandler.FILE;
		ExtensionsHandler.setExtensionsAllowed(type, "a|b|c");
		assertTrue(ExtensionsHandler.isAllowed(type, "a"));
		assertTrue(ExtensionsHandler.isAllowed(type, "b"));
		assertTrue(ExtensionsHandler.isAllowed(type, "c"));
		assertFalse(ExtensionsHandler.isAllowed(type, "d"));
	}

}
