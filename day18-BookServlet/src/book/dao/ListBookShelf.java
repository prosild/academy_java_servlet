package book.dao;

import java.util.ArrayList;
import java.util.List;

import book.exception.DuplicateException;
import book.exception.NotFoundException;
import book.vo.Book;

/**
 * 책 정보를 저장할 자료구조를 리스트로 관리하는 선반 클래스
 * @author PC38218
 *
 */
public class ListBookShelf implements BookShelf {
	
	// 멤버변수 선언
	private List<Book> books;
	
	// 생성자
	public ListBookShelf() {
		books = new ArrayList<>();
	}
	
	public ListBookShelf(List<Book> books) {
		this.books = books;
	}
	
	// getter, setter
	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}

	// 인서트
	@Override
	public int insert(Book book) throws DuplicateException {
		int index = foundIndex(book);
		int insertCnt = 0;
		
		if (index == -1) {
			books.add(book);
			insertCnt = 1;
		} else {
			throw new DuplicateException("insert", book);
		}
		
		return insertCnt;
	}

	// 업데이트
	@Override
	public int update(Book book) throws NotFoundException {
		int index = foundIndex(book);
		int updateCnt = 0;
		
		if (index > -1) {
			books.set(index, book);
			updateCnt = 1;
		} else {
			throw new NotFoundException("update", book);
		}
		
		return updateCnt;
	}

	// 딜리트
	@Override
	public int delete(Book book) throws NotFoundException {
		int index = foundIndex(book);
		int deleteCnt = 0;
		
		if (index > -1) {
			books.remove(index);
			deleteCnt = 1;
		} else {
			throw new NotFoundException("delete", book);
		}
		
		return deleteCnt;
	}

	// 셀렉트 1개
	@Override
	public Book selectOne(Book book) throws NotFoundException {
		int index = foundIndex(book);
		Book found = null;
		
		if (index > -1) {
			found = books.get(index);
		} else {
			throw new NotFoundException("select", book);
		}
		
		return found;
	}

	// 셀렉트 all
	@Override
	public List<Book> selectAll() {
		return this.books;
	}
	
	// index 찾는 메소드
	private int foundIndex(Book book) {
		int index = -1;
		
		for (int idx = 0; idx < books.size(); idx++) {
			if (books.get(idx).equals(book)) {
				index = idx;
				break;
			}
		}
		
		return index;
	}

	@Override
	public List<Book> selectPrice(int low, int high) {
		List<Book> books = new ArrayList<>();
		
		for (Book book: books) {
			if (book.getPrice() >= low && book.getPrice() <= high) {
				books.add(book);
			}
		}
		return books;
	}

	@Override
	public List<Book> selectKeyword(String keyword) {
		List<Book> books = new ArrayList<>();
		
		for (Book book: books) {
			if (book.getTitle().contains(keyword)) {
				books.add(book);
			}
		}
		return books;
	}

	@Override
	public int totalCount() {
		int count = 0;
		
		for (Book book: books) {
			count++;
		}
		
		return count;
	}

}
