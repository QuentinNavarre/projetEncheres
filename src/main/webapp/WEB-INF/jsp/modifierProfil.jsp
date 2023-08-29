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

	<%@ include file="/WEB-INF/fragments/nav.html" %>
	
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
    
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.min.js" integrity="sha384-Rx+T1VzGupg4BHQYs2gCW9It+akI2MM/mndMCy36UVfodzcJcF0GGLxZIzObiEfa" crossorigin="anonymous"></script>
</body>
</html>