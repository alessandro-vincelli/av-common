package it.av.web.common.rest.controller;

import it.av.apps.dao.UserDao;
import it.av.apps.model.User;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author alessandro vincelli
 *
 */
@Controller
@RequestMapping("/users")
public class UsersRestController {

    @Autowired
    private UserDao userDao;

    /**
     * 
     * @param response
     * @return
     */
    @RequestMapping(method = { RequestMethod.GET })
    public List<User> getUsers(HttpServletResponse response) {
        return userDao.getAll();
    }

    /**
     * 
     * @param response
     * @param userId
     * @return
     */
    @RequestMapping(value = "{id}", method = { RequestMethod.GET })
    public User getUser(@PathVariable("id") String id) {
        return userDao.getByID(id);
    }

    /**
     * 
     * @param request
     * @param user
     * @return
     */
    @RequestMapping(method = { RequestMethod.PUT, RequestMethod.POST })
    public void saveUser(HttpServletRequest request, @RequestBody User user) {
        userDao.save(user);
    }

}
