package fr.eni.projetencheres.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import fr.eni.projetencheres.BusinessException;
import fr.eni.projetencheres.bo.Article;
import fr.eni.projetencheres.bo.Categorie;

public class ArticleDAOJdbc implements ArticleDAO {

	private static final String SELECT_ARTICLE_BY_ID_SQL = "SELECT * FROM articles WHERE id = ?";

	public Article selectById(int articleId) throws BusinessException {
		Article article = null;
		try (Connection connection = DataSourceProvider.getConnection()) {
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ARTICLE_BY_ID_SQL);
			preparedStatement.setInt(1, articleId);
			ResultSet resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				int id = resultSet.getInt("id");
				String nom = resultSet.getString("nom");
				String description = resultSet.getString("description");
				int prixInitial = resultSet.getInt("prix_initial");
				int categorieId = resultSet.getInt("categorie_id");

				article = new Article(id, nom, description, null, null, prixInitial, null, null, categorieId);
			}
		} catch (SQLException e) {

			throw new BusinessException();
		}

		return article;
	}

	@Override
	public void insertArticle(Article article, int categorieId, Categorie categorie) throws BusinessException {
		// Implémentez cette méthode pour ajouter un article à la base de données
	}

	@Override
	public void delete(Integer noArticle) {
		// Implémentez cette méthode pour supprimer un article de la base de données
	}
}
