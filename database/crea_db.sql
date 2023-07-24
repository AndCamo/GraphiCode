drop database if exists GraphiCode;
create database GraphiCode;
use GraphiCode;


CREATE TABLE Utente (
    id integer not null primary key auto_increment,
    nome varchar(40) not null,
    cognome varchar(40) not null,
    eMail varchar(40) not null,
    passkey varchar(40) not null,
    telefono varchar(15) not null,
    data_di_nascita date not null,
    nazione varchar(50) not null,
    isAdmin VARCHAR(5) default 'false'
);

CREATE TABLE Carrello (
    id integer not null  primary key auto_increment,
    id_utente integer not null,
    numero_articoli integer not null default '0'
);

CREATE TABLE Prodotto (
    codice varchar(6) not null primary key,
    nome varchar(40) not null,
    prezzo double not null,
    sconto integer not null,
    categoria varchar(40),
    descrizione varchar(1024) not null,
    immagine varchar(512) not null,
    personalized VARCHAR(5) default 'true'
);

CREATE TABLE Briefing(
    id integer not null primary key auto_increment,
    id_utente integer,
    cod_prodotto varchar(6) not null,
    target varchar(255) not null,
    stile varchar(255) not null,
    obiettivi varchar(255) not null,
    palette_colori varchar(128) not null,
    note varchar(1024) not null,
    FOREIGN KEY (id_utente) REFERENCES Utente(id),
    FOREIGN KEY (cod_prodotto) REFERENCES Prodotto(codice)
);

CREATE TABLE Recensione(
    id integer not null primary key auto_increment,
    id_utente integer not null,
    cod_prodotto varchar(6) not null,
    numero_stelle integer not null,
    contenuto varchar(1024) default ' ',
    FOREIGN KEY (id_utente) REFERENCES Utente(id),
    FOREIGN KEY (cod_prodotto) REFERENCES Prodotto(codice)
);

CREATE TABLE Item_Carrello(
    id integer not null primary key auto_increment,
    id_carrello integer not null,
    id_briefing integer,
    cod_prodotto varchar(6) not null,
    quantita integer not null,
    FOREIGN KEY (id_carrello) REFERENCES Carrello(id) ON DELETE CASCADE,
    FOREIGN KEY (cod_prodotto) REFERENCES Prodotto(codice),
    FOREIGN KEY (id_briefing) REFERENCES Briefing(id)
);


DROP TABLE  Pagamento;
CREATE TABLE Pagamento (
    id integer not null primary key auto_increment,
    id_utente integer not null,
    numero_carta varchar(20) not null,
    titolare_carta varchar(128) not null,
    cvv varchar(40) not null,
    scadenza varchar(7) not null,
    FOREIGN KEY  (id_utente) REFERENCES Utente(id)
);

ALTER TABLE Pagamento
    ALTER COLUMN numero_carta VARCHAR(20);

DROP TABLE Ordine;
CREATE TABLE Ordine(
    numero_ordine integer not null primary key auto_increment,
    id_pagamento int not null,
    id_utente integer not null,
    data_ordine date not null,
    importo double not null,
    FOREIGN KEY (id_utente) REFERENCES Utente(id),
    FOREIGN KEY (id_pagamento) REFERENCES Pagamento(id)
);

DROP TABLE Item_Ordine;
CREATE TABLE Item_Ordine(
    id integer not null primary key auto_increment,
    numero_ordine integer not null,
    cod_prodotto varchar(6) not null,
    id_briefing int,
    quantita integer not null,
    prezzo_acquisto double not null,
    FOREIGN KEY (numero_ordine) REFERENCES Ordine(numero_ordine),
    FOREIGN KEY (cod_prodotto) REFERENCES Prodotto(codice),
    FOREIGN KEY (id_briefing) REFERENCES Briefing(id)
);