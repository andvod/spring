package websockets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpSession;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;

import connector.Connector_methods;
import connector.GetHttpSessionConfigurator;
import database.ClientJDBCTemplate;

@ServerEndpoint(value = "/mercedes", 
					configurator = GetHttpSessionConfigurator.class)
public class WebSocketMercedes {
	
	static Logger log = Logger.getLogger(WebSocketMercedes.class.getName());
	
	private Session wsSession;
    private HttpSession httpSession;
    private String userName;
    ClientJDBCTemplate clientJDBCTemplate;
    ApplicationContext context;
    String closeMessage = "";
    private static ArrayList <Session> array = new ArrayList<Session>();
    
    private void sendMessageToAll(String message){
        for(Session s : array){
        	if (s.getId().equals(this.wsSession.getId())) continue;
            try {
                s.getBasicRemote().sendText(String.format("%s: %s \n", userName, message));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
    
	@OnMessage
	public void onMessage(String message, Session session) throws IOException,
			InterruptedException {
		System.out.printf("%s input: %s \n", userName, message);
		sendMessageToAll(message);
		
		wsSession.getBasicRemote().sendText(String.format("%s: %s \n", userName, message));
	}

	@OnOpen
    public void open(Session session, EndpointConfig config) throws IOException,
			InterruptedException {
		System.out.println("connected");
        this.wsSession = session;
        this.httpSession = (HttpSession) config.getUserProperties()
                                           .get(HttpSession.class.getName());
        
        this.array.add(session);
        
        Connector_methods connector_methods = new Connector_methods();
        if (!connector_methods.checkExistingSession(httpSession)){
            this.closeMessage = "sesja zostala zakonczona, zaloguj sie ponownie";
//          onError(new IOException(this.errorMessage));
	        onClose();
        }
        this.userName = connector_methods.getUserName(this.httpSession);
        wsSession.getBasicRemote().sendText(String.format("%s: %s \n", userName, "dolaczyl do czatu"));
        
        return;
    }

	@OnClose
	public void onClose() {
		System.out.println("Polaczenie zamkniete");
		wsSession.getAsyncRemote().sendText(this.closeMessage);
		array.remove(wsSession);
	}
	
	@OnError
	public void onError(Throwable e) throws IOException {
		array.remove(wsSession);
		System.out.println( "onError(): " + e.getMessage() );

		System.out.println(e.getStackTrace());
//	    wsSession.getAsyncRemote().sendText(error);
	}
}
