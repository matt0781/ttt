import java.io.Serializable;
import java.util.*;

public class Attendee implements Serializable{
    Student student;
    ArrayList<Camp> camp = new ArrayList<>();

    public Attendee(Student student){
        this.student = student;
    }
    public void registerCamp(Camp camp){
        this.camp.add(camp);
        Collections.sort(this.camp);
    }

    public void withdrawCamp(Camp camp){
        try{
            this.camp.remove(camp);
        }
        catch(Exception e){
            System.out.println("Camp not in attendee list");
        }
    }

    public ArrayList<Camp> getCamps(){return this.camp;}
}
