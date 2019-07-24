package com.viking.MySpringBoot.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;

/**
 * Created by Viking on 2019/7/24
 */
//@Data
//@Accessors(chain = true)
@Document(indexName = "blog", type = "java")
public class Blog {
    private static final long serialVersionUID = 6320548148250372657L;


    @Id
    @Field(type = FieldType.Keyword)
    private String id;

    private String title;

    //@Field(type = FieldType.Date, format = DateFormat.basic_date)
//    @DateTimeFormat(pattern = "yyyy-MM-dd")
//    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date time;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
