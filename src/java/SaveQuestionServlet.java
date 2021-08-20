import java.io.IOException;
import java.io.PrintWriter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SaveQuestionServlet extends HttpServlet {

    private PreparedStatement ps;
    private Connection con;
    private PrintWriter out;

    @Override
    public void init() {
        try {
            //Process Request
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/projectdata", "root", "root");

            String sql = "Insert into quebank(question,sdate,fid,subject,max_marks) values(?,?,?,?,?)";
            ps = con.prepareStatement(sql);
        } 
        catch (Exception e) {
           out.println(e);
        }
    }

    @Override
    public void destroy() {
        try {
            con.close();
        } catch (SQLException ex) {
            out.println(ex);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
         //Read fid from session
       //Step 1:fetch fid/userid from session
       HttpSession session=request.getSession();
       //Step 2: read data by calling getAttribute();
       String fid=(String)session.getAttribute("uid");
        out = response.getWriter();

        //Step 1 : Accept the request
        
        String question = request.getParameter("question");//1
        String subject = request.getParameter("subject");//4
        String max_marks=request.getParameter("max_marks");//5
        java.util.Date dt=new java.util.Date();
        long val=dt.getTime();
        java.sql.Date sqlDate=new java.sql.Date(val);
        
        try {

            ps.setString(1,question);
            ps.setDate(2, sqlDate);
            ps.setString(3, fid);
            ps.setString(4, subject);
            ps.setString(5, max_marks);
            
            ps.executeUpdate();
            
            //Display Result
            out.println("<html>");
            out.println("<body>");
            out.println("<h3> Question Stored Successfully</h3>");
            out.println("<h4><a href=queupload.jsp>Add-Another-Question</a></h4>");
            out.println("<h4><a href=index.jsp>Now-Login</a></h4>");
            out.println("<h4><a href=facultydashboard.jsp>Faculty-Dashboard</a></h4>");
            out.println("</body>");
            out.println("</html>");
        } catch (Exception ex) {
            out.println(ex);
        }

    }
}
