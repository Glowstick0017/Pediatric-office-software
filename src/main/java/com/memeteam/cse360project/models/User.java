package com.memeteam.cse360project.models;

import java.sql.Date;

public class User {
    public static int DOCTOR_TYPE = 1;
    public static int NURSE_TYPE = 2;
    public static int USER_TYPE = 0;
    
    private int _id;
    private String _firstName;
    private String _lastName;
    private Date _birthday;
    private int _age;
    private String _username;
    private String _password;
    private String _medical;
    private String _phone;
    private String _email;
    private String _message;
    private int _weight;
    private String _height;
    private float _temperature;
    private int _bloodpressure;
    private String _nursenotes;
    private String _doctornotes;

    private int _type;

    //C# get set would be nice but it's java, bleh.


    public int getId() {
        return this._id;
    }
    public void setId(int id) {
        this._id = id;
    }
    
    public String getFirstName() {
        return this._firstName;
    }
    public void setFirstName(String firstName) {
        this._firstName = firstName;
    }
    
    public String getLastName() {
        return this._lastName;
    }
    public void setLastName(String lastName) {
        this._lastName = lastName;
    }

    public Date getBirthday(){
        return this._birthday;
    }

    public void setBirthday(Date birthday){
        this._birthday = birthday;
    }

    public String getFullName(){
        return this._firstName + " " + this._lastName;
    }
    
    public int getAge() {
        return this._age;
    }
    public void setAge(int age) {
        this._age = age;
    }
    
    public String getUsername() {
        return this._username;
    }
    public void setUsername(String username) {
        this._username = username;
    }
    
    public String getPassword() {
        return this._password;
    }
    public void setPassword(String password) {
        this._password = password;
    }
    
    public String getMedical() {
        return this._medical;
    }
    public void setMedical(String medical) {
        this._medical = medical;
    }
    
    public String getPhone() {
        return this._phone;
    }
    public void setPhone(String phone) {
        this._phone = phone;
    }
    
    public String getEmail(){
        return this._email;
    }

    public void setEmail(String email){
        this._email = email;
    }

	public String getMessage() {
		return this._message;
	}

	public void setMessage(String _message) {
		this._message = _message;
	}

	public int getWeight() {
		return this._weight;
	}

	public void setWeight(int _weight) {
		this._weight = _weight;
	}

	public String getHeight() {
		return this._height;
	}

	public void setHeight(String _height) {
		this._height = _height;
	}

	public float getTemperature() {
		return this._temperature;
	}

	public void setTemperature(Float _temperature) {
		this._temperature = _temperature;
	}

	public int getBloodpressure() {
		return this._bloodpressure;
	}

	public void setBloodpressure(int _bloodpressure) {
		this._bloodpressure = _bloodpressure;
	}

	public String getNursenotes() {
		return this._nursenotes;
	}

	public void setNursenotes(String _nursenotes) {
		this._nursenotes = _nursenotes;
	}

	public String getDoctornotes() {
		return this._doctornotes;
	}

	public void setDoctornotes(String _doctornotes) {
		this._doctornotes = _doctornotes;
	}

    public int getType() {
        return this._type;
    }
    public void setType(int type) {
        this._type = type;
    }
}
