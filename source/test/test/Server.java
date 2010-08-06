package test;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class Server {
	public static void main(String args[]) {
		DatagramPacket read;
		DatagramSocket sock;

		byte[] read_buf;
		read_buf = new byte[1024];

		int count = 0;

		try {
			sock = new DatagramSocket(7070);
			read = new DatagramPacket(read_buf, read_buf.length);

			System.out.println("wait..");
			while (true) {
				sock.receive(read);
				count++;
				String temp = new String(read_buf, 0, read.getLength());
				System.out.println(count + " : " + temp.substring(0, 15));
			}

		} catch (SocketException e) {
			System.out.println("");
		} catch (IOException e) {
			System.err.println(e);
		}
	}
}