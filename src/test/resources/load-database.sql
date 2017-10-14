INSERT INTO pessoa(nome, cpf) VALUES ('Iago','18273849228');
INSERT INTO pessoa(nome, cpf) VALUES ('Pedro','0192837488');
INSERT INTO pessoa(nome, cpf) VALUES ('Cauê','34565432123');
INSERT INTO pessoa(nome, cpf) VALUES ('Breno','91827384759');
INSERT INTO pessoa(nome, cpf) VALUES ('Thiago','19284637289');

INSERT INTO telefone(ddd, numero, codigo_pessoa)
VALUES ('11', '72819283719' , 1);
VALUES ('54', '9819283748', 2);
VALUES ('53', '83729182930', 3);
VALUES ('55', '72819465748', 4);
VALUES ('82', '12318291827', 5);

INSERT INTO endereco(logradouro, numero, complemento, bairro, cidade, estado, codigo_pessoa)
VALUES ('Rua José Manoel Camisa Nova', 324, 'apartamento 28 A', 'Jardim São Luis', 'São Paulo','SP', 1)
VALUES ('Rua José Manoel Camisa ', 324, 'apartamento 32 A', 'Morumbi', 'São Paulo','SP', 2)
VALUES ('Rua José Manoel ', 324, 'apartamento 23 A', 'Centro', 'São Paulo','SP', 3)
VALUES ('Rua José ', 324, 'apartamento 1 A', 'Capão Redondo', 'São Paulo','SP', 4)
VALUES ('Rua Das Nações', 324, 'apartamento 22 A', 'Campo Limpo', 'São Paulo','SP', 5)