<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<%@ include file="/WEB-INF/fragments/head.jsp" %>

<body>

	<nav  class="navbar bg-secondary" data-bs-theme="dark">
		<div class="container-fluid">
			<span class="navbar-brand mb-0 h1"><a href="${pageContext.request.contextPath}/encheres"><img src="${pageContext.request.contextPath}/resources/logo.png" alt="Logo" class="img-fluid rounded-circle" style="max-width: 100px; max-height: 80px;"></a>			
			ENI-ENCHÈRES</span>
			<div class="d-flex justify-content-start">
				<c:if
					test="${empty sessionScope.identifiant && empty sessionScope.userLoggedIn }">
					<a href="${pageContext.request.contextPath}/SeConnecter" class="link-light link-offset-2 link-underline-opacity-25 link-underline-opacity-100-hover">S'inscrire - Se connecter</a>
				</c:if>
				<c:if
					test="${ !empty sessionScope.identifiant && !empty sessionScope.userLoggedIn }">
					<p class="text-white">Bonjour ${ sessionScope.identifiant }</p>
					<a href="${pageContext.request.contextPath}/ListeProfils" class="link-light link-offset-2 link-underline-opacity-25 link-underline-opacity-100-hover">Liste Utilisateurs</a>
					<a href="#" class="link-light link-offset-2 link-underline-opacity-25 link-underline-opacity-100-hover">Enchères</a>
					<a href="#" class="link-light link-offset-2 link-underline-opacity-25 link-underline-opacity-100-hover">Vendre un article</a>
					<a href="${pageContext.request.contextPath}/MonProfil" class="link-light link-offset-2 link-underline-opacity-25 link-underline-opacity-100-hover">Mon profil</a>
					<a href="${pageContext.request.contextPath}/TestServletDeconnexion" class="link-light link-offset-2 link-underline-opacity-25 link-underline-opacity-100-hover">Se déconnecter</a>
				</c:if>
			</div>
		</div>
	</nav>
	
	<c:if
		test="${ !empty sessionScope.identifiant && !empty sessionScope.userLoggedIn }">
	<div class="my-4 text-center">
	    <h1 class="text-secondary">Nouvelle vente</h1>
	</div>
	<div class="d-flex justify-content-center">
		
		<form action=NouvelleVente method="post" class="well form-horizontal">
			<div class="saisie">
				<label for="article">Article :</label>
	       		<input type="text" id="article" name="article" required>	
			</div>	
			<br>
			<div class="saisie">
				<label for="description" class="description-texte">Description : </label>
				<textarea rows="5" cols="30" id="description" name="description" required><%=request.getParameter("description")!=null?request.getParameter("description"):""%></textarea>
			</div>
			<br>
			<div class="saisie">
				<label for="categorie">Catégorie : </label>
				<select name="select" id="select" required>
					<option value="" disabled selected>Choisir une catégorie</option>
					<option value="informatique">Informatique</option>
					<option value="ameublemen">Ameublement</option>
					<option value="vêtement">Vêtement</option>
					<option value="sportLoisirs">Sport & Loisirs</option>
				</select>
			</div>
			<br>
			<div class="saisie">
				<label for="miseaprix">Mise à prix : </label> 
				<input type="number" name="miseaprix" id="miseaprix" min="0" required>
			</div>
			<br>
			<div class="saisie">
				<label for="datedebut">Début de l'enchère :</label>
				<input type="date" name="datedebut" id="datedebut" required value="<%=request.getParameter("datedebut")%>"/>
			</div>
			<br>
			<div class="saisie">
				<label for="datefin">Fin de l'enchère :</label>
				<input type="date" name="datefin" id="datefin" required value="<%=request.getParameter("datefin")%>"/>
			</div>
			<br>
			<div class="card">
  				<div class="card-body">
  					<h5 class="card-title text-secondary">Retrait</h5>
					<label for="rue">Rue :</label>
		       		<input type="text" id="rue" name="rue" required><br><br>
					<label for="codepostal">Code postal :</label>
		       		<input type="text" id="codepostal" name="codepostal" required><br><br>
					<label for="ville">Ville :</label>
		       		<input type="text" id="ville" name="ville" required><br>
            	</div>
            </div>
			<br>
			
			<div class="d-flex justify-content-evenly">
				<input type="submit" value="Enregistrer" class="btn btn-secondary"/>
				<input type="button"  class="btn btn-secondary" value="Annuler" onclick="window.location.href='${pageContext.request.contextPath}/encheres'">
			</div>
			<br>
		</form>
		
	</div>
	</c:if>
	<c:if
		test="${empty sessionScope.identifiant && empty sessionScope.userLoggedIn }">
		<div class="d-flex justify-content-center align-items-center vh-100">
			<div class="text-center">
				<h1 class="text-secondary">Nouvelle vente</h1>
				<p>Vous devez vous connecter pour avoir accès au contenu de
					cette page.</p>
			</div>
		</div>
	</c:if>

	<%@ include file="/WEB-INF/fragments/footer.html" %>
</body>
</html>