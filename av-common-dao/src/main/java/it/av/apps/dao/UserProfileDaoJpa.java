package it.av.apps.dao;

import it.av.apps.model.UserProfile;

import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

public class UserProfileDaoJpa extends GenericJpaDAO<UserProfile> implements UserProfileDao {

    /**
     * {@inheritDoc}
     */
    @Override
    public UserProfile getByName(String name) {
        Criterion crit = Restrictions.eq(UserProfile.NAME, name);
        List<UserProfile> result = super.findByCriteria(crit);
        if (result != null && result.size() > 0) {
            return result.get(0);
        } else {
            return null;
        }
    }

}
