package personal.jasonevans.cowboywiki.service;

import personal.jasonevans.cowboywiki.entity.Cowboy;

import java.util.List;

public interface CowboyService {

    public List<Cowboy> findAll();

    public Cowboy findCowboyById(int id);

    public Cowboy findByFirstNameAndLastName(String firstName, String lastName);

    public void save(Cowboy cowboy);

    public void deleteById(int cowboyId);
}
