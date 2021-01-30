package com.xybbz.blog.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xybbz.auth.entity.UserBlog;
import com.xybbz.auth.service.UserBlogService;
import com.xybbz.blog.entity.Blog;
import com.xybbz.blog.entity.BlogReply;
import com.xybbz.mapper.BlogDAO;
import com.xybbz.blog.service.BlogReplyService;
import com.xybbz.blog.service.BlogService;
import com.xybbz.blog.vo.BlogVO;
import com.xybbz.util.BaseEntity;
import com.xybbz.util.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * <p>
 * 博客表 服务实现类
 * </p>
 *
 * @author liu
 * @since 2021-01-10
 */
@Service
public class BlogServiceImpl extends BaseServiceImpl<BlogDAO, Blog> implements BlogService {

    @Autowired
    private UserBlogService userBlogService;

    @Autowired
    private BlogReplyService blogReplyService;

    @Autowired
    private BlogDAO blogDAO;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean addBlog(BlogVO blogVO) {
        return super.save(blogVO);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean removeBlog(List<Long> ids) {
        return super.deleteList(ids);
    }

    @Override
    @Transactional
    public boolean updateBlog(BlogVO blogVO) {
        return super.updateById(blogVO);
    }

    @Override
    public IPage<BlogVO> blogPage(BlogVO blogVO, IPage<Blog> fistPage) {
        IPage<Blog> page = page(fistPage, new LambdaQueryWrapper<>(blogVO));
        List<BlogVO> collect = page.getRecords().stream().map(this::copyBlogVO).collect(Collectors.toList());

        IPage<BlogVO> pageVo = new Page(page.getCurrent(), page.getSize(), page.getTotal());
        pageVo.setRecords(collect);
        return pageVo;
    }

    private BlogVO copyBlogVO(Blog blog) {

        BlogVO blogVO = BeanUtil.copyProperties(blog, BlogVO.class);

        UserBlog userBlog = userBlogService.getById(blogVO.getBlogAuthorId());

        if (Objects.nonNull(userBlog)) {
            blogVO.setUserName(userBlog.getUserName());
            blogVO.setUserIcon(userBlog.getUsrAvatar());
        }

        //获得最后发帖人

        BlogReply blogReply = blogReplyService.lambdaQuery().eq(BlogReply::getBlogId, blogVO.getId())
                .orderByDesc(BlogReply::getReplyTime).last("limit 1").one();

        if (Objects.nonNull(blogReply)) {
            UserBlog userBlogNew = userBlogService.getById(blogReply.getReplyUserId());
            if (Objects.nonNull(userBlogNew)) {
                blogVO.setLastReply(userBlogNew.getUserName());
            }
            blogVO.setLastTime(blogReply.getReplyTime());
        }
        long nanoTime = System.nanoTime();
        Integer count = blogReplyService.lambdaQuery().eq(BlogReply::getBlogId, blogVO.getId()).count();
        blogVO.setReplayContent(Long.valueOf(count));
        long toMillis = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - nanoTime);
        System.out.println(toMillis);
        return blogVO;
    }

    @Override
    public BlogVO detailedBlog(Long fistLong) {
        return copyBlogVO(getById(fistLong));
    }

    @Override
    public Blog newsBlog(Long fistLong) {
        List<Blog> list = lambdaQuery().eq(Blog::getBlogSortId, fistLong).orderByAsc(BaseEntity::getCreateTime).list();
        return list.get(0);
    }

    @Override
    public IPage<BlogVO> sortListPage(String sortName,IPage<Blog> page) {

        List<Blog> blogs = blogDAO.selectBySortName(sortName, page);

        List<BlogVO> collect = blogs.stream().map(this::copyBlogVO).collect(Collectors.toList());
//        List<BlogVO> collect = blogs.stream().map(ite -> BeanUtil.copyProperties(ite, BlogVO.class)).collect(Collectors.toList());

        IPage<BlogVO> pageVo = new Page(page.getCurrent(), page.getSize(), page.getTotal());

        pageVo.setRecords(collect);
        return pageVo;
    }
}
