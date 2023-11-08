import java.io.Serializable;

public abstract class Comment implements Serializable{
    int commentId;
    static int nextSuggestionId = 1;
    static int nextEnquiryId = 1;
    Student student;
    Camp camp;
    String comment;
    Boolean processed = false;

    public void editComment(String comment){this.comment = comment;}
    public boolean getProcessed(){return this.processed;}
    public String stringProcessed(){
        if(this.processed){return "Processed";}
        else{return "Waiting for process";}
    }
    public void printComment(){
        CampInformation campInformation = camp.getCampInformation();
        System.out.printf("Created by: %s\tCamp: %s\tStatus: %s\nText: %s\n", student.getName(), campInformation.getName(), this.stringProcessed(), this.comment);
    }

    public Student getStudent(){return this.student;}
}
