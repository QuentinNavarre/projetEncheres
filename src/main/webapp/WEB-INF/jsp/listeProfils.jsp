<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="fr.eni.projetencheres.messages.LecteurMessage"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>Liste des Profils</title>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
	<link href="style.css" rel="stylesheet">
</head>
<body>

	<nav class="navbar bg-body-tertiary">
		<div class="container-fluid">
			<span class="navbar-brand mb-0 h1"><a href="${pageContext.request.contextPath}/encheres"><img src="${pageContext.request.contextPath}/resources/logo.png" alt="Logo" class="img-fluid rounded-circle" style="max-width: 100px; max-height: 80px;"></a>			
			ENI-ENCHÈRES</span>
			<div class="d-flex justify-content-start">
				<c:if
					test="${empty sessionScope.identifiant && empty sessionScope.userLoggedIn }">
					<a href="${pageContext.request.contextPath}/SeConnecter">S'inscrire - Se connecter</a>
				</c:if>
				<c:if
					test="${ !empty sessionScope.identifiant && !empty sessionScope.userLoggedIn }">
					<p>Bonjour ${ sessionScope.identifiant }</p>
					<a href="${pageContext.request.contextPath}/ListeProfils">Liste Utilisateurs</a>
					<a href="#">Enchères</a>
					<a href="#">Vendre un article</a>
					<a href="${pageContext.request.contextPath}/MonProfil">Mon profil</a>
					<a href="${pageContext.request.contextPath}/TestServletDeconnexion">Se déconnecter</a>
				</c:if>
			</div>
		</div>
	</nav>
		<c:if
			test="${ !empty sessionScope.identifiant && !empty sessionScope.userLoggedIn }">
			<p>User : ${ sessionScope.identifiant }</p>
		</c:if>
      <h1>Liste des Utilisateurs inscrits</h1>
	  <hr>	 
	   <table>
	        <tr>
	            <th>Pseudo</th>
	            <th>Nom</th>
	            <th>Prénom</th>
	            <th>Email</th>
	            <th>Téléphone</th>
	            <th>Rue</th>
	            <th>Code Postal</th>
	            <th>Ville</th>
	        </tr>
	        
	        <c:forEach var="users" items="${usersList}">
	            <tr>
	                <td>${users.pseudo}</td>
	                <td>${users.nom}</td>
	                <td>${users.prenom}</td>
	                <td>${users.email}</td>
	                <td>${users.telephone}</td>
	                <td>${users.rue}</td>
	                <td>${users.code_postal}</td>
	                <td>${users.ville}</td>
	            </tr>
	        </c:forEach>
	    </table>
	    <br>
	  <p>Rentrer le pseudonyme de l'utilisateur dont vous souhaitez afficher spécifiquement le profil !</p>  
	  <form method="post">
			<label for="utilisateur">Utilisateur :&nbsp;</label>
			<input type="text" id="utilisateur" name="utilisateur" required/>
			<input type="submit" value="Rechercher" />
		</form>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.min.js" integrity="sha384-Rx+T1VzGupg4BHQYs2gCW9It+akI2MM/mndMCy36UVfodzcJcF0GGLxZIzObiEfa" crossorigin="anonymous"></script>
</body>
</html>