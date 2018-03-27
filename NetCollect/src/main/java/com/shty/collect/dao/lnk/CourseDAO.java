package com.shty.collect.dao.lnk;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.shty.collect.domain.lnk.Course;

@Repository
public interface CourseDAO {

	public int addCourse(List<Course> course);

	public List<Course> getCourseByPeopleId(Long peopleId);

	// 删除
	public int deleteCourse(Long peopleId);

}
