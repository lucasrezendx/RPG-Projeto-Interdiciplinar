create database if not exists rpg;
use rpg;

create table if not exists racas (
	id int not null auto_increment,
    nome varchar(255) not null,
    bonus_vida int not null,
    bonus_escudo int not null,
    bonus_poder_fisico int not null,
    bonus_poder_habilidade int not null,
    primary key (id)

    );

create table if not exists classes (
	id INT not null auto_increment,
    nome varchar(255) not null,
    bonus_vida int not null,
    bonus_escudo int not null,
    bonus_poder_fisico int not null,
    bonus_poder_habilidade int not null,
    primary key (id)
);

create table if not exists personagens (
	id INT not null auto_increment,
    nome varchar(255) not null,
    vida int not null,
    escudo int not null,
    poder_fisico int not null,
    poder_habilidade int not null,
    raca_id int not null,
    classe_id int not null,
    primary key (id),
    foreign key (raca_id) references racas(id),
    foreign key (classe_id) references classes(id)

);

create table if not exists batalhas (
	id int not null auto_increment,
    lutador1_id int not null,
    lutador2_id int not null,
    vencedor_id int not null,
    primary key (id),
    foreign key (lutador1_id) references personagens(id),
    foreign key (lutador2_id) references personagens(id),
    foreign key (vencedor_id) references personagens(id)



);


