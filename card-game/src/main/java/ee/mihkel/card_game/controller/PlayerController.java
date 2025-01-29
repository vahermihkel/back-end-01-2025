package ee.mihkel.card_game.controller;

import ee.mihkel.card_game.entity.Player;
import ee.mihkel.card_game.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PlayerController {

    @Autowired
    PlayerRepository playerRepository;

    @PostMapping("players")
    public String addPlayer(@RequestBody Player player) {
        playerRepository.save(player);
        return "Isik edukalt salvestatud!";
    }

    @GetMapping("players")
    public List<Player> getPlayers() {
        return playerRepository.findAll();
    }

}
