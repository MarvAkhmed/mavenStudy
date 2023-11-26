package itis.Servlet;

import itis.Modules.User;
import itis.repository.AccountRepositoryJDBCImpl;
import itis.repository.AccountsRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@WebServlet("/save")
public class ServerServlet extends HttpServlet {
    private static final String DB_USERNAME = "postgres";
    private static final String DB_PASSWORD = "reallyStrongPwd123";
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/hw3";
    private AccountsRepository accountsRepository;


    @Override
    public void init() throws ServletException {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            if (connection != null) {
                System.out.println("Database connection established");
            } else {
                System.out.println("Database connection is null");
            }
            accountsRepository = new AccountRepositoryJDBCImpl(connection);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/html/save.html").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws  IOException {
        String accountName = request.getParameter("name");
        String accountSurname = request.getParameter("surname");
        Integer accountAge = Integer.valueOf(request.getParameter("age"));

        User user = User.builder()
                .nameOfUser(accountName)
                .surnameOfUser(accountSurname)
                .ageOfUser(accountAge)
                .build();


        try {
            accountsRepository.save(user);
            System.out.println("User saved successfully");
            response.sendRedirect("/minimal");
        } catch (SQLException e) {
            response.sendRedirect("/notSaved");
        }
    }
}