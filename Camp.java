import java.io.Serializable;
import java.util.*;

public class Camp implements deleteCampInterface, Serializable, Comparable<Camp>{

    private CampInformation campInformation;
    private ArrayList<Student> attendee = new ArrayList<>();
    private ArrayList<Student> committee = new ArrayList<>();
    private ArrayList<Comment> suggestion = new ArrayList<>();
    private ArrayList<Comment> enquiry = new ArrayList<>();
    private int campId;
    private static int nextId = 1;

    public Camp(String name, Date startDate, Date endDate, int totalSlot, String location, Date registrationDeadline,
            Staff staff, ArrayList<Faculty.FACULTY_TYPE> faculty, Boolean visible, String description) {
        this.campInformation = new CampInformation(name, startDate, endDate, totalSlot, location, registrationDeadline,
                staff, faculty, visible, description);
        this.campId = nextId;
        nextId += 1;
    }

    public void editCamp() {
        if (campInformation != null) {

        }
    }

    public boolean hasEmptySlot() {
        return attendee.size() + committee.size() < campInformation.getTotalSlot();
    }

    public void deleteCamp(Camp camp) {
        Staff staff = campInformation.getStaff();
        if (staff != null) {
            staff.deleteCamp(camp);
        }
        for (Student student : attendee) {
            student.deleteCamp(this);
        }
        for (Student student : committee) {
            student.deleteCamp(this);
        }
    }

    public void addAttendee(Student student) {
        if (this.hasEmptySlot()) {
            this.attendee.add(student);
        }
    }

    public void removeAttendee(Student student) {
        if (student == null) {
            return;
        }
        this.attendee.remove(student);
    }

    public void addCommittee(Student student) {
        if (this.hasEmptySlot() && committee.size() < CampInformation.MAX_COMMITTEE_SLOTS) {
            this.committee.add(student);
        }
    }

    public void setVisibility(Boolean value) {
        this.campInformation.setVisible(value);
    }

    public Boolean getVisbility() {
        return this.campInformation.isVisible();
    }

    public ArrayList<Student> getAttendee() {
        return this.attendee;
    }

    public ArrayList<Student> getCommittee() {
        return this.committee;
    }

    public ArrayList<Comment> getSuggestion() {
        return this.suggestion;
    }

    public ArrayList<Comment> getEnquiry() {
        return this.enquiry;
    }

    public CampInformation getCampInformation() {
        return this.campInformation;
    }

    public String getCampDetail() {
        return campInformation.getCampInformation();
    }

    public void printCamp() {
        System.out.println(this.getCampDetail());
    }

    public void addComment(Comment comment) {
        if (comment instanceof Suggestion) {
            this.suggestion.add(comment);
        } else {
            this.enquiry.add(comment);
        }
    }

    public void removeComment(Comment comment) {
        if (comment instanceof Suggestion) {
            try {
                this.suggestion.remove(comment);
            } catch (Exception e) {
                throw new IllegalArgumentException("Unable to remove suggestion");
            }
        } else {
            try {
                this.enquiry.remove(comment);
            } catch (Exception e) {
                throw new IllegalArgumentException("Unable to remove enquiry");
            }
        }
    }

    public int getCampId() {
        return this.campId;
    }

    public Boolean equalsCamp(Camp camp) {
        return this.campId == camp.getCampId();
    }

    @Override
    public int compareTo(Camp otherCamp) {
        return this.campInformation.getName().compareTo(otherCamp.campInformation.getName());
    }
}
