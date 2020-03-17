package com.aaa.lee.repast.service;

import com.aaa.lee.repast.base.BaseService;
import com.aaa.lee.repast.base.ResultData;
import com.aaa.lee.repast.mapper.MemberMapper;
import com.aaa.lee.repast.mapper.PmsCommentMapper;
import com.aaa.lee.repast.model.Integration;
import com.aaa.lee.repast.model.Member;
import com.aaa.lee.repast.model.PmsComment;
import com.aaa.lee.repast.utils.IPUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.aaa.lee.repast.status.StatusEnums.FAILED;
import static com.aaa.lee.repast.status.StatusEnums.SUCCESS;

@Service
public class PmsCommentService extends BaseService<PmsComment> {
    @Autowired
    private MemberMapper memberMapper;
    @Autowired
    private PmsCommentMapper pmsCommentMapper;

    public ResultData byShopIdSelectPms(@RequestParam String token, int shopId){

        ResultData resultData=new ResultData();
        Member member=memberMapper.selectMemberByToken(token);
        if (null !=  member) {
            Map map1=new HashMap<String,Integer>();
            resultData.setCode(SUCCESS.getCode());
            resultData.setMsg(SUCCESS.getMsg());
            resultData.setDetail("根据shopid查询评论");
            map1.put("member", member);
            int i=0;
            List<PmsComment> pmsComments = pmsCommentMapper.selectPmsCommentById(shopId);
            for (PmsComment pmsComment :pmsComments){
                ++i;
            }
            map1.put("条数",i);

            Map map2=new HashMap<String,List>();
            map2.put("pmsComment",pmsComments);

            Map map=new HashMap<String,HashMap>();
            map.put("条数",map1);
            map.put("查询结果",map2);
            resultData.setData(map);
            return resultData;
            }
            resultData.setCode(FAILED.getCode());
            resultData.setMsg(FAILED.getMsg());
            resultData.setDetail("请登录后重试");
            return resultData;
        }

        public ResultData byOrderIdSelectPms(@RequestParam String token, int orderId){

            ResultData resultData=new ResultData();
            Member member=memberMapper.selectMemberByToken(token);
            if (null !=  member) {
                Map map1=new HashMap<String,Integer>();
                resultData.setCode(SUCCESS.getCode());
                resultData.setMsg(SUCCESS.getMsg());
                resultData.setDetail("查询此订单所有评论");
                map1.put("member",member);
                List<PmsComment> pmsComments=pmsCommentMapper.selectPmsCommentByOrderId(orderId);
                int i=0;
                for (PmsComment pmsComment:pmsComments){
                    i++;
                }
                map1.put("num",i);
                Map map2=new HashMap<String,List>();
                map2.put("pmsComment",pmsComments);

                Map map=new HashMap<String,HashMap>();
                map.put("条数",map1);
                map.put("查询结果",map2);
                map.put("i",map1);
                map.put("j",map2);
                resultData.setData(map);
                System.out.println(map);
                return resultData;
            }
            resultData.setCode(FAILED.getCode());
            resultData.setMsg(FAILED.getMsg());
            resultData.setDetail("请登录后重试");
            return resultData;
        }

        public ResultData insertComment(@RequestParam String token, @RequestBody PmsComment pmsComment){
            ResultData resultData=new ResultData();
            Member member=memberMapper.selectMemberByToken(token);

            if (null !=  member) {
                //获取request对象
                HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
                // 获取用户的ip地址
                String ipAddr = IPUtil.getIpAddr(request);
                pmsComment.setMemberIp(ipAddr);
                Map map1=new HashMap<String,Integer>();
                resultData.setCode(SUCCESS.getCode());
                resultData.setMsg(SUCCESS.getMsg());
                resultData.setDetail("为商品添加评论");
                map1.put("member",member);
                int i=pmsCommentMapper.insertComment(pmsComment);
                map1.put("insert",i);
                resultData.setData(map1);
                System.out.println(map1);
                return resultData;
            }
            resultData.setCode(FAILED.getCode());
            resultData.setMsg(FAILED.getMsg());
            resultData.setDetail("请登录后重试");
            return resultData;

        }




        public ResultData deleteComment(@RequestParam String token, @RequestBody PmsComment pmsComment){
            ResultData resultData=new ResultData();
            Member member=memberMapper.selectMemberByToken(token);

            if (null !=  member) {
                //获取request对象
                HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
                // 获取用户的ip地址
                String ipAddr = IPUtil.getIpAddr(request);
                pmsComment.setMemberIp(ipAddr);
                Map map1=new HashMap<String,Integer>();
                resultData.setCode(SUCCESS.getCode());
                resultData.setMsg(SUCCESS.getMsg());
                resultData.setDetail("为此订单删除评论");
                map1.put("member",member);
                int i=pmsCommentMapper.deleteComment(pmsComment);
                map1.put("delete",i);
                resultData.setData(map1);
                System.out.println(map1);
                return resultData;
            }
            resultData.setCode(FAILED.getCode());
            resultData.setMsg(FAILED.getMsg());
            resultData.setDetail("请登录后重试");
            return resultData;

        }
}
