package com.hoggen.sublimation.util;


import com.hoggen.sublimation.dao.TableDealDao;
import com.hoggen.sublimation.dao.UserDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.Calendar;
import java.util.Date;

@Configuration
@Component // 此注解必加
@EnableScheduling // 此注解必加'
@Repository
@Slf4j
public class YLSchedutask {

    @Value("${jdbc.driver}")
    private String jdbcDriver;
    @Value("${jdbc.url}")
    private String jdbcUrl;
    @Value("${jdbc.username}")
    private String jdbcUsername;
    @Value("${jdbc.password}")
    private String jdbcPassword;

    public void beginCreateTable() {

        log.info("time " + StringUtil.dateToStrYMD(getFirstWeakDay(getTomorrow(new Date()))));
//        String timeFail = StringUtil.dateToStrYMD(getFirstWeakDay(getTomorrow(new Date())));
//        String tableName = SqlUtil.creatFrendshipApplySql("tab_friendship_apply_" +timeFail);
//
//        try {
//            dropTable("tab_friendship_apply_" +timeFail,SqlUtil.dropTable("tab_friendship_apply_" +timeFail));
//            //createNewTable(SqlUtil.creatFrendshipApplySql(tableName));
//        }catch (Exception e){
//            log.error(e.toString());
//        }


    }


    private  void  createNewTable(String tableName) throws SQLException, ClassNotFoundException {


        //连接数据库
        Class.forName(jdbcDriver);
        //测试url中是否包含useSSL字段，没有则添加设该字段且禁用
        if( jdbcUrl.indexOf("?") == -1 ){
            jdbcUrl = jdbcUrl + "?useSSL=false" ;
        }
        else if( jdbcUrl.indexOf("useSSL=false") == -1 || jdbcUrl.indexOf("useSSL=true") == -1 )
        {
            jdbcUrl = jdbcUrl + "&useSSL=false";
        }
        Connection conn = DriverManager.getConnection(jdbcUrl, DESUtils.getDecryptString(jdbcUsername) ,  DESUtils.getDecryptString(jdbcPassword));
        Statement stat = conn.createStatement();
        //String tableName = SqlUtil.creatFrendshipApplySql("friendship_apply_" +timeFail);
        //获取数据库表名
        ResultSet rs = conn.getMetaData().getTables(null, null,tableName , null);

        // 判断表是否存在，如果存在则什么都不做，否则创建表
        if( rs.next() ){
            return;
        }
        else{
            // 先判断是否纯在表名，有则先删除表在创建表
//			stat.executeUpdate("DROP TABLE IF EXISTS sys_admin_divisions;CREATE TABLE sys_admin_divisions("
            //创建行政区划表
            stat.executeUpdate(tableName);
        }
        // 释放资源
        stat.close();
        conn.close();
    }


    private  void  dropTable(String tableName, String sqlString) throws SQLException, ClassNotFoundException {

        //连接数据库
        Class.forName(jdbcDriver);
        //测试url中是否包含useSSL字段，没有则添加设该字段且禁用
        if( jdbcUrl.indexOf("?") == -1 ){
            jdbcUrl = jdbcUrl + "?useSSL=false" ;
        }
        else if( jdbcUrl.indexOf("useSSL=false") == -1 || jdbcUrl.indexOf("useSSL=true") == -1 )
        {
            jdbcUrl = jdbcUrl + "&useSSL=false";
        }
        Connection conn = DriverManager.getConnection(jdbcUrl, DESUtils.getDecryptString(jdbcUsername) ,  DESUtils.getDecryptString(jdbcPassword));
        Statement stat = conn.createStatement();
        //获取数据库表名
        ResultSet rs = conn.getMetaData().getTables(null, null,tableName , null);

        // 判断表是否存在，如果存在则什么都不做，否则创建表
        if( rs.next() ){
            stat.executeUpdate(sqlString);
            return;
        }

        // 释放资源
        stat.close();
        conn.close();
    }


    Date getFirstWeakDay(Date date) {
        Calendar ca = Calendar.getInstance();// 得到一个Calendar的实例
        ca.setTime(date);

        ca.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        Date lastDay = ca.getTime(); // 结果
        return lastDay;
    }

    /**
     * @Param 周六凌晨触发，新建周日表
     * @Author:hoggen
     * @Date:17:28 2019-11-25
     */
    Date getTomorrow(Date date) {
        Calendar ca = Calendar.getInstance();// 得到一个Calendar的实例
        ca.setTime(date);
        ;// 月份是从0开始的，
        ca.add(Calendar.DAY_OF_YEAR, + 1); // 日期加1
        Date lastDay = ca.getTime(); // 结果
        log.info("明天日期：" + StringUtil.dateToStrYMD(lastDay));
        return lastDay;
    }
}
