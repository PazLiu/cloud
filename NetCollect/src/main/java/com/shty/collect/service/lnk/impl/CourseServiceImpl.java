package com.shty.collect.service.lnk.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.shty.collect.dao.lnk.CourseDAO;
import com.shty.collect.domain.lnk.Course;
import com.shty.collect.service.lnk.CourseService;

@Service
public class CourseServiceImpl implements CourseService {

	@Autowired
	private CourseDAO courseDAO;

	@Transactional(propagation = Propagation.REQUIRED)
	public int addCourse(List<Course> course) {
		// TODO Auto-generated method stub
		PageHelper.startPage(1, 1);
		return courseDAO.addCourse(course);
	}

}
