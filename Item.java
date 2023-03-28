public record Item(String nome, int quantidade, double peso) {
    public Item(String nome, int quantidade, double peso) {
        this.nome = nome;
        this.quantidade = quantidade;
        this.peso = peso * quantidade;
    }

    @Override
    public String toString() {
        return "Item{" +
                "nome='" + nome + '\'' +
                ", quantidade=" + quantidade +
                ", peso=" + peso +
                '}';
    }
}

