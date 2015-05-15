package it.av.users.dao;

import it.av.common.exception.GenericException;
import it.av.common.utils.DateUtil;
import it.av.users.model.User;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.authentication.encoding.MessageDigestPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

public class UserDaoJpa extends GenericJpaDAO<User> implements UserDao {

    @Autowired
    private MessageDigestPasswordEncoder passwordEncoder;
    @Autowired
    private UserProfileDao userProfileService;
    
    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public User add(User user) {
        if (user == null || StringUtils.isBlank(user.getEmail())) {
            throw new GenericException("User is null or email is empty");
        }
        user.setPasswordSalt(UUID.randomUUID().toString());
        user.setPassword(passwordEncoder.encodePassword(user.getPassword(), user.getPasswordSalt()));
        user.setCreationTime(DateUtil.getTimestamp());
        return super.save(user);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public User addOrUpdate(User user) {
        if (user == null || StringUtils.isBlank(user.getEmail())) {
            throw new GenericException("Vendor is null or email is empty");
        }
        if(StringUtils.isBlank(user.getId())){
            return add(user);
        }
        else{
            return update(user);
        }
    }

    
    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public User update(User object) {
        try {
            super.save(object);
        } catch (DataAccessException e) {
            throw new GenericException(e);
        }
        return object;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public User getByEmail(String email) {
        Criterion crit = Restrictions.eq(User.EMAIL, email);
        List<User> result = super.findByCriteria(crit);
        if (result != null && result.size() == 1) {
            return result.get(0);
        } else {
            return null;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Collection<User> find(String pattern) {
        Criterion critByName = Restrictions.ilike(User.LASTNAME, pattern);
        return findByCriteria(critByName);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isPasswordValid(String encPass, String rawPass, Object salt) {
        return passwordEncoder.isPasswordValid(encPass, rawPass, salt);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String encodePassword(String rawPass, Object salt) {
        return passwordEncoder.encodePassword(rawPass, salt);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public User setRandomPassword(User user) {
        user.setPassword(encodePassword(UUID.randomUUID().toString().substring(0, 8), user.getPasswordSalt()));
        return update(user);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int count() {
        Criteria criteria = getHibernateSession().createCriteria(getPersistentClass());
        criteria.setProjection(Projections.rowCount());
        return ((Long) criteria.uniqueResult()).intValue();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<User> find(String pattern, long firstResult, long maxResults, String sortField, boolean isAscending) {
        Criterion critByName = null;
        if(StringUtils.isNotBlank(pattern)){
            critByName = Restrictions.ilike(User.LASTNAME, pattern);
        }
        Order order = null;
        if(StringUtils.isNotBlank(sortField)){
            if(isAscending){
                order = Order.asc(sortField);
            }
            else{
                order = Order.desc(sortField);
            }            
        }
        return findByCriteria(order, (int)firstResult, (int)maxResults, critByName);
    }

    @Override
    public int count(String pattern) {
        Criteria criteria = getHibernateSession().createCriteria(getPersistentClass());
        Criterion critByName = Restrictions.ilike(User.LASTNAME, pattern);
        criteria.setProjection(Projections.rowCount());
        if(StringUtils.isNotBlank(pattern)){
            criteria.add(critByName);
        }
        return ((Long) criteria.uniqueResult()).intValue();
    }
    
}
