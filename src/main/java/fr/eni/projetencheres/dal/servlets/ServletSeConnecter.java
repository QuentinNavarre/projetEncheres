package fr.eni.projetencheres.dal.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import fr.eni.projetencheres.BusinessException;
import fr.eni.projetencheres.bll.CodesResultatBLL;
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
		String hashedPassword = mdp;

		try {
			
			
			System.out.println("Tentative de connexion avec identifiant : " + identifiant);
	        System.out.println("Mot de passe en clair : " + mdp);
	        System.out.println("Mot de passe haché : " + hashedPassword);


			// Vérifiez l'authentification en utilisant le mot de passe haché
			if (UtilisateurManager.getInstance().authentifier(identifiant, hashedPassword)) {
				HttpSession session = request.getSession();
				session.setAttribute("identifiant", identifiant);
				session.setAttribute("userLoggedIn", true);

				String rememberMe = request.getParameter("rememberMe");

				// Vérifiez si la case a été cochée
				if ("on".equals(rememberMe)) {
					// Créez un cookie pour se souvenir du nom d'utilisateur
					Cookie cookie = new Cookie("rememberedUser", identifiant);
					cookie.setMaxAge(30 * 24 * 60 * 60); // Le cookie expirera après 30 jours
					response.addCookie(cookie);
				}

				// Vérifiez si un cookie "rememberedUser" existe
				Cookie[] cookies = request.getCookies();
				String rememberedUser = null;
				if (cookies != null) {
					for (Cookie cookie : cookies) {
						if ("rememberedUser".equals(cookie.getName())) {
							rememberedUser = cookie.getValue();
							break;
						}
					}
				}

				// Si un nom d'utilisateur est mémorisé, remplissez automatiquement le champ du formulaire
		
				if (rememberedUser != null) {
					request.setAttribute("rememberedUser", rememberedUser);
					System.out.println("Nom d'utilisateur mémorisé : " + rememberedUser);
				}

				// Réinitialisez le champ de mot de passe pour la sécurité
				request.setAttribute("mdp", "******");

				response.sendRedirect(request.getContextPath() + "/encheres");
			} else {
				System.out.println("Authentification échouée pour : " + identifiant);
				// Authentification échouée
				BusinessException be = new BusinessException();
				be.ajouterErreur(CodesResultatBLL.IDENTIFIANT_KO);
				throw be;
			}
		} catch (BusinessException e) {
			System.out.println("Exception BusinessException : " + e.getMessage());
			// Gérez l'exception BusinessException
			request.setAttribute("listeCodesErreur", e.getListeCodesErreur());
			request.getRequestDispatcher("WEB-INF/jsp/projetEncheres.jsp").forward(request, response);
		}
	}
}
