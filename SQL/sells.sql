CREATE TABLE CLIENTE (
rg_client CHAR(9),
nome_client VARCHAR(45),
cpf_client CHAR(11),
ddn_client DATE,
endereco_client VARCHAR(50),
numEnd_client VARCHAR(6),
tel_client CHAR(11),
fid_client VARCHAR(15),
CONSTRAINT PK_CLIENT PRIMARY KEY (rg_client)
); 

CREATE TABLE PRODUTO (
cod_prod INT NOT NULL,
preco_prod NUMBER NOT NULL,
descontoFid NUMBER,
validade_prod DATE NOT NULL,
CONSTRAINT PK_PRODUTO PRIMARY KEY (cod_prod)
);


CREATE TABLE VENDACLIENTE(
id_venda INT,
rg_client CHAR(9),
cod_prod INT,
preco_venda NUMBER,
descontoFid NUMBER,
dt_venda DATE,
CONSTRAINT PK_VENDACLIENTE PRIMARY KEY (id_venda),
CONSTRAINT FK_RG_CLIENT FOREIGN KEY (rg_client) REFERENCES CLIENTE(rg_client),
CONSTRAINT FK_COD_PROD FOREIGN KEY (cod_prod) REFERENCES PRODUTO(cod_prod)
);

CREATE TABLE FORNECEDOR(
cnpj_fornec CHAR(14),
nome_fornec VARCHAR(45),
tel_fornec VARCHAR(11),
status_fornec NUMBER,
CONSTRAINT PK_FORNECEDOR PRIMARY KEY (cnpj_fornec)
);

CREATE TABLE ENCOMENDARPROD(
id_compra INT,
cod_prod INT,
cnpj_fornec CHAR(14),
valor_total_enc NUMBER,
dtCompra_enc DATE,
dtEntrega_enc DATE,
CONSTRAINT PK_ENCOMENDAPROD PRIMARY KEY(id_compra),
CONSTRAINT FK_COD_PROD_REF_ENCOMENDAPROD FOREIGN KEY (cod_prod) REFERENCES PRODUTO(cod_prod),
CONSTRAINT FK_CNPJ_FORNEC FOREIGN KEY (cnpj_fornec) REFERENCES FORNECEDOR(cnpj_fornec)
);

select * from cliente;

insert into Cliente (rg_client, nome_client, cpf_client, ddn_client, endereco_client, numEnd_client, tel_client, fid_client) 
values ('908766547','Jorge','43476895088','13/03/1976','Rua Boitupeva','90','11976547890','ouro');

DELETE FROM CLIENTE
WHERE RG_CLIENT = '504319632';
