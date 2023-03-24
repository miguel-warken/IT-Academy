
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class App {

    public static void main(String[] args) {
        // menu();
        String nome = "src\\DNIT-Distancias.csv";
        Map<String, Map<String, Integer>> cidades = new HashMap();

        try {

            BufferedReader reader = null;
            reader = new BufferedReader(new FileReader(nome));
            String line = "";

            while ((line = reader.readLine()) != null) {

                String data[] = line.split(";");

            }

            System.out.println(cidades);
            reader.close();

        } catch (IOException e) {

            e.printStackTrace();
        }

    }

    public static void menu() {
        int opcao;
        String logo = dely();
        Scanner scan = new Scanner(System.in);
        System.out.println(logo
                + "Olá! Bem-vindo à central de operações da Transportadora Dely! Por favor, selecione sua opção:\n1.[Consultar trechos x modalidade]\n2.[Cadastrar transporte]\n3.[Dados estatísticos]\n4.[Finalizar o programa]");
        opcao = scan.nextInt();
        while (true) {
            switch (opcao) {
                case 1:

                    break;

                case 2:
                    break;

                case 3:
                    break;

                case 4: // sair
                    break;

            }
        }
    }

    public static int geraHashCode(String s) {
        int novoHash = 0;
        for (int i = 0; i < s.length(); i++) {
            s.toLowerCase();
            novoHash = s.charAt(i) - 'a' + 1;
        }
        return novoHash;
    }

    public static String dely() {
        return " .----------------.  .----------------.  .----------------.  .----------------.                                         \n"
                +
                "| .--------------. || .--------------. || .--------------. || .--------------. |                                        \n"
                +
                "| |  ________    | || |  _________   | || |   _____      | || |  ____  ____  | |                                        \n"
                +
                "| | |_   ___ `.  | || | |_   ___  |  | || |  |_   _|     | || | |_  _||_  _| | |                                        \n"
                +
                "| |   | |   `. \\ | || |   | |_  \\_|  | || |    | |       | || |   \\ \\  / /   | |                                        \n"
                +
                "| |   | |    | | | || |   |  _|  _   | || |    | |   _   | || |    \\ \\/ /    | |                                        \n"
                +
                "| |  _| |___.' / | || |  _| |___/ |  | || |   _| |__/ |  | || |    _|  |_    | |                                        \n"
                +
                "| | |________.'  | || | |_________|  | || |  |________|  | || |   |______|   | |                                        \n"
                +
                "| |              | || |              | || |              | || |              | |                                        \n"
                +
                "| '--------------' || '--------------' || '--------------' || '--------------' |                                        \n"
                +
                " '----------------'  '----------------'  '----------------'  '----------------'                                         \n"
                +
                " .----------------.  .----------------.  .----------------.                                                             \n"
                +
                "| .--------------. || .--------------. || .--------------. |                                                            \n"
                +
                "| |   ______     | || |  _________   | || | ____    ____ | |                                                            \n"
                +
                "| |  |_   _ \\    | || | |_   ___  |  | || ||_   \\  /   _|| |                                                            \n"
                +
                "| |    | |_) |   | || |   | |_  \\_|  | || |  |   \\/   |  | |                                                            \n"
                +
                "| |    |  __'.   | || |   |  _|  _   | || |  | |\\  /| |  | |                                                            \n"
                +
                "| |   _| |__) |  | || |  _| |___/ |  | || | _| |_\\/_| |_ | |                                                            \n"
                +
                "| |  |_______/   | || | |_________|  | || ||_____||_____|| |                                                            \n"
                +
                "| |              | || |              | || |              | |                                                            \n"
                +
                "| '--------------' || '--------------' || '--------------' |                                                            \n"
                +
                " '----------------'  '----------------'  '----------------'                                                             \n"
                +
                " .----------------.  .----------------.  .-----------------. .----------------.  .----------------.  .----------------. \n"
                +
                "| .--------------. || .--------------. || .--------------. || .--------------. || .--------------. || .--------------. |\n"
                +
                "| | ____   ____  | || |     _____    | || | ____  _____  | || |  ________    | || |     ____     | || |              | |\n"
                +
                "| ||_  _| |_  _| | || |    |_   _|   | || ||_   \\|_   _| | || | |_   ___ `.  | || |   .'    `.   | || |      _       | |\n"
                +
                "| |  \\ \\   / /   | || |      | |     | || |  |   \\ | |   | || |   | |   `. \\ | || |  /  .--.  \\  | || |     | |      | |\n"
                +
                "| |   \\ \\ / /    | || |      | |     | || |  | |\\ \\| |   | || |   | |    | | | || |  | |    | |  | || |     | |      | |\n"
                +
                "| |    \\ ' /     | || |     _| |_    | || | _| |_\\   |_  | || |  _| |___.' / | || |  \\  `--'  /  | || |     | |      | |\n"
                +
                "| |     \\_/      | || |    |_____|   | || ||_____|\\____| | || | |________.'  | || |   `.____.'   | || |     |_|      | |\n"
                +
                "| |              | || |              | || |              | || |              | || |              | || |     (_)      | |\n"
                +
                "| '--------------' || '--------------' || '--------------' || '--------------' || '--------------' || '--------------' |\n"
                +
                " '----------------'  '----------------'  '----------------'  '----------------'  '----------------'  '----------------' \n\n";
    }
}