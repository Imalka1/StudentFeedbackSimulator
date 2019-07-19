package controller.url_controller.student;

import controller.db_controller.StudentController;
import model.Student;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(urlPatterns = "/load_students")
public class LoadStudents extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int degree = Integer.parseInt(req.getParameter("degree").trim());
        int year = Integer.parseInt(req.getParameter("year").trim());
        int batch = Integer.parseInt(req.getParameter("batch").trim());
//        int pageNo = Integer.parseInt(req.getParameter("page_no").trim());
        List<Student> allStudents = new StudentController().getAllStudents(degree, batch, year);
        JSONObject obj = new JSONObject();
        JSONArray studentsJson = new JSONArray();
        for (Student student : allStudents) {
            JSONObject studentJson = new JSONObject();
            studentJson.put("RegId", student.getUid());
            studentJson.put("StudentName", student.getStudentName());
            studentJson.put("NationalId", student.getNationalId());
            studentJson.put("EmailAddress", student.getEmailAddress());
            studentsJson.add(studentJson);
        }
        obj.put("Students",studentsJson);
        PrintWriter writer = resp.getWriter();
        writer.println(obj.toJSONString());
    }
}