import java.io.IOException;
import java.io.PrintWriter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegistrationServlet extends HttpServlet {

    private PreparedStatement ps;
    private Connection con;
    private PrintWriter out;

    @Override
    public void init() {
        try {
            //Process Request
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/projectdata", "root", "root");

            String sql = "Insert into Student values(?,?,?,?,?,?)";
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
        String Name = request.getParameter("Name");
        String Address = request.getParameter("Address");
        String email = request.getParameter("email");
        String mobile = request.getParameter("mobile");
        try {

            ps.setString(1, userid);
            ps.setString(2, password);
            ps.setString(3, Name);
            ps.setString(4, Address);
            ps.setString(5, email);
            ps.setString(6, mobile);
            ps.executeUpdate();

            //Display Result
            out.println("<html>");
            out.println("<body>");
            out.println("<h3> Registration Completed </h3>");
            out.println("<h4><a href=index.jsp>Home</a></h4>");
            out.println("</body>");
            out.println("</html>");
        } catch (Exception ex) {
            out.println(ex);
        }

    }
}
