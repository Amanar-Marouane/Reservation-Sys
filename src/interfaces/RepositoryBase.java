package src.interfaces;

import java.util.List;

public interface RepositoryBase<T> {

    /**
     * Retrieve all entities.
     * 
     * @return list of all entities
     */
    List<T> all();

    /**
     * Find an entity by a given key/value pair.
     * 
     * @param key   the field/property name (e.g., "id", "email")
     * @param value the value to match
     * @return an Optional containing the entity if found, empty otherwise
     */
    T find(String key, Object value);

    /**
     * Save or update an entity in the repository.
     * 
     * @param entity the entity to save
     */
    void save(T entity);

    /**
     * Delete an entity from the repository by key/value.
     * 
     * @param key   the field/property name
     * @param value the value to match for deletion
     */
    void delete(String key, String value);
}
