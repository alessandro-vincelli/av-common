package it.av.apps.dao;

import it.av.apps.model.UserProfile;

public interface UserProfileDao extends GenericDAO<UserProfile> {

    UserProfile getByName(String name);

}
