package shop.dao;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import shop.exception.DuplicateException;
import shop.exception.NotFoundException;
import shop.vo.Product;

public class SetWarehouse implements GeneralWarehouse {

	// 1. 멤버 변수 : 제품을 저장할 자료구조로 set 선택
	private Set<Product> products;
	
	// 2. 생성자
	// (1) 기본 생성자
	public SetWarehouse() {
		products = new HashSet<Product>();
	}
	
	// (2) 매개변수를 받는 생성자
	public SetWarehouse(Set<Product> products) {
		super();
		this.products = products;
	}
	
	// 3. 메소드
	@Override
	public int add(Product product) throws DuplicateException {
		int addSuccess = 0;
		
		// 같은 객체가 있는지 검사
		if (!isExists(product)) {
			products.add(product);
			addSuccess = 1;
		} else {
			// 같은 객체가 존재함
			throw new DuplicateException("add", product);
		}
		
		return addSuccess;
	}

	@Override
	public Product get(Product product) throws NotFoundException {
		Product found = null;
		
		if (isExists(product)) {
			for (Product prod: products) {
				if (prod.equals(product)) {
					found = prod;
					
					break;
				}
			}			
		} else {
			// 조회할 제품이 존재하지 않음
			throw new NotFoundException("get", product);
		}
		
		return found;
	}

	@Override
	public int set(Product product) throws NotFoundException {
		// Set 은 수정기능의 api 가 없으므로 
		// 기존 것 remove 후 add
		int setSuccess = 0;
		
		if (isExists(product)) {
			products.remove(product);
			products.add(product);
			setSuccess = 1;
		} else {
			// 수정할 제품이 존재하지 않음
			throw new NotFoundException("set", product);
		}
		
		return setSuccess;
	}

	@Override
	public int remove(Product product) {
		int removeSuccess = 0;
		
		if (isExists(product)) {
			products.remove(product);
			removeSuccess = 1;
		}
		
		return removeSuccess;
	}

	@Override
	public List<Product> getAllProducts() {
		List<Product> products = new ArrayList<Product>();
		
		for (Product product: this.products) {
			products.add(product);
		}
		
		return products;
	}

	// 제품이 이미 존재하는지 여부 확인
	// true : 존재함
	// false : 없음
	private boolean isExists(Product product) {
		return products.contains(product);
	}
	
}