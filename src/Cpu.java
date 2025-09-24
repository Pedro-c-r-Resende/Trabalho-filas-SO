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