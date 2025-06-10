package com.intolerance_app.controller;

import com.intolerance_app.model.IntoleranceModel;
import com.intolerance_app.service.IntoleranceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/intolerance")
public class IntoleranceController {

    private final IntoleranceService intoleranceService;

    @Autowired
    public IntoleranceController(IntoleranceService intoleranceService) {
        this.intoleranceService = intoleranceService;
    }

    @GetMapping
    public ResponseEntity<List<IntoleranceModel>> getAllIntolerances() {
        List<IntoleranceModel> intolerances = intoleranceService.getAllIntolerances();
        return ResponseEntity.ok(intolerances);
    }

    @GetMapping("/{id}")
    public ResponseEntity<IntoleranceModel> getIntoleranceById(@PathVariable Long id) {
        return intoleranceService.getIntoleranceById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound()
                        .build());
    }
}
