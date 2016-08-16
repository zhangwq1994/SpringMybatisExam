package com.hand;

import java.io.IOException;
import java.io.Reader;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.hand.Customer;

public class Test {

	public static void selectCustomer(UserOperation userOperation){
		System.out.println("请输入FirstName(first_name):");
		Scanner scanner0 = new Scanner(System.in);
		String setFirst_name = scanner0.next();
		
		System.out.println("请输入LastName(last_name):");
		Scanner scanner1 = new Scanner(System.in);
		String setLast_name = scanner1.next();
		
		System.out.println("请输入Email(emai):");
		Scanner scanner2 = new Scanner(System.in);
		String setEmail = scanner2.next();
		
		List<Integer> temp = userOperation.selectUsers();
		int setAddress_id = 0;
		System.out.println("请输入Address ID:");
		while(true){
			Scanner scanner3 = new Scanner(System.in);
			int i = scanner3.nextInt();
			boolean m = false;
			for(int j : temp){
				if(i == j)
					m = true;
			}
			if(m){
				setAddress_id = i;
				break;
			}
			else{
				System.out.println("你输入的Address	ID不存在,请重新输入:");
			}
		}
			
		Customer customer = new Customer();
		customer.setStore_id(1);
		customer.setFirst_name(setFirst_name);
		customer.setLast_name(setFirst_name);
		customer.setEmail(setEmail);
		customer.setAddress_id(setAddress_id);
		customer.setCreate_date(new Date());
		userOperation.addUser(customer);
		
		Address address = new Address();
		address = userOperation.findById(setAddress_id);
		System.out.println("已经保存的数据如下 ：");
		System.out.println("	ID : "+customer.getAddress_id());
		System.out.println("	FirstName : "+customer.getFirst_name());
		System.out.println("	LastName : "+customer.getLast_name());
		System.out.println("	Email : "+customer.getEmail());
		System.out.println("	Date : "+customer.getCreate_date());
		System.out.println("	Address : "+address.getAddress());
	}
	
	public static int deleteCustomer(UserOperation userOperation){
		List<Integer> temp = userOperation.selectCustomer();
		int customer_id = 0;
		System.out.println("请输入Customer ID:");
		while(true){
			Scanner scanner = new Scanner(System.in);
			int i = scanner.nextInt();
			boolean m = false;
			for(int j : temp){
				if(i == j)
					m = true;
			}
			if(m){
				customer_id = i;
				break;
			}
			else{
				System.out.println("你输入的Customer	ID不存在,请重新输入:");
			}
		}
		userOperation.deleteCustomer(customer_id);
		return customer_id;
	}
	
	public static void main(String[] args) {
//			//获取基本配置文件的路径
//		String resource = "Config.xml";
//			//流对象
//		Reader reader = null;
//			//用于执行SQL语句
//		SqlSession session;
//		try {
//			reader = Resources.getResourceAsReader(resource);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		SqlSessionFactory sqlMapper = new SqlSessionFactoryBuilder().build(reader);
//		session = sqlMapper.openSession();
//		UserOperation userOperation = session.getMapper(UserOperation.class);
		
		ApplicationContext ctx = new ClassPathXmlApplicationContext("SpringConfig.xml"); 
		UserOperation userOperation = (UserOperation)ctx.getBean("userMapper"); 
		
		selectCustomer(userOperation);
		//session.commit();
		int temp = deleteCustomer(userOperation);
		//session.commit();

		System.out.println("你输入的ID为"+temp+"的Customer已经删除.");
		//session.close();
	}

}
