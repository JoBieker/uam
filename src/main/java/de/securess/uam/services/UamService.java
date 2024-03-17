package de.securess.uam.services;

import de.securess.uam.data.Role;
import de.securess.uam.data.RoleRepository;
import de.securess.uam.data.Unit;
import de.securess.uam.data.UnitRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UamService {

    private final RoleRepository roleRepository;
    private final UnitRepository unitRepository;

    public UamService(RoleRepository roleRepository, UnitRepository unitRepository) {
        this.roleRepository = roleRepository;
        this.unitRepository = unitRepository;
    }

    public List<Role> findAllRoles(String stringFilter) {
        if (stringFilter == null || stringFilter.isEmpty()) {
            return roleRepository.findAll();
        } else {
            return roleRepository.search(stringFilter);
        }
    }


    public long countRoles() {
        return roleRepository.count();
    }

    public void deleteRole(Role role) {
        roleRepository.delete(role);
    }

    public void saveRole(Role role) {
        if (role == null) {
            System.err.println("Role is null");
            return;
        }
        roleRepository.save(role);
    }

    public List<Unit> findAllUnits() {
        return unitRepository.findAll();
    }

    public List<Unit> findAllUnits(String stringFilter) {
        if (stringFilter == null || stringFilter.isEmpty()) {
            return unitRepository.findAll();
        } else {
            return unitRepository.search(stringFilter);
        }
    }

}
