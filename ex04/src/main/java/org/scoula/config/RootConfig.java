package org.scoula.config;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.mybatis.spring.annotation.MapperScan;
import javax.sql.DataSource;
@Configuration
@MapperScan(basePackages = {"org.scoula.mapper"})
//인터페이스 자동 구현
@PropertySource({"classpath:/application.properties"})
public class RootConfig {
    @Value("${jdbc.driver}") String driver;
    @Value("${jdbc.url}") String url;
    @Value("${jdbc.username}") String username;
    @Value("${jdbc.password}") String password;

    @Autowired
    ApplicationContext applicationContext;
    //jsp-servlet 할 때 ServletContext -> application Scope 라고 생각하자

    @Bean
    public DataSource dataSource() {
        HikariConfig config = new HikariConfig();
        config.setDriverClassName(driver);
        config.setJdbcUrl(url);
        config.setUsername(username);
        config.setPassword(password);
        HikariDataSource dataSource = new HikariDataSource(config);
        return dataSource;
    }
    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
        sqlSessionFactory.setConfigLocation(applicationContext.getResource("classpath:/mybatis-config.xml"));
        sqlSessionFactory.setDataSource(dataSource()); //ConnectionPool 설정
        return (SqlSessionFactory) sqlSessionFactory.getObject();
    }
    //mybatis-config.xml 이 어디에 있는지 알려주는 것을 확인.
    //ConnectionPool 이 누구인지 확인.
    @Bean
    public DataSourceTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dataSource());
    }
    //트랜잭션 처리 확인
    //DataSourceTransactionManager ConnectionPool 이 누구인지 확인.

}//Context map dataSource Bean이 등록 안 되어 있으면 생성
//데이터 호출 -> dataSource를 확인 -> 싱글톤패턴으로 운영된다.