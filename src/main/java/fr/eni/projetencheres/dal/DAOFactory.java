package fr.eni.projetencheres.dal;

public abstract class DAOFactory {
	
	public static ListeCourseDAO getListeCourseDAO()
	{
		return new ListeCourseDAOJdbcImpl();
	}
}
	