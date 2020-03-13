package com.aaa.lee.repast.service;

import com.aaa.lee.repast.base.BaseService;
import com.aaa.lee.repast.base.ResultData;
import com.aaa.lee.repast.mapper.IntegrationMapper;
import com.aaa.lee.repast.mapper.MemberMapper;
import com.aaa.lee.repast.model.Integration;
import com.aaa.lee.repast.model.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.aaa.lee.repast.status.StatusEnums.FAILED;
import static com.aaa.lee.repast.status.StatusEnums.SUCCESS;
@Service
public class IntegrationService extends BaseService<Integration> {
    @Autowired
    private IntegrationMapper integrationMapper;
    @Autowired
    private MemberMapper memberMapper;

    /**
     * 查询积分
     * @param token
     * @return
     */
    public ResultData queryIntegration(@RequestParam String token) {
        ResultData resultData = new ResultData();
        Member member = memberMapper.selectMemberByToken(token);
        if (null != member) {
            Map map = new HashMap<String, Integer>();
            resultData.setCode(SUCCESS.getCode());
            resultData.setMsg(SUCCESS.getMsg());
            resultData.setDetail("查询积分成功");
            map.put("member", member);
            List<Integration> integrations = integrationMapper.selectIntegrationByMemberId(String.valueOf(member.getId()));
            map.put("integrations", integrations);
            resultData.setData(map);
            return resultData;
        }
        resultData.setCode(FAILED.getCode());
        resultData.setMsg(FAILED.getMsg());
        resultData.setDetail("请登录后重试");
        return resultData;
    }
}
