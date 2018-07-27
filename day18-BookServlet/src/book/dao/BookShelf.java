package book.dao;

import java.util.List;

import book.exception.DuplicateException;
import book.exception.NotFoundException;
import book.vo.Book;

public interface BookShelf {

	/** 책을 1건 입력 */
	int insert(Book book) throws DuplicateException;
	
	/** 책을 1건 수정 */
	int update(Book book) throws NotFoundException;
	
	/** 책을 1건 삭제 */
	int delete(Book book) throws NotFoundException;
	
	/** 책을 1건 조회 */
	Book selectOne(Book book) throws NotFoundException;
	
	/** 책을 전부 조회 */
	List<Book> selectAll();
	
	/** low ~ high 사이의 가격대 책을 검색 */
	List<Book> selectPrice(int low, int high);
	
	/** 책 title에 keyword가 들어가는 책을 검색<br>
	 * 검색 결과는 목록으로 리턴함
	 */
	List<Book> selectKeyword(String keyword);
	
	/**
	 * 전체 등록된 책의 개수를 구하여 리턴
	 * @return
	 */
	int totalCount();
	
}
