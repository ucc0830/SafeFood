package model;

import java.sql.SQLException;

public interface MemberDAO {

	boolean loginCheck(String user, String pass) throws SQLException;

	void add(Member m) throws Exception;
	
	Member find(String user) throws SQLException;
	void update(Member m) throws SQLException;
	void delete(String id) throws SQLException;
}