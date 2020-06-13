
package servlets;

import business.Member;
import business.Purchase;
import business.PurchaseDB;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author n.riley
 */

public class ShowPurchasesServlet extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, 
            HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String URL = "/MemberScreen.jsp";
        String msg, mo, dy, yr;
        double baldue = 0;
        List<Purchase> pur;
        Date pd = null;
        SimpleDateFormat sdf = 
                new SimpleDateFormat("MM-dd-yyyy");
        
        try{
            Member m = 
                    (Member) request.getSession().getAttribute("m");
            
            mo = request.getParameter("month");
            dy = request.getParameter("day");
            yr = request.getParameter("year");
            if(!mo.isEmpty() && !dy.isEmpty() && !yr.isEmpty()) {
                try {
                    pd = 
                        sdf.parse(mo + "-" + dy + "-" + yr);
                } catch (Exception e) {
                    pd = null;
                }
            }
            if(pd==null) {
                pur = PurchaseDB.getPurchases(m.getMemid());
            } else {
                pur = PurchaseDB.getPurchases(m.getMemid(),pd);
            }        
            if(pur == null) {
                msg = "Purchases list returned empty.<br>";
            } else {
                msg = "Purchase entries = " + pur.size();
                URL = "/Purchases.jsp";
                request.setAttribute("pur",pur);
                //develop total for purchase...(from pur)
                for(int i = 0; i < pur.size(); i++){
                    Purchase p = pur.get(i);
                    if(p.getTranscd().equalsIgnoreCase("00")) {
                        baldue -= p.getAmount();
                    } else {
                        baldue += p.getAmount();
                    }
                }
                request.setAttribute("baldue", baldue);
            }
        } catch(Exception e) {
            msg = "Servlet Error: " + e.getMessage();
        }
        request.setAttribute("msg", msg);
        RequestDispatcher disp = 
                getServletContext().getRequestDispatcher(URL);
        disp.forward(request, response);
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
