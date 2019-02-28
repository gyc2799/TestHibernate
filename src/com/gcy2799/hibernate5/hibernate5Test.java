package com.gcy2799.hibernate5;

import static org.junit.jupiter.api.Assertions.*;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.junit.jupiter.api.Test;

class hibernate5Test {

	@Test
	void test() {
		//1.创建一个SessionFactory对象
		SessionFactory sessionFactory =null;
		
		//法1 4.x之前的   备注：4.X有专门的创建方式不过到了5.X又有新的方式
		//先创建Configuration对象，对应hibernate的基本配置信息和对象映射信息
		//Configuration configuration = new Configuration().configure();
		//sessionFactory = configuration.buildSessionFactory();
		
		//法2 （5.X后的）
		StandardServiceRegistry serviceRegistry =  new StandardServiceRegistryBuilder().configure().build();
		sessionFactory = new MetadataSources(serviceRegistry).buildMetadata().buildSessionFactory();

		
		//2 创建一个Session对象
		Session session = sessionFactory.openSession();
		
		//3.开启事务
		Transaction transaction = session.beginTransaction();
		
		//4.执行保存事件
		Student student =new Student("zhangsan001", "M", "31");
		session.save(student);
		
		//5.提交事务
		transaction.commit();
		
		//6.关闭session
		session.close();
		
		//7.关闭sessionFactory对象
		sessionFactory.close();
	}

}
