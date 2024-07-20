drop database if exists Sistema;

create database Sistema;

use Sistema;

create table Usuario(
    id bigint not null auto_increment, 
    nome varchar(256) not null,
    email varchar(256) not null unique,
    senha varchar(256) not null,
    papel varchar(15), 
    primary key (id)
);

create table Empresa( 
    cnpj varchar(18) not null unique,
    cidade varchar(50) not null,
    descricao varchar(500) not null,
    id_usuario bigint not null,
    primary key (cnpj),
    foreign key (id_usuario) references Usuario(id)
);

create table Vaga( 
    id_vaga bigint not null auto_increment,
    salario float not null,
    descricao_vaga varchar(500) not null,
    data_limite date not null, 
    empresa_id varchar(18) not null, 
    primary key (id_vaga),
    foreign key (empresa_id) references Empresa(cnpj)
);

create table Profissional(
    cpf varchar(18) not null unique,
    data_nasc varchar(256) not null,
    sexo varchar(256) not null,
    telefone varchar(500) not null,
    id_usuario bigint not null,
    primary key (cpf),
    foreign key (id_usuario) references Usuario(id)
);

create table Inscricao (
    cpf_id varchar(18) not null,
    vaga_id bigint not null,
    resultado int default 0,
    qualificacao VARCHAR(256),
    PRIMARY KEY (cpf_id, vaga_id),
    foreign key (cpf_id) references Profissional(cpf),
    foreign key (vaga_id) references Vaga(id_vaga)
);


INSERT INTO Usuario (nome, email, senha, papel) VALUES ('Empresa Exemplo', 'empresa', 'empresa', 'Empresa');
INSERT INTO Usuario (nome, email, senha, papel) VALUES ('Profissional Exemplo', 'profissional', 'profissional', 'Profissional');

INSERT INTO Empresa (cnpj, cidade, descricao, id_usuario) VALUES ('12.345.678/0001-90', 'São Paulo', 'Uma empresa de exemplo para testes.', 1);

INSERT INTO Vaga (salario, descricao_vaga, data_limite, empresa_id) VALUES (3500.00, 'Desenvolvedor de Software', '2024-12-31', '12.345.678/0001-90');

INSERT INTO Profissional (cpf, data_nasc, sexo, telefone, id_usuario) VALUES ('123.456.789-00', '1980-05-15', 'Masculino', '(11) 91234-5678', 2);

INSERT INTO Inscricao (cpf_id, vaga_id, qualificacao) VALUES ('123.456.789-00', 1, 'caminhopdf');

/*SELECT * FROM Inscricao i, Empresa e, Vaga v, Profissional p, Usuario u  WHERE e.cnpj = v.empresa_id AND i.vaga_id = v.id_vaga AND p.cpf = i.cpf_id AND u.papel = 'profissional';*/
