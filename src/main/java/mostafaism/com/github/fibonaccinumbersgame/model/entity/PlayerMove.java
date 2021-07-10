package mostafaism.com.github.fibonaccinumbersgame.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Entity
@Table(name = "player_move")
@Data
@AllArgsConstructor
@Builder
public class PlayerMove {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Max(value = 20, message = "A game can have at most 20 turns")
    @Column(name = "turn")
    private int turn;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "game_player_id")
    private GamePlayer gamePlayer;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "move_id")
    private Move move;

}
