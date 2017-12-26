<%-- 
    Document   : clockWithBean
    Created on : 26/12/2017, 11:26:39
    Author     : barcvilla
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Clock</title>
    </head>
    <body>
        <jsp:useBean id="myBean" class ="javaeems.chapter3.clockbean.ClockBean" />
        <div align='center'>
            <br/>
            Hello There!
            <br/><br/>
            It's been
            <jsp:getProperty name="myBean" property="currentTimeSinceEpoch" />
            milliseconds since midnight, January 1st 1970
            <br /><br />
            In other words, its <jsp:getProperty name="myBean" property="readableDate" />
        </div>
    </body>
</html>
