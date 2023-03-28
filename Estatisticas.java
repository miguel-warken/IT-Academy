import java.util.ArrayList;
import java.util.List;

//Para imprimir as estatísticas, uma nova classe foi criada, guardando uma lista do tipo Transporte dos transportes
//já finalizados
public class Estatisticas {

    List<Transporte> finalizados;

    public Estatisticas(){
        finalizados = new ArrayList<>();
    }

    public void adicionarEstatistica(Transporte transporteAdicionado){
        finalizados.add(transporteAdicionado);
    }

    //Esse método contém alguns cálculos, pois não era necessário atribuir toda informação pedida ao objeto do transporte.
    public void getEstatisticas(){
        if(finalizados.size() ==0){
            System.out.println("Vazio por enquanto!");
            return;
        }

        int soma = 0;

        System.out.println("[RELATÓRIO DE TRANSPORTES PASSADOS]");
        for(Transporte transportePassado : finalizados){
            System.out.println("TRANSPORTE ID Nº "+transportePassado.getId());
            System.out.println("CUSTO TOTAL: R$"+transportePassado.getCustoTotal());
            System.out.println("CUSTO POR TRECHO: ");
            for (int i = 0; i < transportePassado.custoPorTrecho.size(); i++) {
                System.out.println("    -"+transportePassado.getCidadesVisitadas().get(i)+" -> "+transportePassado.
                        getCidadesVisitadas().get(i+1)+" = R$"+transportePassado.getCustoPorTrecho().get(i));
            }
            System.out.println("CUSTO MÉDIO POR KM: R$"+transportePassado.getCustoTotal()/transportePassado.getDistanciaTotal());
            System.out.println("CUSTO MÉDIO POR TIPO DE PRODUTO: ");
            for(Item item : transportePassado.itensTransportados){
                System.out.println("    -"+item.nome() +" -> R$"+transportePassado.getCustoTotal()/item.quantidade());
            }
            System.out.println("CUSTO TOTAL POR TRECHO: R$"+transportePassado.getCustoPorTrecho().stream().mapToDouble(f -> f).sum());
            System.out.println("CUSTO TOTAL POR MODALIDADE DE TRANSPORTE: \n    -CAMINHÕES 'GRA' -> R$"+transportePassado.getCamGra()*transportePassado.getDistanciaTotal()+
                    "\n    -CAMINHÕES 'MED' -> R$"+transportePassado.getCamMed()*transportePassado.getDistanciaTotal()+"\n    -CAMINHÕES 'PEQ' -> R$"
                    +transportePassado.getCamPeq()*transportePassado.getDistanciaTotal());
            System.out.println("TOTAL DE CAMINHÕES UTILIZADOS: "+transportePassado.caminhoesUsados.size());

            for(Item item : transportePassado.itensTransportados){
                soma += item.quantidade();
            }
            System.out.println("TOTAL DE ITENS TRANSPORTADOS: " +soma);

            System.out.print("\n-----------------------------------------------\n");
        }
    }

}
