import javax.swing.plaf.nimbus.State;
import javax.xml.crypto.Data;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JobDAO {
    // CRUD
    // CREATE
    public void create(Job job) throws SQLException {
        String sql = "INSERT INTO jobs (id, title, budget, is_open) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = DatabaseConnection.getConnection().prepareStatement(sql)) {
            stmt.setString(1, job.getId());
            stmt.setString(2, job.getTitle());
            stmt.setDouble(3, job.getBudget());
            stmt.setBoolean(4, job.isOpen());
            stmt.executeUpdate();
        }
    }

    // READ
    public List<Job> findAll() throws SQLException {
        List<Job> jobs = new ArrayList<>();
        String sql = "SELECT * from jobs";

        try (Statement stmt = DatabaseConnection.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Job job = new Job(
                        rs.getString("id"),
                        rs.getString("title"),
                        rs.getDouble("budget"),
                        rs.getBoolean("is_open")
                );
                jobs.add(job);
            }
        }
        return jobs;
    }

    public Job findByid(String id) throws SQLException {
        String sql = "SELECT * FROM jobs WHERE id = ?";
        try (PreparedStatement stmt = DatabaseConnection.getConnection().prepareStatement(sql)) {
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();

            if(rs.next()) {
                return new Job(
                        rs.getString("id"),
                        rs.getString("title"),
                        rs.getDouble("budget"),
                        rs.getBoolean("is_open")
                );
            }
        }
        return null;
    }

    // UPDATE
    public void update(Job job) throws SQLException {
        String sql = "UPDATE jobs SET title = ?, budget = ?, is_open = ? WHERE id = ?";

        try (PreparedStatement stmt = DatabaseConnection.getConnection().prepareStatement(sql)) {
            stmt.setString(1, job.getTitle());
            stmt.setDouble(2, job.getBudget());
            stmt.setBoolean(3, job.isOpen());
            stmt.setString(4, job.getId());
            stmt.executeUpdate();
        }
    }

    // DELETE
    public void delete(String id) throws SQLException {
        String sql = "DELETE FROM jobs WHERE id = ?";

        try (PreparedStatement stmt = DatabaseConnection.getConnection().prepareStatement(sql)) {
            stmt.setString(1, id);

        }
    }
}
