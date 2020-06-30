package com.er.dao;

import java.util.List;

import com.er.pojo.Door;

/**
 * 接口类创建：用于调用持久层数据
 * @author Guo
 *	接口的实现类由谁来提供：
 *	如果没有整合mybatis和spring，实现类则由mybatis框架提供
 *	如果整合了mybatis和spring，则由mybatis和spring的整合包提供实现类，由spring容器负责创建接口的子类实例
 */
public interface DoorMapper {
	/**
	 * 1、查询门店信息
	 * @return 
	 */
	public List<Door> findAll();
	/**
	 * 2、新增门店信息
	 */
	public void add(Door door);
	/**
	 * 3、根据id删除门店信息
	 */
	public void deleteById(Integer id);
	/**
	 * 4、根据id查询门店信息
	 */
	public Door findById(Integer id);
	/**
	 * 5、根据id修改门店信息
	 */
	public void updateById(Door door);
}
