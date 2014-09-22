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
<%-- <jsp:useBean id="presupuestoBean" class="Entidades.Presupuesto"  scope="session"/> --%>

<%
        List<Rubro> rubPresu = new ArrayList();
        List<Rubro> rubPresuDev = new ArrayList();
        
        Cliente c = new Cliente();
        c.setIdCliente(1);
        
        Presupuesto p = new Presupuesto();
        p.setCliente(c);
        p.setObservaciones("TEST");
        p.setUsuario((Usuario)session.getAttribute("usuario"));
        
        p.setFechaCreacion(new Date());
        
        Rubro r1 = new Rubro();
        Rubro r2 = new Rubro();
        Rubro r3 = new Rubro();
        Rubro r4 = new Rubro(); 
          
        rubPresu.add(r1.getRubro("001001"));
        rubPresu.add(r4.getRubro("002001"));
        rubPresu.add(r3.getRubro("003001001002"));    
        rubPresu.add(r2.getRubro("009001"));
     
        p.setRubros(rubPresu);
        rubPresuDev = p.devolverRubrosPresupuesto();
          
         session.setAttribute("rubros", rubPresuDev);
        
       
  %>   
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title><%=globconfig.nombrePag() %></title>
          <%@ include file="WEB-INF/jspf/estilo.jspf" %>
          <script src="js/jquery-1.6.4.min.js" ></script>	

<script>
$(function() {
   
   $("#myTable td.unit").each(function() {
    if ($(this).text().length !== 0)
         {
             ($(this).prev()).text("1");
             ($(this).prev()).addClass("edit");
         }
});

$('td:nth-child(4)').hide(); //rubros
$('td:nth-child(5)').hide(); //valores originales

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


 $("#myTable td.edit").click(function() { 
   //  console.log($(this)) ;
        var text = $(this).text();
        var rub = $(this).next().next().text();
        var tieneClase = $(this).next().hasClass("unit");
        $(this).text('');
        $('<input type="text" id="txt" style="width: 40px;" />').appendTo($(this)).val(text).select().keypress(function(e) {
            if(e.keyCode === 13 || e.keyCode === 9)  {
                var newText = $(this).val();
                $(this).parent().text(newText).find('input:text').remove();
 
      if(tieneClase){ //alert ("tiene clase");
            
                 $("#myTable td.hidRub").each(function() {
                   if ($(this).text() === rub   )
                    {   //this es col 4 de rubro
                         var oldVal = $(this).next().text(); //5ta col tiene val sin modif
                         $(this).prev().prev().text(oldVal * newText);
                    }
                });
              }//tiene clase
           } //key code
    } );
    
    
       $("#txt").blur(
                function(){
                var newText = text;
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
                          <h3 >Haga click en las cantidades para editarlas</h3>
                          <div id="formu">
                              <form name="frmEditaCants" action="pantallaTres.jsp">


                                  <div id="tabla">                  
                                      <table id="myTable" >
                                          <tbody>
                                              <tr><th style="width: 300px;">Descripcion</th><th>Cantidad</th><th>Unidad</th></tr>
                                                      <c:forEach items="${sessionScope.rubros}" var="rub" >
                                                          <myTags:displayRubros rub="${rub}"/> 
                                                      </c:forEach>
                                          </tbody>
                                      </table>

                                  </div>
                                  <input type="submit" value="Siguiente" />
                               </form>
                          </div>
                      </div>
                  </div>
          </body>
</html>
