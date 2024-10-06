package com.refactoring.finalproject;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

import java.sql.Connection;
import java.sql.DriverManager;

import static org.springframework.test.util.AssertionErrors.fail;

@WebAppConfiguration
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/servlet-context.xml","file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class FinalProjectTests {

    @Test
    public void TestConnectDB() {
        try (Connection conn =
                     DriverManager.getConnection(
                             "jdbc:oracle:thin:@refactoringprojectdb_high?TNS_ADMIN=/Users/apple/Documents/OCI-Wallet/Wallet_RefactoringProjectDB",
                             "ADMIN",
                             "Tkddyd123456")) {
            System.out.println(conn);
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }
}
