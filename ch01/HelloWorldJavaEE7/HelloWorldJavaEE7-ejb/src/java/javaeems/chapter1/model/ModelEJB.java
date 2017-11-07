/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaeems.chapter1.model;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

/**
 * Enterprise Bean que puede manejar mensajes provisto por el usuario: Almacenarlo en la BD, Limpiar el mensaje
 * almacenado. Recuperar el mensaje almacenado en la BD.
 * @author barcvilla
 */
//esta anotacion convierte a una clase java en Enterprise Bean. Declara como Stateful session bean. Esto significa
//Est significa que el contenedor Emterprise bean ejecutara este Enterprise Bean y creara una instancia del bean
// para cada cliente que se comunica
@Stateful 
public class ModelEJB 
{
    @PersistenceUnit
    private EntityManagerFactory emf;
    
    public void deleteMessage()
    {
        EntityManager em = emf.createEntityManager();
        em.createNamedQuery("deleteMessages");
    }
    
    /**
     * 
     * @param messageString : enviado desde el WriteServlet y convertido a una instancia  de la clase Message
     * @throws MessageException 
     */
    public void putUserMessage(String messageString) throws MessageException
    {
        this.deleteMessage();
        try
        {
            String decodedMessage = URLDecoder.decode(messageString, "UTF-8");
            Message message = new Message("1", "(" + messageString + ")" + " in a DataBase");
            EntityManager em = emf.createEntityManager();
            em.persist(message);
        }
        catch(UnsupportedEncodingException uee)
        {
            throw new MessageException("Something odd about that message..." + messageString);
        }
    }
    
    public String getStoredMessage() throws MessageException
    {
        EntityManager em = emf.createEntityManager();
        List messages = em.createNamedQuery("findMessages").getResultList();
        if(messages.size() > 0)
        {
            Message message = (Message) messages.get(0);
            return "(" + message.getMessageString() + "), inside an EJB";
        }
        else
        {
            throw new MessageException("There was nothing in the database");
        }
    }
}
