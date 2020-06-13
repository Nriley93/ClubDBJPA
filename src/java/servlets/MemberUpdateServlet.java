
package servlets;

import business.Member;
import business.MemberDB;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author n.riley
 */
public class MemberUpdateServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, 
            HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String URL="/MemberScreen.jsp";
        String msg="", sql, lmn, fmn, mnm;
        long newpwd;

        try{
            Member m = (Member) request.getSession().getAttribute("m");
            Member n = new Member();
            n.setMemid(m.getMemid());
            n.setStatus(m.getStatus());
            n.setMemdt(m.getMemdt());
            n.setPassattempt(m.getPassword());
            try{
                lmn = request.getParameter("lastname");
                if(!lmn.isEmpty()){n.setLastname(lmn);}
                else {msg+="Last name is missing.<br>";}
            } catch(Exception e) {
                msg += "Last Name Error: " + e.getMessage() + "<br>";
            }
            try{
                fmn = request.getParameter("firstname");
                if(!fmn.isEmpty()){n.setFirstname(fmn);}
                else {msg+="First name is missing.<br>";}
            } catch(Exception e) {
                msg += "First Name Error: " + e.getMessage() + "<br>";
            }
            try{
                mnm = request.getParameter("middlename");
                if(!mnm.isEmpty()){n.setMiddlename(mnm);}
                else {msg+="Middle name is missing.<br>";}
            } catch(Exception e) {
                msg += "Middle Name Error: " + e.getMessage() + "<br>";
            }
            try{
                newpwd = Long.parseLong(request.getParameter("psswd"));
                if(newpwd > 0) {
                    n.setPassword(newpwd);
                } else {
                    msg += "Illegal password: must be > 0.<br>";
                }
            } catch(NumberFormatException e) {
                msg += "Illegal password: not numeric.<br>";
            }
            
            if(msg.isEmpty()) {
                m = n;
                msg = MemberDB.updtMember(m);
                if(msg.startsWith("Error")) {
                    //update failed: re-acquire member
                    m = MemberDB.getMemberByID(m.getMemid());
                }
                request.getSession().setAttribute("m", m);
            }
        } catch(Exception e) {
            msg += "Servlet Error: " + e.getMessage() + "<br>";
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
