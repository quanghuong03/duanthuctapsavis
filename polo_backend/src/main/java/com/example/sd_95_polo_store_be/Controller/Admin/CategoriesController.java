package com.example.sd_95_polo_store_be.Controller.Admin;

import com.example.sd_95_polo_store_be.Model.Entity.Categories;
import com.example.sd_95_polo_store_be.Model.Entity.Colors;
import com.example.sd_95_polo_store_be.Service.CategoriesService;
import com.example.sd_95_polo_store_be.common.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequestMapping("/admin/category")
@RestController
public class CategoriesController {
    @Autowired
   private CategoriesService categoriesSrevice;
    @GetMapping("")
    public Response<List<Categories>> getsByStatus() {
        return Response.ofSucceeded(categoriesSrevice.getCategoryByStatus());
    }

    @GetMapping("/getAll")
    public Response<List<Categories>> getAll() {
        return Response.ofSucceeded(categoriesSrevice.gets());
    }

    @PostMapping("/add")
    public Response<Categories> create(@RequestBody Categories categories) {
        try {
           Categories categorie = categoriesSrevice.saveCategories(categories);
            return Response.ofSucceeded(categorie);
        } catch (IllegalArgumentException e) {
            return Response.ofError(e.getMessage());
        }
    }

    @PutMapping("/update/{id}")
    public Response<Categories> update(@PathVariable Long id, @RequestBody Categories categories) {
        try {
            categories.setId(id);
            Categories categorie = categoriesSrevice.saveCategories(categories);
            return Response.ofSucceeded(categorie);
        } catch (IllegalArgumentException e) {
            return Response.ofError(e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        try {
            categoriesSrevice.deleteCategoriesById(id);
            return ResponseEntity.ok("Loại đã được xóa thành công");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }


    }

    @GetMapping("/get-page")
    public List<?> getPage(@RequestParam(defaultValue = "0") int p) {
        Pageable pageable = PageRequest.of(p, 5);
        Page<Categories> page = categoriesSrevice.findAllCategories(pageable);
        return page.toList();
    }

    @DeleteMapping("/delete")
    public Response<List<Long>> deleteMultiple(@RequestBody Map<String, List<Long>> request) {
        List<Long> ids = request.get("id");
        try {
            categoriesSrevice.deleteCategoriesByIds(ids);
            return Response.ofSucceeded();
        } catch (IllegalArgumentException e) {
            return Response.ofError(e.getMessage());
        }
    }
    @GetMapping("/{id}")
    public Response<Categories> get(@PathVariable Long id) {
        return Response.ofSucceeded(categoriesSrevice.getOne(id));
    }
    @PutMapping("changeStatus/{id}")
    public Response<Void> changeBran(@PathVariable Long id){
        categoriesSrevice.changeStatus(id);
        return Response.ofSucceeded();
    }


}
