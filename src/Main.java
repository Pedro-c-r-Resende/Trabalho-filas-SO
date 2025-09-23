import java.util.List;

public class Main {
    public static void main(String[] args) {
        String caminhoArquivo = "processos.txt"; // Coloque o arquivo na raiz do projeto ou indique o caminho absoluto

        List<Processo> processos = LeitorProcesso.carregarProcessos(caminhoArquivo);

        // Testando leitura
        System.out.println("=== Processos carregados ===");
        for (Processo p : processos) {
            System.out.println(p);
        }
    }
}
