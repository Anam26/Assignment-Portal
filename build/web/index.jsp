<%
    //step 1 :fetch all the cookies comming with response and save it in cookie array
    Cookie ck[]=request.getCookies();
    //Step 2 : Allpy loop(for or foreach) and get the value of desired cookies 
    String v1="",v2="";
    if(ck!=null)
    {
    for(Cookie c:ck)
    { 
        String name=c.getName(); //getName() returns the name of the cookie
     if(name.equals("id"))
     {
       v1=c.getValue();
     } else if(name.equals("pw"))
     {
         v2=c.getValue();
     }
         
    }
    }
    %>










<html>
   
    <body background="image.jpeg" style="background-repeat:no repeat;background-size:100%" >
        
        
        <h1 align="center" style="color:black;font-size:60px;font-style:italic;font-family:Monotype Corsiva"><u>Assignment Portal</u></h1>
       
        <form action="AuthenticationServlet" method="get" style="font-size:0%">
            
          <table align="center">
            
               
                
              <tr style="font-size:160%" >
                  <td  style="color:black;" >UserId</td> <td><input type="text" name="userid"  value="<%=v1%>" style="font-size:60%"  /></td>
                </tr>
                <tr style="font-size:160%">
                    <td style="color:black;" >Password</td> <td><input type="Password" name="Password" value="<%=v2%>" style="font-size:60%"/></td>
                </tr>
                <tr style="font-size:160%">
                    <td style="color:black;" >UserType</td><td><select name="UserType" style="font-size:70%"><option >Admin</option><option>Faculty</option><option>Student</option></td>
                </tr>
                <tr style="font-size:160%"><td style="color:black;" >Save Password</td><td><input type="checkbox" name="save" value="yes"/></td></tr>
                
                <tr> </tr>
                <tr style="font-size:160%">  <td > <input type="Submit"  value="Login" style="font-size:80%"/></td>
                    <td > <input type="reset" value="Reset" style="font-size:80%"/></td></tr>

                <tr style="font-size:160%"> <td><a href="registration.jsp" style="color:lime">New User Registration</a></td>></tr>  
                <tr></tr>
                <tr></tr>
            </table>
        </form>
       
        
    </body>
</html>