public class CaminhaoGra extends Caminhao {
    public CaminhaoGra(double precoKm, int id, int pesoMax) {
        super(precoKm, id, pesoMax);
        this.precoKm = 27.44;
        this.pesoMax = 10000; // kg
        this.id = id;

    }

    @Override
    public double getPrecoKm() {
        return precoKm;
    }

    @Override
    public double calculaViagem(int km, int[]cidades){
        return 1;
    }
}