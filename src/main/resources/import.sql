/*PERSON*/
INSERT INTO tb_person (name, tel, type) VALUES ('Matheus Felipe', '(75) 99999-9999', 'funcionario');
INSERT INTO tb_person (name, tel, type) VALUES ('Daniel Souza', '(75) 99999-9999', 'cliente');
INSERT INTO tb_person (name, tel, type) VALUES ('Tahinan Souza', '(75) 99999-9999', 'fornecedor');

/*IRON*/
INSERT INTO tb_iron (id, person_id) VALUES (20, 2);
INSERT INTO tb_iron (id, person_id) VALUES (1, 2);

/*City*/
INSERT INTO tb_city (name, state, current_slaughter_price) VALUES ('Ribeira do Pombal', 'Bahia', 150.00);
INSERT INTO tb_city (name, state, current_slaughter_price) VALUES ('Cicero Dantas', 'Bahia', 200.00);

/*Branch*/
INSERT INTO tb_branch (name, cnpj, leather_price) VALUES ('Mata Branca', '000.000.000/0000-00', 2.20);