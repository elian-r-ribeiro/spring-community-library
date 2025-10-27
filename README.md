
# Community Library

Olá! Esse é meu primeiro projeto "completo" usando Spring Boot com JPA e (futuramente) Spring Security JWT. Se trata de uma A.P.I. para uma biblioteca, de controle de estoque e locação de livros.


## Rodando o projeto
Para começar, você precisa ter o Java, o Maven, o Docker com o MySQL instalado (ou então o MySQL intalado diretamente em sua máquina, XAMPP ou qualquer ferramenta do gênero), uma forma de enviar requisições HTTP para a A.P.I. (como Postman ou Insomnia) e uma I.D.E. de sua preferência com suporte a Java (opcional). 

Após se certificar de que tudo acima está de acordo, você precisa abrir seu console na pasta do projeto e rodar o comando "maven clean install". Isso irá instalar todas as dependências necessárias no projeto.

Inicie o MySQL e crie um banco de dados para o projeto, como "community_library". Após isso, vá até "spring-community-library/src/main/resources/application.properties". Lá, você irá fazer os seguintes ajustes:

spring.datasource.url=url. Aqui você irá colocar a URL do seu banco de dados, como exemplo do meu: spring.datasource.url=jdbc:mysql://localhost:3306/community_library

spring.datasource.username=nome. Aqui é onde você irá colocar o nome de usuário para acessar seu banco de dados. Por exemplo: spring.datasource.username=elian

spring.datasource.password=senha. Aqui é a senha para acessar seu banco de dados. Por exemplo: spring.datasource.password=123456

Opcional: server.port=porta. Caso haja algum clonflito de portas, você pode alterar a porta do projeto aqui. Por exemplo: server.port=3030

Após realizar essas configurações, seu projeto deve estar pronto para rodar. Se estiver em uma I.D.E., basta utilizar o runner dela, caso não, abra o console na pasta do projeto e digite: mvn spring-boot:run. Caso nenhuma mensagem de erro apareça, o projeto está funcionando corretamente.


## End-points

Abra o aplicativo que irá utilizar para realizar requisições HTTP e teste:

#### Criar novo usuário

```http
  POST http://localhost:porta_definida_nas_configurações/users
```

| Body(JSON)   | Tipo       | Descrição                           |
| :---------- | :--------- | :---------------------------------- |
| `name` | `String` | **Obrigatório**. O nome do usuário |
| `email` | `String` | **Obrigatório**. O email do usuário |
| `active` | `boolean` | Se o usuário está ativo |

#### Editar um usuário

```http
  PUT http://localhost:porta_definida_nas_configurações/users
```

| Body(JSON)   | Tipo       | Descrição                           |
| :---------- | :--------- | :---------------------------------- |
| `name` | `String` | O nome do usuário |
| `email` | `String` | O email do usuário |
| `active` | `boolean` | Se o usuário está ativo |

#### Deletar usuário

```http
  DELETE http://localhost:porta_definida_nas_configurações/users/id
```

#### Listar todos os usuário

```http
  GET http://localhost:porta_definida_nas_configurações/users
```

#### Listar usuário por ID

```http
  GET http://localhost:porta_definida_nas_configurações/users/id
```

#### Criar um livro

```http
  POST http://localhost:porta_definida_nas_configurações/books
```

| Body(JSON)   | Tipo       | Descrição                           |
| :---------- | :--------- | :---------------------------------- |
| `title` | `String` | **Obrigatório.** O título do livro |
| `author` | `String` | **Obrigatório.** O autor do livro |
| `isbn` | `String` | **Obrigatório.** O isbn do livro |
| `availableCopies` | `Integer` | A quantidade de cópias do livro disponívies |
| `totalCopies` | `Integer` | A quantidade total de cópias do livro |

#### Editar um livro

```http
  PUT http://localhost:porta_definida_nas_configurações/books
```

| Body(JSON)   | Tipo       | Descrição                           |
| :---------- | :--------- | :---------------------------------- |
| `title` | `String` | O título do livro |
| `author` | `String` | O autor do livro |
| `isbn` | `String` | O isbn do livro |
| `availableCopies` | `Integer` | A quantidade de cópias do livro disponívies |
| `totalCopies` | `Integer` | A quantidade total de cópias do livro |

#### Deletar livro

```http
  DELETE http://localhost:porta_definida_nas_configurações/books/id
```

#### Listar todos os livros

```http
  GET http://localhost:porta_definida_nas_configurações/books
```

#### Listar livros por ID

```http
  GET http://localhost:porta_definida_nas_configurações/books/id
```

#### Criar uma locação de livro

```http
  POST http://localhost:porta_definida_nas_configurações/loans
```

| Body(JSON)   | Tipo       | Descrição                           |
| :---------- | :--------- | :---------------------------------- |
| `userId` | `Integer` | **Obrigatório.** O ID do usuário que fez a locação |
| `bookId` | `Integer` | **Obrigatório.** O ID do livro locado |
| `loanDate` | `LocalDate` | **Obrigatório.** A data em que a locação foi realizada |
| `returnDate` | `LocalDate` | A data que o livro foi retornado |
| `returned` | `Boolean` | Se o livro já foi retornado ou não |

#### Editar uma locação

```http
  PUT http://localhost:porta_definida_nas_configurações/loans
```

| Body(JSON)   | Tipo       | Descrição                           |
| :---------- | :--------- | :---------------------------------- |
| `userId` | `Integer` | O ID do usuário que fez a locação |
| `bookId` | `Integer` | O ID do livro locado |
| `loanDate` | `LocalDate` | A data em que a locação foi realizada |
| `returnDate` | `LocalDate` | A data que o livro foi retornado |
| `returned` | `Boolean` | Se o livro já foi retornado ou não |

#### Deletar uma locação

```http
  DELETE http://localhost:porta_definida_nas_configurações/loans/id
```

#### Listar todas as locações

```http
  GET http://localhost:porta_definida_nas_configurações/loans
```

#### Listar locação por ID

```http
  GET http://localhost:porta_definida_nas_configurações/loans/id
```


## Considerações finais
Todos os campos possuem validações como tamanho mínimo, se o ID informado existe ou não, se há livros disponíveis para locação, etc. Se você encontrar alguma inconsistência, problema ou bug, peço que por gentileza abra uma issue. 