package com.hoggen.sublimation.service.httpsevice.Impl;


import com.alibaba.dubbo.common.utils.StringUtils;
import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yx.pres.service.PresService;
import com.yx.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;

/**
 * 医网签服务接口
 *
 * @version V1.0
 * @ClassName: YwqServiceMethod
 * @author: ztb
 */
@Service("BaseServiceMethod")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Transactional
public class BaseServiceMethod {

	private final Logger log = LoggerFactory.getLogger(BaseServiceMethod.class);



}
