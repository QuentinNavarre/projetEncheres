package fr.eni.projetencheres.dal.servlets;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.projetencheres.dal.ArticleDAO;
import fr.eni.projetencheres.dal.CategorieDAO;
import fr.eni.projetencheres.dal.DAOFactory;
import fr.eni.projetencheres.BusinessException;
import fr.eni.projetencheres.bll.UtilisateurManager;
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
		// récupération utilisateurId
		HttpSession session = request.getSession();
		String utilisateurConnecte = (String) session.getAttribute("identifiant");
		int ID = UtilisateurManager.getInstance().getID(utilisateurConnecte);
		
		String nomArticle = request.getParameter("nomarticle");
	    String description = request.getParameter("description");
	    Integer noCategorie = Integer.parseInt(request.getParameter("categorie"));
	    Integer miseAPrix = Integer.parseInt(request.getParameter("miseaprix"));
	    LocalDate dateDebut = LocalDate.parse(request.getParameter("datedebut"));
	    LocalDate dateFin = LocalDate.parse(request.getParameter("datefin"));
	    String rue = request.getParameter("rue");
	    String codePostal = request.getParameter("codepostal");
	    String ville = request.getParameter("ville");
	    
	    // récupération catégorie
	    CategorieDAO categorieDAO = DAOFactory.getCategorieDAO();
	    Categorie categorie = null;
		try {
			categorie = categorieDAO.getCategoriebyId(String.valueOf(noCategorie));
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	    Article article = new Article(nomArticle, description, dateDebut, dateFin, miseAPrix, 0, ID, noCategorie);
	    ArticleDAO articleDAO = DAOFactory.getArticleDAO();
	    try {
	        articleDAO.insertArticle(article, ID, categorie);
	        request.getSession().setAttribute("insertionReussie", true);
	        response.sendRedirect(request.getContextPath() + "/encheres");
	    
	    } catch (BusinessException e) {
	        e.printStackTrace();
	        request.setAttribute("listeCodesErreur", "L'ajout d'un nouvel a échoué. Veuillez réessayer.");
	        request.getRequestDispatcher("WEB-INF/jsp/nouvelleVente.jsp").forward(request, response);
	    }
        
     
        
        
	}


}
