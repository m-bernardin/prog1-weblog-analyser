Project: weblog-analyzer
Aothors: David J. Barnes and Michael Kölling

This project is part of the material for chapter 7 of the book

   Objects First with Java - A Practical Introduction using BlueJ
   Seventh edition
   David J. Barnes and Michael Kölling


Purpose of project: To provide an illustration of the use of arrays.
How to start this project: Create a LogAnalyzer object.

The LogfileReader expects to read a file, weblog.txt,
containing lines of data in the format:

    year month day hour minute

month values are in the range 1-12 and day values in the range 1-31.
If the sample file cannot be found, the reader will create some simulated
data. Alternatively, use the LogfileCreator to create some random data.
Use its createFile method to give a file name and the number of entries
to create.

part i:
q1. 2:00 and 6:00
q2. Person[] people = new Person[];
q3. boolean[] vacant = new boolean[];
q4. hourCounts is used 5 times, 2 of which use brackets
q5. brackets need to be after the type, size should be in the instantiation
q6. 
    double[] readings = new double[60];
    String[] urls = new String[90];
    TicketMachine[] machine = new TicketMachine[5];
q7. 0
q8. should be brackets, double[] prices = new double[50];
q9. out of range error on the last one
q11. 
    /** * Print all the values in the marks array that are greater than mean.
    * @param marks An array of mark values.
    * @param mean The mean (average) mark. */
    public void printGreater(double[] marks, double mean) {
        for(int index = 0; index < marks.length; index++) {
            if(marks[index] > mean) {
                System.out.println(marks[index]);
            }
        }
    }
q13. 
    /** 
    * Return the number of accesses recorded in the log file. 
    */
    public int numberOfAccesses() {
        int total = 0;
        for(int i = 0; i < hourCounts.length; ++i){
            total = total + hourCounts[i];
        }
        return total;
    }
    
q17. it returns the earliest hour