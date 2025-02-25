package com.example.sd_95_polo_store_be.Service.Impl;

import com.example.sd_95_polo_store_be.Model.Entity.Brands;
import com.example.sd_95_polo_store_be.Model.Entity.Colors;
import com.example.sd_95_polo_store_be.Model.Entity.Role;
import com.example.sd_95_polo_store_be.Repository.RoleRepository;
import com.example.sd_95_polo_store_be.Service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleRepository roleRepository;


    @Override
    public List<Role> getAll() {
        return roleRepository.findAll();
    }

    @Override
    public List<Role> getColorByStatus() {
        return roleRepository.findAllByOrderByCreateDateDesc();
    }

    @Override
    public Role saveRole(Role role) {
        var now = OffsetDateTime.now();
        if (role.getId() != null) {
            Optional<Role> existingCategori = roleRepository.findById(role.getId());
            if (existingCategori.isPresent()) {
                Role updateCategori = existingCategori.get();
                updateCategori.setName(role.getName());
                updateCategori.setStatus(1);

                updateCategori.setUpdatedAt(now);
                return roleRepository.save(updateCategori);
            } else {
                throw new IllegalArgumentException("Không tìm thấy chức vu với id: " + role.getId());
            }
        } else {
            Role newCategori = new Role();
            if (isColorDataDuplicate(role)) {
                throw new IllegalArgumentException("Chức vu này đã có rồi");
            }
            newCategori.setName(role.getName());
            newCategori.setStatus(1);
            newCategori.setCreatedAt(now);
            newCategori.setUpdatedAt(now);
            return roleRepository.save(newCategori);
        }
    }

    @Override
    public Role getOne(Integer id) {
        Optional<Role> optionalMauSac = roleRepository.findById(id);
        return optionalMauSac.get();
    }

    @Override
    public Role findById(Integer id) {
        Optional<Role> color = roleRepository.findById(id);
        return color.orElse(null);
    }

    @Override
    public void changeStatus(Integer id) {
        var brand = roleRepository.findById(id).orElseThrow();
        if(brand.getStatus() == 1){
            brand.setStatus(0);
            roleRepository.save(brand);
        }else {
            brand.setStatus(1);
            roleRepository.save(brand);
        }
    }

    @Override
    public boolean isColorDataDuplicate(Role role) {
        return roleRepository.existsByName(role.getName());
    }
}
