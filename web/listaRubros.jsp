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
 
<link href="dist/themes/default/style.min.css" rel="stylesheet" type="text/css" title="Estilo"/>
<style type="text/css">
    #jstree .jstree-open > .jstree-anchor > .jstree-checkbox, 
    #jstree .jstree-closed > .jstree-anchor > .jstree-checkbox { display:none; }
</style>
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
                             <button>Agregar</button>
                             
                             <div id="event_result"></div>
                             <br></br>
                            <div id="jstree">
                         <ul> 
                                            <%
                                    List<Rubro> subrub = new ArrayList();   
                                    List<Rubro> subrub2 = new ArrayList(); 
                                    List<Rubro> subrub3 = new ArrayList(); 
                                            
                                for (int i = 0; i < rub.size(); i++) { %>
                                   <li id="<%=rub.get(i).getIdRubro() %>"><%= rub.get(i).getDescRubro()%>
                                       <ul>            
                               <%
                               subrub = rub.get(i).getSubrubros(); 
                                for (int j = 0; j < subrub.size(); j++) {
                                 %>
                                        <li id="<%=subrub.get(j).getIdRubro() %>">    <%= subrub.get(j).getDescRubro() %>
                                           <ul>  
                                            <%
                                            subrub2 = subrub.get(j).getSubrubros();
                                              for (int k = 0; k < subrub2.size();k++) {
                                             %>    
                                             <li id="<%=subrub2.get(k).getIdRubro() %>"> <%= subrub2.get(k).getDescRubro() %>
                                             <ul>
                                         <%    subrub3 = subrub2.get(k).getSubrubros();
                                         
                                              for (int m = 0; m < subrub3.size();m++) {
                                             %>  
                                                 <li id="<%=subrub3.get(m).getIdRubro() %>" data-jstree='{"icon":"http://jstree.com/tree.png"}'> <%= subrub3.get(m).getDescRubro() %></li>
                                              <% 
                                }%>
                                   </ul></li>                  
                                <%    }          %>  </ul></li>
                               <% 
                                } %>
                                 </ul></li>
                                 <%   }          %>
                         </ul>
  
        </div>
<br></br>                          
                            
    </div>
                                  
                                  
                                    
</div>
  <!-- include the jQuery library -->
    <script src="dist/libs/jquery.js"> </script>
  <!-- include the minified jstree source -->
  <script src="dist/jstree.min.js"></script>

<script>
$(function () {
    $('#jstree').jstree({
                        "checkbox" : {
                                      "keep_selected_style" : false                   
                                     },
                        "plugins" : [ "checkbox" ]
                        });   
});
 			
//bind to events triggered on the tree
$('#jstree').on("changed.jstree", function (e, data) {
  console.log(data.selected);
});    

/* EXPERIMENT
$('#jstree')
  // listen for event
  .on('changed.jstree', function (e, data) {
    var i, j, r = [];
    for(i = 0, j = data.selected.length; i < j; i++) {
      r.push(data.instance.get_node(data.selected[i]).text);
    }
    $('#event_result').html('Selected:<br/> '+ r.join(', '));
  })
*/

//Stops the propagation of the selection of the nodes to their leaves
$('#jstree').on("select_node.jstree deselect_node.jstree", function (e, data) {
    if(data.node.children.length) {
        e.preventDefault(); // may not be necessary
        e.stopImmediatePropagation();
        // uncomment below if you wish to have the parent item open/close the tree when double clicked
        //return data.instance.toggle_node(data.node);
    }
})

/* $('#jstree').on('select_node.jstree', function (e, data) {
            if (data.node.children.length > 0) {
                $('#jstree').jstree(true).deselect_node(data.node);                    
                $('#jstree').jstree(true).toggle_node(data.node);                    
            }
        })*/

$('button').on('click', function (){
var selectedElmsNames = [];
var selectedElmsIds = [];
var selectedElms = $('#jstree').jstree("get_selected", true);
$.each(selectedElms, function() {
    if(this.children.length > 0 === false) //CHECK IF NODE IS LEAF
        {selectedElmsNames.push(this.text);
         selectedElmsIds.push(this.id);
        }
});
$('#event_result').html('Selected:<br/> '+ selectedElmsNames.join(', '));
})


;
  </script>
            </div>
        </div>
    </body>
</html>
