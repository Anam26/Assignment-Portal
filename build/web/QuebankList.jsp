<%@ page import="java.sql.* " %>

<%
   Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/projectdata","root", "root");
            
           Statement stmt=con.createStatement();
           ResultSet rs=stmt.executeQuery("Select * from quebank");
    %>
    <html>
    <body>
        <h3>Question-Bank</h3>
        <hr>
        <table border="2">
            <tr>
                <th> code</th>
            <th> question </th>
            <th> s_date </th>
            <th> fid</th>
            <th> subject</th>
            <th> max_marks</th>
            </tr>
            <%  while(rs.next()){
                String s1=rs.getString(1);
                String s2=rs.getString(2);
                String s3=rs.getString(3);
                String s4=rs.getString(4);
                String s5=rs.getString(5);
                String s6=rs.getString(6);
            
                
                %>
                <tr> 
                    <td><%=s1%></td>
                    <td><%=s2%></td>
                    <td><%=s3%></td>
                    <td><%=s4%></td>
                    <td><%=s5%></td>
                    <td><%=s6%></td>
                    
                </tr>
                <% 
                    }
                 %>
        </table>
        <hr>
        <a href="admindashboard.jsp">Admin-Dashboard</a>
    </body>
</html>
<% 
  con.close();
%>