# UW Navigator

**Status**: Complete

## Overview

UW Navigator is a **Java-based application** designed to compute the **shortest walking path** between two buildings on the **UW-Madison campus**. The application consists of a **frontend** and **backend** that together allow users to:
- Load campus walking data.
- Input a start and destination building.
- Calculate the shortest walking path and total travel time.
- View statistics about the campus walking map.

This project was created as part of a **course project** during my sophomore year at UW-Madison and serves as a foundational exercise in **data structures, algorithms, and Java programming**.

## Features

1. **Shortest Path Calculation**:
   - Uses **Dijkstra's algorithm** to compute the shortest walking path.
   - Outputs the total walking time and step-by-step directions between buildings.

2. **Frontend-Backend Integration**:
   - **Frontend**: A simple command-line interface for user interaction.
   - **Backend**: Handles file reading, graph creation, and pathfinding logic.

3. **Statistics Overview**:
   - Displays metrics such as:
     - Total buildings (nodes) in the graph.
     - Total connections (edges) between buildings.
     - Total walking time across all connections.

---

## Tools & Technologies

- **Java**:
  - Core language for both the frontend and backend.
- **Graph Data Structures**:
  - Custom **GraphADT** and **DijkstraGraph** implementations for managing nodes, edges, and weights.
- **File I/O**:
  - Reads campus data from user-specified input files.
- **Object-Oriented Design**:
  - Clean separation of frontend, backend, and results classes/interfaces.

---

## How It Works

1. **Backend**:
   - Reads a file containing building connections (edges) and walking times (weights).
   - Example input format:
     ```
     "Building A" -- "Building B" [seconds=300];
     "Building B" -- "Building C" [seconds=400];
     ```
   - Constructs a graph of buildings (nodes) and connections (edges).

2. **Frontend**:
   - Command-line menu for user interaction:
     - **Load data file**: Reads building connections from a file.
     - **Show statistics**: Displays campus-wide statistics (buildings, connections, total walking time).
     - **Find shortest path**: Accepts start and destination buildings and outputs the shortest path and time.

3. **Shortest Path Calculation**:
   - The backend uses **Dijkstra's algorithm** to compute:
     - The **shortest path** as a list of buildings.
     - The **walking time** for each step in the path.
     - The **total time** for the entire trip.

