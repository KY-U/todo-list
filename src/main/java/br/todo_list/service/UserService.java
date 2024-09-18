package br.todo_list.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import br.todo_list.model.User;
import br.todo_list.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public User createUser(User user) {
        //System.out.println(user.getPassword());
        //String cryptographedPassword = (passwordEncoder.encode(user.getPassword()));
        //user.setPassword(cryptographedPassword);
        //user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public Optional<User> getUser(Long id) {
        return userRepository.findById(id);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public User getUserByEmail(String email){
        return userRepository.findByEmail(email);
    }

}
