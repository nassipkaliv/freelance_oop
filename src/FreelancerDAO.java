import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class FreelancerDAO {
    public void create(Freelancer freelancer) throws SQLException {
        String sql = "INSERT INTO freelancers (id, name, email, skills, salary) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = DatabaseConnection.getConnection().prepareStatement(sql)) {
            stmt.setString(1, freelancer.getId());
            stmt.setString(2, freelancer.getName());
            stmt.setString(3, freelancer.getEmail());
            stmt.setString(4, String.join(",", freelancer.getSkills()));
            stmt.setDouble(5, freelancer.getSalary());
            stmt.executeUpdate();
        }
    }

    public List<Freelancer> findAll() throws SQLException {
        List <Freelancer> freelancers = new ArrayList<>();
        String sql = "SELECT * FROM freelancers";

        try (Statement stmt = DatabaseConnection.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery(sql)) {
            while(rs.next()) {
                String skillsStr = rs.getString("skills");
                String[] skills = (skillsStr != null && !skillsStr.isEmpty())
                        ? skillsStr.split(",") : new String[]{};
                Freelancer f = new Freelancer(
                        rs.getString("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        skills,
                        rs.getDouble("salary")
                );
                freelancers.add(f);
            }
        }
        return freelancers;
    }

    public Freelancer findById(String id) throws SQLException {
        String sql = "SELECT * FROM freelancers WHERE id = ?";
        try (PreparedStatement stmt = DatabaseConnection.getConnection().prepareStatement(sql)) {
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();

            if(rs.next()) {
                String skillsStr = rs.getString("skills");
                String[] skills = (skillsStr != null && !skillsStr.isEmpty()) ?
                        skillsStr.split(",") : new String[]{};

                return new Freelancer(
                        rs.getString("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        skills,
                        rs.getDouble("salary")
                );
            }
        }
        return  null;
    }

    // UPDATE
    public void update(Freelancer freelancer) throws SQLException {
        String sql = "UPDATE freelancers SET name = ?, email = ?, skills = ?, salary = ? WHERE id = ?";
        try (PreparedStatement stmt = DatabaseConnection.getConnection().prepareStatement(sql)) {
            stmt.setString(1, freelancer.getName());
            stmt.setString(2, freelancer.getEmail());
            stmt.setString(3, String.join(",", freelancer.getSkills()));
            stmt.setDouble(4, freelancer.getSalary());
            stmt.setString(5, freelancer.getId());
            stmt.executeUpdate();
        }
    }

    // DELETE
    public void delete(String id) throws SQLException {
        String sql = "DELETE FROM freelancers WHERE id = ?";
        try(PreparedStatement stmt = DatabaseConnection.getConnection().prepareStatement(sql)) {
            stmt.setString(1, id);
            stmt.executeUpdate();
        }
    }
}
