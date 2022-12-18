import java.util.concurrent.Semaphore;

class Student extends Thread {
    Thread t;                //For current student thread

    int r;                    //Time to program before asking for help (in seconds).

    int studNum;            //Student number.

    static int ch = 0;        //For no. of Chair on which student is sitting

    Semaphore chairs;        //Semaphore used to wait in chairs outside office

    Semaphore availableTA;    //Mutex used to determine if TA is available.

    public Student(int r, Semaphore chairs, Semaphore availableTA, int studNum) {
        this.r = r;
        this.chairs = chairs;
        this.availableTA = availableTA;
        this.studNum = studNum;
        t = Thread.currentThread();
    }

    public void run() {
        while (true) {
            try {
                System.out.println("Student " + studNum + " has started programming for " + r + " seconds.");
                t.sleep(r * 1000);
                System.out.println("Student " + studNum + " is checking whether TA is available or not.");
                /*If TA is available*/
                if (availableTA.tryAcquire()) {
                    System.out.println("TA is available.TA is awoke by Student. Student " + studNum + " has Started working with TA.");
                    t.sleep(5000);
                    System.out.println("Student " + studNum + " has Stopped working with TA.");
                    availableTA.release();        //After completing work release semaphore of TA
                }
                /*If TA is not available*/
                else {
                    /*To check whether chairs are available*/
                    System.out.println("Student " + studNum + " could not see the TA. Checking for available chairs.");
                    if (chairs.tryAcquire()) {
                        ch++;
                        System.out.println("Student " + studNum + " is Sitting on chair " + ch);
                        System.out.println("Student " + studNum + " is checking whether TA is available or not.");
                        availableTA.acquire();    //Again Check whether TA is available
                        chairs.release();        //Release semaphore of chair if TA is available
                        ch--;
                        System.out.println("TA is available. Student " + studNum + " has Started working with TA.");
                        t.sleep(5000);
                        System.out.println("Student " + studNum + " has stopped working with TA.");
                        availableTA.release();    //After completing work release semaphore of TA
                    }
                    /*if Chairs are not available*/
                    else
                        System.out.println("Chairs are not available. Student " + studNum + " Back to programming");
                }
            } catch (Exception e) {
            }
        }
    }
}
