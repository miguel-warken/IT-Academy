import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public record Transporte(
        List<Caminhao> caminhoesUsados,
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

//    private Remessa calcularMelhorRemessa(Item item){
//        Remessa melhorRemessa = null;
//        for(Remessa remessa : remessas){
//            if (melhorRemessa == null || remessa.suportaItem(item) && remessa.getPrecoKm() < melhorRemessa.getPrecoKm()) {
//                melhorRemessa = remessa;
//            }
//        }
//        return melhorRemessa;
//    }

    public boolean distribuirItens(List<Item> itens){
        //Peso total da lista de itens
        List<Double> pesos = new ArrayList<>();
        Transporte transporteCriado = new Transporte();
        double pesoTotal = itens.stream().map(Item::getPeso).mapToDouble(Double::doubleValue).sum();

        for(Item item : itens){
            pesos.add(item.getPeso());
        }

        //Se não totalizar mais de 1000kg, tranquilo colocar no pequeno
        if(pesoTotal<=1000){
            CaminhaoPeq caminhaoPequeno = new CaminhaoPeq();
            Remessa novaRemessa = new Remessa(itens, caminhaoPequeno, transporteCriado);
            caminhaoPequeno.adicionarRemessa(novaRemessa);
        }

        //Distribuir entre os médios e pequenos
        else if(pesoTotal >= 4000 && pesoTotal < 10000) {
            int contagem = 0;
            double nMedios = Math.floor(pesoTotal/4000);
            Collections.sort(pesos);
            Collections.reverse(pesos);
            while(contagem != nMedios){
                CaminhaoMed caminhaoMedio = new CaminhaoMed();
                for(int i =0; i<itens.size(); i++) {
                    Item itemAnalisado = itens.get(i);
                    Remessa novaRemessa = new Remessa(itens, caminhaoMedio, transporteCriado);
                    if (caminhaoMedio.suportaItem(itemAnalisado)) {
                        novaRemessa.adicionaItem(itemAnalisado);
                        itens.remove(itemAnalisado);
                        caminhaoMedio.adicionarPeso(itemAnalisado.getPeso());
                    }
            }
                contagem++;
            }
        }
        //int caminhoesGrandes;
        //Quanto mais caminhoes cheios grandes melhor. Quanto menos melhor.
        //GRANDE : 1      (27,44) -> 10
        //MÉDIO  : 2,3    (11,92) ->  4
        //PEQUENO: 5,63    (4,87) ->  1


        //2 MÉDIOS: mais barato que 1 grande
        return false;
    }
}