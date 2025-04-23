# 🌿 Serenity Therapy Center Management System

**Empowering mental wellness through smart technology.**

## 🧠 Overview

The **Serenity Therapy Center Management System** is an all-in-one desktop application built to **modernize** and **simplify** the daily operations of a mental health therapy center. From handling patient records to managing therapy programs and generating insightful reports, this system ensures **seamless coordination between therapists, patients, and administrative staff**.

Crafted with ❤️ using **JavaFX** and **Hibernate**, the application features a sleek, user-friendly interface and robust backend architecture to ensure both usability and performance.

## ✨ Key Features

### 👤 User & Access Control
* Role-based authentication for **Admins** and **Receptionists**.
* Secure login with **BCrypt password hashing**.

### 🧍‍♂️ Patient Management
* Register, update, and view patient profiles.
* Track **remaining therapy sessions** and therapy history.

### 🧑‍⚕️ Therapist Module
* Add and manage therapist profiles with specializations.
* Monitor availability for better session scheduling.

### 🧘 Therapy Programs
* Define programs with **custom descriptions**, **duration**, and **fee structures**.
* Link patients and therapists to specific programs.

### 📅 Session Scheduling
* Intuitive calendar-based session manager.
* **Conflict detection** to avoid double bookings.

### 💳 Payments & Balances
* Record new payments and calculate outstanding balances.
* Automatic updates based on session attendance.

### 📊 Reports & Insights
* Generate dynamic reports with **JavaFX**.
* Export data on payments, patients, and session attendance.

## 🧰 Tech Stack

| Layer | Tools & Technologies |
|-------|----------------------|
| UI/UX | JavaFX, SceneBuilder |
| Backend | Java, Hibernate ORM, Lombok |
| Database | MySQL |
| Reporting | JavaFX |
| Security | BCrypt |
| Dependency Mgmt. | Maven |

## ⚙️ Installation Guide

### Prerequisites
* Java 17+
* MySQL Server
* Maven
* An IDE (like IntelliJ IDEA or Eclipse)

### Setup Steps
1. **Clone the repository:**
   ```bash
   git clone https://github.com/your-username/Mental-Health-Therapy-Center.git
   ```

2. **Set up the database:**
    * Import the provided SQL script (`database.sql`) into your MySQL server.
    * Configure database credentials in `hibernate.cfg.xml`.

3. **Install dependencies:**
   ```bash
   mvn clean install
   ```

4. **Run the application:**
    * Open the project in your IDE.
    * Run the main class `SerenityApp.java`.

## 📁 Project Structure
```
src/
├── controller/
├── model/
├── dao/
├── util/
├── view/       # FXML files
├── reports/    # JRXML & compiled reports
├── assets/     # Logos, icons
└── main/SerenityApp.java
```

## 📌 Future Enhancements
* Email/SMS reminders for sessions and payments.
* Patient progress tracking charts.
* Therapist performance analytics.
* Cloud backup and multi-device sync.

## 🤝 Contribution
We welcome contributors who are passionate about mental health and software engineering! Feel free to fork the project and submit a pull request. Let's build something impactful together.

## 🧾 License
This project is licensed under the MIT License.

## 👨‍💻 Developed By
**Vinod Niloshana Fernando**  
*Student | Software Engineer | Dreamer*  
[🔗 LinkedIn](https://www.linkedin.com/in/vinod-niloshana-09678731a/) • [💻 GitHub](https://github.com/Vinod663)
