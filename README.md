```markdown
# 🎓 Student & Lesson Management (JavaFX + MySQL)

A JavaFX desktop application for managing students and lessons with a user-friendly interface and full CRUD functionality. Built with pure Java, JDBC, and FXML views.

---

## 🧩 Features

- 🧍 Manage students (Add, View, Update, Delete)
- 📘 Manage lessons (Add, View, Update, Delete)
- 🔄 GUI-based switching between student and lesson views
- 🗃️ Data persistence using MySQL with JDBC
- ⚡ Automatic table creation if not exists

---

## 🛠️ Technologies Used

- Java 17
- JavaFX
- MySQL
- JDBC
- FXML (Scene Builder friendly)
- IntelliJ IDEA / VS Code

---

## 🚀 Getting Started

### 1. Clone the Repository

```bash
git clone https://github.com/your-username/student-lesson-management-javafx.git
cd student-lesson-management-javafx
```

### 2. Set Up the Database

- Make sure MySQL is running
- Create a database named `school`
- Tables are created automatically if not exist

No manual SQL setup is needed thanks to `createTableIfNotExists()` method.

---

### 3. Configuration

If needed, update DB credentials in:

- `StudentRepository.java`
- `LessonRepository.java`

```java
private final String URL = "jdbc:mysql://127.0.0.1:3306/school";
private final String USER = "root";
private final String PASSWORD = "your_password";
```

---

### 4. Run the Application

You can run it from your IDE:
- Open `HelloApplication.java`
- Run as JavaFX Application
- Initial screen: **Student Management**
- Use the "Switch to Lessons" button to navigate

---

## 🖼️ Screenshots


![Ekran görüntüsü 2025-03-24 093034](https://github.com/user-attachments/assets/5d783823-f7f3-4142-bda5-a33d5f27bea5)
![Ekran görüntüsü 2025-03-24 093043](https://github.com/user-attachments/assets/c81e1785-b112-4306-9265-c58de51305c4)

---

## 📂 Project Structure

```
src/
├── Controllers/
│   ├── StudentController.java
│   └── LessonController.java
├── Models/
│   ├── Student.java
│   └── Lesson.java
├── Repository/
│   ├── StudentRepository.java
│   └── LessonRepository.java
├── HelloApplication.java
└── resources/
    ├── student.fxml
    └── lesson.fxml
```


