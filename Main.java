import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(Methods.getFilename()));
        ArrayList<String> picnic = new ArrayList<>(List.of(scanner.nextLine().strip().split(" ")));
        System.out.println("Всего на пикник взяли " + picnic.size() + " продукта(ов)\n");
        System.out.println("Продукты с самыми длинными названиями:");
        ArrayList<String> maxLengthWords = Methods.maxLengthWord(picnic);
        for (String string : maxLengthWords) {
            System.out.println(Methods.printSting(string));
        }
        TreeMap<String, Integer> sort = new TreeMap<>(Methods.countProducts(picnic));
        System.out.println("\nВ корзине для пикника сейчас:");
        Methods.sortedTreeMapByValue(sort, maxLengthWords.getFirst().length());

    }


}

