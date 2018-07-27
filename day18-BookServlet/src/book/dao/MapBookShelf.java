package book.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import book.exception.DuplicateException;
import book.exception.NotFoundException;
import book.vo.Book;

public class MapBookShelf implements BookShelf {
	
	// 멤버변수 선언
	private Map<String, Book> books;
	
	// 생성자
	// (1) 매개변수가 없는 생성자
	public MapBookShelf() {
		books = new HashMap<String, Book>();
	}
	
	// (2) 매개변수가 있는 생성자
	public MapBookShelf(Map<String, Book> books) {
		this.books = books;
	}
	
	public Map<String, Book> getBooks() {
		return books;
	}

	public void setBooks(Map<String, Book> books) {
		this.books = books;
	}

	@Override
	public int insert(Book book) throws DuplicateException {
		int insertCnt = 0;
		
		if (!isExists(book)) {
			books.put(book.getBookId(), book);
			
			insertCnt = 1;
			
		} else {
			throw new DuplicateException("insert", book);
		}
		
		return insertCnt;
	}

	@Override
	public int update(Book book) throws NotFoundException {
		int updateCnt = 0;
	
		if (isExists(book)) {
			books.replace(book.getBookId(), book);
			
			updateCnt = 1;
			
		} else {
			throw new NotFoundException("update", book);
		}
		
		return updateCnt;
	}

	@Override
	public int delete(Book book) throws NotFoundException {
		int deleteCnt = 0;
		
		if (isExists(book)) {
			books.remove(book.getBookId());
			
			deleteCnt = 1;
		} else {
			throw new NotFoundException("delete", book);
		}
		
		return deleteCnt;
	}

	@Override
	public Book selectOne(Book book) throws NotFoundException {
		Book found = null;
		
		if (isExists(book)) {
			found = books.get(book.getBookId());
		} else {
			throw new NotFoundException("select", book);
		}
		
		return found;
	}

	@Override
	public List<Book> selectAll() {
		List<Book> books = new ArrayList<>();
		
		for (Book book: this.books.values()) {
			books.add(book);
		}
		
		return books;
	}
	
	// 책이 이미 존재하는지 여부 확인
	// true  : 존재함
	// false : 없음	
	private boolean isExists(Book book) {		
		return books.containsKey(book.getBookId());
	}

	@Override
	public List<Book> selectPrice(int low, int high) {
		List<Book> books = new ArrayList<>();
		
		for (Book book: this.books.values()) {
			if (book.getPrice() >= low && book.getPrice() <= high) {
				books.add(book);
			}
		}
		
		return books;
	}

	@Override
	public List<Book> selectKeyword(String keyword) {
		List<Book> books = new ArrayList<>();
		
		for (Book book: this.books.values()) {
			if (book.getTitle().contains(keyword)) {
				books.add(book);
			}
		}
		return books;
	}

	@Override
	public int totalCount() {
		int count = 0;
		
		for (Book book: books.values()) {
			count++;
		}
		
		return count;
	}

}
