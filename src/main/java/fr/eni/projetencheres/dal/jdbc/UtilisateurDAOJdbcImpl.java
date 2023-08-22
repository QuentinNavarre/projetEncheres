package fr.eni.projetencheres.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import fr.eni.projetencheres.BusinessException;
import fr.eni.projetencheres.dal.CodesResultatDAL;

public class UtilisateurDAOJdbcImpl implements UtilisateurDAO {
	private static final String SELECT_USER_BY_PSEUDO = "SELECT no_utilisateur, pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur FROM UTILISATEURS WHERE pseudo = ?;";
	private static final String SELECT_USER_BY_EMAIL = "SELECT no_utilisateur, pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur FROM UTILISATEURS WHERE email = ?;";
	
	
	private Utilisateur getUtilisateurByLogin(String login, String requete) throws BusinessException {
		Utilisateur user = null;
		
		try(Connection cnx = ConnectionProvider.getConnection(); 
				PreparedStatement psmt = cnx.prepareStatement(requete)){
			
			//Remplacer "?" de " = ?"
			psmt.setString(1, login);
			
			//Exécuter requête
			try(ResultSet rs = psmt.executeQuery()){
				//Si le résultat est bon
				if(rs.next()) {
					user = new Utilisateur(rs.getInt("no_utilisateur"),
							rs.getString("pseudo"),
							rs.getString("nom"),
							rs.getString("prenom"),
							rs.getString("email"),
							rs.getString("telephone"),
							rs.getString("rue"),
							rs.getString("code_postal"),
							rs.getString("ville"),
							rs.getString("mot_de_passe"),
							rs.getInt("credit"),
							rs.getBoolean("administrateur"));
				}
				
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}catch(SQLException e) {
			BusinessException be = new BusinessException();
			be.ajouterErreur(CodesResultatDAL.SQL_EXCEPTION);
			e.printStackTrace();
			throw be;
		}
		return user;
	}
	
	@Override
	public Utilisateur getUtilisateurByMail(String mail) throws BusinessException {
		return getUtilisateurByLogin(mail, SELECT_USER_BY_MAIL);
	}
	
	@Override
	public Utilisateur getUtilisateurByPseudo(String pseudo) throws BusinessException {
		return getUtilisateurByLogin(pseudo, SELECT_USER_BY_PSEUDO);
	}

}
