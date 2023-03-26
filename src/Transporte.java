import java.util.ArrayList;
import java.util.List;

public record Transporte(
        List<Remessa> remessas,
        List<String> cidadesVisitadas,
        String cidadeOrigem,
        String cidadeDestino,
        int id
        )


{

    public Transporte(){
        this(new ArrayList<>(), new ArrayList<>(), null, null, 0);
    }

    public boolean adicionarParada(String nome) {
        if (nome != null) {
            cidadesVisitadas.add(nome);
            return true;
        }
        return false;
    }

    private Remessa calcularMelhorRemessa(Item item){
        Remessa melhorRemessa = null;
        for(Remessa remessa : remessas){
            if (melhorRemessa == null || remessa.suportaItem(item) && remessa.getPrecoKm() < melhorRemessa.getPrecoKm()) {
                melhorRemessa = remessa;
            }
        }
        return melhorRemessa;
    }

    public boolean distribuirItens(List<Item> itens){
        //Peso total da lista de itens
        List<Double> pesos = new ArrayList<>();

        for(Item item : itens){
            pesos.add(item.getPeso());
        }
        //double pesoTotal = itens.stream().map(Item::getPeso).mapToDouble(Double::doubleValue).sum();
        //int caminhoesGrandes;
        //Quanto mais caminhoes cheios grandes melhor. Quanto menos melhor.
        //GRANDE : 1      (27,44) -> 10
        //MÉDIO  : 2,3    (11,92) ->  4
        //PEQUENO: 5,63    (4,87) ->  1


        //2 MÉDIOS: mais barato que 1 grande
        return false;
    }
}