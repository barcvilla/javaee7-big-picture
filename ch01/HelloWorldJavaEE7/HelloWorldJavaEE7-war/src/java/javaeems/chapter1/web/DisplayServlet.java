/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaeems.chapter1.web;

import java.io.IOException;
import java.io.PrintWriter;
import javaeems.chapter1.model.MessageException;
import javaeems.chapter1.model.ModelEJB;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * DisplayServlet es el responsable de generar el contenido HTML que vemos cuando accedemos a la aplicacion y
 * contiene el ultimo mensaje que fue enviado. 
 * @author barcvilla
 */
@WebServlet(name="DisplayServlet", urlPatterns={"/DisplayServlet"})
public class DisplayServlet extends HttpServlet 
{
    @EJB
    private  ModelEJB helloEJB;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        try
        {
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Hello Java EE </title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<br>");
            out.println("<div align='center'>");
            out.println("<h2>Hello Java EE</h2>");
            out.println("Enter a message for Java EE which will pass through the web tier, the EJB tier to the "
            + "database, and back again!");
            out.println("<br><br><br>");
            out.println("<form action='./WriteServlet' method='POST'>");
            out.println("<input type='submit' value='Enter'>");
            out.println("<input text='text' name='put_message'>");
            out.println("<br>");
            String displayMessage;
            try
            {
                String storedMessage = helloEJB.getStoredMessage();
                displayMessage = "Hello from (" + storedMessage + "), inside a"
                        + " web component";
            }
            catch(MessageException nme)
            {
                displayMessage="you should enter a value...";
            }
            out.println("The current message from Java EE: <br><br>" + displayMessage + "</br>");
            out.println("</div>");
            out.println("</body>");
            out.println("</html>");
        }
        finally
        {
            out.close();
        }
    }
}
