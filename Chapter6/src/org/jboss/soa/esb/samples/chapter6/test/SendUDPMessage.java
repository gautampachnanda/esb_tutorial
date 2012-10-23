package org.jboss.soa.esb.samples.chapter6.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class SendUDPMessage {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		ResponseReceiver receiver = new ResponseReceiver();
		new Thread(receiver).start();
		
		DatagramSocket socket = new DatagramSocket();
		socket.setSoTimeout(3000);
		String msg = "Hello UDP Gateway!";
		DatagramPacket packet = new DatagramPacket(msg.getBytes(), msg.getBytes().length);
		InetAddress address = InetAddress.getByName("localhost");
		packet.setAddress(address);
		packet.setPort(9999);
		socket.send(packet);
		socket.close();
	}

	private static class ResponseReceiver implements Runnable {

		public void run() {
			try {
				ServerSocket receiveSocket = new ServerSocket(8899);
				Socket clientSocket = receiveSocket.accept();
		        BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		        System.out.println(reader.readLine());
		        reader.close();
		        clientSocket.close();
		        receiveSocket.close();	
			} catch (IOException e) {
				e.printStackTrace();
			}		
		}		
	}
}
