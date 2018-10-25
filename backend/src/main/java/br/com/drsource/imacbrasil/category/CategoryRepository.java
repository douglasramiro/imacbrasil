package br.com.drsource.imacbrasil.category;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Short> {

    Category findByName (String name);

}
