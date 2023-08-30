package fr.eni.projetencheres.dal;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import fr.eni.projetencheres.BusinessException;
import fr.eni.projetencheres.bo.Article;
import fr.eni.projetencheres.bo.Categorie;
import fr.eni.projetencheres.bo.Utilisateur;

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
            stmt.setInt(8,article.getNoCategorie());
            
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();

        }
    }
}
