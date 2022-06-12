<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
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
            <form:form action="/products/update" method="put" modelAttribute="productDto">
                <div class="form-group">
                   <form:label path="id">Product id:</form:label><br>
                   <form:input type="text" class="form-control" id="productId" placeholder="Enter product id" name="productId" path="id"/><form:errors path="id" style="color:red"/><br>
                </div>
                <div class="form-group">
                    <form:label path="name">New product name:</form:label><br>
                    <form:input type="text" class="form-control" id="productName" placeholder="Enter product new name" name="productName" path="name"/><form:errors path="name" style="color:red"/><br>
                </div>
                <div class="form-group">
                    <form:label path="price">New product price:</form:label><br>
                    <form:input type="text" class="form-control" id="productPrice" placeholder="Enter new price" name="productPrice" path="price"/><form:errors path="price" style="color:red"/><br>
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