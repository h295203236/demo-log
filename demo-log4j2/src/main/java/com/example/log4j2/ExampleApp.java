package com.example.log4j2;

import com.cbim.logging.AuditLog;
import com.cbim.logging.CbimLoggerFactory;
import com.cbim.logging.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ExampleApp {
    // runtime logger
    static final Logger logger = LoggerFactory.getLogger(ExampleApp.class);
    // audit test
    //static final Logger auditLogger = LoggerFactory.getLogger("auditLogger");
    static final CbimLoggerFactory auditLogger = CbimLoggerFactory.getAuditLogger("auditLogger");
    // xxx business test
    static final Logger xxxBusLogger = LoggerFactory.getLogger("xxxBusinessLogger");

    public static void main(String[] args) {
        SpringApplication.run(ExampleApp.class, args);
        // runtime log
        logger.info("this is runtime log4j2");
        // audit log
        auditLogger.audit(AuditLog.builder("dms", "accountId", "operatorId", "UserLogin")
                .operator("zhangshan")
                .note("用户登录")
                .result(Result.FAILED)
                .exception("\"'Exception: safdsafdsfs'")
                .build());
        // business log
        xxxBusLogger.info("this is business log4j2.");
    }
}
