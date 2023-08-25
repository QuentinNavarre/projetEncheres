<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<%@ include file="/WEB-INF/fragments/head.jsp" %>
<body>
	<nav class="navbar bg-body-tertiary">
		<div class="container-fluid">
			<span class="navbar-brand mb-0 h1">ENI-ENCHÈRES</span>
		</div>
	</nav>
	<div class="my-4 text-center">
	    <h1>Inscription</h1>
	    <form action="Inscription" method="post">
	        <label for="pseudo">Pseudo :</label>
	        <input type="text" id="pseudo" name="pseudo" pattern="^[a-zA-Z0-9]+$" required><br>
	        
	        <label for="nom">Nom :</label>
	        <input type="text" id="nom" name="nom" required><br>
	        
	        <label for="prenom">Prénom :</label>
	        <input type="text" id="prenom" name="prenom" required><br>
	        
	        <label for="email">Email :</label>
	        <input type="email" id="email" name="email" required><br>
	        
	        <label for="telephone">Téléphone :</label>
	        <input type="text" id="telephone" name="telephone"><br>
	
	        <label for="codePostal">Code Postal :</label>
	        <input type="text" id="codePostal" name="codePostal" required pattern="^\d{5}$"><br>
	
	        <label for="ville">Ville :</label>
	        <input type="text" id="ville" name="ville" required><br>
	
	        <label for="rue">Rue :</label>
	        <input type="text" id="rue" name="rue" required><br>
	
	        <label for="motDePasse">Mot de passe :</label>
	        <input type="password" id="motDePasse" name="motDePasse" required><br>
	
	        <label for="confirmationMotDePasse">Confirmer le mot de passe :</label>
	        <input type="password" id="confirmationMotDePasse" name="confirmationMotDePasse" required><br>
	
	        <input type="submit" value="Créer">
	        <input type="button" value="Annuler" onclick="window.location.href='${pageContext.request.contextPath}/encheres'">
	            
	        <%-- Affichage des erreurs --%>
	        <c:if test="${not empty erreurs}">
	            <div class="erreur">
	                <c:forEach var="erreur" items="${erreurs}">
	                    ${erreur}
	                </c:forEach>
	            </div>v 
	        </c:if>
	        <% String inscriptionErreur = (String) request.getAttribute("inscriptionErreur"); %>
	<% if (inscriptionErreur != null) { %>
	    <p><%= inscriptionErreur %></p>
	<% } %>
	        
	        <%-- Fin de l'affichage des erreurs --%>
	    </form>
	</div>
	<%@ include file="/WEB-INF/fragments/footer.html" %>
	
</body>
</html>
