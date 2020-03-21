package com.aaa.lee.repast.mapper;

import com.aaa.lee.repast.model.ShopInformation;
import tk.mybatis.mapper.common.Mapper;

public interface ShopInformationMapper extends Mapper<ShopInformation> {

    ShopInformation selectShopInformationById(Long id);
}