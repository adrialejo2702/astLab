package practica3;

import ast.protocols.tcp.TCPSegment;
import utils.Channel;

public class TSocketSend extends TSocketBase {

    protected int sndMSS;       // Send maximum segment size


    public TSocketSend(Channel channel) {
        super(channel);
        sndMSS = channel.getMMS();
    }

    public void sendData(byte[] data, int offset, int length) {
        int dataLength = length;
        while (dataLength>=sndMSS){
            sendSegment(segmentize(data,offset,sndMSS));
            offset = offset + sndMSS;
            dataLength=dataLength-sndMSS;
        }
        if (dataLength>0)
            sendSegment(segmentize(data,offset,dataLength));
    }

    protected TCPSegment segmentize(byte[] data, int offset, int length) {
        byte[] dataCopy = new byte[length];
        TCPSegment tcpSegment = new TCPSegment();
        System.arraycopy(data,offset,dataCopy,0,length);
        tcpSegment.setData(dataCopy,0,length);
        return tcpSegment;
    }

    protected void sendSegment(TCPSegment segment) {
        channel.send(segment);
    }
}
