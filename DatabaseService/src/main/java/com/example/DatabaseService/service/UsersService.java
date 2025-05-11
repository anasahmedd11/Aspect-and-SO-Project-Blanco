package com.example.DatabaseService.service;

import com.example.DatabaseService.DTO.createUserDTO;
import com.example.DatabaseService.DTO.updateUserDTO;
import com.example.DatabaseService.entity.Users;
import com.example.DatabaseService.repository.UsersRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UsersService {
    @Autowired
    private UsersRepository usersRepository;

    public List<Users> getAllUsers() {
        return usersRepository.findAll();
    }

    public Optional<Users> getUserById(Long id) {
        return usersRepository.findById(id);
    }

    public Users createUser(createUserDTO createUserDTO){
        Users user = new Users(
                createUserDTO.getEmail(),
                createUserDTO.getPassword(),
                createUserDTO.getFirstName(),
                createUserDTO.getLastName()
        );
        return usersRepository.save(user);
    }

    public Users updateUser(Long id, updateUserDTO updateUserDTO){
        return usersRepository.findById(id).map(user -> {
            user.setEmail(updateUserDTO.getEmail());
            user.setPassword(updateUserDTO.getPassword());
            user.setFirstName(updateUserDTO.getFirstName());
            user.setLastName(updateUserDTO.getLastName());
            return usersRepository.save(user);
        }).orElseThrow(() -> new RuntimeException("User not found with ID: " + id));
    }

    public void deleteUser(Long id){
        Users user = usersRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found with ID: " + id));
        usersRepository.deleteById(id);
    }

}
