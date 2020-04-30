package personal.jasonevans.cowboywiki.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import personal.jasonevans.cowboywiki.entity.User;
import personal.jasonevans.cowboywiki.entity.WikiUser;

public interface UserService extends UserDetailsService {

    public User findByUserName(String username);

    public User findById(int userId);

    public void save(WikiUser user);
}
