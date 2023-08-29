<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="fr.eni.projetencheres.messages.LecteurMessage"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<%@ include file="/WEB-INF/fragments/head.jsp" %>
<body>

	<%@ include file="/WEB-INF/fragments/nav.html" %>
	
	<c:if test="${not empty user}">
	<p>Profil de  ${user.pseudo}</p>
        <p><strong>Nom:</strong> <c:out value="${user.nom}" /></p>
        <p><strong>Prénom:</strong> <c:out value="${user.prenom}" /></p>
        <p><strong>Email:</strong> <c:out value="${user.email}" /></p>
        <p><strong>Téléphone:</strong> <c:out value="${user.telephone}" /></p>
        <p><strong>Rue:</strong> <c:out value="${user.rue}" /></p>
        <p><strong>Code Postal:</strong> <c:out value="${user.code_postal}" /></p>
        <p><strong>Ville:</strong> <c:out value="${user.ville}" /></p>
        <button onclick="window.location.href = '${pageContext.request.contextPath}/ModifierProfil';"> Modifier</button>
        <button onclick="window.location.href = '${pageContext.request.contextPath}/SupprimerProfil';"> Supprimer le compte</button>
    </c:if>

<%@ include file="/WEB-INF/fragments/footer.html" %>

</body>
</html>
