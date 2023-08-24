<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="fr.eni.projetencheres.messages.LecteurMessage"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
    <title>Liste des Utilisateurs</title>
</head>
<body>
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
</body>
</html>