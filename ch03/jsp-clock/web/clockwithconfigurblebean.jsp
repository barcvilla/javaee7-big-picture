<%-- 
    Document   : clockwithconfigurblebean
    Created on : 26/12/2017, 12:58:15
    Author     : barcvilla
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title></title>
    </head>
    <body> <!-- declaramos que el JSP usara un JavaBean -->
        <jsp:useBean id="myBean" class="javaeems.chapter3.clockbean.ConfigurableClockBean" />
        <div align='center'>
            <br/>
            Hello there!
            <br/><br/>
            It's been
            <jsp:getProperty name="myBean" property="currentTimeSinceEpoch" /> <!-- Aqui llamamos al JavaBean -->
            milliseconds since nidnight, January 1st 1970
            <br/><br/>
            In other words, its <jsp:getProperty name="myBean" property="readableDate" />
            <br/><br/>
            <jsp:setProperty name="myBean" property="dateFormat" value="MMMMMMMMM" />
            Or in other words, its the month of <jsp:getProperty name="myBean" property="readableDate" /> in
            <jsp:setProperty name="myBean" property="dateFormat" value="YYYY" />
            <jsp:getProperty name="mybean" property="readableDate" /> 
        </div>
    </body>
</html>
