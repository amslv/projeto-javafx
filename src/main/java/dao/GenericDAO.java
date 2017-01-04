package dao;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import database.DatabaseConnection;
import net.ravendb.client.IDocumentSession;
import net.ravendb.client.IDocumentStore;

public abstract class GenericDAO<T> {

	protected IDocumentStore store;
	protected IDocumentSession session;
	private final Class<T> persistentClass;
	
	@SuppressWarnings("unchecked")
	public GenericDAO() {
		store = DatabaseConnection.openConnection();
		session = store.openSession("easystock");
		this.persistentClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass())
				.getActualTypeArguments()[0];
	}
	
	public void save(T objeto) {
		session.store(objeto);
		session.saveChanges();
	}
	
	public abstract void update(T objeto);

	public void remove(T objeto) {
		session.delete(objeto);
		session.saveChanges();
	}
	
	public List<T> getAll() {
		return session.query(persistentClass).take(Integer.MAX_VALUE).toList();
	}
}
