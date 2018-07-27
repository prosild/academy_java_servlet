package book.dao;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import book.exception.DuplicateException;
import book.exception.NotFoundException;
import book.vo.Book;

/**
 * 책 정보를 저장할 자료구조를 Set으로 관리하는 선반 클래스
 *
 * @author PC38218
 *
 */
public class SetBookShelf implements BookShelf {
	
	private Set<Book> books;
	
	public SetBookShelf() {
		books = new HashSet<>();
	}
	
	public SetBookShelf(Set<Book> books) {
		this.books = books;
	}

	@Override
	public int insert(Book book) throws DuplicateException {
		int insertCnt = 0;
		
		if (!isExists(book)) {
			books.add(book);
			
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
			books.remove(book);
			books.add(book);
			
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
			books.remove(book);
			
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
			for (Book bk: books) {
				if (bk.equals(book)) {
					found = bk;
				}
			}
			
		} else {
			throw new NotFoundException("select", book);
		}
		
		return found;
	}
	
	@Override
	public List<Book> selectAll() {
		List<Book> books = new ArrayList<>();
		
		for (Book book: this.books) {
			books.add(book);
		}
		
		return books;
	}
	
	// Set에 book 객체 존재 유무 판단
	// 있으면 true
	// 없으면 false
	private boolean isExists(Book book) {
		return books.contains(book);
	}

	@Override
	public List<Book> selectPrice(int low, int high) {
		List<Book> books = new ArrayList<>();
		
		for (Book book: this.books) {
			if (book.getPrice() >= low && book.getPrice() <= high) {
				books.add(book);
			}
		}
		
		return books;
	}

	@Override
	public List<Book> selectKeyword(String keyword) {
		List<Book> books = new ArrayList<>();
		
		for (Book book: this.books) {
			if (book.getTitle().contains(keyword)) {
				books.add(book);
			}
		}
		
		return books;
	}

	@Override
	public int totalCount() {
		int count = 0;
		
		for (Book book: this.books) {
			count++;
		}
		
		return count;
	}

}
