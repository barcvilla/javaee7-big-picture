/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaeems.chapter02.photos.basic;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author barcvilla
 */
@WebServlet(name="RemovePhotoServlet", urlPatterns={"/RemovePhotoServlet"})
public class RemovePhotoServlet extends HttpServlet
{
    /**
     * Metodo que procesa HTTP GET request, evento que se produce  cuando el usuario hace click el hiperlink 
     * llamado remove link debajo de cada foto en el album. Similar a DisplayPhotoServlet, este servlet espera
     * un query string en la forma de ?photo={index}
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException 
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // se extrae el indice de la foto a ser eliminada del PhotoAlbum
        String indexString = request.getParameter("photo");        
        int index = (new Integer(indexString.trim())).intValue();
        PhotoAlbum pa = PhotoAlbum.getPhotoAlbum(request.getServletContext());
        pa.removePhoto(index);
        /**
         * El servlet obtiene una referencia a RequestDispatcher usando el URI relative DisplayAlbumServlet
         * El objeto RequestDispatcher retornado actua como un envoltorio sobre otros componentes web en la misma
         * app web, en este caso, el DisplayAlbumServlet. Mediante la llamada a forward() el RemovePhotoServlet
         * esta delegando la creacion de HTTP response al DisplayAlbumServlet, el cual procede mostrando las
         * fotos que quedan en el nuevo album de potos reducido.
         */
        /**
         * Una vez que la foto ha sido removida, este servlet usa RequestDispatcher para simplemente enviar la
         * solicitud recibida al servlet DisplayAlbumServlet, la cual lo trata como cualquier solicitud HHTP que ha
         * venido directo desde el cliente a fin de mostrar el nuevo album de foto actualizado
         */
        RequestDispatcher rd = request.getRequestDispatcher("DisplayAlbumServlet");
        rd.forward(request, response); 
    }
}
