package com.viking.MySpringBoot.config.datasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * Created by Viking on 2019/5/9
 * 动态数据源
 */
public class DynamicDatasource extends AbstractRoutingDataSource {
    //默认数据源
    public static final String DEFAULT = "linuxServer";
    private static DynamicDatasource instance;
    private static final byte[] lock = new byte[0];

    private DynamicDatasource (){}
    public static synchronized DynamicDatasource getInstance(){//双重锁同步，返回单例对象
        if (instance==null){
            synchronized (lock){
                if (instance==null){
                    instance = new DynamicDatasource();
                }
            }
        }
        return instance;
    }
    @Override
    protected Object determineCurrentLookupKey() {
        return DynamicDatasourceHolder.getDatasource();
    }

}
