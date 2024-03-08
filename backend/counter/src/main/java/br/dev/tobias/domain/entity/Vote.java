package br.dev.tobias.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.util.UUID;

@Entity
@Table(schema = "counter", name = "votes")
@DynamicInsert
@DynamicUpdate
public class Vote {
    @Id
    @Column(name = "vote_id", unique = true, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "uuid_vote", unique = true, updatable = false)
    private UUID uuid;
    @ManyToOne
    @JoinColumn(name = "player_id")
    private Player player;

    public Vote() {
    }

    public Vote(UUID uuid, Player player) {
        this.uuid = uuid;
        this.player = player;
    }
}
