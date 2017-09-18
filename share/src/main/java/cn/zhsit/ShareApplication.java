package cn.zhsit;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author Darren
 * @email 61947666@qq.com
 * @description:spring boot 启动类
 */
@EnableTransactionManagement
@SpringBootApplication
@EnableScheduling
@EnableCaching
@MapperScan({"cn.zhsit.*.daos"})
public class ShareApplication {
    private static Logger log = LoggerFactory.getLogger(ShareApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(ShareApplication.class, args);
    }

}
