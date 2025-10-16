package documentanalysis.filesize.controller;

import documentanalysis.filesize.entity.FileData;
import documentanalysis.filesize.repository.FileDataRepository;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

@Controller
public class FileUploadController {

    @Autowired
    private FileDataRepository fileDataRepository;

    @GetMapping("/")
    public String showUploadForm() {
        return "index";
    }

    @PostMapping("/analyze")
    public String analyzeFile(@RequestParam("file") MultipartFile file, Model model) {
        try {
            if (file.isEmpty()) {
                model.addAttribute("error", "Please upload a file!");
                return "index";
            }

            String filename = file.getOriginalFilename();
            if (filename == null || filename.trim().isEmpty()) {
                model.addAttribute("error", "File name is invalid or missing.");
                return "index";
            }
            String lowerCaseFilename = filename.toLowerCase();
            String textContent;

            // --- START OF FIX: Efficiently read .txt files ---
            if (lowerCaseFilename.endsWith(".txt")) {
                StringBuilder contentBuilder = new StringBuilder();
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        contentBuilder.append(line).append(System.lineSeparator());
                    }
                }
                textContent = contentBuilder.toString();
            // --- END OF FIX ---
            } else if (lowerCaseFilename.endsWith(".pdf")) {
                PDDocument doc = PDDocument.load(file.getInputStream());
                PDFTextStripper stripper = new PDFTextStripper();
                textContent = stripper.getText(doc);
                doc.close();
            } else {
                model.addAttribute("error", "Only .txt or .pdf files are supported!");
                return "index";
            }

            String[] words = textContent.trim().isEmpty() ? new String[0] : textContent.trim().split("\\s+");
            int wordCount = words.length;
            int charCount = textContent.length();

            String longestWord = "";
            int totalLength = 0;
            for (String w : words) {
                if (w.length() > longestWord.length()) longestWord = w;
                totalLength += w.length();
            }

            double avgWordLen = (wordCount > 0) ? (double) totalLength / wordCount : 0;
            double fileSizeKB = file.getSize() / 1024.0;
            String readableSize = (fileSizeKB < 1024)
                    ? String.format("%.2f KB", fileSizeKB)
                    : String.format("%.2f MB", fileSizeKB / 1024.0);

            FileData fileData = new FileData(filename, readableSize, wordCount, charCount, longestWord, avgWordLen);
            fileDataRepository.save(fileData);

            model.addAttribute("filename", filename);
            model.addAttribute("size", readableSize);
            model.addAttribute("wordCount", wordCount);
            model.addAttribute("charCount", charCount);
            model.addAttribute("longestWord", longestWord);
            model.addAttribute("avgWordLen", String.format("%.2f", avgWordLen));

            return "result";

        } catch (Exception e) {
            model.addAttribute("error", "Error analyzing file: " + e.getMessage());
            return "index";
        }
    }
}