---

# 🎓 APS: Integrated Academic Project

![Language](https://img.shields.io/badge/Language-Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Status](https://img.shields.io/badge/Status-Completed-success?style=for-the-badge)

This repository hosts the **Supervised Practical Activity (APS)** developed during the **5th Semester** of my Computer Science degree. It represents a deep dive into algorithmic complexity, optimization techniques, and the practical challenges of solving NP-Hard problems.

---

## 📘 Project Theme: Traveling Salesperson Problem (TSP) Optimizer

**Objective:**
To implement, analyze, and benchmark different algorithmic approaches—both heuristic and exact—to solve the classic Traveling Salesperson Problem (TSP), measuring the trade-offs between computational time and route accuracy.

### 🎯 Key Features
* **Heuristic Approach (Fast Approximation):** Implementation of the Greedy Algorithm (Nearest Neighbor) providing extremely fast $O(n^2)$ execution times for large datasets, at the cost of finding a sub-optimal route.
* **Exact Solutions (The Perfect Route):** Implementation of a Brute-Force Exhaustive Search and a highly optimized **Branch and Bound** algorithm using recursion and backtracking to guarantee the absolute shortest path.
* **Algorithmic Pruning:** Practical demonstration of how state-space tree pruning in Branch and Bound drastically reduces execution time compared to pure factorial $O(n!)$ Brute Force.
* **TSPLIB Integration:** A custom file parser designed to read standard academic `.tsp` datasets (e.g., `a280.tsp`), calculating Euclidean distances and generating the Adjacency Matrix automatically.

---

## 👥 Contributors (Team)

* **Ana Paula Garbin** - *Developer*
* **Gabriel Henrique de Morais** - *Developer*
* **Gabriel S. B. Paloni** - *Developer*
* **Graziela Lopes Romualdo** - *Developer*

---

## 🛠️ Technical Evolution

This project was pivotal in mastering advanced Computer Science fundamentals:
* **Algorithm Design:** Transitioned from basic logic to complex graph modeling (Adjacency Matrices) and advanced recursive structures.
* **Complexity Analysis:** Gained practical understanding of combinatorial explosion, testing algorithms that break standard computing limits as input size grows.
* **Optimization:** Learned that finding the "perfect" answer is often too costly, and mastering how to make exact algorithms viable through intelligent mathematical cuts (bounding).

### Technologies Used
* **Language:** Java (Pure JDK, no external frameworks)
* **Data Structures:** Graphs, Adjacency Matrices, Dynamic Lists
* **Testing Standards:** TSPLIB (Academic dataset standard)
* **Version Control:** Git

---

## 🚀 How to Run
1.  **Clone the repository:**
    ```bash
    git clone [https://github.com/aps-quinto-semestre.git](https://github.com/gabrielpaloni/aps-quinto-semestre.git)
    ```
2.  **Setup Environment:**
    * Open the project in your preferred Java IDE (IntelliJ IDEA, Eclipse)

---
