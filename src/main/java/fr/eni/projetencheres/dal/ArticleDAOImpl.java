package fr.eni.projetencheres.dal;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import fr.eni.projetencheres.bo.Article;

public class ArticleDAOImpl implements ArticleDAO {
	 private static final String INSERT = "INSERT INTO ARTICLES_VENDUS(nom_article, description, date_debut_encheres, "
	 		+ "date_fin_encheres, prix_initial, prix_vente, no_utilisateur, no_categorie) VALUES (?,?,?,?,?,?,?,?)";

	@Override
	public void insert(Article article) {
		try(Connection con = ConnectionProvider.getConnection()){
			PreparedStatement stmt = con.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
			
			stmt.setString(1, article.getNomArticle());
			stmt.setString(2, article.getDescription());
			stmt.setDate(3, Date.valueOf(article.getDateDebut()));
			stmt.setDate(4, Date.valueOf(article.getDateFin()));
            stmt.setInt(5, article.getPrixInitial());
            stmt.setInt(6, article.getPrixVente());
            stmt.setInt(7, article.getUtilisateurId());
            stmt.setInt(8, article.getNoCategorie());
            int nb = stmt.executeUpdate();
            if(nb>0){
            	ResultSet rss =stmt.getGeneratedKeys();
            	rss.next();
            	article.setNoArticle(rss.getInt(1));
            }
			
		} catch (SQLException e) {
			// TODO : gérer les exceptions 
			e.printStackTrace();
		}
		

	}

}
