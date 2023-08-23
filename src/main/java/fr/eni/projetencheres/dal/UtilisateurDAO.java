package fr.eni.projetencheres.dal;

import fr.eni.projetencheres.BusinessException;
import fr.eni.projetencheres.bo.Utilisateur;

public interface UtilisateurDAO {
	public Utilisateur getUtilisateurByPseudo(String pseudo)throws BusinessException;
	public Utilisateur getUtilisateurByMail(String mail) throws BusinessException;

}
