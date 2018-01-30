package beans;

public class SessionDetails {
	private Integer idUser;
	private String httpSession;
	
	 public void setIdUser(Integer idUser) {
		 this.idUser = idUser;
	 }
	 public Integer getIdUser() {
		 return idUser;
	 }
	 public void setHttpSession(String httpSession) {
		 this.httpSession = httpSession;
	 }
	 public String getHttpSession() {
		 return httpSession;
	 }
}
