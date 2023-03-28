import java.util.ArrayList;
import java.util.List;

public class Transporte {

    List<Caminhao> caminhoesUsados;
    List<String> cidadesVisitadas;
    String cidadeOrigem;
    String cidadeDestino;
    double custoTotal;
    List<Double> custoPorTrecho;
    List<Item> itensTransportados;
    int camGra;
    int camMed;
    int camPeq;
    int id;
    double custoMedioKm;
    double custoMedioPorProduto;
    double distanciaTotal;
    double pesoTotal;

    public Transporte() {
        caminhoesUsados = new ArrayList<>();
        cidadesVisitadas = new ArrayList<>();
        custoPorTrecho = new ArrayList<>();
        itensTransportados = new ArrayList<>();
        camGra=0;
        camMed=0;
        camPeq=0;
    }

    public void adicionarParada(String nome) {
        if (nome != null) {
            cidadesVisitadas.add(nome);
        }
    }

    public void adicionarCaminhao(Caminhao caminhao){
        caminhoesUsados.add(caminhao);
    }

    public List<Caminhao> distribuirCaminhoes(List<Item> itens) {
        List<Caminhao> caminhoes = new ArrayList<>();
        int camGra = 0;
        int camMed = 0;
        int camPeq = 0;

        //Peso total da lista de itens
        double pesoTotal = itens.stream().map(Item::peso).mapToDouble(Double::doubleValue).sum();
        double pesoAux = pesoTotal;

        while(pesoAux>0){
            if(pesoAux>8000){
                camGra++;
                caminhoes.add(new CaminhaoGra());
                pesoAux -=10000;
            }
            else if (pesoAux > 2000) {
                camMed++;
                caminhoes.add(new CaminhaoMed());
                pesoAux-=4000;
            }
            else{
                camPeq++;
                caminhoes.add(new CaminhaoPeq());
                pesoAux-=1000;
            }
        }
        setCamGra(camGra);
        setCamMed(camMed);
        setCamPeq(camPeq);
        System.out.println("Serão necessários "+camGra+" caminhões grandes, "+camMed+" médios e "+camPeq+" pequenos");
        return caminhoes;
    }

    public String getCidadeOrigem () {
        return cidadeOrigem;
    }

    public void setCidadeOrigem (String cidadeOrigem){
        this.cidadeOrigem = cidadeOrigem;
    }

    public String getCidadeDestino () {
        return cidadeDestino;
    }

    public void setCidadeDestino (String cidadeDestino){
        this.cidadeDestino = cidadeDestino;
    }

    public List<Caminhao> getCaminhoesUsados () {
        return caminhoesUsados;
    }

    public List<String> getCidadesVisitadas () {
        return cidadesVisitadas;
    }

    public int getId () {
        return id;
    }

    public void adicionarItemTransportado(Item item){
        itensTransportados.add(item);
    }

    public int getCamGra() {
        return camGra;
    }

    public void setCamGra(int camGra) {
        this.camGra = camGra;
    }

    public int getCamMed() {
        return camMed;
    }

    public void setCamMed(int camMed) {
        this.camMed = camMed;
    }

    public int getCamPeq() {
        return camPeq;
    }

    public void setCamPeq(int camPeq) {
        this.camPeq = camPeq;
    }

    public double getCustoTotal() {
        return custoTotal;
    }

    public void setCustoTotal(double custoTotal) {
        this.custoTotal = custoTotal;
    }

    public List<Double> getCustoPorTrecho() {
        return custoPorTrecho;
    }

    public void adicionarCustoPorTrecho(double custo){
        custoPorTrecho.add(custo);
    }

    public void setCustoMedioKm(double custoMedioKm) {
        this.custoMedioKm = custoMedioKm;
    }

    public double getDistanciaTotal() {
        return distanciaTotal;
    }

    public void setDistanciaTotal(double distanciaTotal) {
        this.distanciaTotal = distanciaTotal;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Transporte{" +
                "caminhoesUsados=" + caminhoesUsados +
                ", cidadesVisitadas=" + cidadesVisitadas +
                ", id=" + id +
                '}';
    }
}

