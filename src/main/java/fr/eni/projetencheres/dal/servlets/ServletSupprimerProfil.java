package fr.eni.projetencheres.dal.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.projetencheres.BusinessException;
import fr.eni.projetencheres.bll.UtilisateurManager;
import fr.eni.projetencheres.bo.Utilisateur;


@WebServlet("/SupprimerProfil")
public class ServletSupprimerProfil extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
	    String utilisateurConnecte = (String) session.getAttribute("identifiant");
	    
		try {
	        Utilisateur user = UtilisateurManager.getInstance().voirUtilisateur(utilisateurConnecte);

	       UtilisateurManager.getInstance().supprimerUtilisateur(user);
	       
	    // Déconnecter l'utilisateur après suppression (a voir de clem)
	       session.invalidate();
	        
	        response.sendRedirect(request.getContextPath() + "/encheres");
	        //request.getRequestDispatcher("/WEB-INF/jsp/monProfil.jsp").forward(request, response);
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		
	}

}
