package fr.eni.projetencheres.bo;

public class Article {
	private Integer articleId;
	private String nomArticle;
	private String description;
	private String dateDebutEncheres;
	private String dateFinEncheres;
	private float prixInitial;
	private float prixVente;
	private Integer utilisateurId;
	private Integer categorieId;
	

	public Article(Integer articleId, String nomArticle, String description, String dateDebutEncheres, 
			String dateFinEncheres, float prixInitial, float prixVente, Integer utilisateurId, Integer categorieId) {
		super();
		this.articleId = articleId;
		this.nomArticle = nomArticle;
		this.description = description;
		this.dateDebutEncheres = dateDebutEncheres;
		this.dateFinEncheres = dateFinEncheres;
		this.prixInitial = prixInitial;
		this.prixVente = prixVente;
		this.utilisateurId = utilisateurId;
		this.categorieId = categorieId;
		}
	
	public Article(String nomArticle, String description, String dateDebutEncheres, 
			String dateFinEncheres, float prixInitial, float prixVente, Integer utilisateurId, Integer categorieId) {
		super();
		this.nomArticle = nomArticle;
		this.description = description;
		this.dateDebutEncheres = dateDebutEncheres;
		this.dateFinEncheres = dateFinEncheres;
		this.prixInitial = prixInitial;
		this.prixVente = prixVente;
		this.utilisateurId = utilisateurId;
		this.categorieId = categorieId;
		}

	public Integer getArticleId() {
		return articleId;
	}

	public void setArticleId(Integer articleId) {
		this.articleId = articleId;
	}

	public String getNomArticle() {
		return nomArticle;
	}

	public void setNomArticle(String nomArticle) {
		this.nomArticle = nomArticle;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDateDebutEncheres() {
		return dateDebutEncheres;
	}

	public void setDateDebutEncheres(String dateDebutEncheres) {
		this.dateDebutEncheres = dateDebutEncheres;
	}

	public String getDateFinEncheres() {
		return dateFinEncheres;
	}

	public void setDateFinEncheres(String dateFinEncheres) {
		this.dateFinEncheres = dateFinEncheres;
	}

	public float getPrixInitial() {
		return prixInitial;
	}

	public void setPrixInitial(float prixInitial) {
		this.prixInitial = prixInitial;
	}

	public float getPrixVente() {
		return prixVente;
	}

	public void setPrixVente(float prixVente) {
		this.prixVente = prixVente;
	}

	public Integer getUtilisateurId() {
		return utilisateurId;
	}

	public void setUtilisateurId(Integer utilisateurId) {
		this.utilisateurId = utilisateurId;
	}

	public Integer getCategorieId() {
		return categorieId;
	}

	public void setCategorieId(Integer categorieId) {
		this.categorieId = categorieId;
	}
	
}
	

