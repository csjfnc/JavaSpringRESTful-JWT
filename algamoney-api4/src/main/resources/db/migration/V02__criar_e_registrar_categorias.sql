CREATE TABLE categoria(
codigo BIGSERIAL,
descricao VARCHAR(50),

PRIMARY KEY(codigo)
);

INSERT INTO categoria(descricao) VALUES('Automotivo');
INSERT INTO categoria(descricao) VALUES('Lazer');
INSERT INTO categoria(descricao) VALUES('Contas');
INSERT INTO categoria(descricao) VALUES('Comidas');
INSERT INTO categoria(descricao) VALUES('Festas');