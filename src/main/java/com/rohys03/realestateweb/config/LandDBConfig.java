package com.rohys03.realestateweb.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages="com.rohys03.realestateweb.repository.landdb", sqlSessionFactoryRef = "db1SessionFactory")
public class LandDBConfig {

    private static final String DB1PREFIX = "spring.datasource.db1";

    @Bean(name= "db1DataSource")
    @ConfigurationProperties(prefix = LandDBConfig.DB1PREFIX)
    @Primary
    public DataSource db1DataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name= "db1SessionFactory")
    @Primary
    public SqlSessionFactory sqlSessionFactory(@Qualifier("db1DataSource") DataSource db1DataSource, ApplicationContext applicationContext) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(db1DataSource);
//        sqlSessionFactoryBean.setConfigLocation(new ClassPathResource("mybatis/mybatis-config.xml"));
        sqlSessionFactoryBean.setMapperLocations(applicationContext.getResources("classpath:mybatis/mapper/articleMapper.xml"));
        return sqlSessionFactoryBean.getObject();
    }

    @Bean(name = "db1SessionTemplate")
    @Primary
    public SqlSessionTemplate sqlSessionTemplate(@Qualifier("db1SessionFactory")SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    @Bean(name= "db1JdbcTemplate")
    @Primary
    public JdbcTemplate JdbcTemplate() {
        return new JdbcTemplate(db1DataSource());
    }

}
