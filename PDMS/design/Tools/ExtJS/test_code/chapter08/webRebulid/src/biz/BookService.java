package biz;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.Book;
import bean.BookType;
import util.DBUtil;
import dao.BookDao;

public class BookService {
	private BookDao dao = new BookDao();
	/**
	 * 查询书籍类型列表
	 * @return 书籍列席列表
	 */
	public List getBookTypes(){
		List bookTypes = null;
		Connection con = null;
		try {
			con = DBUtil.getConnection();
			bookTypes = dao.getBookTypes(con);
		} catch (Exception e) {
			bookTypes = new ArrayList();
			e.printStackTrace();
		} finally{
			try {
				if(con != null){con.close();}
			} catch (SQLException e) {}
		}
		return bookTypes;
	}
	/**
	 * 查询指定书籍类型
	 * @param bookTypeId 书籍类型id
	 * @return 书籍类型对象
	 */
	public BookType getBookType(int bookTypeId){
		Connection con = null;
		BookType bookType = null;
		try {
			con = DBUtil.getConnection();
			bookType = dao.getBookType(con,bookTypeId);
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			try {
				if(con != null){con.close();}
			} catch (SQLException e) {}
		}
		return bookType;
	}
	/**
	 * 增加书籍类型
	 * @param bookType 书籍类型对象
	 * @return  增加成功返回true，否则返回false
	 */
	public int addBookType(BookType bookType){
		Connection con = null;
		int bookTypeId = -1;
		try {
			con = DBUtil.getConnection();
			dao.addBookType(con, bookType);
			bookTypeId = dao.getLastBookTypeId(con);
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			try {
				if(con != null){con.close();}
			} catch (SQLException e) {}
		}
		return bookTypeId;
	}
	/**
	 * 更新书籍类型
	 * @param bookType 书籍类型对象
	 * @return 增加成功返回true，否则返回false
	 */
	public boolean updateBookType(BookType bookType){
		Connection con = null;
		boolean isSuccess = true;
		try {
			con = DBUtil.getConnection();
			dao.updateBookType(con, bookType);
		} catch (Exception e) {
			isSuccess = false;
			e.printStackTrace();
		} finally{
			try {
				if(con != null){con.close();}
			} catch (SQLException e) {}
		}
		return isSuccess;
	}
	/**
	 *  查询某种类型书籍的数量
	 * @param bookTypeId 书籍类型id
	 * @return 书籍数量
	 */
	public int getBookNum(int bookTypeId){
		Connection con = null;
		int num = 0;
		try {
			con = DBUtil.getConnection();
			num = dao.getBookNum(con, bookTypeId);
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			try {
				if(con != null){con.close();}
			} catch (SQLException e) {}
		}
		return num;
	}
	/**
	 * 删除书籍类型
	 * @param bookTypeId 书籍类型id
	 * @return 删除成功返回true，否则返回false
	 */
	public boolean deleteBookType(int bookTypeId){
		Connection con = null;
		boolean isSuccess = true;
		try {
			con = DBUtil.getConnection();
			dao.delBookType(con, bookTypeId);
		} catch (Exception e) {
			isSuccess = false;
			e.printStackTrace();
		} finally{
			try {
				if(con != null){con.close();}
			} catch (SQLException e) {}
		}
		return isSuccess;
	}
	/**
	 * 查询书籍列表
	 * @return 书籍列表
	 */
	public List getBooks(){
		List books = null;
		Connection con = null;
		try {
			con = DBUtil.getConnection();
			books = dao.getBooks(con);
		} catch (Exception e) {
			books = new ArrayList();
			e.printStackTrace();
		} finally{
			try {
				if(con != null){con.close();}
			} catch (SQLException e) {}
		}
		return books;
	}
	/**
	 * 查询指定书籍
	 * @param bookId 书籍id
	 * @return 书籍对象
	 */
	public Book getBook(int bookId){
		Connection con = null;
		Book book = null;
		try {
			con = DBUtil.getConnection();
			book = dao.getBook(con,bookId);
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			try {
				if(con != null){con.close();}
			} catch (SQLException e) {}
		}
		return book;
	}
	/**
	 * 增加书籍
	 * @param book 书籍对象
	 * @return 增加成功返回true，否则返回false
	 */
	public int addBook(Book book){
		Connection con = null;
		int bookId = -1;
		try {
			con = DBUtil.getConnection();
			con.setAutoCommit(false);
			dao.addBook(con, book);
			bookId = dao.getLastBookId(con);
			con.commit();
		} catch (Exception e) {
			try {
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally{
			try {
				if(con != null){con.close();}
			} catch (SQLException e) {}
		}
		return bookId;
	}
	/**
	 * 更新书籍信息
	 * @param book 书籍对象
	 * @return 更新书籍成功返回true，否则返回false
	 */
	public boolean updateBook(Book book){
		Connection con = null;
		boolean isSuccess = true;
		try {
			con = DBUtil.getConnection();
			dao.updateBook(con, book);
		} catch (Exception e) {
			isSuccess = false;
			e.printStackTrace();
		} finally{
			try {
				if(con != null){con.close();}
			} catch (SQLException e) {}
		}
		return isSuccess;
	}
	/**
	 * 删除书籍
	 * @param bookId 书籍id
	 * @return 删除书籍成功返回true，否则返回false
	 */
	public boolean deleteBook(int bookId){
		Connection con = null;
		boolean isSuccess = true;
		try {
			con = DBUtil.getConnection();
			dao.delBook(con, bookId);
		} catch (Exception e) {
			isSuccess = false;
			e.printStackTrace();
		} finally{
			try {
				if(con != null){con.close();}
			} catch (SQLException e) {}
		}
		return isSuccess;
	}
	/**
	 * 成批删除书籍信息
	 * @param bookIds 书籍id列表
	 * @return 成批删除书籍成功返回true，否则返回false
	 */
	public boolean deleteBooks(List bookIds){
		Connection con = null;
		boolean isSuccess = true;
		try {
			con = DBUtil.getConnection();
			con.setAutoCommit(false);//设置是否自动提交事务 设置为不自动提交事务
			for(int i = 0 ; i < bookIds.size() ; i++){
				int bookId = (Integer)bookIds.get(i);
				dao.delBook(con, bookId);
			}
			con.commit();
		} catch (Exception e) {
			try {
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			isSuccess = false;
			e.printStackTrace();
		} finally{
			try {
				if(con != null){con.close();}
			} catch (SQLException e) {}
		}
		return isSuccess;
	}
}