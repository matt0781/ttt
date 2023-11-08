import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Report{

    public enum ListReportType {
        ATTENDEE,
        COMMITTEE,
        BOTH
    }

    public static void generateListReport(Camp camp, ListReportType reportType){

        String fileName = "report.csv";
        // Get the current working directory
        String workingDir = System.getProperty("user.dir");
        // Combine them to create the file path
        String filePath = Paths.get(workingDir, fileName).toString();
        
        // Generate the report in CSV format
        try (FileWriter writer = new FileWriter(filePath)) {
            // Write the header
            writer.append("Name,Email,Faculty,Role\n");

            // Depending on the report type, write the appropriate records
            if (reportType == ListReportType.ATTENDEE || reportType == ListReportType.BOTH) {
                getAttendeeProfile(camp, writer);
            }

            if (reportType == ListReportType.COMMITTEE || reportType == ListReportType.BOTH) {
                // ... (The code for writing committee profiles would go here
                getCommitteeProfile(camp, writer);
            }

            System.out.println("Report generated successfully at: " + filePath);
        } catch (IOException e) {
            System.out.println("An error occurred while writing the report: " + e.getMessage());
        }

    }

    public static void generatePerformanceReport(Camp camp){
        ArrayList<Student> committees = camp.getCommittee();

        String fileName = "performance_report.csv";
        // Get the current working directory
        String workingDir = System.getProperty("user.dir");
        // Combine them to create the file path
        String filePath = Paths.get(workingDir, fileName).toString();

        // Generate the report in CSV format
        try (FileWriter writer = new FileWriter(filePath)) {
            // Write the header
            writer.append("Name,Email,Faculty,Role,Points\n");

            for (Student committee : committees) {
                writer.append(committee.getName()).append(",");
                writer.append(committee.getEmail()).append(",");
                writer.append(committee.getFaculty().toString()).append(",");
                writer.append("committee").append("\n");
                writer.append(Integer.toString(committee.studentCommittee.getPoints()));
            }

            System.out.println("Report generated successfully at: " + filePath);
        } catch (IOException e) {
            System.out.println("An error occurred while writing the report: " + e.getMessage());
        }
    }

    public static void getAttendeeProfile(Camp camp, FileWriter writer) throws IOException{
        ArrayList<Student> attendees = camp.getAttendee();

        for (Student attendee : attendees) {
            writer.append(attendee.getName()).append(",");
            writer.append(attendee.getEmail()).append(",");
            writer.append(attendee.getFaculty().toString()).append(",");
            writer.append("attendee").append("\n");
        }

    }

    public static void getCommitteeProfile(Camp camp, FileWriter writer) throws IOException{
        ArrayList<Student> committees = camp.getCommittee();
        for (Student committee : committees) {
            writer.append(committee.getName()).append(",");
            writer.append(committee.getEmail()).append(",");
            writer.append(committee.getFaculty().toString()).append(",");
            writer.append("committee").append("\n");
        }

    }

}

