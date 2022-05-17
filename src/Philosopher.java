package COMP3432.Project;

public class Philosopher extends Thread implements Runnable {
    private int forkLeft;
    private int forkRight;
    private int index;
    private int eatCount;
    private boolean eat;

    public Philosopher(int index) {
        forkLeft = index;
        forkRight = (index + 1) % 5;
        this.index = index;
        this.eatCount = 0;
        this.eat = true;
    }

    @Override
    public void run() {
       
        while (eat) {
            boolean takenForks = takeForks();
            if (takenForks) {
                eat();
                putDownForks();
            }
            think();
        }
    }

    public int getEatCount() {
        return eatCount;
    }

    public void stopEat() {
        this.eat = false;
    }

   
    private boolean takeForks() {
        System.out.printf("Philosoph#%d is taking forks\n", index);
        boolean leftForkTaken = Table.takeFork(forkLeft);
        if (!leftForkTaken) {
            System.out.printf("Philosoph#%d couldn't take left fork\n", index);
            return false;
        }

        System.out.printf("Philosoph#%d took fork at left\n", index);
        boolean rightForkTaken = Table.takeFork(forkRight);
        if (!rightForkTaken) {
            System.out.printf("Philosoph#%d couldn't take right fork, putting down left fork\n", index);
            Table.putDownFork(forkLeft);
            return false;
        }
        System.out.printf("Philosoph#%d took fork at right\n", index);
        return true;
    }

    private void putDownForks() {
        System.out.printf("Philosoph#%d is putting forks down\n", index);
        Table.putDownFork(forkLeft);
        System.out.printf("Philosoph#%d put left fork down\n", index);
        Table.putDownFork(forkRight);
        System.out.printf("Philosoph#%d put right fork down\n", index);
    }

  
    private void eat() {
        System.out.printf("Philosoph#%d is now eating\n", index);
        eatCount++;
        sleep();
    }

  
    private void think() {
        System.out.printf("Philosoph#%d is now thinking\n", index);
        sleep();
    }

    private void sleep() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
