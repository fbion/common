package work.qizhi.test;


import utils.TestUtils;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2016/3/30.
 */
public class TestDelayQueue
{

    private static final long CUSTOMER_CONVERSATION_DURATION = 30000;

    private static CustomerMap customerMap = new CustomerMap();

    static class CustomerMap extends
            ConcurrentHashMap<String, Map<String, Object>> implements
            Serializable
    {
        /**
         * 人工客服会话队列
         */
        transient DelayQueue<CustomerConversation> queue = new DelayQueue();

        /**
         * 人工客服会话
         */
        class CustomerConversation implements Delayed, Serializable
        {
            /**
             * fromUser_toUser，用于标识人工客服会话
             */
            String identity;

            long trigger;

            public CustomerConversation(String identity)
            {
                this.identity = identity;
                resetTrigger();
            }

            public CustomerConversation resetTrigger()
            {
                trigger = System.currentTimeMillis()
                        + CUSTOMER_CONVERSATION_DURATION;
                return this;
            }

            @Override
            public long getDelay(TimeUnit unit)
            {
                return unit.convert(trigger - System.currentTimeMillis(),
                        TimeUnit.MILLISECONDS);
            }

            @Override
            public int compareTo(Delayed o)
            {
                return (int) (this.getDelay(TimeUnit.MILLISECONDS) - o
                        .getDelay(TimeUnit.MILLISECONDS));
            }

            public String getIdentity()
            {
                return identity;
            }

            public CustomerConversation setToExpired()
            {
                trigger = 0L;
                return this;
            }

            public String toString()
            {
                return identity + ":" + (trigger - System.currentTimeMillis());
            }
        }

        /**
         * 按给定会话标识创建标识，加入CustomerMap和DelayQueue队列
         * 
         * @param conversationIdentity
         * @param map
         * @return
         */
        public CustomerMap add(String conversationIdentity,
                Map<String, Object> map)
        {

            CustomerMap.CustomerConversation conversation = this.new CustomerConversation(
                    conversationIdentity);
            map.put("conversation", conversation);
            put(conversationIdentity, map);
            queue.offer(conversation);
            return this;
        }

        /**
         * 清掉所有过期的元素
         * 
         * @return
         */
        public CustomerMap clearExpired()
        {
            CustomerConversation conversation;
            while ((conversation = queue.poll()) != null)
            {
                this.remove(conversation.getIdentity());
            }
            return this;
        }

        /**
         * 根据人工客服标识更新相应会话的过期时间
         * 
         * @param conversationIdentity
         * @return
         */
        public CustomerMap update(String conversationIdentity)
        {
            Map<String, Object> map = (Map<String, Object>) get(
                    conversationIdentity);
            map.put("lastMessageTime", System.currentTimeMillis());
            CustomerConversation conversation = (CustomerConversation) map.get("conversation");
            conversation.resetTrigger();
            // 因DelayQueue对队首的元素无法正确处理
            // 通过map中存的DelayQueue队首的元素的过期时间更改，DelayQueue仍然会把它放在队首，其他元素在它之后过期，不会重排，
            // 而对非队首的元素更改后，能正确重排序，所以对队首元素需从队列删除，重新加入队列
            if (conversation == queue.peek())
            {
                queue.remove(conversation);
                queue.offer(conversation);
            }
            return this;
        }

        public CustomerMap delete(String conversationIdentity)
        {
            Map<String, Object> map = (Map<String, Object>) get(
                    conversationIdentity);
            CustomerConversation conversation = (CustomerConversation) map.get("conversation");
            conversation.setToExpired();
            remove(conversationIdentity);
            return this;
        }

        public void displayAll()
        {
            System.out.println("queue : " + queue);
            Iterator iterator = queue.iterator();
            if (iterator.hasNext())
            {
                while (iterator.hasNext())
                {
                    System.out.println(iterator.next());
                }
            }
            else
            {
                System.out.println("empty.");
            }
        }

        // private void writeObject(ObjectOutputStream stream) throws
        // IOException {
        // stream.defaultWriteObject();
        // stream.writeObject(b);
        // }
        private void readObject(ObjectInputStream stream) throws IOException,
                ClassNotFoundException
        {
            stream.defaultReadObject();
            queue = new DelayQueue();
            for (Entry<String, Map<String, Object>> entry : this.entrySet())
            {
                CustomerConversation conversation = (CustomerConversation) entry
                        .getValue().get("conversation");
                queue.add(conversation);
            }
        }
    }

    static
    {
        // ddpClient.connect("kf.smartnlp.cn",8080);
        Thread t = new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                while (true)
                {
                    try
                    {
                        customerMap.clearExpired();
                    }
                    catch(Exception e)
                    {
                        TestUtils.recordInFile(e.toString(),
                                System.getProperty("user.dir")
                                        + "/threadException.txt");
                    }
                }
            }
        });
        t.setDaemon(true);
        t.start();
    }

    public static void main(String[] args) throws InterruptedException,
            IOException
    {
        for (int i = 0; i < 5; i++)
        {
            customerMap.add(( i + 1) + "_" + ( i + 1), new HashMap<String, Object>());
        }

        customerMap.displayAll();
        customerMap.delete("1_1");
        System.out.println();
        TimeUnit.MILLISECONDS.sleep(100);
        customerMap.displayAll();



        //
        // TestSerial.storeObjectToFile(new Serializable[]{customerMap}, new
        // String[]{"queue.out"});

        // Serializable[] temp = new Serializable[1];
        // TestSerial.recoverObjectFromFile(temp, new String[]{"queue.out"});
        // customerMap = (CustomerMap)temp[0];
        // customerMap.displayAll();
    }
}
