<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<jsp:useBean id="globconfig" scope="application" class="Base.Config" />

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><%= globconfig.nombrePag()%> </title>
  <%@ include file="WEB-INF/jspf/estilo.jspf" %>
		
    </head>
    <body>
        <div id="bg">
                <div id="outer">
                        <div id="headerlogin">
                                <div id="logologin">
                                    <h1>
                                            <a href="#">Sistema de presupuesto de obras civiles</a>
                                    </h1>
                                </div>

                                        <form id="admlogin" name="admlogin" >
                                            <fieldset>
                                                <legend><strong>Bienvenido Administrador </strong></legend>
                                                 <br />
                                                                                         
                                               <a href="listaProveedores.jsp"> ABM Proveedores</a><br>
                                            </fieldset>
                                        </form>
                        </div>
                </div>
           
        </div>
    </body>
</html>