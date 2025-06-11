package com.intolerance_app.controller;

import com.intolerance_app.model.IntoleranceModel;
import com.intolerance_app.model.ProductModel;
import com.intolerance_app.service.IntoleranceService;
import org.springframework.web.bind.annotation.RequestBody;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/intolerance")
public class IntoleranceController {

    private final IntoleranceService intoleranceService;

    @Autowired
    public IntoleranceController(IntoleranceService intoleranceService) {
        this.intoleranceService = intoleranceService;
    }

    @PostMapping
    public ResponseEntity<IntoleranceModel> createIntolerance (@Valid @RequestBody IntoleranceModel intoleranceModel) {
        IntoleranceModel newIntolerance = intoleranceService.createIntolerance(intoleranceModel);
        return new ResponseEntity<>(newIntolerance, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<IntoleranceModel> updateIntolerance(@PathVariable Long id, @RequestBody IntoleranceModel intolerance) {
        IntoleranceModel updateIntolerance = intoleranceService.updateIntolerance(id, intolerance);
        return ResponseEntity.ok(updateIntolerance);
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
