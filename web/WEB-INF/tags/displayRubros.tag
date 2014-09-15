<%@tag description="recursive call to print tree" pageEncoding="UTF-8"%>
 <%@ taglib tagdir="/WEB-INF/tags" prefix="myTags" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%-- The list of normal or fragment attributes can be specified here: --%>
<%@ attribute name="rub" required="true" type="Entidades.Rubro" %>

<%-- any content can be specified here e.g.: --%>    
<tr>
<td><c:out value= "${rub.descRubro}" /></td>
<td><c:out value= "${rub.idUnidadMedida}"/></td>
<td></td>
</tr>

<c:forEach items="${rub.materiales}" var="mat">
    <tr>
    <td><c:out value= "${mat.descMaterial}"/></td> 
    <td><c:out value= "${mat.idUnidadMedida}"/></td>  
    <td class= "edit"><c:out value= "${mat.coefStdMat}" /></td>
    </tr>
</c:forEach>

<c:forEach items="${rub.manoDeObra}" var="mo">
    <tr>
    <td><c:out value= "${mo.descManoDeObra}" /></td>
    <td><c:out value= "${mo.idUnidadMedida}" /></td> 
    <td class= "edit"><c:out value= "${mo.coefStdMO}" /></td>
    </tr>
</c:forEach>

<c:forEach items="${rub.subrubros}" var="srub">
    <myTags:displayRubros rub="${srub}"/>       
</c:forEach>
  

