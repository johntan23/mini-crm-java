# Mini CRM Java (Swing)

A simple Customer Relationship Management (CRM) system implemented in Java using Swing. 
Manage customers, products, and orders in memory with a user-friendly GUI.

## Features

- Add, view, and manage customers
- Add and view products
- Create orders with stock validation
- GUI tables for easy data display
- All data stored in memory (no database)

## Requirements

- Java 11 or later
- Swing (included in the JDK)

## How to Run

1. Clone the repository:
    git clone https://github.com/johntan23/mini-crm-java.git
2. Navigate to the project folder:
    cd mini-crm-java
3. Compile all classes:
    javac *.java
4. Run the application:
    java CRMGUI

## Notes

- Data is lost when the application closes (no persistent storage)
- Input validation ensures proper customer, product, and order data