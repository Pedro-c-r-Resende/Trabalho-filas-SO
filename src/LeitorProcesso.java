import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LeitorProcesso {

    public static List<Processo> carregarProcessos(String caminhoArquivo) {
        List<Processo> processos = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo))) {
            String linha;
            boolean primeiraLinha = true;
            while ((linha = br.readLine()) != null) {
                linha = linha.trim();
                if (linha.isEmpty()) continue; // pula linhas vazias

                // Se houver cabeçalho (com letras), pula a primeira linha
                if (primeiraLinha && linha.matches(".*[a-zA-Z].*")) {
                    primeiraLinha = false;
                    continue;
                }
                primeiraLinha = false;

                String[] dados = linha.split(",");
                if (dados.length < 5) {
                    System.out.println("Linha ignorada (formato inválido): " + linha);
                    continue;
                }

                int id = Integer.parseInt(dados[0].trim());
                int tempoEntrada = Integer.parseInt(dados[1].trim());
                int tempoIO = Integer.parseInt(dados[2].trim());
                int tempoProcessamento = Integer.parseInt(dados[3].trim());
                int prioridade = Integer.parseInt(dados[4].trim());

                processos.add(new Processo(id, tempoEntrada, tempoIO, tempoProcessamento, prioridade));
            }
        } catch (IOException | NumberFormatException e) {
            System.err.println("Erro ao ler/processar arquivo: " + e.getMessage());
        }

        return processos;
    }
}


