<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="fr.eni.projetencheres.bo.Enchere"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<%@ include file="/WEB-INF/fragments/head.jsp"%>
<body>
	<header>

		<%@ include file="/WEB-INF/fragments/nav.html"%>
		<c:if test="${action == 'participe'}">
			<!-- Affichage spécifique pour l'action "participe" -->
    ...
</c:if>
		<c:if test="${action == 'gagnees'}">
			<!-- Affichage spécifique pour l'action "gagnees" -->
    ...
</c:if>
		<c:if test="${action == 'encheresOuvertes'}">
			<!-- Affichage spécifique pour l'action "encheresOuvertes" -->
    ...
</c:if>

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
