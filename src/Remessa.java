import java.util.ArrayList;
import java.util.List;

public record Remessa(List<Item> listaItens, Caminhao caminhao, Transporte transporte) {
    public Remessa(){
        this(new ArrayList<>(), new CaminhaoPeq(), null);
    }

    public boolean adicionaItem(Item item) {
        if ((caminhao.getPesoAtual() + item.getPeso()) <= caminhao.getPesoMax()) {
            listaItens.add(item);
            return true;
        }
        return false;
    }

    public double getPrecoKm() {
        return caminhao().getPrecoKm();
    }

    public boolean suportaItem(Item item) {
        return caminhao.suportaItem(item);
    }
}
