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
 *
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
