<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
    <head>
         <c:import url="${contextPath}/WEB-INF/html/header.jsp"/>
    </head>

    <body>
        <c:import url="${contextPath}/WEB-INF/html/navibar.jsp"/>
        <div class="container">
            <form:form action="/vendors/" method="post" modelAttribute="vendorDto">
                <div class="form-group">
                    <form:label path="name">Vendor name:</form:label><br>
                    <form:input type="text" class="form-control" id="vendorName" placeholder="Enter vendor name" name="vendorName" path="name"/><form:errors path="name" style="color:red"/><br>
                </div>
                    <input type="submit" value="Submit"/>
           </form:form>
            <c:if test="${not empty errorMessage}">
              <c:forEach items="${errorMessage.errors}" var="error">
                 <p style="color:red">${error}</p>
              </c:forEach>
            </c:if>
        </div>
    </body>
</html>
