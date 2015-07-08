package it.av.apps.dao.cargo;

import it.av.apps.dao.GenericDAO;
import it.av.apps.model.cargo.Customer;

import java.util.Collection;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

public interface CustomerDao extends GenericDAO<Customer> {

    /**
     * Update a Customer
     * 
     * @param Customer
     * @return just updated Customer
     */
    Customer update(Customer Customer);

    /**
     * Add a new Customer, if the role is empty, it's used the Customer role
     * 
     * @param Customer
     * @return just added Customer
     */
    Customer add(Customer vendor);

    /**
     * Return all the Customers
     * 
     * @return all the Customers
     */
    List<Customer> getAll();

    /**
     * Search Customers
     * 
     * @param pattern
     * @return found Customers
     */
    Collection<Customer> find(String pattern);
    
    /**
     * Search Customer
     * 
     * @param pattern
     * @param first first result
     * @param maxResults max number of result, 0 to disable 
     * @param sortField property name on which sort, NULL to disable
     * @param isAscending is ascending sort
     * @return found Customers
     */
    List<Customer> find(String pattern, long first, long maxResults, String sortField, boolean isAscending);

    /**
     * Remove the given Customer
     * 
     * @param Customer
     */
    @Transactional
    void remove(Customer Customer);


    /**
     * Return the Customer by id
     * 
     * @param id
     * @return 
     */
    Customer getByID(String id);


    /**
     * count Customers in the DB
     * 
     * @return number of Customers
     */
    int count();
    
    /**
     * count Customers in the DB that match the given pattern.
     * Useful togheter with the find() 
     * 
     * @param pattern pattern to filter on
     * @return number of Customers
     */
    int count(String pattern);
    
    /**
     * Add as a rgolar Customer if not exists, otherwise updates
     * 
     * @param Customer
     * @return
     */
    Customer addOrUpdate(Customer Customer);
    
}
