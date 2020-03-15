package com.aaa.lee.repast.mapper;

import com.aaa.lee.repast.model.Address;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface AddressMapper extends Mapper<Address> {
    List selAddress(Long memberId);
}