package it.av.users.dao;

import it.av.users.model.User;

import java.util.Collection;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

public interface UserDao extends GenericDAO<User> {

    /**
     * Update a user
     * 
     * @param user
     * @return just updated user
     */
    User update(User user);

    /**
     * Add a new user, if the role is empty, it's used the USER role
     * 
     * @param user
     * @return just added user
     */
    User add(User vendor);

    /**
     * Return all the users
     * 
     * @return all the users
     */
    List<User> getAll();

    /**
     * Search users
     * 
     * @param pattern
     * @return found users
     */
    Collection<User> find(String pattern);
    
    /**
     * Search user
     * 
     * @param pattern
     * @param first first result
     * @param maxResults max number of result, 0 to disable 
     * @param sortField property name on which sort, NULL to disable
     * @param isAscending is ascending sort
     * @return found users
     */
    List<User> find(String pattern, long first, long maxResults, String sortField, boolean isAscending);

    /**
     * Remove the given user
     * 
     * @param user
     */
    @Transactional
    void remove(User user);

    /**
     * Return the user with this email, there is an unique constraint on the user email
     * 
     * @param email
     * @return user with the passed email
     */
    User getByEmail(String email);

    /**
     * Return the user by id
     * 
     * @param id
     * @return user with the passed email
     */
    User getByID(String id);


    /**
     * Takes a previously encoded password and compares it with a rawpassword after mixing in the salt and encoding that value
     * 
     * @param encPass previously encoded password
     * @param rawPass plain text password
     * @param salt salt to mix into password
     * @return true or false
     */
    boolean isPasswordValid(String encPass, String rawPass, Object salt);

    /**
     * Encodes the rawPass using a MessageDigest. If a salt is specified it will be merged with the password before encoding.
     * 
     * @param rawPass The plain text password
     * @param salt The salt to sprinkle
     * @return Hex string of password digest (or base64 encoded string if encodeHashAsBase64 is enabled.
     */
    String encodePassword(String rawPass, Object salt);

    /**
     * Set a random password for the given vendor, and save the vendor in the database
     * 
     * @param vendor
     * @return vendor with updated password
     */
    User setRandomPassword(User vendor);

    /**
     * count users in the DB
     * 
     * @return number of users
     */
    int count();
    
    /**
     * count users in the DB that match the given pattern.
     * Useful togheter with the find() 
     * 
     * @param pattern pattern to filter on
     * @return number of users
     */
    int count(String pattern);
    
    /**
     * Add as a rgolar user if not exists, otherwise updates
     * 
     * @param user
     * @return
     */
    User addOrUpdate(User user);
    
}
