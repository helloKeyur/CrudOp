<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!doctype html>
<html lang="en">
    <head>
	<!-- Required meta tags -->
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	
	<!-- Bootstrap CSS -->
	<link
		href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/css/bootstrap.min.css"
		rel="stylesheet"
		integrity="sha384-wEmeIV1mKuiNpC+IOBjI7aAzPcEZeedi5yW5f2yOq55WWLwNGmvvx4Um1vskeMj0"
	crossorigin="anonymous">

	<title>Author CRUDOP!</title>
        <style>
            .dataTables_paginate,.dataTables_filter {
                float: right!important;
            }
        </style>
    </head>
    <body>

        <!-- main container -->
        <div class="container-fluid py-4 bg-light">
            
            <div class="row g-x3">
                <div class="col-sm-6 col-md-3">
                    <div class="card shadow-sm">
                        <div class="card-body">
                            <h5 class="card-title mb-3">
                                <c:out value="${title}" />
                            </h5>
                                <form method="post" action="${formUrl}" enctype="multipart/form-data">
                                    <div class="mb-3">
                                    	<input type="hidden" name="authorId" value="${eauthor.authorId}">
                                        <input type="text" name="username" class="form-control" placeholder="Username" value="${eauthor.username}">
                                    </div>
                                    <div class="mb-3">
                                        <input type="email" name="email" class="form-control" placeholder="Email" value="${eauthor.email}">
                                    </div>
                                    <div class="mb-3">
                                        <input type="number" name="phone" class="form-control" placeholder="Phone" value="${eauthor.phone}">
                                    </div>
                                    <div class="mb-3">
                                    	<select name="qualification" class="form-control">
                                        	<option selected disabled>Choose Qualification...</option>
                                        	<c:forEach items="${qualiList}" var="q">
                                        		<option ${ q == eauthor.qualification ? "selected" : "" }><c:out value="${q}" /></option>
                                        	</c:forEach>
                                        </select>
                                    </div>
                                    <div class="mb-3">
                                        <input class="form-control" type="file" name="profile" placeholder="Choose Profile image" title="Choose Profile image">
                                        <img src="" width="32" height="32" class="rounded output mt-3"/>
                                    </div>
                                    <c:if test="${ isEdit == false }">
                                    	<button type="submit" class="btn btn-primary btn-sm float-end">CREATE</button>
                                    </c:if>
                                    
                                    <c:if test="${ isEdit == true }">
                                    	<input type="hidden" name="oldFile" value="${eauthor.profile}">
                                    	<button type="submit" class="btn btn-success btn-sm float-end">UPDATE</button>
                                    	<a href="${ pageContext.request.contextPath}/index.jsp" class="ms-2 btn btn-secondary btn-sm float-end">CANCLE</a>
                                    </c:if>
                                </form>
                            </div>
                        </div>
                    </div>
                    <div class="col-6 col-md-9">
                        <div class="card shadow-sm">
                            <div class="card-body">
                                <!-- Alert Message -->
                                <c:if test="${msg != null }">
	                                <div class="alert <c:out value="${msg.cssClass}"/> alert-dismissible fade show" role="alert">
	                                    <strong><c:out value="${msg.status}"/>!</strong> <c:out value="${msg.msg}"/>
	                                    <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
	                                </div>
	                                <% session.removeAttribute("msg"); %>
                                </c:if>
                                <!-- End of Alert Message -->
                                <table class="table table-hover table-bordered">
                                    <thead>
                                        <tr>
                                            <th >Sr. no</th>
                                            <th >Username</th>
                                            <th >Email</th>
                                            <th >Phone</th>
                                            <th >Qualification</th>
                                            <th >Profile</th>
                                            <th >Created At</th>
                                            <th >Action</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach var="author" items="${authors}">
                                            <tr>
                                                <th>
                                                    ${authors.indexOf(author)+1}
                                                </th>
                                                <td>
                                                    <c:out value="${author.username}" />
                                                </td>
                                                <td> 
                                                	<c:out value="${author.email}" />
                                                </td>
                                                <td>
                                                   <c:out value="${author.phone}" />
                                                </td>
                                                <td>
                                                   <c:out value="${author.qualification}" />
                                                </td>
                                                <td>
                                                   <img src="${pageContext.request.contextPath}/upload/profile/${author.getProfile()}" width="32" height="32" class="rounded"/>
                                                </td>
                                                <td>
                                                   <c:out value="${author.createdAt}" />
                                                </td>
                                                <td class="text-center">
													<a href="?_a=index&e=${author.authorId }" class="btn btn-sm btn-primary" href="">EDIT</a>
													<form method="post" action="${pageContext.request.contextPath }/author?_a=delete" class="deleteForm">
														<input type="hidden" name="authorId" value="${author.authorId }">
														<button type="submit" class="btn btn-sm btn-danger">DELETE</button> 
													</form>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!-- end main container -->

            <!-- end footer scripts -->
            <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-p34f1UUtsS3wqzfto5wAAmdvj+osOnFyQFpp4Ua3gs/ZVWx6oOypYoCJhGGScy+8" crossorigin="anonymous"></script>
            <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
            <script src="https://cdn.datatables.net/1.10.24/js/jquery.dataTables.min.js"></script>
            <script src="https://cdn.datatables.net/1.10.24/js/dataTables.bootstrap5.min.js"></script>
            <script>
	            function readURL(input) {
	                console.log(input.files);
	                if(input.files && input.files[0]) {
	                    var reader = new FileReader();
	                    reader.onload = function (e) {
	                        $('img.output').attr('src', e.target.result);
	                    }
	                    reader.readAsDataURL(input.files[0]);
	                }
	            }
                $(document).ready(function (e) {
                    $("table").DataTable();
                    
                   $("form.deleteForm").on("submit",function(e){
                	  return confirm("Do you want to remove this?");
                   });
                   
                   $("input[type=file]").on('change',function(){ readURL(this) });
                });
            </script>
        </body>
    </html>