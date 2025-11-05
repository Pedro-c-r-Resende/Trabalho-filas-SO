public class Processo {
    private int id;
    private int tempoEntrada;
    private int tempoIO;
    private int tempoProcessamento;
    private int prioridade;

    // Campos adicionais para controle da simulação
    private int tempoProcessamentoRestante;
    private int tempoIORestante;
    private int tempoSaida;
    private EstadoProcesso estado;
    private int ultimaExecucao;

    public enum EstadoProcesso {
        AGUARDANDO_ENTRADA,
        PRONTO,
        EXECUTANDO,
        BLOQUEADO_IO,
        FINALIZADO
    }

    public Processo(int id, int tempoEntrada, int tempoIO, int tempoProcessamento, int prioridade) {
        this.id = id;
        this.tempoEntrada = tempoEntrada;
        this.tempoIO = tempoIO;
        this.tempoProcessamento = tempoProcessamento;
        this.prioridade = prioridade;

        // Inicializar campos de controle
        this.tempoProcessamentoRestante = tempoProcessamento;
        this.tempoIORestante = tempoIO;
        this.tempoSaida = -1;
        this.estado = EstadoProcesso.AGUARDANDO_ENTRADA;
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public int getTempoEntrada() {
        return tempoEntrada;
    }

    public int getUltimaExecucao(){
        return ultimaExecucao;
    }

    public int getTempoIO() {
        return tempoIO;
    }

    public int getTempoProcessamento() {
        return tempoProcessamento;
    }

    public int getPrioridade() {
        return prioridade;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTempoEntrada(int tempoEntrada) {
        this.tempoEntrada = tempoEntrada;
    }

    public void setTempoIO(int tempoIO) {
        this.tempoIO = tempoIO;
    }

    public void setTempoProcessamento(int tempoProcessamento) {
        this.tempoProcessamento = tempoProcessamento;
    }

    public void setPrioridade(int prioridade) {
        this.prioridade = prioridade;
    }

    // Getters e Setters para os novos campos
    public int getTempoProcessamentoRestante() {
        return tempoProcessamentoRestante;
    }

    public void setTempoProcessamentoRestante(int tempoProcessamentoRestante) {
        this.tempoProcessamentoRestante = tempoProcessamentoRestante;
    }

    public int getTempoIORestante() {
        return tempoIORestante;
    }

    public void setTempoIORestante(int tempoIORestante) {
        this.tempoIORestante = tempoIORestante;
    }

    public int getTempoSaida() {
        return tempoSaida;
    }

    public void setTempoSaida(int tempoSaida) {
        this.tempoSaida = tempoSaida;
    }

    public EstadoProcesso getEstado() {
        return estado;
    }

    public void setEstado(EstadoProcesso estado) {
        this.estado = estado;
    }

    public void setUltimaExecucao(int ultimaExecucao) {
        return ultimaExecucao ;
    }



    @Override
    public String toString() {
        return "Processo{" +
                "id=" + id +
                ", tempoEntrada=" + tempoEntrada +
                ", tempoIO=" + tempoIO +
                ", tempoProcessamento=" + tempoProcessamento +
                ", prioridade=" + prioridade +
                ", estado=" + estado +
                '}';
    }
}
