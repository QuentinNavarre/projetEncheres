package fr.eni.projetencheres.dal.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.projetencheres.BusinessException;
import fr.eni.projetencheres.bll.UtilisateurManager;

@WebServlet("/SeConnecter")
public class ServletSeConnecter extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ServletSeConnecter() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("WEB-INF/jsp/projetEncheres.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String identifiant = request.getParameter("login");
		String mdp = request.getParameter("mdp");

		try {
			boolean authentifie = UtilisateurManager.getInstance().authentifier(identifiant, mdp);

			if (authentifie) {
				// L'utilisateur est authentifié, redirigez-le vers la page d'accueil
				response.sendRedirect("PageAccueil.jsp");
			} else {
				// L'authentification a échoué, renvoyez un message d'erreur à l'utilisateur
				request.setAttribute("erreur", "Nom d'utilisateur ou mot de passe incorrect");
				request.getRequestDispatcher("WEB-INF/jsp/projetEncheres.jsp").forward(request, response);
			}
		} catch (BusinessException e) {
			// Gérez l'exception BusinessException
			request.setAttribute("listeCodesErreur", e.getListeCodesErreur());
			request.getRequestDispatcher("WEB-INF/jsp/projetEncheres.jsp").forward(request, response);
		}
	}
}
