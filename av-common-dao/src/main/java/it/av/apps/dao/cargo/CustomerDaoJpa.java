package it.av.apps.dao.cargo;

import it.av.apps.dao.GenericJpaDAO;
import it.av.apps.model.cargo.Customer;
import it.av.common.exception.GenericException;

import java.util.Collection;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Transactional;

public class CustomerDaoJpa extends GenericJpaDAO<Customer> implements CustomerDao {

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public Customer add(Customer Customer) {
        return super.save(Customer);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public Customer addOrUpdate(Customer Customer) {
        if (StringUtils.isBlank(Customer.getId())) {
            return add(Customer);
        } else {
            return update(Customer);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public Customer update(Customer object) {
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
    public Collection<Customer> find(String pattern) {
        Criterion critByName = Restrictions.ilike(Customer.COMPANY_NAME, pattern, MatchMode.START);
        return findByCriteria(critByName);
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
    public List<Customer> find(String pattern, long firstResult, long maxResults, String sortField, boolean isAscending) {
        Criterion critByName = null;
        if (StringUtils.isNotBlank(pattern)) {
            critByName = Restrictions.ilike(Customer.COMPANY_NAME, pattern, MatchMode.START);
        }
        Order order = null;
        if (StringUtils.isNotBlank(sortField)) {
            if (isAscending) {
                order = Order.asc(sortField);
            } else {
                order = Order.desc(sortField);
            }
        }
        return findByCriteria(order, (int) firstResult, (int) maxResults, critByName);
    }

    @Override
    public int count(String pattern) {
        Criteria criteria = getHibernateSession().createCriteria(getPersistentClass());
        Criterion critByName = Restrictions.ilike(Customer.COMPANY_NAME, pattern, MatchMode.START);
        criteria.setProjection(Projections.rowCount());
        if (StringUtils.isNotBlank(pattern)) {
            criteria.add(critByName);
        }
        return ((Long) criteria.uniqueResult()).intValue();
    }

}
