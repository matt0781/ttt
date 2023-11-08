import java.util.ArrayList;
import java.util.Scanner;

public class ReportMenu {

    private static Scanner sc = new Scanner(System.in);

    public static void displayStaffReportMenu(Staff staff) {
        ArrayList<Camp> availableCamps = staff.getCampsCreated();

        if (availableCamps == null) {
            System.out.println("No camp can be selected for report generation. Returning to staff menu...");
            return;
        }

        int i = 0;
        System.out.println("Generate report for camp:");
        for (Camp camp : availableCamps) {
            System.out.printf("%d) Camp Id - %d, Camp name - %s\n", ++i, camp.getCampId(),
                    camp.getCampInformation().getName());
        }
        System.out.printf("%d) Exit\n", ++i);
        System.out.print("Choice: ");
        int campChoice = sc.nextInt();

        Camp selectedCamp = availableCamps.get(campChoice - 1);

        System.out.println("What type of report do you want to generate? ");
        System.out.println("1) Attendee lists");
        System.out.println("2) Committee lists");
        System.out.println("3) Both Attendee and Committee lists");
        System.out.println("4) Committee Performance Report");
        System.out.println("5) Exit");
        boolean validInput = false;

        do {
            System.out.print("Choice: ");
            int reportTypeChoice = sc.nextInt();
            switch (reportTypeChoice) {
                case 1:
                    Report.generateListReport(selectedCamp, Report.ListReportType.ATTENDEE);
                    validInput = true;
                    break;
                case 2:
                    Report.generateListReport(selectedCamp, Report.ListReportType.COMMITTEE);
                    validInput = true;
                    break;
                case 3:
                    Report.generateListReport(selectedCamp, Report.ListReportType.BOTH);
                    validInput = true;
                    break;
                case 4:
                    Report.generatePerformanceReport(selectedCamp);
                    validInput = true;
                    break;
                case 5:
                    System.out.println("Quitting report generation. Returning to main menu...");
                    return;
                default:
                    System.out.println("Invalid input. Please try again.");
                    validInput = false;
            }
        } while (!validInput);

        return;

    }

    public static void displayCommitteeReportMenu(Committee committee) {
        Camp camp = committee.getCamp();

        if (camp == null) {
            System.out.println("No camp can be selected for report generation. Returning to committee menu...");
            return;
        }

        int i = 0;
        System.out.printf("Generating report for camp of Camp Id - %d, Camp name - %s\n", ++i, camp.getCampId(),
                camp.getCampInformation().getName());

        System.out.println("What type of report do you want to generate? ");
        System.out.println("1. Attendee lists");
        System.out.println("2. Committee lists");
        System.out.println("3. Both Attendee and Committee lists");
        System.out.println("4. Exit");

        boolean validInput = false;
        do {
            System.out.print("Choice: ");
            int reportTypeChoice = sc.nextInt();

            switch (reportTypeChoice) {
                case 1:
                    Report.generateListReport(camp, Report.ListReportType.ATTENDEE);
                    validInput = true;
                    break;
                case 2:
                    Report.generateListReport(camp, Report.ListReportType.COMMITTEE);
                    validInput = true;
                    break;
                case 3:
                    Report.generateListReport(camp, Report.ListReportType.BOTH);
                    validInput = true;
                    break;
                case 4:
                    System.out.println("Quitting report generation. Returning to main menu...");
                    return;
                default:
                    System.out.println("Invalid input. Please enter a valid input.");
                    validInput = false;

            }
        } while (!validInput);

    }

}