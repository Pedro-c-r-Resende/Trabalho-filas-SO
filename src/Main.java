import java.util.List;

public class Main {
    public static void main(String[] args) {
        // O caminho do arquivo é relativo à pasta raiz do projeto.
        String caminhoArquivo = "Trabalho-filas-SO-master/processos.txt";

        List<Processo> processos = LeitorProcesso.carregarProcessos(caminhoArquivo);

        // Verifica se algum processo foi carregado antes de iniciar a simulação.
        if (processos == null || processos.isEmpty()) {
            System.err.println("Nenhum processo foi carregado. Verifique o arquivo '" + caminhoArquivo + "' e seu conteúdo.");
            return; // Encerra a execução se não houver processos.
        }

        // Cria uma instância do escalonador com a lista de processos carregada.
        Escalonador escalonador = new Escalonador(processos);

        // Inicia a simulação.
        escalonador.simular();
    }
}
