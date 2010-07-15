package test.com.setvect.config;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.junit.Before;
import org.junit.Test;

public class ConfigurationTest {
	private static final String FILE = "E:\\myworkspace2\\literatureboy\\WebContent\\WEB-INF\\config.propertise";
	private static PropertiesConfiguration config;

	@Before
	public void load() {
		try {
			config = new PropertiesConfiguration();

			// ������Ƽ ���� �ε�
			config.load(FILE);
		} catch (org.apache.commons.configuration.ConfigurationException e) {
			System.err.println(e.getMessage());
		}
	}

	@Test
	public void outConfig() throws ConfigurationException {
		System.out.println(config.getString("dog"));
		System.out.println(config.getString("jang"));
		System.out.println(config.getString("hi"));
	}
}
