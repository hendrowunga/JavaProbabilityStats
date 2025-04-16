# Java Probability & Statistics Calculators

![Java](https://img.shields.io/badge/Java-1.8%2B-blue?style=flat&logo=java) [![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)

## Description

This repository contains a collection of Java programs designed for basic probability and statistics calculations, focusing on discrete random variables. The code is structured into packages for better organization and follows Object-Oriented principles.

The programs included are:

1.  **Expected Value and Variance Calculator (Package `number1`):** Calculates E[X] (Expected Value or Mean) and Var(X) (Variance) based on a given discrete probability distribution table (X values and their corresponding P(X) probabilities).
2.  **Binomial Distribution Calculator (Package `number2`):** Generates the probability distribution table for a Binomial Random Variable, representing the number of 'successes' (e.g., heads in coin flips) in a fixed number (K) of independent trials.

This project demonstrates modular code design and is suitable for learning basic probability/statistics concepts or as a quick computational tool.

## Table of Contents

*   [Features](#features)
*   [Prerequisites](#prerequisites)
*   [How to Use](#how-to-use)
    *   [1. Expected Value & Variance Calculator (`number1`)](#1-expected-value--variance-calculator-package-number1)
    *   [2. Binomial Distribution Calculator (`number2`)](#2-binomial-distribution-calculator-package-number2)
*   [Code Structure](#code-structure)
    *   [Package `number1`](#package-number1)
    *   [Package `number2`](#package-number2)
*   [Example Output](#example-output)

## Features

*   **Expected Value & Variance Calculator (Package `number1`):**
    *   Calculates the Expected Value (Mean/μ) and Variance (σ²) of a discrete distribution using `DiscreteProbabilityDistribution`.
    *   Displays the step-by-step calculations in a clear table format via `DistributionReporter`.
    *   `MainApplication` orchestrates the process.
    *   Input data (X values and P(X)) are currently hardcoded in `MainApplication` based on a sample problem.
    *   Includes data validation for probability sums.
*   **Binomial Distribution Calculator (Package `number2`):**
    *   Calculates the probability P(X=x) for each possible number of successes (x) from 0 to K using `BinomialDistribution` and `BinomialMath`.
    *   `BinomialMath` provides static utility methods for combinations and probability calculation.
    *   `BinomialDistribution` models the specific distribution.
    *   `BinomialReporter` displays the complete probability distribution table.
    *   Accepts the number of trials (K) as user input via `MainApplication`.
    *   Assumes a success probability (p) of 0.5 (fair coin) by default.

## Prerequisites

*   **Java Development Kit (JDK):** Version 8 or later.
    *   You can check your Java version by opening a terminal or command prompt and typing:
        ```bash
        java -version
        javac -version
        ```

## How to Use

1.  **Clone or Download the Repository:**
    ```bash
    git clone https://github.com/hendrowunga/JavaProbabilityStats.git
    cd JavaProbabilityStats
    ```
    Ensure the directory structure includes `number1` and `number2` folders containing their respective `.java` files. Often, these are placed within a `src` directory (e.g., `src/number1/`, `src/number2/`).

2.  **Compile and Run the Programs:** Open a terminal or command prompt in the **root directory** of the project (the one containing the `number1` and `number2` folders, or the `src` folder if you have one).

    *   **Important:** The commands below assume your `.java` files are directly inside `number1/` and `number2/` relative to your current location. If they are inside a `src` directory, navigate into `src` first, or adjust the paths accordingly.

### 1. Expected Value & Variance Calculator (Package `number1`)

*   **Compile:** Compile all Java files within the `number1` package.
    ```bash
    javac number1/*.java
    # Or if using a src directory: javac src/number1/*.java
    ```
*   **Run:** Execute the `MainApplication` class within the `number1` package.
    ```bash
    java number1.MainApplication
    # Or if using a src directory (and compiled from project root): java -cp src number1.MainApplication
    ```
*   **Output:** The program will print the calculation tables and the final results for Expected Value and Variance based on the hardcoded data in `number1/MainApplication.java`.

### 2. Binomial Distribution Calculator (Package `number2`)

*   **Compile:** Compile all Java files within the `number2` package.
    ```bash
    javac number2/*.java
    # Or if using a src directory: javac src/number2/*.java
    ```
*   **Run:** Execute the `MainApplication` class within the `number2` package.
    ```bash
    java number2.MainApplication
     # Or if using a src directory (and compiled from project root): java -cp src number2.MainApplication
    ```
*   **Input:** The program will prompt you to enter the number of coin flips (K). Enter a non-negative integer.
*   **Output:** The program will print the Binomial probability distribution table for the entered value of K.

## Code Structure

The project code is organized into packages:

### Package `number1`
*(Handles Expected Value and Variance for a discrete distribution)*

*   **`DiscreteProbabilityDistribution.java`**: Models the distribution, holds X/P(X) data, performs core E[X] and Var(X) calculations, and validates input.
*   **`DistributionReporter.java`**: Formats and prints the detailed calculation steps and final results for the distribution.
*   **`MainApplication.java`**: Entry point for this calculator. Sets up data, creates Distribution and Reporter objects, and initiates the reporting.

### Package `number2`
*(Handles Binomial Distribution)*

*   **`BinomialMath.java`**: Utility class with static methods for calculating binomial coefficients and probabilities.
*   **`BinomialDistribution.java`**: Models a specific binomial distribution (n, p). Calculates and stores P(X=k) for all k, using `BinomialMath`.
*   **`BinomialReporter.java`**: Formats and prints the binomial distribution table based on data from a `BinomialDistribution` object.
*   **`MainApplication.java`**: Entry point for this calculator. Handles user input (K/n), creates Distribution and Reporter objects, and initiates the reporting.

## Example Output

The *format* of the output generated by the `DistributionReporter` and `BinomialReporter` classes matches the examples shown in the PDF document included in this repository:

*   **[View Example Output Format (SOAL AND OUTPUT.pdf)](./SOAL%20AND%20OUTPUT.pdf)**

Please download or view the PDF file to see the expected formatting for both calculators.