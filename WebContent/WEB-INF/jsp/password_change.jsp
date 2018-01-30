<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib uri = "http://www.springframework.org/tags/form" prefix = "form"%>
<!DOCTYPE html>
<html >
<head>
  
   <meta charset="UTF-8">
   <meta name="viewport" content="width=device-width, initial-scale=1" /> 
   <title>Sign-Up/Login Form</title>
   <link href='https://fonts.googleapis.com/css?family=Titillium+Web:400,300,600' rel='stylesheet' type='text/css'>
   <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/5.0.0/normalize.min.css">
  
    <spring:url value="/assets/css/styleLogin.css" var="mainCss" />
	<spring:url value="/assets/js/indexLogin.js" var="jqueryJs" />
	<spring:url value="/assets/scss/styleLogin.scss" var="mainJs" />
	
	<link href="${mainCss}" rel="stylesheet" />
    <link href="${mainJs}" rel="stylesheet" />
    
    <style>
    	/* Add a green text color and a checkmark when the requirements are right */
		.valid {
		    color: green;
		}
		
		.valid:before {
		    position: relative;
		    left: -35px;
		    content: "y";
		}
		
		/* Add a red text color and an "x" when the requirements are wrong */
		.invalid {
		    color: red;
		}
		
		.invalid:before {
		    position: relative;
		    left: -35px;
		    content: "x";
		}
    </style>
    
</head>

<body>
  <div class="form">
      
      <h4>${message}</h4>
      
      <h1>Changing password</h1>
      
      <div style="display:block">
        <div id="signup">   
          
          <form:form action="/com.society/changePassword" onsubmit="return theSame()" method="post">
          
		  <div class="top-row">
		  
		  <!--
          <div class="field-wrap" style="margin-right:0">
            <form:label path="mail">
              Email Address<span class="req">*</span>
            </form:label>
          </div>
          <div class="field-wrap" style="float:right; margin-right:0; width:40%">
            <form:label path="mail">
            	Email Ads<span class="req"></span>
            </form:label>
          </div>
          -->
          
          <div class="field-wrap">
            <form:label path="mail">
              Email Address<span class="req">*</span>
            </form:label>
            <form:input path="mail" type="email"/>
          </div>
          
          <div class="field-wrap">
            <form:label path="password">
              Set A Password<span class="req">*</span>
            </form:label>
            <form:input path="password" id="psw" type="password" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}"
            title="Must contain at least one number and one uppercase and lowercase letter, and at least 8 or more characters"/>
          </div>
          
          <div class="field-wrap">
            <form:label path="password">
              Repeat A Password<span class="req">*</span>
            </form:label>
            <form:input id="psw2" path="password" type="password"/>
          </div>
          
          <button type="submit" class="button button-block"/>Change Password</button>
          
          </form:form>
          
          <div id="message" style="float:left; display:none; margin-left:30px">
			  <h3>Password must contain the following:</h3>
			  <p id="letter" class="invalid">A <b>lowercase</b> letter</p>
			  <p id="capital" class="invalid">A <b>capital (uppercase)</b> letter</p>
			  <p id="number" class="invalid">A <b>number</b></p>
			  <p id="length" class="invalid">Minimum <b>8 characters</b></p>
			  
			</div>
          
          </div>
			<p id="thesame" class="invalid" style="display: none; margin-left:30px">Must be<b>the same</b></p>
        </div>
        
      </div><!-- tab-content -->
      
</div> <!-- /form -->
  <script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>

    <script type="text/javascript" src="${jqueryJs}"></script>
    
    <script>
		var myInput = document.getElementById("psw");
		var myInput2 = document.getElementById("psw2");
		var letter = document.getElementById("letter");
		var capital = document.getElementById("capital");
		var number = document.getElementById("number");
		var length = document.getElementById("length");
		var thesame = document.getElementById("thesame");
		
		// When the user clicks on the password field, show the message box
		myInput.onfocus = function() {
		    document.getElementById("message").style.display = "block";
		    document.getElementById("thesame").style.display = "block";
		}
		
		// When the user clicks outside of the password field, hide the message box
		myInput.onblur = function() {
		    document.getElementById("message").style.display = "none";
		    document.getElementById("thesame").style.display = "none";
		}
		
		// When the user starts to type something inside the password field
		myInput.onkeyup = function() {
		  // Validate lowercase letters
		  var lowerCaseLetters = /[a-z]/g;
		  if(myInput.value.match(lowerCaseLetters)) {  
		    letter.classList.remove("invalid");
		    letter.classList.add("valid");
		  } else {
		    letter.classList.remove("valid");
		    letter.classList.add("invalid");
		  }
		  
		  // Validate capital letters
		  var upperCaseLetters = /[A-Z]/g;
		  if(myInput.value.match(upperCaseLetters)) {  
		    capital.classList.remove("invalid");
		    capital.classList.add("valid");
		  } else {
		    capital.classList.remove("valid");
		    capital.classList.add("invalid");
		  }
		
		  // Validate numbers
		  var numbers = /[0-9]/g;
		  if(myInput.value.match(numbers)) {  
		    number.classList.remove("invalid");
		    number.classList.add("valid");
		  } else {
		    number.classList.remove("valid");
		    number.classList.add("invalid");
		  }
		  
		  // Validate length
		  if(myInput.value.length >= 8) {
		    length.classList.remove("invalid");
		    length.classList.add("valid");
		  } else {
		    length.classList.remove("valid");
		    length.classList.add("invalid");
		  }
		  
		  if(myInput.value == myInput2.value) {
		    thesame.classList.remove("invalid");
		    thesame.classList.add("valid");
		  } else {
		    thesame.classList.remove("valid");
		    thesame.classList.add("invalid");
		  }
		  
		}
		function theSame() {
		    var psw = document.getElementById("psw").value;
		    var psw2 = document.getElementById("psw2").value;
		    var ok = true;
		    if (psw != psw2) {
		        //alert("Passwords Do not match");
		        document.getElementById("psw").style.borderColor = "#E34234";
		        document.getElementById("psw2").style.borderColor = "#E34234";
		        ok = false;
		        document.getElementById("thesame").style.display = "inline";
		    }
		    return ok;
		}
	</script>

</body>
</html>
