package practica1.Protocol;

import ast.protocols.tcp.TCPSegment;
import utils.Channel;

public class TSocketRecv {

    private final Channel channel;

    public TSocketRecv(Channel channel) {
        this.channel = channel;
    }

    public int receiveData(byte[] data, int offset, int length) {
        int bytesDataNum;
        TCPSegment tcpSegmentOnChannel = channel.receive();
        System.arraycopy(tcpSegmentOnChannel.getData(),tcpSegmentOnChannel.getDataOffset(),
                data,offset, length);
        bytesDataNum = length;
        if (length > tcpSegmentOnChannel.getDataLength())
            bytesDataNum = tcpSegmentOnChannel.getDataLength();
        return bytesDataNum;
    }
}
