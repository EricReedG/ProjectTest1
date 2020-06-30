package com.er.dao;

import java.util.List;

import com.er.pojo.Order;

/**
 * 接口类：
 * 	用于控制层调用接口，从而获取数据实体
 * @author Guo
 *
 */
public interface OrderMapper {

	/**
	 * 1、查询所有订单信息
	 */
	public List<Order> findAll();
	/**
	 * 2、新增订单信息
	 */
	public void add(Order order);
	/**
	 * 3、根据id删除订单信息
	 */
	public void deleteById(Integer id);
	/**
	 * 4、根据id查询订单信息
	 */
	public Order findById(Integer id);
	/**
	 * 5、根据id修改订单信息
	 */
	public void updateById(Order order);
}
