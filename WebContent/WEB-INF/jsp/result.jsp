<%@page contentType = "text/html;charset = UTF-8" language = "java" %>
<%@page isELIgnored = "false" %>
<%@taglib uri = "http://www.springframework.org/tags/form" prefix = "form"%>
<html>
   <head>
      <title>Spring MVC Form Handling</title>
   </head>

   <body>
      <h2>Submitted User Information</h2>
      <table>
         <tr>
            <td>Name</td>
            <td>${name}</td>
         </tr>
         <tr>
            <td>Surname</td>
            <td>${surname}</td>
         </tr>
         <tr>
            <td>Age</td>
            <td>${age}</td>
         </tr>
         <tr>
            <td>Mail</td>
            <td>${mail}</td>
         </tr>
         <tr>
            <td>Password</td>
            <td>${password}</td>
         </tr>
         <tr>
            <td>ID</td>
            <td>${id}</td>
         </tr>
         
         <form:form method = "GET" action = "/com.society/room">
         <table>
            <tr>
               <td>
                  <input type = "submit" value = "Redirect Room"/>
               </td>
            </tr>
         </table>  
      </form:form>
      
      </table>  
      
   </body>
   
</html>