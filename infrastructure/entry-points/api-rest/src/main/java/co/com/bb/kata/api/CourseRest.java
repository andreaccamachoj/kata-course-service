package co.com.bb.kata.api;



import co.com.bb.kata.api.dto.request.CourseCreateRequest;
import co.com.bb.kata.api.mapper.CourseMapper;
import co.com.bb.kata.api.mapper.FileMapper;
import co.com.bb.kata.model.chapter.UploadedFile;
import co.com.bb.kata.model.course.Course;
import co.com.bb.kata.usecase.course.CourseUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/courses", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class CourseRest {
    private final CourseUseCase courseUseCase;
    private final CourseMapper courseMapper;
    private final FileMapper fileMapper;

    @GetMapping
    public ResponseEntity<List<Course>> getAllCourses() {
        List<Course> courses = courseUseCase.getAllCourses();
        return ResponseEntity.ok(courses);
    }

    @GetMapping("/module/{moduleId}")
    public ResponseEntity<List<Course>> getCoursesByModule(@PathVariable("moduleId") Long moduleId){
        List<Course> courses = courseUseCase.getCoursesByModule(moduleId);
        return ResponseEntity.ok(courses);
    }

    @GetMapping("/{courseId}")
    public ResponseEntity<List<Course>> getCourseById(@PathVariable("courseId") Long courseId) {
        List<Course> courses = courseUseCase.getCourseById(courseId);
        return ResponseEntity.ok(courses);
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> createCourse(
            @RequestPart("course") @Valid CourseCreateRequest courseRequest,
            @RequestPart("files") MultipartFile[] files) {

        Course course = courseMapper.toDomain(courseRequest);

        List<UploadedFile> uploadedFiles = Arrays.stream(files)
                .map(fileMapper::toUploadedFile)
                .toList();

        courseUseCase.saveCourseWithChapters(course, uploadedFiles);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
