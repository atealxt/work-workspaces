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
package net.fckeditor.handlers;

import static org.junit.Assert.*;
import net.fckeditor.handlers.PropertiesLoader;
import net.fckeditor.handlers.ResourceTypeHandler;

import org.junit.Test;

/**
 * Tests for {@link ResourceTypeHandler}.
 * 
 * @version $Id: ResourceTypeHandlerTest.java 1585 2008-02-21 18:13:09Z th-schwarz $
 */
public class ResourceTypeHandlerTest {

	@Test
	public void getType01() throws Exception {
		assertNull(ResourceTypeHandler.getResourceType("xyz"));
	}

	@Test
	public void getType02() throws Exception {
		assertEquals(ResourceTypeHandler.FILE, ResourceTypeHandler.getResourceType("File"));
	}

	@Test
	public void getType03() throws Exception {
		assertEquals(ResourceTypeHandler.IMAGE, ResourceTypeHandler.getResourceType("Image"));
	}

	@Test
	public void isValid01() throws Exception {
		assertFalse(ResourceTypeHandler.isValid("1234"));
	}

	@Test
	public void isValid02() throws Exception {
		assertFalse(ResourceTypeHandler.isValid("fLash"));
	}

	@Test
	public void isValid03() throws Exception {
		assertFalse(ResourceTypeHandler.isValid("MeDiA"));
	}

	@Test
	public void getTypeDefault01() throws Exception {
		assertEquals(ResourceTypeHandler.FILE, ResourceTypeHandler
				.getDefaultResourceType("wrong-type"));
	}

	@Test
	public void getTypeDefault02() throws Exception {
		assertNotSame(ResourceTypeHandler.FLASH, ResourceTypeHandler
				.getDefaultResourceType("flAsh"));
	}

	@Test
	public void getSubDirForType01() throws Exception {
		assertEquals(PropertiesLoader.getProperty("connector.resourceType.file.path"), 
				ResourceTypeHandler.getDefaultResourceType(null).getPath());
	}

	@Test
	public void getSubDirForType02() throws Exception {
		assertEquals(PropertiesLoader.getProperty("connector.resourceType.image.path"), 
				ResourceTypeHandler.getResourceType("Image").getPath());
	}
}
