<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib uri = "http://www.springframework.org/tags/form" prefix = "form"%>
<!DOCTYPE html>
<html >
<head>
  
   <meta charset="UTF-8">
   <meta name="viewport" content="width=device-width, initial-scale=1" /> 
   <title>Sign-Up/Login</title>
   <link href='https://fonts.googleapis.com/css?family=Titillium+Web:400,300,600' rel='stylesheet' type='text/css'>
   <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/5.0.0/normalize.min.css">
  
    <spring:url value="/assets/css/styleLogin.css" var="mainCss" />
	<spring:url value="/assets/js/indexLogin.js" var="jqueryJs" />
	<spring:url value="/assets/scss/styleLogin.scss" var="mainJs" />
	
	<link href="${mainCss}" rel="stylesheet" />
    <link href="${mainJs}" rel="stylesheet" />
    
</head>

<body>
  <div class="form">
      
      <h4>${message}</h4>
      
      <ul class="tab-group">
        <li class="tab active"><a href="#signup">Sign Up</a></li>
        <li class="tab"><a href="#login">Log In</a></li>
      </ul>
      
      <div class="tab-content">
        <div id="signup">   
          <h1>dolaczanie do</h1>
          
          <form:form action="/com.society/addUser" method="post">
          
          <div class="top-row">
            <div class="field-wrap">
              <form:label path = "name">
                imie<span class="req">*</span>
              </form:label>
              <form:input path = "name" type="text"/>
            </div>
        
            <div class="field-wrap">
              <form:label path = "surname">
                nazwisko<span class="req">*</span>
              </form:label>
              <form:input path="surname" type="text"/>
            </div>
          </div>

		  <div class="field-wrap">
            <form:label path="age">
              wiek<span class="req">*</span>
            </form:label>
            <form:input path="age" type="text"/>
          </div>

          <div class="field-wrap">
            <form:label path="mail">
              mail adres<span class="req">*</span>
            </form:label>
            <form:input path="mail" type="email"/>
          </div>
          
          <div class="field-wrap">
            <form:label path="password">
              haslo<span class="req">*</span>
            </form:label>
            <form:input path="password" type="password"/>
          </div>
          
          <button type="submit" class="button button-block"/>rozpocznij</button>
          
          </form:form>

        </div>
        
        <div id="login">   
          <h1>Witam ponownie</h1>
          
          <form:form action="/com.society/checkUser" method="post">
          
            <div class="field-wrap">
            <form:label path="mail">
              mail sdres<span class="req">*</span>
            </form:label>
            <form:input path="mail" type="email"/>
          </div>
          
          <div class="field-wrap">
            <form:label path="password">
              haslo<span class="req">*</span>
            </form:label>
            <form:input path="password" type="password"/>
          </div>
          
          <p class="forgot"><a href="http://192.168.200.83:8080/com.society/changing_password">zapomnial haslo</a></p>
          
          <button class="button button-block"/>wejsc</button>
          
          </form:form>

        </div>
        
      </div><!-- tab-content -->
      
</div> <!-- /form -->
  <script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>

    <script type="text/javascript" src="${jqueryJs}"></script>

</body>
</html>
