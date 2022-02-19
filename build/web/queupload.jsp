<%
    String userid=(String)session.getAttribute("uid");
    if (userid ==null)
    {
        response.sendRedirect("index.jsp");
    }
%>
<html>

    <body background="assign.jpg" style="background-repeat:no repeat;background-size:100%">
        <h1 align="center" style=" color:black;font-size:260%"><u><b><i>Questions-Upload-Page</i></b></u> </h1>
        
        <form action="SaveQuestionServlet" method="get" style="color:black;font-size:250%;font-style:italic;font-family:Garamond;align-items:center">
            <pre>
            <b><i>Question</i></b> <textarea rows="5" cols="25" name="question" style="font-size:50%"></textarea> 
            <b><i>Subject</i></b>  <select name="subject" style="font-size:55%" >
                <option>java</option>
                <option>python</option>
                <option>Cyber Sec</option>
                <option>C language</option>
                <option>AI</option>
            </select>
          <b><i>Max_Marks</i></b>  <input type="text" name="max_marks" style="font-size:50%"/> 

            <input type="submit" value="SUBMIT" style="color:black;font-size:50%;font-style:bold"/> 
            <a  style="color:blue" href="index.jsp">Home</a>
            </pre>
        </form>
    </body>
</html>
