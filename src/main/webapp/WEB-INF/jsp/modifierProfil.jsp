<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="fr.eni.projetencheres.messages.LecteurMessage"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>Modification du Profil</title>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
	<link href="style.css" rel="stylesheet">
</head>
<body>

	<nav class="navbar bg-body-tertiary">
		<div class="container-fluid">
			<span class="navbar-brand mb-0 h1">ENI-ENCHÈRES</span>
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
	<c:if test="${not empty user}">
	<form action="ModifierProfil" method="post" accept-charset="UTF-8">
	<p>Modifier profil de  ${user.pseudo}</p>
        <label for="nom">Nom :</label>
        <input type="text" id="nom" name="nom" value="${user.nom}"><br>
        <label for="prenom">Prenom :</label>
        <input type="text" id="prenom" name="prenom" value="${user.prenom}"><br>
        <label for="email">Email :</label>
        <input type="text" id="email" name="email" value="${user.email}"><br>
        <label for="tel">Téléphone :</label>
        <input type="text" id="tel" name="tel" value="${user.telephone}"><br>
        <label for="rue">Rue :</label>
        <input type="text" id="rue" name="rue" value="${user.rue}"><br>
        <label for="cp">Code Postal :</label>
        <input type="text" id="cp" name="cp" value="${user.code_postal}"><br>
        <label for="ville">Ville :</label>
        <input type="text" id="ville" name="ville" value="${user.ville}"><br>
        <input type="submit" value="Confirmer">
        </form>
    </c:if>
