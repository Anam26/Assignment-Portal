import java.io.IOException;
import java.io.PrintWriter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FacultyAccountServlet extends HttpServlet {

    private PreparedStatement ps;
    private Connection con;
    private PrintWriter out;

    @Override
    public void init() {
        try {
            //Process Request
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/projectdata", "root", "root");

            String sql = "Insert into faculty(userid,password,name,status) values(?,?,?,'disabled')";
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

        out = response.getWriter();

        //Step 1 : Accept the request
        String userid = request.getParameter("userid");
        String password = request.getParameter("password");
        String name = request.getParameter("name");
        
        try {

            ps.setString(1,userid);
            ps.setString(2, password);
            ps.setString(3, name);
            
            ps.executeUpdate();
            
            //Display Result
            out.println("<html>");
            out.println("<body>");
            out.println("<h3> Account-Created</h3>");
            out.println("<h4><a href=admindashboard.jsp>Admin-Dashboard</a></h4>");
            out.println("</body>");
            out.println("</html>");
        } catch (Exception ex) {
            out.println(ex);
        }

    }
}
