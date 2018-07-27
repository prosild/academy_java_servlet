package book.mapper;

import java.util.List;
import java.util.Map;

import book.vo.Book;

public interface BookMapper {

	int insert(Book book);
	
	int update(Book book);
	
	int delete(Book book);
	
	Book selectOne(Book book);
	
	List<Book> selectAll();
	
	List<Book> selectPrice(Map<String, Integer> price);
	
	List<Book> selectKeyword(String keyword);
	
	int totalCount();
	
	String isExists(Book book);
}
