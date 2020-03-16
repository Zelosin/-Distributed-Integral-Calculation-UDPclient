package zelosin.pack.base;

import zelosin.pack.Data.CalculatingTask;
import zelosin.pack.Data.GFGCell;
import zelosin.pack.Services.UDP.GFGActionType;
import zelosin.pack.Services.UDP.GFGUDPProtocol;

import java.io.IOException;
import java.net.*;

public class EchoClient extends Thread{
    private static DatagramSocket mWorkingSocket;
    private static InetAddress mCurrentServerAddress;

    private static byte[] mByteBuffer= new byte[1024];
    private DatagramPacket mSendingPacket, mReceivingPacket;
    private static String mServerAddressName =  "localhost";
    private static GFGCell mResultingCell;

    public EchoClient(String pAddress) {
        mServerAddressName = pAddress;
    }

    private static DatagramPacket waitPacket(){
        mByteBuffer = new byte[1024];
        DatagramPacket tReceivingPacket
                = new DatagramPacket(mByteBuffer, mByteBuffer.length);
        try {
            mWorkingSocket.receive(tReceivingPacket);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tReceivingPacket;
    }

    private static void sendPacket(GFGUDPProtocol pSendingProtocol){
        mByteBuffer = new byte[1024];
        try {
            mByteBuffer = Serializer.serialize(pSendingProtocol);
        } catch (IOException e) {
            e.printStackTrace();
        }
        DatagramPacket mSendingPacket = new DatagramPacket(mByteBuffer, mByteBuffer.length, mCurrentServerAddress , 4445);
        try {
            mWorkingSocket.send(mSendingPacket);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static{
        try {
            mWorkingSocket = new DatagramSocket();
            mCurrentServerAddress = InetAddress.getByName(mServerAddressName);
           // InetSocketAddress address = new InetSocketAddress("192.168.103.255", 4445);


        } catch (SocketException | UnknownHostException e) {
            e.printStackTrace();
        }

    }

    public void run(){
        try {
            calculatingConnection(new GFGUDPProtocol(GFGActionType.INIT));
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void calculating(GFGUDPProtocol pCalculateTask) throws IOException, ClassNotFoundException {
        mResultingCell = new GFGCell(pCalculateTask.mGFGCell.getUpBorder(), pCalculateTask.mGFGCell.getDownBorder(), pCalculateTask.mGFGCell.getAccuracy());
        CalculatingTask.initCalculationThreads(pCalculateTask.mGFGCell.getUpBorder(), pCalculateTask.mGFGCell.getDownBorder(), pCalculateTask.mGFGCell.getAccuracy());
        CalculatingTask.startAllThreads();
        CalculatingTask.join(mResultingCell);
        sendPacket(new GFGUDPProtocol(mResultingCell, 0, GFGActionType.SUM_RESULT_TASK));
        waitAndProcessingTask();
    }

    private void waitAndProcessingTask() throws IOException, ClassNotFoundException {
        GFGUDPProtocol mCalculateTask = (GFGUDPProtocol) Serializer.deserialize(waitPacket().getData());
        calculating(mCalculateTask);
    }

    private void calculatingConnection(GFGUDPProtocol pSendingMessage) throws IOException, ClassNotFoundException {
        sendPacket(pSendingMessage);
        waitAndProcessingTask();
    }

    public void disconnection() {
        sendPacket(new GFGUDPProtocol(GFGActionType.DISCONNECTION));
    }
}