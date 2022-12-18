package TA;

import java.util.concurrent.Semaphore;
import java.util.*;


class SleepingTA {
    public static void main(String[] args)throws Exception {
        Scanner sc=new Scanner(System.in);
        int numStud,numChairs;

        /*Number of students and no. of Chairs*/
        System.out.println("Enter Total No. Of Students : ");
        numStud=sc.nextInt();
        System.out.println("Enter Total No. Of Chairs : ");
        numChairs=sc.nextInt();

        /*Semaphores for Chairs and TA*/
        Semaphore chairs = new Semaphore(numChairs);
        Semaphore availableTA = new Semaphore(1);

        //For randomly generating program time
        Random r = new Random();

        /*Create student threads and start them*/
        Student st[]=new Student[numStud];
        for (int i = 0; i < numStud; i++) {
            st[i]=new Student(r.nextInt(20),chairs,availableTA,i+1);
            st[i].start();
        }

        /*Create TA thread and start it*/
        Teachingassist ta = new Teachingassist(chairs,availableTA,numChairs);
        ta.start();

    }
}


/*students@CE-Lab-602-D04:~/Downloads$ javac Sleepingta67.java
^[[Astudents@CE-Lab-602-D04:~/Downloads$ java Sleepingta67
Enter Total No. Of Students :
5
Enter Total No. Of Chairs :
3
Student 1 has started programming for 11 seconds.
Student 2 has started programming for 1 seconds.
Student 3 has started programming for 17 seconds.
Student 4 has started programming for 2 seconds.
Student 5 has started programming for 10 seconds.
TA is Sleeping
Student 2 is checking whether TA is available or not.
TA is available.TA is awoke by Student. Student 2 has Started working with TA.
Student 4 is checking whether TA is available or not.
Student 4 could not see the TA. Checking for available chairs.
Student 4 is Sitting on chair 1
Student 4 is checking whether TA is available or not.
Student 2 has Stopped working with TA.
Student 2 has started programming for 1 seconds.
TA is available. Student 4 has Started working with TA.
Student 2 is checking whether TA is available or not.
Student 2 could not see the TA. Checking for available chairs.
Student 2 is Sitting on chair 1
Student 2 is checking whether TA is available or not.
Student 5 is checking whether TA is available or not.
Student 5 could not see the TA. Checking for available chairs.
Student 5 is Sitting on chair 2
Student 5 is checking whether TA is available or not.
Student 1 is checking whether TA is available or not.
Student 1 could not see the TA. Checking for available chairs.
Student 1 is Sitting on chair 3
Student 1 is checking whether TA is available or not.
Student 4 has stopped working with TA.
Student 4 has started programming for 2 seconds.
TA is available. Student 2 has Started working with TA.
Student 4 is checking whether TA is available or not.
Student 4 could not see the TA. Checking for available chairs.
Student 4 is Sitting on chair 3
Student 4 is checking whether TA is available or not.
Student 2 has stopped working with TA.
Student 2 has started programming for 1 seconds.
TA is available. Student 5 has Started working with TA.
Student 3 is checking whether TA is available or not.
Student 3 could not see the TA. Checking for available chairs.
Student 3 is Sitting on chair 3
Student 3 is checking whether TA is available or not.
Student 2 is checking whether TA is available or not.
Student 2 could not see the TA. Checking for available chairs.
Chairs are not available. Student 2 Back to programming
Student 2 has started programming for 1 seconds.
Student 2 is checking whether TA is available or not.
Student 2 could not see the TA. Checking for available chairs.
Chairs are not available. Student 2 Back to programming
Student 2 has started programming for 1 seconds.
Student 2 is checking whether TA is available or not.
Student 2 could not see the TA. Checking for available chairs.
Chairs are not available. Student 2 Back to programming
Student 2 has started programming for 1 seconds.
Student 2 is checking whether TA is available or not.
Student 2 could not see the TA. Checking for available chairs.
Chairs are not available. Student 2 Back to programming
Student 2 has started programming for 1 seconds.
Student 5 has stopped working with TA.
Student 5 has started programming for 10 seconds.
TA is available. Student 1 has Started working with TA.
Student 2 is checking whether TA is available or not.
Student 2 could not see the TA. Checking for available chairs.
Student 2 is Sitting on chair 3
Student 2 is checking whether TA is available or not.
Student 1 has stopped working with TA.
Student 1 has started programming for 11 seconds.
TA is available. Student 4 has Started working with TA.
^Cstudents@CE-Lab-602-D04:~/Downloads$

*/
