package co.com.bb.kata.api;

import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/user-courses", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class UserCourseRest {


}
