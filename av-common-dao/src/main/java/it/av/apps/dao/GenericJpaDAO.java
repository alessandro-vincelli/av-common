package it.av.apps.dao;

import it.av.apps.model.BasicEntity;
import it.av.common.exception.GenericException;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * @author <a href='mailto:a.vincelli@gmail.com'>Alessandro Vincelli</a>
 * 
 * @param <T>
 */
@Repository
public class GenericJpaDAO<T extends BasicEntity> implements GenericDAO<T> {

    /**
     * @param entityManager
     */
    @PersistenceContext(type = PersistenceContextType.TRANSACTION, unitName = "daoPersistence")
    private EntityManager em;

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public T save(T obj) {
        if (obj == null) {
            throw new GenericException("Object is null");
        }

        try {
            if (obj.getId() != null && !obj.getId().isEmpty()) {
                return (em.merge(obj));
            } else {
                em.persist(obj);
            }
            return obj;
        } catch (OptimisticLockingFailureException e) {
            throw new GenericException(e);
        } catch (DataAccessException e) {
            throw new GenericException(e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public List<T> getAll() {
        return findByCriteria();
    }

    /**
     * {@inheritDoc}
     */
    @Deprecated
    public List<T> findFullText(String query) {
        throw new GenericException("not implememted yet");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void remove(T object) {
        try {
            T objectToRemove = getByID(object.getId());
            em.remove(objectToRemove);
        } catch (DataAccessException e) {
            throw new GenericException(e);
        }
    }

    protected final Class<T> getPersistentClass() {
        return (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public List<T> findByCriteria(Criterion... criterion) {
        return findByCriteria(getPersistentClass(), null, 0, 0, criterion);
    }

    @Override
    @Transactional(readOnly = true)
    public List<T> findByCriteria(Order order, int firstResult, int maxResults, Criterion... criterion) {
        return findByCriteria(getPersistentClass(), order, firstResult, maxResults, criterion);
    }

    @Transactional(readOnly = true)
    protected List<T> findByCriteria(Order order, Criterion... criterion) {
        return findByCriteria(getPersistentClass(), order, 0, 0, criterion);
    }

    @Transactional(readOnly = true)
    protected List<T> findByCriteria(Class<T> actualClass, Order order, int firstResult, int maxResults, Criterion... criterion) {
        Criteria criteria = getHibernateSession().createCriteria(getPersistentClass());
        if (order != null) {
            criteria.addOrder(order);
        }
        for (Criterion c : criterion) {
            if (c != null) {
                criteria.add(c);
            }
        }
        if (firstResult > 0) {
            criteria.setFirstResult(firstResult);
        }
        if (maxResults > 0) {
            criteria.setMaxResults(maxResults);
        }
        return criteria.list();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public T getByID(String id) {
        return em.find(getPersistentClass(), id);
    }

    protected final Session getHibernateSession() {
        return (Session) em.getDelegate();
    }
}