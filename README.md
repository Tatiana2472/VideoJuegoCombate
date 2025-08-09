# 🎮 VideoJuegoCombate

Proyecto académico de Java para un **videojuego de combate por turnos** en consola.  
Desarrollado en **NetBeans (Ant)**, usando **Programación Orientada a Objetos**, herencia, estructuras de control y persistencia en base de datos.

## 📌 Características principales

- Dos jugadores ingresan su nombre y seleccionan un personaje de una raza específica:
  - **Humano** → armas de fuego (escopeta o rifle).
  - **Elfo** → magia (fuego, tierra, aire o agua).
  - **Orco** → armas cuerpo a cuerpo (hacha o martillo).
  - **Bestia** → puños o espada.
- Lógica de combate por turnos, con puntos de vida y ataques especiales.
- Métodos de sanación con efectos únicos por raza.
- Persistencia en base de datos (PostgreSQL / SQL Server / Oracle) para:
  - Personajes
  - Razas
  - Armas
  - Jugadores (partidas ganadas y perdidas)
- Código organizado en paquetes:
  - `modelo` → Clases principales del juego.
  - `controlador` → Lógica de combate y menús.
  - `basedatos` → Conexión y operaciones CRUD con JDBC.
  - `dao` → Clases DAO para realizar operaciones CRUD sobre las tablas (`JugadorDAO`, `PersonajeDAO`, etc.).
  - `principal` → Clase `Main` para iniciar el juego.

## 📂 Requisitos

- **Java JDK 17+**
- **NetBeans** con soporte para Ant.
- Motor de base de datos (PostgreSQL, SQL Server o Oracle XE/23c).
- JDBC driver correspondiente.

