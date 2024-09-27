# Todo List - Prova de Conceito

Este projeto é uma **prova de conceito** de um sistema de Todo List, desenvolvido utilizando **Java Spring**. Ele demonstra as funcionalidades básicas de uma aplicação de gerenciamento de tarefas, como adicionar, editar e excluir itens de uma lista de afazeres.

## Pré-requisitos

Para rodar o projeto localmente, você precisa ter as seguintes ferramentas instaladas:

- **Maven**
- **Docker** (se desejar rodar o projeto em container)

## Rodando o Projeto

1. Clone este repositório:

   ```bash
   git clone https://github.com/KY-U/todo-list.git
   ```
2. Entre na pasta do projeto:

    ```bash
   cd todo-list
    ```

3. Para rodar o projeto, você tem duas opções:
   - Para rodar diretamente com maven:

     ```bash
     mvn spring-boot:run
     ```
    
     O projeto estará disponível em https://localhost:8080/

   - Para rodar em um container Docker:
     ```bash
      docker compose up
     ```

     O projeto estará disponível em https://localhost:80/