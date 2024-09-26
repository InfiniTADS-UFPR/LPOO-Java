package tarefa4;

public class Pilha<P> {
    private Elemento topo;

    public Pilha() {
        this.topo = null;
    }

    public void empilha(P valor) {
        topo = new Elemento(valor, topo);
    }

    public P desempilha() {
        if (topo == null) throw new IllegalStateException("Pilha vazia");
        P valor = topo.valor;
        topo = topo.proximo;
        return valor;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder("[ ");
        for (Elemento aux = topo; aux != null; aux = aux.proximo) {
            str.append(aux.valor).append(", ");
        }
        if (str.length() > 1) str.setLength(str.length() - 2); // Remove the last comma and space
        return str.append(" ]").toString();
    }

    private class Elemento {
        private final P valor;
        private final Elemento proximo;

        public Elemento(P valor, Elemento proximo) {
            this.valor = valor;
            this.proximo = proximo;
        }
    }
}