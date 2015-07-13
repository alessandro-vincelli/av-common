package it.av.apps.dao;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import it.av.apps.model.City;
import it.av.apps.model.Country;

public class CityDaoJpa extends GenericJpaDAO<City>implements CityDao {

    @Autowired
    private CountryDao countryService;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<City> getAll() {
        Order orderBYName = Order.asc(City.NAME_FIELD);
        return super.findByCriteria(orderBYName);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<City> find(String string, int maxResults) {
        Criterion critByName = Restrictions.ilike(City.NAME_FIELD, string + "%");
        Order orderByName = Order.asc(City.NAME_FIELD);
        return findByCriteria(orderByName, 0, maxResults, critByName);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<City> find(String string, Country country, int maxResults) {
        Criterion critByName = Restrictions.ilike("name", string + "%");
        Criterion critByCountry = Restrictions.eq("country", country);
        Order orderByName = Order.asc(City.NAME_FIELD);
        return findByCriteria(orderByName, 0, maxResults, critByName, critByCountry);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<String> findByName(String string, Country country, int maxResults) {
        Query query = getEm().createQuery("select name  from City as city where upper(city.name) like upper(:name) and city.country = :country order by length(city.name)");
        query.setParameter("name", string + "%");
        query.setParameter("country", country);
        query.setMaxResults(maxResults);
        return query.getResultList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<String> findName(String string, int maxResults) {
        Query query = getEm().createQuery("select name  from City as city where upper(city.name) like upper(:name)");
        query.setParameter("name", string + "%");
        query.setMaxResults(maxResults);
        return query.getResultList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public City getByNameAndCountry(String cityName, Country country) {
        Query query = getEm().createQuery("select city from City as city where upper(city.name) = upper(:name) and city.country = :country");
        query.setParameter("name", cityName);
        query.setParameter("country", country);
        query.setMaxResults(1);
        List<City> resuts = query.getResultList();
        if (resuts.isEmpty()) {
            return null;
        } else {
            return resuts.get(0);
        }
    }

}
