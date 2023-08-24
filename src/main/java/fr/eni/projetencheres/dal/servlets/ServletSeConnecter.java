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

@WebServlet("/SeConnecter")
public class ServletSeConnecter extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ServletSeConnecter() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("WEB-INF/jsp/projetEncheres.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String identifiant = request.getParameter("login");
		String mdp = request.getParameter("mdp");

		try {

			UtilisateurManager.getInstance().seConnecter(identifiant, mdp);
			HttpSession session = request.getSession();
			session.setAttribute("identifiant", identifiant);
			session.setAttribute("userLoggedIn", true);
			response.sendRedirect(request.getContextPath() + "/encheres");

		} catch (BusinessException e) {

			request.setAttribute("listeCodesErreur", e.getListeCodesErreur());
			request.getRequestDispatcher("WEB-INF/jsp/projetEncheres.jsp").forward(request, response);
		}
	}
}
