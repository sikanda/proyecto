<%@tag description="recursive call to print tree" pageEncoding="UTF-8"%>
 <%@ taglib tagdir="/WEB-INF/tags" prefix="myTags1" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 

<%-- The list of normal or fragment attributes can be specified here: --%>
<%@ attribute name="rub" required="true" type="Entidades.Rubro" %>

<%-- any content can be specified here e.g.: --%>    
<tr>
<td><c:out value= "${rub.descRubro}" /></td>
<td></td> <%-- cantidad --%>    
<td class= "unit"><c:out value= "${rub.idUnidadMedida}"/></td>
<td><c:out value= "${rub.idRubro}" /></td> 
<td></td> <%-- precio --%>  
<td></td> <%-- total --%>  
</tr>

<c:set var="totalPorRubro" value="${0}"/>
<c:set var="totalFinalMat" value="${0}"/>
<c:set var="totalFinalMO" value="${0}"/>

<c:forEach items="${rub.materiales}" var="mat" >
    <tr >
        <td><c:out value= "${mat.descMaterial}"/></td> 
        <td class= "edit"><c:out value= "${mat.coefStdMat}" /></td>  <%-- cantidad --%> 
        <td><c:out value= "${mat.idUnidadMedida}"/></td> 
        <td class="hidRub"><c:out value= "${rub.idRubro}" /></td> 
        <td><c:out value= "${mat.precioMa}"/></td> 
       <c:set var="totalRowMa" value="${mat.coefStdMat * mat.precioMa}"  /> 
        <td><fmt:formatNumber value="${totalRowMa}" type="number" /></td> <%-- total --%> 
   <%--      <td><c:out value= "${totalRowMa}" /></td> <%--total --%> 
   
      <c:set var="totalFinalMat" value="${totalFinalMat + totalRowMa}"  /> 
    </tr>
  </c:forEach>

<c:forEach items="${rub.manoDeObra}" var="mo">
    <tr>
        <td><c:out value= "${mo.descManoDeObra}" /></td>
        <td class= "edit"><c:out value= "${mo.cantPres}" /></td>    <%-- cantidad --%> 
        <td><c:out value= "${mo.idUnidadMedida}" /></td> 
        <td class="hidRub"><c:out value= "${rub.idRubro}" /></td> 
        <td><c:out value= "${mo.precioMo}" /></td>
        <c:set var="totalRowMo" value="${mo.coefStdMO * mo.precioMo}"  /> 
         <td><fmt:formatNumber value="${totalRowMo}" type="number" /></td> <%-- total --%> 
        <%--<td><c:out value= "${totalRowMo}" /></td> <%-- total --%> 
        <c:set var="totalFinalMO" value="${totalFinalMO + totalRowMo}"  /> 
    </tr>
 </c:forEach>

<c:forEach items="${rub.subrubros}" var="srub">
    <myTags1:displayRubrosPres rub="${srub}"/>       
</c:forEach>

    <c:if test="${empty rub.subrubros}">
 <tr >
   <c:set var="totalPorRubro" value="${totalPorRubro + totalFinalMat + totalFinalMO}"></c:set> 
   <td > <c:out value= "Total Rubro:" /> </td>  
   <td colspan="4" style="text-align: right" ><fmt:formatNumber value="${totalPorRubro}" type="currency" /> </td>     
</tr>
</c:if>

  