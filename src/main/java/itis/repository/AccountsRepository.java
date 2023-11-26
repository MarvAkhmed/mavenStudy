package itis.repository;

import itis.Modules.User;

import java.sql.SQLException;

public interface AccountsRepository {
    void save(User user) throws SQLException;
}
