import java.util.Arrays;
import java.util.Random;

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

        System.out.println("Wylosowano gamerki: " + Arrays.toString(gamerki)+" pkt: "+countPoints("gamerki", allGroups, allPoints));
        System.out.println("Wylosowano heroski: " + Arrays.toString(heroski)+" pkt: "+countPoints("heroski", allGroups, allPoints));
        System.out.println("Wylosowano żony Hollywood: " + Arrays.toString(zonyHollywood)+" pkt: "+countPoints("zonyHollywood", allGroups, allPoints));
        System.out.println("Wylosowano kosmonautki: " + Arrays.toString(kosmonautki)+" pkt: "+countPoints("kosmonautki", allGroups, allPoints));
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
            if (allGroups[i]!=null && allGroups[i].equals(groupName)) {
                points += allPoints[i];
            }
        }
        return points;
    }

    //suma puntów za zapisane dotychczas uczestniczki
    public static String[] collectGroup(String groupName, String[] allGroups, String[] allGirls) {
        String[] group = new String[5];
        int position=0;
        for (int i = 0; i < NUMBER_OF_GIRLS; i++) {
            if (allGroups[i]!=null && allGroups[i].equals(groupName)) {
                group[position]= allGirls[i];
                position++;
            }
        }
        return group;
    }
}
