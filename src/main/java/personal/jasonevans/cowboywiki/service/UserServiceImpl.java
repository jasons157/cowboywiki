package personal.jasonevans.cowboywiki.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import personal.jasonevans.cowboywiki.dao.RoleDao;
import personal.jasonevans.cowboywiki.dao.UserDao;
import personal.jasonevans.cowboywiki.entity.Role;
import personal.jasonevans.cowboywiki.entity.User;
import personal.jasonevans.cowboywiki.entity.WikiUser;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    /**
     * Search users by their username
     * @param username
     * @return User
     */
    @Override
    @Transactional
    public User findByUserName(String username) {
        return userDao.findByUserName(username);
    }

    /**
     * Search users by their ID
     * @param userId ID to search for
     * @return User
     */
    @Override
    @Transactional
    public User findById(int userId) {
        return userDao.findByUserId(userId);
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

    /**
     * Overrides UserDetails service method
     * @param userName
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userDao.findByUserName(userName);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(),
                mapRolesToAuthorities(user.getRoles()));
    }

    /**
     * Utility method to map roles to authorities.
     * @param roles
     * @return Collection of authorities
     */
    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }
}
