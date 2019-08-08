//package com.viking.MySpringBoot.controller;
//
//import com.viking.MySpringBoot.entity.Blog;
//import com.viking.MySpringBoot.exception.MyException;
//import com.viking.MySpringBoot.repository.BlogRepository;
//import com.viking.MySpringBoot.response.ResponseCode;
//import org.apache.commons.lang3.StringUtils;
//import org.elasticsearch.index.query.QueryBuilders;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
//import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
//import org.springframework.data.elasticsearch.core.query.SearchQuery;
//import org.springframework.web.bind.annotation.*;
//
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//
///**
// * Created by Viking on 2019/7/24
// * 测试Es使用
// */
//@RestController
//@RequestMapping("blog")
//public class BlogController {
//
//    @Autowired
//    private BlogRepository blogRepository;
//
//    /**
//     * 添加数据到es
//     * @param blog 参数对象
//     * @return 响应
//     */
//    @PostMapping("add")
//    public Object add(Blog blog){
////        Blog blog = new Blog();
////        blog.s
//        try {
//            blog.setTime(new SimpleDateFormat("yyyy-MM-dd").parse("2019-07-25"));
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        blogRepository.save(blog);
//        return "Ok";
//    }
//
//    /**
//     * 通过id查询es中的数据
//     * @param id 参数id
//     * @return 数据结果
//     */
//    @GetMapping("get/{id}")
//    public Object getById(@PathVariable String id){
//        if (StringUtils.isBlank(id)) throw new MyException(ResponseCode.NOPA,"id不能为空");
//        Optional<Blog> blogOptional = blogRepository.findById(id);
//        if (blogOptional.isPresent()){
//            Blog blog = blogOptional.get();
//            System.out.println(blog);
//            return blog;
//        }
//        throw new MyException();
//    }
//
//    /**
//     * 查询所有数据
//     * @return 返回查询结果
//     */
//    @GetMapping("get")
//    public Object getAll(){
//        Iterable<Blog> blogs = blogRepository.findAll();
//        List<Blog> list = new ArrayList<>();
//        blogs.forEach(list::add);
//        return list;
//    }
//
//    /**
//     * 通过id修改数据
//     * @param blog 参数对象
//     * @return 响应
//     */
//    @PostMapping("update")
//    public Object updateById(Blog blog){
//        String id = blog.getId();
//        if (StringUtils.isBlank(id)) throw new MyException(ResponseCode.ERRPA,"id不能为空");
//        blogRepository.save(blog);
//        return "Ok";
//    }
//
//    /**
//     * 根据id删除es数据
//     * @param id 参数id
//     * @return 响应
//     */
//    @DeleteMapping("delete/{id}")
//    public Object deleteById(@PathVariable String id){
//        if (StringUtils.isBlank(id)) throw new MyException(ResponseCode.NOPA,"id不能为空");
//        blogRepository.deleteById(id);
//        return "Ok";
//    }
//
//    /**
//     * 根据title查询数据
//     * @param title 参数title
//     * @return 查询结果
//     */
//    @GetMapping("find/title/{title}")
//    public Object findByTitle(@PathVariable String title){
//        return blogRepository.findByTitle(title);
//    }
//
//    /**
//     * 理论上的模糊查询
//     * @param title 参数title
//     * @return 查询结果
//     */
//    @GetMapping("find/titleLike/{title}")
//    public Object findByTitleLike(@PathVariable String title){
//        return blogRepository.findByTitleLike(title);
//    }
//
//    @Autowired
//    private ElasticsearchTemplate elasticsearchTemplate;
//
//    /**
//     * 使用es模板进行检索
//     * @param keyword 查询标题的关键字
//     * @return 查询结果
//     */
//    @GetMapping("search/title")
//    public Object searchTitle(String keyword){
//        SearchQuery query = new NativeSearchQueryBuilder().withQuery(QueryBuilders.queryStringQuery(keyword)).build();
//        List<Blog> list = elasticsearchTemplate.queryForList(query, Blog.class);
//        return list;
//    }
//}
