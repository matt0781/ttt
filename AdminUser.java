import java.util.*;

public class AdminUser extends User{
    private Authentication authentication;
    private String name;
    private String email;
    private Faculty.FACULTY_TYPE facultyType;
    
    public AdminUser(String name, String email, Faculty.FACULTY_TYPE faculty){
        this.setName(name);
        this.setEmail(email);
        this.setFaculty(faculty);
    }

}
