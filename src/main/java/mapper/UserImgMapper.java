package mapper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;

import beans.UserImg;

public class UserImgMapper implements RowMapper<UserImg> {
	
	static Logger log = Logger.getLogger(UserImgMapper.class.getName());
	
	public UserImg mapRow(ResultSet rs, int rowNum) throws SQLException {
		FileOutputStream fos = null;
		UserImg userImg = new UserImg();
		try {
			File file = new File("C:/Users/HP/Downloads/mazda.jpg");
			fos = new FileOutputStream(file);

			InputStream input = rs.getBinaryStream("img_data");
			byte[] buffer = new byte[1024];
			while (input.read(buffer) > 0) {
				fos.write(buffer);
			}

			// fos = null;
			// File image = new File("C:/Users/HP/Downloads/honda.jpg");
			// fos = new FileOutputStream(image);
			// byte[] buffer = new byte[1];
			// InputStream is = rs.getBinaryStream("img_data");
			// while (is.read(buffer) > 0) {
			// }
			// userImg.setImgTitle(rs.getString("img_title"));
			// userImg.setImgDataOut(fos);
			fos.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

		return userImg;
	}
}
