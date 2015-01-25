package com.diosoft.calendar.objects.datastore;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CalendarDataStoreSQL {

    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void selectAll() {

        String sql = "SELECT * FROM  users";
        Connection conn = null;
        ResultSet resultSet;
        //local code review (vtegza): look into spring jdbcTemplate @ 1/25/2015
        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);

            resultSet = ps.executeQuery(sql);

            while (resultSet.next()) {
                System.out.println(resultSet.getString(2));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);

        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    //local code review (vtegza): do not live empty catch block @ 1/25/2015
                }
            }
        }

    }

}
