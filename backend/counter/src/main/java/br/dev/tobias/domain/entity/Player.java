package br.dev.tobias.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(schema = "counter", name = "players")
@DynamicInsert
@DynamicUpdate
public class Player {
    @Id
    @Column(name = "player_id", unique = true, updatable = false)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "shirt_number")
    private int shirtNumber;
    @Column(name = "team_name")
    private String teamName;

    public Player() {
    }

    public Player(Long id, String name, int shirtNumber, String teamName) {
        this.id = id;
        this.name = name;
        this.shirtNumber = shirtNumber;
        this.teamName = teamName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getShirtNumber() {
        return shirtNumber;
    }

    public void setShirtNumber(int shirtNumber) {
        this.shirtNumber = shirtNumber;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }
}
