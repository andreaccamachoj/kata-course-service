package co.com.bb.kata.api;
import co.com.bb.kata.model.module.Module;
import co.com.bb.kata.usecase.module.ModuleUseCase;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping(value = "/api/v1/modules", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class ApiRest {

    private final ModuleUseCase moduleUseCase;

    @GetMapping
    public ResponseEntity<List<Module>> getAllModules() {
        List<Module> modules = moduleUseCase.getAllModules();
        return ResponseEntity.ok(modules);
    }

}