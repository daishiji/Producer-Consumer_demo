package demo;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * 
 * @copyright Copyright 2016-2017 JD.COM All Right Reserved
 * @author 戴时机 部门：营销创新部-智能营销研发部
 * @version 1.0
 * @data 2016年11月2日 上午9:05:53
 * 
 *       通过限制大小的阻塞队列实现生产者消费者demo。队列长度为20， 生产到20个时，生产者线程 会阻塞，消费到0个时，消费者线程会阻塞。
 */
public class Main {

    public static void main(String[] args) {

        BlockingQueue<Object> queue = new ArrayBlockingQueue<Object>(20); //一个限制大小的阻塞队列

        Producer producer = new Producer(queue); //生产者线程
        Consumer consumer = new Consumer(queue); //消费者线程
  
        //这个地方简单演示没有使用线程池控制线程
        producer.start();
        consumer.start();

        //确保最终会停止
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            producer.setStopFlag();
            consumer.setStopFlag();
            System.out.println("生产者消费者线程都结束了");
        }
    }
}
