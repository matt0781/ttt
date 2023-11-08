import java.util.*;
public class CommitteeMenu{

    public static void mainMenu(AllUser allUser, AllCamp allCamp, Committee committee){
        String menuText = "\nWhat are we doing today?\n(1)\tSubmit suggestions\n(2)\tRespond enquiries\n"+
        "(3)\tView, edit, delete my suggestions\n(4)\tGenerate report\n)(5)\tExit\nChoice: ";
        int choice;
        do{
        System.out.print(menuText);
        
        choice = MainProgram.sc.nextInt();
        MainProgram.sc.nextLine();
        switch(choice){
            case 1: //submit suggestions
                SuggestionMenu.addSuggestion(allUser, allCamp, committee);
                break;
            case 2:  //view n reply enquires        
                committee.viewEnquiry();
                System.out.print("Enter enquiry to reply: ");
                int enquiryNum = MainProgram.sc.nextInt();       
                Enquiry enquiry = (Enquiry)committee.getCamp().getEnquiry().get(enquiryNum-1);         
                committee.replyEnquiry(enquiry);
                break;
            case 3: // view,edit,delete my suggestions
                SuggestionMenu.suggestionMenu(committee.getStudent(), allCamp.getCamps());       
                break;
            case 4: //generate report
                ReportMenu.displayCommitteeReportMenu(committee);
                break;
            case 5: //quit program
                break;
            default: 
                System.out.println("Sorry, please enter a valid input.");

        }
        }while (true);
        
    }
}
