package com.aaa.lee.repast.service;

import com.aaa.lee.repast.base.BaseService;
import com.aaa.lee.repast.base.ResultData;
import com.aaa.lee.repast.mapper.MemberMapper;
import com.aaa.lee.repast.mapper.ProductMapper;
import com.aaa.lee.repast.mapper.UmsCollectMapper;
import com.aaa.lee.repast.mapper.UmsShopFacilityMapper;
import com.aaa.lee.repast.model.Member;
import com.aaa.lee.repast.model.Product;
import com.aaa.lee.repast.model.UmsCollect;
import com.aaa.lee.repast.model.UmsShopFacility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import static com.aaa.lee.repast.status.StatusEnums.FAILED;
import static com.aaa.lee.repast.status.StatusEnums.SUCCESS;
@Service
public class UmsCollectService extends BaseService<UmsCollect> {
    @Autowired
    private UmsCollectMapper umsCollectMapper;
    @Autowired
    private MemberMapper memberMapper;
    @Autowired
    private UmsShopFacilityMapper umsShopFacilityMapper;
    @Autowired
    private ProductMapper productMapper;

    public ResultData insertUmsCollect(@RequestParam("token") String token,@RequestParam("shopId") Long shopId,@RequestParam("productId") Long productId){
        ResultData resultData=new ResultData();
        Member member=memberMapper.selectMemberByToken(token);
        UmsCollect umsCollect=new UmsCollect();
        int status;
        if (null !=  member) {
            Long kong= Long.valueOf(0);

            if (null!=shopId&&kong!=shopId&&!shopId.equals(kong)){
                //用户id
                long memeberId= member.getMemberLevelId();
                UmsCollect umsCollect1=new UmsCollect();
                umsCollect1.setMemberId(memeberId);
                umsCollect1.setShopId(shopId);
                umsCollect1.setProductId(productId);

                List<UmsCollect> umsCollectList=umsCollectMapper.select(umsCollect1);
                int k=umsCollectList.size();


                umsCollect.setMemberId(memeberId);
                //status
                 status=1;

                //时间
                Date date = new Date();//获得系统时间.
                SimpleDateFormat sdf =   new SimpleDateFormat( " yyyy-MM-dd HH:mm:ss " );
                String nowTime = sdf.format(date);
                Date time = null;
                try {
                    time = sdf.parse( nowTime );
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                umsCollect.setCollectStartTime(time);
                //

                if (k==0){
                resultData.setDetail("收藏此店铺");
                umsCollect.setProductId(productId);
                umsCollect.setShopId(shopId);
                }
                else if (kong==productId){
                    resultData.setCode(FAILED.getCode());
                    resultData.setMsg(FAILED.getMsg());
                    resultData.setDetail("不能重复收藏店铺");
                    return  resultData;
                }
                //验证收藏的是店铺还是产品，如果接受不到productid就说明收藏的店铺，productid传入0


                if (null!=productId&&kong!=productId&&!productId.equals(kong)){

                    UmsCollect umsCollect2=new UmsCollect();
                    umsCollect2.setMemberId(memeberId);
                    umsCollect2.setShopId(shopId);
                    umsCollect2.setProductId(productId);

                    List<UmsCollect> umsCollectList1=umsCollectMapper.select(umsCollect2);
                    int l=umsCollectList1.size();
                    if (l==0) {
                        umsCollect.setProductId(productId);
                        resultData.setDetail("收藏此店铺中的此商品");
                        status = 0;
                    }else {
                        resultData.setCode(FAILED.getCode());
                        resultData.setMsg(FAILED.getMsg());
                        resultData.setDetail("不能重复收藏商品");
                        return  resultData;
                    }
                }
                resultData.setCode(SUCCESS.getCode());
                resultData.setMsg(SUCCESS.getMsg());
                umsCollect.setStatus(status);
                int i=umsCollectMapper.insertSelective(umsCollect);
                if (i==1) {
                    return resultData;
                }else {
                    resultData.setCode(FAILED.getCode());
                    resultData.setMsg(FAILED.getMsg());
                    resultData.setDetail("添加收藏失败");
                    return  resultData;
                }

            }
            resultData.setCode(FAILED.getCode());
            resultData.setMsg(FAILED.getMsg());
            resultData.setDetail("店铺id不能为空");
            return resultData;
        }
        resultData.setCode(FAILED.getCode());
        resultData.setMsg(FAILED.getMsg());
        resultData.setDetail("请登录后重试");
        return resultData;

    }
    /*
    * 查询收藏的店铺
    * */
    public ResultData selectUmsCollectShopIp(@RequestParam("token") String token){
        ResultData resultData=new ResultData();
        Member member=memberMapper.selectMemberByToken(token);
        if (null !=  member) {
            //获取用户id
         long memberId= member.getMemberLevelId();
         UmsCollect umsCollect=new UmsCollect();
         umsCollect.setMemberId(memberId);
         //在收藏表内查询被用户的所有收藏
         List<UmsCollect> list=umsCollectMapper.select(umsCollect);
         List<UmsShopFacility> dianPu=new LinkedList<>();
         Long kong= Long.valueOf(0);
         for (UmsCollect umsCollect1:list){
            if (umsCollect1.getProductId()==null||kong==umsCollect1.getProductId()||umsCollect1.getProductId().equals(kong)){
                //如果收藏中的productid为空或0就证明是收藏的店铺
                Long shopId=umsCollect1.getShopId();
                UmsShopFacility umsShopFacility=new UmsShopFacility();
                umsShopFacility.setShopId(shopId);
                UmsShopFacility shopFacilities=umsShopFacilityMapper.selectOne(umsShopFacility);
                dianPu.add(shopFacilities);
            }
         }
            System.out.println(dianPu);
            resultData.setCode(SUCCESS.getCode());
            resultData.setMsg(SUCCESS.getMsg());
            resultData.setData(dianPu);
            resultData.setDetail("展示收藏的店铺");
            return resultData;
        }
        resultData.setCode(FAILED.getCode());
        resultData.setMsg(FAILED.getMsg());
        resultData.setDetail("请登录后重试");
        return resultData;

    }

    /*
     * 查询收藏的商品
     * */
    public ResultData selectUmsCollectProductId(@RequestParam("token") String token){
        ResultData resultData=new ResultData();
        Member member=memberMapper.selectMemberByToken(token);
        if (null !=  member) {
            //获取用户id
            long memberId= member.getMemberLevelId();
            UmsCollect umsCollect=new UmsCollect();
            umsCollect.setMemberId(memberId);
            //在收藏表内查询被用户的所有收藏
            List<UmsCollect> list=umsCollectMapper.select(umsCollect);
            List<Product> shangpin=new LinkedList<>();
            Long kong= Long.valueOf(0);
            for (UmsCollect umsCollect1:list){
                if (umsCollect1.getProductId()!=null&&umsCollect1.getProductId()!=kong&&!umsCollect1.getProductId().equals(kong)){
                    //如果收藏中的productid不为空或0就证明是收藏的商品
                    Long productId=umsCollect1.getProductId();
                    Product product=new Product();
                    product.setId(productId);
                    product.setShopId(umsCollect1.getShopId());
                    Product product1=productMapper.selectOne(product);
                    shangpin.add(product1);
                }
            }
            resultData.setCode(SUCCESS.getCode());
            resultData.setMsg(SUCCESS.getMsg());
            resultData.setData(shangpin);
            resultData.setDetail("展示收藏的商品");
            return resultData;
        }
        resultData.setCode(FAILED.getCode());
        resultData.setMsg(FAILED.getMsg());
        resultData.setDetail("请登录后重试");
        return resultData;

    }
    /*
     * 删除收藏的商品
     * */
    public ResultData deleteUmsCollectProductId(@RequestParam("token") String token,@RequestParam("productId") Long productId,@RequestParam("shopId") Long shopId){
        ResultData resultData=new ResultData();
        Member member=memberMapper.selectMemberByToken(token);
        if (null !=  member) {
            //获取用户id
            long memberId= member.getMemberLevelId();
            UmsCollect umsCollect=new UmsCollect();
            umsCollect.setMemberId(memberId);
            umsCollect.setShopId(shopId);

            Long kong= Long.valueOf(0);

            if (umsCollect.getProductId()!=null&&umsCollect.getProductId()!=kong&&umsCollect.getProductId().equals(kong)){
                //如果收藏中的productid不为空或0就证明是收藏的商品
                umsCollect.setProductId(productId);
                int i=umsCollectMapper.delete(umsCollect);
            }

            resultData.setCode(SUCCESS.getCode());
            resultData.setMsg(SUCCESS.getMsg());

            resultData.setDetail("删除收藏的商品");
            return resultData;
        }
        resultData.setCode(FAILED.getCode());
        resultData.setMsg(FAILED.getMsg());
        resultData.setDetail("请登录后重试");
        return resultData;

    }
    /*
     * 删除收藏的店铺
     * */
    public ResultData deleteUmsCollectShopId(@RequestParam("token") String token,@RequestParam("shopId") Long shopId){
        ResultData resultData=new ResultData();
        Member member=memberMapper.selectMemberByToken(token);
        if (null !=  member) {
            //获取用户id
            long memberId= member.getMemberLevelId();
            UmsCollect umsCollect=new UmsCollect();
            umsCollect.setMemberId(memberId);
            umsCollect.setShopId(shopId);
            int i=umsCollectMapper.delete(umsCollect);

            resultData.setCode(SUCCESS.getCode());
            resultData.setMsg(SUCCESS.getMsg());
            resultData.setDetail("删除收藏的店铺");
            return resultData;
        }
        resultData.setCode(FAILED.getCode());
        resultData.setMsg(FAILED.getMsg());
        resultData.setDetail("请登录后重试");
        return resultData;

    }
}
