package com.heima.pay.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.heima.pay.domain.po.OrderDetail;
import com.heima.pay.mapper.OrderDetailMapper;
import com.heima.pay.service.IOrderDetailService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单详情表 服务实现类
 * </p>
 *
 * @author 虎哥
 * @since 2023-05-05
 */
@Service
public class OrderDetailServiceImpl extends ServiceImpl<OrderDetailMapper, OrderDetail> implements IOrderDetailService {

}
