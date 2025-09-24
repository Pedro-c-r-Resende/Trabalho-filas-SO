public class Fila {
    private No inicio;
    private No fim;

    public Fila() {
        this.inicio = null;
        this.fim = null;
    }

    public void enfileirar(Processo processo) {
        No novoNo = new No(processo);
        if (estaVazia()) {
            inicio = novoNo;
            fim = novoNo;
        } else {
            fim.setProximo(novoNo);
            fim = novoNo;
        }
    }

    public Processo desenfileirar() {
        if (estaVazia()) {
            return null;
        }
        Processo processo = inicio.getProcesso();
        inicio = inicio.getProximo();
        if (inicio == null) {
            fim = null;
        }
        return processo;
    }

    public boolean estaVazia() {
        return inicio == null;
    }

    private static class No {
        private Processo processo;
        private No proximo;

        public No(Processo processo) {
            this.processo = processo;
            this.proximo = null;
        }

        public Processo getProcesso() {
            return processo;
        }

        public No getProximo() {
            return proximo;
        }

        public void setProximo(No proximo) {
            this.proximo = proximo;
        }
    }

}
