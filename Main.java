import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static class Item {
        public String jogos, categoria;
        public double avaliacao;

        public Item(String jogos, String categoria, double avaliacao) {
            this.jogos = jogos;
            this.categoria = categoria;
            this.avaliacao = avaliacao;
        }
    }

    public static Item[] lerArquivo() throws IOException {
        File arquivo = new File("JogosDesordenados.csv");
        FileReader fr = new FileReader(arquivo);
        BufferedReader br = new BufferedReader(fr);

        String linha;
        List<Item> listaItens = new ArrayList<>();

        while ((linha = br.readLine()) != null) {
            String[] dados = linha.split(",");
            String jogos = dados[0].trim();
            String categoria = dados[1].trim();
            double avaliacao = Double.parseDouble(dados[2].trim());

            Item item = new Item(jogos, categoria, avaliacao);
            listaItens.add(item);
        }

        br.close();

        return listaItens.toArray(new Item[0]);
    }

    public static void ordenarPorAvaliacao(Item[] vetor) {
        for (int i = 1; i < vetor.length; i++) {
            Item chave = vetor[i];
            int j = i - 1;

            while (j >= 0 && vetor[j].avaliacao < chave.avaliacao) {
                vetor[j + 1] = vetor[j];
                j = j - 1;
            }
            vetor[j + 1] = chave;
        }
    }
    
    public static void ordenarPorCategoria(Item[] vetor) {
        for (int i = 0; i < vetor.length - 1; i++) {
            for (int j = 0; j < vetor.length - i - 1; j++) {
                if (vetor[j].categoria.compareTo(vetor[j + 1].categoria) > 0) {
                    Item temp = vetor[j];
                    vetor[j] = vetor[j + 1];
                    vetor[j + 1] = temp;
                }
            }
        }
    }



    public static void salvarEmArquivo(Item[] vetor, String nomeArquivo) throws IOException {
        FileWriter fw = new FileWriter(nomeArquivo);
        BufferedWriter bw = new BufferedWriter(fw);

        for (Item item : vetor) {
            bw.write(item.jogos + "," + item.categoria + "," + item.avaliacao);
            bw.newLine();
        }

        bw.close();
    }

    public static void exibirMenu() {
        System.out.println("-=-=- Bem vindo ao Menu -=-=-");
        System.out.println("-=-=- Escolha uma opção -=-=-");
        System.out.println("[1] Ler arquivo");
        System.out.println("[2] Ordenar por categoria");
        System.out.println("[3] Ordenar por avaliação");
        System.out.println("[4] Sair");
        System.out.print("Opção: ");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Item[] vetor = null;

        int opcao;
        do {
            exibirMenu();
            opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    try {
                        vetor = lerArquivo();
                        System.out.println("Arquivo lido com sucesso!");
                    } catch (IOException e) {
                        System.out.println("Erro na leitura do arquivo: " + e.getMessage());
                    }
                    break;
                case 2:
                    if (vetor != null) {
                        ordenarPorCategoria(vetor);
                        try {
                            salvarEmArquivo(vetor, "JogosOrdenadosporCategoria.csv");
                            System.out.println("Dados ordenados por categoria e foram salvos com sucesso!");
                        } catch (IOException e) {
                            System.out.println("Erro ao salvar o arquivo: " + e.getMessage());
                        }
                    } else {
                        System.out.println("É necessário que o arquivo seja lido primeiro (Opção 1)!");
                    }
                    break;
                case 3:
                    if (vetor != null) {
                        ordenarPorAvaliacao(vetor);
                        try {
                            salvarEmArquivo(vetor, "JogosOrdenadosporAvaliacao.csv");
                            System.out.println("Dados ordenados por avaliação salvos com sucesso!");
                        } catch (IOException e) {
                            System.out.println("Erro ao salvar o arquivo: " + e.getMessage());
                        }
                    } else {
                        System.out.println("É necessário que o arquivo seja lido primeiro (Opção 1)!");
                    }
                    break;
                case 4:
                    System.out.println("Encerrando...");
                    break;
                default:
                    System.out.println("Opção inválida! Escolha uma opção válida.");
                    break;
            }

        } while (opcao != 4);

        scanner.close();
    }
}