
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Escalonador {
    private List<Processo> todosProcessos;
    private Fila filaDeProcessamento;
    private Fila filaDeIO;
    private List<Processo> processosFinalizados;
    private int clock;
    private Processo processoExecutando;
    private Processo processoEmIO;

    public Escalonador(List<Processo> processos) {
        this.todosProcessos = new ArrayList<>(processos);
        this.filaDeProcessamento = new Fila();
        this.filaDeIO = new Fila();
        this.processosFinalizados = new ArrayList<>();
        this.clock = 0;
        this.processoExecutando = null;
        this.processoEmIO = null;
    }

    public void simular() {
        System.out.println("=== Iniciando Simulação ===");

        while (!todosProcessosFinalizados()) {
            System.out.println("\n--- Clock: " + clock + " ---");

            // 1. Verificar novos processos para entrar no sistema
            verificarNovosProcessos();

            // 2. Processar I/O
            processarIO();

            // 3. Processar CPU
            processarCPU();

            // 4. Mostrar estado atual
            mostrarEstadoAtual();

            clock++;
        }

        System.out.println("\n=== Simulação Finalizada ===");
        gerarSaida();
    }

    private void verificarNovosProcessos() {
        for (Processo p : todosProcessos) {
            if (p.getEstado() == Processo.EstadoProcesso.AGUARDANDO_ENTRADA &&
                    p.getTempoEntrada() == clock) {
                p.setEstado(Processo.EstadoProcesso.PRONTO);
                filaDeProcessamento.enfileirar(p);
                System.out.println("Processo " + p.getId() + " entrou no sistema");
            }
        }
    }

    private void processarIO() {
        // Processar processo atual em I/O
        if (processoEmIO != null) {
            processoEmIO.setTempoIORestante(processoEmIO.getTempoIORestante() - 1);
            System.out.println("Processo " + processoEmIO.getId() + " executando I/O (restante: " +
                    processoEmIO.getTempoIORestante() + ")");

            if (processoEmIO.getTempoIORestante() <= 0) {
                // I/O finalizado
                if (processoEmIO.getTempoProcessamentoRestante() > 0) {
                    // Ainda tem processamento, volta para fila de processamento
                    processoEmIO.setEstado(Processo.EstadoProcesso.PRONTO);
                    filaDeProcessamento.enfileirar(processoEmIO);
                    System.out.println(
                            "Processo " + processoEmIO.getId() + " terminou I/O, voltou para fila de processamento");
                } else {
                    // Processo finalizado
                    processoEmIO.setEstado(Processo.EstadoProcesso.FINALIZADO);
                    processoEmIO.setTempoSaida(clock);
                    processosFinalizados.add(processoEmIO);
                    System.out.println("Processo " + processoEmIO.getId() + " finalizado no clock " + clock);
                }
                processoEmIO = null;
            }
        }

        // Iniciar novo processo em I/O se houver na fila
        if (processoEmIO == null && !filaDeIO.estaVazia()) {
            processoEmIO = filaDeIO.desenfileirar();
            processoEmIO.setEstado(Processo.EstadoProcesso.BLOQUEADO_IO);
            System.out.println("Processo " + processoEmIO.getId() + " iniciou I/O");
        }
    }

    private void processarCPU() {
        // Processar processo atual na CPU
        if (processoExecutando != null) {
            processoExecutando.setTempoProcessamentoRestante(processoExecutando.getTempoProcessamentoRestante() - 1);
            System.out.println("Processo " + processoExecutando.getId() + " executando na CPU (restante: " +
                    processoExecutando.getTempoProcessamentoRestante() + ")");

            if (processoExecutando.getTempoProcessamentoRestante() <= 0) {
                // Processamento finalizado
                if (processoExecutando.getTempoIORestante() > 0) {
                    // Precisa fazer I/O
                    processoExecutando.setEstado(Processo.EstadoProcesso.PRONTO);
                    filaDeIO.enfileirar(processoExecutando);
                    System.out.println(
                            "Processo " + processoExecutando.getId() + " terminou processamento, foi para fila de I/O");
                } else {
                    // Processo finalizado
                    processoExecutando.setEstado(Processo.EstadoProcesso.FINALIZADO);
                    processoExecutando.setTempoSaida(clock);
                    processosFinalizados.add(processoExecutando);
                    System.out.println("Processo " + processoExecutando.getId() + " finalizado no clock " + clock);
                }
                processoExecutando = null;
            }
        }

        // Iniciar novo processo na CPU se houver na fila
        if (processoExecutando == null && !filaDeProcessamento.estaVazia()) {
            processoExecutando = filaDeProcessamento.desenfileirar();
            processoExecutando.setEstado(Processo.EstadoProcesso.EXECUTANDO);
            System.out.println("Processo " + processoExecutando.getId() + " iniciou execução na CPU");
        }
    }

    private boolean todosProcessosFinalizados() {
        for (Processo p : todosProcessos) {
            if (p.getEstado() != Processo.EstadoProcesso.FINALIZADO) {
                return false;
            }
        }
        return true;
    }

    private void mostrarEstadoAtual() {
        System.out.println("CPU: " + (processoExecutando != null ? "P" + processoExecutando.getId() : "IDLE"));
        System.out.println("I/O: " + (processoEmIO != null ? "P" + processoEmIO.getId() : "IDLE"));
        System.out.println("Fila Processamento: " + contarProcessosNaFila(filaDeProcessamento));
        System.out.println("Fila I/O: " + contarProcessosNaFila(filaDeIO));
        System.out.println("Finalizados: " + processosFinalizados.size());
    }

    private int contarProcessosNaFila(Fila fila) {
        int count = 0;
        Fila temp = new Fila();

        while (!fila.estaVazia()) {
            Processo p = fila.desenfileirar();
            temp.enfileirar(p);
            count++;
        }

        while (!temp.estaVazia()) {
            fila.enfileirar(temp.desenfileirar());
        }

        return count;
    }

    private void gerarSaida() {
        try (FileWriter writer = new FileWriter("saida.txt")) {
            for (Processo p : processosFinalizados) {
                writer.write(p.getTempoSaida() + ";" + p.getId() + "\n");
            }
            System.out.println("Arquivo 'saida.txt' gerado com sucesso!");
        } catch (IOException e) {
            System.err.println("Erro ao gerar arquivo de saída: " + e.getMessage());
        }
    }
}
