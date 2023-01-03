import java.time.LocalDate;
import java.time.LocalDateTime;
public class Contacts{
    public String Name;
    public LocalDate Birth;
    public String Number;
    public String City;
    public LocalDateTime Created_date_time;

    public Contacts() {
    }

    public Contacts(String name, LocalDate birth, String number, String city, LocalDateTime created_date_time) {
        Name = name;
        Birth = birth;
        Number = number;
        City = city;
        Created_date_time = created_date_time;
    }
}
