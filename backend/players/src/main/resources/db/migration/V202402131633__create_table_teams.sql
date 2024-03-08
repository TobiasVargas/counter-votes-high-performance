create table players.teams (
    team_id serial not null,
    name varchar(100) not null,
    constraint teams_pk primary key (team_id)
);
