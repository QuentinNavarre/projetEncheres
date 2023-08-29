package fr.eni.projetencheres.dal;

import fr.eni.projetencheres.dal.jdbc.UtilisateurDAOJdbcImpl;

public abstract class DAOFactory {
	
	public static UtilisateurDAO getUtilisateurDAO()
	{
		return new UtilisateurDAOJdbcImpl();
	}
	
	public static ArticleDAO getArticleDAO() {
		return new ArticleDAOImpl();
	}
}
