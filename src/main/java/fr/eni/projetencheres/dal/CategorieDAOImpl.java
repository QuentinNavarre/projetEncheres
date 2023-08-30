package fr.eni.projetencheres.dal;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.ResultSet;

import fr.eni.projetencheres.BusinessException;
import fr.eni.projetencheres.bo.Categorie;

public class CategorieDAOImpl implements CategorieDAO {

    private static final String SELECT_CATEGORIE_BY_ID = "SELECT no_categorie, libelle FROM CATEGORIES WHERE no_categorie = ?";

    @Override
    public Categorie getCategoriebyId(String id) throws BusinessException {
        Categorie categorie = null;

        try (Connection con = ConnectionProvider.getConnection();
             PreparedStatement stmt = con.prepareStatement(SELECT_CATEGORIE_BY_ID)) {

        	stmt.setInt(1, Integer.parseInt(id));

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    categorie = new Categorie(
                            rs.getInt("no_categorie"),
                            rs.getString("libelle")
                    );
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            BusinessException be = new BusinessException();
            be.ajouterErreur(CodesResultatDAL.SQL_EXCEPTION);
            e.printStackTrace();
            throw be;
        }

        return categorie;
    }
}
