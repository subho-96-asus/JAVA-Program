import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;
import java.util.Scanner;

public class AgeCalculator {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input format
        System.out.print("Enter the date format (DD-MM-YYYY, YYYY-MM-DD, MM-DD-YYYY): ");
        String dateFormat = scanner.nextLine();

        // Input DOB or age
        System.out.println("Enter 'DOB' for date of birth or 'AGE' for age: ");
        String inputType = scanner.nextLine().toUpperCase();

        if (inputType.equals("DOB")) {
            System.out.println("Enter date of birth in the format " + dateFormat + ": ");
            String dobString = scanner.nextLine();

            try {
                Date dob = new SimpleDateFormat(dateFormat).parse(dobString);
                Date today = new Date();

                // Calculate age in years, months, and days
                int ageInYears = calculateAgeInYears(dob, today);
                int ageInMonths = calculateAgeInMonths(dob, today);
                int ageInDays = calculateAgeInDays(dob, today);

                System.out.println("Age: " + ageInYears + " years, " + ageInMonths + " months, " + ageInDays + " days");
            } catch (ParseException e) {
                System.out.println("Invalid date format. Please enter the date in the specified format.");
            }
        } else if (inputType.equals("AGE")) {
            System.out.println("Enter age in the format DD-MM-YYYY: ");
            String ageString = scanner.nextLine();

            try {
                Date ageDate = new SimpleDateFormat("dd-MM-yyyy").parse(ageString);
                Date today = new Date();

                // Calculate DOB
                Date dob = calculateDOB(ageDate, today);
                String dobString = new SimpleDateFormat(dateFormat).format(dob);

                System.out.println("Date of Birth: " + dobString);
            } catch (ParseException e) {
                System.out.println("Invalid age format. Please enter the age in the format DD-MM-YYYY.");
            }
        } else {
            System.out.println("Invalid input. Please enter 'DOB' or 'AGE'.");
        }

        scanner.close();
    }

    // Helper methods to calculate age and DOB
    private static int calculateAgeInYears(Date dob, Date today) {
        // ... (implementation omitted for brevity)
        Calendar dobCalendar = Calendar.getInstance();
        dobCalendar.setTime(dob);

        Calendar todayCalendar = Calendar.getInstance();
        todayCalendar.setTime(today);

        int ageInYears = todayCalendar.get(Calendar.YEAR) - dobCalendar.get(Calendar.YEAR);

        // Adjust for leap years
        if (todayCalendar.get(Calendar.MONTH) < dobCalendar.get(Calendar.MONTH) ||
                (todayCalendar.get(Calendar.MONTH) == dobCalendar.get(Calendar.MONTH) &&
                        todayCalendar.get(Calendar.DAY_OF_MONTH) < dobCalendar.get(Calendar.DAY_OF_MONTH))) {
            ageInYears--;
        }

        return ageInYears;
    }

    private static int calculateAgeInMonths(Date dob, Date today) {
        // ... (implementation omitted for brevity)
        Calendar dobCalendar = Calendar.getInstance();
        dobCalendar.setTime(dob);

        Calendar todayCalendar = Calendar.getInstance();
        todayCalendar.setTime(today);

        int ageInYears = todayCalendar.get(Calendar.YEAR) - dobCalendar.get(Calendar.YEAR);
        int ageInMonths = ageInYears * 12;

        int todayMonth = todayCalendar.get(Calendar.MONTH);
        int dobMonth = dobCalendar.get(Calendar.MONTH);

        ageInMonths += todayMonth - dobMonth;

        // Adjust for days if necessary
        if (todayCalendar.get(Calendar.DAY_OF_MONTH) < dobCalendar.get(Calendar.DAY_OF_MONTH)) {
            ageInMonths--;
        }

        return ageInMonths;
    }

    private static int calculateAgeInDays(Date dob, Date today) {
        long diffInMillies = Math.abs(today.getTime() - dob.getTime());
        return (int) TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
    }

    private static Date calculateDOB(Date ageDate, Date today) {
        // ... (implementation omitted for brevity)
        Calendar ageCalendar = Calendar.getInstance();
        ageCalendar.setTime(ageDate);

        Calendar todayCalendar = Calendar.getInstance();
        todayCalendar.setTime(today);

        int ageInYears = todayCalendar.get(Calendar.YEAR) - ageCalendar.get(Calendar.YEAR);

        // Adjust for leap years
        if (todayCalendar.get(Calendar.MONTH) < ageCalendar.get(Calendar.MONTH) ||
                (todayCalendar.get(Calendar.MONTH) == ageCalendar.get(Calendar.MONTH) &&
                        todayCalendar.get(Calendar.DAY_OF_MONTH) < ageCalendar.get(Calendar.DAY_OF_MONTH))) {
            ageInYears--;
        }

        todayCalendar.add(Calendar.YEAR, -ageInYears);

        return todayCalendar.getTime();
    }
}