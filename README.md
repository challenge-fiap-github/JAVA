# OdontoVision

## 1. Sobre a Aplicação
A aplicação **OdontoVision** foi desenvolvida para oferecer uma solução inovadora voltada à redução de sinistros no setor odontológico. Utilizando análise preditiva e Inteligência Artificial, o sistema é capaz de antecipar fraudes e detectar padrões de uso indevido de serviços odontológicos, otimizando assim a eficiência das operações e reduzindo custos. O sistema também inclui um aplicativo com gamificação para incentivar práticas preventivas de saúde bucal, melhorando a experiência dos pacientes e a qualidade do atendimento.

---

## 2. Sobre o Grupo

O grupo **OdontoVision** é composto por três integrantes, que se organizaram da seguinte maneira:

- **Sabrina da Motta Café (553568)**: Responsável pelas atividades de **Java Advanced** e **Quality Assurance**. Sabrina focou no desenvolvimento do back-end utilizando Java, implementando os principais serviços e a arquitetura do sistema.
- **Luis Henrique Oliveira da Silva (552692)**: Responsável pelas áreas de **Banco de Dados**, **DevOps** e **Mobile**. Luis foi o encarregado de configurar os pipelines de integração contínua e a estrutura de banco de dados.
- **Matheus Duarte Oliveira (554199)**: Responsável pelas áreas de **Inteligência Artificial** e **C# com .NET**. Matheus implementou os algoritmos de análise preditiva e a lógica de detecção de fraudes no projeto.

---

## 3. Instruções para Executar a Aplicação

Para rodar a aplicação **OdontoVision**, siga as instruções abaixo:

1. **Pré-requisitos**:
   - Tenha o **Java JDK 17** ou superior instalado em sua máquina.
   - Certifique-se de ter o **Maven** configurado para gerenciar as dependências do projeto.
   - O banco de dados utilizado é o **SQL Developer**. Instale e configure o banco de dados localmente.

2. **Passos para execução**:
   - Clone o repositório em sua máquina:
     ```bash
     git clone <link_do_repositorio>
     ```
   - Navegue até o diretório raiz do projeto:
     ```bash
     cd JAVA-main
     ```
   - Execute o comando para compilar e instalar as dependências:
     ```bash
     mvn clean install
     ```
   - Após a instalação, inicie a aplicação:
     ```bash
     mvn spring-boot:run
     ```

3. **Configuração do Banco de Dados**:
   - Certifique-se de que o banco **SQL Developer** esteja configurado e rodando.
   - Configure o arquivo `application.properties` com as credenciais corretas do seu banco de dados local, ajustando as seguintes propriedades:
     ```properties
     spring.datasource.url=jdbc:oracle:thin:@localhost:1521:xe
     spring.datasource.username=seu_usuario
     spring.datasource.password=sua_senha
     spring.datasource.driver-class-name=oracle.jdbc.OracleDriver
     ```
   - Certifique-se de que as tabelas necessárias estejam criadas ou que o script de migração seja executado no banco de dados.

---

## 4. Imagem dos Diagramas

**Macro**

![java](https://github.com/user-attachments/assets/8e1a85c5-0a0a-40b3-89d6-6dcff221468f)

**Micro**
![java2](https://github.com/user-attachments/assets/22c05088-9c30-4002-8a54-737b9ebe49d3)


---

## 5. Apresentação em Vídeo

Link para o vídeo que apresenta a **Proposta Tecnológica**, o **público-alvo** da aplicação e os **problemas** que a solução se propõe a resolver:

[Vídeo de Apresentação](<link_do_video>)

---

## 6. Documentação da API

### Endpoints de Dentistas:
- **POST** `/api/dentistas`: Criar um novo dentista.
- **GET** `/api/dentistas`: Listar todos os dentistas.
- **PUT** `/api/dentistas/{id}`: Atualizar informações de um dentista.
- **GET** `/api/dentistas/{id}`: Buscar dentista por ID.
- **DELETE** `/api/dentistas/{id}`: Deletar dentista.

### Endpoints de Diagnósticos:
- **POST** `/api/diagnosticos`: Criar um novo diagnóstico.
- **GET** `/api/diagnosticos`: Listar todos os diagnósticos.
- **GET** `/api/diagnosticos/{id}`: Buscar diagnóstico por ID.
- **PUT** `/api/diagnosticos/{id}`: Atualizar diagnóstico existente.
- **DELETE** `/api/diagnosticos/{id}`: Deletar diagnóstico.

### Endpoints de Pacientes:
- **POST** `/api/pacientes`: Criar um novo paciente.
- **GET** `/api/pacientes`: Listar todos os pacientes.
- **GET** `/api/pacientes/{id}`: Buscar paciente por ID.
- **PUT** `/api/pacientes/{id}`: Atualizar informações de um paciente.
- **DELETE** `/api/pacientes/{id}`: Deletar paciente.

### Endpoints de Procedimentos:
- **POST** `/api/procedimentos`: Criar um novo procedimento.
- **GET** `/api/procedimentos`: Listar todos os procedimentos.
- **GET** `/api/procedimentos/{id}`: Buscar procedimento por ID.
- **PUT** `/api/procedimentos/{id}`: Atualizar informações de um procedimento.
- **DELETE** `/api/procedimentos/{id}`: Deletar procedimento.
- **GET** `/api/procedimentos/suspeitas-fraude`: Listar procedimentos com suspeita de fraude.

### Endpoints de Sinistros:
- **POST** `/api/sinistros`: Criar um novo sinistro.
- **GET** `/api/sinistros`: Listar todos os sinistros.
- **GET** `/api/sinistros/{id}`: Buscar sinistro por ID.
- **PUT** `/api/sinistros/{id}`: Atualizar informações de um sinistro.
- **DELETE** `/api/sinistros/{id}`: Deletar sinistro.


Este README foi desenvolvido para fornecer todas as informações necessárias para execução e entendimento da aplicação **OdontoVision**.
