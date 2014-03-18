package vn.uts.facebookgetfeed.dao;

public interface GenericDao<T> {
	T findById(String id);
	boolean delete(T object);
	boolean update(T object);
}
