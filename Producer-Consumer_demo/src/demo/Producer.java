package demo;

import java.util.concurrent.BlockingQueue;

/**
 * 
 * @copyright Copyright 2016-2017 JD.COM All Right Reserved
 * @author 戴时机 部门：营销创新部-智能营销研发部
 * @version 1.0
 * @data 2016年11月2日 上午9:49:17
 * 
 *       生产者线程，可以外部设置停止标志从而停止线程。通过注入一个阻塞队列和消费者线程竞争使用阻塞队列
 */
public class Producer extends Thread {

    private boolean stopFlag = true; //停止标志

    private BlockingQueue<Object> queue = null; //由外部注入的阻塞队列

    private Object o = new Object(); //用来放入阻塞队列的对象

    public Producer(BlockingQueue<Object> queue) { //构造函数注入阻塞队列
        this.queue = queue;
    }

    public void setStopFlag() { //外部间接控制这个线程
        stopFlag = false;
    }

    @Override
    public void run() {
        while (stopFlag) {
            try {
                queue.put(o);
                System.out.println("生产了一个，现在的数量是" + queue.size());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
