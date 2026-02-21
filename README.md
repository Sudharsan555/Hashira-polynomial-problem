# Hashira Polynomial Problem

## 📌 Problem Statement

This project solves a polynomial reconstruction problem where the constant term of an unknown polynomial must be determined from a given dataset.

The input is provided as a JSON file containing:

- A value **k** → minimum number of valid points required
- Multiple coordinate points *(x, y)*  
- Some points may be invalid or noisy

The objective is to:

✔ Extract valid coordinate points  
✔ Select the required number of points  
✔ Reconstruct the polynomial  
✔ Compute the **constant term (c)**

---

## 💡 Key Idea

A polynomial of degree *(k − 1)* is uniquely determined by **k distinct points**.

By applying **Lagrange Interpolation**, we can reconstruct the polynomial and directly compute its constant term without explicitly solving large equation systems.

---

## ⚙️ Solving Method

### Step 1 — Input Parsing
- Read JSON input file
- Extract value of **k**
- Extract valid *(x, y)* coordinate pairs

### Step 2 — Data Validation
- Ignore malformed / incomplete entries
- Store only valid points

### Step 3 — Polynomial Reconstruction
Using:

\[
c = \sum_{i=0}^{k-1} y_i \cdot L_i(0)
\]

---

## 🧠 Algorithm Used

**Lagrange Polynomial Interpolation**

✔ Efficient  
✔ Mathematically optimal  
✔ No matrix operations required

---

## 🏗 Code Explanation

### `PolynomialConstant.java`

✔ Reads JSON  
✔ Extracts k  
✔ Parses points  
✔ Computes constant term  
✔ Displays result per test case

---

## 📂 Folder Structure

```
Hashira-polynomial-problem/
│
├── src/
│   └── PolynomialConstant.java
│
├── input.json
│
└── README.md
```

---

## ▶️ How To Run

### Compile
```
javac src/PolynomialConstant.java
```

### Execute
```
java -cp src PolynomialConstant
```

---

## ✅ Test Case 1

### Input

```json
{
  "keys": { "k": 3 },

  "1": { "x": 1, "y": 6 },
  "2": { "x": 2, "y": 11 },
  "3": { "x": 3, "y": 18 }
}
```

### Explanation

These points belong to polynomial:

\[
f(x) = x^2 + 2x + 3
\]

### Output

```
Test Case 1:
Constant term (c): 3
```

---

## ✅ Test Case 2

### Input

```json
{
  "keys": { "k": 4 },

  "1": { "x": 1, "y": 10 },
  "2": { "x": 2, "y": 21 },
  "3": { "x": 3, "y": 38 },
  "4": { "x": 4, "y": 61 }
}
```

### Explanation

These points belong to polynomial:

\[
f(x) = x^3 + 2x^2 + 3x + 4
\]

### Output

```
Test Case 2:
Constant term (c): 4
```

---

## 🚀 Skills Demonstrated

✔ Algorithmic Thinking  
✔ Mathematical Problem Solving  
✔ JSON Data Parsing  
✔ Java Implementation  
✔ Clean Code Practices  

---

## 🎯 Why This Project Matters

✔ Converts theory → implementation  
✔ Handles structured data  
✔ Demonstrates interpolation technique  
✔ Reflects strong problem-solving ability  

---

## 👨‍💻 Author

**Sudharsan V**

Focused on:

✔ Software Engineering  
✔ Algorithms & Data Structures  
✔ System Design  
✔ Scalable Code  

---

## ⭐ Closing Note

> *Understand the model → Apply optimal algorithm → Deliver clean solution*

A fundamental engineering principle.
