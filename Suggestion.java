public class Suggestion extends Comment{
    public Suggestion(Student student, Camp camp, String comment){
        this.student = student;
        this.camp = camp;
        this.comment = comment;
        this.commentId = Comment.nextSuggestionId;
        Comment.nextSuggestionId += 1;
    }

    public void setProcessed(Boolean value){this.processed = value;}

    public void printComment(){
        System.out.printf("Suggestion %d:\n", this.commentId);
        super.printComment();
    }
}
