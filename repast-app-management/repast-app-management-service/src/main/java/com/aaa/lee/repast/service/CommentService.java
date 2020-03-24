package com.aaa.lee.repast.service;

import com.aaa.lee.repast.base.BaseService;
import com.aaa.lee.repast.mapper.CommentMapper;
import com.aaa.lee.repast.mapper.MemberMapper;
import com.aaa.lee.repast.model.Comment;
import com.aaa.lee.repast.model.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import static com.aaa.lee.repast.status.StaticCode.*;
import static com.aaa.lee.repast.status.StatusEnums.*;
import static com.aaa.lee.repast.status.LoginStatus.*;
/**
 * @Company
 * @Author 杨盼灵
 * @Date 2020/3/24
 * @Description
 **/
@Service
public class CommentService extends BaseService<Comment> {
    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private MemberMapper memberMapper;
    /**
     *      保存用户评价的信息
     */
    public Map<String , Object> saveComment(Comment comment, String token){
        //验证用户是否存在
        Member member = memberMapper.selectMemberByToken(token);
        //用户是否已登录
        Map<String ,Object> map = new HashMap<>();
        if (null!=member){
            //登录成功（具有可以执行的权限）
            //判断是否执行成功
            if (super.add(comment)>ZERO){
                map.put(CODE,INSERT_OPERATION.getCode());
                map.put(MSG,INSERT_OPERATION.getMsg());
                map.put(TOKEN,token);
                return map;
            }else {
                map.put(CODE,FAILED.getCode());
                map.put(MSG,FAILED.getMsg());
                map.put(TOKEN,token);
                return map;
            }
        }
        map.put(CODE,LOGOUT_WRONG.getCode());
        map.put(MSG,LOGOUT_WRONG.getMsg());
        return map;
    }
}
