package com.mamalimomen.dtos;

import com.mamalimomen.base.controllers.utilities.InValidDataException;
import com.mamalimomen.base.controllers.utilities.SecurityManager;

import java.io.Serializable;

public final class UserDTO implements Serializable {

    private static final long serialVersionUID = 7514504754105592800L;

    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String aboutMe;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) throws InValidDataException {
        if (!firstName.matches("(\\w\\s?){3,}")) {
            throw new InValidDataException("FirstName");
        }
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) throws InValidDataException {
        if (!lastName.matches("(\\w\\s?){3,}")) {
            throw new InValidDataException("LastName");
        }
        this.lastName = lastName;
    }

    public String getFullName() {
        return getFirstName() + " " + getLastName();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) throws InValidDataException {
        if (!password.matches("[a-zA-Z0-9]{3,}")) {
            throw new InValidDataException("Password");
        }
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) throws InValidDataException {
        if (!password.matches("[a-zA-Z0-9]{3,}")) {
            throw new InValidDataException("Password");
        }
        this.password = SecurityManager.getPasswordHash(password);
    }

    public String getAboutMe() {
        return aboutMe;
    }

    public void setAboutMe(String aboutMe) {
        this.aboutMe = aboutMe;
    }

    @Override
    public String toString() {
        return String.format("%s&n%s%n%s%n", getUsername(), getFullName(), getAboutMe());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        UserDTO user = (UserDTO) obj;
        return this.hashCode() == user.hashCode();
    }
}
