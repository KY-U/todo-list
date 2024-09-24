package br.todo_list.service.interfaces;

import br.todo_list.model.User;

public interface IUserService {
    User createUser(User user);

    User getUserByEmail(String email);
}
