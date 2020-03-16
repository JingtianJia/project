package com.aaa.lee.repast.service;

import com.aaa.lee.repast.base.BaseService;
import com.aaa.lee.repast.base.ResultData;
import com.aaa.lee.repast.mapper.MemberMapper;
import com.aaa.lee.repast.mapper.PmsCommentMapper;
import com.aaa.lee.repast.model.Integration;
import com.aaa.lee.repast.model.Member;
import com.aaa.lee.repast.model.PmsComment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.aaa.lee.repast.status.StatusEnums.FAILED;
import static com.aaa.lee.repast.status.StatusEnums.SUCCESS;

@Service
public class PmsCommentService  extends BaseService<PmsComment> {
    @Autowired
    private PmsCommentMapper pmsCommentMapper;
    @Autowired
    private MemberMapper memberMapper;

    public ResultData byshopidselectpms(@RequestParam String token, int shopid){
        System.out.println(token+"+++"+shopid);
        ResultData resultData=new ResultData();
        Member member=memberMapper.selectMemberByToken(token);
        if (null !=  member) {
            Map map1=new HashMap<String,Integer>();
            resultData.setCode(SUCCESS.getCode());
            resultData.setMsg(SUCCESS.getMsg());
            resultData.setDetail("查询此商品所有评论");
            map1.put("member",member);
            List<PmsComment> pmsComments=pmsCommentMapper.selectpmsCommentbyid(shopid);
            int i=0;
            for (PmsComment pmsComment:pmsComments){
                i++;
            }
            map1.put("num",i);
            Map map2=new HashMap<Integration,List>();
            map2.put("pmsComment",pmsComments);

            Map map=new HashMap<String,HashMap>();
            map.put("i",map1);
            map.put("j",map2);
            resultData.setData(map);
            return resultData;
        }
        resultData.setCode(FAILED.getCode());
        resultData.setMsg(FAILED.getMsg());
        resultData.setDetail("请登录后重试");
        return resultData;
        }
    }

