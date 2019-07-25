package com.viking.MySpringBoot.repository;

import com.viking.MySpringBoot.entity.Blog;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

/**
 * Created by Viking on 2019/7/24
 */
public interface BlogRepository extends ElasticsearchRepository<Blog, String> {
    List<Blog> findByTitle(String title);
    @Query("{\"match_phrase\":{\"title\":\"?0\"}}")
    List<Blog> findByTitleLike(String title);
}
