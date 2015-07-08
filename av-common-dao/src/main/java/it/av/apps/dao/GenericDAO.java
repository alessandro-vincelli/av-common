package it.av.apps.dao;

import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;

/**
 * Common data access object operations
 * 
 * @author <a href='mailto:a.vincelli@gmail.com'>Alessandro Vincelli</a>
 * 
 */
public interface GenericDAO<T> {
    /**
     * Save a T item
     * 
     * @param item
     * @return just saved T item
     */
    T save(T item);

    /**
     * Return all the T item
     * 
     * @return all T item
     */
    List<T> getAll();

    /**
     * Remove the given T item
     * 
     * @param item
     */
    void remove(T item);

    /**
     * Get an item by ID
     * 
     * @param id
     * @return T item
     */
    T getByID(String id);

    /**
     * find using hibernate criteria
     * 
     * @param criterion
     * @return list of found objects
     */
    List<T> findByCriteria(Criterion... criterion);

    /**
     * find using hibernate criteria
     * 
     * @param order
     * @param firstResult
     * @param maxResults
     * @param criterion
     * @return list of found objects
     */
    List<T> findByCriteria(Order order, int firstResult, int maxResults, Criterion[] criterion);
}