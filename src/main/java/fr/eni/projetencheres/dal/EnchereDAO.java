package fr.eni.projetencheres.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.eni.projetencheres.BusinessException;
import fr.eni.projetencheres.bo.Enchere;

public class EnchereDAO {

	public List<Enchere> getEncheresEnCours(String categorie, String nomArticle) throws BusinessException {
		// TODO: Gérer les exceptions BusinessException
		// TODO: Sécuriser la requête SQL avec des requêtes préparées
		List<Enchere> encheresEnCours = new ArrayList<>();

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
				Enchere enchere = mapResultSetToEnchere(resultSet);
				encheresEnCours.add(enchere);
			}
		} catch (SQLException e) {
			// TODO: Gérer les exceptions BusinessException ici
			e.printStackTrace();
		}
		return encheresEnCours;
	}

	public List<Enchere> getEncheresGagneesParUtilisateur(int idUtilisateur) throws BusinessException {
		// TODO: Gérer les exceptions BusinessException
		// TODO: Sécuriser la requête SQL avec des requêtes préparées
		List<Enchere> encheresGagnees = new ArrayList<>();

		try (Connection connection = ConnectionProvider.getConnection();
				PreparedStatement preparedStatement = connection
						.prepareStatement("SELECT * FROM encheres e " + "WHERE e.no_utilisateur_gagnant = ?")) {

			preparedStatement.setInt(1, idUtilisateur);

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				Enchere enchere = mapResultSetToEnchere(resultSet);
				encheresGagnees.add(enchere);
			}
		} catch (SQLException e) {
			// TODO: Gérer les exceptions BusinessException ici
			e.printStackTrace();
		}
		return encheresGagnees;
	}

	public List<Enchere> getEncheresOuvertesPourUtilisateur(int idUtilisateur) throws BusinessException {
		// TODO: Gérer les exceptions BusinessException
		// TODO: Sécuriser la requête SQL avec des requêtes préparées
		List<Enchere> encheresOuvertes = new ArrayList<>();

		try (Connection connection = ConnectionProvider.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(
						"SELECT * FROM encheres e " + "INNER JOIN articles_vendus a ON e.no_article = a.no_article "
								+ "WHERE e.date_enchere > NOW() " + "AND e.no_utilisateur = ?")) {

			preparedStatement.setInt(1, idUtilisateur);

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				Enchere enchere = mapResultSetToEnchere(resultSet);
				encheresOuvertes.add(enchere);
			}
		} catch (SQLException e) {
			// TODO: Gérer les exceptions BusinessException ici
			e.printStackTrace();
		}
		return encheresOuvertes;
	}

	public List<Enchere> filterEncheres(String categorie, String nomArticle, boolean achats, boolean ventes,
			boolean enchereOuverte, boolean mesEncheresEnCours, boolean mesEncheresRemportees, boolean mesVentesEnCours,
			boolean ventesNonDebutees, boolean ventesTerminees, int idUtilisateur) throws BusinessException {
		// TODO: Gérer les exceptions BusinessException
		// TODO: Sécuriser la requête SQL avec des requêtes préparées
		List<Enchere> encheres = new ArrayList<>();

		try (Connection connection = ConnectionProvider.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM encheres e "
						+ "INNER JOIN articles_vendus a ON e.no_article = a.no_article " + "WHERE 1 = 1 "
						+ "AND (:categorie IS NULL OR a.no_categorie = (SELECT no_categorie FROM categories WHERE libelle = :categorie)) "
						+ "AND (:nomArticle IS NULL OR a.nom_article LIKE :nomArticle) "
						+ "AND (:achats = 0 OR (e.no_utilisateur = ? AND e.date_enchere > NOW())) "
						+ "AND (:ventes = 0 OR (a.no_utilisateur = ? AND e.date_enchere > NOW())) "
						+ "AND (:enchereOuverte = 0 OR (e.date_enchere > NOW() AND e.no_utilisateur != ?)) "
						+ "AND (:mesEncheresEnCours = 0 OR (e.date_enchere > NOW() AND e.no_utilisateur = ?)) "
						+ "AND (:mesEncheresRemportees = 0 OR (e.date_enchere > NOW() AND e.no_utilisateur_gagnant = ?)) "
						+ "AND (:mesVentesEnCours = 0 OR (a.date_debut_encheres <= NOW() AND a.date_fin_encheres > NOW() AND a.no_utilisateur = ?)) "
						+ "AND (:ventesNonDebutees = 0 OR (a.date_debut_encheres > NOW() AND a.no_utilisateur = ?)) "
						+ "AND (:ventesTerminees = 0 OR (a.date_fin_encheres <= NOW() AND a.no_utilisateur = ?)))")) {

			preparedStatement.setString(1, categorie);
			preparedStatement.setString(2, "%" + nomArticle + "%");
			preparedStatement.setBoolean(3, achats);
			preparedStatement.setBoolean(4, ventes);
			preparedStatement.setBoolean(5, enchereOuverte);
			preparedStatement.setBoolean(6, mesEncheresEnCours);
			preparedStatement.setBoolean(7, mesEncheresRemportees);
			preparedStatement.setBoolean(8, mesVentesEnCours);
			preparedStatement.setBoolean(9, ventesNonDebutees);
			preparedStatement.setBoolean(10, ventesTerminees);
			preparedStatement.setInt(11, idUtilisateur);
			preparedStatement.setInt(12, idUtilisateur);
			preparedStatement.setInt(13, idUtilisateur);
			preparedStatement.setInt(14, idUtilisateur);
			preparedStatement.setInt(15, idUtilisateur);
			preparedStatement.setInt(16, idUtilisateur);
			preparedStatement.setInt(17, idUtilisateur);

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				Enchere enchere = mapResultSetToEnchere(resultSet);
				encheres.add(enchere);
			}
		} catch (SQLException e) {
			// TODO: Gérer les exceptions BusinessException ici
			e.printStackTrace();
		}
		return encheres;
	}

	// Méthode utilitaire pour mapper un ResultSet en Enchere
	private Enchere mapResultSetToEnchere(ResultSet resultSet) throws SQLException {
		Enchere enchere = new Enchere();
		enchere.setNoUtilisateur(resultSet.getInt("no_utilisateur"));
		enchere.setNoArticle(resultSet.getInt("no_article"));
		enchere.setDateEnchere(resultSet.getTimestamp("date_enchere"));
		enchere.setMontantEnchere(resultSet.getInt("montant_enchere"));
		return enchere;
	}

	public List<Enchere> getEncheresAuxquellesUtilisateurParticipe(int idUtilisateur) throws BusinessException {
		// TODO: Écrivez le code pour récupérer les enchères auxquelles l'utilisateur
		// participe
		// Assurez-vous de gérer les exceptions BusinessException ici
		List<Enchere> encheresParticipees = new ArrayList<>();

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

				encheresParticipees.add(enchere);
			}
		} catch (SQLException e) {
			// Gérer les exceptions BusinessException ici
			throw new BusinessException();
		}
		return encheresParticipees;
	}

}
