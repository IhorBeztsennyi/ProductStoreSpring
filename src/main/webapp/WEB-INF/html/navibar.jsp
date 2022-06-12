<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

           <nav class="navbar navbar-inverse">
              <div class="container">
                <div class="navbar-header">
                  <a class="navbar-brand" href="/">Product Store App</a>
                </div>
                <ul class="nav navbar-nav">
                  <li><a href="/">Home</a></li>
                  <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">Vendors<span class="caret"></span></a>
                    <ul class="dropdown-menu">
                    <li><a href="/vendors/all">Show all</a></li>
                      <li><a href="/vendors/form/find">Find by name</a></li>
                      <li><a href="/vendors/form/add">Add new</a></li>
                      <li><a href="/vendors/form/update">Update</a></li>
                      <li><a href="/vendors/form/delete">Delete</a></li>
                    </ul>
                  </li>
                  <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">Products <span class="caret"></span></a>
                    <ul class="dropdown-menu">
                         <li><a href="/products/all">Show all</a></li>
                         <li><a href="/products/form/find">Find by name</a></li>
                         <li><a href="/products/form/add">Add new</a></li>
                         <li><a href="/products/form/update">Update</a></li>
                         <li><a href="/products/form/delete">Delete</a></li>
                    </ul>
                  </li>
                <security:authorize access="hasRole('ROLE_ADMIN')">
                  <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">Users <span class="caret"></span></a>
                   <ul class="dropdown-menu">
                        <li><a href="/users/all">Show all</a></li>
                        <li><a href="/users/form/find">Find by email</a></li>
                        <li><a href="${pageContext.request.contextPath}/users/registration">Create new</a></li>
                        <li><a href="/users/form/update/admin">Up to admin</a></li>
                        <li><a href="/users/form/update/user">Down to user</a></li>
                        <li><a href="/users/form/delete">Delete</a></li>
                   </ul>
                  </li>
                </security:authorize>
                </ul>
                <ul class="nav navbar-nav navbar-right">
                    <li>
                        <a style="float: right" href="/logout">Logout</a>
                    </li>
                </ul>
              </div>
            </nav>