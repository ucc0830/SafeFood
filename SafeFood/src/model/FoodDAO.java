package model;

import java.util.List;

public interface FoodDAO {

	List<Food> search() throws Exception;

	List<Food> searchFoodsByName(String name) throws Exception;

	List<Food> searchFoodsByMaker(String maker) throws Exception;

	List<Food> searchFoodsByMaterial(String material) throws Exception;

	List<Food> searchFoodsByCategory(String category) throws Exception;

	Food searchFoodByCode(int code) throws Exception;

	Food searchFoodByName(String name) throws Exception;

}
