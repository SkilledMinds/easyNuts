// Queue
class CustomerQueue
{

    String sharedCustomerQueue[] = new String[5];
}

// Client
public class ProducerConsumerQ
{

    static int NEED_TO_SERVE_BATCH = 2;

    public static void main(String[] args)
    {

        System.out.println(" Producer will fill SharedQueue with 5 elements in each batch.");
        System.out.println(" Consumer will consume elements available in SharedQueue in each batch.");

        System.out.println(" Total " + NEED_TO_SERVE_BATCH + " will be served.");
        System.out.println(" \n ***************************************************************************** ");
        CustomerQueue cq = new CustomerQueue();
        Thread pro = new producer(cq);
        Thread con = new consumer(cq);
        pro.setName("Producer");
        con.setName("Consumer");
        pro.start();
        con.start();

    }

}

class producer extends Thread
{

    int count = 0;
    CustomerQueue qp;

    public producer(CustomerQueue q)
    {
        qp = q;
    }

    @Override
    public void run()
    {

        while (count < ProducerConsumerQ.NEED_TO_SERVE_BATCH)
        {
            System.out.println("\n");
            synchronized (qp.sharedCustomerQueue)
            {

                // everytime generate 5 random numbers and put in Queue
                for (int p = 0; p < 5; p++)
                {
                    qp.sharedCustomerQueue[p] = (int) ((Math.random() * 10) % 10) + "";
                    System.out.println("Producer : Genereated item is :" + qp.sharedCustomerQueue[p]);
                }
                qp.sharedCustomerQueue.notify();
                try
                {
                    qp.sharedCustomerQueue.wait();
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
                count++;
            }
        }

    }
}

class consumer extends Thread
{

    int count = 0;
    CustomerQueue qc;

    public consumer(CustomerQueue q)
    {
        qc = q;
    }

    @Override
    public void run()
    {

        while (count < ProducerConsumerQ.NEED_TO_SERVE_BATCH)
        {
            if (qc.sharedCustomerQueue[0] != null)
            {
                synchronized (qc.sharedCustomerQueue)
                {

                    for (String qItem : qc.sharedCustomerQueue)
                    {
                        System.out.println("Consumer : Consuming item " + qItem);
                    }

                    System.out.println("\n");
                    qc.sharedCustomerQueue.notify();

                    try
                    {
                        qc.sharedCustomerQueue.wait();
                    }
                    catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                    count++;
                }
            }
        }

    }
}
