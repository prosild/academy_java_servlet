package book.dao;

import static java.sql.DriverManager.getConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import book.exception.DuplicateException;
import book.exception.NotFoundException;
import book.vo.Book;

public class JdbcBookShelf implements BookShelf {

	// DB 커넥션을 위한 정보
	private static final String URL = "jdbc:oracle:thin:@//127.0.0.1:1521/XE";
	private static final String USER = "SCOTT";
	private static final String PASSWORD = "TIGER";
	private static final String DRIVER = "oracle.jdbc.OracleDriver";

	// 싱글턴 2. 이 클래스 타입의 static 변수 instance를 선언
	public static JdbcBookShelf instance;
	
	// 싱글턴 3. 이 클래스 타입을 리턴하는 static 메소드 선언
	public static JdbcBookShelf getInstance() {
		if (instance == null) {
			instance = new JdbcBookShelf();
		}
		
		return instance;
	}

	// 2. 생성자 선언
	// 싱글턴 1. 생성자를 private으로 변경
	private JdbcBookShelf() { 
		// 드라이버 로드는 실행할 때 최초 1번만 수행하면 되므로
		// 한번 실행되는 생성자로 이동
		
		// 1. 드라이버 로드
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			System.err.println("드라이버 로드 오류!!");
			e.printStackTrace();
		}
	}
	
	@Override
	public int insert(Book book) throws DuplicateException {
		// 추가하려는 제품이 이미 존재하는지 검사
		if (isExists(book)) {
			throw new DuplicateException("insert", book);
		}
		
		// 0. 필요 객체 선언
		int insertCnt = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			// 2. 커넥션 맺기
			conn = getConnection(URL, USER, PASSWORD);
			
			// 3. 쿼리 준비
			String sql = "INSERT INTO book (bookid, title, author, price, isbn, publish)"
					   + "VALUES (?, ?, ?, ?, ?, ?)";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, book.getBookId());
			pstmt.setString(2, book.getTitle());
			pstmt.setString(3, book.getAuthor());
			pstmt.setInt(4, book.getPrice());
			pstmt.setString(5, book.getIsbn());
			pstmt.setString(6, book.getPublish());
			
			// 4. 쿼리 실행
			insertCnt = pstmt.executeUpdate();
			
			// 5. 결과 처리
			//   ==> 쿼리 실행 전 중복 여부 검사하므로 특별한
			//       결과 처리가 필요 없음
		} catch (SQLException e) {
			e.printStackTrace();
			
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			// 6. 자원 해제
			closeResources(null, pstmt, conn);
		}
		
		return insertCnt;
	}

	@Override
	public int update(Book book) throws NotFoundException {
		if (isExists(book)) {
			throw new NotFoundException("update", book);
		}
		
		// 0. 필요 객체 선언
		int updateCnt = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			// 2. 커넥션 맺기
			conn = getConnection(URL, USER, PASSWORD);
			
			// 3. 쿼리 준비
			String sql = "UPDATE book SET title = ?"
					   + "              , author = ?"
					   + "              , price = ?"
					   + "              , isbn = ?"
					   + "              , publish = ?"
					   + "              , moddate = sysdate";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, book.getTitle());
			pstmt.setString(2, book.getAuthor());
			pstmt.setInt(3, book.getPrice());
			pstmt.setString(4, book.getIsbn());
			pstmt.setString(5, book.getPublish());
			
			// 4. 쿼리 실행
			updateCnt = pstmt.executeUpdate();
			
			// 5. 결과 처리
			//   ==> 쿼리 실행 전 중복 여부 검사하므로 특별한
			//       결과 처리가 필요 없음
		} catch (SQLException e) {
			e.printStackTrace();
			
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			// 6. 자원 해제
			closeResources(null, pstmt, conn);
		}
		
		return updateCnt;
	}

	@Override
	public int delete(Book book) throws NotFoundException {
		if (isExists(book)) {
			throw new NotFoundException("delete", book);
		}
		
		// 0. 필요 객체 선언
		int deleteCnt = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			// 2. 커넥션 맺기
			conn = getConnection(URL, USER, PASSWORD);
			
			// 3. 쿼리 준비
			String sql = "DELETE FROM book"
					   + " WHERE bookid = ?";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, book.getBookId());
			
			// 4. 쿼리 실행
			deleteCnt = pstmt.executeUpdate();
			
			// 5. 결과 처리
			//   ==> 쿼리 실행 전 중복 여부 검사하므로 특별한
			//       결과 처리가 필요 없음
		} catch (SQLException e) {
			e.printStackTrace();
			
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			// 6. 자원 해제
			closeResources(null, pstmt, conn);
		}
		
		return deleteCnt;
	}

	@Override
	public Book selectOne(Book book) throws NotFoundException {
		if (isExists(book)) {
			throw new NotFoundException("select", book);
		}
		
		// 0. 필요 객체 선언
		Book found = null;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet result = null;
		
		try {
			// 2. 커넥션 맺기
			conn = getConnection(URL, USER, PASSWORD);
			
			String sql = "SELECT b.BOOKID" 
					   + "  FROM book b"
					   + " WHERE b.BOOKID = ?";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, book.getBookId());
			
			result = pstmt.executeQuery();
			
			while (result.next()) {
				String bookId = result.getString(1);
				String title = result.getString(2);
				String author = result.getString(3);
				int price = result.getInt(4);
				String isbn = result.getString(5);
				String publish = result.getString(6);
				
				found = new Book(bookId, title, author, price, isbn, publish);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			closeResources(result, pstmt, conn);
		}
		
		return found;
	}

	@Override
	public List<Book> selectAll() {
		
		Book found = null;
		List<Book> books = new ArrayList<>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet result = null;
		
		try {
			conn = getConnection(URL, USER, PASSWORD);
			
			String sql = "SELECT b.BOOKID" 
					   + "  FROM book b";
			
			pstmt = conn.prepareStatement(sql);
			
			result = pstmt.executeQuery();
			
			while (result.next()) {
				String bookId = result.getString(1);
				String title = result.getString(2);
				String author = result.getString(3);
				int price = result.getInt(4);
				String isbn = result.getString(5);
				String publish = result.getString(6);
				
				found = new Book(bookId, title, author, price, isbn, publish);
				
				books.add(found);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			closeResources(result, pstmt, conn);
		}
		
		
		return books;
	}

	@Override
	public List<Book> selectPrice(int low, int high) {
		
		Book found = null;
		List<Book> books = new ArrayList<>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet result = null;
		
		try {
			conn = getConnection(URL, USER, PASSWORD);
			
			String sql = "SELECT b.BOOKID"
					   + "     , b.TITLE"
					   + "     , b.AUTHOR"
					   + "     , b.PRICE"
					   + "     , b.ISBN"
					   + "     , b.PUBLISH"
					   + "  FROM book b"
					   + " WHERE b.PRICE BETWEEN ? AND ?";
			
			pstmt = conn.prepareStatement(sql);
			
			result = pstmt.executeQuery();
			
			while (result.next()) {
				String bookId = result.getString(1);
				String title = result.getString(2);
				String author = result.getString(3);
				int price = result.getInt(4);
				String isbn = result.getString(5);
				String publish = result.getString(6);
				
				found = new Book(bookId, title, author, price, isbn, publish);
				
				books.add(found);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			closeResources(result, pstmt, conn);
		}
		
		return books;
	}

	@Override
	public List<Book> selectKeyword(String keyword) {

		Book found = null;
		List<Book> books = new ArrayList<>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet result = null;
		
		try {
			conn = getConnection(URL, USER, PASSWORD);
			
			String sql = "SELECT b.BOOKID"
					   + "     , b.TITLE"
					   + "     , b.AUTHOR"
					   + "     , b.PRICE"
					   + "     , b.ISBN"
					   + "     , b.PUBLISH"
					   + "  FROM book b"
					   + " WHERE b.TITLE LIKE '%" + "?" + "%'";
			
			pstmt = conn.prepareStatement(sql);
			
			result = pstmt.executeQuery();
			
			while (result.next()) {
				String bookId = result.getString(1);
				String title = result.getString(2);
				String author = result.getString(3);
				int price = result.getInt(4);
				String isbn = result.getString(5);
				String publish = result.getString(6);
				
				found = new Book(bookId, title, author, price, isbn, publish);
				
				books.add(found);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			closeResources(result, pstmt, conn);
		}
		
		return books;
	}

	@Override
	public int totalCount() {
		int count = 0;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet result = null;
		
		try {
			conn = getConnection(URL, USER, PASSWORD);
			
			String sql = "SELECT COUNT(b.BOOKID)"
					   + "  FROM book b";
			
			pstmt = conn.prepareStatement(sql);
			
			result = pstmt.executeQuery();
			
			while (result.next()) {
				count = result.getInt(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return count;
	}
	
	private boolean isExists(Book book) {
		boolean isExist = false;
		
		// 0. 필요객체 선언
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet result = null;		

		try {
			// 2. 커넥션
			conn = getConnection(URL, USER, PASSWORD);
			
			// 3. 쿼리 준비
			String sql = "SELECT b.bookid" 
			           + "  FROM book b" 
					   + " WHERE b.bookid = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, book.getBookId());
			
			// 4. 쿼리 실행
			result = pstmt.executeQuery();
			
			// 5. 결과 처리
			while (result.next()) {
				// 조회 결과가 있다는 뜻은 동일 제품 코드가 등록되었음
				isExist = true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			// 6. 자원 해제
			closeResources(result, pstmt, conn);
		}
		
		return isExist;
	}
	
	/**
	 * 반복되는 자원 해제코드를 수행하는 지원 메소드
	 * @param result
	 * @param stmt
	 * @param conn
	 */
	private void closeResources(ResultSet result, Statement stmt, Connection conn) {
		try {
			if (result != null) {
				result.close();
			}
			if (stmt != null) {
				stmt.close();					
			}
			if (conn != null) {
				conn.close();
			}
			
		} catch (SQLException e) {
			System.err.println("자원 반납 오류!");
			e.printStackTrace();
		}
		
	}

}
