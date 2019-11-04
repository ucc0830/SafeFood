package test;

import java.io.IOException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import model.Food;

public class TestFood {

	public static void main(String[] args) throws IOException {
		SqlSession session = null;
		// ==> TestUtil 의 getSqlSessionFactory()을 이용 SqlSessionFactory instance GET
		SqlSessionFactory factory = TestUtil.getSqlSessionFactory();
		session = factory.openSession();

		// ==> Test 용 User instance 생성
		Food food = new Food(101, "hello", 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 120,
				"해태", "해태", "해태", "해태", "해태");

//		System.out.println(":: 1. addUser(INSERT)  ? " + session.insert("FoodMapper.addFood", food)); // 1
//		session.commit();

		System.out.println(":: 2. getAllFoods (SELECT)  ? ");
		TestUtil.printList((List)session.selectList("FoodMapper.search"));

		System.out.println(":: 3. searchFoodsByName(SELECT)  ? ");
		TestUtil.printList((List)session.selectList("FoodMapper.searchFoodsByName","신라면"));

		System.out.println(":: 4. searchFoodsByMaker(SELECT)  ? ");
		TestUtil.printList((List)session.selectList("FoodMapper.searchFoodsByMaker","농심"));

		System.out.println(":: 5. searchFoodsByMaterial(SELECT)  ? ");
		TestUtil.printList((List)session.selectList("FoodMapper.searchFoodsByMaterial", "소맥분"));

		System.out.println(":: 6. searchFoodsByCategory(SELECT)  ? ");
		TestUtil.printList((List)session.selectList("FoodMapper.searchFoodsByCategory", "식사"));

		System.out.println(":: 7. searchFoodByCode(SELECT)  ? ");
		System.out.println(" :: " + session.selectOne("FoodMapper.searchFoodByCode", 2));

		System.out.println(":: 8. searchFoodByName(SELECT)  ? ");
		System.out.println(" :: " + session.selectOne("FoodMapper.searchFoodByName", "신라면큰사발"));

	}

}
