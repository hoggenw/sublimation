package com.hoggen.sublimation.service.httpsevice;

import java.util.Map;

/**
* 基础服务者
*
* @version V1.0
* @ClassName: YwqServiceMethod
* @author: ztb
*/
public interface BaseService {
   public Map<String, Object> call(Map<String, Object> map);
}
