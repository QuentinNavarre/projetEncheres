package fr.eni.projetencheres.dal;

import fr.eni.projetencheres.BusinessException;
import fr.eni.projetencheres.bo.Article;
import fr.eni.projetencheres.bo.Categorie;

public interface ArticleDAO {
	void insertArticle(Article article, int categorieId, Categorie categorie) throws BusinessException;

	Article selectById(int articleId) throws BusinessException;

	void delete(Integer noArticle);
}


