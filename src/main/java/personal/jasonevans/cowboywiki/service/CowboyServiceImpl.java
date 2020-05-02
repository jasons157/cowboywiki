package personal.jasonevans.cowboywiki.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import personal.jasonevans.cowboywiki.dao.CowboyDao;
import personal.jasonevans.cowboywiki.entity.Cowboy;

@Service
public class CowboyServiceImpl implements CowboyService{

    @Autowired
    private CowboyDao cowboyDao;

    @Override
    @Transactional
    public Cowboy findCowboyById(int id) {
        return cowboyDao.findCowboyById(id);
    }

    /**
     * Assign the details of a cowboy to the database
     * This cowboy will be coming back from a web form,
     * so will be an assembled cowboy already. It just
     * needs to be assigned and saved to a DB object.
     *
     * @param cowboy Cowboy constructed from web form to save to DB
     */
    @Override
    @Transactional
    public void save(Cowboy cowboy) {
        Cowboy newCowboy = new Cowboy();

        //assign details of cowboy to new cowboy
        newCowboy.setFirstName(cowboy.getFirstName());
        newCowboy.setLastName(cowboy.getLastName());
        newCowboy.setBiography(cowboy.getBiography());
        newCowboy.setBirthdate(cowboy.getBirthdate());
        newCowboy.setDeathdate(cowboy.getDeathdate());
        newCowboy.setImagePath(cowboy.getImagePath());

        //save cowboy to DB
        cowboyDao.save(cowboy);
    }
}
