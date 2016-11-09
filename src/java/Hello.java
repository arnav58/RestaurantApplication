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
 * @author Mahe
 */
public class Hello extends HttpServlet {
    
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
        
        List<ItemsClass> items = new ArrayList<>();
        
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
            while(rs.next()){
                    ItemsClass newItem = new ItemsClass();
                    newItem.setItemName(rs.getString("itemname"));
                    newItem.setItemPrice(rs.getInt("itemprice"));
                    items.add(newItem);
            }
        
        }catch(ClassNotFoundException | SQLException e){
        }

        //Dummy Data
//        ItemsClass item1 = new ItemsClass();
//        item1.setItemName("Paneer Butter Masala");
//        item1.setItemPrice(120);
//        items.add(item1);
//        
//        ItemsClass item2 = new ItemsClass();
//        item2.setItemName("Dal Tadka");
//        item2.setItemPrice(80);
//        items.add(item2);
//        
//        ItemsClass item3 = new ItemsClass();
//        item3.setItemName("Mushroom Masala");
//        item3.setItemPrice(100);
//        items.add(item3);
//        
//        ItemsClass item4 = new ItemsClass();
//        item4.setItemName("Aloo Mutter");
//        item4.setItemPrice(90);
//        items.add(item4);
        
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
            out.println("<link href=\"assets/css/servletcss.css\" rel=\"stylesheet\" type=\"text/css\"/>");
            out.println("</head>");
            out.println("<body style=\"background-color: #FAFAFA; top: 0; font-family: 'Roboto', sans-serif; font-weight: 400\">");
            out.println("<section id=\"home\" class=\"tt-fullHeight\" data-stellar-vertical-offset=\"50\" data-stellar-background-ratio=\"0.2\">");
            out.println("<h1 class=\"tt-headline letters type\">");
            out.println("<div class=\"servintro\"><u>Menu</u></div>");
            out.println("</h1>");
            out.println("<section class=\"menu\">");
            out.println("<form method=\"POST\" action=\"OrderConfirmation\"");
            for(int i=0;i<items.size();i++){
                out.println("<div><p>" + items.get(i).getItemName() +" &nbsp;"+ items.get(i).getItemPrice() +
                    "<select id="+i+" name="+i+" style=\"position: absolute; left: 400px; color: #000000\">\n" +
                    "<option value=\"0\">0</option>\n" +
                    "<option value=\"1\">1</option>\n" +
                    "<option value=\"2\">2</option>\n" +
                    "<option value=\"3\">3</option>\n" +
                    "<option value=\"4\">4</option>\n" +
    "           </select></p></div>");
                out.println("<br>");
            }
            out.println("</section>");
            out.println("<input type=\"submit\" value=\"submit\"><button id=\"servmain\">Place Order</button></a>");
            out.println("</form>");
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
