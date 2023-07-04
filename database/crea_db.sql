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
    id integer not null auto_increment,
    id_utente integer not null,
    costo_totale double not null default '0.00',
    numero_articoli integer not null default '0',
    FOREIGN KEY (id_utente) REFERENCES utente(id) ON DELETE CASCADE,
    PRIMARY KEY (id, id_utente)
);

CREATE TABLE Prodotto (
    codice varchar(6) not null primary key,
    nome varchar(40) not null,
    prezzo double not null,
    sconto integer not null,
    categoria varchar(40),
    descrizione varchar(1024) not null,
    immagine varchar(512) not null
);

CREATE TABLE Briefing(
    id integer not null primary key auto_increment,
    id_utente integer not null,
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

CREATE TABLE Item_Carello(
    id integer not null primary key auto_increment,
    id_carello integer not null,
    cod_prodotto varchar(6) not null,
    quantita integer not null,
    FOREIGN KEY (id_carello) REFERENCES Carrello(id),
    FOREIGN KEY (cod_prodotto) REFERENCES Prodotto(codice)
);

CREATE TABLE Pagamento (
    numero_carta varchar(16) not null primary key,
    titolare varchar(128) not null,
    cvv varchar(3) not null,
    scadenza date not null
);

CREATE TABLE Ordine(
    numero_ordine integer not null primary key auto_increment,
    numero_carta varchar(16) not null,
    id_utente integer not null,
    data_ordine date not null,
    importo double not null,
    FOREIGN KEY (id_utente) REFERENCES Utente(id),
    FOREIGN KEY (numero_carta) REFERENCES Pagamento(numero_carta)
);

CREATE TABLE Item_Ordine(
    id integer not null primary key auto_increment,
    numero_ordine integer not null,
    cod_prodotto varchar(6) not null,
    quantita integer not null,
    prezzo_acquisto double not null,
    FOREIGN KEY (numero_ordine) REFERENCES Ordine(numero_ordine),
    FOREIGN KEY (cod_prodotto) REFERENCES Prodotto(codice)
);