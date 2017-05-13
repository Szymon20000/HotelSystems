package models;

import play.api.Play;
import play.db.Database;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public abstract class Model {

    protected static Database db = Play.current().injector().instanceOf(Database.class);

    public void save()  {
        if(db == null) {
            throw new NullDataBaseException();
        }

        try {
            boolean insertNew = true;

            Connection connection = db.getConnection();
            String sql;
            sql = "SELECT id FROM \"" + getClassName() + "\" WHERE id = " + getId();

            Statement statement = null;

            try {
                statement = connection.createStatement();

                ResultSet result = statement.executeQuery(sql);
                if(result.next()) {
                    insertNew = false;
                }

                result.close();
                statement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            if (insertNew) {
                insert(true);
            } else {
                update();
            }
        }  catch (IllegalAccessException | NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    public final void loadById(int id) throws NoSuchFieldException, IllegalAccessException {
        if(db == null) {
            throw new NullDataBaseException();
        }

        Connection connection = db.getConnection();
        String sql;
        sql = "SELECT * FROM \"" + getClassName() + "\" WHERE id = " + id;
        Statement statement = null;

        try {
            statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);

            if(!result.next()) {
                throw new NoSuchElementException();
            } else {
                Field[] fields = this.getClass().getDeclaredFields();

                for(Field field : fields) {
                    field.set(this, result.getObject(makeSql(field.getName())));
                }

            }

            result.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private final void insert(boolean insertNew) throws IllegalAccessException, NoSuchFieldException {
        Connection connection = db.getConnection();
        String sql;
        sql = "INSERT INTO \"" + getClassName() + "\" VALUES ";

        sql = prepareBrackets(sql);
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(sql);
            Field[] fields = this.getClass().getDeclaredFields();

            int index = 0;
            for(Field field : fields) {
                if(insertNew && index == 0) {
                    statement.setObject(1, findMaxId()+1);
                    index++;
                    continue;
                }
                statement.setObject(++index, field.get(this));
            }

            statement.executeUpdate();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private final void update() throws IllegalAccessException, NoSuchFieldException {
        delete();
        insert(false);
    }

    public final void delete() throws NoSuchFieldException, IllegalAccessException {
        Connection connection = db.getConnection();
        String sql;
        sql = "DELETE FROM \"" + getClassName() + "\" WHERE id = " + getId();

        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(sql);
            statement.executeUpdate();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    final int findMaxId() throws NoSuchFieldException, IllegalAccessException {
        int res = 0;
        Connection connection = db.getConnection();
        String sql;
        sql = "SELECT max(id) AS id FROM \"" + getClassName() + "\"";

        Statement statement = null;

        try {
            statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);
            if(result.next()) {
                res = result.getInt(1);
            }

            result.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return res;
    }

    public final boolean load(String fieldName, Object val) throws NoSuchFieldException, IllegalAccessException {
        if(db == null) {
            throw new NullDataBaseException();
        }

        boolean found = false;

        Connection connection = db.getConnection();
        String sql;
        sql = "SELECT * FROM \"" + getClassName() + "\" WHERE "+ makeSql(fieldName) + " = ?";
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(sql);
            statement.setObject(1, val);
            ResultSet result = statement.executeQuery();

            if(!result.next()) {
                found = false;
            } else {
                Field[] fields = this.getClass().getDeclaredFields();

                for(Field field : fields) {
                    field.set(this, result.getObject(makeSql(field.getName())));
                }
                found = true;
            }

            result.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return found;
    }

    public static <T extends Model> T getById(int id, Class<T> cl) throws IllegalAccessException, InstantiationException, NoSuchFieldException {
        T res = cl.newInstance();
        res.loadById(id);
        return res;
    }

    public static <T extends Model> T find(String fieldName, Object val, Class<T> cl) throws IllegalAccessException, InstantiationException, NoSuchFieldException {
        T res = cl.newInstance();
        if(res.load(fieldName, val)) {
            return res;
        }
        return null;
    }

    public static <T extends Model> List<T> findAll(String fieldName, Object val, Class<T> cl)  {
        if(db == null) {
            throw new NullDataBaseException();
        }

        List<T> res = new ArrayList<>();

        try {
            Connection connection = db.getConnection();
            String sql;
            sql = "SELECT * FROM \"" + makeSql(cl.getSimpleName()) + "\" WHERE " + makeSql(fieldName) + " = ?";
            PreparedStatement statement = null;

            try {
                statement = connection.prepareStatement(sql);
                statement.setObject(1, val);
                ResultSet result = statement.executeQuery();

                while (result.next()) {
                    res.add(getById(result.getInt("id"), cl));
                }

                result.close();
                statement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }  catch (IllegalAccessException | InstantiationException | NoSuchFieldException e) {
            e.printStackTrace();
        }
        return res;
    }

    public static <T extends Model> List<T> findAll(Class<T> cl) {
        if(db == null) {
            throw new NullDataBaseException();
        }

        List<T> res = new ArrayList<>();

        try {
            Connection connection = db.getConnection();
            String sql;
            sql = "SELECT * FROM \"" + makeSql(cl.getSimpleName()) + "\"";
            PreparedStatement statement = null;

            try {
                statement = connection.prepareStatement(sql);
                ResultSet result = statement.executeQuery();

                while (result.next()) {
                    res.add(getById(result.getInt("id"), cl));
                }

                result.close();
                statement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        catch(IllegalAccessException | InstantiationException | NoSuchFieldException e) {
            e.printStackTrace();
        }
        return res;
    }

    final String prepareBrackets(String s) {
        Field[] fields = this.getClass().getDeclaredFields();

        StringBuilder sb = new StringBuilder(s);

        sb.append('(');

        for(Field field : fields) {
            sb.append("?,");
        }
        sb.deleteCharAt(sb.length()-1);
        sb.append(')');
        return sb.toString();
    }

    final static String makeSql(String s) {
        StringBuilder sb = new StringBuilder();
        sb.append((Character.toLowerCase(s.charAt(0))));
        for(int i = 1; i < s.length(); ++i) {
            if(Character.isUpperCase(s.charAt(i))) {
                sb.append('_');
            }
            sb.append((Character.toLowerCase(s.charAt(i))));
        }
        return sb.toString();
    }

    private String getId() throws NoSuchFieldException, IllegalAccessException {
        Object id = this.getClass().getDeclaredField("id").get(this);
        if(id == null)
            return null;
        return id.toString();
    }

    String getClassName() {
        return makeSql(this.getClass().getSimpleName());
    }
}

class NullDataBaseException extends DatabaseException {}
