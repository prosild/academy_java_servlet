package book.factory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import book.dao.BookShelf;
import book.dao.ListBookShelf;
import book.dao.MapBookShelf;
import book.dao.MybatisShelf;
import book.dao.SetBookShelf;
import book.vo.Book;

/**
 * 창고 객체(GeneralBookShelf 타입) 생성을
 * 전문적으로 수행하는 팩토리 클래스
 * @author PC38218
 *
 */
public class BookShelfFactory {

	public static BookShelf getBookShelf(String type) {
		BookShelf shelf = null;
		
		if ("list".equals(type)) {
			List<Book> books = new ArrayList<>();
			shelf = new ListBookShelf(books);
			
		} else if ("set".equals(type)) {
			Set<Book> books = new HashSet<>();
			shelf = new SetBookShelf(books);

		} else if ("map".equals(type)) {
			Map<String, Book> books = new HashMap<>();
			shelf = new MapBookShelf(books);
			
		} else if("mybatis".equals(type)) {
			shelf = new MybatisShelf();
		}
		
		return shelf;
	}
}
