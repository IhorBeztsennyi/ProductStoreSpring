<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
    <head>
         <c:import url="${contextPath}/WEB-INF/html/header.jsp"/>
    </head>

    <body>
        <c:import url="${contextPath}/WEB-INF/html/navibar.jsp"/>
        <div class="container">
              <table class="table table-hover">
                      <thead>
                      <tr>
                          <td>Vendor name</td>
                          <td>id</td>
                      </tr>
                      </thead>
                      <tbody>
                         <c:forEach items="${vendors}" var="vendor">
                              <tr>
                                  <td>
                                      <c:out value="${vendor.name}"/>
                                  </td>
                                  <td>
                                      <c:out value="${vendor.id}"/>
                                  </td>
                              </tr>
                         </c:forEach>
                      </tbody>
              </table>
        </div>
    </body>
</html>