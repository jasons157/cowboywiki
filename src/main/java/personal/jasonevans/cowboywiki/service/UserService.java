package personal.jasonevans.cowboywiki.service;

import personal.jasonevans.cowboywiki.entity.User;
import personal.jasonevans.cowboywiki.entity.WikiUser;

public interface UserService {

    public User findByUserName(String username);

    public void save(WikiUser user);
}
