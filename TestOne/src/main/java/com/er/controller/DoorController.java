package com.er.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;

import com.er.dao.DoorMapper;
import com.er.dao.OrderMapper;
import com.er.pojo.Door;

/**
 * 控制层
 * @author Guo
 *
 */
@Controller
public class DoorController {
	/**
	 * 通用页面跳转方法（优先级最低）
	 * 如果浏览器访问服务器的路径为：/index  {}中的page变量的值就是index
	 * 在方法上声明一个同名的参数page，
	 * 再通过@PathVariable注解接受{}中的变量的值传递给下面的方法，赋值给方法上的page变量
	 * 因此再访问jsp，可以通过"/jsp文件名" 来访问
	 */
	public String toPage(@PathVariable String page) {
		return page ;
	}
	/**
	 * 测试ssm开发环境
	 */
	@RequestMapping("/testssm")
	public String testSSM(Model model) {
		//调用DoorMapper接口子类实例的findAll方法
		List<Door> list = doorMapper.findAll();
		//将list集合加到model中
		model.addAttribute("list",list);
		//将数据转发到test.jsp，在test.jsp中取出数据并显示
		return "test" ;
	}
	/* 测试springmvc的开发环境 */
	@RequestMapping("/testmvc")
	public String testmvc() {
		System.out.println( "testmvc()...." );
		return "test";
	}
	
	/**
	 * 通过spring的自动装配将DoorMapper接口的子类实现实例复制给Dao
	 */
	@Autowired
	DoorMapper doorMapper ;
	@Autowired
	OrderMapper orderMapper ;
	/**
	 * 1、查询所有门店信息
	 */
	@RequestMapping("/doorList")
	public String doorList(Model model) {
		//查询所有门店信息，返回所有门店对象集合
		List<Door> doorList = doorMapper.findAll();
		//将门店对象集合存入Model中
		model.addAtrribute("list",doorList);
		//转发到门店列表页面，便利所有门店信息
		return "door_list";
	}
	/**
	 * 2、新增门店信息
	 */
	@RequestMapping("doorAdd")
	public String doorAdd(Door door,Model model) {
		//调用dao新增门店的方法
		doorMapper.add(door);
		//跳转回门店列表，显示门店最新门店信息
		//return doorList(model);
		return "forward:/doorList" ;
	}
	/**
	 * 3、根据id删除门店信息，
	 * 如果门店下由订单，门店不能删除
	 */
	/*public String doorDelete(Integer id) {
		doorMapper.deleteById(id);
		return "forward:/doorList";
	}*/
	@RequestMapping("doorDelete")
	private String doorDelete(Integer id) {
		//根据门店id到订单列表中，删除关联订单
		orderMapper.deleteById(id);
		//再根据id删除门店信息
		doorMapper.deleteById(id);
		return "forward:/doorList";
	}
	/**
	 * 4、根据id查询门店信息
	 * 		将查询到的信息带到修改页面进行回显
	 */
	@RequestMapping("/doorInfo")
	public String doorInfo(Integer id,Medel model) {
		//调用doorMapper的方法，根据id查询门店信息
		Door door = doorMapper.findById(id);
		//将门店信息存入MOdel中
		model.addAtribute("door",door);
		//转发到门店修改页面，回显当前门店信息
		return "door_update" ;
	}
	/**
	 * 5、根据本店id修改门店信息
	 */
	@RequestMapping("/doorUpdate")
	public String doorUpdate(Door door) {
		doorMapper.updateById(door);
		return "redirect:/doorList";
	}
}
