package fr.eni.projetencheres.dal.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.projetencheres.BusinessException;
import fr.eni.projetencheres.bll.UtilisateurManager;
import fr.eni.projetencheres.bo.Utilisateur;

/**
 * Servlet implementation class ServletChoixProfil
 */
@WebServlet("/ServletChoixProfil")
public class ServletChoixProfil extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ServletChoixProfil() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			request.getRequestDispatcher("WEB-INF/jsp/choixProfil.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String identifiant = request.getParameter("identifiant");
		String r = "SELECT no_utilisateur, pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur FROM UTILISATEURS WHERE pseudo ="+ identifiant;
		ResultSet rs = stmtbd.executeQuery(r);
		
		try {
			utilisateur.afficherUtilisateur();
			response.sendRedirect("https://www.google.com/");
		} catch (BusinessException e) {
			request.setAttribute("listeCodesErreur", e.getListeCodesErreur());
			request.getRequestDispatcher("WEB-INF/jsp/projetEncheres.jsp").forward(request, response);
		}
			
		PrintWriter out = response.getWriter();
		out.println("L'utilisateur choisi est : " + identifiant);
	}

}
