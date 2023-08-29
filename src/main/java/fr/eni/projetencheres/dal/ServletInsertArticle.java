package fr.eni.projetencheres.dal;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.projetencheres.BusinessException;
import fr.eni.projetencheres.bll.UtilisateurManager;
import fr.eni.projetencheres.bo.Article;
import fr.eni.projetencheres.bo.Utilisateur;

/**
 * Servlet implementation class ServletInsertArticle
 */
@WebServlet("/InsertArticle")
public class ServletInsertArticle extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String utilisateurConnecte = (String) session.getAttribute("identifiant");
		try {
			Utilisateur user = UtilisateurManager.getInstance().voirUtilisateur(utilisateurConnecte);
			request.setAttribute("user", user);
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		@SuppressWarnings("unused")
		int ID = UtilisateurManager.getInstance().getID(utilisateurConnecte);
		
		request.getRequestDispatcher("/WEB-INF/jsp/testID.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
}

