import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Comparator;

public class Main {
    static Scanner input = new Scanner(System. in);
    static int tableWidth = 60;
    public static void PrintTable(List<Toys> toys)
    {
        String[] header = {"Назва", "Ціна", "Вікові межі", "Характристики"};

        PrintLine();
        PrintRow(header);
        PrintLine();

        for (Toys t : toys){
            String[] str = {t.getName(), String.valueOf(t.getPrice()),"Від"+ t.getAge_From()+" до "+t.getAge_To(), t.getCharacteristic()};
            PrintRow(str);

            PrintLine();
        }

    }
    public static void PrintLine()
    {
        System.out.println(new String(new char[tableWidth]).replace("\0", "-"));
    }
    public static String centerString (int width, String s) {
        return String.format("%-" + width  + "s", String.format("%" + (s.length() + (width - s.length()) / 2) + "s", s));
    }
    public static void PrintRow(String[] columns)
    {
        int width = (tableWidth - columns.length) / columns.length;

        StringBuilder row = new StringBuilder("|");
        for (String column : columns)
        {
            row.append(centerString(width, column)).append("|");

        }
        System.out.print(row);
        System.out.println();


    }
    public static List<Toys> Filter(List<Toys> toys) {
        int maxPrice = toys.stream().max(Comparator.comparingInt(Toys::getPrice)).get().Price;
        List<Toys> filteredToys = new ArrayList<>();
        for (Toys toy:toys) {
            if(Math.abs(toy.Price - maxPrice) <= 10) {
                filteredToys.add(toy);
            }
        }
        filteredToys.sort((o1, o2) -> o2.Price - o1.Price );
        return filteredToys;
    }

    public static void main(String[] args) {

        List<Toys> toys = new ArrayList<>();

        try {
            File myObj = new File("toys.txt");
            Scanner myReader = new Scanner(myObj);

            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] entries = data.split(",");
                Toys newToy = new Toys();
                newToy.setName(entries[0]);
                newToy.setPrice(Integer.parseInt(entries[1]));
                newToy.setAge_From(Integer.parseInt(entries[2]));
                newToy.setAge_To(Integer.parseInt(entries[3]));
                newToy.setCharacteristic(entries[4]);
                toys.add(newToy);
            }
            myReader.close();

        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        PrintTable(toys);

        boolean exit = false;
        do
        {
            int action;
            System.out.println("  [1] - Найбільш коштовні іграшки.");
            System.out.println("  [2] - Вивести всі іграшки.");
            System.out.println("  [0] - Завершити роботу.");
            System.out.print("  Виберіть дію: "); action = input.nextInt();
            switch (action) {
                case 1 -> {
                    List<Toys> filteredToys = Filter(toys);
                    PrintTable(filteredToys);
                }
                case 2 -> {
                    PrintTable(toys);
                }
                case 0 -> exit = true;
                default -> System.out.println("\nНевідома дія!");
            }

        } while (!exit);

    }
}