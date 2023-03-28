import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class DistanciasCidades {
    private final Map<String, Map<String, Integer>> cidades;
    private String cidadeOrigem;
    private String cidadeFinal;

    public DistanciasCidades(String caminho) throws IOException {
        cidades = new LinkedHashMap<>();
        try (BufferedReader reader = Files.newBufferedReader(Path.of(caminho))) {

            // Serão os nomes das cidades na primeira linha
            String header = reader.readLine();
            String[] cidadesHeaders = header.split(";");

            // Coloca tais nomes em cidades, serão as colunas
            for (String nomeCidade : cidadesHeaders) {
                //Os nomes são os HashCodes enquanto instanciam HashMaps respectivos
                cidades.put(nomeCidade, new LinkedHashMap<>());
            }

            //Index da linha, começando com 0 (desconsiderando o header)
            int lineNum = -1;
            String linhaTabela;


            while ((linhaTabela = reader.readLine()) != null) {
                //Começa com 0 e incrementa a cada linha
                lineNum++;

                //Contém os nomes das cidades em forma de lista
                List<String> listaCidades = (cidades.keySet().stream().toList());
                //Cidade atual para armazenamento dos dados
                String cidade = listaCidades.get(lineNum);
                //Distâncias de todas as cidades relativas à atual
                Map<String, Integer> distancias = cidades.get(cidade);
                //Lista com todas as distâncias a serem inseridas
                List<Integer> distanciasNum = Arrays.stream(linhaTabela.split(";")).map(Integer::parseInt).toList();

                for (int i = 0; i < distanciasNum.size(); i++) {
                    //Cidade i da lista de nomes
                    String cidadeAlvo = listaCidades.get(i);
                    //Insere a distância entre cidades
                    distancias.put(cidadeAlvo, distanciasNum.get(i));
                }
            }
        }
    }

    public Integer getDistanciaTotal(String cidadeOrigem, String cidadeDestino) {
        if (!cidades.containsKey(cidadeOrigem) || !cidades.get(cidadeOrigem).containsKey(cidadeDestino)) {
            return null;
        }
        return cidades.get(cidadeOrigem).get(cidadeDestino);
    }

    public boolean contains(String nomeCidade) {
        return cidades.containsKey(nomeCidade);
    }

    public String getCidadeOrigem(){
        return cidadeOrigem;
    }

    public void setCidadeOrigem(String cidadeOrigem){
        this.cidadeOrigem = cidadeOrigem;
    }

    public String getCidadeFinal(){
        return cidadeFinal;
    }

    public void setCidadeFinal(String cidadeFinal){
        this.cidadeFinal = cidadeFinal;
    }
}


