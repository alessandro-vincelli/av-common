package it.av.apps.dao;

import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import it.av.apps.model.Country;

public class CountryDaoJpa extends GenericJpaDAO<Country>implements CountryDao {

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Country> getAll() {
        Order orderBYName = Order.asc(Country.NAME);
        return super.findByCriteria(orderBYName);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Country getByIso2(String iso2) {
        Criterion crit = Restrictions.eq(Country.ISO2, iso2);
        List<Country> result = super.findByCriteria(crit);
        if (result != null && result.size() == 1) {
            return result.get(0);
        } else {
            return null;
        }
    }

}
