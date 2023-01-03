
import java.util.Scanner;

public class Main {
    static Scanner input = new Scanner(System. in);
    public static void main(String[] args) {

        Container<Contacts> contacts;
        contacts = Utilities.Read_File();
        boolean exit = false;
        do
        {
            Utilities.PrintTable(contacts);
            int action;
            System.out.println("  [1] - Сортувати за ПІП");
            System.out.println("  [2] - Сортувати за датою народження");
            System.out.println("  [3] - Сортувати за датою і часом останньої зміни");
            System.out.println("  [4] - Серілізувати");
            System.out.println("  [5] - Десерілізувати");
            System.out.println("  [6] - Додати");
            System.out.println("  [7] - Видалити");
            System.out.println("  [8] - Змінити");
            System.out.println("  [0] - Завершити роботу");
            System.out.print("  Виберіть дію: "); action = input.nextInt();
            switch (action) {
                case 1 -> {
                    System.out.println("\n Відсортовано за ПІП");
                    Utilities.Sort_by(contacts, 1);
                }
                case 2 -> {
                    System.out.println("\n Відсортовано за датою народження");
                    Utilities.Sort_by(contacts,2);
                }
                case 3 -> {
                    System.out.println("\n Відсортовано за датою і часом останньої зміни\"");
                    Utilities.Sort_by(contacts,3);
                }
                case 4 -> {
                    String filename;
                    System.out.print("\n Введіть назву файла: "); filename = input.next();
                    Utilities.serialize(contacts, filename);
                }
                case 5 -> {
                    String filename;
                    System.out.print("\n Введіть назву файла: "); filename = input.next();
                    contacts = Utilities.deserialize(filename);
                }
                case 6 -> {
                    Contacts contact = new Contacts();
                    contact.input();
                    contacts.add(contact);

                }
                case 7 -> {
                    int id;
                    System.out.print("\n Введіть id: "); id = input.nextInt();
                    for(int i = 0; i < contacts.size(); i++) {
                        if(contacts.get(i).getID() == id) {
                            contacts.remove(contacts.get(i));
                            break;
                        }
                    }
                }
                case 8 -> {
                    int id;
                    System.out.print("\n Введіть id: "); id = input.nextInt();
                    for (Contacts contact : contacts) {
                        if (contact.getID() == id) {
                            contact.input();
                            break;
                        }
                    }
                }
                case 0 -> exit = true;
                default -> System.out.println("\nНевідома дія!");
            }

        } while (!exit);
    }
}