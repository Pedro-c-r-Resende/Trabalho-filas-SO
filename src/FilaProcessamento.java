//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

class FilaProcessamento {
    private NoProcesso inicio = null;

    public FilaProcessamento() {
    }

    public void enfileirar(Processo p) {
        NoProcesso novo = new NoProcesso(p);
        if (this.inicio != null && p.getPrioridade() >= this.inicio.processo.getPrioridade()) {
            NoProcesso atual;
            for(atual = this.inicio; atual.proximo != null && atual.proximo.processo.getPrioridade() <= p.getPrioridade(); atual = atual.proximo) {
            }

            novo.proximo = atual.proximo;
            atual.proximo = novo;
        } else {
            novo.proximo = this.inicio;
            this.inicio = novo;
        }

    }

    public Processo desenfileirar() {
        if (this.inicio == null) {
            return null;
        } else {
            Processo p = this.inicio.processo;
            this.inicio = this.inicio.proximo;
            return p;
        }
    }

    public boolean estaVazia() {
        return this.inicio == null;
    }
}
