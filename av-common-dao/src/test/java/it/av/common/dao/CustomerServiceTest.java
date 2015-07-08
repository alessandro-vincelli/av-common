package it.av.common.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import it.av.apps.dao.cargo.CustomerDao;
import it.av.apps.model.cargo.Customer;

import java.util.Collection;

import org.junit.After;
import org.junit.Before;
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
public class CustomerServiceTest extends EasySendTest {

    @Autowired
    private CustomerDao dao;

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
        Customer a = new Customer();
        a.setCompanyName("company1");
        dao.add(a);

        // a = eaterService.getByPath(a.getPath());
        assertNotNull("A is null", a);
        assertNotNull("Company name is null", a.getCompanyName());

        Collection<Customer> all = dao.getAll();
        assertNotNull(all);
        assertTrue(all.size() > 0);

        a.setCompanyName("company2");
        dao.update(a);
        assertEquals("Invalid value for company2", "company2", a.getCompanyName());
        a = dao.getByID(a.getId());
        assertNotNull("A is null", a);
        assertEquals("Invalid value for test", "company2", a.getCompanyName());

        dao.remove(a);

    }

}
