package com.example.log4j2;

import com.cbim.logging.AuditLog;
import com.cbim.logging.CbimLoggerFactory;
import com.cbim.logging.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
public class DemoController {

    static final Logger logger = LoggerFactory.getLogger(ExampleApp.class);
    static final CbimLoggerFactory AUDIT_LOGGER = CbimLoggerFactory.getAuditLogger("auditLogger");
    static final Logger xxxBusLogger = LoggerFactory.getLogger("xxxBusinessLogger");

    @GetMapping("/test1")
    public Object auditTest() {
        // runtime log
        logger.info("this is runtime logback");
        // audit log
        Random random = new Random();
        int rInt = random.nextInt();
        AUDIT_LOGGER.audit(AuditLog.builder("dms",
                        "accountId-" + rInt,
                        "operatorId-" + rInt,
                        "UserLogin")
                .operator("ZhangSan")
                .note("用户登录Log4j2")
                .result(Result.FAILED)
                .exception(new IllegalArgumentException("param error").toString())
                .build());
        // business log
        xxxBusLogger.info("this is business logback.");
        return "OK.";
    }

}
