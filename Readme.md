# Minecraft Engine (Java + LWJGL)

Projeto educacional de desenvolvimento de uma engine gráfica simples em Java, utilizando LWJGL, GLFW e OpenGL, com foco em entender os fundamentos de:

Game loop

Renderização 2D

Entrada de teclado

Organização inicial de engine

Arquitetura básica de jogos

Este projeto não é um clone completo do Minecraft, mas um estudo técnico inspirado na ideia de construir um jogo do zero, começando pela base a fim de aprender novas tecnologias e aprimorar meus conhecimentos na linguagem Java.

## Objetivo do Projeto

O objetivo principal é aprender como jogos funcionam internamente, evitando abstrações prontas e entendendo diretamente:

Como uma janela OpenGL é criada

Como eventos de teclado são capturados via GLFW

Como o loop principal de um jogo funciona

Como separar responsabilidades entre input, update e render

Este repositório representa o estado inicial funcional da engine.

## Funcionalidades Atuais

Criação de janela com GLFW

Loop principal de renderização

Renderização de um quadrado via OpenGL (modo imediato)

Sistema de input por teclado (WASD)

Movimento contínuo baseado em estado de teclas

Encerramento seguro da aplicação (ESC)

## Estrutura do Projeto
.
├── app
│   ├── src
│   │   ├── main
│   │   │   └── java
│   │   │       └── minecraft
│   │   │           ├── App.java        # Classe de entrada
│   │   │           └── Render.java     # Loop, input e renderização
│   │   └── test
│   │       └── java
│   │           └── minecraft
│   │               └── AppTest.java
│   └── build.gradle
├── gradle
├── gradlew
├── settings.gradle
└── README.md

## Controles

W → mover para cima

S → mover para baixo

A → mover para a esquerda

D → mover para a direita

ESC → fechar a janela

## Tecnologias Utilizadas

Java

LWJGL

GLFW

OpenGL

Gradle

## Próximos Passos Planejados

Separação de responsabilidades (Player, Input, Renderer)

Introdução de delta time

Sistema de câmera 2D

Colisão básica

Organização em módulos de engine

Evolução para renderização moderna (VBO / VAO / shaders)

## Observações

Este projeto tem foco didático.
Algumas decisões (como OpenGL em modo imediato) são intencionais para facilitar o entendimento inicial.

A arquitetura será refinada progressivamente conforme o projeto evoluir.

## Autor

Kauã Richard
Estudante de Engenharia de Software
Projeto de estudo pessoal em gráficos e desenvolvimento de jogos
