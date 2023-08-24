<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="fr.eni.projetencheres.messages.LecteurMessage"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
    <title>Affichage utilisateur</title>
</head>
<body>
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
    
		
</body>
</html>