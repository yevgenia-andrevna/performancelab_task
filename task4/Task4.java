import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Task4 {
    public static void main(String[] args) {
        // Чтение из файла
        ArrayList<Integer> nums = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(new File(args[0]));
            while (scanner.hasNext()) nums.add(scanner.nextInt());
        } catch (FileNotFoundException fne) { fne.printStackTrace(); }

        // Поиск медианного числа
        Collections.sort(nums);
        int target;
        if (nums.size() % 2 == 1) target = nums.get(nums.size() / 2);
        else target = (int) Math.round((nums.get(nums.size() / 2) + nums.get(nums.size() / 2 - 1)) / 2.0);

        // Поиск количества шагов
        int steps = 0;
        for (int i = 0; i < nums.size(); i++)
            steps += Math.abs(nums.get(i) - target);
        System.out.println(steps);
    }
}
