package database;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import beans.SessionDetails;
import beans.User;
import beans.UserImg;
import mapper.SessionDetailsMapper;
import mapper.UserImgMapper;
import mapper.UserMapper;

public class ClientJDBCTemplate implements ClientDAO {
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplateObject;
	static Logger log = Logger.getLogger(ClientJDBCTemplate.class.getName());

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemplateObject = new JdbcTemplate(dataSource);
	}

	public void create(String name, Integer age) {
		String SQL = "insert into User (name, age) values (?, ?)";
		jdbcTemplateObject.update(SQL, name, age);
		System.out.println("Created Record Name = " + name + " Age = " + age);
		return;
	}

	public void create(String name, Integer age, String surname, String mail, String password) {
		String SQL = "insert into User (name, age, surname, mail, password) " + "values (?, ?, ?, ?, ?)";
		jdbcTemplateObject.update(SQL, name, age, surname, mail, password);
		System.out.println("Created Record Name = " + name + " Age = " + age + "Surname = " + surname + "Mail = " + mail
				+ "Password = " + password);
		return;
	}

	public void create(String name, Integer age, String surname, String mail, String password, String session) {
		String SQL = "insert into User (name, age, surname, mail, password) " + "values (?, ?, ?, ?, ?)";
		jdbcTemplateObject.update(SQL, name, age, surname, mail, password);

		SQL = "insert into sessionDetails(idUser, httpsession, active) " + "values ((select max(id) from user), ?, ?)";
		jdbcTemplateObject.update(SQL, session, "T");
		System.out.println("Created Record Name = " + name + " Age = " + age + "Surname = " + surname + "Mail = " + mail
				+ "Password = " + password + "Session = " + session);
		return;
	}

	public boolean changePassword(String mail, String password) {
		String SQL = "update user set password=? where mail=?";
		jdbcTemplateObject.update(SQL, password, mail);
		return true;
	}

	public void changeHTTPSession(String sessionId, Integer idUser) {
		String SQL = "update sessiondetails set httpsession=?, active=? where idUser=?";
		jdbcTemplateObject.update(SQL, sessionId, "T", idUser);
		return;
	}

	public User getUser(Integer id) {
		String SQL = "select * from User where id = ?";
		try {
			User user = jdbcTemplateObject.queryForObject(SQL, new Object[] { id }, new UserMapper());
			return user;
		} catch (EmptyResultDataAccessException ex) {
			System.err.println("this is error " + ex.toString());
			return new User();
		}
	}

	public User getUser(String mail) {
		String SQL = "select * from User where mail = ?";
		try {
			User user = jdbcTemplateObject.queryForObject(SQL, new Object[] { mail }, new UserMapper());

			return user;
		} catch (EmptyResultDataAccessException ex) {
			System.err.println("there is no user with mail:   ");
			return new User();
		}
	}

	public void saveImage() {
	   FileInputStream inputStream = null;
	   try{
		   File image = new File("C:/honda.jpg");
	       inputStream = new FileInputStream(image);
	       
	       String SQL = "insert into user_img (img_title, img_data) " + "values (?, ?)";
			jdbcTemplateObject.update(SQL, "Honda Car", inputStream);
	       
		} catch (FileNotFoundException e) {
			System.out.println("FileNotFoundException: - " + e);
		}
	}
	public UserImg getImage() {
		   FileInputStream inputStream = null;
		   try{
			   File f = new File("C:/Users/HP/Downloads/mazda.jpg");
			   if (!f.exists())	f.createNewFile();
		       inputStream = new FileInputStream(f);
		       
		       String imgTitle = new String("Honda Car");
		       
		       String SQL = "select * from User_Img where img_title = ?";
				try {
					UserImg userImg = jdbcTemplateObject.queryForObject(SQL, new Object[] { imgTitle },
							new UserImgMapper());
					
//					f = new File("D:\\Image\\Output.jpg");  //output file path
//				      ImageIO.write(image, "jpg", f);
//				      System.out.println("Writing complete.");
				      
					return userImg;
				} catch (EmptyResultDataAccessException ex) {
					System.err.println("there is no user with idUser:   ");
					return new UserImg();
				}
			} catch (IOException e) {
				System.out.println("FileNotFoundException: - " + e);
				return new UserImg();
			}
		}

	public SessionDetails getSessionDetails(String httpSession) {
		String SQL = "select * from SessionDetails where httpSession = ? and active = \"T\";";
		try {
			SessionDetails sessionDetails = jdbcTemplateObject.queryForObject(SQL, new Object[] { httpSession },
					new SessionDetailsMapper());

			return sessionDetails;
		} catch (EmptyResultDataAccessException ex) {
			System.err.println("there is no user with httpSession:   ");
			return new SessionDetails();
		} catch (org.springframework.dao.IncorrectResultSizeDataAccessException ex) {
			System.err.println("there are more than one user with active httpSession:   ");
			disconnectHttpSessionForAll(httpSession);
			return new SessionDetails();
		}

	}

	public SessionDetails getSessionDetails(Integer idUser) {
		String SQL = "select * from SessionDetails where idUser = ?";
		try {
			SessionDetails sessionDetails = jdbcTemplateObject.queryForObject(SQL, new Object[] { idUser },
					new SessionDetailsMapper());

			return sessionDetails;
		} catch (EmptyResultDataAccessException ex) {
			System.err.println("there is no user with idUser:   ");
			return new SessionDetails();
		}

	}

	public void disconnectHttpSessionForAll(String httpSession) {
		String SQL = "update SessionDetails set active = \"F\" where httpSession = ?";
		jdbcTemplateObject.update(SQL, httpSession);

		System.out.println("in disconnectHttpSessionForAll()");
		System.out.println("Updated Record with httpSession = " + httpSession);
		return;
	}

	public List<User> listUsers() {
		String SQL = "select * from User";
		List<User> users = jdbcTemplateObject.query(SQL, new UserMapper());
		return users;
	}

	public void delete(Integer id) {
		String SQL = "delete from User where id = ?";
		jdbcTemplateObject.update(SQL, id);
		System.out.println("Deleted Record with ID = " + id);
		return;
	}

	public void update(Integer id, Integer age) {
		String SQL = "update User set age = ? where id = ?";
		jdbcTemplateObject.update(SQL, age, id);
		System.out.println("Updated Record with ID = " + id);
		return;
	}
}