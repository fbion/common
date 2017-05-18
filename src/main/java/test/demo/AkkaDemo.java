package test.demo;

/**
 * Created by Administrator on 2016/9/24.
 */
public class AkkaDemo {
//    // 显示线程信息
//    private static void threadMessage(Thread thread, String index) {
//        if (thread == null)
//            return;
//        System.out.println(index + "ThreadName- " + thread.getName()
//                + "  Priority- " + thread.getPriority()
//                + (thread.isDaemon() ? " Daemon" : "")
//                + (thread.isAlive() ? "" : " Inactive"));
//    }
//
//    // 显示线程组信息
//    private static void threadGroupMessage(ThreadGroup group, String index) {
//        if (group == null)
//            return; // 判断线程组
//        int count = group.activeCount(); // 获得活动的线程数
//        // 获得活动的线程组数
//        int countGroup = group.activeGroupCount();
//        // 根据活动的线程数创建指定个数的线程数组
//        Thread[] threads = new Thread[count];
//        // 根据活动的线程组数创建指定个数的线程组数组
//        ThreadGroup[] groups = new ThreadGroup[countGroup];
//        group.enumerate(threads, false); // 把所有活动子组的引用复制到指定数组中，false表示不包括对子组的所有活动子组的引用
//        group.enumerate(groups, false);
//        System.out.println(index + "ThreadGroupName-" + group.getName()
//                + "MaxPriority- " + group.getMaxPriority()
//                + (group.isDaemon() ? " Daemon" : ""));
//        // 循环显示当前活动的线程信息
//        for (int i = 0; i < count; i++)
//            threadMessage(threads[i], index + "    ");
//        for (int i = 0; i < countGroup; i++)
//            // 循环显示当前活动的线程组信息
//            threadGroupMessage(groups[i], index + "    ");// 递归调用方法
//    }
//
//    public static void threadsList() { // 找到根线程组并列出它递归的信息
//        ThreadGroup currentThreadGroup; // 当前线程组
//        ThreadGroup rootThreadGroup; // 根线程组
//        ThreadGroup parent;
//        // 获得当前活动的线程组
//        currentThreadGroup = Thread.currentThread().getThreadGroup();
//        rootThreadGroup = currentThreadGroup; // 获得根线程组
//        parent = rootThreadGroup.getParent(); // 获得根线程
//        while (parent != null) { // 循环对根线程组重新赋值
//            rootThreadGroup = parent;
//            parent = parent.getParent();
//        }
//        threadGroupMessage(rootThreadGroup, ""); // 显示根线程组
//    }
//
//    public static void test() {
//        Map<Thread, StackTraceElement[]> allStacks = Thread.getAllStackTraces();
//        Map<Thread.State, Map<Thread, StackTraceElement[]>> classifyThreads = new HashMap<Thread.State, Map<Thread, StackTraceElement[]>>();
//        if (allStacks != null) {
//            Iterator<Map.Entry<Thread, StackTraceElement[]>> stackIterator = allStacks.entrySet().iterator();
//            while (stackIterator.hasNext()) {
//                Map.Entry<Thread, StackTraceElement[]> stackEntry = stackIterator.next();
//                Thread thread = stackEntry.getKey();
//                Thread.State state = thread.getState();
//                StackTraceElement[] traceElements = stackEntry.getValue();
//                Map<Thread, StackTraceElement[]> threadStacks = classifyThreads.get(state);
//                if(threadStacks == null) {
//                    threadStacks = new HashMap<Thread, StackTraceElement[]>();
//                    classifyThreads.put(state, threadStacks);
//                }
//                threadStacks.put(thread, traceElements);
//
//            }
//        }
//
//        for(Thread.State state : Thread.State.values()) {
//            Map<Thread, StackTraceElement[]> threadStacks = classifyThreads.get(state);
//            if(threadStacks != null) {
//                Iterator<Map.Entry<Thread, StackTraceElement[]>> stackIterator = threadStacks.entrySet().iterator();
//                System.out.println(state.name()+"("+threadStacks.size());
//                while (stackIterator.hasNext()) {
//                    Map.Entry<Thread, StackTraceElement[]> stackEntry = stackIterator.next();
//                    Thread thread = stackEntry.getKey();
//                    StackTraceElement[] traceElements = stackEntry.getValue();
//                    System.out.println("" + thread.getId() + thread + "-" + thread.getState() + ":" + thread.getId()  + traceElements.length);
//                    if(traceElements != null) {
//                        for(StackTraceElement stack : traceElements) {
//                            System.out.println("\t\t" + stack + " - " + stack.getFileName());
//                        }
//                    }
//                }
//            }
//        }
//    }
//
//    public static class Actor1 extends UntypedActor {
//        public Object onReceive(Object message)  {
//            if (message instanceof String) {
//                System.out.println("查看JVM中所有的线程的活动状况如下：");
//                threadsList(); // 调用方法显示所有线程的信息
//                try {
//                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                System.out.println(Thread.currentThread().getName() + " sleep end");
//                System.out.println("接收的消息：" + message);
//                // 返回一个消息
//                this.getSender().tell("hello world", this.getSelf());
//                System.out.println("sender path=" + this.getSender().path());
//                getContext().stop(this.getSelf());
//                System.out.println("In Actor : " + Thread.currentThread().getName());
//            }
//            return null;
//        }
//    }
//
//    public static void main(String[] args)  throws Exception {
////        ActorSystem system = ActorSystem.create("mySystem");
////        ActorRef a = system.actorOf(Props.create(Actor1.class), "helloWorld");
////        Timeout timeout = new Timeout(Duration.create(5, "seconds"));
////        Future<Object> future = Patterns.ask(a, "are you ready?", timeout);
////
////
////        // This will cause the current thread to block and wait for the UntypedActor to ‘complete’
////        // the Future with it’s reply.
////        // 在这里会阻塞到 Await.result 方法上，但这会导致性能的损失。
////        String result = (String) Await.result(future, timeout.duration());
////        System.out.println(result);
////
////        System.out.println("In main : " + Thread.currentThread().getName());
//
////        System.out.println("查看JVM中所有的线程的活动状况如下：");
////        threadsList(); // 调用方法显示所有线程的信息
//
//        Method method = HashMap.class.getMethod("put", Object.class, Object.class);
//        System.out.println(method);
//    }
}