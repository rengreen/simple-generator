import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Main {
    private static final int STARTERKA_POINTS = 5;
    private static final int JUNIORKA_POINTS = 6;
    private static final int SZEREGOWA_POINTS = 7;
    private static final int KOMANDOSKA_POINTS = 8;
    private static final int NUMBER_OF_GIRLS = 20;

    public static void main(String[] args) {

        //osoby z podziałem na kategorie
        String[] starterki = new String[]{"starterka1", "starterka2", "starterka3", "starterka4", "starterka5"};
        String[] juniorki = new String[]{"juniorka1", "juniorka2", "juniorka3", "juniorka4", "juniorka5"};
        String[] szeregowe = new String[]{"szeregowa1", "szeregowa2", "szeregowa3", "szeregowa4", "szeregowa5", "szeregowa6", "szeregowa7"};
        String[] komandoski = new String[]{"komandoska1", "komandoska2", "komandoska3"};

        System.out.println("starterki " + Arrays.toString(starterki));
        System.out.println("juniorki " + Arrays.toString(juniorki));
        System.out.println("szeregowe " + Arrays.toString(szeregowe));
        System.out.println("komandoski " + Arrays.toString(komandoski));

        Scanner scanner = new Scanner(System.in);
        System.out.println("Czy chcesz usunąć(u) lub dodać(d) osobę?");
        System.out.println("lub wciśnij enter, aby przejść dalej.");

        String answer = scanner.nextLine();
        if (answer.equals("u")) {
            System.out.println("Wybierz kategorię: komandoska(k), szeregowa(z), juniorka(j), starterka(s)");
            System.out.println("lub wciśnij enter, aby zrezygnować.");
            String answerDelete = scanner.nextLine();

            switch (answerDelete) {
                case "k": //komandoski
                    System.out.println("Wybierz osobę: ");
                    for (int i = 0; i < komandoski.length; i++) {
                        System.out.println(komandoski[i] + " (" + (i + 1) + ")");
                    }
                    String answerPerson = scanner.nextLine();
                    int personNumber = Integer.parseInt(answerPerson) - 1;
                    if (personNumber >= 0 && personNumber < komandoski.length) {
                        komandoski = deleteOneGirl(komandoski, personNumber);
                        System.out.println("po usunięciu: komandoski " + Arrays.toString(komandoski));
                    }
                    break;
                case "z": //szeregowe
                    System.out.println("Wybierz osobę: ");
                    for (int i = 0; i < szeregowe.length; i++) {
                        System.out.println(szeregowe[i] + "(" + (i + 1) + ")");
                    }
                    answerPerson = scanner.nextLine();
                    personNumber = Integer.parseInt(answerPerson) - 1;
                    if (personNumber >= 0 && personNumber < szeregowe.length) {
                        szeregowe=deleteOneGirl(szeregowe, personNumber);
                        System.out.println("po usunięciu: szeregowe " + Arrays.toString(szeregowe));
                    }
                    break;
                case "j": //juniorki
                    System.out.println("Wybierz osobę: ");
                    for (int i = 0; i < juniorki.length; i++) {
                        System.out.println(juniorki[i] + "(" + (i + 1) + ")");
                    }
                    answerPerson = scanner.nextLine();
                    personNumber = Integer.parseInt(answerPerson) - 1;
                    if (personNumber >= 0 && personNumber < juniorki.length) {
                        juniorki=deleteOneGirl(juniorki, personNumber);
                        System.out.println("po usunięciu: juniorki " + Arrays.toString(juniorki));
                    }
                    break;
                case "s": //starterki
                    System.out.println("Wybierz osobę: ");
                    for (int i = 0; i < starterki.length; i++) {
                        System.out.println(starterki[i] + "(" + (i + 1) + ")");
                    }
                    answerPerson = scanner.nextLine();
                    personNumber = Integer.parseInt(answerPerson) - 1;
                    if (personNumber >= 0 && personNumber < starterki.length) {
                        starterki=deleteOneGirl(starterki, personNumber);
                        System.out.println("po usunięciu: starterki " + Arrays.toString(starterki));
                    }
                    break;

                default:
                    throw new IllegalStateException("Nieprawidłowa odpowiedź: " + answerDelete);
            }
        } else if (answer.equals("d")) {
            System.out.println("Wybierz kategorię: komandoska(k), szeregowa(z), juniorka(j), starterka(s)");
            System.out.println("Wciśnij enter, aby zrezygnować.");
            String answerAdd = scanner.nextLine();
        }


        //pomieszanie osób
        String[] starterkiMix = shuffleArray(starterki);
        String[] juniorkiMix = shuffleArray(juniorki);
        String[] szeregoweMix = shuffleArray(szeregowe);
        String[] komandoskiMix = shuffleArray(komandoski);

        //zapisanie wszystkich osób do listy allGirls oraz wszystkich punktów do listy allPoints
        //w tej samej kolejności (od największdej liczby punktów i z zachowaniem wcześniejszego mieszania)
        String[] allGirls = new String[NUMBER_OF_GIRLS];
        int[] allPoints = new int[NUMBER_OF_GIRLS];

        int position = 0;

        for (int i = 0; i < komandoski.length; i++) {
            allGirls[position] = komandoskiMix[i];
            allPoints[position] = KOMANDOSKA_POINTS;
            position++;
        }

        for (int i = 0; i < szeregowe.length; i++) {
            allGirls[position] = szeregoweMix[i];
            allPoints[position] = SZEREGOWA_POINTS;
            position++;
        }

        for (int i = 0; i < juniorki.length; i++) {
            allGirls[position] = juniorkiMix[i];
            allPoints[position] = JUNIORKA_POINTS;
            position++;
        }

        for (int i = 0; i < starterki.length; i++) {
            allGirls[position] = starterkiMix[i];
            allPoints[position] = STARTERKA_POINTS;
            position++;
        }

        //pusta lista pomocnicza grup
        String[] allGroups = new String[NUMBER_OF_GIRLS];

        //wyjściowa kolejność grup
        String[] groupOrder = new String[]{"gamerki", "heroski", "kosmonautki", "zonyHollywood"};

        position = 0;

        for (int i = 0; i < 5; i++) {
            //pomieszanie kolejności grup
            groupOrder = shuffleArray(groupOrder);

            //posortowanie grup wg liczby punktow (od najmniejszej)
            groupOrder = sortArray(groupOrder, allGroups, allPoints);

            //wpisanie na listę pomocniczą 4 grup wg kolejności
            for (String groupName : groupOrder) {
                allGroups[position] = groupName;
                position++;
            }
        }

        //gotowe grupy
        String[] gamerki = collectGroup("gamerki", allGroups, allGirls);
        String[] heroski = collectGroup("heroski", allGroups, allGirls);
        String[] zonyHollywood = collectGroup("zonyHollywood", allGroups, allGirls);
        String[] kosmonautki = collectGroup("kosmonautki", allGroups, allGirls);

        System.out.println("Wylosowano gamerki: " + Arrays.toString(gamerki) + " pkt: " + countPoints("gamerki", allGroups, allPoints));
        System.out.println("Wylosowano heroski: " + Arrays.toString(heroski) + " pkt: " + countPoints("heroski", allGroups, allPoints));
        System.out.println("Wylosowano żony Hollywood: " + Arrays.toString(zonyHollywood) + " pkt: " + countPoints("zonyHollywood", allGroups, allPoints));
        System.out.println("Wylosowano kosmonautki: " + Arrays.toString(kosmonautki) + " pkt: " + countPoints("kosmonautki", allGroups, allPoints));
    }

    //losowe pomieszanie tablicy
    public static String[] shuffleArray(String[] array) {
        Random random = new Random();

        for (int i = 0; i < array.length; i++) {
            int randomPosition = random.nextInt(array.length);
            String temp = array[i];
            array[i] = array[randomPosition];
            array[randomPosition] = temp;
        }
        return array;
    }

    //posortowanie grup według punktów za zapisane dotychczas uczestniczki (sortowanie rosnące)
    public static String[] sortArray(String[] groupOrder, String[] allGroups, int[] allPoints) {
        for (int i = 0; i < groupOrder.length - 1; i++) {
            for (int j = 0; j < groupOrder.length - i - 1; j++) {
                int jScore = countPoints(groupOrder[j], allGroups, allPoints);
                int jPlusScore = countPoints(groupOrder[j + 1], allGroups, allPoints);

                if (jScore > jPlusScore) {
                    String temp = groupOrder[j];
                    groupOrder[j] = groupOrder[j + 1];
                    groupOrder[j + 1] = temp;
                }
            }
        }
        return groupOrder;
    }

    //suma puntów za zapisane dotychczas uczestniczki
    public static int countPoints(String groupName, String[] allGroups, int[] allPoints) {
        int points = 0;
        for (int i = 0; i < NUMBER_OF_GIRLS; i++) {
            if (allGroups[i] != null && allGroups[i].equals(groupName)) {
                points += allPoints[i];
            }
        }
        return points;
    }

    //suma puntów za zapisane dotychczas uczestniczki
    public static String[] collectGroup(String groupName, String[] allGroups, String[] allGirls) {
        String[] group = new String[5];
        int position = 0;
        for (int i = 0; i < NUMBER_OF_GIRLS; i++) {
            if (allGroups[i] != null && allGroups[i].equals(groupName)) {
                group[position] = allGirls[i];
                position++;
            }
        }
        return group;
    }

    public static String[] deleteOneGirl(String[] categoryList, int position) {
        int arrayLength = categoryList.length;
        String[] newCategoryList = new String[arrayLength - 1];

        for (int i = 0; i < arrayLength; i++) {
            if (i < position) {
                newCategoryList[i] = categoryList[i];
            } else if (i > position) {
                newCategoryList[i - 1] = categoryList[i];
            }
        }

        return newCategoryList;
    }
}
