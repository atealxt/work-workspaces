package net.fckeditor.response;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Tests for {@link UploadResponse}.
 * 
 * @version: $Id: UploadResponseTest.java 2167 2008-07-03 21:33:15Z mosipov $
 */
public class UploadResponseTest {

	@Test(expected = IllegalArgumentException.class)
	public void noArguments() throws Exception {
		new UploadResponse();
	}

	@Test(expected = IllegalArgumentException.class)
	public void tooManyArguments() throws Exception {
		new UploadResponse(101, "/some/url/file.txt", "file.txt",
				"something's wrong", "arg no. 5");
	}

	@Test(expected = IllegalArgumentException.class)
	public void notANumber() throws Exception {
		new UploadResponse("1");
	}

	@Test
	public void onlyErrorNumber() throws Exception {
		UploadResponse actual = new UploadResponse(
				UploadResponse.SC_INVALID_EXTENSION);
		String expected = new String("<script type=\"text/javascript\">\n"
				+ "(function(){var d=document.domain;while (true){try{var A=window.parent.document.domain;break;}catch(e) {};d=d.replace(/.*?(?:\\.|$)/,'');if (d.length==0) break;try{document.domain=d;}catch (e){break;}}})();\n"
				+ "window.parent.OnUploadCompleted(202);\n</script>");
		assertEquals(expected, actual.toString());
	}
	
	@Test
	public void fourArguments() throws Exception {
		UploadResponse actual = new UploadResponse(UploadResponse.SC_OK,"/fckeditor-java/userfiles/image/fredck.jpg");
		String expected = new String("<script type=\"text/javascript\">\n"
				+ "(function(){var d=document.domain;while (true){try{var A=window.parent.document.domain;break;}catch(e) {};d=d.replace(/.*?(?:\\.|$)/,'');if (d.length==0) break;try{document.domain=d;}catch (e){break;}}})();\n"
				+ "window.parent.OnUploadCompleted(0,'/fckeditor-java/userfiles/image/fredck.jpg');\n</script>");
		assertEquals(expected, actual.toString());
	}
	
	@Test
	public void renamedFile() throws Exception {
		UploadResponse actual = new UploadResponse(UploadResponse.SC_RENAMED,"/fckeditor-java/userfiles/image/hacked_php.txt","hacked_php.txt");
		String expected = new String("<script type=\"text/javascript\">\n"
				+ "(function(){var d=document.domain;while (true){try{var A=window.parent.document.domain;break;}catch(e) {};d=d.replace(/.*?(?:\\.|$)/,'');if (d.length==0) break;try{document.domain=d;}catch (e){break;}}})();\n"
				+ "window.parent.OnUploadCompleted(201,'/fckeditor-java/userfiles/image/hacked_php.txt','hacked_php.txt');\n</script>");
		assertEquals(expected, actual.toString());
	}

	@Test
	public void customMessage() throws Exception {
		UploadResponse actual = new UploadResponse(UploadResponse.SC_ERROR);
		actual.setCustomMessage("some error");
		String expected = new String("<script type=\"text/javascript\">\n"
				+ "(function(){var d=document.domain;while (true){try{var A=window.parent.document.domain;break;}catch(e) {};d=d.replace(/.*?(?:\\.|$)/,'');if (d.length==0) break;try{document.domain=d;}catch (e){break;}}})();\n"
				+ "window.parent.OnUploadCompleted(1,'','','some error');\n</script>");
		assertEquals(expected, actual.toString());
	}


	@Test
	public void nullArguments() throws Exception {
		UploadResponse actual = new UploadResponse(UploadResponse.SC_ERROR,null,null,null);
		String expected = new String("<script type=\"text/javascript\">\n"
				+ "(function(){var d=document.domain;while (true){try{var A=window.parent.document.domain;break;}catch(e) {};d=d.replace(/.*?(?:\\.|$)/,'');if (d.length==0) break;try{document.domain=d;}catch (e){break;}}})();\n"
				+ "window.parent.OnUploadCompleted(1,'','','');\n</script>");
		assertEquals(expected, actual.toString());
	}

}
