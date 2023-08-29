package fr.eni.projetencheres.dal.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.projetencheres.BusinessException;
import fr.eni.projetencheres.bo.Enchere;
import fr.eni.projetencheres.dal.EnchereDAO;

@WebServlet("/ServletListeEnchere")
public class ServletListeEnchere extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			// Récupérer la session de l'utilisateur
			HttpSession session = request.getSession(false);

			// Vérifier si l'utilisateur est connecté
			if (session != null && session.getAttribute("userId") != null) {
				int idUtilisateur = (int) session.getAttribute("userId");
				List<Enchere> encheres = null;

				// Récupérer l'action à effectuer depuis les paramètres de la requête
				String action = request.getParameter("action");

				// Créer une instance de la classe EnchereDAO
				EnchereDAO enchereDAO = new EnchereDAO();

				// En fonction de l'action, effectuer la requête appropriée
				if (action == null || "toutes".equals(action)) {
					encheres = enchereDAO.filterEncheres(null, null, false, false, false, false, false, false, false,
							false, idUtilisateur);
					action = "toutes";
				} else if ("participe".equals(action)) {
					encheres = enchereDAO.getEncheresAuxquellesUtilisateurParticipe(idUtilisateur);
				} else if ("gagnees".equals(action)) {
					encheres = enchereDAO.getEncheresGagneesParUtilisateur(idUtilisateur);
				} else if ("encheresOuvertes".equals(action)) {
					encheres = enchereDAO.getEncheresOuvertesPourUtilisateur(idUtilisateur);
				}

				// Ajouter la liste d'enchères à la requête
				request.setAttribute("encheres", encheres);
			}

			// Rediriger vers la page ListeEncheres.jsp (quelle que soit la session)
			request.getRequestDispatcher("/WEB-INF/jsp/ListeEncheres.jsp").forward(request, response);

		} catch (BusinessException e) {
			// Gérer les exceptions BusinessException en redirigeant vers une page d'erreur
			// personnalisée
			request.setAttribute("errorMessage", e.getMessage());
			request.getRequestDispatcher("/WEB-INF/jsp/error.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
