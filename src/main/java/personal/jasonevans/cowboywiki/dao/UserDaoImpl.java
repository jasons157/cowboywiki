package personal.jasonevans.cowboywiki.dao;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import personal.jasonevans.cowboywiki.entity.User;

import javax.persistence.EntityManager;

@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private EntityManager entityManager;

    /**
     * Find a user by their username
     * @param username Username to search for
     * @return searched User (null if not found)
     */
    @Override
    public User findByUserName(String username) {

        //get current HB session
        Session currentSession = entityManager.unwrap(Session.class);

        Query<User> query = currentSession.createQuery("from Users where username=:searchUserName", User.class);
        query.setParameter("searchUserName", username);

        User theUser = null;

        try{
            theUser = query.getSingleResult();
        } catch (Exception e){
            //If it didn't work, there is no user
            theUser = null;
        }

        return theUser;
    }

    /**
     * Find a user by their ID
     * @param userId ID to search for
     * @return searched User (null if not found)
     */
    @Override
    public User findByUserId(int userId) {

        Session currentSession = entityManager.unwrap(Session.class);

        Query<User> query = currentSession.createQuery("from Users U where U.id=:userId", User.class);
        query.setParameter("userId", userId);

        User theUser = null;

        try{
            theUser = query.getSingleResult();
        } catch (Exception e){
            //If it didn't work, there is no user
            theUser = null;
        }

        return theUser;
    }

    /**
     * Save a user to the database
     * @param user
     */
    @Override
    public void save(User user) {

        //get current HB session
        Session currentSession = entityManager.unwrap(Session.class);

        currentSession.saveOrUpdate(user);

    }
}
