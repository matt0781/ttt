import java.util.*;

public class MainProgram {
    public static final String mainObj_filename = "mainObj.ser";
    public static final Scanner sc = new Scanner(System.in);
    public static Date currentDate = new Date();
    public static void main(String[] args) {

        MainObj mainObj = SerializationUtil.loadMainObj(mainObj_filename);
        AllUser allUser = mainObj.getAllUser();
        AllCamp allCamp = mainObj.getAllCamp();
        allUser.printAllUser();
        allCamp.printAllCamp();
        User mainUser = AuthenticationMenu.loginMenu(allUser);
        if (mainUser == null) {
            System.out.println("Could not find user! Try again!");
            return;
        }
        SerializationUtil.saveObj(mainObj, mainObj_filename);

        
        boolean loop = true;
        String menuText = "\n(1)\tEdit profile\n(2)\tCamp menu\n(3)\tLogout\nChoice: ";
        String adminMenuText = "\n(1)\tEdit profile\n(2)\tAdd/Delete Users\n(3)\tLogout\nChoice: ";
        if (mainUser.getName().equals("admin")){
            while(loop){
                System.out.print(adminMenuText);
                int choice = sc.nextInt();
                    sc.nextLine();
                    switch(choice){
                        case 1: AuthenticationMenu.authenticationMenu(mainUser);
                                break;
                        case 2: userMenu(mainUser, allUser, allCamp);
                                SerializationUtil.saveObj(mainObj, mainObj_filename);
                                break;
                        default: loop = false;break;
                    }
                }
        }
        else{
            while (loop) {
                System.out.print(menuText);
                int choice = MainProgram.sc.nextInt();
                MainProgram.sc.nextLine();
                switch (choice) {
                    case 1:
                        AuthenticationMenu.authenticationMenu(mainUser);
                        SerializationUtil.saveObj(mainObj, mainObj_filename);
                        break;
                    case 2:
                        userMenu(mainUser, allUser, allCamp);
                        SerializationUtil.saveObj(mainObj, mainObj_filename);
                        break;
                    default:
                        loop = false;
                        break;
                }
            }
        }
        sc.close();
        SerializationUtil.saveObj(mainObj, mainObj_filename);
    }

    private static void userMenu(User mainUser, AllUser allUser, AllCamp allCamp) {
        if (mainUser instanceof Staff) {
            Staff mainStaff = (Staff) mainUser;
            StaffMenu staffMenu = new StaffMenu(mainStaff);
            staffMenu.mainMenu(allUser, allCamp);

        } else if (mainUser instanceof Student){
            Student mainStudent = (Student) mainUser;
            StudentMenu studentMenu = new StudentMenu(mainStudent);
            studentMenu.mainMenu(allUser, allCamp);
        }
        // else {
        //     AdminUser mainAdmin = (AdminUser) mainUser;
        //     AdminMenu adminmenu = new AdminMenu(mainAdmin, allUser,mainObj);
        //     adminmenu.mainMenu();
        // }
        return;
    }
}
