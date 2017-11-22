/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaeems.chapter02.photos.basic;

import java.io.IOException;
import java.io.OutputStream;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet que obtiene una referencia al photo album mediante del paso de un objeto ServletContext a la clase
 * PhotAlbum. Luego este obtiene el image data desde una instancia de PhotoAlbum y escribe la respuesta
 * @author barcvilla
 * el Servlet es mapeado al URI /DisplayPhotoServlet. Lo cual significa que es accesado por el cliente en el
 * URI http://<hostname:port>/photo/DisplayPhotoServlet ya que photo es el context path que contiene al web app
 */
@WebServlet(name="DisplayPhotoServlet", urlPatterns={"/DisplayPhotoServlet"})
public class DisplayPhotoServlet extends HttpServlet
{
    /*
    * metodo doGet() sobreescrito de la clase HttpServlet. Lo que significa que el servlet solo controla las
    * HTTP GET request. Otras HTTP request no son pasados al este servlet. 
    */
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        // servlet hace un parse out en el query string
        String indexString = request.getParameter("photo");
        int index = (new Integer(indexString.trim())).intValue();
        // set el context-type de la respuesta a image/jpeg que enviara de regreso image data al cliente.
        response.setContentType("image/jpeg");
        // obtenemos una referencia al OutputStream response.  referencia donde se escribirá el image data
        OutputStream out = response.getOutputStream();
        try
        {
            // obtenemos una referencia a un objeto de tipo ServletContext. Este objeto representa a todo el
            // servelt en la app web
            ServletContext myServletContext = request.getServletContext();
            PhotoAlbum pa = PhotoAlbum.getPhotoAlbum(myServletContext);
            byte[] bytes = pa.getPhotoData(index);
            for(int i = 0; i < bytes.length; i++)
            {
                out.write(bytes[i]);
            }
        }
        finally
        {
            out.close();
        }
    }
}
