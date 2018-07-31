CREATE TABLE pessoa(
codigo BIGSERIAL,
nome VARCHAR(50) NOT NULL,
ativo BOOLEAN NOT NULL,
lagradouro VARCHAR(50),
numero VARCHAR(50),
complemento VARCHAR(50),
bairro VARCHAR(50),
cep VARCHAR(50),
cidade VARCHAR(50),
estado VARCHAR(50),
	
PRIMARY KEY(codigo)
);

INSERT INTO pessoa(nome, ativo, lagradouro, numero, complemento, bairro, cep, cidade, estado) VALUES ('Francisco de la Santos', true, 'Rua barreto Lemes', '56', null, 'JD. Orquideoas', '13025258', 'Campinas', 'SP');
INSERT INTO pessoa(nome, ativo, lagradouro, numero, complemento, bairro, cep, cidade, estado) VALUES ('Jackson Five', true, 'Rua Sidney Guimarães Rosa', '56', null, 'JD. Orquideoas', '13025258', 'Campinas', 'SP');
INSERT INTO pessoa(nome, ativo, lagradouro, numero, complemento, bairro, cep, cidade, estado) VALUES ('Marcio Francesa', true, 'Rua Taco Bel', '56', null, 'JD. Orquideoas', '13025258', 'Campinas', 'SP');
INSERT INTO pessoa(nome, ativo, lagradouro, numero, complemento, bairro, cep, cidade, estado) VALUES ('Jeremias Natalino', true, 'Rua Engenheiro Coleho', '56', null, 'JD. Orquideoas', '13025258', 'Campinas', 'SP');