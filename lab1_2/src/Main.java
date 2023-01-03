import java.util.List;
import java.util.Scanner;

public class Main {
    static Scanner input = new Scanner(System. in);
    public static void main(String[] args) {

        List<Contacts> contacts;
        contacts = Utilities.Read_File();
        Utilities.PrintTable(contacts);
        boolean exit = false;
        do
        {
            int action;
            System.out.println("  Сортувати за : ");
            System.out.println("  [1] - ПІП");
            System.out.println("  [2] - датою народження");
            System.out.println("  [3] - датою і часом редагування");
            System.out.println("  [0] - Завершити роботу");
            System.out.print("  Виберіть дію: "); action = input.nextInt();
            switch (action) {
                case 1 -> {
                    System.out.print("\n Відсортовано за ПІП.");
                    Utilities.Sort_by(contacts,1);
                }
                case 2 -> {
                    System.out.print("\n Відсортовано за датою народження.");
                    Utilities.Sort_by(contacts,2);
                }
                case 3 -> {
                    System.out.print("\n Відсортовано за датою і часом редагування.");
                    Utilities.Sort_by(contacts,3);
                }
                case 0 -> exit = true;
                default -> System.out.println("\nНевідома дія!");
            }

        } while (!exit);
    }
}