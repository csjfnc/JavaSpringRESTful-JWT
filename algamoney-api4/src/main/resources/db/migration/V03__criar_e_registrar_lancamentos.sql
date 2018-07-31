CREATE TABLE lancamento(
    codigo BIGSERIAL,
    descricao VARCHAR(50) NOT NULL, 
    data_vencimento DATE NOT NULL,
    data_pagamento DATE,
    valor DECIMAL(10,2),
    tipo VARCHAR(20),
    codigo_categoria BIGSERIAL,
    codigo_pessoa BIGSERIAL,

    PRIMARY KEY(codigo),
    FOREIGN KEY(codigo_categoria) REFERENCES categoria(codigo),
	FOREIGN KEY(codigo_categoria) REFERENCES pessoa(codigo)

);

INSERT INTO lancamento(descricao, data_vencimento, data_pagamento, valor, tipo, codigo_categoria, codigo_pessoa)
VALUES('Novo Lancamento', '2018-07-26', null, '5800.66', 'RECEITA', 1, 2);

INSERT INTO lancamento(descricao, data_vencimento, data_pagamento, valor, tipo, codigo_categoria, codigo_pessoa)
VALUES('Mais um Lancamento', '2018-07-20', null, '6000.00', 'DESPESA', 3, 3);

INSERT INTO lancamento(descricao, data_vencimento, data_pagamento, valor, tipo, codigo_categoria, codigo_pessoa)
VALUES('Despesas do Fim de Ano', '2018-06-22', null, '6000.00', 'DESPESA', 2, 2);

INSERT INTO lancamento(descricao, data_vencimento, data_pagamento, valor, tipo, codigo_categoria, codigo_pessoa)
VALUES('Despesas Festas', '2018-07-26', null, '2000.00', 'DESPESA', 3, 3);