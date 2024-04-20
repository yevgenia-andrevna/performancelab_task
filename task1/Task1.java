import java.util.ArrayList;
import java.util.Arrays;

public class
Task1 {
    public static void main(String[] args) {
        // Считывание чисел
        int n =  Integer.parseInt(args[0]);
        int m = Integer.parseInt(args[1]);

        // Поиск пути
        ArrayList<int[]> nestedList = new ArrayList<>();
        int start = 1;
        do {
            int[] tempList = getSequence(start, n, m);
            nestedList.add(tempList);
            start = tempList[m - 1]; }
        while (start != 1);

        // Вывод пути
        int[] path = new int[nestedList.size()];
        for (int i = 0; i < path.length; i++) {
            path[i] = (nestedList.get(i)[0]);
        }
        System.out.println("Полученный путь: " + Arrays.toString(path));
    }

    /** Создать круговую последовательность чисел
     * @param start начальное число
     * @param n длина последовательности от 1 до n
     * @param m длина получаемого массива
     */
    private static int[] getSequence(int start, int n, int m) {
        int[] result = new int[m];
        for (int i = 0; i < m; i++) {
            result[i] = start;
            if (start < n) start++;
            else start = 1;
        }
        return result;
    }
}

