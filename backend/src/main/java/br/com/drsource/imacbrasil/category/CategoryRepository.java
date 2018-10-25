package br.com.drsource.imacbrasil.category;

import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category,Short> {
    Category findByName (String name);
}
