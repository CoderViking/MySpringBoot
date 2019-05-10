package com.viking.MySpringBoot.config.dynamicDatasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.lang.Nullable;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Viking on 2019/5/9
 * 动态数据源
 */
public class DynamicDatasource extends AbstractRoutingDataSource {
    //默认数据源
    public static final String DEFAULT = "springboot";
    private static DynamicDatasource instance;
    private static final byte[] lock = new byte[0];
    private static Map<Object,Object> datasourceMap = new HashMap<>();

    @Override
    public void setTargetDataSources(Map<Object, Object> targetDataSources) {
        super.setTargetDataSources(targetDataSources);
        datasourceMap.putAll(targetDataSources);
        super.afterPropertiesSet();
    }

    public Map<Object,Object> getDatasourceMap(){
        return datasourceMap;
    }

    @Nullable
    @Override
    protected Object determineCurrentLookupKey() {
        String key = DatasourceContextHolder.getDB();
        return key;
    }
    private DynamicDatasource (){}

    public static synchronized DynamicDatasource getInstance(){
        if (instance==null){
            synchronized (lock){
                if (instance==null){
                    instance = new DynamicDatasource();
                }
            }
        }
        return instance;
    }

}
