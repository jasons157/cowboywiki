package personal.jasonevans.cowboywiki.dao;

import personal.jasonevans.cowboywiki.entity.User;

public interface UserDao {

    public User findByUserName(String username);

    public User findByUserId(int userId);

    public void save(User user);
}
