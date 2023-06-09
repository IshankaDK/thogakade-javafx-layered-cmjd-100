### Getting Started

[] Welcome to the VS Code Java world. Here is a guideline to help you get started to write Java code in Visual Studio Code.

### Folder Structure

[]The workspace contains two folders by default, where:

- `src`: the folder to maintain sources
- `lib`: the folder to maintain dependencies

[]Meanwhile, the compiled output files will be generated in the `bin` folder by default.

> If you want to customize the folder structure, open `.vscode/settings.json` and update the related settings there.

### Project Name

Java, JavaFX and MySQL Standalone Demo (Thogakade for CMJD100 (IJSE)).

## Table of Contents

- [Getting Started](#getting-started)
  - [Prerequisites](#prerequisites)
  - [Installation](#installation)

## Getting Started

### Prerequisites

- Java Development Kit (JDK) 11 or higher: Ensure that you have JDK installed on your system. You can download the latest JDK from the official Oracle website: [https://www.oracle.com/java/technologies/javase-jdk11-downloads.html](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html).

- JAVAFX
  https://openjfx.io/openjfx-docs/

- VsCode
  https://code.visualstudio.com.

- MySQL
  https://dev.mysql.com/downloads/mysql/

### Installation

1. Clone the repository:

   ```shell
   git clone https://github.com/IshankaDK/thogakade-javafx-layered-cmjd-100

   ```

2. Add JavaFx libraries(https://openjfx.io/), jfoenix-(version).jar and MySQl connector to the /lib folder.
3. Define vmArgs in launch.json.
4. Execute Database script db.sql in MySQL for define your database.
5. Change the Database credentials in DBConnection.java in to yours.
6. Run the Application.
