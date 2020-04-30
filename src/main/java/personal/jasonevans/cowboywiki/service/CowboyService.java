package personal.jasonevans.cowboywiki.service;

import personal.jasonevans.cowboywiki.entity.Cowboy;

public interface CowboyService {

    public Cowboy findCowboyById(int id);

    public void save(Cowboy cowboy);
}
