package fr.eni.projetencheres.dal.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.projetencheres.bo.Enchere;
import fr.eni.projetencheres.dal.ListeEnchereDAO;

@WebServlet("/ServletListeEnchere")
public class ServletListeEnchere extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ServletListeEnchere() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			ListeEnchereDAO enchereDAO = new ListeEnchereDAO();
			String action = request.getParameter("action");
			HttpSession session = request.getSession(false);

			if (session != null && session.getAttribute("userId") != null) {
				int idUtilisateur = (int) session.getAttribute("userId");
				List<Enchere> encheres = null;

				if (action == null || "toutes".equals(action)) {
					encheres = enchereDAO.getToutesEncheres();
					action = "toutes";
				} else if ("participe".equals(action)) {
					encheres = enchereDAO.getEncheresAuxquellesUtilisateurParticipe(idUtilisateur);
				} else if ("gagnees".equals(action)) {
					encheres = enchereDAO.getEncheresGagneesParUtilisateur(idUtilisateur);
				} else if ("encheresOuvertes".equals(action)) {
					encheres = enchereDAO.getEncheresOuvertesPourUtilisateur(idUtilisateur);
				}

				request.setAttribute("encheres", encheres);

				request.getRequestDispatcher("/WEB-INF/jsp/ListeEncheres.jsp").forward(request, response);

			}
		} catch (SQLException e) {
			e.printStackTrace(); // TODO rediriger vers une page d'erreur personnalisée.
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
