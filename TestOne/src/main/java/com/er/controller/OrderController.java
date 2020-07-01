package com.er.controller;
/**
 * 控制层
 * @author Guo
 *
 */

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.er.dao.DoorMapper;
import com.er.dao.OrderMapper;
import com.er.pojo.Door;
import com.er.pojo.Order;

@Controller
public class OrderController {
	@Autowired
	OrderMapper orderMapper ;
	@Autowired
	DoorMapper doorMapper ;
	
	/**
	 * 1、查询所有订单信息
	 */
	@RequestMapping("/orderList")
	public String orderList(Model model) {
		//调用dao层的findAll方法查询所有订单信息
		List<Order> list = orderMapper.findAll();
		//将list集合存入moedl中
		model.addAttribute("list",list);
		//将集合转发到订单列表页面便利集合进行显示
		return "order_list";
	}
	/**
	 * 2、新增订单信息
	 */
	@RequestMapping("/orderAdd")
	public String orderAdd(Order order) {
		orderMapper.add(order);
		return "forward:/orderList" ;
	}
	/**
	 * 跳转到订单新增页面，跳转之前先查询所有门店，将所有门店带到订单新增页面
	 */
	@RequestMapping("/toOrderAdd")
	public String toOrderAdd(Model model) {
		//查询所有门店信息dlist
		List<Door> dlist = doorMapper.findAll();
		//将dlist加入model集合中
		model.addAttribute("dlist",dlist);
		return "forward:/order_add" ;
	}
	/**
	 * 3、根据订单id删除订单信息
	 */
	@RequestMapping("/orderDelete")
	public String orderDelete(Integer id) {
		orderMapper.deleteById(id);
		return "forward:/orderList" ;
	}
	/**
	 * 4、根据id查询订单信息
	 */
	@RequestMapping("/orderInfo")
	public String orderInfo(Integer id,Model model) {
		//根据id查询订单
		Order order = orderMapper.findById(id);
		//将订单集合添加到model中
		model.addAttribute("order",order);
		
		//查询所有门店信息并添加到model集合中
		List<Door> dlist = doorMapper.findAll();
		model.addAttribute("dlist",dlist);
		//转发带到订单修改页面，进行数据的回显
		return "order_update" ;
	}
	/**
	 * 5、根据id修改订单信息
	 */
	@RequestMapping("/orderUpdate")
	public String orderUpdate(Order order) {
		orderMapper.updateById(order);
		return "forward:/orderList";
	}
}
