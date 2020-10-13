package dmzsmos.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.PropertySourceFactory;
import org.springframework.core.io.support.ResourcePropertySource;
import org.springframework.util.ResourceUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Properties;

public class DisconfPropertySourceFactory implements PropertySourceFactory {

	private static Logger logger = LoggerFactory.getLogger(DisconfPropertySourceFactory.class);
	private static final String DISCONF_NAME = "disconf.properties";

	private static String[] HOSTS;
	private static String VERSION = "2_0_0";
	private static String APP = "uyun";
	private static String ENV = "local";

	static {
		Properties properties = new Properties();
		String disconfPath = System.getProperty("spring.config.location");
		Resource resource = null;
		if(disconfPath != null && disconfPath.trim().length() != 0){
			disconfPath += "/" + DISCONF_NAME;
		}
		else {
			disconfPath = ResourceUtils.CLASSPATH_URL_PREFIX + DISCONF_NAME;
		}
		PathMatchingResourcePatternResolver p  = new PathMatchingResourcePatternResolver();
		resource = p.getResource(disconfPath);

		InputStream in = null;
		try {
			in = resource.getInputStream();
			properties.load(in);
			HOSTS = properties.getProperty("disconf.conf_server_host").split(",");
			VERSION = properties.getProperty("disconf.version");
			APP = properties.getProperty("disconf.app");
			ENV = properties.getProperty("disconf.env");
		} catch (IOException e) {
			logger.warn("Load disconf.properties failed, read properties from env.");
			HOSTS = System.getProperty("disconf.conf_server_host").split(",");
		} finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (IOException ignore) {
			}
		}
	}

	@Override
	public PropertySource<?> createPropertySource(String name, EncodedResource resource) throws IOException {
		String fileName = resource.getResource().getFilename();
		ResourcePropertySource r = null;
		for (String host : HOSTS) {
			String url = String.format("http://%s/api/config/file?app=%s&env=%s&type=0&version=%s&key=%s", host, APP, ENV, VERSION, fileName);
			URI uri;
			try {
				uri = new URI(url);
				InputStream is = uri.toURL().openStream();
				r = new ResourcePropertySource(new InputStreamResource(is));
				break;
			} catch (URISyntaxException | IOException e) {
				logger.warn("download url:{} failed.", url, e);
				continue;
			}
		}
		return r;
	}

}
