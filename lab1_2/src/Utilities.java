import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Utilities {

    public static List<Contacts>  Read_File()
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        DateTimeFormatter formatterWithTime = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
        formatter = formatter.withLocale(Locale.ENGLISH);

        List<Contacts> contacts = new ArrayList<>();
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
    public static void PrintTable(List<Contacts> contacts)
    {
        PrintLine();
        PrintRow(new ArrayList<String>(Arrays.asList("ПІП", "Дата народження", "Телефони", "Адреса", "Дата і час редагування")));
        PrintLine();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        DateTimeFormatter formatterWithTime = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
        for (Contacts g : contacts){
            List<String> list = new ArrayList<>();
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
    public static void  Sort_by(List<Contacts> contacts, int sort_by)
    {
        switch (sort_by) {
            case 1 -> contacts.sort(Comparator.comparing(x -> x.Name));
            case 2 -> contacts.sort(Comparator.comparing(x -> x.Birth));
            case 3 -> contacts.sort(Comparator.comparing(x -> x.Created_date_time));
            default -> {
            }
        }

        PrintTable(contacts);
    }
}