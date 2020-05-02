package personal.jasonevans.cowboywiki.dao;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import personal.jasonevans.cowboywiki.entity.Cowboy;

import javax.persistence.EntityManager;

@Repository
public class CowboyDaoImpl implements CowboyDao{

    @Autowired
    private EntityManager entityManager;

    @Override
    public Cowboy findCowboyById(int theId) {

        //get current HB session
        Session currentSession = entityManager.unwrap(Session.class);

        //query db for matching IDs
        Query<Cowboy> theQuery = currentSession.createQuery("from Cowboys C where C.id=:theId");
        theQuery.setParameter("theId", theId);

        Cowboy cowboy = null;

        try{
            cowboy = theQuery.getSingleResult();
        }catch (Exception e){
            cowboy = null;
        }

        return cowboy;
    }

    @Override
    public void save(Cowboy cowboy) {

        //get current HB session
        Session currentSession = entityManager.unwrap(Session.class);

        currentSession.saveOrUpdate(cowboy);
    }
}
