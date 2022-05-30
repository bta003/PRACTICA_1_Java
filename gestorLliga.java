import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.util.Scanner;

public class gestorLliga {
    public static void main(String[] args) throws IOException {
        // QUAN S'EXECUTA EL PROGRAMA PRIMER ES LLEGEIX L'ARXIU I DESPRES PROCEDEIX
        // AMB EL MENU PRINCIPAL
        try {
            llegirArxiu(nomsequips, dadesequips);
            menuAPP();
        } catch (Exception e) {
            // SI HI HA UN ERROR ES MOSTRA EL SEGUENT MISSATGE
            System.out.println("Hi ha hagut un problema amb l'aplicació");
            e.printStackTrace();
        }
    }

    // DECLAREM UN ARRAY QUE TINDRA ELS NOMS DE TOTS ELS EQUIPS I UN SEGON ARRAY
    // BIDIMENSIONAL
    // QUE TINDRA COM A CONTINGUT TOTES LES DADES I PUNTUACIONS
    static String[] nomsequips = new String[15];
    static int[][] dadesequips = new int[150][150];

    // MENÚ DE GESTIÓ DE LA LLIGA ESPORTIVA
    static void menuAPP() throws IOException {
        Scanner teclat = new Scanner(System.in);
        boolean sortir = false;
        do {
            System.out.println("\n--------------------------------------------------------");
            System.out.println("*MENU GESTOR LLIGA DE FUTBOL*");
            System.out.println("1. Visualitzar taula de puntuacions");
            System.out.println("2. Afegir un nou equip a la taula");
            System.out.println("3. Modificar les dades d'un equip");
            System.out.println("4. Visualitzar les dades del líder i cuer de la lliga");
            System.out.println("5. Sortir");
            System.out.println("--------------------------------------------------------");
            System.out.println("\nTRIA UNA OPCIÓ:");

            int opcio = teclat.nextInt();

            switch (opcio) {
                case 1:
                    // AQUESTA OPCIO LLEGEIX L'ARXIU AMB LA TAULA DE PUNTUACIONS I ENS
                    // LA MOSTRA PER CONSOLA
                    llegirArxiu(nomsequips, dadesequips);
                    break;
                case 2:
                    // AFEGEIX UN NOU EQUIP A LA TAULA
                    afegirNouEquip();
                    break;
                case 3:
                    // MODIFICA UN DELS EQUIPS EXISTENTS
                    modificarEquip();
                    break;
                case 4:
                    // VISUALITZA L'EQUIP AMB MÉS PUNTUACIÓ I L'EQUIP AMB MENYS PUNTUACIÓ
                    visualitzarLiderCuer();
                    break;
                case 5:
                    // QUAN SORTIM DEL PROGRAMA, AQUEST GUARDA LA INFORMACIO DELS ARRAYS AL
                    // ARXIU DE TEXT
                    escriuArxiu(nomsequips, dadesequips);
                    sortir = true;
                    break;
                default:
                    System.out.println("\n" + opcio + " NO ÉS UNA OPCIÓ NO VÀLIDA \nSelecciona un numero entre 1 i 5");
            }

        } while (!sortir);
    }

    // AQUESTA OPCIO LLEGEIX L'ARXIU AMB LA TAULA DE PUNTUACIONS I ENS
    // LA MOSTRA PER CONSOLA
    static void llegirArxiu(String[] nomsequips, int[][] dadesequips) throws IOException {
        // INICIALITZEM A NULL
        File arxiu = null;
        FileReader fr = null;
        BufferedReader br = null;

        try {

            arxiu = new File("TaulaPuntuacions.txt");
            fr = new FileReader(arxiu);
            br = new BufferedReader(fr);

            String linea;
            // CAPÇALERA DE LA TAULA DE PUNTUACIONS
            System.out.println("-----------------------------------------------------------------");
            System.out.printf("%20s %5s %5s %5s %5s %8s", "NOM", "PJ", "PG", "PE", "PP",
                    "PUNTS");
            System.out.println();
            System.out.println("-----------------------------------------------------------------");

            int i = 0;
            int z = 0;

            // BUCLE QUE LLEGEIX TOTES LES LINIES I ES QUEDA AMB LES QUE TENEN ALGUNA COSA
            // ESCRITA
            while ((linea = br.readLine()) != null) {

                // SEPAREM TOTA LA INFORMACIO QUE ESTA SEPARADA PER GUIONS I ASSIGNEM CADA
                // VALOR A L'ESPAI CORRESPONENT EN UN ARRAY
                String[] taulapunts = linea.split("-");
                String nom = taulapunts[0];
                String pj = taulapunts[1];
                String pg = taulapunts[2];
                String pe = taulapunts[3];
                String pp = taulapunts[4];
                String punts = taulapunts[5];

                // System.out.printf("%20s %5s %5s %5s %5s %5s", nom, pj, pg, pe, pp, punts);
                // System.out.println();

                // PASSEM ELS STRINGS A INTEGERS
                int pj1 = Integer.parseInt(pj);
                int pg1 = Integer.parseInt(pg);
                int pe1 = Integer.parseInt(pe);
                int pp1 = Integer.parseInt(pp);
                int punts1 = Integer.parseInt(punts);

                // ASSIGNACIO DE VALORS EN ELS ARRAYS PRINCIPALS
                nomsequips[i] = nom;

                i++;

                int x = 0;
                dadesequips[x][z] = pj1;
                x++;
                dadesequips[x][z] = pg1;
                x++;
                dadesequips[x][z] = pe1;
                x++;
                dadesequips[x][z] = pp1;
                x++;
                dadesequips[x][z] = punts1;
                z++;

                visualitzarTaula(nomsequips, dadesequips, nom, pj1, pg1, pe1, pp1, punts1);
            }

        } catch (Exception e) {
            System.out.println("Hi ha hagut un problema amb l'aplicació");
            e.printStackTrace();
        }
    }

    // METODE QUE VA IMPRIMINT TOTA LA INFORMACIÓ AMB FORMAT
    static void visualitzarTaula(String[] nomsequips, int[][] dadesequips, String nom1, int pj1, int pg1, int pe1,
            int pp1, int punts1) throws IOException {

        System.out.printf("%20s %5s %5s %5s %5s %5s", nom1, pj1, pg1, pe1, pp1, punts1);
        System.out.println();

    }

    // METODE QUE AFEGEIX UN NOU EQUIP
    static void afegirNouEquip() throws IOException {
        Scanner teclat = new Scanner(System.in);
        boolean sortir = false;
        // PrintWriter pw = null;
        System.out.println();
        System.out.println("***AFEGIR UN NOU EQUIP***");
        System.out.println();

        // BUCLE QUE RECORRE TOT L'ARRAY
        for (int i = 0; i < nomsequips.length; i++) {
            // ENS INTERESSA INTRODUIR UN NOU EQUIP ALLÀ ON ES TROBI UNA POSICIÓ
            // DE L'ARRAY ON NO HI HAGI UN ALTRE EQUIP
            if (nomsequips[i] == null) {
                // INTRODUCCIO DE VALORS PER CONSOLA
                System.out.println("Nom de l'equip: ");
                String nom = teclat.nextLine();
                System.out.println();

                nomsequips[i] = nom;

                System.out.println("Partits guanyats: ");
                int pguanyats = teclat.nextInt();
                teclat.nextLine();
                System.out.println();

                dadesequips[1][i] = pguanyats;

                System.out.println("Partits empatats: ");
                int pempatats = teclat.nextInt();
                teclat.nextLine();
                System.out.println();

                dadesequips[2][i] = pempatats;

                System.out.println("Partits perduts: ");
                int pperduts = teclat.nextInt();
                teclat.nextLine();
                System.out.println();

                dadesequips[3][i] = pperduts;

                int pjugats = pguanyats + pempatats + pperduts;

                dadesequips[0][i] = pjugats;

                // CALCULEM ELS PUNTS TOTALS(2 PER VICTORIA I 1 PER EMPATAT)
                int puntstotals = pguanyats * 2 + pempatats * 1;

                dadesequips[4][i] = puntstotals;
                System.out.println();
                System.out.println("Nou equip afegit: " + nom);
                break;
            }
        }

        escriuArxiu(nomsequips, dadesequips);

    }

    // GRAVAR INFORMACIO DELS ARRAYS A L'ARXIU AMB LA TAULA DE PUNTUACIONS
    static PrintWriter escriuArxiu(String[] nomsequips, int[][] dadesequips) throws IOException {
        // CREEM NOU FITXER
        FileWriter fw = new FileWriter("TaulaPuntuacions.txt", false);
        BufferedWriter bf = new BufferedWriter(fw);
        PrintWriter pw = new PrintWriter(bf);

        // ESCRIVIM LA INFORMACIO
        // BUCLE QUE RECORRE ELS ARRAYS I IMPRIMEIX TOTA LA INFORMACIO ALLÀ ON ES TROBI
        // EL NOM D'UN EQUIP I HO VA SEPARANT AMB GUIONS
        for (int i = 0; i < nomsequips.length; i++) {
            if (nomsequips[i] != null) {
                pw.print(nomsequips[i]);
                pw.print("-" + dadesequips[0][i] + "-" + dadesequips[1][i] + "-" + dadesequips[2][i] + "-"
                        + dadesequips[3][i] + "-" + dadesequips[4][i]);
                pw.println();
            }
        }

        // TANQUEM
        pw.close();

        return pw;
    }

    // METODE QUE MODIFICA UN EQUIP EXISTENT
    static void modificarEquip() throws IOException {
        Scanner teclat = new Scanner(System.in);
        boolean sortir = false;
        PrintWriter pw = null;

        // CAPÇALERA DE MODIFICACIÓ D'EQUIPS
        do {
            System.out.println("\n--------------------------------------------------------");
            // IMPRIMEIX TOTS ELS EQUIPS EN ORDRE PER TAL QUE INTRODUINT UN
            // NUMERO PER CONSOLA L'USUARI PUGUI SELECCIONAR UN EQUIP
            for (int i = 0; i < nomsequips.length; i++) {
                if (nomsequips[i] != null) {
                    System.out.println(i + 1 + ". " + nomsequips[i]);
                }
            }
            System.out.println("0. SORTIR AL MENU PRINCIPAL");
            System.out.println("--------------------------------------------------------");
            System.out.println("\nSELECCIONA UN EQUIP A MODIFICAR");

            // SELECCIÓ D'UN NUMERO QUE CORRESPON A UN EQUIP DINS L'ARRAY
            int opcio = teclat.nextInt();

            if (opcio != 0) {
                if (nomsequips[opcio - 1] != null) {
                    // System.out.println("Nom de l'equip: ");
                    // String nom = teclat.nextLine();
                    // System.out.println();

                    // nomsequips[opcio - 1] = nom;

                    System.out.println();
                    System.out.println("EQUIP A MODIFICAR: " + nomsequips[opcio-1]);
                    System.out.println();

                    System.out.println("Partits guanyats: ");
                    int pguanyats = teclat.nextInt();
                    teclat.nextLine();
                    System.out.println();

                    dadesequips[1][opcio - 1] = pguanyats;

                    System.out.println("Partits empatats: ");
                    int pempatats = teclat.nextInt();
                    teclat.nextLine();
                    System.out.println();

                    dadesequips[2][opcio - 1] = pempatats;

                    System.out.println("Partits perduts: ");
                    int pperduts = teclat.nextInt();
                    teclat.nextLine();
                    System.out.println();

                    dadesequips[3][opcio - 1] = pperduts;

                    int pjugats = pguanyats + pempatats + pperduts;

                    dadesequips[0][opcio - 1] = pjugats;

                    int puntstotals = pguanyats * 2 + pempatats * 1;

                    dadesequips[4][opcio - 1] = puntstotals;

                    pw = escriuArxiu(nomsequips, dadesequips);

                    System.out.println("EQUIP MODIFICAT: " + nomsequips[opcio-1]);
                    break;

                    

                } else {
                    System.out
                            .println("\n" + opcio
                                    + " NO ÉS UNA OPCIÓ NO VÀLIDA \nSELECCIONA UN DELS EQUIPS DISPONIBLES");
                }

            }

            if (opcio == 0) {
                sortir = true;
            }
        } while (!sortir);
    }

    // VISUALITZAR L'EQUIP QUE VA PRIMER I L'EQUIP QUE VA ULTIM EN LA CLASSIFICACIÓ
    static void visualitzarLiderCuer() {
        // INICIALITZEM MAXIM EN LA POSICIO 0
        int maxim = dadesequips[4][0];
        // CONTADOR PER TAL DE TROBAR EL NOM DE L'QUIP AMB LA PUNTUACIO MES ALTA
        int contmax = 0;
        for (int i = 0; i < nomsequips.length; i++) {
            // QUAN ES TROBA UNA PUNTUACIO MES ALTA ES GUARDA EL VALOR EN EL CONTADOR MAXIM
            if (dadesequips[4][i] > maxim) {
                maxim = dadesequips[4][i];
                contmax = i;
            }
        }
        System.out.println();
        System.out.println("EQUIP LÍDER \n" + nomsequips[contmax] + " - " + maxim + " punts");

        // INICIALITZEM MINIM EN LA POSICIO 0
        int minim = dadesequips[4][0];
        // CONTADOR PER TAL DE TROBAR EL NOM DE L'QUIP AMB LA PUNTUACIO MINIMA
        int contmin = 0;
        for (int i = 0; i < nomsequips.length; i++) {
            if (nomsequips[i] != null) {
                // QUAN ES TROBA UNA PUNTUACIO MES BAIXA ES GUARDA EL VALOR EN EL CONTADOR MINIM
                if (dadesequips[4][i] < minim) {
                    minim = dadesequips[4][i];
                    contmin = i;
                }

            }
        }
        System.out.println();
        System.out.println("EQUIP CUER \n" + nomsequips[contmin] + " - " + minim + " punts");
    }

}