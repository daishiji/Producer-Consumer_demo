package demo;

import java.util.concurrent.BlockingQueue;

/**
 * 
 * 
 * @copyright Copyright 2016-2017 JD.COM All Right Reserved
 * @author 戴时机 部门：营销创新部-智能营销研发部
 * @version 1.0
 * @data 2016年11月2日 上午9:55:48
 * 
 *       一个消费者线程，不停消费阻塞队列里面的元素，接受外部的停止信号。
 */
public class Consumer extends Thread {

    private boolean stopFlag = true; // 停止标志

    private BlockingQueue<Object> queue = null; // 阻塞队列

    private Object o = new Object(); // 对象

    public Consumer(BlockingQueue<Object> queue) { // 构造函数租入阻塞队列
        this.queue = queue;
    }

    public void setStopFlag() { // 外部间接控制这个线程
        stopFlag = false;
    }

    @Override
    public void run() {
        while (stopFlag) {
            try {
                queue.take();
                System.out.println("消费了一个，现在数量是：" + queue.size());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
