package connector;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import beans.SessionDetails;
import beans.User;
import database.ClientJDBCTemplate;

public class Connector_methods extends Connector {

	ClientJDBCTemplate clientJDBCTemplate;
	private boolean userExists = false;
	ApplicationContext context;
	static Logger log = Logger.getLogger(Connector_methods.class.getName());
	
	public Connector_methods() {
		context = new ClassPathXmlApplicationContext("Beans.xml");
		clientJDBCTemplate = 
				(ClientJDBCTemplate)context.getBean("clientJDBCTemplate");
	}
	public boolean getUserExists(){
		return userExists;
	}
	public void setUserExists(boolean state){
		this.userExists = true;
	}
	
	public boolean checkExistingUser(String mail){
	    User client = clientJDBCTemplate.getUser(mail);
	    if ((client.getMail() != null) && (client.getName() != null)){
	    	setUserExists(true);
	    }
	    log.info("in checkExistingUser()");
	    log.info(userExists);
	    return userExists;
	}
	public boolean checkPassword(String mail, String password){
		User user = clientJDBCTemplate.getUser(mail);
//		clientJDBCTemplate.saveImage();
		clientJDBCTemplate.getImage();
		if (user.getMail() == null) return getUserExists();
	    if ((user.getMail().equals(mail)) && (user.getPassword().equals(password))){
	    	setUserExists(true);
	    }
	   
	    return userExists;
	}
	public boolean changePassword(String mail, String password){
		boolean result = clientJDBCTemplate.changePassword(mail, password);
	    return result;
	}
	public void changeHTTPSession(String mail){
		String sessionId = session().getId();
		User user = clientJDBCTemplate.getUser(mail);
		Integer userId = user.getId();
		
		log.info("in changeHTTPSession()");
		log.info(sessionId);
		
		clientJDBCTemplate.disconnectHttpSessionForAll(sessionId);
		clientJDBCTemplate.changeHTTPSession(sessionId, userId);
	   
	    return;
	}
	public String getUserName(HttpSession httpSession){
		SessionDetails sessiondetails = clientJDBCTemplate.getSessionDetails(httpSession.getId());
        User user = clientJDBCTemplate.getUser(sessiondetails.getIdUser());
        
        log.info("in getUserName()");
        System.out.format("idUser %s  httpsession %s \n", sessiondetails.getIdUser(), sessiondetails.getHttpSession());

        return user.getName();

	}
	public User getUser(String mail){
        User user = clientJDBCTemplate.getUser(mail);
        return user;

	}
	public boolean checkExistingSession(HttpSession httpSession){
		boolean result = false;
		SessionDetails sessiondetails = clientJDBCTemplate.getSessionDetails(httpSession.getId());
		if (sessiondetails != null) return true;
        return false;
	}

	public void storeUser(User user){
		
		Integer age = -1;
		try{
			   age = Integer.parseInt(user.getAge());
		} catch(java.lang.NumberFormatException ex){
			   log.info(ex);
		}
		
		clientJDBCTemplate.create(user.getName(), age, user.getSurname(),
				user.getMail(), user.getPassword());
		return;
	}
	public void storeUser(User user, HttpSession session){
		
		Integer age = -1;
		try{
			   age = Integer.parseInt(user.getAge());
		} catch(java.lang.NumberFormatException ex){
			   log.info(ex);
		}
		String idSession = session.getId();
		clientJDBCTemplate.disconnectHttpSessionForAll(idSession);
		
		clientJDBCTemplate.create(user.getName(), age, user.getSurname(),
				user.getMail(), user.getPassword(), idSession);
		return;
	}
	public static HttpSession session() {
	    ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
	    return attr.getRequest().getSession(true); // true == allow create
	}
	
}
