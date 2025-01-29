package ee.mihkel.card_game.repository;

import ee.mihkel.card_game.entity.Game;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GameRepository extends JpaRepository<Game, Long> {


    List<Game> findByPlayer_Name(String name);
}
