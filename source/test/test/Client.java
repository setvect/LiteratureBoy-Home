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

		DatagramPacket write, read; // UDP ��Ŷ��ü�ͳλ���
		DatagramSocket sock; // UDP ���� ��ü����
		InetAddress ip = InetAddress.getByName("192.168.1.117");

		// ��ſ� ���ۼ���
		byte[] write_buf, read_buf;
		write_buf = new byte[1024];
		read_buf = new byte[1024];

		try {

			sock = new DatagramSocket(); // ���ϻ���
			int count = 0;
			while (true) {
				temp = "";
				for (int i = 0; i < 1; i++) {
					temp += System.currentTimeMillis() + ": AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAa";
				}
				write_buf = temp.getBytes();

				// �������� ������ ��Ŷ����
				write = new DatagramPacket(write_buf, write_buf.length, ip, 7070);
				sock.send(write); // ��Ŷ����
				Thread.sleep(1);
				count++;
				System.out.println(count + "����");
			}
			// sock.close();
		} catch (SocketException e) {
			System.out.println("���ϻ�������");
		} catch (IOException e) {
			System.err.println(e);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}