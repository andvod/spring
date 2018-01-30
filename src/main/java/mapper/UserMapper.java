package mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;

import beans.User;

public class UserMapper implements RowMapper<User> {
	
	static Logger log = Logger.getLogger(UserMapper.class.getName());
	
   public User mapRow(ResultSet rs, int rowNum) throws SQLException {
	   User user = new User();
	   user.setId(rs.getInt("id"));
	   user.setName(rs.getString("name"));
	   user.setAge(rs.getString("age"));
	   user.setSurname(rs.getString("surname"));
	   user.setMail(rs.getString("mail"));
	   user.setPassword(rs.getString("password"));
	   
      return user;
   }
}