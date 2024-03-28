import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        String fileName = "input.txt";
        Scanner scanner = new Scanner(new File(fileName));
        ArrayList<String> picnic = new ArrayList<>(List.of(scanner.nextLine().strip().split(" ")));
        System.out.println("Всего на пикник взяли " + picnic.size() + " продукта(ов)\n");
        System.out.println("Продукты с самыми длинными названиями:");
        ArrayList<String> maxLengthWords = maxLengthWord(picnic);
        for (String string : maxLengthWords) {
            System.out.println(capitalized(string)
                    + " - "
                    + string.length()
                    + " cимволов");
        }

        TreeMap<String, Integer> sort = new TreeMap<>(countProducts(picnic));
        System.out.println("\nВ корзине для пикника сейчас:");
        sortedTreeMapByValue(sort, maxLengthWords.getFirst().length());

    }

    public static ArrayList<String> maxLengthWord(ArrayList<String> picnic) {
        picnic.sort(Comparator.comparingInt(String::length));
        ArrayList<String> maxLengthSizeWords = new ArrayList<>();
        int maxSizeWord = picnic.getLast().length();
        for (int i = picnic.size() - 1; i > 0; i--) {
            if (picnic.get(i).length() == maxSizeWord && !maxLengthSizeWords.contains(picnic.get(i).toLowerCase())) {
                maxLengthSizeWords.add(picnic.get(i).toLowerCase());
            } else break;
        }
        return maxLengthSizeWords;
    }

    public static HashMap<String, Integer> countProducts(ArrayList<String> picnic) {
        HashMap<String, Integer> countProduct = new HashMap<>();
        for (String product : picnic) {
            if (!countProduct.containsKey(product.toLowerCase())) {
                countProduct.put(product.toLowerCase(), 1);
            } else {
                int count = (Integer) countProduct.get(product.toLowerCase()) + 1;
                countProduct.put(product.toLowerCase(), count);
            }
        }
        return countProduct;
    }

    public static String capitalized(String string) {
        return string.substring(0, 1).toUpperCase() + string.substring(1);
    }

    public static void sortedTreeMapByValue(TreeMap<String, Integer> treeMap, int maxLengthWords) {
        TreeSet<Integer> values = new TreeSet<>(Comparator.reverseOrder());
        values.addAll(treeMap.values());
        Set<String> keySet = treeMap.keySet();
        for (Integer i : values) {
            for (String key : keySet) {
                if (Objects.equals(treeMap.get(key), i)) {
                    System.out.println(capitalized(key)
                            + " ".repeat(maxLengthWords - key.length())
                            + " - " + i
                            + " шт.");
                }
            }
        }

    }
}

