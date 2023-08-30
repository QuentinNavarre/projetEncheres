package fr.eni.projetencheres.dal;

import fr.eni.projetencheres.BusinessException;
import fr.eni.projetencheres.bo.Categorie;

public interface CategorieDAO {
	
	public Categorie getCategoriebyId(String id) throws BusinessException;
	

}
