package cn.et.food.controller;

import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.et.food.service.MyFoodService;
/**
 * 后台验证步骤 
 *   1 javabean添加验证注解 
 *   2 action中使用 @Valid表示javabean 使用 Errors或者BindingResult判断是否验证失败
 *   3 出现jar包冲突  4.3.2
 * @author Administrator
 *
 */
@Controller
public class MyFoodController {
	/**
	 * 原始的输出json方式
	 *  OutpuStream os;
	 *  os.write(通过第三方json-lib转换的json字符串.getByte())
	 * 查询food
	 */
	@Autowired
	MyFoodService mdi;
	/**
	 * 直接返回对象 springmvc自动转换成json
	 * @param foodname 
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value="/queryFoodList",method={RequestMethod.GET})
	public List<Map<String, Object>> queryFoodList(String foodname) throws Exception{
		List<Map<String, Object>> queryAllFood = mdi.queryAllFood(foodname);
		return queryAllFood;
	}
	
	
	/**
	 * 删除food
	 * @param foodId 菜品id
	 * @param os
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/food/{foodId}",method={RequestMethod.DELETE})
	public String deleteFood(@PathVariable String foodId,OutputStream os) throws Exception{
		try {
			mdi.deleteFood(foodId);
			os.write("1".getBytes("UTF-8"));
		} catch (Exception e) {
			e.printStackTrace();
			os.write("0".getBytes("UTF-8"));
		}
		return null;
	}
	/**
	 * 修改food
	 * @param foodId 菜品id
	 * @param foodName 菜品名
	 * @param price 菜品价格
	 * @param os
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/food/{foodId}",method={RequestMethod.PUT})
	public String udpateFood(@PathVariable String foodId,String foodName,String price,OutputStream os) throws Exception{
		try {
			mdi.updateFood(foodId, foodName, price);
			os.write("1".getBytes("UTF-8"));
		} catch (Exception e) {
			os.write("0".getBytes("UTF-8"));
		}
		return null;
	}
	/**
	 * 现在菜品
	 * @param foodName 菜品名称
	 * @param price 价格
	 * @param os
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/food",method={RequestMethod.POST})
	public String saveFood(String foodName,String price,OutputStream os) throws Exception{
		try {
			mdi.saveFood(foodName, price);
			os.write("1".getBytes("UTF-8"));
		} catch (Exception e) {
			os.write("0".getBytes("UTF-8"));
		}
		return null;
	}
	
}
