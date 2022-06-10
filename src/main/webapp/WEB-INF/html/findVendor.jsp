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
            <form action="/vendors/name/">
                 <div class="form-group">
                    <label for="name">Vendor name:</label><br>
                    <input type="text" class="form-control" id="name" placeholder="Enter vendor name" name="vendorName"><br>
                 </div>
                     <input type="submit" value="Submit">
            </form>
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
