<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="fr.eni.projetencheres.messages.LecteurMessage"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>Affichage Utilisateur</title>
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
      <h1>Vous avez choisi l'utilisateur suivant :</h1>
	  <hr>
	 <c:if test="${not empty utilisateur}">
        <p><strong>Pseudo:</strong> <c:out value="${utilisateur.pseudo}" /></p>
        <p><strong>Nom:</strong> <c:out value="${utilisateur.nom}" /></p>
        <p><strong>Prénom:</strong> <c:out value="${utilisateur.prenom}" /></p>
        <p><strong>Email:</strong> <c:out value="${utilisateur.email}" /></p>
        <p><strong>Téléphone:</strong> <c:out value="${utilisateur.telephone}" /></p>
        <p><strong>Rue:</strong> <c:out value="${utilisateur.rue}" /></p>
        <p><strong>Code Postal:</strong> <c:out value="${utilisateur.code_postal}" /></p>
        <p><strong>Ville:</strong> <c:out value="${utilisateur.ville}" /></p>
    </c:if>
    <c:if test="${empty utilisateur}">
    	<p><strong>Ce pseudo n'existe pas</strong></p>
    </c:if>
    
    <a href="${pageContext.request.contextPath}/WEB_INF/jsp/accueil.jsp">Retour</a>
    
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.min.js" integrity="sha384-Rx+T1VzGupg4BHQYs2gCW9It+akI2MM/mndMCy36UVfodzcJcF0GGLxZIzObiEfa" crossorigin="anonymous"></script>
</body>
</html>