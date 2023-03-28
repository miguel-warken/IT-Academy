import java.io.IOException;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;


public class App {
    //Transportes criados irão para relatorios
    static Estatisticas relatorios = new Estatisticas();
    //Contador de id para identificação de transportes
    static int id = 0;
    public static void main(String[] args) {
        //Chamar menu
        try {
            menu();
        }
        catch (IOException e) {
            System.err.println("Erro fatal");
        }

        String caminho = "src\\DNIT-Distancias.csv";

        try {
            DistanciasCidades tabela = new DistanciasCidades(caminho);
        }
        catch (IOException e) {
            System.err.println("Erro ao ler arquivo");
        }

    }

    //MENU
    public static void menu() throws IOException {
        int opcao;
        System.out.println(getBoasVindas()+"\n");
        Scanner scan = new Scanner(System.in);

        do {
            System.out.println(getLogo() + "\nOlá! Bem-vindo à central de operações da Transportadora Dely! Por favor, selecione sua opção:" +
                    "\n1.[Consultar trechos x modalidade]\n2.[Cadastrar transporte]\n3.[Dados estatísticos]\n4.[Finalizar o programa]");
            opcao = scan.nextInt();
            switch (opcao) {
                case 1:
                    consultarTrecho();
                    break;

                case 2:
                    cadastroTransporte();
                    break;

                case 3:
                    relatorios.getEstatisticas();
                    break;

                case 4: // sair
                    break;

            }
        }
        while (opcao != 4);
    }

    //FUNCIONALIDADE Nº1
    public static void consultarTrecho()throws IOException {
        DistanciasCidades tabela = new DistanciasCidades("src\\DNIT-Distancias.csv");
        String cidadeOrigem, cidadeDestino, caminhao = "";
        Transporte transporteConsultado = new Transporte();
        Caminhao caminhaoUtilizado = null;
        int condicaoParada = -1;

        Scanner scanner = new Scanner(System.in);
        System.out.println("Você selecionou: 1.[Consultar trechos x modalidade]. Por favor, insira a cidade de origem e a de destino para a viagem!\nCidade de origem: ");
        try {
            do {
                //Remove todos acentos e deixa em letra maiúscula
                cidadeOrigem = Normalizer.normalize(scanner.nextLine(), Normalizer.Form.NFKD).toUpperCase();
                System.out.println("Cidade de destino: ");
                cidadeDestino = Normalizer.normalize(scanner.nextLine(), Normalizer.Form.NFKD).toUpperCase();
                if (tabela.contains(cidadeOrigem)) {
                    if (tabela.contains(cidadeDestino)) {
                        System.out.println("Cidades adicionadas!");
                        //Adiciona as paradas no transporteConsultado, que não irá entrar nas estatísticas!
                        transporteConsultado.adicionarParada(cidadeOrigem);
                        transporteConsultado.adicionarParada(cidadeDestino);
                        tabela.setCidadeOrigem(cidadeOrigem);
                        tabela.setCidadeFinal(cidadeDestino);
                        condicaoParada++;
                    }
                    else if(tabela.contains(cidadeOrigem)&&(!tabela.contains(cidadeDestino))){
                        System.out.println("A cidade de destino não existe! Insira ambas novamente.");
                    }
                    else if(!tabela.contains(cidadeOrigem)&&(tabela.contains(cidadeDestino))){
                        System.out.println("A cidade de origem não existe! Insira ambas novamente.");
                    }
                }
                else
                    System.out.println("Escreva cidades que constam em nosso sistema!");
            }
            while (condicaoParada == -1);
        } catch (InputMismatchException e) {
            System.out.println("Insira apenas cidades com a ortografia correta que constam em nosso sistema!");
        }

        System.out.println("Estas são as duas cidades cadastradas:" + transporteConsultado.getCidadesVisitadas());
        System.out.println("Agora, selecione sua modade de transporte. Digite 'GRA', 'MED' ou 'PEQ' para selecionar o tamanho do caminhão utilizado!");
        try {
            caminhao = "";
            caminhao = scanner.nextLine();
            while(!(caminhao.equals("GRA") || caminhao.equals("MED") || caminhao.equals("PEQ"))){
                System.out.println("Insira uma modalidade válida!");
                caminhao = scanner.nextLine();
            }

            //Identifica o caminhão selecionado e cria um novo
            switch (caminhao) {
                case "GRA" -> caminhaoUtilizado = new CaminhaoGra();
                case "MED" -> caminhaoUtilizado = new CaminhaoMed();
                case "PEQ" -> caminhaoUtilizado = new CaminhaoPeq();
            }


            System.out.println("Você escolheu o caminhão modelo " + caminhao);

            //Variáveis apenas para facilitar o acesso posteriormente
            int distanciaTotal = tabela.getDistanciaTotal(tabela.getCidadeOrigem(), tabela.getCidadeFinal());
            double precoKmCaminhaoUsado = caminhaoUtilizado.getPrecoKm();

            System.out.println("A distância total entre " + tabela.getCidadeOrigem() + " e " + tabela.getCidadeFinal()+ " é  de: "+distanciaTotal +"km.");
            System.out.println("Sendo assim, usando um caminhao do tipo "+caminhao+" com a taxa de " + precoKmCaminhaoUsado +"R$/km," +" o preço total é de: ["
            +distanciaTotal*precoKmCaminhaoUsado +"] reais!");

        } catch(InputMismatchException e){
            System.out.println("Insira uma modalidade válida!");
        }
    }

    //Funcionalidade Nº2
    public static void cadastroTransporte() throws IOException {
        System.out.println("Você selecionou: 2.[Cadastrar transporte]. Por favor, primeiro insira todas as cidades para transporte EM ORDEM DE ORIGEM -> DESTINO! " +
                "Quando finalizar, digite 'PRONTO'.");

        Transporte novoTransporte = new Transporte();
        DistanciasCidades tabela = new DistanciasCidades("src\\DNIT-Distancias.csv");
        List<Item> itens = new ArrayList<>();

        String nomeItem;
        int quantidadeItem;
        double pesoItem;
        int quantidadeTotal = 0;

        int distanciaTrecho;
        int distanciaTotal = 0;
        double custoTotal = 0;
        double custoPorTrecho;

        int primCondicaoParada = -1;

        Scanner scanner = new Scanner(System.in);

        //Identifica as cidades e coloca em novoTransporte
        do {
            try {
                String parada = "";
                do {
                    parada = Normalizer.normalize(scanner.nextLine(), Normalizer.Form.NFKD).toUpperCase();
                    if (tabela.contains(parada)) {
                        novoTransporte.adicionarParada(parada);
                        System.out.println(parada + " foi adicionado(a) com sucesso!");
                        primCondicaoParada++;
                    }
                    else if (!parada.equals("PRONTO")) {
                        System.out.println("Essa cidade não existe no sistema. Por favor, verifique a ortografia da cidade escrita e tente de novo!");
                    }
                }
                while (!parada.equals("PRONTO"));

            }
            catch (InputMismatchException e) {
                System.out.println("Por favor, insira apenas nomes de cidades incluídas em nosso sistema!");
            }

            try {
                novoTransporte.setCidadeOrigem(novoTransporte.getCidadesVisitadas().get(1));
                novoTransporte.setCidadeDestino(novoTransporte.getCidadesVisitadas().get(novoTransporte.cidadesVisitadas.size() - 1));
            }
            catch (IndexOutOfBoundsException e) {
                System.out.println("Você deve inserir ao menos duas cidades!");
                primCondicaoParada = -1;
            }

        }while(primCondicaoParada == -1);

        System.out.println("Estas são as cidades escolhidas: " + novoTransporte.getCidadesVisitadas() + "\nAgora, cadastre seus itens para transporte.\n");

        //Com cidades escolhidas, o programa começa a cadastrar os itens
        try {
            String condicaoParada ="";
            do {
                System.out.println("Nome do item: ");
                nomeItem = scanner.nextLine();
                System.out.println("Quantidade de itens deste tipo: ");
                quantidadeItem = scanner.nextInt();
                scanner.nextLine();
                System.out.println("Peso de cada item (em kg): ");
                pesoItem = scanner.nextDouble();
                scanner.nextLine();

                //Cria de fato o item
                Item itemCriado = new Item(nomeItem, quantidadeItem, pesoItem);

                if(itemCriado.peso()>0){
                    System.out.println("Item adicionado. Continuar adicionando? Se sim, pressione 'ENTER', se não, digite 'PRONTO'.");
                    //Aqui, adiciona o item criado na lista de itens e na lista de itens transportados
                    itens.add(itemCriado);
                    quantidadeTotal++;
                    novoTransporte.adicionarItemTransportado(itemCriado);
                    condicaoParada = scanner.nextLine();
               }
                else if(itemCriado.nome() == null || itemCriado.peso() <= 0 || itemCriado.quantidade() <= 0) {
                    System.out.println("Não foi possível adicionar o item");
                    condicaoParada="";
                }
                else{
                    System.out.println("Não foi possível adicionar o item");
                    condicaoParada="";
                }

            } while (!condicaoParada.equals("PRONTO"));

            //Aqui, o método fundamental para separar os itens em caminhões de acordo com seus pesos é chamado, retornando
            //uma lista de caminhoes.

            List<Caminhao> caminhoesUsados = novoTransporte.distribuirCaminhoes(itens);

            //Adiciona caminhoes ao transporte sendo criado
            for(Caminhao caminhao : caminhoesUsados){
                novoTransporte.adicionarCaminhao(caminhao);
            }

            //Variável apenas para fácil acesso
            List<String> cidadesVisitadasAtalho = novoTransporte.getCidadesVisitadas();


            //Imprime as informações básicas e seta elas ao transporte criado
            for (int i = 0; i < cidadesVisitadasAtalho.size()-1; i++) {
                distanciaTrecho = tabela.getDistanciaTotal(cidadesVisitadasAtalho.get(i), cidadesVisitadasAtalho
                        .get(i+1));

                distanciaTotal+= distanciaTrecho;

                custoPorTrecho = distanciaTrecho*(novoTransporte.getCamGra()*27.44+ novoTransporte.getCamMed()*11.92+novoTransporte.getCamPeq()*4.87);
                novoTransporte.adicionarCustoPorTrecho(custoPorTrecho);

                custoTotal+= custoPorTrecho;
                System.out.println("TRANSPORTE ID Nº "+id+"\n");
                System.out.println("TRECHO: "+cidadesVisitadasAtalho.get(i)+" -> "+cidadesVisitadasAtalho.get(i+1));
                System.out.println("O preço deste trecho de ["+distanciaTrecho+"km] é de: R$ "+custoPorTrecho);
                System.out.println("O preço totalizado para ["+distanciaTotal+"km] é de: R$ "+custoTotal);
                System.out.println("A distância total percorrida é de: "+distanciaTotal+"km, sendo o custo do transporte desta de: " +
                        "R$"+custoTotal+"\n---------");
                System.out.println("Custo unitário médio: R$" +custoTotal/quantidadeTotal);
            }

            novoTransporte.setId(id);
            novoTransporte.setCustoTotal(custoTotal);
            novoTransporte.setCustoMedioKm(distanciaTotal/custoTotal);
            novoTransporte.setDistanciaTotal(distanciaTotal);

            //Adiciona o novo transporte criado às estatísticas e incrementa o id
            relatorios.adicionarEstatistica(novoTransporte);
            id++;

        } catch (InputMismatchException e){
            System.out.println("Por favor, insira apenas o solicitado.");
        }
    }

    public static String getLogo() {
        return """
                 .----------------.  .----------------.  .----------------.  .----------------.                                        \s
                | .--------------. || .--------------. || .--------------. || .--------------. |                                       \s
                | |  ________    | || |  _________   | || |   _____      | || |  ____  ____  | |                                       \s
                | | |_   ___ `.  | || | |_   ___  |  | || |  |_   _|     | || | |_  _||_  _| | |                                       \s
                | |   | |   `. \\ | || |   | |_  \\_|  | || |    | |       | || |   \\ \\  / /   | |                                       \s
                | |   | |    | | | || |   |  _|  _   | || |    | |   _   | || |    \\ \\/ /    | |                                       \s
                | |  _| |___.' / | || |  _| |___/ |  | || |   _| |__/ |  | || |    _|  |_    | |                                       \s
                | | |________.'  | || | |_________|  | || |  |________|  | || |   |______|   | |                                       \s
                | |              | || |              | || |              | || |              | |                                       \s
                | '--------------' || '--------------' || '--------------' || '--------------' |                                       \s
                 '----------------'  '----------------'  '----------------'  '----------------'\s""";

    }

    public static String getBoasVindas(){
        return """
                 .----------------.  .----------------.  .----------------.                                        \s
                | .--------------. || .--------------. || .--------------. |                                       \s
                | |   ______     | || |  _________   | || | ____    ____ | |                                       \s
                | |  |_   _ \\    | || | |_   ___  |  | || ||_   \\  /   _|| |                                       \s
                | |    | |_) |   | || |   | |_  \\_|  | || |  |   \\/   |  | |                                       \s
                | |    |  __'.   | || |   |  _|  _   | || |  | |\\  /| |  | |                                       \s
                | |   _| |__) |  | || |  _| |___/ |  | || | _| |_\\/_| |_ | |                                       \s
                | |  |_______/   | || | |_________|  | || ||_____||_____|| |                                       \s
                | |              | || |              | || |              | |                                       \s
                | '--------------' || '--------------' || '--------------' |                                       \s
                 '----------------'  '----------------'  '----------------'                                        \s
                 .----------------.  .----------------.  .-----------------. .----------------.  .----------------.\s
                | .--------------. || .--------------. || .--------------. || .--------------. || .--------------. |
                | | ____   ____  | || |     _____    | || | ____  _____  | || |  ________    | || |     ____     | |
                | ||_  _| |_  _| | || |    |_   _|   | || ||_   \\|_   _| | || | |_   ___ `.  | || |   .'    `.   | |
                | |  \\ \\   / /   | || |      | |     | || |  |   \\ | |   | || |   | |   `. \\ | || |  /  .--.  \\  | |
                | |   \\ \\ / /    | || |      | |     | || |  | |\\ \\| |   | || |   | |    | | | || |  | |    | |  | |
                | |    \\ ' /     | || |     _| |_    | || | _| |_\\   |_  | || |  _| |___.' / | || |  \\  `--'  /  | |
                | |     \\_/      | || |    |_____|   | || ||_____|\\____| | || | |________.'  | || |   `.____.'   | |
                | |              | || |              | || |              | || |              | || |              | |
                | '--------------' || '--------------' || '--------------' || '--------------' || '--------------' |
                 '----------------'  '----------------'  '----------------'  '----------------'  '----------------'\s""";
    }
}