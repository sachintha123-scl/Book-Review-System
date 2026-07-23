
# Book Review & Recommendation System

A console-based Java application for cataloging books, collecting reviews, and browsing a library — built entirely on **custom, hand-rolled data structures** (no `java.util.List`, `Stack`, `Queue`, or `TreeMap` shortcuts). It's a demonstration of core CS fundamentals: binary search trees, linked lists, stacks, and queues, all wired together into a working, menu-driven app.

---

## Features

- **Book catalog** backed by a Binary Search Tree, keyed on Book ID, for O(log n) average lookups and sorted in-order traversal
- **Add, search, and delete books** by ID or title keyword
- **Add reviews** (1–5 star rating + comment) to any book, with live average-rating calculation
- **Recent Activity feed** — a fixed-size queue (last 5 reviews) showing the latest community activity
- **Recently Viewed history** — a stack that tracks every book you've looked at, with the option to pop the most recent entry
- **Custom data structures built from scratch:**
  - `MyLinkedList<T>` — singly linked list with iterator support
  - `MyStack<T>` — LIFO stack (linked-node based)
  - `MyQueue<T>` — FIFO queue with optional max-capacity eviction
  - `BookBST` — binary search tree with insert / search / delete / in-order traversal / keyword search
- Friendly input validation throughout (no crashes on bad input — just a re-prompt)
- Pre-seeded with 4 sample books on startup so there's always something to explore

---

## Screenshots

**Main menu & catalog view**

<img width="900" height="528" alt="01-main-menu" src="https://github.com/user-attachments/assets/53e089f0-7987-40b4-8f19-320fd5a1f36f"/>

**Adding a review and searching by title**

<img width="900" height="506" alt="02-add-review" src="https://github.com/user-attachments/assets/de943035-de44-435e-8de3-691e12ec48a8" />

**Recent activity feed & recently viewed history**

<img width="900" height="374" alt="03-activity-history" src="https://github.com/user-attachments/assets/e9fac654-2fa4-42ed-8083-a8e4003cc306" />

---

## Project Structure

```
BookReview System/
├── BookReviewSystem.java   # Entry point (main)
├── Library.java            # Menu loop, I/O, and all use-case logic
├── Book.java                # Book entity (BST node)
├── Review.java              # Review entity
├── BookBST.java             # Binary search tree catalog
├── MyLinkedList.java        # Custom singly linked list
├── MyStack.java              # Custom stack
├── MyQueue.java               # Custom queue
└── README.md
```

---

## How It Works

| Concept              | Data Structure | Used For                                      |
|----------------------|-----------------|------------------------------------------------|
| Book catalog          | `BookBST`       | Storing books by ID, sorted traversal, keyword search |
| Book reviews          | `MyLinkedList`  | Storing an arbitrary number of reviews per book |
| Recent activity feed  | `MyQueue`       | Last 5 reviews across the whole system (FIFO, capped) |
| Browsing history      | `MyStack`       | Most-recently-viewed books (LIFO)              |

Deleting a book with two children uses the classic **in-order successor** BST deletion strategy, preserving its reviews on the replacement node.

---

## Getting Started

### Prerequisites
- Java JDK 8 or later

### Compile
```bash
cd "BookReview System"
javac *.java
```

### Run
```bash
java BookReviewSystem
```

### Menu Options
```
 1. Display All Books
 2. Add a Book
 3. Add a Review to a Book
 4. Search Book by ID
 5. Search Books by Title
 6. Recent Activity
 7. View Reviews of a Book
 8. View Recently Viewed Books
 9. Delete a Book
 0. Exit
```

---

## Possible Future Improvements

- Persist the catalog to a file or database between sessions
- Sort/filter books by genre or average rating
- Balance the BST (e.g. AVL/Red-Black) to guarantee O(log n) in the worst case
- Unit tests for each custom data structure

---

## License

This project is open source and available for learning purposes. Feel free to fork it, study it, or build on top of it.

