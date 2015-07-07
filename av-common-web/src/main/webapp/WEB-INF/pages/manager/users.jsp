<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html ng-app="aleniaApp" >
<head>
	<meta name="viewport" content="width=device-width, initial-scale=1"/>
	<jsp:include page="../common/common-head.jsp" />
</head>
<body>
  
<div class="container-fluid" ng-controller="ManagerUsersController">

<jsp:include page="../common/nav.jsp" />

<form  class="form-inline" role="form" >
  <div class="form-group">
    <label class="sr-only" for="searchUsers">cerca utente</label>
    <input type="text" class="form-control" id="searchUsers" placeholder="cerca...">
  </div>
  <button type="submit" class="btn btn-default">cerca</button>
  	
</form>


   	<div class="row">
   		<div class="col-md-12">
			<div class="table-responsive">
			  <table class="table table-striped table-condensed" ng-show="users">
			  <thead>
			  <tr>
			  <th>nome</th>
			  <th>cognome</th>
			  <th>nome utente</th>
			  <th>email</th>
			  </tr>
			  </thead>
			  <tbody>
			  <tr ng-repeat="u in users">
			  	<td>{{u.name}}</td>
			  	<td>{{u.surname}}</td>
			  	<td>{{u.username}}</td>
			  	<td>{{u.mail}}</td>
			  	<td><a href="/#todo/manager/user?id={{u.idusers}}" type="button" class="btn btn-default">
				todo
			  	</a></td>
			  </tr>
			  </tbody>
			  </table>
			</div>
   		</div>
   	</div>

</div>

<jsp:include page="../common/footer.jsp" />
  
  
</body>


</html>
