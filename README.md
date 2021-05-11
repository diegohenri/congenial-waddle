### Overview sobre o projeto
API REST para apontamento de horas de usuários em projetos.

O projeto foi desenvolvido na linguagem Kotlin, uma linguagem multiplataforma funcional que compila para a Maquina Virtual Java (JVM)

Foi escolhida um padrão de arquitetura conhecido como Clean Architecture, possui dois módulos principais, um modulo responsável por resolver dependências da aplicação e outro módulo responsável pela execução e garantia de toda a regra de negócio.
### Instruções de inicialização
#### Ambiente - Pré-requisitos
O projeto utiliza o banco de dados relacional PostgreSQL que é disponibilizado pela infraestrutura docker.
É um pré-requisito que o executor possua o docker e docker-compose instalados.

Também faz-se necessário possuir a versão 11 do java, para  execução do projeto.

Uma collection postman está incluso no projeto, na pasta raiz, com todos os endpoints disponíveis com exemplo de execução.

#### Instruções
- Execute o comando `docker-compose up -d` para disponibilizar a infraestrutura do banco de dados PostgreSQL
- Logo após a mensagem de configuramação do docker, execute o comando `./gradlew bootRun` para iniciar a aplicação.

#### Consumindo os endpoints

Um projeto com as collections do postman está disponível na raiz do projeto, com instruçõs para utilização.

- Para criar um usuário, utilize o endpoint "Create" da pasta "User"
- Para efetuar o login, utilize o endpoint "Login" da pasta Auth com as credenciais craidas no passo anterior.
- Após receber o token de confirmação de autenticação, copie o token JWT e cole nas configurações de autenticação da pasta principal


### Regras de negócio
#### Login

##### Descrição da funcionalidade
A funcionalidade de login recebe como parâmetros: login e senha e retorna o token de autenticação e as informações do usuário.

##### Regras de negócio
* RN1 - Autenticar usuário no sistema utilizando login e senha


#### Registro de tempo

##### Descrição da funcionalidade
A funcionalidade de registro de tempo recebe como parâmetros: projeto, Data e hora de inicio e Data e hora de finalização.

##### Regras de negócio
* RN1 - Validar se a data e hora de inicio é menor que a data e hora de finalização
* RN2 - Validar se o usuário tem permissão para registrar horas no projeto informado


#### Cadastro de usuário

##### Descrição da funcionalidade
A funcionalidade de cadastro de usuário recebe como parâmetros: nome, email, login, senha e confirmação de senha.

##### Regras de negócio
* RN1 - Validar se o cliente informou um e-mail válido.
* RN2 - Validar se o e-mail já está em uso.
* RN3 - Validar se o login já existe.
* RN4 - Validar se as senhas coincidem.

#### Alteração dos dados cadastrais do usuário

##### Descrição da funcionalidade
A funcionalidade de alteração cadastral do usuário recebe como parâmetros: nome, email.

##### Regras de negócio
* RN1 - Validar se o cliente informou um e-mail válido.
* RN2 - Validar se o e-mail já está em uso.

#### Consulta de usuário cadastrado

##### Descrição da funcionalidade
A funcionalidade de consulta de usuário cadastrado recebe o token de autenticação do usuário e retorna suas informações cadastrais.

#### Registro de projeto

##### Descrição da funcionalidade
A funcionalidade de registro de projeto recebe como parâmetros: titulo, descrição e lista de usuários participantes
* RN1 - Adicionar o usuário que criou o projeto como usuário participante.

#### Alteração de projeto

##### Descrição da funcionalidade
A funcionalidade de alteração de projeto recebe como parâmetros: id do projeto a ser alterado, titulo, descrição e lista de usuários participantes

#### Listagem de projetos

##### Descrição da funcionalidade
A funcionalidade de listagem de projetos retorna uma lista com todos os projetos

#### Detalhamento de projeto

##### Descrição da funcionalidade
A funcionalidade de detalhamento de projeto retorna as informações do projeto

#### Listagem de horas por projeto

##### Descrição da funcionalidade
A funcionalidade de listagem de registro de horas por projeto recebe como parâmetro o id do projeto e retorna uma lista com os registros de horas.


### Listagem de tarefas

* Configuração inicial do projeto
    
    Descrição: *Boillerplate das tecnologias necessárias para o desenvolvimento do sistema.*

* Mapeamentos das entidades de usuário e autenticação

    Descrição: *Mapear VOs de domínio, payloads e converters*

* Configuração da camada de autenticação e autorização
    
    Descrição: *Configuração inicial dos métodos de criptografia e segregação de acesso*
    
* Configuração do token JWT
    
    Descrição: *Configuração da camada de manutenção de token JWT*
    
* Desenvolvimento do endpoint e das regras de registro de usuário.

* Desenvolvimento do endpoint e das regras de login

* Desenvolvimento do endpoint e das regras de alteração cadastral do usuário.

* Desenvolvimento do endpoint de busca de informações do usuário

* Desenvolvimento do endpoint de consulta de projetos 

* Desenvolvimento do endpoint de consulta de um único projeto

* Desenvolvimento do endpoint e das regras de registro de projetos

* Desenvolvimento do endpoint e das regras de alteração de projetos

* Desenvolvimento do endpoint de listagem de registros de tempo por projeto

* Desenvolvimento do endpoint de ateração de registro de tempo
