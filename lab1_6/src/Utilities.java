import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.regex.Pattern;

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
    public static<T extends Contacts> void PrintTable(List<T> contacts)
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
    public static void serialize(List<Contacts> contacts, String filename) {
        try (FileOutputStream fos = new FileOutputStream(filename);
             ObjectOutputStream oos = new ObjectOutputStream(fos);) {
            oos.writeObject(contacts);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public static List<Contacts> deserialize(String filename) {
        List<Contacts> contacts = new ArrayList<>();

        try (FileInputStream fis = new FileInputStream(filename);
             ObjectInputStream ois = new ObjectInputStream(fis)) {

            contacts = (List<Contacts>) ois.readObject();
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
    public static<T extends Contacts> void Sort_by(List<T> contacts, int sort_by)
    {
        switch (sort_by) {
            case 1 -> contacts.sort(Comparator.comparing(x -> x.Name));
            case 2 -> contacts.sort(Comparator.comparing(x -> x.Birth));
            case 3 -> contacts.sort(Comparator.comparing(x -> x.Created_date_time));
            default -> {
            }
        }

    }

    public static List<Contacts> Search(List<Contacts> pf1)
    {
        DateTimeFormatter  formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        List<Contacts> pf2 = new ArrayList<>();
        String life_number = "^\\+38063|\\+38093\\d{7}";
        String kyivstar_number = "^\\+38039\\d{7}|^\\+38067\\d{7}|^\\+38068\\d{7}|^\\+38096\\d{7}|^\\+38097\\d{7}|^\\+38098\\d{7}";
        for (Contacts p : pf1)
        {
            if(Pattern.matches(life_number, p.Number) || Pattern.matches(kyivstar_number, p.Number) && p.Name.equals("Львів")) {
                pf2.add(p);
            }

        }

        return pf2;
    }
}