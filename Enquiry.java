import java.util.ArrayList;

public class Enquiry extends Comment {
    ArrayList<String> replies = new ArrayList<>();
    
    public Enquiry(Student student, Camp camp, String comment){
        this.comment = comment;
        this.student = student;
        this.camp = camp;
        this.commentId = Comment.nextEnquiryId;
        Comment.nextEnquiryId += 1;
    }

    public void addReply(String reply){
        replies.add(reply);
        this.processed = true;
    }
    public ArrayList<String> getReplies(){return this.replies;}
    public void printComment(){
        System.out.printf("Enquiry %d:\n", this.commentId);
        super.printComment();
        System.out.printf("Replies (%d)\n", replies.size());
        for(String reply : replies){
            System.out.println(reply);
        }
    }
}
