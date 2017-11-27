package com.example.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import com.example.pojo.Student;

@SpringBootApplication
@ComponentScan("com.example")
public class Application {
	
	private static 	List<Student> list = new ArrayList<>();


	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.scan("com.example.pojo");
		context.refresh();
		context.registerShutdownHook();
		//模拟创建10个学生
		for(int i = 0;i <10; i++) {
			Student s = new Student();
			s.setId(i+1);
			s.setName("tom"+(i+1));
			list.add(s);
		}
		System.out.println("--------欢迎进入签到界面------");
		while(true) {
			System.out.println("请您选择：1.签到 2签退.3.查询 4.退出");
			Scanner scan = new Scanner(System.in);
			if(scan.hasNextInt()) {
				int  a= scan.nextInt();
				switch(a) {
					case 1:signUp();break;
					case 2:signDown();break;
					//统计已经签到的学生
					case 3:selDetail();break;
					case 4:exit();return;
					default:System.out.println("请输入正确的数字");break;
				}
			}else {
				System.out.println("请输入数字");
			}
			
		}
	}
	//签到
	public static void signUp() {
		System.out.println("请输入您的学号来签到");
		Scanner scan = new Scanner(System.in);
		int id = scan.nextInt();
		scan.nextLine();
		if(id>=1&&id<=10) {
			if(list.get(id-1).getFlag()==true) {
				System.out.println("您已经签到了");
				return ;
			}
			list.get(id-1).setFlag(true);
			System.out.println("签到成功");
		}
	}
	//签退
	public static void signDown() {
		System.out.println("请输入您的学号来签退");
		Scanner scan = new Scanner(System.in);
		int id = scan.nextInt();
		scan.nextLine();
		if(id>=1&&id<=10) {
			if(list.get(id-1).getFlag()==true) {
				list.get(id-1).setFlag(false);
				System.out.println("签退成功");
				return ;
			}
			System.out.println("您今天未签到");
		}
	}
	//查询已经签到的学生
	public static void selDetail() {
		List<Student> haveSign = new ArrayList<>();
		List<Student> havNotSign = new ArrayList<>();
		for(Student s:list) {
			if(s.getFlag()==true) {
				haveSign.add(s);
			}else {
				havNotSign.add(s);
			}
		}
		for(int i = 0;i<haveSign.size();i++) {
			System.out.println(haveSign.get(i).getName()+"已签到");
		}
		System.out.println("***************");
		for(int i = 0;i<havNotSign.size();i++) {
			System.out.println(havNotSign.get(i).getName()+"未签到");
		}
	}
	//推出系统
	public static void exit() {
		System.out.println("欢迎您使用此系统");
	}
	
}
