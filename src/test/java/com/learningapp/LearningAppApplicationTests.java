package com.learningapp;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

import java.sql.Connection;
import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Slf4j
class LearningAppApplicationTests {



  DataSource dataSource = dataSource ();
   
   @BeforeEach
   void setUp(){
   }


  @Test
  void connectToDataBaseTest(){
    assertThat (dataSource).isNotNull ();
    log.info ( "data source properties------{}", dataSource );
    try{
      Connection connection = dataSource.getConnection ();
      assertThat ( connection ).isNotNull ();
      log.info ( "database{}", connection.getCatalog () );
    } catch (SQLException e) {
      log.info ( "An exception occurred{}", e.getMessage () );

    }

  }

  @Bean
  public DataSource dataSource() {
    DriverManagerDataSource dataSource = new DriverManagerDataSource();
    dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
    dataSource.setUrl("jdbc:mysql://localhost:3306/learning_app_db");
    dataSource.setUsername("root");
    dataSource.setPassword("HUNters007");

    return dataSource;
  }

}
