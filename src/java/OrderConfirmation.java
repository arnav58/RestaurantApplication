/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.sql.Statement;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author student
 */
public class OrderConfirmation extends HttpServlet {
    
    static Scanner s = new Scanner(System.in);

    static final String DB_URL = "jdbc:mysql://localhost/arnavdb";
    static final String USER = "root";
    static final String PASS = "student";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String m=request.getParameter("1");
        
        List<OrderSummary> orderSummary = new ArrayList<>();
        
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        
        try{
        
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT * FROM menudb";
            rs = stmt.executeQuery(sql);
            int n = 0;
            while(rs.next()){
                    OrderSummary newItem = new OrderSummary();
                    newItem.setItemName(rs.getString("itemname"));
                    newItem.setQuantity(request.getParameter(""+n));
                    n++;
                    if(!"0".equals(newItem.getQuantity())){
                        orderSummary.add(newItem);
                    }
            }
            
            String deleteSql;
            deleteSql = "DELETE FROM orderdb";
            stmt.executeUpdate(deleteSql);
            
            String insertSql;
            for(int j=0;j<orderSummary.size();j++){
                insertSql = "INSERT INTO orderdb VALUES(\""+orderSummary.get(j).getItemName()+"\",\""+orderSummary.get(j).getQuantity()+"\")";
                stmt.executeUpdate(insertSql);
            }
        }catch(ClassNotFoundException | SQLException e){
        }
        
        
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Menu</title>"); 
            out.println("<link href='https://fonts.googleapis.com/css?family=Roboto' rel='stylesheet' type='text/css'>");
            out.println("<link href=\"assets/css/magnific-popup.css\" rel=\"stylesheet\" type=\"text/css\"/>");
            out.println("<link href=\"assets/css/responsive.css\" rel=\"stylesheet\" type=\"text/css\"/>");
            out.println("<link href=\"assets/css/style.css\" rel=\"stylesheet\" type=\"text/css\"/>");
            out.println("<link href=\"assets/bootstrap/css/bootstrap.min.css\" rel=\"stylesheet\" type=\"text/css\"/>");
            out.println("</head>");
            out.println("<body style=\"background-color: #FAFAFA; font-family: 'Roboto', sans-serif; font-weight: 400\">");
            out.println("<section id=\"home\" class=\"tt-fullHeight\" data-stellar-vertical-offset=\"50\" data-stellar-background-ratio=\"0.2\">");
            out.println("<div class=\"intro\">");
            out.println("<h1 class=\"tt-headline letters type\">");
            out.println("<div class=\"intro-sub\">Order Placed Successfully!</div>");
            out.println("</h1>");
            out.println("<br>");
            out.println("<div style=\"color: #FFFFFF\"><p><h5>Order Summary:</h5>");
            String toAdd="";
            for(int i=0;i<orderSummary.size();i++){
                toAdd += orderSummary.get(i).getItemName()+" "+orderSummary.get(i).getQuantity()+"<br>";
                
            }
            out.println("<br>"+toAdd+"</p></div>");
            out.println("</div>");
            out.println("</section>");
            out.println("<script src=\"assets/bootstrap/js/bootstrap.min.js\" type=\"text/javascript\"></script>");
            out.println("<script src=\"assets/js/jquery.inview.min.js\" type=\"text/javascript\"></script>");
            out.println("<script src=\"assets/js/jquery.js\" type=\"text/javascript\"></script>");
            out.println("<script src=\"assets/js/jquery.magnific-popup.min.js\" type=\"text/javascript\"></script>");
            out.println("<script src=\"assets/js/jquery.shuffle.min.js\" type=\"text/javascript\"></script>");
            out.println("<script src=\"assets/js/jquery.stellar.min.js\" type=\"text/javascript\"></script>");
            out.println("<script src=\"assets/js/jquery.sticky.js\" type=\"text/javascript\"></script>");
            out.println("<script src=\"assets/js/scripts.js\" type=\"text/javascript\"></script>");
            out.println("<script src=\"assets/js/smoothscroll.js\" type=\"text/javascript\"></script>");
            out.println("<script src=\"assets/js/wow.min.js\" type=\"text/javascript\"></script>");
//            out.println("");
            out.println("</body>");
            out.println("</html>");
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
