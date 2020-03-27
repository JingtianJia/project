package com.aaa.lee.repast.mapper;

import com.aaa.lee.repast.model.Member;
import tk.mybatis.mapper.common.Mapper;

public interface MemberMapper extends Mapper<Member> {

    Member selectMemberByOpenId(String openId);

    Member selectMemberByToken(String token);

    //根据主键查询用户积分
    Member selectMemberIdByIntegration(int Id);

    //根据会员信息增加积分，和历史总积分
    Integer updateMemberIntegration(Member member);

    //根据会员信息减少积分
    Integer updateMemberReduceIntegration(Member member);
}