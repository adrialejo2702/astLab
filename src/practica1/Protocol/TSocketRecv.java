package practica1.Protocol;

import ast.protocols.tcp.TCPSegment;
import utils.Channel;

public class TSocketRecv {

    private final Channel channel;

    public TSocketRecv(Channel channel) {
        this.channel = channel;
    }

    public int receiveData(byte[] data, int offset, int length) {
        int bytesDataNum = length;
        TCPSegment tcpSegmentOnChannel = channel.receive();

        if (length > tcpSegmentOnChannel.getDataLength())
            bytesDataNum = tcpSegmentOnChannel.getDataLength();
        System.arraycopy(tcpSegmentOnChannel.getData(),tcpSegmentOnChannel.getDataOffset(),
                data,offset, bytesDataNum);
        return bytesDataNum;
    }
}
