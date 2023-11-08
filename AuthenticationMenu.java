import java.util.*;

public class AuthenticationMenu {
    public static User loginMenu(AllUser allUser){
        int retry = 3;
        
        ArrayList<User> userArray = allUser.getUserArray();

        System.out.println("\nWelcome to the camp portal!");
        while(true){System.out.print("Username: ");
        String username = MainProgram.sc.nextLine();
       
        for(User user : userArray){
            if(username.toLowerCase().equals(user.getUsername().toLowerCase())){
                System.out.printf("Hello, %s! Key in your password to proceed\n", user.getName());
                do{
                    System.out.print("Password: ");
                    String password = MainProgram.sc.nextLine();
                    user.setAuthenticated(username, password);
                    retry -= 1;
                }while(!user.getauthenticated() && retry > 0);
                System.out.println();
                if(user.getauthenticated()){
                    if(!user.getLoggedIn()){
                        System.out.println("Please change your password on your first login!");
                        changePasswordMenu(user);
                        user.setLoggedIn(true);
                    }
                    return user;
                }
            }
        }}
    }

    public static void authenticationMenu(User user){
        

        String menuText = "\n(1)\tView profile\n(2)\tChange password\n(3)\tExit\nChoice: ";
        int choice = 0;
        while(true){
            System.out.print(menuText);
            choice = MainProgram.sc.nextInt();
            MainProgram.sc.nextLine();
            switch(choice){
                case 1: System.out.println(user.getUserInfo());break;
                case 2: changePasswordMenu(user);break;
                default: return;
            }
        }
    }

    private static void changePasswordMenu(User user){
        
        System.out.print("Enter new password: ");
        String password1 = MainProgram.sc.nextLine();
        System.out.print("Re-enter new password: ");
        String password2 = MainProgram.sc.nextLine();
        if(password1.equals(password2)){
            Authentication auth = user.getAuthentication();
            auth.setPassword(password2);
            System.out.println("Password updated successfully!");
        }
        else{
            System.out.println("Password does not match. Please try again...");
        }
        return;
    }
}
