package fr.eni.projetencheres.dal;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.projetencheres.BusinessException;
import fr.eni.projetencheres.bo.*;

/**
 * Servlet implementation class TestInsertionArticleServlet
 */
@WebServlet("/TestInsertionArticleServlet")
public class TestInsertionArticleServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArticleDAO articleDAO = DAOFactory.getArticleDAO();
        UtilisateurDAO utilisateurDAO = DAOFactory.getUtilisateurDAO();

        // Récupérer un utilisateur par son ID (par exemple, 24)
        Utilisateur utilisateur = null;
        try {
            utilisateur = utilisateurDAO.getUtilisateurById(23); // Remplacez 24 par l'ID d'un utilisateur existant
        } catch (BusinessException e) {
            e.printStackTrace();
        }

        if (utilisateur != null) {
            // Créer un nouvel article avec les valeurs souhaitées
            Article article = new Article("Chaise", "Une superbe chaise",
                    LocalDate.now(), LocalDate.now().plusDays(7),
                    100, 0, utilisateur.getUtilisateurId(), 1); // Remplacez 1 par le no_categorie souhaité

            // Insérer l'article dans la base de données
            try {
                articleDAO.insertArticle(article);
            } catch (BusinessException e) {
                e.printStackTrace();
            }

            // Vérifier si l'article a été inséré avec succès
            if (article.getNoArticle() != null) {
                System.out.println("Article inséré avec succès. NoArticle : " + article.getNoArticle());
            } else {
                System.out.println("L'insertion de l'article a échoué.");
            }
        } else {
            System.out.println("Utilisateur non trouvé.");
        }
    }
}
