package practica1.Protocol;

import ast.protocols.tcp.TCPSegment;
import practica1.CircularQ.CircularQueue;
import utils.Channel;

public class QueueChannel implements Channel {

    private CircularQueue<TCPSegment> segmentsOnChannel;

    public QueueChannel(int N) {
        segmentsOnChannel = new CircularQueue<TCPSegment>(N);
    }

    @Override
    public void send(TCPSegment s) {
        segmentsOnChannel.put(s);
    }

    @Override
    public TCPSegment receive() {
        return segmentsOnChannel.get();
    }

    @Override
    public int getMMS() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
