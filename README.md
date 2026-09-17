# SGM - Sistema de Gerenciamento de Manutenção (Dilly Sports)

Sistema de gerenciamento de manutenção industrial desenvolvido em Java, com persistência em banco de dados MySQL, criado como projeto de portfólio para o curso técnico em Desenvolvimento de Sistemas (SENAI).

## 📋 Sobre o projeto

O SGM permite cadastrar máquinas de uma linha de produção, abrir e acompanhar ordens de serviço de manutenção vinculadas a elas, e calcular métricas de manutenção (MTTR) com base no histórico real de reparos.

## 🛠️ Tecnologias utilizadas

- **Java** (JDK) — lógica da aplicação
- **MySQL** — persistência de dados
- **JDBC** (via mysql-connector-j) — conexão entre Java e o banco
- **Git/GitHub** — controle de versão

## ⚙️ Funcionalidades

### Máquinas
- Cadastrar, listar, atualizar e excluir máquinas
- Cada máquina possui: nome, setor, criticidade (BAIXA/MEDIA/ALTA) e status (ATIVA/EM_MANUTENCAO/INATIVA)

### Ordens de Serviço
- Abrir, listar, atualizar status, concluir e excluir ordens de serviço
- Cada ordem de serviço é vinculada a uma máquina (chave estrangeira) e possui: descrição, tipo (CORRETIVA/PREVENTIVA), prioridade, status e datas de abertura/conclusão

### Métricas
- Cálculo de **MTTR** (Tempo Médio de Reparo) por máquina, com base no histórico de ordens de serviço concluídas


## 🗂️ Estrutura do projeto

dilly-sports-sgm/
├── sql/
│   └── schema.sql          # script de criação do banco de dados
└── src/
    ├── Main.java            # menu principal (console)
    ├── Maquina.java          # modelo de dados de Máquina
    ├── MaquinaDAO.java       # acesso ao banco para Máquina (CRUD)
    ├── ConexaoBD.java        # conexão com o MySQL
    ├── OrdemServico.java     # modelo de dados de Ordem de Serviço
    └── OrdemServicoDAO.java  # acesso ao banco para Ordem de Serviço (CRUD + métricas)


## ▶️ Como rodar o projeto

1. Instale o MySQL e crie o banco executando o script `sql/schema.sql`
2. Baixe o [MySQL Connector/J](https://dev.mysql.com/downloads/connector/j/) e ajuste o caminho do `.jar` no comando abaixo
3. Configure o usuário/senha do banco em `ConexaoBD.java`
4. Compile e execute:

```bash
cd src
javac *.java
java -cp ".;CAMINHO_DO_CONECTOR.jar" "-Dfile.encoding=UTF-8" Main
```

## 🚧 Próximos passos (roadmap)

- Cálculo de MTBF (Tempo Médio Entre Falhas)
- Leitura de QR code / código de barras vinculada às máquinas (versão mobile futura)
- Sincronização offline
- Interface gráfica

## 👤 Autor

Tiago — estudante de Desenvolvimento de Sistemas (SENAI)