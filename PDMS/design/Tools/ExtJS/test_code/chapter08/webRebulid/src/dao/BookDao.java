package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import bean.Book;
import bean.BookType;

public class BookDao {
	/**
	 * 查询某种类型书籍的数量
	 * @param con
	 * @param bookTypeId 书籍类型id
	 * @return 书籍数量
	 * @throws SQLException
	 */
	public int getBookNum(Connection con,int bookTypeId) throws SQLException{
		String sql = "select count(*) from book where type_id = ?";
		PreparedStatement pst = con.prepareStatement(sql);
		pst.setInt(1, bookTypeId);
		ResultSet rs = pst.executeQuery();
		int num = 0 ;
		while(rs.next()){
			num = rs.getInt(1);
		}
		return num;
	}
	/**
	 * 查询全部书籍类型列表
	 * @param con
	 * @return 书籍类型列表
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public List getBookTypes(Connection con) throws SQLException, ClassNotFoundException{
		List bookTypes = new ArrayList();
		Statement st = con.createStatement();
		String sql = "select id,title,detail from booktype";
		ResultSet rs = st.executeQuery(sql);
		while(rs.next()){
			BookType bookType = new BookType();
			bookType.setId(rs.getInt("id"));
			bookType.setTitle(rs.getString("title"));
			bookType.setDetail(rs.getString("detail"));
			bookTypes.add(bookType);
		}
		rs.close();
		st.close();
		return bookTypes;
	}
	/**
	 * 查询指定id的书籍类型
	 * @param con
	 * @param bookTypeId 书籍类型id
	 * @return 书籍类型对象
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public BookType getBookType(Connection con ,int bookTypeId) throws SQLException, ClassNotFoundException{
		String sql = "select id,title,detail from booktype where id = ?";
		PreparedStatement pst = con.prepareStatement(sql);
		pst.setInt(1, bookTypeId);
		ResultSet rs = pst.executeQuery();
		BookType bookType = null;
		while(rs.next())
		{
			bookType = new BookType();
			bookType.setId(rs.getInt("id"));
			bookType.setTitle(rs.getString("title"));
			bookType.setDetail(rs.getString("detail"));
		}
		rs.close();
		pst.close();
		return bookType;
	}
	/**
	 * 新增书籍类型
	 * @param con
	 * @param bookType 书籍类型对象
	 * @throws SQLException
	 */
	public void addBookType(Connection con,BookType bookType) throws SQLException{
		String sql = "insert into booktype(title,detail) values(?,?)";
		PreparedStatement pst = con.prepareStatement(sql);
		pst.setString(1, bookType.getTitle());
		pst.setString(2, bookType.getDetail());
		pst.execute();
		pst.close();
	}
	/**
	 * 修改书籍类型
	 * @param con
	 * @param bookType 书籍类型对象
	 * @throws SQLException
	 */
	public void updateBookType(Connection con,BookType bookType) throws SQLException{
		String sql = "update booktype set title = ?,detail = ? where id = ?";
		PreparedStatement pst = con.prepareStatement(sql);
		pst.setString(1, bookType.getTitle());
		pst.setString(2, bookType.getDetail());
		pst.setInt(3, bookType.getId());
		pst.execute();
		pst.close();
	}
	/**
	 * 删除书籍类型
	 * @param con
	 * @param bookType 书籍类型id
	 * @throws SQLException
	 */
	public void delBookType(Connection con,int bookTypeId) throws SQLException{
		String sql = "delete from booktype where id = ?";
		PreparedStatement pst = con.prepareStatement(sql);
		pst.setInt(1, bookTypeId);
		pst.execute();
		pst.close();
	}
	/**
	 * 查询书籍列表
	 * @param con
	 * @return 书籍列表
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public List getBooks(Connection con) throws SQLException, ClassNotFoundException{
		List books = new ArrayList();
		Statement st = con.createStatement();
		String sql = "select b.id,b.book_name,b.author,b.type_id,t.title,b.price,b.brief from book b,booktype t where b.type_id = t.id order by b.id";
		ResultSet rs = st.executeQuery(sql);
		while(rs.next()){
			Book book = new Book();
			book.setId(rs.getInt("id"));
			book.setBookName(rs.getString("book_name"));
			book.setAuthor(rs.getString("author"));
			book.setBookTypeId(rs.getInt("type_id"));
			book.setTypeName(rs.getString("title"));
			book.setPrice(rs.getFloat("price"));
			book.setBrief(rs.getString("brief"));
			books.add(book);
		}
		rs.close();
		st.close();
		return books;
	}
	/**
	 * 查询指定id的书籍信息
	 * @param con
	 * @param bookId 书籍id
	 * @return 书籍对象
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public Book getBook(Connection con,int bookId) throws SQLException, ClassNotFoundException{
		String sql = "select b.id,b.book_name,b.author,b.type_id,t.title,b.price,b.brief from book b,booktype t where b.type_id = t.id and b.id=?";
		PreparedStatement pst = con.prepareStatement(sql);
		pst.setInt(1, bookId);
		ResultSet rs = pst.executeQuery();
		Book book = null;
		if(rs.next()){
			book = new Book();
			book.setId(rs.getInt("id"));
			book.setBookName(rs.getString("book_name"));
			book.setAuthor(rs.getString("author"));
			book.setBookTypeId(rs.getInt("type_id"));
			book.setTypeName(rs.getString("title"));
			book.setPrice(rs.getFloat("price"));
			book.setBrief(rs.getString("brief"));
		}
		rs.close();
		pst.close();
		return book;
	}
	/**
	 * 新增书籍
	 * @param con
	 * @param book 书籍对象
	 * @throws SQLException
	 */
	public void addBook(Connection con,Book book) throws SQLException{
		String sql = "insert into book(book_name,author,type_id,price,brief) values(?,?,?,?,?)";
		PreparedStatement pst = con.prepareStatement(sql);
		pst.setString(1, book.getBookName());
		pst.setString(2, book.getAuthor());
		pst.setInt(3, book.getBookTypeId());
		pst.setFloat(4, book.getPrice());
		pst.setString(5, book.getBrief());
		pst.execute();
		pst.close();

	}
	/**
	 * 修改书籍信息
	 * @param con
	 * @param book 书籍对象
	 * @throws SQLException
	 */
	public void updateBook(Connection con,Book book) throws SQLException{
		String sql = "update book set book_name = ?,author = ? ,type_id = ? ,price = ? ,brief = ? where id = ?";
		PreparedStatement pst = con.prepareStatement(sql);
		pst.setString(1, book.getBookName());
		pst.setString(2, book.getAuthor());
		pst.setInt(3, book.getBookTypeId());
		pst.setFloat(4, book.getPrice());
		pst.setString(5, book.getBrief());
		pst.setInt(6, book.getId());
		pst.execute();
		pst.close();
	}
	/**
	 * 删除书籍
	 * @param con
	 * @param bookId 书籍id
	 * @throws SQLException
	 */
	public void delBook(Connection con,int bookId) throws SQLException{
		String sql = "delete from book where id = ?";
		PreparedStatement pst = con.prepareStatement(sql);
		pst.setInt(1, bookId);
		pst.execute();
		pst.close();
	}
	/**
	 * 查询最新的书籍id
	 * @param con
	 * @return 书籍id
	 * @throws SQLException
	 */
	public int getLastBookId(Connection con) throws SQLException{
		Statement st = con.createStatement();
		String sql = "select last_insert_id() bookId from book";
		ResultSet rs = st.executeQuery(sql);
		int bookId = -1;
		if(rs.next()){
			bookId = rs.getInt("bookId");
		}
		return bookId;
	}
	/**
	 * 查询最新的书籍id
	 * @param con
	 * @return 书籍id
	 * @throws SQLException
	 */
	public int getLastBookTypeId(Connection con) throws SQLException{
		Statement st = con.createStatement();
		String sql = "select last_insert_id() bookTypeId from booktype";
		ResultSet rs = st.executeQuery(sql);
		int bookId = -1;
		if(rs.next()){
			bookId = rs.getInt("bookTypeId");
		}
		return bookId;
	}
}