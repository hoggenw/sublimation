
server.port=8099
#\u52A0\u5165o2o\u524D\u7F00
#server.servlet.context-path=/
#添加那个目录的文件需要restart
spring.devtools.restart.additional-paths=src/main/java
#排除那个目录的文件不需要restart
spring.devtools.restart.exclude=static/**,public/**

netty.server.port=6969

#==================== \u65E5\u5FD7 ============================
logging.config=classpath:logback.xml
#datasourceA
jdbc.driver=com.mysql.cj.jdbc.Driver
jdbc.url=jdbc:mysql://49.235.149.115:3306/7chat?useUnicode=true&characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B8
jdbc.username=WnplV/ietfQ=
jdbc.password=Vx4uJc2qZfJJnjkHs26fsw==

#是否允许跨越
custom.flag=true
mybatis_config_file =mybatis-config.xml
mapper_path =/mapper/**.xml
type_alias_package =com.hoggen.sublimation.entity

# Redis数据库索引（默认为0）
spring.redis.database=6
# Redis服务器地址
spring.redis.host=49.235.149.115
# Redis服务器连接端口
spring.redis.port=6379
# Redis服务器连接密码（默认为空）
spring.redis.password=Xinjin_611430
# 连接池最大阻塞等待时间（使用负值表示没有限制）
spring.redis.pool.max-wait=30000
# 连接池最大连接数（使用负值表示没有限制）
spring.redis.pool.max-active=1000
# 连接池中的最大空闲连接
spring.redis.pool.max-idle=20
# 连接池中的最小空闲连接
spring.redis.pool.min-idle=1
# 连接超时时间（毫秒）
spring.redis.timeout=15000

# Redis数据库索引（默认为0）
spring.local-redis.database=3
# Redis服务器地址
spring.local-redis.host=127.0.0.1
# Redis服务器连接端口
spring.local-redis.port=6379
# Redis服务器连接密码（默认为空）
spring.local-redis.password=

# 连接超时时间（毫秒）
spring.local-redis.timeout=15000




#上传文件配置

upload-config.department-path=ihosdev/department
upload-config.module-path=ihosdev/module
upload-config.doctor-portrait-path=ihosdev/doctorportrait
upload-config.doctor-qrcode-path=ihosdev/doctorqrcode
upload-config.nurse-qrcode-path=ihosdev/nurseqrcode
upload-config.coupon-qrcode-path=ihosdev/couponqrcode
upload-config.organization-qrcode-path=ihosdev/organizationqrcode
upload-config.user-portrait-path=ihosdev/userportrait
upload-config.hospital-path=ihosdev/hospital
upload-config.chat-path=ihosdev/chat
upload-config.public-access-path=http://inthosfiles.rc.schlwyy.com
upload-config.nurse-portrait-path=ihosdev/nurseportrait
upload-config.channel-qrcode-path=ihosdev/channelqrcode
upload-config.image-shuffling-path=ihosdev/imageshuffling
upload-config.return-visit-path=ihosdev/returnvisit
upload-config.multi-send-meesgae=ihosdev/multimessage
upload-config.first-diagnosis-path=ihosdev/first-diagnosis

qiniu-cloud.access-key=lwuiNQK07h_lCSXrg2E2lGHJIxxDnauMjNgxw5aL
qiniu-cloud.secret-key=fBp48kiyTN0T3O3fhoJZiC3mDGUCiTRKpqDPYbQK
qiniu-cloud.bucket=internethospital-dev
qiniu-cloud.domain=http://qnfilesdev.schlwyy.com




