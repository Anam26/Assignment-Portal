<%  
   // session.setMaxInactiveInterval(8000);
    
    String userid=(String)session.getAttribute("uid");
    if (userid ==null)
    {
        response.sendRedirect("index.jsp");
    }
    int val=session.getMaxInactiveInterval();
%>
<html>
    
    <body style="font-size:160%">
        
        <h2>Faculty-Dashboard</h2>
        <h3> Welcome <%=userid  %> </h3>
    <h4>If you remain inactive for <%=val %> seconds, your session will expire!! </h4> 
        <hr>
        <pre>
         <a href="queupload.jsp ">Upload-Questions</a>
         <a href="EndSession">Home</a>

        
</pre>
        <hr>
    </body>
</html>
