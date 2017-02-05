package ru.beorg.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.beorg.test.dto.ProductPriceDto;
import ru.beorg.test.logic.ProductApiLogic;

import javax.validation.Valid;

/**
 * Created by Vlad on 05.02.2017.
 */
@CrossOrigin
@RestController
public class ProductController {

    @Autowired
    private ProductApiLogic logic;

    @PostMapping
    public ResponseEntity add(@Valid @RequestBody ProductPriceDto dto) {
        logic.add(dto);
        return ResponseEntity.ok().build();
    }
}
