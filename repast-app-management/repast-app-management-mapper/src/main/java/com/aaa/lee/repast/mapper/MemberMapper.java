package com.aaa.lee.repast.mapper;

import com.aaa.lee.repast.model.Member;
import tk.mybatis.mapper.common.Mapper;

public interface MemberMapper extends Mapper<Member> {

    Member selectMemberByOpenId(String openId);

}