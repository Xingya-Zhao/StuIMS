package service.impl;

import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import entity.Students;
import service.StudentsDAO;

public class TestStudentsDAOImpl {
	
	@Test
	public void testQueryAllStudents() {
		StudentsDAO sdao = new StudentsDAOImpl();
		List<Students> list = sdao.queryAllStudents();
		for(Students s:list) {
			System.out.println(s.toString());
		}
	}
	
	@Test
	public void testAddStudents() {
		Students s = new Students("s0000005","赵兴亚","男",new Date(),"山东");
		StudentsDAO sdao = new StudentsDAOImpl();
		Assert.assertEquals(true, sdao.addStudents(s));
	}

}
