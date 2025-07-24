package com.example.todo;
import java.io.*;
import java.util.Scanner;

public class App {
    
    // Method to encrypt text
    public static String caesarEncrypt(String text, int key) {
        StringBuilder encrypted = new StringBuilder();

        for (char ch : text.toCharArray()) {
            if (Character.isUpperCase(ch)) {
                char c = (char) ((ch - 'A' + key) % 26 + 'A');
                encrypted.append(c);
            } else if (Character.isLowerCase(ch)) {
                char c = (char) ((ch - 'a' + key) % 26 + 'a');
                encrypted.append(c);
            } else {
                encrypted.append(ch); // Non-alphabetical characters remain unchanged
            }
        }

        return encrypted.toString();
    }

    // Method to decrypt text
    public static String caesarDecrypt(String text, int key) {
        return caesarEncrypt(text, 26 - key); // decryption is reverse of encryption
    }

    // Method to read from file
    public static String readFile(String filePath) throws IOException {
        StringBuilder content = new StringBuilder();
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;

        while ((line = reader.readLine()) != null) {
            content.append(line).append("\n");
        }
        reader.close();
        return content.toString();
    }

    // Method to write to file
    public static void writeFile(String filePath, String data) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
        writer.write(data);
        writer.close();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("Enter input file path: ");
            String inputFile = scanner.nextLine();

            System.out.print("Enter output file path: ");
            String outputFile = scanner.nextLine();

            System.out.print("Enter Caesar cipher key (number): ");
            int key = scanner.nextInt();

            scanner.nextLine(); // consume newline

            System.out.print("Encrypt or Decrypt? (E/D): ");
            String choice = scanner.nextLine().toUpperCase();

            String fileData = readFile(inputFile);
            String result;

            if (choice.equals("E")) {
                result = caesarEncrypt(fileData, key);
                System.out.println("Encryption completed.");
            } else {
                result = caesarDecrypt(fileData, key);
                System.out.println("Decryption completed.");
            }

            writeFile(outputFile, result);
            System.out.println("Output written to: " + outputFile);

        } catch (IOException e) {
            System.out.println("File error: " + e.getMessage());
        }
        scanner.close();
    }
}
