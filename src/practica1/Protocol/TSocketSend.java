package practica1.Protocol;

import ast.protocols.tcp.TCPSegment;
import utils.Channel;

public class TSocketSend {

    private final Channel channel;

    public TSocketSend(Channel channel) {
        this.channel = channel;
    }

    public void sendData(byte[] data, int offset, int length) {
        byte[] dataToSend = new byte[length];
        System.arraycopy(data, offset, dataToSend, 0, length);
        TCPSegment tcpSegmentToSend = new TCPSegment();
        tcpSegmentToSend.setData(dataToSend);
        channel.send(tcpSegmentToSend);
    }
}
