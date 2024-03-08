create table players.players (
    player_id serial not null,
    team_id bigint not null,
    name varchar(200) not null,
    shirt_number integer not null,
    constraint players_pk primary key (player_id),
    constraint player_teams_fk_01 foreign key (player_id) references players.teams(team_id)
);
