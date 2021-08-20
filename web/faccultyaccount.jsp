
<html>
     <body background="imagef.jpg" style="background-repeat:no repeat;background-size:100%">
         <h3 align="center" style="color:black;font-size:60px;font-style:italic;font-family:Monotype Corsiva"><u>Faculty-Registration-Form</u></h3>
        
        <form action="FacultyAccountServlet" method="get">
            
            <table align="center">
            <tr style="font-size:160%" >
                <td style="color:black;">UserId</td> <td><input type="text" name="userid"/></td>
            </tr>
            <tr style="font-size:160%" >
                <td style="color:black;">Password</td><td><input type="Password" name="password"/></td>
            </tr>
            <tr style="font-size:160%" >
                <td style="color:black;">Name</td> <td><input type="text" name="name" style="font-size:60%"/></td>
            </tr>
            
            <tr style="font-size:160%" >
                <td style="color:black;"><input type="Submit" value="Create Account" style="font-size:80%"/></td>
                <td style="color:black;"><input type="Reset" value="Reset" style="font-size:80%"/></td>
            <tr style="font-size:160%" ><td style="color:black;"><a href="index.jsp" style="color:purple">Home</a></td></tr>
            </table>
            
        </form>
    </body>
</html>
