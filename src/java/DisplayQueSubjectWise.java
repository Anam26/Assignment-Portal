
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;




public class DisplayQueSubjectWise extends HttpServlet {
    private PreparedStatement ps;
    private Connection con;
    
    @Override
    public void init() {
        try {

            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/projectdata","root", "root");
            String sql="SELECT * FROM quebank where subject=?";
            ps=con.prepareStatement(sql);
        } catch (Exception ex) {
             ex.printStackTrace();
        }

    }
    @Override
    public void destroy()
    {
        try {
            con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

   
    @Override
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException
   {
        PrintWriter out=response.getWriter();
        String subject=request.getParameter("sub");
        try
        {
            ps.setString(1,subject);
            ResultSet rs=ps.executeQuery();
            out.println("<html>");
            out.println("<body>");
            out.println("<h3>Question-List-For-"+subject+"</h3>");
            out.println("<hr>");
            out.println("<table border =2>");
            out.println("<tr>");
            out.println("<th>Queno.</th><th>Question</th><th>sdate</th><th>fid</th><th>Max_marks</th>");
            out.println("</tr>");
            while(rs.next())
            {
                String code=rs.getString("code");
                String question=rs.getString("question");
                String sdate=rs.getString("sdate");
                String fid=rs.getString("fid");
                String marks=rs.getString("Max_marks");
                out.println("<tr>");
                out.println("<td>"+code+"</td>");
                out.println("<td>"+question+"</td>");
                out.println("<td>"+sdate+"</td>");
                out.println("<td>"+fid+"</td>");
                 out.println("<td>"+marks+"</td>");
                out.println("</tr>");
                
            }
            out.println("</table>");
            out.println("<hr>");
            out.println("<a href=studentdashboard.jsp>StudentDashboard</a><br>");
            
            out.println("<a href=SubjectListServlet>Subject-List</a>");
            out.println("</body>");
            out.println("</html>");
        }
        catch(Exception e)
        {
            out.println(e);
        }
    }
    

}
