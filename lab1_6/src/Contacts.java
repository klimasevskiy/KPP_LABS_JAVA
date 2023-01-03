import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Contacts implements Serializable {
    private int ID;
    public String Name;
    public LocalDate Birth;
    public String Number;
    public String City;
    public LocalDateTime Created_date_time;
    private static int count = 0;
    public Contacts() {
        count++;
        ID = count;
    }


    public Contacts(String name, LocalDate birth, String number, String city, LocalDateTime created_date_time) {
        count++;
        ID = count;
        Name = name;
        Birth = birth;
        Number = number;
        City = city;
        Created_date_time = created_date_time;
    }

    public void input() {
        String buf;
        String reg_number = "^\\+380\\d{9}|\\d{10}";
        String reg_birth  = "^\\d{2}\\.\\d{2}\\.\\d{4}";
        String reg_created = "^\\d{2}\\.\\d{2}\\.\\d{4} \\d{2}\\:\\d{2}";
        Pattern pattern = Pattern.compile("", Pattern.CASE_INSENSITIVE);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        DateTimeFormatter formatterWithTime = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
        Scanner input = new Scanner(System. in);
        System.out.print("Введіть ПІП: "); Name = input.nextLine();
        do {
            System.out.print("Введіть дату народження(дд.мм.рррр): ");
            buf = input.nextLine();
            if(Pattern.matches(reg_birth, buf)) {
                break;
            }
            else {
                System.out.println("Не вірний формат! Повторіть спробу!");
            }
        }while (true);
        Birth = LocalDate.parse(buf, formatter);

        do {
            System.out.print("Введіть номер: ");
            buf = input.nextLine();
            if(Pattern.matches(reg_number, buf)) {
                break;
            }
            else {
                System.out.println("Не вірний формат! Повторіть спробу!");
            }
        }while (true);
        Number = buf;

        System.out.print("Введіть адресу: "); City = input.nextLine();

        do {
            System.out.print("Введіть дату останнього редагування(дд.мм.рррр гг:хх): ");
            buf = input.nextLine();
            if(Pattern.matches(reg_created, buf)) {
                break;
            }
            else {
                System.out.println("Не вірний формат! Повторіть спробу!");
            }
        }while (true);
        Created_date_time = LocalDateTime.parse(buf, formatterWithTime);
    }
    public int getID() {
        return ID;
    }
}
