import java.io.Serializable;
import java.util.*;

public class Committee  implements Serializable{
    private Camp camp;
    private Student student;
    private int points = 0;
    
    public Committee(Camp camp, Student student){
        this.camp = camp;
        this.student = student;
    }
    public String viewCampDetial(){return this.camp.getCampDetail();}
    public void createSuggestion(String comment){
        Suggestion suggestion = new Suggestion(this.student, this.camp, comment);
        this.camp.addComment(suggestion);
    }
    public void viewEnquiry(){
        ArrayList<Comment> enquiries = camp.getEnquiry();
        for(Comment comment : enquiries){
            int i = 1;
            System.out.printf("%d ->\t", i);
            comment.printComment();
            i++;
        }
    }

    public void replyEnquiry(Enquiry enquiry){
        if(enquiry != null){
            System.out.println("Enter your reply: ");
            String reply = MainProgram.sc.nextLine();
            enquiry.addReply(reply);
            System.out.printf("Enquiry replied\nYou have been awarded one point\nYour points: %d",getPoints());
        }
    }
    public void addPoint(){this.points++;}
    public int getPoints(){return this.points;}
    public Camp getCamp(){return this.camp;}
    public Student getStudent(){return this.student;}
}
