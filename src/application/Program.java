package application;

import model.entities.Employee;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Program {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        List<Employee> employees = new ArrayList<>();
        Scanner scan = new Scanner(System.in);

        try (BufferedReader br = new BufferedReader(new FileReader("/home/ana-meira/Documentos/emails"))){

            while (true){
                String line = br.readLine();

                if (line == null){
                    break;
                }

                String[] fields = line.split(",");

                employees.add(new Employee(fields[0], fields[1], Double.parseDouble(fields[2])));
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.print("Digite um valor: ");
        double valor = scan.nextDouble();

        List<String> emails = employees.stream()
                .filter(x -> x.getSalary() > valor)
                .map(x -> x.getEmail())
                .sorted((x1, x2) -> - x1.toUpperCase().compareTo(x2.toUpperCase()))
                .collect(Collectors.toList());

        emails.forEach(System.out::println);

        Double sum = employees.stream()
                .filter(x -> x.getName().charAt(0) == 'M')
                .map(x -> x.getSalary())
                .reduce(0.0, (x, y) -> x + y);

        System.out.println(sum);
    }
}
