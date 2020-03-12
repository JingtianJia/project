package com.aaa.lee.repast.service;

import com.aaa.lee.repast.base.BaseService;
import com.aaa.lee.repast.mapper.MemberMapper;
import com.aaa.lee.repast.model.Member;
import com.aaa.lee.repast.utils.IDUtil;
import com.aaa.lee.repast.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.common.Mapper;

/**
 * @Company AAA软件教育
 * @Author Seven Lee
 * @Date Create in 2020/3/10 10:54
 * @Description
 **/
@Service
public class MemberService extends BaseService<Member> {

    @Autowired
    private MemberMapper memberMapper;

    @Override
    public Mapper<Member> getMapper() {
        return memberMapper;
    }

    /**
     * @author Seven Lee
     * @description
     *      执行登录操作
     *      虽然目前使用仍然是微信小程序作为前端，登录必须接收的是微信的账号
     *      有两种可能性:
     *          1.第一次进入到微信小程序中(这个用户根本就没有登录过，也就是说第一次登录)
     *              insert 用户信息和token值
     *          2.用户之前就已经登录过了(就不需要再做insert操作)
     *              update token(再次登录我要给你生成一个新的token，防止用户被拦截，保证了用户的安全性)
     *          无状态:
     *              所有的操作都不再进行存入session，存入cookie(一旦存入就相当于记录了登录状态)
     *              所谓的无状态就不再去记录状态
     *              因为是微服务项目--->session跨域(非常不好解决)--->所有的操作都使用无状态
     *          问题:
     *              如果确保其他的项目也都知道该用户已经处于登录状态了
     *          当用户每一次登录的时候，都要分配给用户一个新的token(为了保证用户安全)
     *          然后用户无论在什么操作中都必须要携带这个token(也就是说Controller中除了登录方法之外
     *          所有的方法都必须要接收一个token参数(可以使用切面完成))
     *          用户在每一次操作的时候都去检测这个token--->也就是说有了这个token就说明用户已经处于登录状态
     *          没有这个token用户没有登录，无法访问！！(而且这个token还是在服务器内部生成的，并不是由微信传过来的)
     * @param [member]
     * @date 2020/3/10
     * @return java.lang.Boolean
     * @throws 
    **/
    public Boolean doLogin(Member member) {
        // 1.判断member是否为null，并且还要判断openId是否为null
        if(null != member && null != member.getOpenId() && StringUtil.isNotEmpty(member.getOpenId())) {
            // 说明是从微信端跳转的请求
            // 2.需要再次确保是否是新用户(通过openId进行查询-->看用户是否存在)
            try {
                /**
                 * 这个member是由微信端传递过来的
                 *      nickname: zhangsan(微信名)
                 *      region: 郑州(地区)
                 *      openId: 微信的唯一标识符
                 *      ...
                 *      select xxx from member where nickname = 'zhangsan' and region = '郑州'...
                 *
                 *      用户修改微信名:
                 *          nickname:lisi
                 *      select xxx from member where nickname = 'lisi' and region = '郑州'...
                 */
                Member mb = memberMapper.selectMemberByOpenId(member.getOpenId()); // 必须要只根据openId查询(有两个东西是不变的1.openId,2.微信号)
                String token = IDUtil.getUUID() + member.getOpenId();
                // 3.判断查询的mb是否为null
                if(null != mb) {
                    // 说明并不是新用户，之前一定登陆过
                    // 只需要修改token即可
                    mb.setToken(token); // 这个token一定是新的token
                    Integer updateResult = super.update(mb);
                    if(updateResult > 0) {
                        // 说明已经修改成功
                        return true;
                    }

                } else {
                    // 说明是新用户，需要执行的是insert操作
                    member.setToken(token);
                    Integer saveResult = super.add(member);
                    // 4.判断是否保存成功
                    if(saveResult > 0) {
                        // 保存成功
                        return true;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        return false;
    }

}
