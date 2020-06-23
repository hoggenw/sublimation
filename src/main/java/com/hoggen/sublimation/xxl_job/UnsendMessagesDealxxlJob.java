package com.hoggen.sublimation.xxl_job;


import com.alibaba.dubbo.config.annotation.Reference;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.annotation.XxlJob;
import httpsevice.FriendshipApplyService;
import httpsevice.UnsendMessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import static com.xxl.job.core.biz.model.ReturnT.FAIL;
import static com.xxl.job.core.biz.model.ReturnT.SUCCESS;

@Component
public class UnsendMessagesDealxxlJob {
    @Reference
    private UnsendMessageService unsendMessageService;

    private static final Logger logger = LoggerFactory.getLogger(UnsendMessagesDealxxlJob.class);
    @XxlJob("UnsendMessagesDealxxlJob")
    public ReturnT<String> UnsendMessagesDealxxlJob(String param) throws Exception {

        logger.info("unsendMessage deal ");
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.DATE, -1);
        Date start = c.getTime();

        int reult = unsendMessageService.deleteMessages(start);
        if (reult <= 0){
            return FAIL;
        }

        return SUCCESS;
    }
}
