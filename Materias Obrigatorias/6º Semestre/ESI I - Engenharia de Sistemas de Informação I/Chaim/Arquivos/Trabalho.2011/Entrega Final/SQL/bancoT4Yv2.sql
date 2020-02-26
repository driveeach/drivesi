create table Endereco (
  idendereco serial NOT NULL,
  rua character varying(50) NOT NULL,
  numend integer NOT NULL,
  bairro character varying(50) NOT NULL,
  cidade character varying(50) NOT NULL,
  estado character varying(2) NOT NULL,
  cep character varying(8) NOT NULL,
  CONSTRAINT pk_endereco PRIMARY KEY (idendereco)
     );

create table Parada (
  idparada serial NOT NULL,
  idendereco integer NOT NULL,
  latitude double precision NOT NULL,
  longitude double precision NOT NULL,
  sentido integer DEFAULT 0,
  CONSTRAINT pk_parada PRIMARY KEY (idparada),
  CONSTRAINT fk_parada_endereco FOREIGN KEY (idendereco) REFERENCES endereco (idendereco) MATCH SIMPLE ON UPDATE NO ACTION ON DELETE NO ACTION
     );

create table Linha (
     idLinha serial not null,
     nome char(50) not null,
     constraint pk_linha primary key (idLinha)
     );

create table Veiculo (
  idveiculo serial NOT NULL,
  idlinha integer,
  codigobt character varying(12),
  CONSTRAINT pk_veiculo PRIMARY KEY (idveiculo),
  CONSTRAINT fk_veiculo_linha FOREIGN KEY (idlinha) REFERENCES linha (idlinha) MATCH SIMPLE ON UPDATE NO ACTION ON DELETE NO ACTION
     );

create table Tipo (
  idtipo serial NOT NULL,
  nome character varying(20) NOT NULL,
  CONSTRAINT pk_tipo PRIMARY KEY (idtipo)
     );

create table Telefone (
  idtelefone serial NOT NULL,
  idbluetooth character varying(12) NOT NULL,
  ddd integer NOT NULL,
  numero character varying(8) NOT NULL,
  CONSTRAINT pk_telefone PRIMARY KEY (idtelefone)
     );
     
create table Passageiro (
  idpassageiro serial NOT NULL,
  idendereco integer NOT NULL,
  idtipo integer NOT NULL,
  idtelefone integer NOT NULL,
  nome character varying(100) NOT NULL,
  rg character varying(9) NOT NULL,
  cpf character varying(11) NOT NULL,
  saldo double precision NOT NULL,
  email character varying(100) NOT NULL,
  senha character varying(8) NOT NULL,
  CONSTRAINT pk_passageiro PRIMARY KEY (idpassageiro),
  CONSTRAINT fk_passageiro_endereco FOREIGN KEY (idendereco) REFERENCES endereco (idendereco) MATCH SIMPLE ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_passageiro_telefone FOREIGN KEY (idtelefone) REFERENCES telefone (idtelefone) MATCH SIMPLE ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_passageiro_tipo FOREIGN KEY (idtipo) REFERENCES tipo (idtipo) MATCH SIMPLE ON UPDATE NO ACTION ON DELETE NO ACTION
     );

create table itinerario (
  idlinha integer NOT NULL,
  idparada integer NOT NULL,
  CONSTRAINT fk_itinerario_linha FOREIGN KEY (idlinha) REFERENCES linha (idlinha) MATCH SIMPLE ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_itinerario_parada FOREIGN KEY (idparada) REFERENCES parada (idparada) MATCH SIMPLE ON UPDATE NO ACTION ON DELETE NO ACTION
     );

create table utiliza (
  idutiliza serial NOT NULL,
  idpassageiro integer NOT NULL,
  idveiculo integer NOT NULL,
  tarifa boolean NOT NULL DEFAULT false,
  datahora timestamp without time zone,
  idparada integer,
  tipoutiliza integer DEFAULT 0,
  CONSTRAINT pk_utiliza PRIMARY KEY (idutiliza),
  CONSTRAINT fk_utiliza_parada_entrada FOREIGN KEY (idparada) REFERENCES parada (idparada) MATCH SIMPLE ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_utiliza_passageiro FOREIGN KEY (idpassageiro) REFERENCES passageiro (idpassageiro) MATCH SIMPLE ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_utiliza_veiculo FOREIGN KEY (idveiculo) REFERENCES veiculo (idveiculo) MATCH SIMPLE ON UPDATE NO ACTION ON DELETE NO ACTION
     );


INSERT INTO tipo(idtipo, nome) VALUES (1, 'Comum');
INSERT INTO tipo(idtipo, nome) VALUES (2, 'Estudante');
INSERT INTO tipo(idtipo, nome) VALUES (3, 'Especial');
INSERT INTO tipo(idtipo, nome) VALUES (4, 'Administrador');
