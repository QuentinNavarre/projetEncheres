<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<<<<<<< HEAD
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>Inscription</title>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
	<link href="style.css" rel="stylesheet">
</head>
<body>

	<nav class="navbar bg-body-tertiary">
		<div class="container-fluid">
			<span class="navbar-brand mb-0 h1"><a href="${pageContext.request.contextPath}/encheres"><img src="${pageContext.request.contextPath}/resources/logo.png" alt="Logo" class="img-fluid rounded-circle" style="max-width: 100px; max-height: 80px;"></a>			
			ENI-ENCHÈRES</span>
			<div class="d-flex justify-content-start">
			</div>
		</div>
	</nav>
</head>
=======
<%@ include file="/WEB-INF/fragments/head.jsp" %>
>>>>>>> branch 'main' of git@github.com:QuentinNavarre/projetEncheres.git
<body>
<<<<<<< HEAD
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
        <input type="button" value="Annuler" onclick="window.location.href='ServletAccueil';">
            
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
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.min.js" integrity="sha384-Rx+T1VzGupg4BHQYs2gCW9It+akI2MM/mndMCy36UVfodzcJcF0GGLxZIzObiEfa" crossorigin="anonymous"></script>
=======
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
	
>>>>>>> branch 'main' of git@github.com:QuentinNavarre/projetEncheres.git
</body>
</html>
