package personal.jasonevans.cowboywiki.service;

import personal.jasonevans.cowboywiki.entity.Cowboy;

import java.util.List;

public interface CowboyService {

    public List<Cowboy> findAll();

    public Cowboy findCowboyById(int id);

    public Cowboy findByFirstNameAndLastName(String firstName, String lastName);

    public List<Cowboy> findAllFirstNameAsc();

    public List<Cowboy> findAllFirstNameDesc();

    public List<Cowboy> findAllLastNameAsc();

    public List<Cowboy> findAllLastNameDesc();

    public List<Cowboy> findAllBirthdateAsc();

    public List<Cowboy> findAllBirthdateDesc();

    public List<Cowboy> findAllDeathdateAsc();

    public List<Cowboy> findAllDeathdateDesc();

    public void save(Cowboy cowboy);

    public void deleteById(int cowboyId);
}
