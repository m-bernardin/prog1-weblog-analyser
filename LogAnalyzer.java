import java.util.*;

/**
 * Read web server data and analyse hourly access patterns.
 * 
 * @author David J. Barnes and Michael Kölling.
 * @version 7.0
 */
public class LogAnalyzer
{
    public static final int HOURS_PER_DAY = 24;
    public static final int DAYS_PER_MONTH = 32;
    // Where to calculate the hourly access counts.
    private int[] hourCounts;
    private int[] dayCounts;
    private HashMap<Integer,Integer> statusCounts;
    // Use a LogfileReader to access the data.
    private LogfileReader reader;

    /**
     * Create an object to analyze hourly web accesses.
     */
    public LogAnalyzer()
    { 
        // Create the array object to hold the hourly
        // access counts.
        hourCounts = new int[HOURS_PER_DAY];
        dayCounts = new int[DAYS_PER_MONTH];
        statusCounts = new HashMap<>();
        // Create the reader to obtain the data.
        reader = new LogfileReader();
    }
    
    /**
     * q12
     */
    public LogAnalyzer(String filename)
    {
        hourCounts = new int[HOURS_PER_DAY];
        reader = new LogfileReader(filename);
    }

    /**
     * Analyze the hourly access data from the log file.
     */
    private void analyzeHourlyData()
    {
        while(reader.hasNext()) {
            LogEntry entry = reader.next();
            int hour = entry.getHour();
            hourCounts[hour]++;
        }
    }

    /**
     * Analyze the hourly access data from the log file.
     */
    private void analyzeDailyData()
    {
        while(reader.hasNext()){
            LogEntry entry = reader.next();
            int day = entry.getDay();
            dayCounts[day]++;
        }
    }
    
    /**
     * Analyze the hourly access data from the log file.
     */
    private void analyzeStatusData()
    {
        while(reader.hasNext()){
            LogEntry entry = reader.next();
            int status = entry.getStatusCode();
            int amount = statusCounts.getOrDefault(status, 0);
            statusCounts.put(status, ++amount);
        }
    }
    
    /**
     * Print the hourly counts.
     * These should have been set with a prior
     * call to analyzeHourlyData.
     */
    public void printHourlyCounts()
    {
        analyzeHourlyData();
        System.out.println("Hr: Count");
        //q10
        int hour = 0;
        while(hour < hourCounts.length) {
            System.out.println(hour + ": " + hourCounts[hour]);
            ++hour;
        }
    }
    
    /**
     * Print the daily counts.
     * q19
     */
    public void printDailyCounts()
    {
        analyzeDailyData();
        System.out.println("Day: Count");
        int day = 0;
        while(day < dayCounts.length) {
            System.out.println((day+1) + ": " + dayCounts[day]);
            ++day;
        }
    }
    
    public void printStatusCounts()
    {
        analyzeStatusData();
        System.out.println("Status code: Count");
        
    }
    
    /**
     * this doesnt do anything because calendar doesnt work how i expected it to and i dont feel like figuring it out right now, 
     * but i may implement what it was intende for in the future
     */
    public String convertDayNames(int dayNum)
    {
        if(dayNum==0){
            return "Sunday";
        }
        else if(dayNum==1){
            return "Monday";
        }
        else if(dayNum==2){
            return "Tuesday";
        }else if(dayNum==3){
            return "Wednesday";
        }
        else if(dayNum==4){
            return "Thursday";
        }
        else if(dayNum==5){
            return "Friday";
        }
        else{
            return "Saturday";
        }
    }
    
    /**
     * Print the lines of data read by the LogfileReader
     */
    public void printData()
    {
        reader.printData();
    }
    
    /** 
    * q14
    * Return the number of accesses recorded in the log file. 
    */
    public int numberOfAccesses() 
    {
        int total = 0;
        for(int i = 0; i < hourCounts.length; ++i){
            total = total + hourCounts[i];
        }
        return total;
    }
    
    public int busiestHour()
    {
        boolean found = false;
        int currentLargestIndex = 0;
        int totalCounted = 0;
        int accesses = numberOfAccesses();
        for(int i = 0; !found; ++i){
            if(hourCounts[i]>hourCounts[currentLargestIndex]){
                currentLargestIndex=i;
            }
            totalCounted = totalCounted+hourCounts[i];
            if(accesses-totalCounted<=hourCounts[currentLargestIndex]){
                found = true;
            }
        }
        return currentLargestIndex;
    }
    
    public int quietestHour()
    {
        int currentSmallestIndex = 0;
        for(int i = 0; i<hourCounts.length; ++i){
            if(hourCounts[i]<hourCounts[currentSmallestIndex]){
                currentSmallestIndex=i;
            }
        }
        return currentSmallestIndex;
    }
    
    /**
     * q18
     */
    public String busiestTwoHours()
    {
        boolean found = false;
        ArrayList<Integer> twoHours = new ArrayList<>();
        int currentLargestIndex = 0;
        int totalCounted = 0;
        int accesses = numberOfAccesses();
        String range;
        for(int i = 0; i<hourCounts.length; i+=2){
            twoHours.add(hourCounts[i]+hourCounts[i+1]);
        }
        for(int j = 0; !found; ++j){
            if(twoHours.get(j)>twoHours.get(currentLargestIndex)){
                currentLargestIndex=j;
            }
            totalCounted = totalCounted+twoHours.get(j);
            if(accesses-totalCounted<=twoHours.get(currentLargestIndex)){
                found = true;
            }
        }
        if(currentLargestIndex==0){
            range = "0-1";
        }
        else if(currentLargestIndex==1){
            range = "2-3";
        }
        else if(currentLargestIndex==2){
            range = "4-5";
        }
        else if(currentLargestIndex==3){
            range = "6-7";
        }
        else if(currentLargestIndex==4){
            range = "8-9";
        }
        else if(currentLargestIndex==5){
            range = "10-11";
        }
        else if(currentLargestIndex==6){
            range = "12-13";
        }
        else if(currentLargestIndex==7){
            range = "14-15";
        }
        else if(currentLargestIndex==8){
            range = "16-17";
        }
        else if(currentLargestIndex==9){
            range = "18-19";
        }
        else if(currentLargestIndex==10){
            range = "20-21";
        }
        else{
            range = "22-23";
        }
        return range;
    }
    
    /**
     * q19
     */
    public String quietestTwoHours()
    {
        boolean found = false;
        ArrayList<Integer> twoHours = new ArrayList<>();
        int currentSmallestIndex = 0;
        String range;
        for(int i = 0; i<hourCounts.length; i+=2){
            twoHours.add(hourCounts[i]+hourCounts[i+1]);
        }
        for(int j = 0; !found; ++j){
            if(twoHours.get(j)>twoHours.get(currentSmallestIndex)){
                currentSmallestIndex=j;
            }
        }
        if(currentSmallestIndex==0){
            range = "0-1";
        }
        else if(currentSmallestIndex==1){
            range = "2-3";
        }
        else if(currentSmallestIndex==2){
            range = "4-5";
        }
        else if(currentSmallestIndex==3){
            range = "6-7";
        }
        else if(currentSmallestIndex==4){
            range = "8-9";
        }
        else if(currentSmallestIndex==5){
            range = "10-11";
        }
        else if(currentSmallestIndex==6){
            range = "12-13";
        }
        else if(currentSmallestIndex==7){
            range = "14-15";
        }
        else if(currentSmallestIndex==8){
            range = "16-17";
        }
        else if(currentSmallestIndex==9){
            range = "18-19";
        }
        else if(currentSmallestIndex==10){
            range = "20-21";
        }
        else{
            range = "22-23";
        }
        return range;
    }
}
