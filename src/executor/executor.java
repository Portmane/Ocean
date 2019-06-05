package executor;


import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.Executor;



class SerialExecutor implements Executor {
    final Queue<Runnable> tasks = new ArrayDeque<>();
    final Executor executor;
    Runnable active;

    SerialExecutor(Executor executor) {
        this.executor = executor;
    }

    public synchronized void execute(Runnable r) {
        tasks.add(() -> {
            try {
                r.run();
            } finally {
                scheduleNext();
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
        };
        SerialExecutor se = new SerialExecutor(ex);
        se.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello");
            }
        });
    }
}
