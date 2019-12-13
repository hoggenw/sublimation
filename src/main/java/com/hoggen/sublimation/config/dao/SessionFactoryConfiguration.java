package com.hoggen.sublimation.config.dao;

import java.io.IOException;

import javax.sql.DataSource;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

@Configuration
public class SessionFactoryConfiguration {
	@Autowired
	private DataSource dataSource;

	private static String mybatisConfigFile;

	@Value("${mybatis_config_file}")
	public void setMybatisConfigFile(String mybatisConfigFile) {
		SessionFactoryConfiguration.mybatisConfigFile = mybatisConfigFile;
	}

	private static String mapperPath;

	@Value("${mapper_path}")
	public void setMapperPath(String mapperPath) {
		SessionFactoryConfiguration.mapperPath = mapperPath;
	}

	private String typeAliasPackage;

	@Value("${type_alias_package}")
	public void setTypeAliasPackage(String typeAliasPackage) {
		this.typeAliasPackage = typeAliasPackage;
	}

	@Bean(name = "sqlSessionFactory")
	public SqlSessionFactoryBean creatSqlSessionFactoryBean() throws IOException {

		SqlSessionFactoryBean ssfb = new SqlSessionFactoryBean();
		// 设置mybatisConfig 扫描路径
		ssfb.setConfigLocation(new ClassPathResource(mybatisConfigFile));
		// 添加mapper扫描路径
		PathMatchingResourcePatternResolver pathMatchingResourcePatternResolver = new PathMatchingResourcePatternResolver();
		String packPath = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX + mapperPath;
		ssfb.setMapperLocations(pathMatchingResourcePatternResolver.getResources(packPath));
		// 设置datasource
		ssfb.setDataSource(dataSource);
		// 设置扫描路径
		ssfb.setTypeAliasesPackage(typeAliasPackage);
		return ssfb;
	}
}
