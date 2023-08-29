package fr.eni.projetencheres.dal.servlets;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.projetencheres.dal.ArticleDAO;
import fr.eni.projetencheres.dal.DAOFactory;
import fr.eni.projetencheres.dal.UtilisateurDAO;
import fr.eni.projetencheres.BusinessException;
import fr.eni.projetencheres.bo.*;

/**
 * Servlet implementation class ServletNouvelleVente
 */
@WebServlet("/NouvelleVente")
public class ServletNouvelleVente extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("WEB-INF/jsp/nouvelleVente.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    String nomArticle = request.getParameter("nomarticle");
	    String description = request.getParameter("description");
	    String categorie = request.getParameter("select");
	    Integer miseAPrix = Integer.parseInt(request.getParameter("miseaprix"));
	    LocalDate dateDebut = LocalDate.parse(request.getParameter("datedebut"));
	    LocalDate dateFin = LocalDate.parse(request.getParameter("datefin"));
	    String rue = request.getParameter("rue");
	    String codePostal = request.getParameter("codepostal");
	    String ville = request.getParameter("ville");

	    // récupération pseudo
        UtilisateurDAO utilisateurDAO = DAOFactory.getUtilisateurDAO();
        Utilisateur utilisateur = null;
		try {
			utilisateur = utilisateurDAO.getUtilisateurByPseudo("pseudo");
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        if (utilisateur != null) {
            // création d'un nouvel article
            Article article = new Article(nomArticle, description, dateDebut, dateFin, miseAPrix, 0, utilisateur.getUtilisateurId(), 0);


            // insertion de l'article
            ArticleDAO articleDAO = DAOFactory.getArticleDAO();
            try {
				articleDAO.insertArticle(article);
			} catch (BusinessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

 
            response.sendRedirect(request.getContextPath() + "/encheres");
        } else {
            // TODO : gérer les erreurs
            response.sendRedirect(request.getContextPath() + "/NouvelleVente");
				
	        
	        
	    }
	}

}
