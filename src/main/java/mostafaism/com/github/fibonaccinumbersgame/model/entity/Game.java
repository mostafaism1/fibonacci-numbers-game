package mostafaism.com.github.fibonaccinumbersgame.model.entity;

import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "game")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Column(name = "game_code", unique = true)
    private UUID gameCode;

    @Max(value = 20, message = "A game can have at most 20 turns")
    @Column(name = "turn")
    private int turn;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "game", cascade = CascadeType.ALL)
    private List<Player> players;

    @OneToOne
    @JoinColumn(name = "on_turn_player_id")
    private Player onTurnPlayer;

    @ElementCollection
    private List<Integer> fibonacciNumbers;

    @Column(name = "is_ended")
    private boolean isEnded;

}
