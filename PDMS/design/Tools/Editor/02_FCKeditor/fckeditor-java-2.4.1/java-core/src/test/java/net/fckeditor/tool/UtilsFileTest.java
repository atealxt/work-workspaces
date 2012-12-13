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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;


/**
 * Tests for {@link UtilsFile}.
 *
 * @version $Id: UtilsFileTest.java 1848 2008-03-30 23:04:49Z mosipov $
 */
public class UtilsFileTest {

	@Test
    public void isSingleExtension01() {
    	boolean condition = UtilsFile.isSingleExtension("hacked.txt");
    	assertTrue(condition);
    }

	@Test
    public void isSingleExtension02() {
    	boolean condition = UtilsFile.isSingleExtension("hacked.php_txt");
    	assertTrue(condition);
    }

	@Test
    public void isSingleExtension03() {
    	boolean condition = UtilsFile.isSingleExtension("hacked.php.txt");
    	assertFalse(condition);
    }

	@Test
    public void isSingleExtension04() {
    	boolean condition = UtilsFile.isSingleExtension("hacked.txt.");
    	assertFalse(condition);
    }

	@Test
    public void isSingleExtension05() {
    	boolean condition = UtilsFile.isSingleExtension("hacked..txt");
    	assertFalse(condition);
    }

	@Test
    public void forceSingleExtension01() {
    	String actual = UtilsFile.forceSingleExtension("hacked.txt");
    	assertEquals("hacked.txt", actual);
    }

	@Test
    public void forceSingleExtension02() {
    	String actual = UtilsFile.forceSingleExtension("hacked.php_txt");
    	assertEquals("hacked.php_txt", actual);
    }

	@Test
    public void forceSingleExtension03() {
    	String actual = UtilsFile.forceSingleExtension("hacked.php.txt");
    	assertEquals("hacked_php.txt", actual);
    }

	@Test
    public void forceSingleExtension04() {
    	String actual = UtilsFile.forceSingleExtension("hacked..txt");
    	assertEquals("hacked_.txt", actual);
    }

	@Test
    public void isValidPath01() {
    	String path = "";
    	boolean condition = UtilsFile.isValidPath(path);
    	assertFalse(condition);
    }

	@Test
    public void isValidPath02() {
    	String path = "/";
    	boolean condition = UtilsFile.isValidPath(path);
    	assertTrue(condition);
    }

	@Test
    public void isValidPath03() {
    	String path = "/./";
    	boolean condition = UtilsFile.isValidPath(path);
    	assertFalse(condition);
    }

	@Test
    public void isValidPath04() {
    	String path = "/newf/..";
    	boolean condition = UtilsFile.isValidPath(path);
    	assertFalse(condition);
    }

	@Test
    public void isValidPath05() {
    	String path = "/../";
    	boolean condition = UtilsFile.isValidPath(path);
    	assertFalse(condition);
    }

	@Test
    public void isValidPath06() {
    	String path = "/stuff/../..";
    	boolean condition = UtilsFile.isValidPath(path);
    	assertFalse(condition);
    }

	@Test
    public void isValidPath07() {
    	String path = "/my/stuff/../";
    	boolean condition = UtilsFile.isValidPath(path);
    	assertFalse(condition);
    }

	@Test
    public void isValidPath08() {
    	String path = "/my/stuff/";
    	boolean condition = UtilsFile.isValidPath(path);
    	assertTrue(condition);
    }

	@Test
    public void isValidPath09() {
    	String path = "/my/stuff";
    	boolean condition = UtilsFile.isValidPath(path);
    	assertFalse(condition);
    }

	@Test
    public void isValidPath10() {
    	String path = "my/stuff/";
    	boolean condition = UtilsFile.isValidPath(path);
    	assertFalse(condition);
    }
	
	@Test
    public void isValidPath11() {
    	String path = "/some/pa\\th/";
    	boolean condition = UtilsFile.isValidPath(path);
    	assertFalse(condition);
    }
	
	@Test
    public void isValidPath12() {
    	String path = "/\\/";
    	boolean condition = UtilsFile.isValidPath(path);
    	assertFalse(condition);
    }

	@Test
    public void sanitizeFolder01() {
    	assertEquals("a_b_c_d_e_f_g_h_i_", UtilsFile.sanitizeFolderName("a.b|c<d>e:f?g*h<i>"));
    }
	
	@Test
	public void sanitizeFolder02() {
		assertEquals("a_b_c_d_e_f_g_h_i_", UtilsFile.sanitizeFolderName("a.b|c\u007Fd>e:f\u0005g*h<i>"));
	}

	@Test
    public void sanitizeFile01() {
    	assertEquals("b_c_d_e_f_g_h_i_", UtilsFile.sanitizeFileName("b|c<d>e:f?g*h<i>"));
    }

	@Test
    public void sanitizeFile02() {
    	assertEquals("name_ext1.ext2", UtilsFile.sanitizeFileName("name.ext1.ext2"));
    }

	@Test
	public void sanitizeFile03() {
		assertEquals("b_c_d_e_f_g_h_i_", UtilsFile.sanitizeFileName("b|c\u007Fd>e:f\u0005g*h<i>"));
	}
}
