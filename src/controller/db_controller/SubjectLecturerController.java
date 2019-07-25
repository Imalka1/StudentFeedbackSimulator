package controller.db_controller;

import db.DBConnection;
import model.Lecturer;
import model.Subject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SubjectLecturerController {
    public List<Lecturer> getAllLecturersViaSubject(Subject subject) {
        List<Lecturer> lecturers = new ArrayList<>();
        try {
            Connection connection = DBConnection.getDBConnection().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select l.lecid,l.name from lecturer l,subject_lecturer sl where l.lecid=sl.lecid && subid=?");
            preparedStatement.setObject(1, subject.getSubjectId());
            ResultSet rst = preparedStatement.executeQuery();
            while (rst.next()) {
                Lecturer lecturer = new Lecturer();
                lecturer.setLecId(rst.getString(1));
                lecturer.setLecturerName(rst.getString(2));
                lecturers.add(lecturer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lecturers;
    }
}
