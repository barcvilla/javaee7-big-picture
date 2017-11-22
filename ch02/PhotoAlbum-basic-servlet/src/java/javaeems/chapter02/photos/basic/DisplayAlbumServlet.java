/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaeems.chapter02.photos.basic;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 * Servlet que muestra la pagina principal de la web app (photo album) y tambien controla la carga de una
 * nueva foto. Este servlet respondera a los request HTTP GET y HTTP POST
 * @author barcvilla
 */
@WebServlet(name="DisplayAlbumServlet", urlPatterns={"/DisplayAlbumServlet"})
@MultipartConfig()
public class DisplayAlbumServlet extends HttpServlet
{
    /*
    * El get request sera solicitado para mostrar solo el album
    */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        handleRequest(request, response);
    }
    
    /*
    * El Post request sera solicitado por el cliente para cargar un archiv y mostarlo el album actualizado.
    */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        handleRequest(request, response);
    }
    
    protected void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        ServletContext servletContext = request.getServletContext();
        PhotoAlbum pa = PhotoAlbum.getPhotoAlbum(servletContext);
        // lo primero es verificar si el tipo de contenido del request es : multipart/from-data
        if(request.getContentType() != null && request.getContentType().startsWith("multipart/from-data"))
        {
            // si es asi, se detecta que hay informacion de una foto y el servicio de upload se delega al metodo.
            this.uploadPhoto(request, pa);
        }
        
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter writer = response.getWriter();
        try {    
            writer.write("<html>");
            writer.write("<head>");
            writer.write("<title>Photo Viewer</title>");            
            writer.write("</head>");
            writer.write("<body>");
            writer.write("<h3 align='center'>Photos</h3>");

            
            this.displayAlbum(pa, "", writer);

            writer.println("</body>");
            writer.println("</html>");
        } finally {            
            writer.close();
        }

    }
    
    /**
     * 
     * @param request
     * @param pa
     * @throws IOException
     * @throws ServletException 
     */
    private void uploadPhoto(HttpServletRequest request, PhotoAlbum pa) throws IOException, ServletException
    {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        String fileName = null;
        for(Part p : request.getParts())
        {
            this.copyBytes(p.getInputStream(), baos);
            fileName = p.getSubmittedFileName();
        }
        if("".equals(fileName))
        {
            String photoName = fileName.substring(0, fileName.lastIndexOf("."));
            pa.addPhoto(photoName, baos.toByteArray());
        }
    }
    
    /**
     * Metodo para escribir codigo html de una table, itera sobre los items en el PhotoAlbum, colocando
     * cada item en una celda de la tabla. Cuando se hace click en la imagen, se invoca al DisplayPhotoServlet
     * para mostrar la imagen completa. La ultima fila de la tabla inserta un link RemovePhotoServlet
     * @param pa
     * @param label
     * @param writer 
     */
    private void displayAlbum(PhotoAlbum pa, String label, PrintWriter writer)
    {
        writer.write("<h3 align='center'>" + label + "</h3>");
        writer.write("<table align='center'>");
        for (int j = 0; j < pa.getPhotoCount(); j++) 
        {
            writer.write("<td>");
            writer.write("<a href='./DisplayPhotoServlet?photo=" + j + "'>");
            writer.write("<img src='./DisplayPhotoServlet?photo=" + j + "' alt='photo' height='120' width='150'> ");
            writer.write("</a>");
            writer.write("</td>");
        }
        writer.write("<td bgcolor='#cccccc' width='120' height='120'>");
        writer.write("<form align='left' action='DisplayAlbumServlet' method='post' enctype='multipart/form-data'>");
        writer.write("<input value='Choose' name='myFile' type='file' accept='image/jpeg'><br>");
        writer.write("<input value='Upload' type='submit\'><br>");
        writer.write("</form>");
        writer.write("</td>");
        writer.write("</tr>");

        writer.write("<tr>");
        for (int j = 0; j < pa.getPhotoCount(); j++) {
            writer.write("<td align='center'>");
            writer.write(pa.getPhotoName(j));
            writer.write("</td>");
        }
        writer.write("</tr>");

        writer.write("<tr>");
        for (int j = 0; j < pa.getPhotoCount(); j++) {
            writer.write("<td align='center'>");
            writer.write("<a href='RemovePhotoServlet?photo=" + j + "'>remove</a>");
            writer.write("</td>");
        }
        writer.write("</tr>");
        writer.write("</table>");

    }
    
    private void copyBytes(InputStream is, OutputStream os) throws IOException
    {
        int i;
        while((i = is.read()) != -1)
        {
            os.write(i);
        }
        
        is.close();
        os.close();
    }
}
