package id.co.bni.ets.oauth.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(basePackages = "id.co.bni.ets.oauth.repo",
        entityManagerFactoryRef = "baseEntityManagerFactory", transactionManagerRef = "baseTransactionManager")
public class BaseDataSourceConfiguration {

    @Bean
    @ConfigurationProperties("spring.datasource-base")
    public DataSource baseDataSource() {
        return DataSourceBuilder.create()
                .build();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean baseEntityManagerFactory(EntityManagerFactoryBuilder builder) {
        return builder.dataSource(baseDataSource())
                .packages("id.co.bni.ets.lib.model.entity")
                .persistenceUnit("base")
                .build();
    }

    @Bean
    public PlatformTransactionManager baseTransactionManager(
            @Qualifier("baseEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
        final JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);
        return transactionManager;
    }
}
