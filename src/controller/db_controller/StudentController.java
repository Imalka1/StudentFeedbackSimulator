package controller.db_controller;

import db.DBConnection;
import model.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentController {

    //--------------------------------Get student landing page data via student's user id-------------------------------
    public Student getStudentLandingPageData(Student student) {
        Student studentObj = null;
        try {
            Connection connection = DBConnection.getDBConnection().getConnection();//---Get database connection
            PreparedStatement preparedStatement = connection.prepareStatement("" +
                    "select f.name,d.name,d.degreeId,sem.name,sem.semesterId,student_name,b.name " +
                    "from student s,degree d,faculty f,semester sem,batch b " +
                    "where s.degreeId=d.degreeId && f.facultyId=d.facultyId && s.semesterId=sem.semesterId && b.batchId=s.batchId && s.uId=?");//---Prepare sql as a java object
            preparedStatement.setObject(1, student.getuId());//---Set values to sql object
            ResultSet rst = preparedStatement.executeQuery();//---Execute sql and store result
            if (rst.next()) {//---Navigate pointer to results
                studentObj = new Student();
                studentObj.setFacultyName(rst.getString(1));//---Set table row data to student model object
                studentObj.setDegreeName(rst.getString(2));//---Set table row data to student model object
                studentObj.setDegreeId(rst.getInt(3));//---Set table row data to student model object
                studentObj.setSemesterName(rst.getString(4));//---Set table row data to student model object
                studentObj.setSemesterId(rst.getInt(5));//---Set table row data to student model object
                studentObj.setStudentName(rst.getString(6));//---Set table row data to student model object
                studentObj.setBatchName(rst.getString(7));//---Set table row data to student model object
            }
        } catch (SQLException e) {//--Catch if any sql exception occurred
            e.printStackTrace();
        }
        return studentObj;//---Return student object if user exist, if not return null
    }

    //-------------------------------Get student national id number via student's user id-------------------------------
    public Student getStudentNic(Student student) {
        Student studentObj = null;
        try {
            Connection connection = DBConnection.getDBConnection().getConnection();//---Get database connection
            PreparedStatement preparedStatement = connection.prepareStatement("select national_id from student where uId=?");//---Prepare sql as a java object
            preparedStatement.setObject(1, student.getuId());//---Set values to sql object
            ResultSet rst = preparedStatement.executeQuery();//---Execute sql and store result
            if (rst.next()) {//---Navigate pointer to results
                studentObj = new Student();//--Creates student object if nic exist
                studentObj.setNationalId(rst.getString(1));//---Set table row data to student model object
            }
        } catch (SQLException e) {//--Catch if any sql exception occurred
            e.printStackTrace();
        }
        return studentObj;//---Return student object if nic exist, if not return null
    }

    //-------------------------------Get all students via degree id, batch id, semester id------------------------------
    public List<Student> getAllStudents(Student student) {
        List<Student> students = new ArrayList<>();//---Creates an array object (ArrayList) to store multiple objects
        try {
            Connection connection = DBConnection.getDBConnection().getConnection();//---Get database connection
            PreparedStatement preparedStatement = connection.prepareStatement("" +
                    "select u.uId,student_name,national_id,emailAddress " +
                    "from student s,batch b,degree d,user u " +
                    "where b.batchId=s.batchId && d.degreeId=s.degreeId && u.uId=s.uId && d.degreeId=? && b.batchId=? && s.semesterId=? " +
                    "order by studentId desc");//---Prepare sql as a java object
            preparedStatement.setObject(1, student.getDegreeId());//---Set values to sql object
            preparedStatement.setObject(2, student.getBatchId());//---Set values to sql object
            preparedStatement.setObject(3, student.getSemesterId());//---Set values to sql object
            ResultSet rst = preparedStatement.executeQuery();//---Execute sql and store result
            while (rst.next()) {//---Navigate pointer to result rows until it ends
                Student studentObj = new Student();//---Creates a student object
                studentObj.setuId(rst.getString(1));//---Set table row data to student model object
                studentObj.setStudentName(rst.getString(2));//---Set table row data to student model object
                studentObj.setNationalId(rst.getString(3));//---Set table row data to student model object
                studentObj.setEmailAddress(rst.getString(4));//---Set table row data to student model object
                students.add(studentObj);//---Add student object to array object
            }
        } catch (SQLException e) {//--Catch if any sql exception occurred
            e.printStackTrace();
        }
        return students;//---Return students array object with a length > 0 if students exists, if not array object returns with a length = 0
    }

    //--------------------Change the semester of all students for respective faculty and batch--------------------------
    public boolean changeSemester(Student student) {
        try {
            Connection connection = DBConnection.getDBConnection().getConnection();//---Get database connection
            PreparedStatement preparedStatement = connection.prepareStatement("" +
                    "update student s,degree d " +
                    "set semesterId=? " +
                    "where semesterId=? && d.degreeId=s.degreeId && facultyId=? && batchId=?");//---Prepare sql as a java object
            preparedStatement.setObject(1, student.getSemesterId());//---Set values to sql object
            preparedStatement.setObject(2, student.getSemesterId() - 1);//---Set values to sql object
            preparedStatement.setObject(3, student.getFacultyId());//---Set values to sql object
            preparedStatement.setObject(4, student.getBatchId());//---Set values to sql object
            if (preparedStatement.executeUpdate() > 0) {//---Execute sql and returns whether it was executed or not
                return true;
            }
        } catch (SQLException e) {//---Catch if any sql exception occurred
            e.printStackTrace();
        }
        return false;//---Returns if update fails
    }

    //---------------------------------------------------Add student----------------------------------------------------
    public boolean addStudent(Student student) {
        try {
            Connection connection = DBConnection.getDBConnection().getConnection();//---Get database connection
            PreparedStatement preparedStatement = connection.prepareStatement("" +
                    "insert into student (uId,degreeId,batchId,semesterId,student_name,national_id) " +
                    "values (?,?,?,?,?,?)");//---Prepare sql as a java object
            preparedStatement.setObject(1, student.getuId());//---Set values to sql object
            preparedStatement.setObject(2, student.getDegreeId());//---Set values to sql object
            preparedStatement.setObject(3, student.getBatchId());//---Set values to sql object
            preparedStatement.setObject(4, student.getSemesterId());//---Set values to sql object
            preparedStatement.setObject(5, student.getStudentName());//---Set values to sql object
            preparedStatement.setObject(6, student.getNationalId());//---Set values to sql object
            if (preparedStatement.executeUpdate() > 0) {//---Execute sql and returns whether it was executed or not
                return true;
            }
        } catch (SQLException e) {//---Catch if any sql exception occurred
            e.printStackTrace();
        }
        return false;
    }

    //-------------------------------------------------Update student---------------------------------------------------
    public boolean updateStudent(Student student) {
        try {
            Connection connection = DBConnection.getDBConnection().getConnection();//---Get database connection
            PreparedStatement preparedStatement = connection.prepareStatement("" +
                    "update student " +
                    "set degreeId=?,batchId=?,semesterId=?,student_name=?,national_id=? " +
                    "where uId=?");//---Prepare sql as a java object
            preparedStatement.setObject(1, student.getDegreeId());//---Set values to sql object
            preparedStatement.setObject(2, student.getBatchId());//---Set values to sql object
            preparedStatement.setObject(3, student.getSemesterId());//---Set values to sql object
            preparedStatement.setObject(4, student.getStudentName());//---Set values to sql object
            preparedStatement.setObject(5, student.getNationalId());//---Set values to sql object
            preparedStatement.setObject(6, student.getuId());//---Set values to sql object
            if (preparedStatement.executeUpdate() > 0) {//---Execute sql and returns whether it was executed or not
                return true;
            }
        } catch (SQLException e) {//---Catch if any sql exception occurred
            e.printStackTrace();
        }
        return false;
    }

    //-------------------------------------------------Delete student---------------------------------------------------
    public boolean deleteStudent(Student student) {
        try {
            Connection connection = DBConnection.getDBConnection().getConnection();//---Get database connection
            PreparedStatement preparedStatement = connection.prepareStatement("delete from user where uId=?");//---Prepare sql as a java object
            preparedStatement.setObject(1, student.getuId());//---Set values to sql object
            if (preparedStatement.executeUpdate() > 0) {//---Execute sql and returns whether it was executed or not
                return true;
            }
        } catch (SQLException e) {//---Catch if any sql exception occurred
            e.printStackTrace();
        }
        return false;
    }
}
