package controller.url_controller.student;

import controller.db_controller.StudentController;
import model.StudentDTO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/update_student")
public class UpdateStudent extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int degree = Integer.parseInt(req.getParameter("degree"));
        int batch = Integer.parseInt(req.getParameter("batch"));
        String regNo = req.getParameter("regNo").trim();
        String studetName = req.getParameter("studetName").trim();
        String nationalId = req.getParameter("nationalId").trim();
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setUid(regNo);
        studentDTO.setStudentName(studetName);
        studentDTO.setNationalId(nationalId);
        studentDTO.setDegId(degree);
        studentDTO.setBatchId(batch);

        PrintWriter writer = resp.getWriter();
        if (new StudentController().updateStudent(studentDTO)) {
            writer.println(true);
        } else {
            writer.println(false);
        }
    }
}