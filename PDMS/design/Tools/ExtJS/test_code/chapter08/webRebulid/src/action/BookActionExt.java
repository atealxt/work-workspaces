package action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import bean.Book;
import bean.BookType;
import biz.BookService;

import util.ExtHelper;

public class BookActionExt extends DispatchAction {

	private BookService service = new BookService();
	/*
	 * 显示书籍列表页面
	 */
	public ActionForward showBookList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		return mapping.findForward("bookList");
	}
	/*
	 * 显示书籍类型列表页面
	 */
	public ActionForward showBookTypeList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		return mapping.findForward("bookTypeList");
	}
	/*
	 * 查询书籍列表
	 */
	public ActionForward getBookList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		List books = service.getBooks();
		String xml = ExtHelper.getXmlFromList(books);
		response.setContentType("application/xml;charset=UTF-8");
		response.getWriter().write(xml);
		return null;
	}
	/*
	 * 查询书籍类型列表
	 */
	public ActionForward getBookTypeList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		List bookTypes = service.getBookTypes();
		String xml = ExtHelper.getXmlFromList(bookTypes);
		response.setContentType("application/xml;charset=UTF-8");
		response.getWriter().write(xml);
		return null;
	}
	/*
	 * 新增书籍
	 */
	public ActionForward addBook(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		String bookName = request.getParameter("bookName");
		String author = request.getParameter("author");
		int bookTypeId = Integer.parseInt(request.getParameter("bookTypeId"));
		float price = Float.parseFloat(request.getParameter("price"));
		String brief = request.getParameter("brief");
		Book book = new Book();
		book.setBookName(new String(bookName.getBytes("ISO-8859-1"),"UTF-8"));
		book.setAuthor(new String(author.getBytes("ISO-8859-1"),"UTF-8"));
		book.setBookTypeId(bookTypeId);
		book.setPrice(price);
		book.setBrief(new String(brief.getBytes("ISO-8859-1"),"UTF-8"));
		int bookId = service.addBook(book);
		boolean isSuccess = true;
		if(bookId == -1){
			isSuccess = false;
		}
		response.setContentType("text/json;charset=UTF-8");
		response.getWriter().write("{success:"+isSuccess+",bookId:"+bookId+"}");
		return null;
	}
	/*
	 * 新增书籍类型
	 */
	public ActionForward addBookType(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		String title = request.getParameter("title");
		String detail = request.getParameter("detail");
		BookType bookType = new BookType();
		bookType.setTitle(new String(title.getBytes("ISO8859-1"),"UTF-8"));
		bookType.setDetail(new String(detail.getBytes("ISO8859-1"),"UTF-8"));
		int bookTypeId = service.addBookType(bookType);
		boolean isSuccess = true;
		if(bookTypeId == -1){
			isSuccess = false;
		}
		response.setContentType("text/json;charset=UTF-8");
		response.getWriter().write("{success:"+isSuccess+",bookTypeId:"+bookTypeId+"}");
		return null;
	}
	/*
	 * 修改书籍信息
	 */
	public ActionForward modifyBook(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		int bookId = Integer.parseInt(request.getParameter("id"));
		String bookName = request.getParameter("bookName");
		String author = request.getParameter("author");
		int bookTypeId = Integer.parseInt(request.getParameter("bookTypeId"));
		float price = Float.parseFloat(request.getParameter("price"));
		String brief = request.getParameter("brief");
		Book book = new Book();
		book.setId(bookId);
		book.setBookName(new String(bookName.getBytes("ISO-8859-1"),"UTF-8"));
		book.setAuthor(new String(author.getBytes("ISO-8859-1"),"UTF-8"));
		book.setBookTypeId(bookTypeId);
		book.setPrice(price);
		book.setBrief(new String(brief.getBytes("ISO-8859-1"),"UTF-8"));
		boolean isSuccess = service.updateBook(book);
		response.setContentType("text/json;charset=UTF-8");
		response.getWriter().write("{success:"+isSuccess+",bookId:"+bookId+"}");
		return null;
	}
	/*
	 * 修改书籍类型信息
	 */
	public ActionForward modifyBookType(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		int bookTypeId = Integer.parseInt(request.getParameter("id"));
		String title = request.getParameter("title");
		String detail = request.getParameter("detail");
		BookType bookType = new BookType();
		bookType.setId(bookTypeId);
		bookType.setTitle(new String(title.getBytes("ISO8859-1"),"UTF-8"));
		bookType.setDetail(new String(detail.getBytes("ISO8859-1"),"UTF-8"));
		boolean isSuccess = service.updateBookType(bookType);
		response.setContentType("text/json;charset=UTF-8");
		response.getWriter().write("{success:"+isSuccess+",bookTypeId:"+bookTypeId+"}");
		return null;
	}
	/*
	 * 删除书籍类型
	 */
	public ActionForward deleteBookType(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		int bookTypeId = Integer.parseInt(request.getParameter("bookTypeId"));
		int num = service.getBookNum(bookTypeId);
		response.setContentType("text/json;charset=UTF-8");
		if(num == 0){
			boolean isSuccess = service.deleteBookType(bookTypeId);
			response.getWriter().write("{success:"+isSuccess+",num:"+num+"}");
		}else{
			response.getWriter().write("{success:false,num:"+num+"}");
		}
		return null;
	}
	/*
	 * 根据书籍id查询书籍信息
	 */
	public ActionForward getBookById(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		response.setContentType("text/json;charset=UTF-8");
		int bookId = Integer.parseInt(request.getParameter("bookId"));
		Book book = service.getBook(bookId);
		String json = null;
		if(book != null){
			json = "{success:true,data:"+ExtHelper.getJsonFromBean(book)+"}";
		}else{
			json = "{success:false}";
		}
		response.getWriter().write(json);
		return null;
	}
	/*
	 * 根据书籍类型id查询书籍类型信息
	 */
	public ActionForward getBookTypeById(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		response.setContentType("text/json;charset=UTF-8");
		int bookTypeId = Integer.parseInt(request.getParameter("bookTypeId"));
		BookType bookType = service.getBookType(bookTypeId);
		String json = null;
		if(bookType != null){
			json = "{success:true,data:"+ExtHelper.getJsonFromBean(bookType)+"}";
		}else{
			json = "{success:false}";
		}
		response.getWriter().write(json);
		return null;
	}
	/*
	 * 删除书籍信息
	 */
	public ActionForward deleteBooks(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		String bookIds = request.getParameter("bookIds");
		String[] ids =  bookIds.split("-");
		List idList = new ArrayList();
		for(int i = 0 ; i < ids.length ; i++){
			Integer id = new Integer(ids[i]);
			idList.add(id);
		}
		boolean isSuccess = service.deleteBooks(idList);
		response.setContentType("text/json;charset=UTF-8");
		response.getWriter().write("{success:"+isSuccess+"}");
		return null;
	}
}
