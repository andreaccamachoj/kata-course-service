package co.com.bb.kata.usecase.course;

import co.com.bb.kata.model.chapter.UploadedFile;
import co.com.bb.kata.model.course.Course;
import co.com.bb.kata.model.course.gateways.CourseRepository;
import co.com.bb.kata.model.gateway.StorageGateway;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
public class CourseUseCase {

    private final CourseRepository courseRepository;
    private final StorageGateway storageGateway;

    public List<Course> getAllCourses() {
        return courseRepository.findAllCourses();
    }

    public List<Course> getCoursesByModule(Long moduleId) {
        return courseRepository.findByIdModule(moduleId);
    }

    public List<Course> getCourseById(Long courseId) {
        return courseRepository.findByIdCourse(courseId);
    }

    public void saveCourseWithChapters(Course course, List<UploadedFile> uploadedFiles) {

        course.getChapterList().forEach(chapter -> {
            UploadedFile matchingFile = findFileByFileName(uploadedFiles, chapter.getFileName());

            String s3Key = storageGateway.upload(
                    matchingFile,
                    String.format("courses/%s/chapter-%d-%s",
                            sanitize(course.getTitle()),
                            chapter.getOrderIndex(),
                            UUID.randomUUID())
            );

            chapter.setS3Key(s3Key);
            chapter.setContentType(matchingFile.getContentType());
        });
        courseRepository.saveAggregate(course);
    }

    private UploadedFile findFileByFileName(List<UploadedFile> files, String fileName) {
        return files.stream()
                .filter(f -> f.getOriginalFilename().equalsIgnoreCase(fileName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No file found for fileName: " + fileName));
    }

    private String sanitize(String text) {
        return text.toLowerCase().replaceAll("[^a-z0-9]+", "-");
    }

}