package personal.jasonevans.cowboywiki.dao;

import personal.jasonevans.cowboywiki.entity.Cowboy;

public interface CowboyDao {

    public Cowboy findCowboyById(int id);

    public void save(Cowboy cowboy);

}
