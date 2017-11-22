/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaeems.chapter02.photos.basic;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

/**
 * Esta clase contiene toda la informacion de la foto a la cual las otras 3 clases servlet utilizan
 * @author barcvilla
 */
public class PhotoAlbum 
{
    public static String ATTRIBUTE_NAME = "Photo_Album";
    private List<byte[]> photoDataList = new ArrayList<byte[]>();
    private List<String> names = new ArrayList<String>();
    
    private PhotoAlbum()
    {
    }
    
    public void addPhoto(String name, byte[] bytes) {
        this.photoDataList.add(bytes);
        this.names.add(name);
    }
    
    public byte[] getPhotoData(int i) {
        return (byte[]) photoDataList.get(i);
    }
    
    public String getPhotoName(int i) {
        return (String) names.get(i);
    }
    
    public int getPhotoCount() {
        return photoDataList.size();
    }
    
    public void removePhoto(int i) {
        photoDataList.remove(i);
        names.remove(i);
    }
    
    /**
     * Se toma un objeto ServletContext como parametro. Un ServletContext es un objeto global en la app web que
     * representa el web container que alberga a la web app. Una de sus principales caracteristicas, que el contien
     * un object map de key-value en elcual una web app puede almacenar y recuperar objetos de la aplicacion usando
     * la llamada de metodos: setAttributes, getAttributes
     * @param session
     * @return 
     */
    public static PhotoAlbum getPhotoAlbum(ServletContext session)
    {
        if(session.getAttribute(ATTRIBUTE_NAME) == null)
        {
            PhotoAlbum pa = new PhotoAlbum();
            session.setAttribute(ATTRIBUTE_NAME, pa);
        }
        
        return (PhotoAlbum)session.getAttribute(ATTRIBUTE_NAME);
    }
}
