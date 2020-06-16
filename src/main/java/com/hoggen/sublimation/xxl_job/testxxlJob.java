package com.hoggen.sublimation.xxl_job;

import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.XxlJob;
import com.xxl.job.core.log.XxlJobLogger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

import static com.xxl.job.core.biz.model.ReturnT.SUCCESS;

/** 测试 */

@Component
public class testxxlJob {
    private static final Logger logger = LoggerFactory.getLogger(testxxlJob.class);
    @XxlJob("jobDemo")
    public ReturnT<String> jobDemo(String param) throws Exception {
        logger.info("XXL-JOB, Hello World.");

        for (int i = 0; i < 5; i++) {
            logger.info("beat at:" + i);
            TimeUnit.SECONDS.sleep(2);
        }
        return SUCCESS;
    }

}
