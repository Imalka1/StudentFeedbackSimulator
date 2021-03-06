package controller.url_controller.admin.student;

import controller.db_controller.StudentController;
import model.Student;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/delete_student")//---URL extension which mapped to this servlet object
public class DeleteStudentController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //---------------------------------Retrieve data which submitted to the server----------------------------------
        String regNo = req.getParameter("regNo").trim();

        //------------------------------------------Set data to model object--------------------------------------------
        Student student = new Student();
        student.setuId(regNo);

        if (new StudentController().deleteStudent(student)) {//---Call the db server (UserController(db_controller)) to delete student
            resp.getWriter().println(true);
        } else {
            resp.getWriter().println(false);
        }
    }
}
