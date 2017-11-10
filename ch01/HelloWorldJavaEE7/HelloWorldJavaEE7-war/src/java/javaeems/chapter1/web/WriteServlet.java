/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaeems.chapter1.web;

import java.io.IOException;
import javaeems.chapter1.model.MessageException;
import javaeems.chapter1.model.ModelEJB;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * WriteServlet es el responsable de procesar el mensaje que llega desde la web page y pasa el mensaje a la capa
 * Enterprise Bean para su procesamiento. 
 * @author barcvilla
 */
// @WebServlet define su posicion en el URI Space.
@WebServlet(name="WriteServlet", urlPatterns={"/WriteServlet"})
public class WriteServlet extends HttpServlet // este web component intercepta las solicitudes HTTP POST
{
    @EJB
    private ModelEJB helloEJB; // obtenemos una instancia del EJB object
    private static String PUT_MESSAGE = "put_message";
    
    @Override // metodo override de la clase HttpServlet
    protected void doPost(HttpServletRequest request, HttpServletResponse responde) throws ServletException, IOException
    {
        String message = request.getParameter(PUT_MESSAGE);
        if("".equals(message))
        {
            helloEJB.deleteMessage();
        }
        else
        {
            try
            {
                helloEJB.putUserMessage(message);
            }
            catch(MessageException nme)
            {
                throw new ServletException(nme);
            }
        }
        responde.sendRedirect("./DisplayServlet");
    }
}
