package com.example;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.function.Supplier;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Test
    void findUser_executesPreparedSelectQuery() throws Exception {
        Connection conn = mock(Connection.class);
        PreparedStatement ps = mock(PreparedStatement.class);
        ResultSet rs = mock(ResultSet.class);

        when(conn.prepareStatement(anyString())).thenReturn(ps);
        when(ps.executeQuery()).thenReturn(rs);

        Supplier<Connection> supplier = () -> conn;
        UserService service = new UserService(supplier);

        service.findUser("alice");

        verify(conn).prepareStatement("SELECT id, name FROM users WHERE name = ?");
        verify(ps).setString(1, "alice");
        verify(ps).executeQuery();
        verify(ps).close();
        verify(conn).close();
    }

    @Test
    void deleteUser_executesPreparedDeleteQuery() throws Exception {
        Connection conn = mock(Connection.class);
        PreparedStatement ps = mock(PreparedStatement.class);

        when(conn.prepareStatement(anyString())).thenReturn(ps);
        when(ps.executeUpdate()).thenReturn(1);

        Supplier<Connection> supplier = () -> conn;
        UserService service = new UserService(supplier);

        service.deleteUser("bob");

        verify(conn).prepareStatement("DELETE FROM users WHERE name = ?");
        verify(ps).setString(1, "bob");
        verify(ps).executeUpdate();
        verify(ps).close();
        verify(conn).close();
    }
}