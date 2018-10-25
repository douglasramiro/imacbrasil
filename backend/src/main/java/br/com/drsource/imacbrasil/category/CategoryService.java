package br.com.drsource.imacbrasil.category;


import br.com.drsource.imacbrasil.exception.ImacException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {


    private CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Transactional
    public void save(@NotNull @Valid Category category) throws ImacException {


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
