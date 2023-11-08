import java.util.*;




public class AdminMenu {
    private AdminUser adminUser;
    private AllUser allUser;
    private String mainObj_filename="mainObj_filename";
    private MainObj mainObj;
    ArrayList<User> userArray;
    String domain = "@ntu.edu.sg";

    public AdminMenu(AdminUser adminUser,AllUser allUser,MainObj mainObj){
        this.adminUser = adminUser;
        this.allUser = allUser;
        this.mainObj = mainObj;
        this.userArray = allUser.getUserArray();
    }

    public void mainMenu() {
        String menuText = "\nAdmin Page\n(1)\tAdd user\n(2)\tDelete user\n(3)\tExit\nChoice: ";
        System.out.print(menuText);
        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();
        sc.nextLine();
        while (true) {
            switch (choice) {
                case 1:
                    this.AddUserMenu();
                    break;
                case 2:
                    this.DeleteMenu();
                    break;
                default:
                    return;
            }
            System.out.print(menuText);
            choice = sc.nextInt();
            sc.nextLine();
        }
    }

    public void AddUserMenu() {
        String AddUserMenuText = "\nAdd User Page\n(1)\tAdd staff\n(2)\tAdd student\n(3)\tExcel add staff\n" + //
                "(4)\tExcel add student\n" + //
                        "(5)\tBack\nChoice: ";
        System.out.print(AddUserMenuText);
        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();
        sc.nextLine();
        while (true) {
            switch (choice) {
                case 1:
                    this.AddStaffMenu();
                    break;
                case 2:
                    this.AddStudentMenu();
                    break;
                case 3:
                    this.AddStaffXLSXMenu();
                    break;
                case 4:
                    this.AddStudentXLSXMenu();
                    break;
                default:
                    return;
            }
            System.out.print(AddUserMenuText);
            choice = sc.nextInt();
            sc.nextLine();
        }
    }



        public void AddStaffMenu() {
        System.out.printf("Input New Staff Information\n");
        Scanner sc = new Scanner(System.in);
        System.out.print("New Staff Name: ");
        String newStaffName = sc.nextLine();
//error checking for name
        if (newStaffName == null || newStaffName.isEmpty()) {
            System.out.println("Error: Name cannot be null or empty.");
            return;
        }

        System.out.print("New Staff Email: ");
        String newStaffEmail = sc.nextLine();
//error checking for email
        if (newStaffEmail == null || newStaffEmail.isEmpty()) {
            System.out.println("Error: Email cannot be null or empty.");
            return;
        }

        int atIndex = newStaffEmail.indexOf("@");
        if (atIndex != -1) {
            String userdomain = newStaffEmail.substring(atIndex); // Extracts from @ to the end
            if (!userdomain.equalsIgnoreCase(domain)) {
                System.out.println("Incorrect email domain");
                return;
            }
        }
        else{
            System.out.println("Incorrect email domain");
            return;
        }
//repeat checking for email
        Iterator<User> iterator = userArray.iterator();
            while (iterator.hasNext()) {
                User user = iterator.next();
                if (newStaffEmail.equalsIgnoreCase(user.getEmail())) {
                    System.out.println("User email already in used.");
                        return;}
                }
//error checking for faculty
        System.out.print("New Staff Faculty: ");
        String inputFaculty = sc.nextLine();
        Faculty.FACULTY_TYPE faculty = Faculty.getFacultyType(inputFaculty);
        
        if (faculty == null) {
            System.out.println("Invalid faculty");
            return;
        }
            
        Staff newStaff = new Staff(newStaffName, newStaffEmail, faculty);
        allUser.addUser(newStaff);
        System.out.println("New staff added successfully!");
        SerializationUtil.saveObj(mainObj, mainObj_filename);
        this.userArray = allUser.getUserArray();
        return;

        }



        public void AddStudentMenu() {
        System.out.printf("Input New Student Information\n");
        Scanner sc = new Scanner(System.in);
        System.out.print("New Student Name: ");
        String newStudentName = sc.nextLine();

        if (newStudentName == null || newStudentName.isEmpty()) {
            System.out.println("Error: Name cannot be null or empty.");
            return;
        }

        System.out.print("New Student Email: ");
        String newStudentEmail = sc.nextLine();

        if (newStudentEmail == null || newStudentEmail.isEmpty()) {
            System.out.println("Error: Email cannot be null or empty.");
            return;
        }

        int atIndex = newStudentEmail.indexOf("@");
        if (atIndex != -1) {
            String userdomain = newStudentEmail.substring(atIndex); // Extracts from @ to the end
            if (!userdomain.equalsIgnoreCase(domain)) {
                System.out.println("Incorrect email domain");
                return;
            }
        }
        else{
            System.out.println("Incorrect email domain");
            return;
        }

        Iterator<User> iterator = userArray.iterator();
            while (iterator.hasNext()) {
                User user = iterator.next();
                if (newStudentEmail.equalsIgnoreCase(user.getEmail())) {
                    System.out.println("User email already in used.");
                        return;}
                }

        System.out.print("New Student Faculty: ");
        String inputFaculty = sc.nextLine();
        Faculty.FACULTY_TYPE faculty = Faculty.getFacultyType(inputFaculty);
        
        if (faculty == null) {
            System.out.println("Invalid faculty");
            return;
        }
            
        Student newStudent = new Student(newStudentName, newStudentEmail, faculty);
        allUser.addUser(newStudent);
        System.out.println("New student added successfully!");
        SerializationUtil.saveObj(mainObj, mainObj_filename);
        this.userArray = allUser.getUserArray();
        return;

        }




    public void AddStaffXLSXMenu() {
        /* System.out.print("Input File Name. Include .xlsx");
        Scanner sc = new Scanner(System.in);
        String fileNameString = sc.nextLine();



        try {
            ArrayList<User> newuser = ExcelReader.readExcel(fileNameString, ExcelReader.ROLE.STAFF);
                allUser.addMultiUser(newuser);
                SerializationUtil.saveObj(allUser, "allUser.ser");
                this.userArray = allUser.getUserArray();
                System.out.printf("Succesfully added");
                return;
        } catch (Exception e) {
            System.out.printf("Operation failed!");
            return;
        } */
    }
    public void AddStudentXLSXMenu() {}


    public void DeleteMenu() {
        System.out.print("Delete account ");
        Scanner sc = new Scanner(System.in);
        do{ System.out.print("Username to be deleted: ");
            String Acc = sc.nextLine();

            if(Acc.equalsIgnoreCase("admin")){
                System.out.println("Unable to delete admin!");
                return;     
            }
            

            Iterator<User> iterator = userArray.iterator();
            while (iterator.hasNext()) {
                User user = iterator.next();
                if (Acc.equalsIgnoreCase(user.getUsername())) {
                    System.out.printf("Delete user: %s ? Press [y] to confirm.\n", user.getName());
                    String selection = sc.nextLine();
                    if (selection.equalsIgnoreCase("y")) {
                        iterator.remove();  // Removes the current user safely
                        SerializationUtil.saveObj(mainObj, mainObj_filename);
                        this.userArray = allUser.getUserArray();
                        System.out.println("Deletion successful");
                        return;}

                        
                    
                    else{
                        System.out.printf("Deletion not successful");
                        return;
                    }
                }
            }
            System.out.println("Could not find user! Try again!\n(1)\tInput username for deletion\n(2)\tReturn to Admin Page");
            Integer choice = sc.nextInt();
            if (choice.equals(2)){
                return;
            }
            sc.nextLine();
        }while( true);
    }

}

