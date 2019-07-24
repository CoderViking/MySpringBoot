package com.viking.MySpringBoot.repository;

import com.viking.MySpringBoot.entity.Blog;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Created by Viking on 2019/7/24
 */
public interface BlogRepository extends ElasticsearchRepository<Blog, String> {
}
