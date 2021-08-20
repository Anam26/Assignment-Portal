
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

public class SubjectListServlet extends HttpServlet {

    private PreparedStatement ps;
    private Connection con;
    
    

    @Override
    public void init() {
        try {

            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/projectdata","root", "root");
            String sql="SELECT distinct subject FROM quebank order by subject";
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

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //fetch all the subjects from quebank table and will show in the form of html page( hyperlinks)
    PrintWriter out=response.getWriter();
      
     out.println("<html>");
     out.println("<body style=\"font-size:160%\">");
    
     out.println("<h3>Subject-List</h3>");
     out.println("<hr>");
     
    
     
     
     try{
         
          ResultSet rs= ps.executeQuery();
          while(rs.next())
          {
              String s=rs.getString(1);
              out.println("<a href=\"DisplayQueSubjectWise?sub="+s+"\">");
              out.println(s);
              out.println("</a>");
              out.println("<br>");
           
          }
      }
      catch(Exception e){
          out.println(e);
      }
     
     
     out.println("<br> <a href=studentdashboard.jsp>Go-Back</a>");
     out.println("<hr>");
     
     out.println("</body>");
     out.println("</html>");
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
