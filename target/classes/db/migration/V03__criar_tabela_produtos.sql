CREATE TABLE produto (
    id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(50) NOT NULL,
    preco DECIMAL(6) NOT NULL, 
    quantidade INT NOT NULL,
    id_departamento BIGINT(20),
    FOREIGN KEY (id_departamento) REFERENCES departamento(id)
    ) 
    ENGINE=InnoDB DEFAULT CHARSET=UTF8;