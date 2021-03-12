package practica7;

import ast.logging.Log;
import ast.logging.LogFactory;
import ast.protocols.tcp.TCPSegment;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.ArrayList;
import utils.FDuplexChannel;

public class Protocol {

    public static Log log = LogFactory.getLog(Protocol.class);

    protected Lock lk;
    protected Thread task;
    protected FDuplexChannel.Peer channel;
    protected ArrayList<TSocket> listenTSocks;
    /* All unbound TSockets (in state LISTEN) */
    protected ArrayList<TSocket> activeTSocks;
    /* All bound TSockets (in active states) */
    private int nextFreePort;

    protected Protocol(FDuplexChannel.Peer ch) {
        lk = new ReentrantLock();
        channel = ch;
        listenTSocks = new ArrayList();//<-- sockets servidor
        activeTSocks = new ArrayList();//<-- sockets normals que formen part d'una connexió
        nextFreePort = 0xc000;
        task = new Thread(new ReceiverTask());
        task.start();
    }

    //<-- TSocketServidor
    public TSocket openListen(int localPort) {
        // Comprobar que el port no esta ocupat
        if (portInUse(localPort, listenTSocks) || portInUse(localPort, activeTSocks)) {
            log.error("openListen: port %d is in use", localPort);
            return null;
        }
        TSocket socket = new TSocket(this, localPort);
        socket.listen();
        return socket;
    }

    //<-- TSocket normal (client)
    public TSocket openConnect(int remotePort) {
        int localPort = newPort();
        TSocket sock = new TSocket(this, localPort);
        sock.connect(remotePort);
        return sock;
    }

    public void ipInput(TCPSegment segment) {
        // Search matching TSocket
        // Completar       
        // Fet en anteriors practiques
        //...
        throw new RuntimeException("Falta completar");
    }

    //-------------------------------------------
    // Internals:
    /**
     * Add a TSock to list of listen TSocks. We assume the TSock is in state
     * LISTEN.
     */
    protected void addListenTSocket(TSocket tcb) {
        lk.lock();
        listenTSocks.add(tcb);
        lk.unlock();
    }

    /**
     * Add a TSock to list of active TSocks. We assume the TSock is in an active
     * state.
     */
    protected void addActiveTSocket(TSocket tcb) {
        lk.lock();
        activeTSocks.add(tcb);
        lk.unlock();
    }

    /**
     * Remove a TSock from list of listen TSocks. We assume the TSock is in
     * CLOSED state.
     */
    protected void removeListenTSocket(TSocket tcb) {
        lk.lock();
        listenTSocks.remove(tcb);
        lk.unlock();
    }

    /**
     * Remove a TSock from list of active TSocks. We assume the TSock is in
     * CLOSED state.
     */
    protected void removeActiveTSocket(TSocket sc) {
        lk.lock();
        activeTSocks.remove(sc);
        lk.unlock();
    }

    protected TSocket getMatchingTSocket(int localPort, int remotePort) {
        lk.lock();
        try {
            // Completar
            // Modificar adientment el metode de alguna practica anterior
            //...
            
            //s'ha de buscar destinatari a la llista de sockets actius
            //és a dir, sockets que ja formen part d'una connexió establerta.
            //En aquest cas han de coincidir els dos ports (iles iP's)
            //....
            //<-- en cas de no haver trobat destinatari:
            //s'ha de buscar un socket servidor, de la llista de sockets listen,
            //com a possible destinatari. En aquest cas només han de coincidir
            //el port destí amb el port local del socket servidor (que de fet
            //no té port remot)
            
            throw new RuntimeException("Falta completar");
        } finally {
            lk.unlock();
        }
    }

    /**
     * Used from method 'listen'.
     */
    protected boolean portInUse(int localPort, ArrayList<TSocket> list) {
        lk.lock();
        try {
            // Search in active TSocks
            for (TSocket c : list) {
                if (c.localPort == localPort) {
                    return true;
                }
            }
            return false;
        } finally {
            lk.unlock();
        }
    }

    /**
     * Allocate a new (free) local port.
     */
    protected int newPort() {
        lk.lock();
        try {
            int base = nextFreePort & 0x3fff;
            nextFreePort = 0xc000 | ((base + 1) & 0x3fff);
            for (int i = 0; i <= 0x3fff; i++) {
                int port = 0xc000 | ((base + i) & 0x3fff);
                if (!portInUse(port, activeTSocks) && !portInUse(port, listenTSocks)) {
                    return port;
                }
            }
            log.error("newPort: resources exhausted");
            return -1;
        } finally {
            lk.unlock();
        }
    }

    //-------------------------------------------
    class ReceiverTask implements Runnable {

        public void run() {
            while (true) {
                TCPSegment rseg = channel.receive();
                ipInput(rseg);
            }
        }
    }
}
