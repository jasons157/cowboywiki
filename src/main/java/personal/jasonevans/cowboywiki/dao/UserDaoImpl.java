package personal.jasonevans.cowboywiki.dao;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import personal.jasonevans.cowboywiki.entity.User;

import javax.persistence.EntityManager;

public class UserDaoImpl implements UserDao {

    @Autowired
    private EntityManager entityManager;

    //Get users from database
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

    @Override
    public void save(User user) {

        //get current HB session
        Session currentSession = entityManager.unwrap(Session.class);

        currentSession.saveOrUpdate(user);

    }
}
