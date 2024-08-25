# Sudharma-Warune-21BCE2057

# Turn-based Chess-like Game with WebSocket Communication

## Overview

This project implements a turn-based chess-like game with a server-client architecture using WebSockets for real-time communication. The backend is built with Java 17 and Spring Boot, while the frontend is developed with JavaScript and CSS.

## Components

1. **Server**: Handles game logic, WebSocket communication, and game state management.
2. **WebSocket Layer**: Manages real-time communication between the server and clients.
3. **Web Client**: Provides a web-based user interface for the game.

## Game Rules

The game is played on a 5x5 grid with each player controlling 5 characters. Players alternate turns to move characters and capture opponent's pieces.

## Setup and Run Instructions

### Server (Backend)

#### Prerequisites

- Java 17
- Maven

#### Setup

1. Clone the repository:
   ```bash
   git clone https://github.com/your-username/turn-based-chess-game.git

2. Navigate to the backend directory:
   ```bash
   cd turn-based-chess-game/backend

3. Build the project:
   ```bash
   mvn clean install

4. Run the Spring Boot application:
   ```bash
   mvn spring-boot:run
The server will start on port 8090.

### Web Client (Frontend)
#### Prerequisites

A modern web browser (e.g., Chrome, Firefox)

#### Setup

   Navigate to the frontend directory:
      ```bash
      cd turn-based-chess-game/frontend

Open index.html in a web browser to view the game interface.

#### WebSocket Communication
The WebSocket server endpoint is ws://localhost:8090/game.

## Game Rules

### Game Setup

- The game is played on a 5x5 grid by two players.
- Each player controls 5 characters: a mix of Pawns, Hero1, and Hero2.
- Players arrange their characters on their respective starting rows.

### Characters and Movement

1. **Pawn**:
   - Moves one block in any direction (Left, Right, Forward, Backward).
   - Example command: `P1:L` (Pawn 1 moves Left).

2. **Hero1**:
   - Moves two blocks straight in any direction.
   - Eliminates any opponent's character in its path.
   - Example command: `H1:F` (Hero1 moves Forward).

3. **Hero2**:
   - Moves two blocks diagonally in any direction.
   - Eliminates any opponent's character in its path.
   - Example command: `H2:BR` (Hero2 moves Backward-Right).

### Game Flow

- Players alternate turns, making one move per turn.
- If a character lands on an opponent's character, the opponent's character is removed.
- Invalid moves are not allowed, and players must retry their turn if an invalid move is made.
- The game ends when one player eliminates all of the opponent’s characters.
