package vega.notification;

import org.zeromq.ZMQ;

public class Publisher {

    public static void main(final String[] args) throws InterruptedException {
        protocolBuffers();
    }

    public static void protocolBuffers() {
        final ZMQ.Context context = ZMQ.context(1);
        final ZMQ.Socket publisher = context.socket(ZMQ.PUB);

        // Subscriber tells us when it's ready here
        final ZMQ.Socket sync = context.socket(ZMQ.PULL);

        sync.bind("tcp://*:5561");

        // We send updates via this socket
        publisher.bind("tcp://*:5562");

        System.out.println("Publisher Running");

        // Wait for synchronization request
        sync.recv(0);



        final long start = System.currentTimeMillis();
        int i;
        for (i = 0; i != 10; i++) {
            System.out.println(i);
            String message = "toto"+i;
            publisher.send(message.getBytes(), 0); // serialization
        }
        final long end = System.currentTimeMillis();
        final long diff = (end - start);

        System.out.println("time taken to send messages " + i + " is :" + diff);
    }

    public static void characters() throws InterruptedException {
        // Prepare our context and publisher
        final ZMQ.Context context = ZMQ.context(1);
        final ZMQ.Socket publisher = context.socket(ZMQ.PUB);

        publisher.bind("tcp://*:5563");
        while (true) {
            // Write two messages, each with an envelope and content
            publisher.send("A".getBytes(), ZMQ.SNDMORE);
            publisher.send("We don't want to see this".getBytes(), 0);
            publisher.send("B".getBytes(), ZMQ.SNDMORE);
            publisher.send("We would like to see this".getBytes(), 0);
            Thread.sleep(1000);
        }
    }
}
