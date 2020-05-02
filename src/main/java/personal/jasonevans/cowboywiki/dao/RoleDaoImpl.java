package personal.jasonevans.cowboywiki.dao;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import personal.jasonevans.cowboywiki.entity.Role;

import javax.persistence.EntityManager;

@Repository
public class RoleDaoImpl implements RoleDao {

    @Autowired
    private EntityManager entityManager;

    @Override
    public Role findRoleByName(String roleName) {

        //get current session
        Session currentSession = entityManager.unwrap(Session.class);

        // now retrieve/read from database using name
        Query<Role> theQuery = currentSession.createQuery("from Role where name=:roleName", Role.class);
        theQuery.setParameter("roleName", roleName);

        Role theRole = null;

        try {
            theRole = theQuery.getSingleResult();
        } catch (Exception e) {
            theRole = null;
        }

        return theRole;
    }
}
