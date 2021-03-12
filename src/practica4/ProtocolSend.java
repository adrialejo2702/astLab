package practica4;

import java.util.ArrayList;
import utils.Channel;

public class ProtocolSend extends ProtocolBase {

    protected ArrayList<TSocketSend> sockets;

    public ProtocolSend(Channel ch) {
        super(ch);
        sockets = new ArrayList();
    }

    //<-- crear socket (i el retorna) i l'afegeix a la llista de sockets
    public TSocketSend openForOutput(int localPort, int remotePort) {
        lk.lock();
        try {

            TSocketSend t = new TSocketSend(this, localPort, remotePort);
            //<-- falta posar-lo a la llista sockets
            //...
            //treu aquesta sentencia en completar el codi:
            return t;

        } finally {
            lk.unlock();
        }
    }
}
