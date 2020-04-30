package personal.jasonevans.cowboywiki.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import personal.jasonevans.cowboywiki.dao.RoleDao;
import personal.jasonevans.cowboywiki.dao.UserDao;
import personal.jasonevans.cowboywiki.entity.Role;
import personal.jasonevans.cowboywiki.entity.User;
import personal.jasonevans.cowboywiki.entity.WikiUser;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;


    @Override
    @Transactional
    public User findByUserName(String username) {
        return userDao.findByUserName(username);
    }

    /**
     * Saves user to database when they register
     * @param wikiUser desired user to save
     */
    @Override
    @Transactional
    public void save(WikiUser wikiUser) {

       User user = new User();

       //assign new user details
        user.setUserName(wikiUser.getUsername());
        user.setPassword(passwordEncoder.encode(wikiUser.getPassword()));//Set the password as an encrypted pw
        user.setFirstName(wikiUser.getFirstName());
        user.setLastName(wikiUser.getLastName());
        user.setEmail(wikiUser.getEmail());

        //give user default role of "visitor" so they can look but not edit
        user.setRoles(Arrays.asList(roleDao.findRoleByName("ROLE_VISITOR")));

        userDao.save(user);
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }
}
