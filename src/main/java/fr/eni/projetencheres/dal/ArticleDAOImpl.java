package fr.eni.projetencheres.dal;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import fr.eni.projetencheres.bo.Article;
import fr.eni.projetencheres.bo.Categorie;

public class ArticleDAOImpl implements ArticleDAO {
	private static final String INSERT = "INSERT INTO ARTICLES_VENDUS(nom_article, description, date_debut_encheres, "
			+ "date_fin_encheres, prix_initial, prix_vente, no_utilisateur, no_categorie) VALUES (?,?,?,?,?,?,?,?)";

	@Override
	public void insertArticle(Article article, int ID, Categorie categorie) {
		String requete = INSERT;

		try (Connection con = ConnectionProvider.getConnection()) {
			PreparedStatement stmt = con.prepareStatement(requete);

			stmt.setString(1, article.getNomArticle());
			stmt.setString(2, article.getDescription());
			stmt.setDate(3, Date.valueOf(article.getDateDebut()));
			stmt.setDate(4, Date.valueOf(article.getDateFin()));
			stmt.setInt(5, article.getPrixInitial());
			stmt.setInt(6, article.getPrixVente());
			stmt.setInt(7, ID);
			stmt.setInt(8, article.getNoCategorie());

			stmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();

		}
	}

	private static final String SELECT_BY_ID = "SELECT * FROM ARTICLES_VENDUS WHERE no_article = ?";

	@Override
	public Article selectById(int articleId) {
		Article article = null;
		String query = SELECT_BY_ID;

		try (Connection connection = ConnectionProvider.getConnection();
				PreparedStatement statement = connection.prepareStatement(query)) {

			statement.setInt(1, articleId);

			try (ResultSet resultSet = statement.executeQuery()) {
				if (resultSet.next()) {
					// Créer un nouvel objet Article en utilisant les données de la base de données
					article = new Article(resultSet.getInt("no_article"), resultSet.getString("nom_article"),
							resultSet.getString("description"), resultSet.getDate("date_debut_encheres").toLocalDate(),
							resultSet.getDate("date_fin_encheres").toLocalDate(), resultSet.getInt("prix_initial"),
							resultSet.getInt("prix_vente"), resultSet.getInt("no_utilisateur"),
							resultSet.getInt("no_categorie"));
				}
			}
		} catch (SQLException e) {
			// Gérer l'exception de manière appropriée, par exemple, en journalisant
			// l'erreur
			e.printStackTrace();
		}

		return article;
	}

	@Override
	public void delete(Integer noArticle) {
		// TODO Auto-generated method stub
		
	}

//
//	@Override
//	public void delete(Integer noArticle) {
//		String query = DELETE_ARTICLE;
//
//		try (Connection connection = ConnectionProvider.getConnection();
//				PreparedStatement statement = connection.prepareStatement(query)) {
//
//			statement.setInt(1, noArticle);
//			statement.executeUpdate();
//
//		} catch (SQLException e) {
//
//			e.printStackTrace();
//		}
//	}

}
