import java.io.IOException;
import java.text.Normalizer;
import java.util.InputMismatchException;
import java.util.Scanner;


public class App {

    public static void main(String[] args) {

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

    public static void menu() throws IOException {
        int opcao;
        String logo = getLogo();
        System.out.println(getBoasVindas());
        Scanner scan = new Scanner(System.in);

        do {
            System.out.println(logo + "\nOlá! Bem-vindo à central de operações da Transportadora Dely! Por favor, selecione sua opção:" +
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
                    break;

                case 4: // sair
                    break;

            }
        }
        while (opcao != 4);
    }

    public static void consultarTrecho()throws IOException {
        DistanciasCidades tabela = new DistanciasCidades("src\\DNIT-Distancias.csv");
        String cidadeOrigem, cidadeDestino, caminhao = "";
        Transporte transporteConsultado = new Transporte();
        Caminhao caminhaoUtilizado = null;
        int condicaoParada = -1;

        Scanner scanner = new Scanner(System.in);
        System.out.println("Você selecionou: 1.[Consultar trechos x modalidade]. Por favor, insira a cidade de origem e a de destino para a viagem!");
        try {
            do {
                cidadeOrigem = Normalizer.normalize(scanner.nextLine(), Normalizer.Form.NFKD).toUpperCase();
                cidadeDestino = Normalizer.normalize(scanner.nextLine(), Normalizer.Form.NFKD).toUpperCase();
                if (tabela.contains(cidadeOrigem)) {
                    System.out.println("A cidade de origem foi adicionada!");
                    if (tabela.contains(cidadeDestino)) {
                        System.out.println("A cidade de destino foi adicionada!");
                        transporteConsultado.adicionarParada(cidadeOrigem);
                        transporteConsultado.adicionarParada(cidadeDestino);
                        tabela.setCidadeOrigem(cidadeOrigem);
                        tabela.setCidadeFinal(cidadeDestino);
                        condicaoParada++;
                    }
                    System.out.println("A cidade de destino não existe!");
                }
                else
                    System.out.println("Escreva cidades que constam em nosso sistema!");
            }
            while (condicaoParada == -1);
        } catch (InputMismatchException e) {
            System.out.println("Insira apenas cidades com a ortografia correta que constam em nosso sistema!");
        }

        System.out.println("Estas são as duas cidades cadastradas:" + transporteConsultado.cidadesVisitadas());
        System.out.println("Agora, selecione sua modade de transporte. Digite 'GRA', 'MED' ou 'PEQ' para selecionar o tamanho do caminhão utilizado!");
        try {
            caminhao = "";
            caminhao = scanner.nextLine();
            while(!(caminhao.equals("GRA") || caminhao.equals("MED") || caminhao.equals("PEQ"))){
                System.out.println("Insira uma modalidade válida!");
                caminhao = scanner.nextLine();
            }

            switch (caminhao) {
                case "GRA" -> caminhaoUtilizado = new CaminhaoGra();
                case "MED" -> caminhaoUtilizado = new CaminhaoMed();
                case "PEQ" -> caminhaoUtilizado = new CaminhaoPeq();
            }


            System.out.println("Você escolheu o caminhão modelo " + caminhao);

            int distanciaTotal = tabela.getDistanciaTotal(tabela.getCidadeOrigem(), tabela.getCidadeFinal());
            double precoKmCaminhaoUsado = caminhaoUtilizado.getPrecoKm();

            System.out.println("A distância total entre " + tabela.getCidadeOrigem() + " e " + tabela.getCidadeFinal()+ " é  de: "+distanciaTotal +"km.");
            System.out.println("Sendo assim, usando um caminhao do tipo "+caminhao+" com a taxa de " + precoKmCaminhaoUsado +"R$/km," +" o preço total é de: ["
            +distanciaTotal*precoKmCaminhaoUsado +"] reais!");

        } catch(InputMismatchException e){
            System.out.println("Insira uma modalidade válida!");
        }
    }

    public static void cadastroTransporte() throws IOException {
        System.out.println("Você selecionou: 2.[Cadastrar transporte]. Por favor, primeiro insira todas as cidades para transporte em ordem de parada! Quando finalizar, digite 'PRONTO'.");

        Transporte novoTransporte = new Transporte();
        DistanciasCidades tabela = new DistanciasCidades("src\\DNIT-Distancias.csv");
        Remessa remessaCriada = new Remessa();

        String nomeItem;
        int quantidadeItem;
        double pesoItem;

        Scanner scanner = new Scanner(System.in);

        try {
            String parada = "";
            do {
                parada = Normalizer.normalize(scanner.nextLine(), Normalizer.Form.NFKD).toUpperCase();
                if (tabela.contains(parada)) {
                    novoTransporte.adicionarParada(parada);
                    System.out.println(parada + " foi adicionado(a) com sucesso!");
                }
                else if (!parada.equals("PRONTO")) {
                    System.out.println("Essa cidade não existe no sistema. Por favor, verifique a ortografia da cidade escrita e tente de novo!");
                }
            } while (!parada.equals("PRONTO"));

        } catch(InputMismatchException e){
                System.out.println("Por favor, insira apenas nomes de cidades incluídas em nosso sistema!");
        }

        System.out.println("Estas são as cidades escolhidas: " + novoTransporte.cidadesVisitadas() + "\nAgora, cadastre seus itens para transporte.\n");

        try {
            String condicaoParada ="";
            do {
                System.out.println("Nome do item: \nQuantidade de itens deste tipo: \nPeso de cada item: ");
                nomeItem = scanner.nextLine();
                quantidadeItem = scanner.nextInt();
                pesoItem = scanner.nextInt();
                Item itemCriado = new Item(nomeItem, quantidadeItem, pesoItem);
                if(remessaCriada.adicionaItem(itemCriado)){
                    System.out.println("Item adicionado. Continuar adicionando? Se sim, digite qualquer coisa, se não, digite 'PRONTO'.");
                    condicaoParada = scanner.nextLine();
               }
                else {
                    System.out.println("Não foi possível adicionar o item");
                }
            } while (!condicaoParada.equals("PRONTO"));

        } catch (InputMismatchException e){
            System.out.println("Por favor, insira apenas o solicitado");
        }

            System.out.println(remessaCriada.toString());

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
        return  " .----------------.  .----------------.  .----------------.                                         \n" +
                "| .--------------. || .--------------. || .--------------. |                                        \n" +
                "| |   ______     | || |  _________   | || | ____    ____ | |                                        \n" +
                "| |  |_   _ \\    | || | |_   ___  |  | || ||_   \\  /   _|| |                                        \n" +
                "| |    | |_) |   | || |   | |_  \\_|  | || |  |   \\/   |  | |                                        \n" +
                "| |    |  __'.   | || |   |  _|  _   | || |  | |\\  /| |  | |                                        \n" +
                "| |   _| |__) |  | || |  _| |___/ |  | || | _| |_\\/_| |_ | |                                        \n" +
                "| |  |_______/   | || | |_________|  | || ||_____||_____|| |                                        \n" +
                "| |              | || |              | || |              | |                                        \n" +
                "| '--------------' || '--------------' || '--------------' |                                        \n" +
                " '----------------'  '----------------'  '----------------'                                         \n" +
                " .----------------.  .----------------.  .-----------------. .----------------.  .----------------. \n" +
                "| .--------------. || .--------------. || .--------------. || .--------------. || .--------------. |\n" +
                "| | ____   ____  | || |     _____    | || | ____  _____  | || |  ________    | || |     ____     | |\n" +
                "| ||_  _| |_  _| | || |    |_   _|   | || ||_   \\|_   _| | || | |_   ___ `.  | || |   .'    `.   | |\n" +
                "| |  \\ \\   / /   | || |      | |     | || |  |   \\ | |   | || |   | |   `. \\ | || |  /  .--.  \\  | |\n" +
                "| |   \\ \\ / /    | || |      | |     | || |  | |\\ \\| |   | || |   | |    | | | || |  | |    | |  | |\n" +
                "| |    \\ ' /     | || |     _| |_    | || | _| |_\\   |_  | || |  _| |___.' / | || |  \\  `--'  /  | |\n" +
                "| |     \\_/      | || |    |_____|   | || ||_____|\\____| | || | |________.'  | || |   `.____.'   | |\n" +
                "| |              | || |              | || |              | || |              | || |              | |\n" +
                "| '--------------' || '--------------' || '--------------' || '--------------' || '--------------' |\n" +
                " '----------------'  '----------------'  '----------------'  '----------------'  '----------------' ";
    }
}