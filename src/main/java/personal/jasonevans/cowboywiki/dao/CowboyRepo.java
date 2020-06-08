package personal.jasonevans.cowboywiki.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import personal.jasonevans.cowboywiki.entity.Cowboy;

import java.util.List;

public interface CowboyRepo extends JpaRepository<Cowboy, Integer> {

    public Cowboy findByFirstNameAndLastName(String firstName, String lastName);

    public List<Cowboy> findAllByOrderByFirstNameAsc();

    public List<Cowboy> findAllByOrderByFirstNameDesc();

    public List<Cowboy> findAllByOrderByLastNameAsc();

    public List<Cowboy> findAllByOrderByLastNameDesc();

    public List<Cowboy> findAllByOrderByBirthdateAsc();

    public List<Cowboy> findAllByOrderByBirthdateDesc();

    public List<Cowboy> findAllByOrderByDeathdateAsc();

    public List<Cowboy> findAllByOrderByDeathdateDesc();

}
