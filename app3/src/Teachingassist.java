import java.util.concurrent.Semaphore;

class Teachingassist extends Thread {
    Semaphore chairs;
    Thread t1;
    Semaphore availableTA;

    int numChairs;

    int cta, cchairs;

    public Teachingassist(Semaphore chairs, Semaphore availableTA, int numChairs) {
        this.chairs = chairs;
        this.availableTA = availableTA;
        this.numChairs = numChairs;
        t1 = Thread.currentThread();
    }

    public void run() {
        while (true) {
            try {

                cta = availableTA.availablePermits();            //Returns current value of semaphore availableta
                cchairs = chairs.availablePermits();            //Returns current value of semaphore chairs

                /*if no students in cabin and on chairs*/
                if (cta == 1 && cchairs == numChairs) {
                    System.out.println("TA is Sleeping");
                    t1.sleep(1000);
                }

            } catch (Exception e) {
            }
        }

    }
}
