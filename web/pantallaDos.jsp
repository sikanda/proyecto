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

<%@ taglib tagdir="/WEB-INF/tags" prefix="myTags" %>

<%@ include file="WEB-INF/jspf/redirUsr.jspf" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<jsp:useBean id="globconfig" scope="application" class="Base.Config" />


<%
        boolean lala  ;
	List<Rubro> rub = new ArrayList();
        List<Rubro> rubPresu = new ArrayList();
        List<Rubro> rubPresuDev = new ArrayList();
//	rub = rubroDB.getRubrosConSubrubros();
        
        Cliente c = new Cliente();
        c.setIdCliente(1);
        
        Presupuesto p = new Presupuesto();
        p.setCliente(c);
        p.setObservaciones("TEST");
        p.setUsuario((Usuario)session.getAttribute("usuario"));
        
        p.setFechaCreacion(new Date());
        
        Rubro r1,r2,r3,r4 = new Rubro();
          
        rubPresu.add(r1.getRubro("001001"));
        rubPresu.add(r4.getRubro("002001"));
        rubPresu.add(r2.getRubro("003001003"));
        rubPresu.add(r3.getRubro("003001001002"));    
     
        p.setRubros(rubPresu);
        rubPresuDev = p.devolverRubrosPresupuesto();
        
        
         request.setAttribute("rubros", rubPresuDev);
        
       
  %>   
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title><%=globconfig.nombrePag() %></title>
          <%@ include file="WEB-INF/jspf/estilo.jspf" %>
          <script src="js/jquery-1.6.4.min.js" ></script>	

<script>
$(function() {
    alert('jq carga ok');
//$("#myTable td.edit").click(function() {     // function_td
  //$(this).css("font-weight","bold");
 //  alert($(this).text());
 //});
  
 $("#myTable td.edit").click(function() { 
        var text = $(this).text();
        $(this).text('');
        $('<input type="text" />').appendTo($(this)).val(text).select().blur(
            function(){
                var newText = $(this).val();
                $(this).parent().text(newText).find('input:text').remove();
           });
    });
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
                          <h2 id="titulo">Editar cantidades</h2>
                          <div id="tabla">                  
                              <table id="myTable" >
                                  <tbody>
                                      <tr><th>Descripcion</th><th>Unidad</th><th>Cantidad</th></tr>
                                              <c:forEach items="${requestScope.rubros}" var="rub">
                                                  <myTags:displayRubros rub="${rub}"/> 
                                              </c:forEach>
                                  </tbody>
                              </table>

                          </div>         
                      </div>
                  </div>
              </div>
          </body>
</html>
