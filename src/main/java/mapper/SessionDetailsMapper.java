package mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;

import beans.SessionDetails;

public class SessionDetailsMapper implements RowMapper<SessionDetails> {
	
	static Logger log = Logger.getLogger(SessionDetailsMapper.class.getName());
	
   public SessionDetails mapRow(ResultSet rs, int rowNum) throws SQLException {
	   SessionDetails sessionDetails = new SessionDetails();
	   sessionDetails.setIdUser(rs.getInt("idUser"));
	   sessionDetails.setHttpSession(rs.getString("httpSession"));
	   
      return sessionDetails;
   }
}