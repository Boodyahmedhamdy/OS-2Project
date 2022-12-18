

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