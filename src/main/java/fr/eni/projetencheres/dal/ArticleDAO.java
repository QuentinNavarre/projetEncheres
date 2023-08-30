package fr.eni.projetencheres.dal;

import fr.eni.projetencheres.BusinessException;
import fr.eni.projetencheres.bo.Article;
import fr.eni.projetencheres.bo.Categorie;

public interface ArticleDAO {
	public void insertArticle (Article article,int ID, Categorie categorie) throws BusinessException;
}
