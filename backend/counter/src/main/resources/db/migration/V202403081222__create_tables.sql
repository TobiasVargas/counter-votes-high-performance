create table counter.players (
    player_id bigint not null,
    team_name varchar(100) not null,
    name varchar(200) not null,
    shirt_number integer not null,
    constraint players_pk primary key (player_id)
);

create table counter.votes (
    vote_id serial not null,
    uuid_vote uuid not null,
    player_id bigint not null,
    constraint votes_pk primary key (vote_id),
    constraint votes_unique unique (uuid_vote)
);