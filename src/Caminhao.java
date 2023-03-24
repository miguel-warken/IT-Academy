public abstract class Caminhao {
    double precoKm;
    // int acionados; //acionados são numeros de objetos, não um atributo
    int id;
    int pesoMax;
    double pesoAtual;

    public Caminhao(double precoKm, int id, int pesoMax) {
        this.precoKm = precoKm;
        this.id = id;
        this.pesoMax = pesoMax;
    }

    public double getPrecoKm() {
        return precoKm;
    }

    public int getPesoMax() {
        return this.pesoMax;
    }

    public double getPesoAtual() {
        return this.pesoAtual;
    }

    // public int getAcionados(){return this.acionados;}

    public abstract double calculaViagem(int km, int[] cidades);

    // public void adicionaPeso(double peso){};

}
