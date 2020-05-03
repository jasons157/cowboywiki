package personal.jasonevans.cowboywiki.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import personal.jasonevans.cowboywiki.entity.Cowboy;

public interface CowboyRepo extends JpaRepository<Cowboy, Integer> {
}
