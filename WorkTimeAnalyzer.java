import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class WorkTimeAnalyzer {

    public static void main(String[] args) throws ParseException {
        Scanner inputScanner = new Scanner(System.in);

        int totalLogs = Integer.parseInt(inputScanner.nextLine());
        Map<Integer, List<WorkLog>> employeeLogs = new HashMap<>();

        for (int logIndex = 0; logIndex < totalLogs; logIndex++) {
            String[] logDetails = inputScanner.nextLine().split(" ");
            int empId = Integer.parseInt(logDetails[0]);
            String activity = logDetails[1];
            String place = logDetails[2];
            String timestamp = logDetails[3] + " " + logDetails[4];

            employeeLogs.computeIfAbsent(empId, k -> new ArrayList<>())
                    .add(new WorkLog(activity, place, timestamp));
        }

        int suspectedEmployeeId = Integer.parseInt(inputScanner.nextLine());
        inputScanner.close();

        Map<Integer, Long> activeDurations = new HashMap<>();
        SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm a");

        for (Map.Entry<Integer, List<WorkLog>> logEntry : employeeLogs.entrySet()) {
            int empId = logEntry.getKey();
            List<WorkLog> logList = logEntry.getValue();

            Collections.sort(logList, Comparator.comparing(log -> {
                try {
                    return timeFormat.parse(log.timestamp);
                } catch (ParseException e) {
                    System.err.println("Error parsing timestamp: " + log.timestamp);
                    return new Date(0); // Default to epoch in case of error
                }
            }));

            long totalWorkTime = 0;
            Stack<WorkLog> roomActivityStack = new Stack<>();

            for (WorkLog log : logList) {
                if (log.place.startsWith("room")) {
                    try {
                        if (log.activity.equals("enters")) {
                            roomActivityStack.push(log);
                        } else if (log.activity.equals("exits") && !roomActivityStack.isEmpty()) {
                            WorkLog entryLog = roomActivityStack.pop();
                            Date entryTime = timeFormat.parse(entryLog.timestamp);
                            Date exitTime = timeFormat.parse(log.timestamp);
                            totalWorkTime += (exitTime.getTime() - entryTime.getTime());
                        }
                    } catch (ParseException e) {
                        System.err.println("Error parsing timestamp for activity: " + log.timestamp);
                    }
                }
            }

            activeDurations.put(empId, totalWorkTime);
        }

        long minimumWorkTime = Long.MAX_VALUE;
        for (long duration : activeDurations.values()) {
            minimumWorkTime = Math.min(minimumWorkTime, duration);
        }

        long suspectedEmployeeWorkTime = activeDurations.getOrDefault(suspectedEmployeeId, 0L);

        if (suspectedEmployeeWorkTime == minimumWorkTime) {
            System.out.println("Yes");
        } else if (suspectedEmployeeWorkTime > 0 && suspectedEmployeeWorkTime > minimumWorkTime) {
            System.out.println("No");
        } else {
            System.out.println("Cannot be determined");
        }
    }

    static class WorkLog {
        String activity;
        String place;
        String timestamp;

        WorkLog(String activity, String place, String timestamp) {
            this.activity = activity;
            this.place = place;
            this.timestamp = timestamp;
        }
    }
}

