package it.av.apps.dao;

import it.av.apps.model.Country;

public interface CountryDao extends GenericDAO<Country> {

    Country getByIso2(String iso2);
    
}
