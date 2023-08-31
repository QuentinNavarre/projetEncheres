package fr.eni.projetencheres.dal.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.projetencheres.BusinessException;
import fr.eni.projetencheres.bll.ArticleBLL;
import fr.eni.projetencheres.bll.CategorieBLL;
import fr.eni.projetencheres.bll.EnchereArticleBLL;
import fr.eni.projetencheres.bo.Categorie;
import fr.eni.projetencheres.bo.EnchereArticle;

@WebServlet("/listeArticles")
public class ServletListeEnchere extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ArticleBLL articleBLL;
	EnchereArticleBLL enchereArticleBLL;
	CategorieBLL categorieBLL;

	@Override
	public void init() throws ServletException {
		enchereArticleBLL = new EnchereArticleBLL();
		categorieBLL = new CategorieBLL();
		articleBLL = new ArticleBLL();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<EnchereArticle> listeEnchereArticle = new ArrayList<EnchereArticle>();
		List<Categorie> listeCategorie = null;
		String categorie = (String) request.getParameter("selectCategory");
		String textArticle = (String) request.getParameter("textArticle");

		HttpSession session = request.getSession(false); // Récupération de la session sans création

		if (session != null && session.getAttribute("userId") != null) {
			// L'utilisateur est connecté
			String mestrucs = request.getParameter("mestrucs");
			String mesachatsouverts = request.getParameter("mesachatsouverts");
			String mesachatsencheres = request.getParameter("mesachatsencheres");
			String mesachatsencheresremporte = request.getParameter("mesachatsencheresremporte");
			String mesventesencours = request.getParameter("mesventesencours");
			String mesventesnondebutees = request.getParameter("mesventesnondebutees");
			String mesventesterminees = request.getParameter("mesventesterminees");
			if (mestrucs != null) {
				request.setAttribute("mestrucs", mestrucs);
				if (mestrucs.equals("mesachats")) {
					if (mesachatsouverts != null) {
						listeEnchereArticle = enchereArticleBLL.selectJoin();
						request.setAttribute("mesachatsouverts", mesachatsouverts);
					}

					if (mesachatsencheres != null) {
						try {
							listeEnchereArticle = enchereArticleBLL
									.selectJoinByUserEnchere(session.getAttribute("userId"));
							for (EnchereArticle enchereArticle : listeEnchereArticle) {
								// Code pour récupérer les informations de l'utilisateur
								// Si nécessaire, vous pouvez le faire ici
							}
							request.setAttribute("mesachatsencheres", mesachatsencheres);
						} catch (BusinessException e) {
							e.printStackTrace();
						}
					}

					if (mesachatsencheresremporte != null) {
						try {
							listeEnchereArticle = enchereArticleBLL
									.selectJoinByUserEnchereVD(session.getAttribute("userId"));
							for (EnchereArticle enchereArticle : listeEnchereArticle) {
								// Code pour récupérer les informations de l'utilisateur
								// Si nécessaire, vous pouvez le faire ici
							}
							request.setAttribute("mesachatsencheresremporte", mesachatsencheresremporte);
						} catch (BusinessException e) {
							e.printStackTrace();
						}
					}

				}

				if (mestrucs.equals("mesventes")) {
					List<EnchereArticle> listeEnchereArticleEncours = new ArrayList<EnchereArticle>();
					List<EnchereArticle> listeEnchereArticledebutees = new ArrayList<EnchereArticle>();
					List<EnchereArticle> listeEnchereArticleterminees = new ArrayList<EnchereArticle>();
					List<EnchereArticle> listeEnchereArticleByUser = null;
					listeEnchereArticleByUser = enchereArticleBLL.selectJoinByUser(session.getAttribute("userId"));

					if (mesventesencours != null) {
						listeEnchereArticleEncours = listeEnchereArticleByUser.stream()
								.filter(t -> t.getEtat_vente().contains("EC")).collect(Collectors.toList());
						listeEnchereArticle.addAll(listeEnchereArticleEncours);
						request.setAttribute("mesventesencours", mesventesencours);
					}

					if (mesventesnondebutees != null) {
						listeEnchereArticledebutees = listeEnchereArticleByUser.stream()
								.filter(t -> t.getEtat_vente().contains("CR")).collect(Collectors.toList());
						listeEnchereArticle.addAll(listeEnchereArticledebutees);
						request.setAttribute("mesventesnondebutees", mesventesnondebutees);
					}

					if (mesventesterminees != null) {
						listeEnchereArticleterminees = listeEnchereArticleByUser.stream()
								.filter(t -> t.getEtat_vente().contains("VD")).collect(Collectors.toList());
						listeEnchereArticle.addAll(listeEnchereArticleterminees);
						request.setAttribute("mesventesterminees", mesventesterminees);
					}
				}

				if (categorie != null || textArticle != null) {
					if (categorie == null || categorie.isEmpty()) {
						listeEnchereArticle = listeEnchereArticle.stream()
								.filter(t -> t.getNom_article().contains(textArticle)).collect(Collectors.toList());
						request.setAttribute("textArticle", textArticle);
					} else if (textArticle == null || textArticle.isEmpty()) {
						listeEnchereArticle = listeEnchereArticle.stream()
								.filter(t -> t.getCategorie().contains(categorie)).collect(Collectors.toList());
						request.setAttribute("categorie", categorie);
					} else {
						listeEnchereArticle = listeEnchereArticle.stream()
								.filter(t -> t.getNom_article().contains(textArticle)).collect(Collectors.toList());

						listeEnchereArticle = listeEnchereArticle.stream()
								.filter(t -> t.getCategorie().contains(categorie)).collect(Collectors.toList());
						request.setAttribute("textArticle", textArticle);
						request.setAttribute("categorie", categorie);
					}
				}
			} else {
				listeEnchereArticle = enchereArticleBLL.selectJoin();
				if (categorie != null || textArticle != null) {
					if (categorie == null || categorie.isEmpty()) {
						listeEnchereArticle = listeEnchereArticle.stream()
								.filter(t -> t.getNom_article().contains(textArticle)).collect(Collectors.toList());
						request.setAttribute("textArticle", textArticle);
					} else if (textArticle == null || textArticle.isEmpty()) {
						listeEnchereArticle = listeEnchereArticle.stream()
								.filter(t -> t.getCategorie().contains(categorie)).collect(Collectors.toList());
						request.setAttribute("categorie", categorie);
					} else {
						listeEnchereArticle = listeEnchereArticle.stream()
								.filter(t -> t.getNom_article().contains(textArticle)).collect(Collectors.toList());

						listeEnchereArticle = listeEnchereArticle.stream()
								.filter(t -> t.getCategorie().contains(categorie)).collect(Collectors.toList());
						request.setAttribute("textArticle", textArticle);
						request.setAttribute("categorie", categorie);
					}
				}
			}

		} else {
			// L'utilisateur n'est pas connecté
			if (categorie != null || textArticle != null) {
				if (categorie == null || categorie.isEmpty()) {
					listeEnchereArticle = enchereArticleBLL.selectJoinLike(textArticle);
					request.setAttribute("textArticle", textArticle);
				} else if (textArticle == null || textArticle.isEmpty()) {
					listeEnchereArticle = enchereArticleBLL.selectJoinCat(categorie);
					request.setAttribute("categorie", categorie);
				} else {
					listeEnchereArticle = enchereArticleBLL.selectJoinCatLike(categorie, textArticle);
					request.setAttribute("textArticle", textArticle);
					request.setAttribute("categorie", categorie);
				}
			} else {
				listeEnchereArticle = enchereArticleBLL.selectJoin();
			}
		}

		try {
			listeCategorie = categorieBLL.selectAll();
		} catch (BusinessException e) {
			// Gérer l'exception, par exemple en enregistrant un message d'erreur
			e.printStackTrace(); // À adapter en fonction de votre gestion d'erreurs
		}

		request.setAttribute("listeEnchereArticle", listeEnchereArticle);
		request.setAttribute("listeCategorie", listeCategorie);
		request.getRequestDispatcher("/WEB-INF/jsp/ListeEncheres.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
