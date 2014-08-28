<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%@ page import="Entidades.Rubro"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>

<%@ include file="WEB-INF/jspf/redirUsr.jspf" %>

<jsp:useBean id="globconfig" scope="application" class="Base.Config" />
<jsp:useBean id="rubroDB" scope="page" class="Datos.RubroDB" />

<%
	List<Rubro> rub = new ArrayList();
	rub = rubroDB.getRubrosConSubrubros();
%>
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title><%=globconfig.nombrePag() %></title>
 
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
                                        <li><p class="posicion"><a href="<%= response.encodeURL("inicioUsuario.jsp")%>">inicio</a><%=globconfig.separador()%>rubros</a></p></li>
                                   </ul>
                                    <br class="clear" />
                                </div>
                        </div>
            <div id="main">

                            <div id="opciones">
                           
                            </div>

                            <h2 id="titulo">Lista de rubros</h2>
                          
                         
                                            <%
                                    List<Rubro> subrub = new ArrayList();   
                                    List<Rubro> subrub2 = new ArrayList(); 
                                     List<Rubro> subrub3 = new ArrayList(); 
                                            
                                for (int i = 0; i < rub.size(); i++) { %>
                                          
                                      <%= rub.get(i).getDescRubro()%><br/>
                                               
                               <%
                               subrub = rub.get(i).getSubrubros(); 
                                for (int j = 0; j < subrub.size(); j++) {
                                 %>
                                                
                                   <ul>   
                                        <li>    <%= subrub.get(j).getDescRubro() %></li>
                                         
                                            <%
                                            subrub2 = subrub.get(j).getSubrubros();
                                              for (int k = 0; k < subrub2.size();k++) {
                                             %>    
                                             <ul>  
                                             
                                             <li> <%= subrub2.get(k).getDescRubro() %></li>
                                             
                                         <%    subrub3 = subrub2.get(k).getSubrubros();
                                         
                                              for (int m = 0; m < subrub3.size();m++) {
                                             %>  
                                    <ul><li> <%= subrub3.get(m).getDescRubro() %></li></ul>
                                              <% 
                                }%>
                                   
                                     </ul>
                                <%    }          %>
                                   </ul>
                               <% 
                                }
                                
                                  }          %>
                  </div>
            </div>
            </div>
        </div>
    </body>
</html>
