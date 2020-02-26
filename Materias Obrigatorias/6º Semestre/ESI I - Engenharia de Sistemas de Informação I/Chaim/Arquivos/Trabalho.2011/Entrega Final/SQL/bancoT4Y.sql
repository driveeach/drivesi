create table Endereco (
     idEndereco serial not null,
     rua varchar(50) not null,
     numend integer not null,
     bairro varchar(50) not null,
     cidade varchar(50) not null,
     estado varchar(2) not null,
     CEP varchar(8) not null,
     constraint pk_endereco primary key (idEndereco)
     );

create table Parada (
     idParada serial not null,
     idEndereco integer not null,
     latitude float not null,
     longitude float not null,
     sentido varchar(50) not null,
     constraint pk_parada primary key (idParada),
     constraint fk_parada_endereco foreign key (idEndereco) references Endereco (idEndereco) 
     );

create table Linha (
     idLinha serial not null,
     nome char(50) not null,
     constraint pk_linha primary key (idLinha)
     );

create table Veiculo (
     idVeiculo serial not null,
     idLinha integer,
     constraint pk_veiculo primary key (idVeiculo),
     constraint fk_veiculo_linha foreign key (idLinha) references Linha (idLinha)
     );

create table Tipo (
     idTipo serial not null,
     nome varchar(20) not null,
     constraint pk_tipo primary key (idTipo)
     );

create table Telefone (
	 idTelefone serial not null,
     idBluetooth varchar(12) not null,
     DDD integer not null,
     numero varchar(8) not null,
     constraint pk_telefone primary key (idTelefone)
     );
     
create table Passageiro (
     idPassageiro serial not null,
     idEndereco integer not null,
     idTipo integer not null,
     idTelefone integer not null,
     nome varchar(100) not null,
     RG varchar(9) not null,
     CPF varchar(11) not null,
     saldo float not null,
	 email varchar(100) not null,
	 senha varchar(8) not null,
     constraint pk_passageiro primary key (idPassageiro),
     constraint fk_passageiro_endereco foreign key (idEndereco) references Endereco (idEndereco),
     constraint fk_passageiro_tipo foreign key (idTipo) references Tipo (idTipo),
     constraint fk_passageiro_telefone foreign key (idTelefone) references Telefone (idTelefone)
     );

create table itinerario (
     idLinha integer not null,
     idParada integer not null,
     constraint fk_itinerario_linha foreign key (idLinha) references Linha (idLinha),
     constraint fk_itinerario_Parada foreign key (idParada) references Parada (idParada)
     );

create table utiliza (
     idUtiliza serial not null,
     idPassageiro integer not null,
     idVeiculo integer not null,
     tarifa boolean not null,
     dataEntrada date not null,
     dataSaida date not null,
     constraint pk_utiliza primary key (idUtiliza),
     constraint fk_utiliza_passageiro foreign key (idPassageiro) references Passageiro (idPassageiro),
     constraint fk_utiliza_veiculo foreign key (idVeiculo) references Veiculo (idVeiculo)
     );


INSERT INTO tipo(idtipo, nome) VALUES (1, 'Comum');
INSERT INTO tipo(idtipo, nome) VALUES (2, 'Estudante');
INSERT INTO tipo(idtipo, nome) VALUES (3, 'Especial');
INSERT INTO tipo(idtipo, nome) VALUES (4, 'Administrador');
