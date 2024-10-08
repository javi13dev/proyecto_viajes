package init;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity // Proporciona unas configuraciones por defecto.
@Configuration // Porque es una clase de configuración
public class SecurityConfig {
	
	@Value("${IP_HOST:localhost}")
	private String ipHost;
	
	@Bean
	public JdbcUserDetailsManager users() {
		DriverManagerDataSource ds=new DriverManagerDataSource();
		ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
		ds.setUrl("jdbc:mysql://"+ ipHost +":3306/springsecurity");
		ds.setUsername("root");
		ds.setPassword("root");
		JdbcUserDetailsManager jdbc=new JdbcUserDetailsManager(ds);
		jdbc.setUsersByUsernameQuery("select user,pwd,enabled from users where user=?");
		jdbc.setAuthoritiesByUsernameQuery("select user,rol from roles where user=?");
		return jdbc;
	} 

	// Teniendo los usuarios, se plantea cómo securizar los servicios.
	@Bean
	public SecurityFilterChain filter(HttpSecurity http) throws Exception{ // Indica dónde accede cada user.

		// securizado de maneara que solo usuarios de un determinado rol puedan utilizarlo
		http.csrf(c->c.disable())
		.authorizeHttpRequests(
				aut->aut.requestMatchers(HttpMethod.POST, "/reserva").hasRole("USERS")
				.anyRequest().permitAll()
				)
		.httpBasic(Customizer.withDefaults());
		return http.build();
	}
	
}