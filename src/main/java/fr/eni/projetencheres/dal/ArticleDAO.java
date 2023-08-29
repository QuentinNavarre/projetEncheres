package fr.eni.projetencheres.dal;

import fr.eni.projetencheres.BusinessException;
import fr.eni.projetencheres.bo.Article;

public interface ArticleDAO {
	public void insertArticle (Article article, int ID) throws BusinessException;
}
