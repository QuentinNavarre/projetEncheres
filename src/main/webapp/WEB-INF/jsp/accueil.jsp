<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<%@ include file="/WEB-INF/fragments/head.jsp" %>

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

	<header>
		<div class="my-4 text-center">
			<h1>Liste des enchères</h1>
		</div>
	</header>

	<div class="container">
		<form>
			<input type="text" id="filtrer" name="filtrer" placeholder="Je recherche..." ><br><br>
			<select name="select" id="select">
				<option value="" disabled selected>Choisir une catégorie</option>
				<option value="informatique">Informatique</option>
				<option value="ameublemen">Ameublement</option>
				<option value="vêtement">Vêtement</option>
				<option value="sportLoisirs">Sport & Loisirs</option>
			</select><br><br>
			<button type="submit">Rechercher</button>
			
		</form>
	</div>
	
	
	<br>

	<c:if test="${ !empty sessionScope.identifiant && !empty sessionScope.userLoggedIn }">
		<div class="container">
			 <div class="row justify-content-start">
				<div class="col-4">
					<input type="radio" name="filtres" id="achats" value="achats" onclick="onclickAchats()">
					<label for="encheresencours">Achats</label><br>

					<input type="checkbox" name="encheresouvertes" id="encheresouvertes">
					<label for="encheresencours">Enchères ouvertes</label><br>
					<input type="checkbox" name="encheresencours" id="encheresencours">
					<label for="encheresencours">Mes enchères en cours</label><br>
					<input type="checkbox" name="encheresremportees" id="encheresremportees">
					<label for="encheresremportees">Mes enchères remportées</label>	
				</div>		
				<div class="col-4">
				<input type="radio" name="filtres" id="ventes" value="ventes" onclick="onclickVentes()">
				<label for="ventes">Mes ventes</label><br>

					<input type="checkbox" name="ventesencours" id="ventesencours">
					<label for="ventesencours">Mes ventes en cours</label><br>
					<input type="checkbox" name="ventesnondebutees" id="ventesnondebutees">
					<label for="ventesnondebutees">Ventes non débutées</label><br>
					<input type="checkbox" name="ventesterminees" id="ventesterminees">
					<label for="ventesterminees">Ventes terminéees</label>	
				</div>
			</div>
		</div>
	</c:if>

	<script type="application/javascript">
		
    function onclickAchats(){
        let ventesencours = document.getElementById("ventesencours");
        ventesencours.checked = false;
        ventesencours.disabled = true;
        let ventesnondebutees = document.getElementById("ventesnondebutees");
        ventesnondebutees.checked = false;
        ventesnondebutees.disabled = true;
        let ventesterminees = document.getElementById("ventesterminees");
        ventesterminees.checked = false;
        ventesterminees.disabled = true;
        
        let encheresouvertes = document.getElementById("encheresouvertes");
        encheresouvertes.disabled = false;
        let encheresencours = document.getElementById("encheresencours");
        encheresencours.disabled = false;
        let encheresremportees = document.getElementById("encheresremportees");
        encheresremportees.disabled = false;
    }
    function onclickVentes(){
        let encheresouvertes = document.getElementById("encheresouvertes");
        encheresouvertes.checked = false;
        encheresouvertes.disabled = true;
        let encheresencours = document.getElementById("encheresencours");
        encheresencours.checked = false;
        encheresencours.disabled = true;
        let encheresremportees = document.getElementById("encheresremportees");
        encheresremportees.checked = false;
        encheresremportees.disabled = true;
        
        let ventesencours = document.getElementById("ventesencours");
        ventesencours.disabled = false;
        let ventesnondebutees = document.getElementById("ventesnondebutees");
        ventesnondebutees.disabled = false;
        let ventesterminees = document.getElementById("ventesterminees");
        ventesterminees.disabled = false;
    }
	</script>


	<%@ include file="/WEB-INF/fragments/footer.html" %>
</body>
</html>