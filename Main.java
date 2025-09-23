// -----------------------------
// Nó da fila
// -----------------------------
class NoProcesso {
    Processo processo;
    NoProcesso proximo;

    public NoProcesso(Processo processo) {
        this.processo = processo;
        this.proximo = null;
    }
}

// -----------------------------
// Fila de Processamento (com prioridade)
// -----------------------------
class FilaProcessamento {
    private NoProcesso inicio;

    public FilaProcessamento() {
        this.inicio = null;
    }

    // Insere ordenando pela prioridade (menor número = maior prioridade)
    public void enfileirar(Processo p) {
        NoProcesso novo = new NoProcesso(p);

        if (inicio == null || p.getPrioridade() < inicio.processo.getPrioridade()) {
            novo.proximo = inicio;
            inicio = novo;
        } else {
            NoProcesso atual = inicio;
            while (atual.proximo != null &&
                    atual.proximo.processo.getPrioridade() <= p.getPrioridade()) {
                atual = atual.proximo;
            }
            novo.proximo = atual.proximo;
            atual.proximo = novo;
        }
    }

    // Retira o processo mais prioritário
    public Processo desenfileirar() {
        if (inicio == null) return null;
        Processo p = inicio.processo;
        inicio = inicio.proximo;
        return p;
    }

    public boolean estaVazia() {
        return inicio == null;
    }
}

// -----------------------------
// Classe CPU
// -----------------------------
class Cpu {
    private final int QUANTUM = 3; // fatia de processamento

    /**
     * Executa um processo da fila de processamento por até 3 ciclos
     * @param filaProc fila de processos prontos
     * @param clockAtual tempo atual do relógio global
     * @return processo que foi executado (com tempos atualizados)
     */
    public Processo executar(FilaProcessamento filaProc, int clockAtual) {
        if (filaProc.estaVazia()) return null;

        Processo p = filaProc.desenfileirar();

        // Executa até 3 ciclos
        int ciclos = Math.min(QUANTUM, p.getTempoProcessamento());
        p.setTempoProcessamento(p.getTempoProcessamento() - ciclos);

        // Marca tempo da última execução
        int novoTempo = clockAtual + ciclos;
        p.setUltimaExecucao(novoTempo);

        return p;
    }
}
