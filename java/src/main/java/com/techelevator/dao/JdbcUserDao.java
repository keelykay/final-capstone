package com.techelevator.dao;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import com.techelevator.exception.DaoException;
import com.techelevator.model.RegisterUserDto;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.techelevator.model.User;

@Component
public class JdbcUserDao implements UserDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcUserDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public User getUserById(int userId) {
        User user = null;
        String sql = "SELECT * FROM users WHERE user_id = ?"; //this works
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);
            if (results.next()) {
                user = mapRowToUser(results);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return user;
    }

    @Override
    public List<User> getUsers() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT user_id, username, password_hash, role FROM users"; //this works
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
            while (results.next()) {
                User user = mapRowToUser(results);
                users.add(user);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return users;
    }

    @Override
    public User getUserByUsername(String username) {
        if (username == null) throw new IllegalArgumentException("Username cannot be null");
        User user = null;
        String sql = "SELECT * FROM users WHERE username = LOWER(TRIM(?));"; //this works
        try {
            SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, username);
            if (rowSet.next()) {
                user = mapRowToUser(rowSet);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return user;
    }

    @Override
    public User createUser(RegisterUserDto user) {
        User newUser = null;

        String password_hash = new BCryptPasswordEncoder().encode(user.getPassword());
        String ssRole = user.getRole().toUpperCase().startsWith("ROLE_") ? user.getRole().toUpperCase() : "ROLE_" + user.getRole().toUpperCase();

        String firstName = user.getFirstName();
        String lastName = user.getLastName();
        String middleInitials = user.getMiddleInitials();
        String gender;
        if(user.getGender() != null && user.getGender().isEmpty()){
            gender = "Other";
        }else {
            gender = user.getGender();
        }
        String phoneNumber = user.getPhoneNumber();
        String email = user.getEmail();
        LocalDate dateOfBirth = user.getDateOfBirth();

        //properties of doctor
        LocalTime hoursFrom = null;
        LocalTime hoursTo = null;
        boolean isMonday = false;
        boolean isTuesday = false;
        boolean isWednesday = false;
        boolean isThursday = false;
        boolean isFriday = false;
        boolean isSaturday = false;
        boolean isSunday = false;

        //properties of patient
        String address = user.getAddress();
        String city = user.getCity();
        String stateAbbreviation = user.getStateAbbreviation();
        String zipcode = user.getZipcode();

        if(user.getRole().equals("provider")) {
            //only doctor
            hoursFrom = user.getHoursFrom();
            hoursTo = user.getHoursTo();
            isMonday = user.isOpenMonday();
            isTuesday = user.isOpenTuesday();
            isWednesday = user.isOpenWednesday();
            isThursday = user.isOpenThursday();
            isFriday = user.isOpenFriday();
            isSaturday = user.isOpenSaturday();
            isSunday = user.isOpenSunday();
        }

        String insertUserSql = "INSERT INTO users " + //this works
                "(" +
                "username, " +
                "password_hash, " +
                "role, " +
                "first_name, " +
                "last_name, " +
                "middle_initials, " +
                "gender, " +
                "phone_number, " +
                "email, " +
                "date_of_birth, " +
                "address, " +
                "city, " +
                "state_abbreviation, " +
                "zip_code, " +
                "hours_from, " +
                "hours_to, " +
                "is_monday, " +
                "is_tuesday, " +
                "is_wednesday, " +
                "is_thursday, " +
                "is_friday, " +
                "is_saturday, " +
                "is_sunday " +
                ") values (LOWER(TRIM(?)), ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) RETURNING user_id";

        try {
            int newUserId = jdbcTemplate.queryForObject(insertUserSql, int.class,
                    user.getUsername(),
                    password_hash,
                    ssRole,
                    firstName,
                    lastName,
                    middleInitials,
                    gender,
                    phoneNumber,
                    email,
                    dateOfBirth,
                    address,
                    city,
                    stateAbbreviation,
                    zipcode,
                    hoursFrom,
                    hoursTo,
                    isMonday,
                    isTuesday,
                    isWednesday,
                    isThursday,
                    isFriday,
                    isSaturday,
                    isSunday
                    );

            newUser = getUserById(newUserId);

        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }catch(NullPointerException error){
            throw new DaoException("Unable to process user data, Null pointer exception", error);
        }
        return newUser;
    }

    @Override
    public User updateUser(User user, int userId){

        User updatedUser = getUserById(userId);

        if(user.getFirstName() != null){
            updatedUser.setFirstName(user.getFirstName());
        }
        if(user.getLastName()!= null){
            updatedUser.setLastName(user.getLastName());
        }
        if(user.getMiddleInitials() != null){
            updatedUser.setMiddleInitials(user.getMiddleInitials());
        }
        if(user.getGender() != null){
            updatedUser.setGender(user.getGender());
        }
        if(user.getPhoneNumber()!= null){
            updatedUser.setPhoneNumber(user.getPhoneNumber());
        }
        if(user.getEmail()!= null){
            updatedUser.setEmail(user.getEmail());
        }
        if(user.getDateOfBirth()!= null){
            updatedUser.setDateOfBirth(user.getDateOfBirth());
        }
        if(user.getAddress()!= null){
            updatedUser.setAddress(user.getAddress());
        }
        if(user.getCity() != null){
            updatedUser.setAddress(user.getAddress());
        }
        if(user.getStateAbbreviation()!= null){
            updatedUser.setStateAbbreviation(user.getStateAbbreviation());
        }
        if(user.getZipcode()!= null){
            updatedUser.setZipcode(user.getZipcode());
        }
        if(user.getHoursFrom()!= null){
            updatedUser.setHoursFrom(user.getHoursFrom());
        }
        if(user.getHoursTo()!= null){
            updatedUser.setHoursTo(user.getHoursTo());
        }

        updatedUser.setOpenMonday(user.isOpenMonday());
        updatedUser.setOpenTuesday(user.isOpenTuesday());
        updatedUser.setOpenWednesday(user.isOpenWednesday());
        updatedUser.setOpenThursday(user.isOpenThursday());
        updatedUser.setOpenFriday(user.isOpenFriday());
        updatedUser.setOpenSaturday(user.isOpenSaturday());
        updatedUser.setOpenSunday(user.isOpenSunday());

        String sql = "UPDATE users SET " +
                "first_name = ?, " +
                "last_name = ?, " +
                "middle_initials = ?, " +
                "gender = ?, " +
                "phone_number = ?, " +
                "email = ?, " +
                "date_of_birth = ?, " +
                "address = ?, " +
                "city = ?, " +
                "state_abbreviation = ?, " +
                "zip_code = ?, " +
                "hours_from = ?, " +
                "hours_to = ?, " +
                "is_monday = ?, " +
                "is_tuesday = ?, " +
                "is_wednesday = ?, " +
                "is_thursday = ?, " +
                "is_friday = ?, " +
                "is_saturday = ?, " +
                "is_sunday = ? " +
                "WHERE user_id = ?";

        try{
            int rowsAffected = jdbcTemplate.update(sql,
                    updatedUser.getFirstName(),
                    updatedUser.getLastName(),
                    updatedUser.getMiddleInitials(),
                    updatedUser.getGender(),
                    updatedUser.getPhoneNumber(),
                    updatedUser.getEmail(),
                    updatedUser.getDateOfBirth(),
                    updatedUser.getAddress(),
                    updatedUser.getCity(),
                    updatedUser.getStateAbbreviation(),
                    updatedUser.getZipcode(),
                    updatedUser.getHoursFrom(),
                    updatedUser.getHoursTo(),
                    updatedUser.isOpenMonday(),
                    updatedUser.isOpenTuesday(),
                    updatedUser.isOpenWednesday(),
                    updatedUser.isOpenThursday(),
                    updatedUser.isOpenFriday(),
                    updatedUser.isOpenSaturday(),
                    updatedUser.isOpenSunday(),
                    userId
                    );
            if(rowsAffected == 0){
                throw new DaoException("Zero rows affected, expected at least one");
            }
            else{
                updatedUser = getUserById(userId);
            }


        }catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }catch(NullPointerException error){
            throw new DaoException("Unable to process user data, Null pointer exception", error);
        }

        return updatedUser;
    }



    private User mapRowToUser(SqlRowSet rs) {
        User user = new User();
        user.setId(rs.getInt("user_id"));
        user.setUsername(rs.getString("username"));
        user.setPassword(rs.getString("password_hash"));
        user.setAuthorities(Objects.requireNonNull(rs.getString("role")));
        user.setActivated(true);
        user.setFirstName(rs.getString("first_name")); //fails here
        user.setLastName(rs.getString("last_name"));
        user.setMiddleInitials(rs.getString("middle_initials"));
        user.setGender(rs.getString("gender"));
        user.setPhoneNumber(rs.getString("phone_number"));
        user.setEmail(rs.getString("email"));
        user.setAddress(rs.getString("address"));
        user.setCity(rs.getString("city"));
        user.setStateAbbreviation(rs.getString("state_abbreviation"));
        user.setZipcode(rs.getString("zip_code"));
        try {
            //will produce null pointers if date or time is null
            if(rs.getDate("date_of_birth") != null){
                user.setDateOfBirth(rs.getDate("date_of_birth").toLocalDate());
            }
            if(rs.getString("hours_from") != null){
                user.setHoursFrom(LocalTime.parse( rs.getString("hours_from")));
            }
            if(rs.getString("hours_to") != null){
                user.setHoursTo(LocalTime.parse( rs.getString("hours_to")) );
            }



            user.setOpenMonday(rs.getBoolean("is_monday"));
            user.setOpenTuesday(rs.getBoolean("is_tuesday"));
            user.setOpenWednesday(rs.getBoolean("is_wednesday"));
            user.setOpenThursday(rs.getBoolean("is_thursday"));
            user.setOpenFriday(rs.getBoolean("is_friday"));
            user.setOpenSaturday(rs.getBoolean("is_saturday"));
            user.setOpenSunday(rs.getBoolean("is_sunday"));

//            user.setOpenMonday((boolean) rs.getObject("is_monday"));
//            user.setOpenTuesday((boolean) rs.getObject("is_tuesday"));
//            user.setOpenWednesday((boolean) rs.getObject("is_wednesday"));
//            user.setOpenThursday((boolean) rs.getObject("is_thursday"));
//            user.setOpenFriday((boolean) rs.getObject("is_friday"));
//            user.setOpenSaturday((boolean) rs.getObject("is_saturday"));
//            user.setOpenSunday((boolean) rs.getObject("is_sunday"));
        }catch (NullPointerException error){
            throw new DaoException("Null pointer exception for a user value", error);
        }catch(Exception error){
            throw new DaoException("general mapper error", error);
        }
        return user;
    }

}
