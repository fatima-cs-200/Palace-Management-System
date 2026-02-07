
## 📌 Palace Booking Management System

The **Palace Booking Management System** is a Java Swing-based desktop application designed to streamline palace record management and client booking operations. The system allows users to add, update, and delete palace details, create bookings with automated total price calculation, and prevent scheduling conflicts through date-based availability checks.

This project focuses on applying core **Object-Oriented Programming principles** and building a structured **GUI-based management system** while handling persistent data using file storage.

---

## 🚀 Key Features

* Manage palace records (Name, Location, Price per Day)
* Create, update, and delete bookings
* Automatic total cost calculation based on number of days
* Date conflict detection to prevent double bookings
* User-friendly GUI using Java Swing
* Persistent storage using text files
* Navigation using CardLayout for smooth panel switching

---

## 🧠 Concepts Used

This project heavily applies fundamental computer science and software engineering concepts:

### ✅ Object-Oriented Programming (OOP)

* **Encapsulation:** Separate classes for Palace, Booking, ControlPanel, and Frames
* **Modularity:** Each component handles a specific responsibility
* **Reusability:** Panels and frames are designed for extension

### ✅ Java Swing (GUI Development)

* JFrame, JPanel, JTable, JButton, JComboBox
* Layout Managers (BorderLayout, GridLayout, FlowLayout, CardLayout)
* Event Handling with ActionListener

### ✅ Data Structures

* **HashMap** used to map palace names to prices for quick lookup
* **Table Models** for dynamic data rendering

### ✅ File Handling

* BufferedReader / BufferedWriter
* Persistent storage using `.txt` files
* Load-on-start and save-on-update mechanism

### ✅ Date & Time API

* `SimpleDateFormat`
* `Calendar`
* `Date`
* Booking overlap detection logic

### ✅ MVC-like Design Approach

While not a full MVC implementation, the project separates:

* UI (Swing components)
* Logic (availability + calculations)
* Data (file storage)

---

## 🎯 Learning Outcomes

* Building real-world GUI applications
* Managing structured data without databases
* Implementing scheduling logic
* Designing modular desktop software
* Handling user input safely

---

## ⚙️ Tech Stack

* **Language:** Java
* **GUI:** Swing
* **Storage:** File System (.txt)
* **IDE:** IntelliJ

---


