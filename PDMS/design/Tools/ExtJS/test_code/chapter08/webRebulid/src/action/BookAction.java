package action;

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

public class BookAction extends DispatchAction{
	private BookService service = new BookService();
	/*
	 * 跳转到书籍类型列表页面
	 */
	public ActionForward showBookTypeList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		List bookTypes = service.getBookTypes();
		request.setAttribute("bookTypes", bookTypes);
		return mapping.findForward("bookTypeList");
	}
	/*
	 * 跳转到书籍列表页面
	 */
	public ActionForward showBookList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		List books = service.getBooks();
		request.setAttribute("books", books);
		return mapping.findForward("bookList");
	}
	/*
	 * 跳转到书籍新增页面
	 */
	public ActionForward toAddBook(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		List bookTypes = service.getBookTypes();
		request.setAttribute("bookTypes", bookTypes);
		return mapping.findForward("addBook");
	}
	/*
	 * 跳转到书籍修改页面
	 */
	public ActionForward toModifyBook(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		int bookId = Integer.parseInt(request.getParameter("bookId"));
		List bookTypes = service.getBookTypes();
		request.setAttribute("bookTypes", bookTypes);
		Book book = service.getBook(bookId);
		request.setAttribute("book", book);
		return mapping.findForward("modifyBook");
	}
	/*
	 * 修改书籍信息
	 */
	public ActionForward modifyBook(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		int bookId = Integer.parseInt(request.getParameter("bookId"));
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
		if(isSuccess){
			return mapping.findForward("toBookList");
		}else{
			return mapping.findForward("error");
		}
	}
	/*
	 * 新增书籍
	 * 操作成功后将页面跳转到书籍列表页面
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
		if(bookId != -1){
			return mapping.findForward("toBookList");
		}else{
			return mapping.findForward("error");
		}
	}
	/*
	 * 新增书籍类型
	 * 操作成功后将页面跳转到书籍类型列表页面
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
		if(bookTypeId != -1){
			return mapping.findForward("toBookTypeList");
		}else{
			return mapping.findForward("error");
		}
	}
	/*
	 * 跳转到书籍类型新增页面
	 */
	public ActionForward toAddBookType(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		return mapping.findForward("addBookType");
	}
	/*
	 * 跳转到书籍类型修改页面
	 */
	public ActionForward toModifyBookType(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		int bookTypeId = Integer.parseInt(request.getParameter("bookTypeId"));
		BookType bookType = service.getBookType(bookTypeId);
		request.setAttribute("bookType", bookType);
		return mapping.findForward("modifyBookType");
	}
	/*
	 * 修改书籍类型信息
	 */
	public ActionForward modifyBookType(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		int bookTypeId = Integer.parseInt(request.getParameter("bookTypeId"));
		String title = request.getParameter("title");
		String detail = request.getParameter("detail");
		BookType bookType = new BookType();
		bookType.setId(bookTypeId);
		bookType.setTitle(new String(title.getBytes("ISO8859-1"),"UTF-8"));
		bookType.setDetail(new String(detail.getBytes("ISO8859-1"),"UTF-8"));
		boolean isSuccess = service.updateBookType(bookType);
		if(isSuccess){
			return mapping.findForward("toBookTypeList");
		}else{
			return mapping.findForward("error");
		}
	}
	/*
	 * 删除书籍类型
	 * 操作成功后将页面跳转到书籍类型列表页面
	 */
	public ActionForward deleteBookType(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		int bookTypeId = Integer.parseInt(request.getParameter("bookTypeId"));
		int num = service.getBookNum(bookTypeId);
		if(num == 0){
			boolean isSuccess = service.deleteBookType(bookTypeId);
			if(isSuccess){
				return mapping.findForward("toBookTypeList");
			}else{
				return mapping.findForward("error");
			}
		}else{
			request.setAttribute("message", "已包含书籍的书籍类型不能删除！");
			request.setAttribute("method", "showBookTypeList");
			return mapping.findForward("message");
		}
	}
	/*
	 * 删除书籍
	 * 操作成功后将页面跳转到书籍列表页面
	 */
	public ActionForward deleteBook(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		int bookId = Integer.parseInt(request.getParameter("bookId"));
		boolean isSuccess = service.deleteBook(bookId);
		if(isSuccess){
			return mapping.findForward("toBookList");
		}else{
			return mapping.findForward("error");
		}
	}
}
