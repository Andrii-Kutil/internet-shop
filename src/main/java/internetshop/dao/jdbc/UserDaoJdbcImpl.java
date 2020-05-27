package internetshop.dao.jdbc;

import internetshop.dao.UserDao;
import internetshop.exceptions.DataProcessingException;
import internetshop.lib.Dao;
import internetshop.model.Role;
import internetshop.model.User;
import internetshop.util.ConnectionUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Dao
public class UserDaoJdbcImpl implements UserDao {

    @Override
    public Optional<User> findByLogin(String login) {
        String query = "SELECT * FROM users WHERE user_login = ?";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return Optional.of(getUserFromResultSet(resultSet));
            }
            return Optional.empty();
        } catch (SQLException e) {
            throw new DataProcessingException("User was not got", e);
        }
    }

    @Override
    public User create(User user) {
        String query = "INSERT INTO users (user_name, user_login, "
                + "user_password, user_salt) VALUES(?, ?, ?, ?)";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection
                    .prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, user.getName());
            statement.setString(2, user.getLogin());
            statement.setString(3, user.getPassword());
            statement.setBytes(4, user.getSalt());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                user.setId(resultSet.getLong(1));
            }
            setRole(user);
            return user;
        } catch (SQLException e) {
            throw new DataProcessingException("User was not created", e);
        }
    }

    @Override
    public Optional<User> get(Long id) {
        String query = "SELECT * FROM users WHERE user_id = ?";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return Optional.of(getUserFromResultSet(resultSet));
            }
            return Optional.empty();
        } catch (SQLException e) {
            throw new DataProcessingException("User was not got", e);
        }
    }

    @Override
    public User update(User user) {
        String query = "UPDATE users "
                + "SET user_name = ?, user_login = ?, user_password = ? WHERE user_id = ?";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, user.getName());
            statement.setString(2, user.getLogin());
            statement.setString(3, user.getPassword());
            statement.setLong(4, user.getId());
            statement.executeUpdate();
            deleteRole(user.getId());
            setRole(user);
            return user;
        } catch (SQLException e) {
            throw new DataProcessingException("User was not updated", e);
        }
    }

    @Override
    public boolean delete(Long id) {
        deleteRole(id);
        String query = "DELETE FROM users WHERE user_id = ?";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, id);
            if (statement.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException ex) {
            throw new DataProcessingException("User was not deleted", ex);
        }
        return false;
    }

    @Override
    public List<User> getAll() {
        List<User> users = new ArrayList<>();
        String query = "SELECT * FROM users";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                users.add(getUserFromResultSet(resultSet));
            }
        } catch (SQLException ex) {
            throw new DataProcessingException("Users were not found", ex);
        }
        return users;
    }

    private User getUserFromResultSet(ResultSet rs) throws SQLException {
        Long id = rs.getLong("user_id");
        String name = rs.getString("user_name");
        String login = rs.getString("user_login");
        String password = rs.getString("user_password");
        byte[] salt = rs.getBytes("user_salt");
        User user = new User(name, login, password, id, salt);
        user.setRole(getRole(id));
        return user;
    }

    private Set<Role> getRole(Long userId) {
        Set<Role> roles = new HashSet<>();
        String query = "SELECT users_roles.user_id AS id, "
                + "roles.role_name FROM users_roles INNER JOIN roles ON  users_roles.role_id"
                + "= roles.role_id WHERE users_roles.user_id = ?";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, userId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                roles.add(Role.of(resultSet.getString("role_name")));
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Role was not given", e);
        }
        return roles;
    }

    private void setRole(User user) {
        try (Connection connection = ConnectionUtil.getConnection()) {
            for (Role role : user.getRoles()) {
                String query = "INSERT INTO users_roles (user_id, role_id) VALUES(?, ?)";
                PreparedStatement statement = connection.prepareStatement(query);
                statement.setLong(1, user.getId());
                statement.setLong(2, getRoleId(role.getRoleName().name(), connection));
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Role was not added", e);
        }
    }

    private void deleteRole(Long userId) {
        try (Connection connection = ConnectionUtil.getConnection()) {
            String query = "DELETE FROM users_roles WHERE user_id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, userId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DataProcessingException("Role was not deleted", e);
        }
    }

    private Long getRoleId(String roleName, Connection connection) throws SQLException {
        String query = "SELECT role_id FROM roles WHERE role_name = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, roleName);
        ResultSet resultSet = statement.executeQuery();
        resultSet.next();
        return resultSet.getLong("role_id");
    }
}
