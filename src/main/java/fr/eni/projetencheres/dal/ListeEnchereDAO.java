package fr.eni.projetencheres.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.eni.projetencheres.bo.Enchere;

public class ListeEnchereDAO {

	public List<Enchere> getEncheresEnCours(String categorie, String nomArticle) throws SQLException {
		List<Enchere> encheres = new ArrayList<>();

		try (Connection connection = ConnectionProvider.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM encheres e "
						+ "INNER JOIN articles_vendus a ON e.no_article = a.no_article "
						+ "WHERE e.date_enchere > NOW()"
						+ " AND (:categorie IS NULL OR a.no_categorie = (SELECT no_categorie FROM categories WHERE libelle = :categorie))"
						+ " AND (:nomArticle IS NULL OR a.nom_article LIKE :nomArticle)")) {

			preparedStatement.setString(1, categorie);
			preparedStatement.setString(2, "%" + nomArticle + "%");

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				Enchere enchere = new Enchere();
				enchere.setNoUtilisateur(resultSet.getInt("no_utilisateur"));
				enchere.setNoArticle(resultSet.getInt("no_article"));
				enchere.setDateEnchere(resultSet.getTimestamp("date_enchere"));
				enchere.setMontantEnchere(resultSet.getInt("montant_enchere"));

				encheres.add(enchere);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return encheres;
	}

	public List<Enchere> getEncheresAuxquellesUtilisateurParticipe(int idUtilisateur) throws SQLException {
		List<Enchere> encheresParticipe = new ArrayList<>();
		try (Connection connection = ConnectionProvider.getConnection();
				PreparedStatement preparedStatement = connection
						.prepareStatement("SELECT * FROM encheres e " + "WHERE e.no_utilisateur = ?")) {

			preparedStatement.setInt(1, idUtilisateur);

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				Enchere enchere = new Enchere();
				enchere.setNoUtilisateur(resultSet.getInt("no_utilisateur"));
				enchere.setNoArticle(resultSet.getInt("no_article"));
				enchere.setDateEnchere(resultSet.getTimestamp("date_enchere"));
				enchere.setMontantEnchere(resultSet.getInt("montant_enchere"));

				encheresParticipe.add(enchere);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return encheresParticipe;
	}

	public List<Enchere> getEncheresGagneesParUtilisateur(int idUtilisateur) throws SQLException {
		List<Enchere> encheresGagnees = new ArrayList<>();
		try (Connection connection = ConnectionProvider.getConnection();
				PreparedStatement preparedStatement = connection
						.prepareStatement("SELECT * FROM encheres e " + "WHERE e.no_utilisateur_gagnant = ?")) {

			preparedStatement.setInt(1, idUtilisateur);

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				Enchere enchere = new Enchere();
				enchere.setNoUtilisateur(resultSet.getInt("no_utilisateur"));
				enchere.setNoArticle(resultSet.getInt("no_article"));
				enchere.setDateEnchere(resultSet.getTimestamp("date_enchere"));
				enchere.setMontantEnchere(resultSet.getInt("montant_enchere"));

				encheresGagnees.add(enchere);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return encheresGagnees;
	}
}
