package repository;

import domain.Entitate;
import org.sqlite.SQLiteDataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class DBRepo extends Repository<Entitate> {
    private String JDBC_URL = "jdbc:sqlite:entitati";
    Connection connection;

    public DBRepo()
    {
        openConnection();
        createTable();
        loadDataInMemory();
//        initData();
    }

    private void openConnection()
    {
        SQLiteDataSource ds = new SQLiteDataSource();
        ds.setUrl(JDBC_URL);

        try {
            if (connection == null || connection.isClosed())
            {
                connection = ds.getConnection();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void closeConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            e. printStackTrace();
        }
    }

    private void createTable() {
        try (final Statement st = connection.createStatement()) {
            st.execute("CREATE TABLE IF NOT EXISTS entitati(p1 int, p2 varchar(400));");
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
    }

    private void loadDataInMemory() {
        for (Entitate e : getAll())
        {
//            entitati.add(e);
            super.add(e);
        }
    }

    private void initData() {
        List<Entitate> entitati = new ArrayList<>();
        entitati.add(new Entitate(1, "Ion Pop"));
        entitati.add(new Entitate(2, "Alexandra Ionescu"));
        entitati.add(new Entitate(3, "Radu Oprea"));
        try (PreparedStatement statement = connection.prepareStatement("INSERT INTO entitati VALUES (?,?)")) {
            for (Entitate e : entitati)
            {
                statement.setInt(1, e.getP1());
                statement.setString(2, e.getP2());
                statement.executeUpdate();
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public Collection<Entitate> getAll()
    {
        List<Entitate> entitati = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM entitati")) {
            ResultSet rs = statement.executeQuery();
            while (rs.next())
            {
                entitati.add(new Entitate(rs.getInt(1), rs.getString(2)));
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return entitati;
    }

    public void add(Entitate e) {
        super.add(e);
//        System.out.println("s-a afisat");
        try (PreparedStatement statement = connection.prepareStatement("INSERT INTO entitati VALUES (?,?)")) {
            statement.setInt(1, e.getP1());
            statement.setString(2, e.getP2());
            statement.executeUpdate();
        }
        catch (SQLException a)
        {
            a.printStackTrace();
        }
    }

    public void delete(int p1){
        try {
            super.delete(p1);
            try (PreparedStatement statement = connection.prepareStatement("DELETE FROM entitati WHERE p1=?")) {
                statement.setInt(1, p1);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(Entitate e){
        try {
            super.update(e);
            try (PreparedStatement statement = connection.prepareStatement("UPDATE entitati SET p2=? WHERE p1=?")) {
                statement.setString(1, e.getP2());
                statement.setInt(2, e.getP1());
                statement.executeUpdate();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }


}
