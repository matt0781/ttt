import java.util.*;

public class SuggestionMenu {
    public static void suggestionMenu(User user, ArrayList<Camp> campArrayList){
        Camp targetCamp = CampMenu.selectCamp(campArrayList);
        selectSuggestion(targetCamp);
    }

    private static void selectSuggestion(Camp camp){
        if(camp == null){return;}
        ArrayList<Comment> suggArrayList = camp.getSuggestion();
        if(suggArrayList.isEmpty()){
            System.out.println("No available suggestions! Choose another camp...");
            return;
        }
        int i = 1;
        System.out.println("Select Suggestion:");
        for(Comment comment : suggArrayList){
            System.out.printf("%d ->\t", i);
            comment.printComment();
            i += 1;
        }
        int suggestionChoice = MainProgram.sc.nextInt();
        Suggestion chosenSugg = (Suggestion)suggArrayList.get(suggestionChoice-1);
            if (chosenSugg.getProcessed()==true){
                System.out.println("Sorry, your suggestion has been processed");
                return;
            }
            else{
                do{
                System.out.println("(1)\tEdit suggestion\n(2)\tDelete suggestion\n(3)\tExit\nChoice: ");
                int editChoice = MainProgram.sc.nextInt();
                switch(editChoice){
                    case 1:
                        editSuggestion(chosenSugg); break;
                    case 2:
                        deleteSuggestion(camp,chosenSugg); break;
                    case 3: return;
                    default:
                        System.out.println("Please enter a valid input");
                }
            }while(true);
            }
    }
    public static void addSuggestion(AllUser allUser, AllCamp allCamp, Committee committee){
        ArrayList<Camp> availableCamps = FilterCamp.getAvailableCamps(allCamp, committee.getStudent());
        if(availableCamps == null){System.out.println("No camp available to suggest\n");return;}

        System.out.println("Enter your suggestion:\n");
        String comment = MainProgram.sc.nextLine();
        committee.createSuggestion(comment);
        committee.addPoint();
        System.out.printf("Suggestion submitted\nYou have been awarded one point\nYour points: %d",committee.getPoints());
        return;
    }

    public static void editSuggestion(Comment comment){
        System.out.println("Enter your new suggestion:");
        String newSugg = MainProgram.sc.nextLine();
        comment.editComment(newSugg);
        System.out.println("Your suggestion has been edited successfully");
    }

    public static void deleteSuggestion(Camp camp, Comment comment){
        camp.removeComment(comment);
        System.out.println("Your suggestion has been deleted successfully");
    }
}
