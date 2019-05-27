package com.viking.MySpringBoot.baidu.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.viking.MySpringBoot.baidu.entity.Apad;
import com.viking.MySpringBoot.baidu.mapper.BaiduMapper;
import com.viking.MySpringBoot.utils.BaiduMapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Viking on 2019/5/27
 * 地理位置编码
 */
@RestController
@RequestMapping("baidu")
public class TestAddressCodeController {
    @Value("${baidu.ak}")
    private String yourak;
    @Value("${baidu.sk}")
    private String yoursk;

    @Autowired
    private BaiduMapper mapper;

    @RequestMapping("code")
    public Object code(){
        List<Map<String, Object>> result = new ArrayList<>();
        for (int pageNum=1;pageNum<=5;pageNum++) {
            PageHelper.startPage(pageNum, 100);
            List<Apad> list = mapper.getApadList();
            PageInfo<Apad> pageInfo = new PageInfo<>(list);
            List<Apad> apads = pageInfo.getList();
            for (Apad bean : apads) {
                try {
                    Map<String, Object> map = BaiduMapUtils.getLocation(yourak, yoursk, bean.apad, bean.cty);
                    map.put("ap", bean.ap);
                    map.put("apid", bean.apid);
                    map.put("ctr", bean.ctr);
                    map.put("apad", bean.apad);
                    map.put("cty", bean.cty);
                    result.add(map);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                }
            }
        }
        mapper.insertResult(result);
        return result;
    }
}
