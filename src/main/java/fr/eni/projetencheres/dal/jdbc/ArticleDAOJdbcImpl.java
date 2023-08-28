package fr.eni.projetencheres.dal.jdbc;

public class ArticleDAOJdbcImpl {
	private static final String SELECT_ARTICLES_BY_CATEGORY = "SELECT no_article, nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, no_utilisateur, no_categorie FROM ARTICLES_VENDUS WHERE no_categorie = ?;";


}
