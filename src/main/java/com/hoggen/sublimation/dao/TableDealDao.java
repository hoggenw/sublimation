package com.hoggen.sublimation.dao;

import io.lettuce.core.dynamic.annotation.Param;

public interface TableDealDao {

    int existTable(String tableName);

    int dropTable(@Param(value = "tableName")String tableName);

    int createNewTable(@Param(value = "tableName")String tableName);

}
