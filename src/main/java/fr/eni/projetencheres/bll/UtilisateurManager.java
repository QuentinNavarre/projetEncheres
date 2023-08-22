package fr.eni.projetencheres.bll;

import fr.eni.projetencheres.BusinessException;
import fr.eni.projetencheres.bo.Utilisateur;
import fr.eni.projetencheres.dal.DAOFactory;

// TODO import fr.eni.projetencheres.dal.UtilisateurDAO;
public class UtilisateurManager {

	private UtilisateurDAO utilisateurDao;
	private static UtilisateurManager instance;

	private UtilisateurManager() {
		utilisateurDAO = DAOFactory.getUtilisateurDAO();
	}

	public static UtilisateurManager getInstance() {

		if (instance == null) {
			instance = new UtilisateurManager();
		}
		return instance;
	}

	public void seConnecter(String identifiant, String mdp) throws BuisinessException {
		Utilisateur user = null;
		if (identifiant.contains("@"))
			user = utilisateurDAO.getUtilisateurByMail(identifiant);
		else
			user = utilisateurDAO.getUtilisateurByPseudo(identifiant);

		if (user == null || !mdp.equals(user.getMot_de_passe)) {
			BusinessException be = new BusinesseException();
			be.ajouterErreur(codeResultatBLL.IDENTIFANT_KO);
			throw be;
		}
	}
}
