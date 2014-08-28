<%@page contentType="text/html" pageEncoding="UTF-8"%>


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
  <link rel="stylesheet" href="WEB-INF/dist/themes/default/style.min.css" />
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
                            <div id="jstree">
                         
                                            <%
                                    List<Rubro> subrub = new ArrayList();   
                                    List<Rubro> subrub2 = new ArrayList(); 
                                            
                                for (int i = 0; i < rub.size(); i++) {
                                       subrub = rub.get(i).getSubrubros();   %>
                                          
                                      <%= rub.get(i).getDescRubro()%><br/>
                                               
                               <%
                                for (int j = 0; j < subrub.size(); j++) {
                                 %>
                                                
                                   <ul>   
                                        <li id="child_node_1">    <%= subrub.get(j).getDescRubro() %></li>
                                          
                                            <%
                                            subrub2 = subrub.get(j).getSubrubros();
                                              for (int k = 0; k < subrub2.size();k++) {
                                             %>    
                                              
                                             
                                             <ul><li> <%= subrub2.get(k).getDescRubro() %></li></ul>
                                             
                                   <%                
                                    }
                                   %>
                                     </ul>
                               <% 
                                }
                                  }          %>
                                           
                                   
                               
                            </div>
            </div>
            </div>
        </div>
  <!-- 4 include the jQuery library -->
  <script src="WEB-INF/dist/libs/jquery.js"></script>
  <!-- 5 include the minified jstree source -->
  <script src="WEB-INF/dist/jstree.min.js"></script>
  <script>
  $(function () {
    // 6 create an instance when the DOM is ready
    $('#jstree').jstree();
    // 7 bind to events triggered on the tree
    $('#jstree').on("changed.jstree", function (e, data) {
      console.log(data.selected);
    });
    // 8 interact with the tree - either way is OK
    $('button').on('click', function () {
      $('#jstree').jstree(true).select_node('child_node_1');
      $('#jstree').jstree('select_node', 'child_node_1');
      $.jstree.reference('#jstree').select_node('child_node_1');
    });
  });
  </script>
    </body>
    </html>
