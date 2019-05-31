package com.viking.MySpringBoot.baidu;

import java.util.ArrayList;
/**
 * Created by yanshuai on 2019/5/28
 */
public class KmeansTest {
    public  static void main(String[] args)
    {
        //初始化一个Kmean对象，将k置为10
        KMeansCluster1 k=new KMeansCluster1(3);
        ArrayList<float[]> dataSet=new ArrayList<float[]>();

        dataSet.add(new float[]{1,2});
        dataSet.add(new float[]{3,3});
        dataSet.add(new float[]{3,4});
        dataSet.add(new float[]{5,6});
        dataSet.add(new float[]{8,9});
        dataSet.add(new float[]{4,5});
        dataSet.add(new float[]{6,4});
        dataSet.add(new float[]{3,9});
        dataSet.add(new float[]{5,9});
        dataSet.add(new float[]{4,2});
        dataSet.add(new float[]{1,9});
        dataSet.add(new float[]{7,8});
        dataSet.add(new float[]{1,1});
        dataSet.add(new float[]{2,1});
        dataSet.add(new float[]{2,2});
        dataSet.add(new float[]{6,9});

        //设置原始数据集
        k.setDataSet(dataSet);
        //执行算法
        k.execute();
        //得到聚类结果
        ArrayList<ArrayList<float[]>> cluster=k.getCluster();
        //查看结果
        for(int i=0;i<cluster.size();i++)
        {
            k.printDataArray(cluster.get(i), "cluster["+i+"]");
        }

    }
}
