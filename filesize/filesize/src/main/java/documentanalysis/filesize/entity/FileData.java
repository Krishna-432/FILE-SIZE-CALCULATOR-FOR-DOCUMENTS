package documentanalysis.filesize.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "file_data")
public class FileData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fileName;
    private String fileSize;
    private int wordCount;
    private int charCount;
    private String longestWord;
    private double avgWordLength;

    public FileData() {}

    public FileData(String fileName, String fileSize, int wordCount, int charCount, String longestWord, double avgWordLength) {
        this.fileName = fileName;
        this.fileSize = fileSize;
        this.wordCount = wordCount;
        this.charCount = charCount;
        this.longestWord = longestWord;
        this.avgWordLength = avgWordLength;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getFileName() { return fileName; }
    public void setFileName(String fileName) { this.fileName = fileName; }

    public String getFileSize() { return fileSize; }
    public void setFileSize(String fileSize) { this.fileSize = fileSize; }

    public int getWordCount() { return wordCount; }
    public void setWordCount(int wordCount) { this.wordCount = wordCount; }

    public int getCharCount() { return charCount; }
    public void setCharCount(int charCount) { this.charCount = charCount; }

    public String getLongestWord() { return longestWord; }
    public void setLongestWord(String longestWord) { this.longestWord = longestWord; }

    public double getAvgWordLength() { return avgWordLength; }
    public void setAvgWordLength(double avgWordLength) { this.avgWordLength = avgWordLength; }
}