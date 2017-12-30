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
 * ��̨��֤���� 
 *   1 javabean�����֤ע�� 
 *   2 action��ʹ�� @Valid��ʾjavabean ʹ�� Errors����BindingResult�ж��Ƿ���֤ʧ��
 *   3 ����jar����ͻ  4.3.2
 * @author Administrator
 *
 */
@Controller
public class MyFoodController {
	/**
	 * ԭʼ�����json��ʽ
	 *  OutpuStream os;
	 *  os.write(ͨ��������json-libת����json�ַ���.getByte())
	 * ��ѯfood
	 */
	@Autowired
	MyFoodService mdi;
	/**
	 * ֱ�ӷ��ض��� springmvc�Զ�ת����json
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
	 * ɾ��food
	 * @param foodId ��Ʒid
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
	 * �޸�food
	 * @param foodId ��Ʒid
	 * @param foodName ��Ʒ��
	 * @param price ��Ʒ�۸�
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
	 * ���ڲ�Ʒ
	 * @param foodName ��Ʒ����
	 * @param price �۸�
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
