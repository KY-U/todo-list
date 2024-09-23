package br.todo_list.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

    //@Autowired
    //private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User createUser(User user) {
        //System.out.println("Senha original: " + user.getPassword());  // Para verificar a senha original
        //String cryptedPassword = passwordEncoder.encode(user.getPassword());
        //System.out.println("Senha encriptada: " + cryptedPassword);
        //user.setPassword(cryptedPassword);
        //user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

        user.setPassword(passwordEncoder.encode(user.getPassword()));
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
