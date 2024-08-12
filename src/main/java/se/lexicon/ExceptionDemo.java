package se.lexicon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.*;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.nio.file.Files.*;

public class ExceptionDemo {
    public static void main(String[] args) {
       //Checked Exception
        /*Path path = Paths.get("dir/skills.txt");
        Files.newBufferedReader(path);*/
    //Unchecked Exception
        /*int[] numbers = {1, 2, 3, 4};
        System.out.println(numbers[5]);*/

        /*System.out.println(getDate());
        System.out.println("DONE");*/
        writeTextToFile();
    }
    public static Supplier<LocalDate> getFunctionalDate = () ->  {
        Scanner scanner = new Scanner(System.in);
        LocalDate date;
        while (true) {
            try{
                System.out.println("Enter a date (yyyy-mm-dd):");
                String input = scanner.nextLine();
                date = LocalDate.parse(input);
                break;
            } catch (DateTimeException e) {
                System.out.println("Invalid date format. Please enter date in yyyy-MM-dd format. " + e.getMessage());
            }

        };
        return date;
    };
    public static  LocalDate getDate (){
        Scanner scanner = new Scanner(System.in);
        LocalDate date;
        while (true) {
            try{
                System.out.println("Enter a date (yyyy-mm-dd):");
                String input = scanner.nextLine();
                date = LocalDate.parse(input);
                break;
            } catch (DateTimeException e) {
                System.out.println("Invalid date format. Please enter date in yyyy-MM-dd format. " + e.getMessage());
            }

        }
        return date;
    }
    //Catching checked exception
    public static void readTextFile() {
        // java.io
        // java.nio


       try {
           Path relativePath = Paths.get("dir/skills.txt");
           // Path relativePath = Paths.get("non_existing_filename.txt");
           //Path invalidPath = Paths.get("dir:/<invalid>|path?.txt");
           // Path absolutePath = Paths.get("C:\\Users\\eu\\IdeaProjects\\Exception-IO\\dir\\skills.txt");
           BufferedReader reader = Files.newBufferedReader(relativePath);
           Stream<String> stringStream = reader.lines();
           List<String> strings = stringStream.collect(Collectors.toList());
           strings.forEach(System.out::println);
           // Use case: Suitable for medium-sized files (10MB to 100MB)

           System.out.println("-------------------");
           List<String> stringList = Files.readAllLines(relativePath);
           stringList.forEach(System.out::println);
           // Use case: Suitable for small files (1MB to 10MB)

           Files.lines(relativePath).forEach(System.out::println);
           // Use case: Suitable for large files (100MB to 1GB)

       } catch (InvalidPathException e) {
           System.out.println("Invalid path exception");
           System.out.println("The file path is not valid: " + e.getMessage());
       } catch (NoSuchFileException e) {
           System.out.println("File not found: " + e.getMessage());
           System.out.println("File not found: " + e.getFile());
       } catch (IOException e) {
           System.out.println("An I/O Exception occurred: " + e.getMessage());
       } catch (SecurityException e) {
           System.out.println("Permission denied: " + e.getMessage());
       } catch (Exception e) {
           System.out.println("An unexpected error occurred: " + e.getMessage());
           e.printStackTrace();
       }
    }

    public static void readImage() {
        try {
            Path source = Paths.get("source/logo.jpg");
            Path destinationPath = Paths.get("destination/logo.jpg");
            Files.copy(source, destinationPath , StandardCopyOption.REPLACE_EXISTING);
            System.out.println("File copied successfully!");

        } catch (FileAlreadyExistsException e) {
            System.out.println("File already exists! " + e.getFile());
        } catch (IOException e) {
            System.out.println("An I/O Exception occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void writeTextToFile() {


        try (
                BufferedWriter bufferedWriter =  Files.newBufferedWriter(


                Paths.get("dir/skills.txt"),
                StandardOpenOption.APPEND,
                StandardOpenOption.CREATE);

        ) {

           bufferedWriter.write("JavaScript");
           bufferedWriter.newLine();
           bufferedWriter.close();
            System.out.println("File written successfully!");
        } catch (IOException e) {
            System.out.println("An I/O Exception occurred: " + e.getMessage());
            e.printStackTrace();
        } finally {
            System.out.println("Finally block executed!");

        }

    }
}
