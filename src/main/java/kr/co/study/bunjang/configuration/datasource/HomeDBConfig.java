package kr.co.study.bunjang.configuration.datasource;

import java.util.Map;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.hibernate.cfg.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateProperties;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateSettings;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariDataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
    entityManagerFactoryRef = "homeEntityManager"
  , transactionManagerRef = "homeTransactionManager"
  , basePackages = "kr.co.study.bunjang.mvc.domain.home.repository")
public class HomeDBConfig {

    @Autowired
    private JpaProperties jpaProperties;

    @Autowired
    private HibernateProperties hibernateProperties;

    @Primary
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.home")
    public DataSource homeDataSource() {
        return DataSourceBuilder.create().type(HikariDataSource.class).build();
    }

    @Primary
    @Bean(name = "homeEntityManager")
    public LocalContainerEntityManagerFactoryBean homeEntityManager(EntityManagerFactoryBuilder builder) {
        Map<String, Object> properties = hibernateProperties.determineHibernateProperties(jpaProperties.getProperties(),
                new HibernateSettings());

        properties.put(Environment.DIALECT, "org.hibernate.dialect.MariaDBDialect");

        return builder.dataSource(homeDataSource())
                .properties(properties)
                .packages("kr.co.study.bunjang.mvc.domain.home.model")
                .build();
    }

    @Primary
    @Bean(name = "homeTransactionManager")
    public PlatformTransactionManager homeTransactionManager(
            @Qualifier("homeEntityManager") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}
