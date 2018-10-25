package br.com.drsource.imacbrasil.category;


import br.com.drsource.imacbrasil.exception.ImacException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public void save(Category category) throws ImacException {


        Category exists = categoryRepository.findByName(category.getName());
        if (exists != null){
            throw new ImacException("Category "+category.getName()+" already exists");
        }else{
            categoryRepository.save(category);
        }
    }

    public List<Category> findAll(){
        List<Category> categories = new ArrayList<>();
        categoryRepository.findAll().forEach(categories::add);
        return categories;

    }

    public Optional<Category> findById(Short id){
        return categoryRepository.findById(id);
    }
}
