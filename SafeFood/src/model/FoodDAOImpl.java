package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.apache.ibatis.session.SqlSession;

import exception.DuplicateSSNException;
import util.DBUtil;
import util.FoodNutritionSAXHandler;
import util.FoodSAXHandler;
import model.Food;

public class FoodDAOImpl implements FoodDAO {
	private SqlSession sqlSession;
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
		System.out.println("::" + getClass().getName() + ".setSqlSession() Call...");
	}
	private String nutritionFilePath = "C:\\ssafy\\work_web\\ssafy_throughproject_191002\\WebContent\\res\\FoodNutritionInfo.xml";
	private String foodFilePath = "C:\\ssafy\\work_web\\ssafy_throughproject_191002\\WebContent\\res\\foodInfo.xml";
	private List<Food> list;
	private static FoodDAOImpl fdao = new FoodDAOImpl();
	private FoodDAOImpl() {
		loadData();
	}
	public static FoodDAOImpl getInstance() {
		return fdao;
	}
	
	public void loadData() {
		// FoodNutritionSaxPaser를 이용하여 Food 데이터들을 가져온다
		// 가져온 Food 리스트 데이터를 DB에 저장한다.
		SAXParserFactory factory = SAXParserFactory.newInstance();
		try {
			SAXParser parser = factory.newSAXParser();
			FoodSAXHandler handler = new FoodSAXHandler();
			FoodNutritionSAXHandler nHandler = new FoodNutritionSAXHandler();
			parser.parse(foodFilePath, handler);
			parser.parse(nutritionFilePath, nHandler);
			Map<String, Food> foodMap = handler.getFoods();
			list = nHandler.getList();
			Food find;
			for (Food food : list) {
				find = foodMap.get(food.getName());
				if (find != null) {
					food.setCode(find.getCode());
					food.setName(find.getName());
					food.setCategory(find.getCategory());
					food.setMaker(find.getMaker());
					food.setMaterial(find.getMaterial());
					food.setImg(find.getImg());
				}
			}
			// DB 연결 후 저장
			for (Food food : list) {
				addFood(food);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private boolean isExist(int code, Connection conn) throws SQLException {// 제품이 있는지 확인
		String query = "SELECT code FROM food WHERE code = ?";
		PreparedStatement ps = conn.prepareStatement(query);
		ps.setInt(1, code);
		ResultSet rs = ps.executeQuery();
		return rs.next();
	}

	public void addFood(Food food) throws SQLException, DuplicateSSNException {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = DBUtil.getConnection();
			System.out.println("Database Connected...");
			if (!isExist(food.getCode(), conn)) {
				String query = "INSERT INTO food VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
				ps = conn.prepareStatement(query);
				ps.setInt(1, food.getCode());
				ps.setString(2, food.getName());
				ps.setDouble(3, food.getSupportpereat());
				ps.setDouble(4, food.getCalory());
				ps.setDouble(5, food.getCarbo());
				ps.setDouble(6, food.getProtein());
				ps.setDouble(7, food.getFat());
				ps.setDouble(8, food.getSugar());
				ps.setDouble(9, food.getNatrium());
				ps.setDouble(10, food.getChole());
				ps.setDouble(11, food.getFattyacid());
				ps.setDouble(12, food.getTransfat());
				ps.setInt(13, food.getBgnYear());
				ps.setString(14, food.getMaker());
				ps.setString(15, food.getMaterial());
				ps.setString(16, food.getImg());
				ps.setString(17, food.getAllergy());
				System.out.println(food.getCategory());
				ps.setString(18, food.getCategory());
				
				System.out.println(ps.executeUpdate() + " row INSERT SUCCESS");
			}
		} finally {
			try {
				if (ps != null)
					ps.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public List<Food> search() throws Exception {
		return sqlSession.selectList("FoodMapper.search");
//		Connection conn = null;
//        PreparedStatement pstmt = null;
//        ResultSet rs = null;
//        List<Food> list = new ArrayList<Food>();        
//        String query = "SELECT * FROM food order by code";
//        try {
//        	conn = DBUtil.getConnection();
//        	pstmt = conn.prepareStatement(query);
//			rs = pstmt.executeQuery();
//			while(rs.next()) {
//				int code = rs.getInt("code");
//				String name = rs.getString("name");
//				String maker = rs.getString("maker");
//				String img = rs.getString("img");
//				String material = rs.getString("material");
//				double supportpereat = rs.getDouble("supportpereat");
//				double calory = rs.getDouble("calory");
//				double carbo = rs.getDouble("carbo");
//				double protein = rs.getDouble("protein");
//				double fat = rs.getDouble("fat");
//				double sugar = rs.getDouble("sugar");
//				double natrium = rs.getDouble("natrium");
//				double chole = rs.getDouble("chole");
//				double fattyacid = rs.getDouble("fattyacid");
//				double transfat = rs.getDouble("transfat");
//				int bgnYear = rs.getInt("bgnyear");
//				String allergy = rs.getString("allergy");
//				String category = rs.getString("category");
//				
//				list.add(new Food(code, name, supportpereat, calory,
//						carbo, protein, fat, sugar, natrium, chole, fattyacid, transfat, 
//						bgnYear, maker, material, img, allergy, category));
//			}
//        } finally {
//        	DBUtil.close(rs);
//            DBUtil.close(pstmt);
//            DBUtil.close(conn);
//        }	
//        return list;
	}
	
	@Override
	public List<Food> searchFoodsByCategory(String category) throws Exception {
		return sqlSession.selectList("FoodMapper.searchFoodsByCategory", category);
//
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//        ResultSet rs = null;
//        List<Food> list = new ArrayList<Food>();        
//        String query = "SELECT * FROM food WHERE category=?";
//        try {
//        	conn = DBUtil.getConnection();
//        	pstmt = conn.prepareStatement(query);
//        	pstmt.setString(1, category);
//			rs = pstmt.executeQuery();
//			while(rs.next()) {
//				int code = rs.getInt("code");
//				String name = rs.getString("name");
//				String maker = rs.getString("maker");
//				String img = rs.getString("img");
//				String material = rs.getString("material");
//				double supportpereat = rs.getDouble("supportpereat");
//				double calory = rs.getDouble("calory");
//				double carbo = rs.getDouble("carbo");
//				double protein = rs.getDouble("protein");
//				double fat = rs.getDouble("fat");
//				double sugar = rs.getDouble("sugar");
//				double natrium = rs.getDouble("natrium");
//				double chole = rs.getDouble("chole");
//				double fattyacid = rs.getDouble("fattyacid");
//				double transfat = rs.getDouble("transfat");
//				int bgnYear = rs.getInt("bgnyear");
//				String allergy = rs.getString("allergy");
//				
//				list.add(new Food(code, name, supportpereat, calory,
//						carbo, protein, fat, sugar, natrium, chole, fattyacid, transfat, 
//						bgnYear, maker, material, img, allergy, category));
//			}		
//        } finally {
//        	DBUtil.close(rs);
//            DBUtil.close(pstmt);
//            DBUtil.close(conn);
//        }	
//        return list;
	}

	@Override
	public List<Food> searchFoodsByName(String name) throws Exception {
		return sqlSession.selectList("FoodMapper.searchFoodsByName", name);
//		
//		Connection conn = null;
//        PreparedStatement pstmt = null;
//        ResultSet rs = null;
//        List<Food> list = new ArrayList<Food>();        
//        String query = "SELECT * FROM food WHERE name like '%"+name+"%";
//        try {
//        	conn = DBUtil.getConnection();
//        	pstmt = conn.prepareStatement(query);
//			rs = pstmt.executeQuery();
//			while(rs.next()) {
//				int code = rs.getInt("code");
//				String maker = rs.getString("maker");
//				String img = rs.getString("img");
//				String material = rs.getString("material");
//				double supportpereat = rs.getDouble("supportpereat");
//				double calory = rs.getDouble("calory");
//				double carbo = rs.getDouble("carbo");
//				double protein = rs.getDouble("protein");
//				double fat = rs.getDouble("fat");
//				double sugar = rs.getDouble("sugar");
//				double natrium = rs.getDouble("natrium");
//				double chole = rs.getDouble("chole");
//				double fattyacid = rs.getDouble("fattyacid");
//				double transfat = rs.getDouble("transfat");
//				int bgnYear = rs.getInt("bgnyear");
//				String allergy = rs.getString("allergy");
//				String category = rs.getString("category");
//				
//				list.add(new Food(code, name, supportpereat, calory,
//						carbo, protein, fat, sugar, natrium, chole, fattyacid, transfat, 
//						bgnYear, maker, material, img, allergy, category));
//			}		
//        } finally {
//        	DBUtil.close(rs);
//            DBUtil.close(pstmt);
//            DBUtil.close(conn);
//        }	
//        return list;
	}

	@Override
	public List<Food> searchFoodsByMaker(String maker) throws Exception {
		return sqlSession.selectList("FoodMapper.searchFoodsByMaker", maker);

//		Connection conn = null;
//        PreparedStatement pstmt = null;
//        ResultSet rs = null;
//        List<Food> list = new ArrayList<Food>();        
//        String query = "SELECT * FROM food WHERE maker like %"+maker+"%";
//        try {
//        	conn = DBUtil.getConnection();
//        	pstmt = conn.prepareStatement(query);
//			rs = pstmt.executeQuery();
//			while(rs.next()) {
//				int code = rs.getInt("code");
//				String name = rs.getString("name");
//				String img = rs.getString("img");
//				String material = rs.getString("material");
//				double supportpereat = rs.getDouble("supportpereat");
//				double calory = rs.getDouble("calory");
//				double carbo = rs.getDouble("carbo");
//				double protein = rs.getDouble("protein");
//				double fat = rs.getDouble("fat");
//				double sugar = rs.getDouble("sugar");
//				double natrium = rs.getDouble("natrium");
//				double chole = rs.getDouble("chole");
//				double fattyacid = rs.getDouble("fattyacid");
//				double transfat = rs.getDouble("transfat");
//				int bgnYear = rs.getInt("bgnyear");
//				String allergy = rs.getString("allergy");
//				String category = rs.getString("category");
//				
//				list.add(new Food(code, name, supportpereat, calory,
//						carbo, protein, fat, sugar, natrium, chole, fattyacid, transfat, 
//						bgnYear, maker, material, img, allergy, category));
//			}		
//        } finally {
//        	DBUtil.close(rs);
//            DBUtil.close(pstmt);
//            DBUtil.close(conn);
//        }	
//        return list;
	}

	@Override
	public List<Food> searchFoodsByMaterial(String material) throws Exception {
		Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Food> list = new ArrayList<Food>();     
        String query = "SELECT * FROM food WHERE material like %"+material+"%";
        try {
        	conn = DBUtil.getConnection();
        	pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				int code = rs.getInt("code");
				String name = rs.getString("name");
				String maker = rs.getString("maker");
				String img = rs.getString("img");
				double supportpereat = rs.getDouble("supportpereat");
				double calory = rs.getDouble("calory");
				double carbo = rs.getDouble("carbo");
				double protein = rs.getDouble("protein");
				double fat = rs.getDouble("fat");
				double sugar = rs.getDouble("sugar");
				double natrium = rs.getDouble("natrium");
				double chole = rs.getDouble("chole");
				double fattyacid = rs.getDouble("fattyacid");
				double transfat = rs.getDouble("transfat");
				int bgnYear = rs.getInt("bgnyear");
				String allergy = rs.getString("allergy");
				String category = rs.getString("category");
				
				list.add(new Food(code, name, supportpereat, calory,
						carbo, protein, fat, sugar, natrium, chole, fattyacid, transfat, 
						bgnYear, maker, material, img, allergy, category));
			}
        } finally {
        	DBUtil.close(rs);
            DBUtil.close(pstmt);
            DBUtil.close(conn);
        }	
        return list;
	}

	@Override
	public Food searchFoodByName(String name) throws Exception {
		Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String query = "SELECT * FROM food WHERE name=?";
        Food food = null;
        try {
        	conn = DBUtil.getConnection();
        	pstmt = conn.prepareStatement(query);
        	pstmt.setString(1, name);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				int code = rs.getInt("code");
				String maker = rs.getString("maker");
				String material = rs.getString("material");
				String img = rs.getString("img");
				double supportpereat = rs.getDouble("supportpereat");
				double calory = rs.getDouble("calory");
				double carbo = rs.getDouble("carbo");
				double protein = rs.getDouble("protein");
				double fat = rs.getDouble("fat");
				double sugar = rs.getDouble("sugar");
				double natrium = rs.getDouble("natrium");
				double chole = rs.getDouble("chole");
				double fattyacid = rs.getDouble("fattyacid");
				double transfat = rs.getDouble("transfat");
				int bgnYear = rs.getInt("bgnyear");
				String allergy = rs.getString("allergy");
				String category = rs.getString("category");
				food = new Food(code, name, supportpereat, calory,
						carbo, protein, fat, sugar, natrium, chole, fattyacid, transfat, 
						bgnYear, maker, material, img, allergy, category);
			}		
        } finally {
        	DBUtil.close(rs);
            DBUtil.close(pstmt);
            DBUtil.close(conn);
        }	
        return food;
	}
	@Override
	public Food searchFoodByCode(int code) throws Exception {
		Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String query = "SELECT * FROM food WHERE code=?";
        Food food = null;
        try {
        	conn = DBUtil.getConnection();
        	pstmt = conn.prepareStatement(query);
        	pstmt.setInt(1, code);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				String name = rs.getString("name");
				String maker = rs.getString("maker");
				String material = rs.getString("material");
				String img = rs.getString("img");
				double supportpereat = rs.getDouble("supportpereat");
				double calory = rs.getDouble("calory");
				double carbo = rs.getDouble("carbo");
				double protein = rs.getDouble("protein");
				double fat = rs.getDouble("fat");
				double sugar = rs.getDouble("sugar");
				double natrium = rs.getDouble("natrium");
				double chole = rs.getDouble("chole");
				double fattyacid = rs.getDouble("fattyacid");
				double transfat = rs.getDouble("transfat");
				int bgnYear = rs.getInt("bgnyear");
				String allergy = rs.getString("allergy");
				String category = rs.getString("category");
				
				list.add(new Food(code, name, supportpereat, calory,
						carbo, protein, fat, sugar, natrium, chole, fattyacid, transfat, 
						bgnYear, maker, material, img, allergy, category));
			}		
        } finally {
        	DBUtil.close(rs);
            DBUtil.close(pstmt);
            DBUtil.close(conn);
        }	
        return food;
	}
//	public static void main(String[] args) {
//		try {
//			Food food = FoodDAOImpl.getInstance().searchFoodByName("비비빅");
//				System.out.println(food);
//		} catch (Exception e) {
//			System.out.println(e);
//		}
//	}
}
