package com.caifulif.whstudying.udp;

import android.content.Intent;
import android.util.Log;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 * Created by 皓 on 2018/3/23.
 */

public class UDPClient implements Runnable{
    final static int udpPort = 8600;
    final static String hostIp = "192.168.191.7";
    private static DatagramSocket socket = null;
    private static DatagramPacket packetSend,packetRcv;
    private boolean udpLife = true; //udp生命线程
    private byte[] msgRcv = new byte[1024]; //接收消息
    InetAddress hostAddress = null;

    public UDPClient(){
        super();
    }

    //返回udp生命线程因子是否存活
    public boolean isUdpLife(){
        if (udpLife){
            return true;
        }

        return false;
    }

    //更改UDP生命线程因子
    public void setUdpLife(boolean b){
        udpLife = b;
    }

    //发送消息
    public String send(String msgSend){
        try {
            hostAddress = InetAddress.getByName(hostIp);
        } catch (UnknownHostException e) {
            Log.i("udpClient","未找到服务器");
            e.printStackTrace();
        }
        packetSend = new DatagramPacket(msgSend.getBytes() , msgSend.getBytes().length,hostAddress,udpPort);

        try {
            socket.send(packetSend);
        } catch (IOException e) {
            e.printStackTrace();
            Log.i("udpClient","发送失败");
        }
        //   socket.close();
        return msgSend;
    }

    @Override
    public void run() {
        try {
            socket = new DatagramSocket();
            socket.setSoTimeout(3000);//设置超时为3s
        } catch (SocketException e) {
            Log.i("udpClient","建立接收数据报失败");
            e.printStackTrace();
        }
        packetSend = new DatagramPacket(msgRcv,msgRcv.length,hostAddress,udpPort);
        while (udpLife){
            try {
                Log.i("udpClient", "UDP监听");
                String RcvMsg = new String(packetSend.getData(),packetSend.getOffset(),packetSend.getLength());
                //将收到的消息发给主界面
                Intent RcvIntent = new Intent();
                RcvIntent.setAction("udpRcvMsg");
                RcvIntent.putExtra("udpRcvMsg", RcvMsg);
                UDPActivity.context.sendBroadcast(RcvIntent);
                socket.receive(packetSend);
                Log.i("Rcv",RcvMsg);
            }catch (IOException e){
                e.printStackTrace();
            }
        }

        Log.i("udpClient","UDP监听关闭");
        socket.close();
    }
}
