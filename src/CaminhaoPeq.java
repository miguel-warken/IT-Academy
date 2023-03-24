public class CaminhaoPeq extends Caminhao{
    public CaminhaoPeq(double precoKm, int id, int pesoMax) {
        super(precoKm, id, pesoMax);
        this.precoKm = 4.87;
        this.pesoMax = 1000; // kg
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

