BEGIN;

-- Criação da tabela GPS
CREATE TABLE GPS (
    noserie serial PRIMARY KEY,
    latitude NUMERIC(6,4) NOT NULL,
    longitude NUMERIC(6,4) NOT NULL,
    bateria INTEGER CHECK (bateria >= 0 AND bateria <= 100) NOT NULL
);

-- Criação da tabela PESSOA
create table PESSOA (
    id SERIAL primary key,
    nome VARCHAR(40) not null,
    morada VARCHAR(150),
    email VARCHAR(40) unique not null check (position('@' in email) > 0),
    telefone VARCHAR(30) not null check (LENGTH(telefone) >= 9),
    noident CHAR(12) unique not null,
    nacionalidade VARCHAR(20),
    atrdisc CHAR(2) check (atrdisc in ('G', 'C')) -- 'G' para Gestor, 'C' para Cliente
);

-- Criação da tabela LOJA
CREATE TABLE LOJA (
    codigo INTEGER PRIMARY KEY,
    email VARCHAR(40) UNIQUE NOT NULL CHECK (POSITION('@' IN email) > 0),
    endereco VARCHAR(100) NOT NULL,
    localidade VARCHAR(30) NOT NULL,
    notelefone VARCHAR NOT NULL CHECK (LENGTH(notelefone) >= 9),
    gestor INTEGER NOT NULL,
    FOREIGN KEY (gestor) REFERENCES PESSOA(id)
);



-- Criação da tabela BICICLETA
create table BICICLETA (
    id_bicicleta SERIAL primary key,
    peso NUMERIC(4,2) check (peso >= 0) not null,
    raio INTEGER not null,
    modelo VARCHAR(20) not null,
    marca VARCHAR(30) not null,
    mudanca INTEGER check (mudanca in (1, 6, 18, 24)),
    estado VARCHAR(30) check (estado in ('livre', 'ocupado', 'em reserva', 'em manutenção')) not null,
    atrdisc CHAR(1) check (atrdisc in ('C', 'E')), -- 'C' para Clássica, 'E' para Elétrica
    gps INTEGER,
    loja INTEGER,
    foreign key (gps) references GPS(noserie),
    foreign key (loja) references LOJA(codigo)

);



-- Criação da tabela ELECTRICA
CREATE TABLE ELECTRICA (
    bicicleta INTEGER PRIMARY KEY,
    autonomia INTEGER CHECK (autonomia >= 0) NOT NULL,
    velocidade INTEGER CHECK (velocidade >= 0) NOT NULL,
    FOREIGN KEY (bicicleta) REFERENCES BICICLETA(id_bicicleta)
);


-- Criação da tabela RESERVA
CREATE TABLE RESERVA (
    noreserva SERIAL,
    loja INTEGER,
    dtinicio TIMESTAMP NOT NULL,
    dtfim TIMESTAMP CHECK (dtfim > dtinicio),
    valor NUMERIC(4,2),
    bicicleta INTEGER,
    PRIMARY KEY (noreserva, loja),
    FOREIGN KEY (loja) REFERENCES LOJA(codigo),
    FOREIGN KEY (bicicleta) REFERENCES BICICLETA(id_bicicleta)
);

-- Criação da tabela CLIENTERESERVA
create table CLIENTERESERVA (
    cliente INTEGER,
    reserva INTEGER,
    loja INTEGER,
    PRIMARY KEY (cliente, reserva, loja),
    FOREIGN KEY (cliente) REFERENCES PESSOA(id),
    FOREIGN KEY (reserva, loja) REFERENCES RESERVA(noreserva, loja)
);

COMMIT;

GRANT ALL PRIVILEGES ON ALL TABLES IN SCHEMA public to t43dg35;
GRANT ALL PRIVILEGES ON TABLE PESSOA TO t43dg35;
alter table pessoa owner to t43dg35;
\du

drop table CLIENTERESERVA;
drop table RESERVA;
drop table ELECTRICA;
drop table BICICLETA;
drop table LOJA;
drop table PESSOA;
drop table GPS;