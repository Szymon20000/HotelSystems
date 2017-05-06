package models;

import play.db.Database;
import java.lang.reflect.Field;
import java.sql.*;
import java.util.NoSuchElementException;

public abstract class Model {

    protected Database db;

    public final void save() throws NoSuchFieldException, IllegalAccessException {
        if(db == null) {
            throw new NullDataBaseException();
        }

        boolean insertNew = true;

        Connection connection = db.getConnection();
        String sql;
        sql = "SELECT id FROM " + getClassName() + " WHERE id = " + getId();

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

        if(insertNew) {
            insert(true);
        } else {
            update();
        }
    }

    public final void get(int id) throws NoSuchFieldException, IllegalAccessException {
        if(db == null) {
            throw new NullDataBaseException();
        }

        Connection connection = db.getConnection();
        String sql;
        sql = "SELECT * FROM " + getClassName() + " WHERE id = " + id;
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

    final void insert(boolean insertNew) throws IllegalAccessException, NoSuchFieldException {
        Connection connection = db.getConnection();
        String sql;
        sql = "INSERT INTO " + getClassName() + " VALUES ";

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

    final void update() throws IllegalAccessException, NoSuchFieldException {
        delete();
        insert(false);
    }

    public final void delete() throws NoSuchFieldException, IllegalAccessException {
        Connection connection = db.getConnection();
        String sql;
        sql = "DELETE  FROM " + getClassName() + " WHERE id = " + getId();

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
        sql = "SELECT max(id) AS id FROM " + getClassName();

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

    public final boolean find(String fieldName, Object val) throws NoSuchFieldException, IllegalAccessException {
        if(db == null) {
            throw new NullDataBaseException();
        }

        boolean found = false;

        Connection connection = db.getConnection();
        String sql;
        sql = "SELECT * FROM " + getClassName() + " WHERE "+ makeSql(fieldName) + " = ?";
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

    public final void setDb(Database db) {
        this.db = db;
    }

    String getId() throws NoSuchFieldException, IllegalAccessException {
        return this.getClass().getDeclaredField("id").get(this).toString();
    }

    String getClassName() {
        return this.getClass().getSimpleName().toLowerCase();
    }
}

class NullDataBaseException extends  RuntimeException {}


