package it.av.common.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import it.av.apps.dao.UserDao;
import it.av.apps.model.User;

import java.util.Collection;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

@ContextConfiguration(locations = "classpath:application-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
public class UserServiceTest extends EasySendTest {

    @Autowired
    private UserDao userDao;

    @Before
    @Transactional
    public void setUp() {
        super.setUp();
    }

    @After
    @Transactional
    public void tearDown() {
    }

    @Test
    public void testUserService_generic() {

        // Basic Test
        User a = new User();
        a.setFirstname("Alessandro");
        a.setLastname("Vincelli");
        a.setPassword("secret");
        a.setEmail("userServiceTest@test");
        a.setUserProfile(getProfile());
        a.setLanguage(getLanguage());

        userDao.add(a);

        // a = eaterService.getByPath(a.getPath());
        assertNotNull("A is null", a);
        assertNotNull("Profile is null", a.getUserProfile());
        assertEquals("Invalid value for test", "Alessandro", a.getFirstname());

        Collection<User> all = userDao.getAll();
        assertNotNull(all);
        assertTrue(all.size() > 0);

        a.setLastname("Modified");
        userDao.update(a);
        assertEquals("Invalid value for test", "Modified", a.getLastname());
        a = userDao.getByEmail("userServiceTest@test");
        assertNotNull("A is null", a);
        assertEquals("Invalid value for test", "Alessandro", a.getFirstname());

        /*List<User> found = eaterService.freeTextSearch("Ale*");
        assertNotNull(found);
        assertTrue(found.size() > 0);
        
        found = eaterService.freeTextSearch("vinc*");
        assertNotNull(found);
        assertTrue(found.size() > 0);
        */

        userDao.remove(a);

    }

    @Test
    @Ignore("page needed")
    public void testUserService_remove() {

        User a = new User();
        a.setFirstname("Alessandro");
        a.setLastname("Vincelli");
        a.setPassword("secret");
        a.setEmail("userServiceTest@test");
        a.setUserProfile(getProfile());
        a.setLanguage(getLanguage());

        userDao.add(a);

        User b = new User();
        b.setFirstname("Alessandro");
        b.setLastname("Vincelli");
        b.setPassword("secret");
        b.setEmail("userServiceTest@test.com");
        b.setUserProfile(getProfile());
        b.setLanguage(getLanguage());
        b = userDao.add(b);
        assertNotNull("B is null", b);

        userDao.add(b);

        userDao.remove(a);
        userDao.remove(b);

    }
}
