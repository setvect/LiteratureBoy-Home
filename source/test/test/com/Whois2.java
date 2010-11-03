package test.com;

import java.io.IOException;

import com.setvect.common.http.HttpPageGetter;

public class Whois2 {
	public static void main(String[] args) throws IOException {
		HttpPageGetter getter = new HttpPageGetter("http://whois.nic.or.kr/result.php", "euc-kr", "euc-kr");
		getter.setParameter("domain_name", "168.126.63.1");
		getter.setMethod(HttpPageGetter.METHOD_POST);
		String s = getter.getBody();
		System.out.println(s);
	}
}
