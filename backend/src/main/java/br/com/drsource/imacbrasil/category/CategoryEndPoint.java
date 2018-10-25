package br.com.drsource.imacbrasil.category;

import br.com.drsource.imacbrasil.exception.ImacException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;


@RestController
@RequestMapping("category")
public class CategoryEndPoint {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<Category>> listAll(){
        return ResponseEntity.ok(categoryService.findAll());
    }

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody Category category) throws ImacException {
        categoryService.save(category);
        URI location = ServletUriComponentsBuilder.fromCurrentContextPath().path("/category/{id}").buildAndExpand(category.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> findById(@PathVariable Short id){
        return categoryService.findById(id).map(c-> ResponseEntity.ok(c)).orElse(ResponseEntity.notFound().build());
    }



}
