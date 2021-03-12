package practica4;

import ast.protocols.tcp.TCPSegment;
import java.util.ArrayList;
import utils.Channel;

public class ProtocolRecv extends ProtocolBase {

    protected Thread task;
    protected ArrayList<TSocketRecv> sockets;

    public ProtocolRecv(Channel ch) {
        super(ch);
        sockets = new ArrayList<TSocketRecv>();
        task = new Thread(new ReceiverTask());
        task.start();
    }

    //<--- crear socket i posar-lo a la llista de sockets
    public TSocketRecv openForInput(int localPort, int remotePort) {
        lk.lock();
        try {

            //...
            //treu aquesta sentencia en completar el codi:
            return null;

        } finally {
            lk.unlock();
        }
    }

    //<--- DEMULTIPLEXAT
    protected void ipInput(TCPSegment segment) {
        //<-- troba (en cas d'existir) el socket destinatari del segment:
        //<-------- cridant erl mètode getMatchingTSocket
        //<-- i li passa el segment (crident processReceivedSegment)
        //...
    }

    protected TSocketRecv getMatchingTSocket(int localPort, int remotePort) {
        lk.lock();
        try {
            //<-- itera la llista de sockets fins a trobar el destinatari
            //...
            return null;
        } finally {
            lk.unlock();
        }
    }

    class ReceiverTask implements Runnable {

        @Override
        public void run() {
            while (true) {
                TCPSegment rseg = channel.receive();
                ipInput(rseg);
            }
        }
    }

}
