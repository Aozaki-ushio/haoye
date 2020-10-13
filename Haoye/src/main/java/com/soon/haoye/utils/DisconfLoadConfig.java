package dmzsmos.utils;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

@Configuration
@Profile("prod")
@PropertySource(value="common.properties", factory = DisconfPropertySourceFactory.class)
public class DisconfLoadConfig {
	
}