package kr.co.gidion.board.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.sql.DataSource;

@Configuration
// properties 설정 파일들을 명시한다. (복수개 가능)
@PropertySource("classpath:/application.properties")
public class DatabaseConfig {

    @Autowired
    private ApplicationContext applicationContext;

    // application.properties 에 설정했던 데이터베이스 관련 정보를 사용하도록 지정한다.
    @Bean
    // prefix 를 spring.datasource 라고 사용했으므로
    // spring.datasource 로 시작하는 설정을 이용한다.
    @ConfigurationProperties(prefix = "spring.datasource")
    public HikariConfig hikariConfig() {
        return new HikariConfig();
    }

    @Bean
    // hikariConfig 를 사용해서 데이터베이스와
    // 연결하는 DataSource 를 생성한다.
    public DataSource dataSource() throws Exception {
        DataSource dataSource = new HikariDataSource(hikariConfig());
        System.out.println(dataSource.toString());

        return dataSource;
    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();

        sqlSessionFactoryBean.setDataSource(dataSource);
        sqlSessionFactoryBean.setConfigLocation(applicationContext.getResource("classpath:/config/mybatis-config.xml"));
        sqlSessionFactoryBean.setMapperLocations(applicationContext.getResources("classpath:/mapper/**/*Mapper.xml"));

        return sqlSessionFactoryBean.getObject();
    }

}
