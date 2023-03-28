public class Caminhao {
    private double precoKm;
    private int pesoMax;

    public Caminhao() {}

    public double getPrecoKm() {
        return precoKm;
    }

    protected void setPrecoKm(double precoKm) {
        this.precoKm = precoKm;
    }

    protected void setPesoMax(int pesoMax) {
        this.pesoMax = pesoMax;
    }


    @Override
    public String toString() {
        return "Caminhao{" +
                "precoKm=" + precoKm +
                ", pesoMax=" + pesoMax +
                "}";
    }
}
