package br.dev.tobias.domain.dto;

public class PlayerDTO {
    private Long id;
    private String name;
    private int shirtNumber;
    private String teamName;

    public PlayerDTO(Long id, String name, int shirtNumber, String teamName) {
        this.id = id;
        this.name = name;
        this.shirtNumber = shirtNumber;
        this.teamName = teamName;
    }

    public PlayerDTO() {
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getShirtNumber() {
        return shirtNumber;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setShirtNumber(int shirtNumber) {
        this.shirtNumber = shirtNumber;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }
}