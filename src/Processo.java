public class Processo {
    private int id;
    private int tempoEntrada;
    private int tempoIO;
    private int tempoProcessamento;
    private int prioridade;

    public Processo(int id, int tempoEntrada, int tempoIO, int tempoProcessamento, int prioridade) {
        this.id = id;
        this.tempoEntrada = tempoEntrada;
        this.tempoIO = tempoIO;
        this.tempoProcessamento = tempoProcessamento;
        this.prioridade = prioridade;
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public int getTempoEntrada() {
        return tempoEntrada;
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

    @Override
    public String toString() {
        return "Processo{" +
                "id=" + id +
                ", tempoEntrada=" + tempoEntrada +
                ", tempoIO=" + tempoIO +
                ", tempoProcessamento=" + tempoProcessamento +
                ", prioridade=" + prioridade +
                '}';
    }
}
