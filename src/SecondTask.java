import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Created by Ксения on 3/6/2016.
 */
public class SecondTask extends HttpServlet{
  @Override
   protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      //HttpSession hs = req.getSession(true);
      Cookie logcook;
      Cookie passcook;
      Cookie [] dataSaved = req.getCookies();
      String login = req.getParameter("login");
      String password = req.getParameter("password");
      if (login.isEmpty()&&password.isEmpty())
      {
          //login = (String) hs.getAttribute("login");//password = (String) hs.getAttribute("password");
          for (Cookie cook:dataSaved) {
              if (cook.getName().equals("login"))
                  login = cook.getValue();
              if (cook.getName().equals("password"))
                  password = cook.getValue();
          }
      }
      ReadWriter rw = new ReadWriter("D:\\IdeaProjects\\SecondTask\\users.txt");
      List<User> uss = rw.readUser();
      String pass = "";
      for (User us:uss) {
            if (us.eqLogin(login))
            {
                pass = us.getPassword();
                break;
            }
      }

      if (!pass.isEmpty()) {
          if (pass.equals(password)) {
              //hs.setAttribute("login", login);//hs.setAttribute("password", password);
              logcook = new Cookie("login", login);
              passcook = new Cookie("password", password);
              logcook.setMaxAge(60 * 60 * 24);
              passcook.setMaxAge(60 * 60 * 24);
              resp.addCookie(logcook);
              resp.addCookie(passcook);
              PrintWriter pw = resp.getWriter();
              pw.write("<b> Welcome " + login + "</b>");
          } else
              resp.sendError(400, "Wrong password");
      }
      if(pass.isEmpty()) {
            RequestDispatcher dispatcher = req.getRequestDispatcher("registr.html");
            dispatcher.forward(req, resp);
      }
    }

   @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
