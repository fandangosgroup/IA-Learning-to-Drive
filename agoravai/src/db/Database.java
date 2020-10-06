package db;

public interface Database<T> {
	public void insert(T obj);
	public void update(T obj);
	public void delete(Integer id);
}
