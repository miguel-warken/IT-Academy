public class CaminhaoMed extends Caminhao {
    public CaminhaoMed(double precoKm, int id, int pesoMax) {
        super(precoKm, id, pesoMax);
        this.precoKm = 11.92;
        this.pesoMax = 4000; // kg
        this.id = id;

    }

    @Override
    public double getPrecoKm() {
        return precoKm;
    }

    @Override
    public double calculaViagem(int km, int[] cidades) {
        return 1;
    }
}
