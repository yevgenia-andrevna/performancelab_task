import java.io.File;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Scanner;

public class Task2 {
    public static void main(String[] args) throws java.io.FileNotFoundException{
        // Считывание данных об окружности
        File f_circle = new File(args[0]);
        Scanner scan_circle = new Scanner(f_circle);
        Circle circle = new Circle
                (scan_circle.nextBigInteger(), scan_circle.nextBigInteger(), scan_circle.nextBigInteger());
        scan_circle.close();

        // Считывание координат
        File f_coordinates = new File(args[1]);
        Scanner scan_coordinates = new Scanner(f_coordinates);
        ArrayList<BigInteger[]> coordinates = new ArrayList<>();
        while (scan_coordinates.hasNext()) {
            BigInteger[] point = new BigInteger[2];
            point[0] = scan_coordinates.nextBigInteger();
            point[1] = scan_coordinates.nextBigInteger();
            coordinates.add(point);
        }
        scan_coordinates.close();

        // Проверка координат
        for (BigInteger[] point : coordinates) {
            System.out.println(circle.checkPointLocation(point));
        }
    }
}

class Circle {
    BigInteger x;
    BigInteger y;
    BigInteger radius;

    Circle(BigInteger x, BigInteger y, BigInteger radius) {
        this.x = x;
        this.y = y;
        this.radius = radius;
    }
    public int checkPointLocation(BigInteger[] point) {
        BigInteger distance = getDistanceFromPointToCenter(point);
        if (distance.compareTo(this.radius) == 0) return 0;
        else if (distance.compareTo(this.radius) == -1) return 1;
        else return 2;
    }
    private BigInteger getDistanceFromPointToCenter(BigInteger[] point) {
        BigInteger a = point[0].subtract(this.x);
        BigInteger b = point[1].subtract(this.y);
        return a.pow(2).add(b.pow(2)).sqrt();
    }
}