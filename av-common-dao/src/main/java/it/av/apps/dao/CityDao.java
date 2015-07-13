package it.av.apps.dao;

import java.util.List;

import it.av.apps.model.City;
import it.av.apps.model.Country;

public interface CityDao extends GenericDAO<City> {

    List<City> find(String string, int maxResults);

    List<City> find(String string, Country country, int maxResults);

    List<String> findByName(String string, Country country, int maxResults);

    List<String> findName(String string, int maxResults);

    City getByNameAndCountry(String cityName, Country country);

}
