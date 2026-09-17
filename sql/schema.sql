CREATE DATABASE sgm_dilly_sports;
USE sgm_dilly_sports;

CREATE TABLE maquina (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR (100) NOT NULL,
    setor VARCHAR (100) NOT NULL,
    criticidade ENUM ('BAIXA', 'MEDIA', 'ALTA') NOT NULL,
    status ENUM('ATIVA', 'EM_MANUTENCAO', 'INATIVA') NOT NULL

);

CREATE TABLE ordem_servico (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_maquina INT NOT NULL,
    descricao VARCHAR(255) NOT NULL,
    tipo ENUM('CORRETIVA', 'PREVENTIVA') NOT NULL,
    prioridade ENUM('BAIXA', 'MEDIA', 'ALTA') NOT NULL DEFAULT 'MEDIA',
    status ENUM('ABERTA', 'EM_ANDAMENTO', 'CONCLUIDA') NOT NULL DEFAULT 'ABERTA',
    data_abertura DATETIME DEFAULT CURRENT_TIMESTAMP,
    data_conclusao DATETIME NULL,
    FOREIGN KEY (id_maquina) REFERENCES maquina(id)
);