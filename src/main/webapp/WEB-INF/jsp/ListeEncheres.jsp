<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="fr.eni.projetencheres.bo.Enchere"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Liste des Enchères</title>
</head>
<body>
	<header>
		<nav class="navbar bg-body-tertiary">
			<div class="container-fluid">
				<span class="navbar-brand mb-0 h1"><a
					href="${pageContext.request.contextPath}/encheres"><img
						src="${pageContext.request.contextPath}/resources/logo.png"
						alt="Logo" class="img-fluid rounded-circle"
						style="max-width: 100px; max-height: 80px;"></a> ENI-ENCHÈRES</span>
				<div class="d-flex justify-content-start">
					<c:if
						test="${empty sessionScope.identifiant && empty sessionScope.userLoggedIn }">
						<a href="${pageContext.request.contextPath}/SeConnecter">S'inscrire
							- Se connecter</a>
					</c:if>
					<c:if
						test="${ !empty sessionScope.identifiant && !empty sessionScope.userLoggedIn }">
						<p>Bonjour ${ sessionScope.identifiant }</p>
						<a href="${pageContext.request.contextPath}/ListeProfils">Liste
							Utilisateurs</a>
						<a href="#">Enchères</a>
						<a href="#">Vendre un article</a>
						<a href="${pageContext.request.contextPath}/MonProfil">Mon
							profil</a>
						<a
							href="${pageContext.request.contextPath}/TestServletDeconnexion">Se
							déconnecter</a>
					</c:if>
				</div>
			</div>
		</nav>

		<h1>Liste des Enchères en Cours</h1>
	</header>
	<div>
		<form action="ServletListeEnchere" method="get">
			<input type="text" name="filtre"
				placeholder="Rechercher par nom d'article"> <select
				name="categorie">
				<option value="" disabled selected>Choisir une catégorie</option>
				<option value="informatique">Informatique</option>
				<option value="ameublement">Ameublement</option>
				<option value="vetement">Vêtement</option>
				<option value="sportLoisirs">Sport & Loisirs</option>
			</select>
			<button type="submit">Rechercher</button>
		</form>
	</div>
	<table>
		<tr>
			<th>Nom de l'article</th>
			<th>Prix actuel</th>
			<th>Date de fin</th>
		</tr>
		<c:forEach var="enchere" items="${encheres}">
			<tr>
				<td>${enchere.nomArticle}</td>
				<td>${enchere.montantEnchere}</td>
				<td>${enchere.dateEnchere}</td>
			</tr>
		</c:forEach>
		<%@ include file="/WEB-INF/fragments/footer.html"%>
</body>
</html>
