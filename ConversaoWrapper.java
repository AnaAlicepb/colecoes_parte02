import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

class Pessoa {
    String nome;
    String sexo;

    Pessoa(String nome, String sexo) {
        this.nome = nome;
        this.sexo = sexo;
    }

    @Override
    public String toString() {
        return nome + " (" + sexo + ")";
    }
}

public class ConversaoWrapper {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Pessoa> pessoas = new ArrayList<>();

        System.out.println("Bem-vindo ao Organizador de Pessoas!");
        System.out.println("Por favor, insira a quantidade de pessoas que deseja registrar:");
        int quantidade = scanner.nextInt();
        scanner.nextLine();  // Consumir a nova linha após o nextInt()

        for (int i = 0; i < quantidade; i++) {
            System.out.print("Pessoa " + (i + 1) + " (Nome-Sexo, ex: Ana-F): ");
            String entrada = scanner.nextLine();
            String[] dados = entrada.split("-");
            if (dados.length == 2) {
                String nome = dados[0].trim();
                String sexo = dados[1].trim().toUpperCase();
                if (sexo.equals("M") || sexo.equals("F")) {
                    pessoas.add(new Pessoa(nome, sexo.equals("M") ? "Masculino" : "Feminino"));
                } else {
                    System.out.println("Sexo inválido. Por favor, use 'M' para Masculino ou 'F' para Feminino. Tente novamente.");
                    i--; // Repete a iteração para garantir a entrada correta
                }
            } else {
                System.out.println("Formato inválido. Por favor, use o formato 'Nome-Sexo', ex: Ana-F. Tente novamente.");
                i--; // Repete a iteração para garantir a entrada correta
            }
        }

        // Separar listas por sexo
        ArrayList<Pessoa> masculinos = new ArrayList<>();
        ArrayList<Pessoa> femininos = new ArrayList<>();

        for (Pessoa pessoa : pessoas) {
            if (pessoa.sexo.equalsIgnoreCase("Masculino")) {
                masculinos.add(pessoa);
            } else if (pessoa.sexo.equalsIgnoreCase("Feminino")) {
                femininos.add(pessoa);
            }
        }

        // Ordenar cada lista pelo nome
        Comparator<Pessoa> comparadorPorNome = new Comparator<Pessoa>() {
            @Override
            public int compare(Pessoa p1, Pessoa p2) {
                return p1.nome.compareTo(p2.nome);
            }
        };
        Collections.sort(masculinos, comparadorPorNome);
        Collections.sort(femininos, comparadorPorNome);

        // Exibir as listas separadas e ordenadas
        System.out.println("\nPessoas do sexo masculino em ordem alfabética:");
        for (Pessoa pessoa : masculinos) {
            System.out.println(pessoa);
        }

        System.out.println("\nPessoas do sexo feminino em ordem alfabética:");
        for (Pessoa pessoa : femininos) {
            System.out.println(pessoa);
        }

        System.out.println("\nObrigado por usar o Organizador de Pessoas!");
        scanner.close();
    }
}
