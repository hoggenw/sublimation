package com.hoggen.sublimation.service.httpsevice.Impl;

import com.hoggen.sublimation.service.httpsevice.BaseService;
import com.alibaba.dubbo.config.annotation.Service;

import java.util.Map;

@Service
public class BaseServiceImpl implements BaseService {
    @Override
    public Map<String, Object> call(Map<String, Object> map) {
        return null;
    }
}
