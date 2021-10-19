package com.memeteam.cse360project.orm;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.function.Function; 

import com.memeteam.cse360project.models.*;



//Useful for connecting and getting data from the database.

public class DatabaseService {
    
    //The connection string to use for SQL actions
    private String _conString;
    private Function<ResultSet, User> _userParser;
    private Function<ResultSet, Integer> _countParser;

    //Constructor that takes the connection string
    public DatabaseService(String conString) {
        this._conString = conString;
        this._userParser = r -> UserParser(r);
        this._countParser = r -> CountParser(r);
    }

    //Fetch all of the data from database for the given query.
    private <T> List<T> Get(String sql, List<String> parameters, Function<ResultSet, T> parser) throws SQLException {
        
        //create a list to store our results in
        var results = new ArrayList<T>();
        //create the connection to the database
        Connection con = DriverManager.getConnection(_conString);
        //create a prepared statement to query with
        var stmt = con.prepareStatement(sql);

        //Add all of our parameters to our prepared statement
        int i = 0;
        for (String key : parameters) {
            stmt.setString(i + 1, key);
            i++;
        }

        //Execute the query
        var rs = stmt.executeQuery();

        //Read all of the results from the query using the parser to fetch each record
        while(rs.next()) {
            //Use the parser to get this records data
            T current = parser.apply(rs);
            //Add the record to our results list
            results.add(current);
        }

        //return the list of results.
        return results;
    }

    //overload for no parameters
    private <T> List<T> Get(String sql, Function<ResultSet, T> parser) throws SQLException {
        
        //create a list to store our results in
        var results = new ArrayList<T>();
        //create the connection to the database
        Connection con = DriverManager.getConnection(_conString);
        //create a prepared statement to query with
        var stmt = con.prepareStatement(sql);

        //Execute the query
        var rs = stmt.executeQuery();

        //Read all of the results from the query using the parser to fetch each record
        while(rs.next()) {
            //Use the parser to get this records data
            T current = parser.apply(rs);
            //Add the record to our results list
            results.add(current);
        }

        //return the list of results.
        return results;
    }

     //Execute with no query returns
     private void Exec(String sql, List<String> parameters) throws SQLException {
        //create the connection to the database
        Connection con = DriverManager.getConnection(_conString);
        //create a prepared statement to query with
        var stmt = con.prepareStatement(sql);

        //Add all of our parameters to our prepared statement
        int i = 0;
        for (String key : parameters) {
            stmt.setString(i + 1, key);
            i++;
        }
        stmt.execute();
    }

    public int GetUserCount() throws SQLException {
        //Create our SQL query
        String sql = "SELECT COUNT(*) FROM users";
        //fetch count without parsing user data
        var data = Get(sql, this._countParser);
        return data.get(0);
    }

    public User GetUserByUsername(String username) throws SQLException {
        //Create our SQL query
        var sql = "SELECT * FROM users WHERE upper(username) = ?";
        //Create a list to hold our parameters in
        var pars = new ArrayList<String>();
        //Add our "Username" parameter
        pars.add(username.toUpperCase(Locale.ROOT));
        //Fetch all of our data
        var data = Get(sql, pars, this._userParser);
        //If we have no data, return null
        if (data.size() == 0) return null;

        //Return the first record
        return data.get(0);
    }

    public User GetUserById(int currentUserID) throws SQLException {
        //Create our SQL query
        var sql = "SELECT * FROM users WHERE id = ?";
        //Create a list to hold our parameters in
        var pars = new ArrayList<String>();
        //Add our "Username" parameter
        pars.add(String.valueOf(currentUserID));
        //Fetch all of our data
        var data = Get(sql, pars, this._userParser);
        //If we have no data, return null
        if (data.size() == 0) return null;

        //Return the first record
        return data.get(0);
    }

    public int ConvertAge(ResultSet rs) throws SQLException{
        java.sql.Date currentDate = new java.sql.Date(Calendar.getInstance().getTime().getTime());
        return (currentDate.getYear() - rs.getDate("birthday").getYear());
    }

    private User UserParser(ResultSet rs) {
        try {
            var user = new User();

            user.setId(rs.getInt("id"));
            user.setFirstName(rs.getString("firstname"));
            user.setLastName(rs.getString("lastname"));
            user.setAge(ConvertAge(rs));
            user.setBirthday(rs.getDate("birthday"));
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            user.setMedical(rs.getString("medical"));
            user.setPhone(rs.getString("phone"));
            user.setEmail(rs.getString("email"));
            user.setMessage(rs.getString("message"));
            user.setWeight(rs.getInt("weight"));
            user.setHeight(rs.getString("height"));
            user.setTemperature(rs.getFloat("temperature"));
            user.setBloodpressure(rs.getString("bloodpressure"));
            user.setNursenotes(rs.getString("nursenotes"));
            user.setDoctornotes(rs.getString("doctornotes"));
            //user.setType(rs.getInt("type"));

            return user;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    //counts instances
    private int CountParser(ResultSet res) { 
        try { 
            return res.getInt("count(*)");
        } catch(SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public void registerUser(List<String> stringList) throws SQLException {
        String sql = "INSERT INTO Users (firstname, lastname, birthday, username, password, medical, phone, email) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        //Execute
        Exec(sql, stringList);
    }

    //Exec sql statements that don't have returns nor params
    private void ExecStmt(String sql){
        try(Connection con = DriverManager.getConnection(_conString);
        var stmt = con.createStatement()){
            stmt.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Checks if database exists, if it doesn't create a new one.
    public void CreateNewTable() throws SQLException {
        // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS users (\n"
                + "	id integer PRIMARY KEY AUTOINCREMENT,\n"
                + "	firstname text,\n"
                + "	lastname text,\n"
                + "	birthday date,\n"
                + "	username text,\n"
                + "	password text,\n"
                + "	medical text,\n"
                + "	phone text,\n"
                + "	email text,\n"
                + "	message text,\n"
                + "	weight int,\n"
                + "	height text,\n"
                + "	temperature float,\n"
                + "	bloodpressure text,\n"
                + "	nursenotes text,\n"
                + "	doctornotes text\n"
                + ");";
        ExecStmt(sql);
    }

    //For Doctors and Nurses to update
    public void PatientUpdate(User user) throws SQLException{
        //Create our SQL query
        String sql = "UPDATE users SET weight = ?, height = ?, bloodpressure = ?, temperature = ?, nursenotes = ?, doctornotes = ? WHERE id = ?";
        //Create a list to hold our parameters in
        var pars = new ArrayList<String>();
        List<String> userPars = Arrays.asList( String.valueOf(user.getWeight()), String.valueOf(user.getHeight()), 
        String.valueOf(user.getBloodpressure()), String.valueOf(user.getTemperature()), String.valueOf(user.getNursenotes()), 
        String.valueOf(user.getDoctornotes()), String.valueOf(user.getId()));
        //Adds all of our parameters
        pars.addAll(userPars);
        //Execute
        Exec(sql, pars);
    }

    //For users to update their own email and password
    public void UserUpdate(User user) throws SQLException{
        //Create our SQL query
        String sql = "UPDATE users SET email = ?, password = ?, phone = ?, medical = ?, message = ? WHERE id = ?";
        //Create a list to hold our parameters in
        var pars = new ArrayList<String>();
        List<String> userPars = Arrays.asList( String.valueOf(user.getEmail()), String.valueOf(user.getPassword()), 
        String.valueOf(user.getPhone()), String.valueOf(user.getMedical()), String.valueOf(user.getMessage()), String.valueOf(user.getId()));
        //Adds all of our parameters
        pars.addAll(userPars);
        //Fetch all of our data
        Exec(sql, pars);
    }
}
