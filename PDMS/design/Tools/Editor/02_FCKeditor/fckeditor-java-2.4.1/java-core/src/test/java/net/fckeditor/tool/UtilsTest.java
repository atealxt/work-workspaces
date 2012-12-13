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
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

/**
 * Tests for {@link Utils}.
 * 
 * @version $Id: UtilsTest.java 2538 2008-10-10 18:27:19Z mosipov $
 */
public class UtilsTest {

	@Test
	public void getSet01() {
		Set<String> set = new HashSet<String>();
		set.add("a");
		set.add("ab");
		set.add("c");

		Set<String> newSet = Utils.getSet("a|Ab|c", "|");
		for (String string : newSet) {
			assertTrue(set.contains(string));
		}
	}

	@Test
	public void getSet02() {
		Set<String> set = new HashSet<String>();
		set.add("png");
		set.add("jpg");
		set.add("gif");

		Set<String> newSet = Utils.getSet("png|jpg|gif");
		for (String string : newSet) {
			assertTrue(set.contains(string));
		}
	}

	@Test
	public void getSet03() {
		Set<String> set = Utils.getSet(null);
		assertTrue(set != null);
		assertTrue(set.isEmpty());
	}

	@Test
	public void getSet04() {
		Set<String> set = Utils.getSet("");
		assertTrue(set != null);
		assertTrue(set.isEmpty());
	}

	@Test
	public void isBlank01() {
		assertTrue(Utils.isBlank(null));
	}

	@Test
	public void isBlank02() {
		assertTrue(Utils.isBlank(""));
	}

	@Test
	public void isBlank03() {
		assertTrue(Utils.isBlank("   "));
	}

	@Test
	public void isBlank04() {
		assertTrue(Utils.isBlank("  \t  \n \r"));
	}

	@Test
	public void isBlank05() {
		assertFalse(Utils.isBlank(" h "));
	}

	@Test
	public void isBlank06() {
		assertFalse(Utils.isBlank("\t n "));
	}

	@Test
	public void isNotBlank01() {
		assertTrue(Utils.isNotBlank(" h "));
	}

	@Test
	public void isNotBlank02() {
		assertTrue(Utils.isNotBlank(" h \t "));
	}

	@Test
	public void isNotBlank03() {
		assertFalse(Utils.isNotBlank("  \t  \n \r"));
	}

	@Test
	public void isNotBlank04() {
		assertFalse(Utils.isNotBlank(null));
	}

	@Test
	public void isNotBlank05() {
		assertFalse(Utils.isNotBlank(""));
	}

	@Test
	public void isNotBlank06() {
		assertFalse(Utils.isNotBlank("  "));
	}

}
