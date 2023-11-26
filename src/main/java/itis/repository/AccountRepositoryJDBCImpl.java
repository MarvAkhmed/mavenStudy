package itis.repository;

import itis.Modules.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AccountRepositoryJDBCImpl implements AccountsRepository{

    private final Connection connection;

    private static final String SQL_INSERT = "insert into accounts(first_name, second_name, age) values ";

    public AccountRepositoryJDBCImpl(Connection connection){
       this.connection = connection;
   }
    @Override
    public void save(User user) throws SQLException {
       String sql = SQL_INSERT + "(?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,user.getNameOfUser());
        preparedStatement.setString(2,user.getSurnameOfUser());
        preparedStatement.setInt(3,user.getAgeOfUser());
        preparedStatement.executeUpdate();
        System.out.println("Done");
    }
}
