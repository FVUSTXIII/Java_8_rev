import java.io.File;  // Import the File class
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RetrieveSiteLoginActivity {
    private static Random random = new Random();
    private static boolean deleteDirectory (File directoryToBeDeleted) {
        File[] allContents = directoryToBeDeleted.listFiles();
        if (allContents != null) {
            for (File file : allContents) {
                deleteDirectory(file);
            }
        }
        return directoryToBeDeleted.delete();
    }
    private static String parseLine(String str) {
        Scanner sc = new Scanner(str);
        sc.useDelimiter("   ");
        String dateTimeString = sc.next();
        return dateTimeString;
    }
    private static String genUser() {
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = random.nextInt(10);
        String suffix = (targetStringLength < 4) ? "VISIT_ID:" : ( (targetStringLength < 5) ? "ERROR:" : "WARNING:");
        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .limit(targetStringLength+1)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
         return suffix + " " + generatedString;
    }
    private static void createActivityLog() {
        File exist = new File("./src/ActivityLog/logins");
        if (exist.exists()) {
           deleteDirectory(exist);
        }
        try {
            File ActivityLogDirectory = new File("./src/ActivityLog/logins");
            Boolean flag = ActivityLogDirectory.mkdirs();
            if (flag) {
                File ActivityLogFile = new File("./src/ActivityLog/logins/logs.txt");
                Integer day_of_month = random.nextInt(29) + 1;
                LocalDateTime start_time = LocalDateTime.of(2022, Month.MARCH, day_of_month, 0, 0);
                LocalDateTime end_time = LocalDateTime.of(2022, Month.MARCH, day_of_month +1, 0, 0);
                List<LocalDateTime> logins_list = Stream.iterate(start_time, h -> h.plusMinutes(random.nextInt(60)))
                        .limit(ChronoUnit.HOURS.between(start_time, end_time)*2)
                        .collect(Collectors.toList());
                if (ActivityLogFile.createNewFile()) {
                    FileWriter myWriter = new FileWriter("./src/ActivityLog/logins/logs.txt");
                    System.out.println("successfully created!");
                    logins_list.forEach( login -> {
                        try {
                            myWriter.write(login
                                    + " "
                                    + genUser()
                                    + "\n");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
                    myWriter.close();
                } else {
                    System.out.println("check implementation");
                }
            } else {
                System.out.println("A problem was encountered while trying to create directory");
            }

        } catch(IOException e) {
            System.out.println("An error occured");
            e.printStackTrace();
        }
    }

    public static List<LocalDateTime> trackActivityFrequency() throws IOException{
        createActivityLog();

        Supplier<Stream<String>> logs = () -> {
            try {
                Stream<String> lines = Files.lines(Paths.get("./src/ActivityLog/logins/logs.txt"));

                return lines;
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        };

        Map<Integer, Long> logs_ = logs.get()
                .map( log -> LocalDateTime.parse(log.split(" ")[0]))
                .sorted(new Comparator<LocalDateTime>() {
                    @Override
                    public int compare(LocalDateTime o1, LocalDateTime o2) {
                        return o1.getHour() - o2.getHour();
                    }
                })
                .collect(Collectors.groupingBy(LocalDateTime::getHour, Collectors.counting()));

        long max = logs_
                .entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .get()
                .getKey();
        List<String> uncut_dates = logs.get().filter(log -> log.split(" ")[1].equals("VISIT_ID:")).collect(Collectors.toList());

        List<LocalDateTime> dates = uncut_dates.stream()
                                    .map( log -> LocalDateTime.parse (log.split(" ")[0]))
                                    .filter(log -> log.getHour() == max)
                                    .collect(Collectors.toList());
        return dates;
    }
}
