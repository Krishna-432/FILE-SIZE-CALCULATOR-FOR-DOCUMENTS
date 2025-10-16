# 📄 Document Analyzer – Java Swing Application

## 🔹 Project Overview
The **Document Analyzer** is a **Java-based desktop application** that allows users to upload a document (such as `.txt` or `.pdf`) and quickly calculate important statistics. It is **beginner-friendly** and demonstrates the practical use of **Java Swing** for GUI and **Java File I/O** for analyzing documents.

The application automatically provides:

- **File Size** (KB/MB)  
- **Word Count**  
- **Character Count**  
- **Longest Word**  
- **Average Word Length**  

---

## ✨ Key Features
- **📂 File Selection:** Upload `.txt` or `.pdf` files from your system.  
- **📝 Word & Character Count:** Automatically counts words and characters.  
- **📏 File Size Calculation:** Displays actual file size in KB or MB.  
- **🔍 Basic Statistics:** Shows longest word and average word length.  
- **🖥️ Simple GUI:** Intuitive interface built using **Java Swing**.  

---

## 🛠 Technologies Used
- **Java SE 8+**  
- **Java Swing** for GUI  
- **File I/O** for reading files  
- **Optional:** Apache PDFBox (for PDF support)  

---

## 🚀 How to Use
1. **Run the application** by executing `Main.java` or the JAR file.  
2. Click **Upload File** to select your `.txt` or `.pdf` document.  
3. The application displays:  
   - File name  
   - File size  
   - Word count  
   - Character count  
   - Longest word  
   - Average word length  

---

## 📂 Project Structure

DocumentAnalyzer/
│
├── src/
│ ├── DocumentAnalyzer.java // Main GUI application
│ └── FileData.java // Logic for calculating statistics
├── README.md
└── pom.xml (optional, if using Maven)
