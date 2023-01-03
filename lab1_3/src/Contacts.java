import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

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
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        DateTimeFormatter formatterWithTime = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
        Scanner input = new Scanner(System. in);
        System.out.print("Введіть ПІП: "); Name = input.nextLine();
        System.out.print("Введіть дату народження(дд.мм.рррр): "); Birth = LocalDate.parse(input.nextLine(), formatter);
        System.out.print("Введіть номери: "); Number = input.nextLine();
        System.out.print("Введіть адресу: "); City = input.nextLine();
        System.out.print("Введіть дату останнього редагування(дд.мм.рррр гг:хх): "); Created_date_time = LocalDateTime.parse(input.nextLine(), formatterWithTime);
    }
    public int getID() {
        return ID;
    }
}
