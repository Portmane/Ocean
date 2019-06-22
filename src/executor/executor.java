package executor;


import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.Executor;



class SerialExecutor implements Executor {
    final Queue<Runnable> tasks = new ArrayDeque<>();   //Queue of Runnable objects.
    final Executor executor;                            //Variable to witch will be assigned executor to perform Runnable instance.
    Runnable active;                                    //Will store value returned from tasks(local variable) Queue.

    SerialExecutor(Executor executor) {
        this.executor = executor;
    }   //Assigned executor to local variable.

    public synchronized void execute(Runnable r) {  //Overrides Executor method.
        tasks.add(() -> {   //Adding Runnable instance to Queue throw lambda expression.
            try {
                r.run();    //Line of adding.
            } finally {
                scheduleNext(); //Final step in witch we will be going throw queue until the instance won't be performed.
            }
        });
        if (active == null) {
            scheduleNext();
        }
    }

    protected synchronized void scheduleNext() {
        if ((active = tasks.poll()) != null) {
            executor.execute(active);
        }
    }
}

public class executor {
    public static void main(String[] args) {
        Executor ex = new Executor() {
            @Override
            public void execute(Runnable command) {
                command.run();
            }
        };              /*Over executor that will be used in SerialExecutor
                                                        * to execute Runnable objects. */
        SerialExecutor se = new SerialExecutor(ex);     /*Assign ex object(executor) to executor variable in SerialExecutor
                                                        * class.*/
        se.execute(new Runnable() {                     //Example !!! Of using.
            @Override
            public void run() {
                System.out.println("Hello");
            }
        });
    }
}
