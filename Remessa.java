//import java.util.ArrayList;
//import java.util.List;
//
//public class Remessa{
//List<Item> listaItens;
//Caminhao caminhao;
//Transporte transporte;
//    public Remessa() {
//        listaItens = new ArrayList<>();
//        caminhao = new Caminhao();
//    }
//
//
//    public double getPrecoKm() {
//        return caminhao.getPrecoKm();
//    }
//
//    public boolean suportaItem(Item item) {
//        if ((caminhao.getPesoAtual() + item.getPeso()) <= caminhao.getPesoMax()) {
//            listaItens.add(item);
//            return true;
//        }
//        return false;
//    }
//
//    public double getPesoTotal(){
//        return listaItens.stream().map(Item::getPeso).mapToDouble(Double::doubleValue).sum();
//    }
//
//    public void setCaminhao(Caminhao caminhao) {
//        this.caminhao = caminhao;
//    }
//
//    @Override
//    public String toString() {
//        return "Remessa{" +
//                "listaItens=" + listaItens +
//                ", caminhao=" + caminhao +
//                ", transporte=" + transporte +
//                '}';
//    }
//}
