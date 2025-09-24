public class NoProcesso {
        Processo processo;
        NoProcesso proximo;

        public NoProcesso(Processo processo) {
            this.processo = processo;
            this.proximo = null;
        }
}
