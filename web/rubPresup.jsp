<%@page import="Entidades.Cliente"%>
<%@page import="java.util.Date"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<%@ page import="Entidades.Rubro"%>
<%@ page import="Entidades.Presupuesto"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>

<%@ include file="WEB-INF/jspf/redirUsr.jspf" %>

<jsp:useBean id="globconfig" scope="application" class="Base.Config" />
<jsp:useBean id="rubroDB" scope="page" class="Datos.RubroDB" />
<jsp:useBean id="presupuestoDB" scope="page" class="Datos.PresupuestoDB" />

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
        
        Rubro r1 = new Rubro();
        Rubro r2 = new Rubro();
        Rubro r3 = new Rubro();
        Rubro r4 = new Rubro(); 
          
//        rubPresu.add(r1.getRubro("001001"));
//        rubPresu.add(r4.getRubro("002001"));
        rubPresu.add(r1.getRubro("003001003"));
        rubPresu.add(r4.getRubro("003001008"));
        rubPresu.add(r2.getRubro("003001001001"));
        rubPresu.add(r3.getRubro("003001001002"));    
        
        
//        
        p.setRubros(rubPresu);
//        rubPresuDev = p.devolverRubrosPresupuesto();
        
        lala = p.save();
        
        //comment
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
                                    //List<Rubro> subrub = new ArrayList();   
                                   // List<Rubro> subrub2 = new ArrayList(); 
                                  //   List<Rubro> subrub3 = new ArrayList(); 
                                            
                               // for (int i = 0; i < rub.size(); i++) { %>
                                          
                                      <%//= rub.get(i).getDescRubro()%><br/>
                                               
                               <%
                               //subrub = rub.get(i).getSubrubros(); 
                              //  for (int j = 0; j < subrub.size(); j++) {
                                 %>
                                                
                                   <ul>   
                                        <li>    <%//= subrub.get(j).getDescRubro() %>
                                         <ul><li> <%//= subrub.get(j).getMateriales() %></li>
                                                 <li> <%//= subrub.get(j).getManoDeObra() %></li></ul></li>
                                         
                                            <%
                                            //subrub2 = subrub.get(j).getSubrubros();
                                             // for (int k = 0; k < subrub2.size();k++) {
                                             %>    
                                             <ul>  
                                             
                                             <li> <%//= subrub2.get(k).getDescRubro() %>
                                             <ul><li> <% //= subrub2.get(k).getMateriales() %></li>
                                                 <li> <% //=subrub2.get(k).getManoDeObra() %></li></ul></li>
                                             
                                         <% //   subrub3 = subrub2.get(k).getSubrubros();
                                         
                                             // for (int m = 0; m < subrub3.size();m++) {
                                             %>  
                                    <ul><li> <%//= subrub3.get(m).getDescRubro() %>
                                        
                                      <ul><li> <%//= subrub3.get(m).getMateriales() %></li>
                                         <li> <%//= subrub3.get(m).getManoDeObra() %></li></ul></li>
                                    </ul>
                                              <% 
                               // }%>
                                   
                                     </ul>
                                <%  //  }          %>
                                   </ul>
                               <% 
                              //  }
                                
                                //  }          %>
                               
                            </div>  
            </div>
            </div>
        </div>
    </body>
    </html>
