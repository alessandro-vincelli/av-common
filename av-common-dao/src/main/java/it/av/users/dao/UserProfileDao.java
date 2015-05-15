package it.av.users.dao;

import it.av.users.model.UserProfile;

public interface UserProfileDao extends GenericDAO<UserProfile> {

    UserProfile getByName(String name);

}
