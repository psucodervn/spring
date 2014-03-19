package vn.uts.facebookgetfeed.dao;

public interface GenericDao<T> {
	
	T findOne(String id);
	
	void delete(T object);
	
	T save(T object);
	
	Long count();
	
	Iterable<T> findAll();
	
	boolean exist(String id);
}
