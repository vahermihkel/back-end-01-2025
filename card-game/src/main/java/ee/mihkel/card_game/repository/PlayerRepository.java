package ee.mihkel.card_game.repository;

import ee.mihkel.card_game.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<Player, String> {
}
