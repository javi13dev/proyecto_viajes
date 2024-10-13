package init;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import javax.sql.DataSource;

@Configuration
public class VuelosDataSourceConfig {

    @Value("${DATABASE_URL}")  // URL de la base de datos de vuelos
    private String vuelosDatabaseUrl;

    @Value("${USUARIO_BBDD}")
    private String dbUser;

    @Value("${PASS_BBDD}")
    private String dbPassword;

    // Configuración del DataSource para la base de datos de vuelos
    @Bean
    public DataSource vuelosDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl(vuelosDatabaseUrl);  // Conexión a la DB de vuelos
        dataSource.setUsername(dbUser);
        dataSource.setPassword(dbPassword);
        return dataSource;
    }
}

