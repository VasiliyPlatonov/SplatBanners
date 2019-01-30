package com.vasiliyplatonov.splatwork.dao;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * Unified interface for managing persistent state of any domain
 *
 * @param <T> type of domain
 * @param <ID> type of primary key
 */
public interface GeneralDAO<T, ID extends Serializable> {

    /**
     * Saves a given entity. Use the returned instance for further operations as the save operation might have changed the
     * entity instance completely.
     *
     * @param entity
     * @return the saved entity
     */
    T save(T entity);

    /**
     * Saves all given entities.
     *
     * @param entities
     * @return count of the saved entities
     * @throws IllegalArgumentException in case the given entity is {@literal null}.
     */
    int save(List<T> entities);

    /**
     * Retrieves an entity by its id.
     *
     * @param id must not be {@literal null}.
     * @return the entity with the given id or {@literal null} if none found
     * @throws IllegalArgumentException if {@code id} is {@literal null}
     */
    T findOne(ID id);

    /**
     * Returns whether an entity with the given id exists.
     *
     * @param id must not be {@literal null}.
     * @return true if an entity with the given id exists, {@literal false} otherwise
     * @throws IllegalArgumentException if {@code id} is {@literal null}
     */
    boolean exists(ID id);

    /**
     * Returns all instances of the type.
     *
     * @return all entities
     */
    List<T> findAll();

    /**
     * Returns all instances of the type with the given IDs.
     *
     * @param ids
     * @return
     */
    List<T> findAll(List<ID> ids);

    /**
     * Returns the number of entities available.
     *
     * @return the number of entities
     */
    int count();

    /**
     * Deletes the entity with the given id.
     *
     * @param id must not be {@literal null}.
     * @throws IllegalArgumentException in case the given {@code id} is {@literal null}
     */
    void delete(ID id);

    /**
     * Deletes a given entity.
     *
     * @param entity
     * @throws IllegalArgumentException in case the given entity is {@literal null}.
     */
    void delete(T entity);

    /**
     * Deletes the given entities.
     *
     * @param entities
     * @throws IllegalArgumentException in case the given {@link List} is {@literal null}.
     */
    void delete(List<? extends T> entities);

    /**
     * Updates the entities with the given id
     *
     * @param entity  must not be {@literal null}
     * @throws IllegalArgumentException in case the given entity is {@literal null}.
     *
     * */
    void update(T entity) throws Exception;
}
