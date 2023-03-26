import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Caminhao {
    private double precoKm;
    private int pesoMax;
    private double pesoAtual;
    //Campo pode existir ou não. Avisa de forma explícita
    private Optional<Transporte> atual;
    private final List<Transporte> finalizados;

    public Caminhao() {
        finalizados = new ArrayList<>();
        this.pesoAtual =0;
    }

    public double getPrecoKm() {
        return precoKm;
    }

    protected void setPrecoKm(double precoKm) {
        this.precoKm = precoKm;
    }

    public int getPesoMax() {
        return this.pesoMax;
    }

    protected void setPesoMax(int pesoMax) {
        this.pesoMax = pesoMax;
    }

    public double getPesoAtual() {
        return this.pesoAtual;
    }

    protected void setPesoAtual(double pesoAtual) {
        this.pesoAtual = pesoAtual;
    }

    public boolean suportaItem(Item item) {
        return getPesoAtual()+ item.getPeso() <= getPesoMax();
    }
}
