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

			String action = request.getParameter("action"); // Ajoutez un paramètre "action" pour déterminer l'action de
															// l'utilisateur

			if ("participe".equals(action)) {
				HttpSession session = request.getSession(false);

				if (session != null && session.getAttribute("userId") != null) {
					int idUtilisateur = (int) session.getAttribute("userId");

					List<Enchere> encheresParticipe = enchereDAO
							.getEncheresAuxquellesUtilisateurParticipe(idUtilisateur);

					request.setAttribute("encheres", encheresParticipe);

					request.getRequestDispatcher("/WEB-INF/jsp/EncheresParticipe.jsp").forward(request, response);
				} else {
					response.sendRedirect("PageDeConnexion.jsp");
				}
			} else if ("gagnees".equals(action)) {
				int idUtilisateur = (int) request.getSession().getAttribute("userId");

				List<Enchere> encheresGagnees = enchereDAO.getEncheresGagneesParUtilisateur(idUtilisateur);

				request.setAttribute("encheres", encheresGagnees);

				request.getRequestDispatcher("/WEB-INF/jsp/EncheresGagnees.jsp").forward(request, response);
			}
			// TODO exception
		} catch (SQLException e) {
			e.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
