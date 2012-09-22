package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class Client {
	public static void main(String args[]) throws IOException {
		String temp = "bokaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa1234";
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		DatagramPacket write, read; // UDP 패킷객체터널생성
		DatagramSocket sock; // UDP 소켓 객체생성
		InetAddress ip = InetAddress.getByName("192.168.1.117");

		// 통신용 버퍼설정
		byte[] write_buf, read_buf;
		write_buf = new byte[1024];
		read_buf = new byte[1024];

		try {

			sock = new DatagramSocket(); // 소켓생성
			int count = 0;
			while (true) {
				temp = "";
				for (int i = 0; i < 1; i++) {
					temp += System.currentTimeMillis() + ": AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAa";
				}
				write_buf = temp.getBytes();

				// 서버에게 전달할 패킷생성
				write = new DatagramPacket(write_buf, write_buf.length, ip, 7070);
				sock.send(write); // 패킷전송
				Thread.sleep(1);
				count++;
				System.out.println(count + "전송");
			}
			// sock.close();
		} catch (SocketException e) {
			System.out.println("소켓생성오류");
		} catch (IOException e) {
			System.err.println(e);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}