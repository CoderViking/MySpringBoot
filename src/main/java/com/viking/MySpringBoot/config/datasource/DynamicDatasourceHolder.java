package com.viking.MySpringBoot.config.dynamicDatasource;

/**
 * Created by Viking on 2019/5/9
 */
public class DynamicDatasourceHolder {
    private static final ThreadLocal<String> THREAD_DATASOURCE_HOLDER = new ThreadLocal<>();
    // 设置数据源名
    public static void setDatasource(String key) {
        THREAD_DATASOURCE_HOLDER.set(key);
    }

    // 获取数据源名
    public static String getDatasource() {
        return (THREAD_DATASOURCE_HOLDER.get());
    }

    // 清除数据源名
    public static void clearDatasource() {
        THREAD_DATASOURCE_HOLDER.remove();
    }
}
