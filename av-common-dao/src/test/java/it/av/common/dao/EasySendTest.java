package it.av.common.dao;

import it.av.users.dao.LanguageDao;
import it.av.users.dao.UserProfileDao;
import it.av.users.model.Language;
import it.av.users.model.UserProfile;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;


public abstract class EasySendTest {

    @Autowired
    private UserProfileDao userProfileDao;
    @Autowired
    private LanguageDao languageDao;
    private UserProfile profile;
    private Language language;

    public void setUp() {
        profile = new UserProfile();
        profile.setName("testProfile");
        profile = userProfileDao.save(profile);

        if (userProfileDao.getByName("ADMIN") == null) {
            UserProfile adminProfile = new UserProfile("ADMIN");
            adminProfile = userProfileDao.save(adminProfile);
        }
        if(languageDao.getAll().size() == 0){
            languageDao.save(new Language("it", "italy"));
            languageDao.save(new Language("en", "usa"));
        }
        language = languageDao.getAll().get(0);
    }
    
    public void tearDown(){
        Collection<UserProfile> all2 = userProfileDao.getAll();
        for (UserProfile userProfile : all2) {
            userProfileDao.remove(userProfile);
        }
        List<Language> all = languageDao.getAll();
        for (Language language : all) {
            languageDao.remove(language);
        }
        
    }

    public UserProfile getProfile() {
        return profile;
    }

    public Language getLanguage() {
        return language;
    }
    
}