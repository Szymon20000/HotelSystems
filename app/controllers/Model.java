package controllers;

import play.db.Database;
import java.lang.reflect.Field;
import java.sql.*;
import java.util.NoSuchElementException;


/**
 * Created by szymon on 5/1/17.
 */
public abstract class Model {

    protected Database db;

    final void save() throws NoSuchFieldException, IllegalAccessException {
        if(db == null) {
            throw new NullDataBaseException();
        }

        boolean fresh = true;

        Connection connection = db.getConnection();
        String sql;
        sql = "SELECT id FROM " + getClassName() + " where id = " + getId();

        Statement statement = null;

        try {
            statement = connection.createStatement();

            ResultSet result = statement.executeQuery(sql);
            if(result.next()) {
                fresh = false;
            }

            result.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if(fresh) {
            insert(true);
        } else {
            update();
        }
    }

    final void get(int id) throws NoSuchFieldException, IllegalAccessException {
        if(db == null) {
            throw new NullDataBaseException();
        }

        Connection connection = db.getConnection();
        String sql;
        sql = "SELECT * FROM " + getClassName() + " where id = " + id;
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

    final void insert(boolean fresh) throws IllegalAccessException, NoSuchFieldException {
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
                if(fresh && index == 0) {
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
        Connection connection = db.getConnection();
        String sql;
        sql = "DELETE  from " + getClassName() + " where id = " + getId();

        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(sql);
            statement.executeUpdate();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        insert(false);
    }

    final int findMaxId() throws NoSuchFieldException, IllegalAccessException {
        int res = 0;
        Connection connection = db.getConnection();
        String sql;
        sql = "SELECT max(id) as id FROM " + this.getClass().getSimpleName().toLowerCase();

        Statement statement = null;

        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            ResultSet result = statement.executeQuery(sql);
            if(result.next()) {
                res = result.getInt(1);
            }

            result.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            connection.close();
        } catch (SQLException e) {
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

    final void setDb(Database db) {
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


