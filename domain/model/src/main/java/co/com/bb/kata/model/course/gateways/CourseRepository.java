package co.com.bb.kata.model.course.gateways;

import co.com.bb.kata.model.course.Course;

import java.util.List;

public interface CourseRepository {
    public List<Course> findAllCourses();
    public List<Course> findByIdModule(Long moduleId);
    public List<Course> findByIdCourse(Long courseId);
    public Course saveAggregate(Course course);
}
