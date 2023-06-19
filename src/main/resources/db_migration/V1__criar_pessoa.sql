CREATE TABLE IF NOT EXISTS pessoa(id_pessoa UUID NOT NULL PRIMARY KEY,
codigo int,
nome VARCHAR(255) NOT NULL,
tipo_pessoa int,
cpf_cnpj int(15) NOT NULL,
endereco VARCHAR(255),
bairro VARCHAR(255),
cidade VARCHAR(255),
estado VARCHAR(255),
numero int,
complemento VARCHAR(255),
cep int(8),
numero_telefone VARCHAR(20),
data_nascimento date)