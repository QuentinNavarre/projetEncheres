<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>Accueil</title>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
</head>
<body>

	<nav class="navbar bg-body-tertiary">
		<div class="container-fluid">
			<span class="navbar-brand mb-0 h1">ENI-ENCHÈRES</span>
			<a href="<%=request.getContextPath()%>/SeConnecter">S'inscrire - Se connecter</a>
		</div>
				<div class="d-flex justify-content-end mt-2">
			
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

	
	<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.min.js" integrity="sha384-Rx+T1VzGupg4BHQYs2gCW9It+akI2MM/mndMCy36UVfodzcJcF0GGLxZIzObiEfa" crossorigin="anonymous"></script>
</body>
</html>