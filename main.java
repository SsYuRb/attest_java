package CR_Java;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.Scanner;
import java.io.File;
import CR_Java.base_toy;

public class main {
    static ArrayList<base_toy> toyses = new ArrayList<>();
    static Scanner user_input = new Scanner(System.in);
    static Random random = new Random();
    static Queue<base_toy> queue = new LinkedList<>(); 

    public static void main(String[] args) {
        System.out.println("Создай игрушку");
        for (int i = 0; i < 5; i++) {
            base_toy toy = toy_maker();
            toyses.add(toy);
        }
        lottery(toyses);
        take_prize(queue);
    }

    public static base_toy toy_maker() {
        System.out.println("Введите id игрушки ");
        int id = user_input.nextInt();
        user_input.nextLine();
        System.out.println("Введите название игрушки ");
        String name = user_input.nextLine();
        System.out.println("Введите количество ");
        int count = user_input.nextInt();
        System.out.println("Введите частоту ");
        int frequency = user_input.nextInt();
        base_toy toy = new base_toy(id, name, count, frequency); 
        return toy;
    }

    public static void lottery(ArrayList<base_toy> toyses) {
        int winner = random.nextInt(toyses.size());
        base_toy prize = toyses.get(winner);
        prize.takeWin();
        queue.add(prize);
    }

    public static void take_prize(Queue<base_toy> queue) {
        base_toy toy = queue.remove();
        try {
            PrintWriter writer = new PrintWriter(new File("CR_Java/output.txt"));
            writer.write(toy.getName());
            writer.write(toy.getCount());
            writer.close();
            System.out.println("Записали приз");
        } catch (FileNotFoundException e) {
            System.out.println("Ошибка");
            e.printStackTrace();
        }
    }
}