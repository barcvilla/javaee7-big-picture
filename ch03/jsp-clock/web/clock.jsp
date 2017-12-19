<%-- 
    Document   : clock
    Created on : 12/12/2017, 09:16:38
    Author     : barcvilla
--%>
<!-- Directiva page: rige las propiedades de JSP, como: La salida, almacenamiento en buffer, java import  -->
<!-- Directiva include: incluye el contenido d otro archivo, podemos incluir el encabezado estandard en JSP-->
<%--
Directiva taglib: Esta directiva permite a un JSP declarar que usara etiquetas especiales de una biblioteca.
Una biblioteca de etiquetas es una coleccion de clases java que formula pequeño fragmentos de salida.
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Clock</title>
    </head>
    <body>
        <div align='center'>
            <br/>
            Hello there!
            <br/><br/>
            It's been <%=System.currentTimeMillis()%> milliseconds
            since midnight, January 1st 1970
            <br/><br/>
            In other words, it's
            <%
                Date now = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("EEEEEEEE");
                String today = sdf.format(now);
                out.println(today.trim());
            %>
        </div>
    </body>
</html>
