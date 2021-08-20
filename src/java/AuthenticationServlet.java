

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletConfig;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class AuthenticationServlet extends HttpServlet {
private PrintWriter out;
private Connection con;
private PreparedStatement ps,ps1;

@Override
public void init()
{
    try {
        Class.forName("com.mysql.jdbc.Driver");
         con=DriverManager.getConnection("jdbc:mysql://localhost:3306/projectdata","root","root");
        String sql="Select * from student where userid=? and password=?";
        ps=con.prepareStatement(sql);
       ps1=con.prepareStatement("Select * from faculty where userid=? and password=?");
    } catch (Exception ex) {
        out.println(ex);
    }
    
}

public void destroy()
{
    try {
        con.close();
    } catch (SQLException ex) {
        out.println(ex);
    }
}
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       try{
        out=response.getWriter();
        String userid=request.getParameter("userid");
        String password=request.getParameter("Password");
        String UserType=request.getParameter("UserType");
        String save=request.getParameter("save");
        
        
        
        if(UserType.equals("Admin"))
        {  
        ServletConfig config=getServletConfig();
        String validId=config.getInitParameter("admin-id");
        String validPass=config.getInitParameter("admin-password");
           
        if(userid.equals(validId) && password.equals(validPass))
            {                  
                if(save!=null)   //if user wish to save password,we will write id/password with cookies
                {
                    Cookie c1=new Cookie("id",validId);
                    Cookie c2=new Cookie("pw",validPass);
                    c1.setMaxAge(60*60*24*30);
                    c2.setMaxAge(60*60*24*30);
                    response.addCookie(c1);
                    response.addCookie(c2);
                   
                }
                    
                //Redirect request to adminDashboard
                response.sendRedirect("admindashboard.jsp");
            } 
            else
            {
                out.println("Invalid Credentials");
            }
            
        }
        else if(UserType.equals("Faculty"))
        {
            ps1.setString(1, userid);
            ps1.setString(2, password);
            ps1.executeQuery();
            ResultSet rs=ps1.executeQuery();
            boolean found=rs.next(); // if value is true then resultset contain 1 value thus correct credentials
            if(found)
            {
                //storing the userif in session (so that other servlets can access it 
                //Step1: Fetch the session (create session object)
                HttpSession session=request.getSession(); 
                
                //Step2: write data to session object
                session.setAttribute("uid",userid); //writing userid in uid variable
                
                        
                String status=rs.getString("status");
                if(status.equals("disabled"))
                {
                    response.sendRedirect("facultyprofileupdate.jsp");
                }else
                response.sendRedirect("facultydashboard.jsp");
                
            }
            else
                out.println("Credentials are wrong...Invalid Faculty Account");
        }
        else if(UserType.equals("Student"))
        {
            ps.setString(1, userid);
            ps.setString(2, password);
            ps.executeQuery();
            ResultSet rs=ps.executeQuery();
            boolean found=rs.next(); // if value is true then resultset contain 1 value thus correct credentials
            if(found)
            {
                response.sendRedirect("studentdashboard.jsp");
                
            }
            else
                out.println("Credentials are wrong...");
            
                    
        }
    }
       
       catch(Exception e)
       {
           out.println(e);
       }
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
