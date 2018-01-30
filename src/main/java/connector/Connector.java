package connector;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import beans.User;
import database.ClientJDBCTemplate;

@Controller
public class Connector {
	
	List<User> list;
	ClientJDBCTemplate clientJDBCTemplate;
	Connector_methods client;
	static Logger log = Logger.getLogger(Connector.class.getName());
	
   @RequestMapping(value = "/login", method = RequestMethod.GET)
   public ModelAndView login(ModelMap model) {
      return new ModelAndView("login", "command", new User());
   }
   
   @RequestMapping(value = "/changing_password", method = RequestMethod.GET)
   public ModelAndView changing_password(ModelMap model) {
      return new ModelAndView("password_change", "command", new User());
   }
   
   @RequestMapping(value = "/room", method = RequestMethod.GET)
   public ModelAndView mainRoom(ModelMap model) {
      return new ModelAndView("room", "command", new User());
   }
   
   @RequestMapping(value = "/changePassword", method = RequestMethod.POST)
   public ModelAndView changePassword(@ModelAttribute("SpringWeb")User user, 
			ModelMap model) {
	   
	   this.client = new Connector_methods();
	   if (client.checkExistingUser(user.getMail()))	client.changePassword(user.getMail(), user.getPassword());
	   
	   log.info(user.getMail());
	   log.info(user.getPassword());
	   
      return new ModelAndView("room", "command", new User());
   }
   
   @RequestMapping(value = "/addUser", method = RequestMethod.POST)
   public ModelAndView addUser(@ModelAttribute("SpringWeb")User user, 
	ModelMap model) {
	   
	   this.client = new Connector_methods();
	   
	   log.info("in addUser method");
	   log.info(user.getName());
	   log.info(user.getSurname());
	   log.info(user.getMail());
	   log.info(user.getPassword());
	   log.info(user.getAge());
	   log.info(user.getId());
	   log.info(client.session().getId());
	   
	   int age = -1;
	   try{
		   age = Integer.parseInt(user.getAge());
	   } catch(Exception ex){
		   log.info(ex);
		   model.addAttribute("message", "You gave bad date in age field");
		   return new ModelAndView("login", "command", new User());
	   }
	   
	   model.addAttribute("name", user.getName());
	   model.addAttribute("surname", user.getSurname());
	   model.addAttribute("mail", user.getMail());
	   model.addAttribute("password", user.getPassword());
	   model.addAttribute("age", age);
	   model.addAttribute("id", user.getId());
	   
	   if (!client.checkExistingUser(user.getMail())) client.storeUser(user, client.session());

	   return new ModelAndView("result");
   }
   
   @RequestMapping(value = "/checkUser", method = RequestMethod.POST)
   public ModelAndView checkUser(@ModelAttribute("SpringWeb")User user, 
			ModelMap model){
	   
	   Connector_methods client = new Connector_methods();
	   if (client.checkPassword(user.getMail(), user.getPassword())){
		   client.changeHTTPSession(user.getMail());
		   
		   user = client.getUser(user.getMail());
		   
		   model.addAttribute("name", user.getName());
		   model.addAttribute("surname", user.getSurname());
		   model.addAttribute("mail", user.getMail());
		   model.addAttribute("password", user.getPassword());
		   model.addAttribute("age", (user.getAge().equals("-1")) ? user.getAge().toString() : null);
		   model.addAttribute("id", user.getId());
		   
		   return new ModelAndView("result");
	   }
	   else{
		   log.info("in else");
	      model.addAttribute("message", "Wprowadziles bledne haslo");
	      return new ModelAndView("login", "command", new User());
	   }
   }

   @RequestMapping(value = "/websocket", method = RequestMethod.GET)
   public String chat(ModelMap model) {
      return "websocket";
   }
   
   @RequestMapping(value = "/bmw", method = RequestMethod.GET)
   public String chatBMW(ModelMap model) {
      model.addAttribute("message", "First Maven");
      return "bmw";
   }
   @RequestMapping(value = "/audi", method = RequestMethod.GET)
   public String chatAudi(ModelMap model) {
      model.addAttribute("message", "First Maven");
      return "audi";
   }
   @RequestMapping(value = "/mercedes", method = RequestMethod.GET)
   public String chatMercedes(ModelMap model) {
      model.addAttribute("message", "First Maven");
      return "mercedes";
   }
   @RequestMapping(value = "/bmwelements", method = RequestMethod.GET)
   public String chatBMWElements(ModelMap model) {
      model.addAttribute("message", "First Maven");
      return "bmwelements";
   }
   @RequestMapping(value = "/audielements", method = RequestMethod.GET)
   public String chatAudiElements(ModelMap model) {
      model.addAttribute("message", "First Maven");
      return "audielements";
   }
   @RequestMapping(value = "/mercedeselements", method = RequestMethod.GET)
   public String chatMercedesElements(ModelMap model) {
      model.addAttribute("message", "First Maven");
      return "mercedeselements";
   }
   
   @RequestMapping(value = "/redirect", method = RequestMethod.GET)
   public String redirect(ModelMap model) {
	   ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");

	      clientJDBCTemplate = 
	         (ClientJDBCTemplate)context.getBean("clientJDBCTemplate");
	      
	      log.info("------Records Creation--------" );
	      clientJDBCTemplate.create("Zara", 11);
	      clientJDBCTemplate.create("Nuha", 2);
	      clientJDBCTemplate.create("Ayan", 15);

	      log.info("------Listing Multiple Records--------" );
	      List<User> users = clientJDBCTemplate.listUsers();
	      
	      for (User record : users) {
	         System.out.print("ID : " + record.getId() );
	         System.out.print(", Name : " + record.getName() );
	         log.info(", Age : " + record.getAge());
	      }

	      log.info("----Updating Record with ID = 2 -----" );
	      clientJDBCTemplate.update(2, 20);

	      log.info("----Listing Record with ID = 2 -----" );
	      User user = clientJDBCTemplate.getUser(2);
	      System.out.print("ID : " + user.getId() );
	      System.out.print(", Name : " + user.getName() );
	      log.info(", Age : " + user.getAge());
	   
      return "forward:finalPage";
   }
   
   @RequestMapping(value = "/finalPage", method = RequestMethod.GET)
   public String finalPage(ModelMap model) {
	   User user = clientJDBCTemplate.getUser(2);
	   model.addAttribute("ID", user.getId());
	   model.addAttribute("Name", user.getName());
	   model.addAttribute("Age", user.getAge());
      return "final";
   }
}
