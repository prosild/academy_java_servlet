package book.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import book.exception.DuplicateException;
import book.exception.NotFoundException;
import book.mapper.BookMapper;
import book.mybatis.MybatisClient;
import book.vo.Book;

public class MybatisShelf implements BookShelf {
	
	private SqlSessionFactory factory;
	
	public MybatisShelf() {
		factory = MybatisClient.getFactory();
	}

	@Override
	public int insert(Book book) throws DuplicateException {
		// 추가할 대상 책이 있는지 선 조회
		if (isExists(book)) {
			throw new DuplicateException("insert", book);
		}
		
		// 1. SqlSession 얻기 : DML 작업은 auto-commit을 활성화
		SqlSession session = factory.openSession(true);
		int insertCnt = 0;
		
		// 2. Mapper 인터페이스 객체를 session에서 얻기
		BookMapper mapper;
		mapper = session.getMapper(BookMapper.class);
		
		try {
			// 3. mapper를 통하여 insert 진행
			insertCnt = mapper.insert(book);
		} finally {
			if (session != null) {
				session.close();
			}
		}
		
		return insertCnt;
	}

	@Override
	public int update(Book book) throws NotFoundException {
		// 수정할 대상 책이 있는지 선 조회
		if (!isExists(book)) {
			throw new NotFoundException("update", book);
		}
		
		// 1. SqlSession 얻기 : DML 작업은 auto-commit을 활성화
		SqlSession session = factory.openSession(true);
		int updateCnt = 0;
		
		// 2. Mapper 인터페이스 객체를 session에서 얻기
		BookMapper mapper;
		mapper = session.getMapper(BookMapper.class);
		
		try {
			updateCnt = mapper.update(book);
		} finally {
			if (session != null) {
				session.close();
			}
		}
		
		return updateCnt;
	}

	@Override
	public int delete(Book book) throws NotFoundException {
		// 삭제할 대상 책이 있는지 선 조회
		if (book != null && !isExists(book)) {
			throw new NotFoundException("delete", book);
		}
		
		// 1. SqlSession 얻기 : DML 작업은 auto-commit을 활성화
		SqlSession session = factory.openSession(true);
		int deleteCnt = 0;
		
		// 2. Mapper 인터페이스 객체를 session에서 얻기
		BookMapper mapper;
		mapper = session.getMapper(BookMapper.class);
		
		try {
			// 3. mapper를 통하여 삭제 진행
			deleteCnt = mapper.delete(book);
		} finally {
			if (session != null) {
				session.close();
			}
		}
		
		return deleteCnt;
	}

	@Override
	public Book selectOne(Book book) throws NotFoundException {
		// 조회할 대상 책이 있는지 선 조회
		if (!isExists(book)) {
			throw new NotFoundException("조회", book);
		}
		
		// 1. SqlSession 얻기 : DQL 작업은
		//						non-auto-commit으로 오픈해도 상관없음
		SqlSession session = factory.openSession();
		Book found = null;
		
		// 2. Mapper 인터페이스 객체를 session에서 얻기
		BookMapper mapper;
		mapper = session.getMapper(BookMapper.class);
		
		try {
			// 3. mapper를 통하여 삭제 진행
			found = mapper.selectOne(book);
			
		} finally {
			if (session != null) {
				session.close();
			}
		}
		
		return found;
	}

	@Override
	public List<Book> selectAll() {
		// 1. SqlSession 얻기
		SqlSession session = factory.openSession();
		
		// 2. Mapper 인터페이스 객체를 session에서 얻기
		BookMapper mapper;
		mapper = session.getMapper(BookMapper.class);
		
		List<Book> books= null;
		
		try {
			// 3. mapper를 통하여 조회
			books = mapper.selectAll();
		} finally {
			// 4. session 닫기
			if (session != null) {
				session.close();
			}
		}
		
		return books;
	}

	@Override
	public List<Book> selectPrice(int low, int high) {
		// low, high 값 담을 map 생성
		Map<String, Integer> price = new HashMap<>();
		price.put("low", low);
		price.put("high", high);
		
		// 1. SqlSession 얻기
		SqlSession session = factory.openSession();
		
		// 2. Mapper 인터페이스 객체를 session에서 얻기
		BookMapper mapper;
		mapper = session.getMapper(BookMapper.class);
		
		List<Book> books = null;
		
		try {
			// 3. mapper를 통하여 조회
			books = mapper.selectPrice(price);
			
		} finally {
			// 4. session 닫기
			if (session != null) {
				session.close();
			}
		}
		
		return books;
	}

	@Override
	public List<Book> selectKeyword(String keyword) {
		// 1. SqlSession 얻기
		SqlSession session = factory.openSession();
		
		// 2. Mapper 인터페이스 객체를 session에서 얻기
		BookMapper mapper;
		mapper = session.getMapper(BookMapper.class);
		
		List<Book> books = null;
		
		try {
			// 3. mapper를 통하여 조회
			books = mapper.selectKeyword(keyword);
			
		} finally {
			// 4. session 닫기
			if (session != null) {
				session.close();
			}
		}
		
		return books;
	}

	@Override
	public int totalCount() {
		// 1. SqlSession 얻기
		SqlSession session = factory.openSession();
		int totalCount = 0;
		
		// 2. Mapper 인터페이스 객체를 session에서 얻기
		BookMapper mapper;
		mapper = session.getMapper(BookMapper.class);
		
		try {
			// 3. mapper를 통하여 조회
			totalCount = mapper.totalCount();
			
		} finally {
			// 4. session 닫기
			if (session != null) {
				session.close();
			}
		}
		
		return totalCount;
	}
	
	// isExists 지원 메소드 작성
	// 있으면 true
	// 없으면 false
	private boolean isExists(Book book) {
		boolean isExists = false;
		
		// 1. SqlSession 얻기
		SqlSession session = factory.openSession();
		
		// 2. Mapper 인터페이스 객체를 session에서 얻기
		BookMapper mapper;
		mapper = session.getMapper(BookMapper.class);
		
		// 3. mapper 객체에 메소드 호출로 쿼리 실행
		try {
			String bookId = mapper.isExists(book);
			
			if (bookId != null) {
				isExists = true;
			}
			
		} finally {
			// 4. session 닫기
			if (session != null) {
				session.close();
			}
		}
		
		return isExists;
	}
	
}
