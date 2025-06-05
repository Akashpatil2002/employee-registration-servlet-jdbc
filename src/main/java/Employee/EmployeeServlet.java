package Employee;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/formSubmit")
public class EmployeeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            Connection conn = DBConnection.getConnection();

            String sql = "INSERT INTO employees (first_name, last_name, email, phone, gender, dob, address, city, state, country, department, position, salary, `joining_date`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, request.getParameter("first_name"));
            ps.setString(2, request.getParameter("last_name"));
            ps.setString(3, request.getParameter("email"));
            ps.setString(4, request.getParameter("phone"));
            ps.setString(5, request.getParameter("gender"));
            ps.setString(6, request.getParameter("dob"));
            ps.setString(7, request.getParameter("address"));
            ps.setString(8, request.getParameter("city"));
            ps.setString(9, request.getParameter("state"));
            ps.setString(10, request.getParameter("country"));
            ps.setString(11, request.getParameter("department"));
            ps.setString(12, request.getParameter("position"));
            ps.setDouble(13, Double.parseDouble(request.getParameter("salary")));
            ps.setString(14, request.getParameter("joining_date"));

            int status = ps.executeUpdate();

            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            if (status > 0) {
                out.println("<h2>Employee Registered Successfully!</h2>");
            } else {
                out.println("<h2>Error Occurred!</h2>");
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
