
/**
 * Read web server data and analyse hourly access patterns.
 * 
 * @author David J. Barnes and Michael Kölling.
 * @version 7.0
 */
public class LogAnalyzer
{
    public static final int HOURS_PER_DAY = 24;
    // Where to calculate the hourly access counts.
    private int[] hourCounts;
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
    public void analyzeHourlyData()
    {
        while(reader.hasNext()) {
            LogEntry entry = reader.next();
            int hour = entry.getHour();
            hourCounts[hour]++;
        }
    }

    /**
     * Print the hourly counts.
     * These should have been set with a prior
     * call to analyzeHourlyData.
     */
    public void printHourlyCounts()
    {
        System.out.println("Hr: Count");
        //q10
        int hour = 0;
        while(hour < hourCounts.length) {
            System.out.println(hour + ": " + hourCounts[hour]);
            ++hour;
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
}
