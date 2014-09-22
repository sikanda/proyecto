<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%@ page import="Entidades.Rubro"%>
<%@ page import="Entidades.Material"%>
<%@ page import="Entidades.ManoDeObra"%>
<%@ page import="Entidades.Presupuesto"%>
<%@page import="Entidades.Cliente"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@page import="java.util.Date"%>

<%@ taglib tagdir="/WEB-INF/tags" prefix="myTags1" %>

<%@ include file="WEB-INF/jspf/redirUsr.jspf" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<jsp:useBean id="globconfig" scope="application" class="Base.Config" />


<%
     	

        List<Rubro> rub =(List<Rubro>)session.getAttribute("rubros");
         session.setAttribute("rubros", rub);
       
  %>   
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title><%=globconfig.nombrePag() %></title>
          <%@ include file="WEB-INF/jspf/estilo.jspf" %>
          <script src="js/jquery-1.6.4.min.js" ></script>	

<script>
$(function() {
   $('td:nth-child(4)').hide(); //rubros
   
   $("#myTable td:nth-child(4)").each(function(){
switch($(this).text().length) {
 case 6:
  (($(this)).prev().prev().prev()).css("text-indent","15px");
  break;
   case 9:
   (($(this)).prev().prev().prev()).css("text-indent","30px");
  break;
 case 12:
   ($(this)).prev().prev().prev().css("text-indent","45px");
  break;
} 
});

  var tot = 0;
   $("#myTable td:nth-child(6)").each(function() {
        $this = $(this);
        if ($this.html() !== "") {
            tot += parseInt($this.html());
        }
    });
    $("#totalGral").html(tot);

});
</script>
    </head>
          <body>
              <div id="bg">
                  <div id="outer">
                      <div id="header">
                          <div id="logo">
                              <h1>
                                  <a href="#"><%= globconfig.nombrePag()%></a>
                              </h1>
                          </div>
                          <%@ include file="WEB-INF/jspf/barrausuario.jspf" %>
                          <div id="nav">
                              <ul>
                                  <li><p class="posicion"><a href="<%= response.encodeURL("inicioUsuario.jsp")%>">inicio</a><%=globconfig.separador()%>edit</a></p></li>
                              </ul>
                              <br class="clear" />
                          </div>
                      </div>
                      <div id="main">
                          <h2 id="titulo">Presupuesto</h2>
              
                          <div id="formu">
                              <form name="frmPresupuesto" action="pantallaTres.jsp">
                            <div id="tabla">                  
                              <table id="myTable" >
                                  <tbody>
                                      <tr>
                                          <th style="width: 300px;">Descripcion</th>
                                          <th>Cantidad</th>
                                          <th>Unidad</th>
                                          <th>Precio</th>
                                          <th>Total</th>
                                      </tr>
                                   <c:set var="totalGeneral" value="${0}"/>     
                                  <c:forEach items="${sessionScope.rubros}" var="rub" >
                                  <myTags1:displayRubrosPres rub="${rub}"/> 
                               <%--<tr> 
                                    <td  style="font-weight: bold"> Total Rubro: </td> 
                                   <c:set var="totalPorRubro" value="${totalPorRubro + totalRowMa + totalRowMo}"></c:set> 
                                    <td colspan="4" ><c:out value= "${totalPorRubro}" /></td>
                                </tr> --%>  
                              
                                    </c:forEach>
                                         <tr > 
                                        <td > Total General:  </td>  
                                      <%--  <td colspan="4"><c:out value= "${totalGeneral}" /></td> --%>  
                                       <td colspan="4" id = "totalGral"></td>
                                    </tr>
                                  </tbody>
                              </table>
                         </div>  
                        <input type="submit" value="Guardar" />
                   </form>
                      </div>
                  </div>
              </div>
          </body>
</html>
