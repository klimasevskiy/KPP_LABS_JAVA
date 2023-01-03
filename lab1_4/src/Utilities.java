import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Utilities {
    public static Container<Contacts>  Read_File()
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        DateTimeFormatter formatterWithTime = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
        formatter = formatter.withLocale(Locale.ENGLISH);

        Container<Contacts> contacts = new Container<>();
        try {
            File myObj = new File("contacts.txt");
            Scanner myReader = new Scanner(myObj);

            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] entries = data.split(",");

                Contacts contact = new Contacts();
                contact.Name = entries[0];
                contact.Birth = LocalDate.parse(entries[1], formatter);
                contact.Number = entries[2];
                contact.City = entries[3];
                contact.Created_date_time = LocalDateTime.parse(entries[4], formatterWithTime);


                contacts.add(contact);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return contacts;
    }
    static int tableWidth = 180;
    public static<T extends Contacts> void PrintTable(Container<T> contacts)
    {
        PrintLine();
        PrintRow(new ArrayList<String>(Arrays.asList("ID","ПІП", "Дата народження", "Телефони", "Адреса", "Дата і час редагування")));
        PrintLine();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        DateTimeFormatter formatterWithTime = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
        for (Contacts g : contacts){
            List<String> list = new ArrayList<>();
            list.add(String.valueOf(g.getID()));
            list.add(g.Name);
            list.add(g.Birth.format(formatter));
            list.add(g.Number);
            list.add(g.City);
            list.add(g.Created_date_time.format(formatterWithTime));

            PrintRow(list);

            PrintLine();
        }
        System.out.println();
    }
    public static void PrintLine()
    {
        System.out.println(new String(new char[tableWidth]).replace("\0", "-"));
    }
    public static String centerString (int width, String s) {
        return String.format("%-" + width  + "s", String.format("%" + (s.length() + (width - s.length()) / 2) + "s", s));
    }
    public static void serialize(Container<Contacts> contacts, String filename) {
        try (FileOutputStream fos = new FileOutputStream(filename);
             ObjectOutputStream oos = new ObjectOutputStream(fos);) {
            oos.writeObject(contacts);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public static Container<Contacts> deserialize(String filename) {
        Container<Contacts> contacts = new Container<>();

        try (FileInputStream fis = new FileInputStream(filename);
             ObjectInputStream ois = new ObjectInputStream(fis)) {

            contacts = (Container<Contacts>) ois.readObject();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (ClassNotFoundException c) {
            System.out.println("Class not found");
            c.printStackTrace();
        }
        return contacts;
    }
    public static void PrintRow(List<String> columns)
    {
        int width = (tableWidth - columns.size()) / columns.size();

        StringBuilder row = new StringBuilder("|");
        for (String column : columns)
        {
            row.append(centerString(width, column)).append("|");

        }
        System.out.print(row);
        System.out.println();

    }
    public static<T extends Contacts> void Sort_by(Container<T> contacts, int sort_by)
    {
        switch (sort_by) {
            case 1 -> contacts.sort(Comparator.comparing(x -> x.Name));
            case 2 -> contacts.sort(Comparator.comparing(x -> x.Birth));
            case 3 -> contacts.sort(Comparator.comparing(x -> x.Created_date_time));
            default -> {
            }
        }

    }
}