# Budget Project
This project is a simple budget tracker for anyone to use. It is written using Java and JavaFX.
![Demo Video](assets/DEMO_VIDEO.mkv)

## 🚀 Setup Instructions
1. **Clone Repository**  
    ```bash
    git clone https://github.com/BRobDan/budget-project.git
    ```
2. **Navigate to Project Folder**
    ```bash
    cd budget-project
    ```
3. **Start the Application**
    ```bash
    mvn javafx:run
    ```
💼 Ensure you have Maven and JDK 17+ installed.

## ⭐ Features
* 📃 Budget entries support CRUD operations
* 🧾 Summary page overview
* 🍰 Pie chart visualization of expenses
* 📊 Entry form with input validation
* 📰 Transaction list with sorting & filtering
* 🏬 SQLite database support for persistent data handling

## 🪳 Known Issues
* Most exceptions are handled by printing the stack trace
* Filtering & Sorting settings are not saved between runs
* Lack of user authentication
* FXML files were built with JavaFX API version 23.0.1, while the runtime is version 17.0.9
   * No current known issues with this version conflict

## 🔬 Testing
For test report, click this link: [Test Report](TEST_REPORT.md).  
JUnit was used for all unit tests.

## 🙋🏻‍♂️ Author Information
**Bryan Daniel**  
🎓 CompSci & Cybersec Student  
🌐 [GitHub Profile](https://github.com/BRobDan)