package com.learningapp;

import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;

import java.sql.Connection;
import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Slf4j
class LearningAppApplicationTests {


  @Autowired
  DataSource dataSource;
   
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

}
