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
import org.junit.Test;

/**
 * Tests for {@link CommandHandler}.
 * 
 * @version $Id: CommandHandlerTest.java 1711 2008-03-17 16:21:05Z th-schwarz $
 */
public class CommandHandlerTest {

	@Test
	public void valueOf01() {
		assertEquals(CommandHandler.FILE_UPLOAD, CommandHandler
				.valueOf("FileUpload"));
	}

	@Test
	public void valueOf02() {
		assertEquals(CommandHandler.QUICK_UPLOAD, CommandHandler
				.valueOf("QuickUpload"));
	}

	@Test
	public void valueOf03() {
		assertEquals(CommandHandler.CREATE_FOLDER, CommandHandler
				.valueOf("CreateFolder"));
	}

	@Test
	public void valueOf04() {
		assertEquals(CommandHandler.GET_FOLDERS, CommandHandler
				.valueOf("GetFolders"));
	}

	@Test
	public void valueOf05() {
		assertEquals(CommandHandler.GET_FOLDERS_AND_FILES, CommandHandler
				.valueOf("GetFoldersAndFiles"));
	}

	@Test(expected = IllegalArgumentException.class)
	public void valueOfStringNull() {
		CommandHandler.valueOf(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void valueOfInvalidCommand() {
		CommandHandler.valueOf("GetAll");
	}

	@Test
	public void getCommandNull() {
		CommandHandler command = CommandHandler.getCommand(null);
		assertNull(command);
	}

	@Test
	public void getCommandInvalid() {
		CommandHandler command = CommandHandler.getCommand("DeleteFolders");
		assertNull(command);
	}

	@Test
	public void getCommandValid() {
		CommandHandler actual = CommandHandler.getCommand("FileUpload");
		assertEquals(CommandHandler.FILE_UPLOAD, actual);
	}

	@Test
	public void equalsNot01() {
		assertFalse(CommandHandler.GET_FOLDERS
				.equals(CommandHandler.FILE_UPLOAD));
	}

	@Test
	public void equalsNot02() {
		assertFalse(CommandHandler.GET_FOLDERS.equals(new Object()));
	}

	@Test
	public void hashCode01() {
		assertEquals("GetFoldersAndFiles".hashCode(),
				CommandHandler.GET_FOLDERS_AND_FILES.hashCode());
	}

	@Test
	public void hashCode02() {
		assertNotSame(-1, CommandHandler.FILE_UPLOAD.hashCode());
	}

	@Test
	public void toString01() {
		assertEquals("GetFoldersAndFiles", CommandHandler.GET_FOLDERS_AND_FILES
				.toString());
	}

}
