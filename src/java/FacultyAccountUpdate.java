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

public class FacultyAccountUpdate extends HttpServlet {

    private PreparedStatement ps;
    private Connection con;
    private PrintWriter out;

    @Override
    public void init() {
        try {
            //Process Request
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/projectdata", "root", "root");

            String sql = "Update faculty SET password=?,name=?,email=?,mobile=?,post=?,branch=?,status='enabled' Where userid=?";
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
        //read userid using session
        HttpSession session=request.getSession();
        String userid=(String)session.getAttribute("uid");
        out = response.getWriter();

        //Step 1 : Accept the request
        
        String password = request.getParameter("password");
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String mobile = request.getParameter("mobile");
        String post = request.getParameter("post");
        String branch = request.getParameter("branch");
        
        try {

           ps.setString(1, password);
            ps.setString(2, name);
            ps.setString(3, email);
            ps.setString(4, mobile);
            ps.setString(5,post);
            ps.setString(6, branch);
            ps.setString(7,userid);
            
            ps.executeUpdate();
            
            //Display Result
            out.println("<html>");
            out.println("<body>");
            out.println("<hr>");
            out.println("<h3> Update Successfull</h3>");
            out.println("<h4><a href=index.jsp>Now-Login</a></h4>");
            out.println("<hr>");
            out.println("</body>");
            out.println("</html>");
        } catch (Exception ex) {
            out.println(ex);
        }

    }
}
