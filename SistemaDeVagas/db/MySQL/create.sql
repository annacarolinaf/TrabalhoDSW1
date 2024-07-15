drop database if exists Sistema;

create database Sistema;

use Sistema;

create table Empresa( 
    id_empresa bigint not null auto_increment, 
    cnpj varchar(18) not null unique,
    nome varchar(256) not null,
    email varchar(256) not null unique,
    senha varchar(256) not null,
    cidade varchar(256) not null,
    descrição varchar(500) not null,
    primary key (id_empresa)
);

create table Vaga( 
    id_vaga bigint not null auto_increment,
    salario float not null,
    descrição varchar(500) not null,
    data_limite date not null, 
    empresa_id bigint not null, 
    primary key (id_vaga),
    foreign key (empresa_id) references Empresa(id_empresa)
);

create table Profissional(
    id_prof bigint not null auto_increment, 
    cnpj varchar(18) not null unique,
    nome varchar(256) not null,
    email varchar(256) not null unique,
    senha varchar(256) not null,
    data_nasc date not null,
    sexo varchar(256) not null,
    telefone varchar(500) not null,
    primary key (id_prof)
);

create table Inscricao (
    prof_id bigint not null,
    vaga_id bigint not null,
    resultado int default 0,
    PRIMARY KEY (prof_id, vaga_id),
    foreign key (prof_id) references Profissional(id_prof),
    foreign key (vaga_id) references Vaga(id_vaga)
);


INSERT INTO Empresa (cnpj, nome, email, senha, cidade, descrição) VALUES ('12.345.678/0001-90', 'Empresa Exemplo', 'contato@empresaexemplo.com', 'senha123', 'São Paulo', 'Uma empresa de exemplo para testes.');

INSERT INTO Vaga (salario, descrição, data_limite, empresa_id) VALUES (3500.00, 'Desenvolvedor de Software', '2024-12-31', 1);

INSERT INTO Profissional (cnpj, nome, email, senha, data_nasc, sexo, telefone) VALUES ('12.345.678/0002-88', 'Profissional Exemplo', 'contato@profissionalexemplo.com', 'senhaSegura456', '1980-05-15', 'Masculino', '(11) 91234-5678');

INSERT INTO Inscricao (prof_id, vaga_id) VALUES (1, 1);