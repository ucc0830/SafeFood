package test;

import model.Food;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

//SqlSessionFactory를 생성하는 놈..
public class TestUtil {
	public static void printList(List<Food> list) {
		for (int i = 0; i < list.size(); i++) {
			System.out.println("<" + (i + 1 + "> 번째 음식...." + list.get(i).toString()));
		}
		System.out.println("\n");
	}

	public static SqlSessionFactory getSqlSessionFactory() throws IOException {
		Reader reader = Resources.getResourceAsReader("sql/SqlMapConfig01.xml");
		SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(reader);
		return factory;
	}
}
