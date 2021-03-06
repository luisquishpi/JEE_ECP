package views.beans;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/jsp/*")
public class Dispatcher extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private static String PATH_ROOT_VIEW = "/jsps/";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getPathInfo().substring(1);
        String view;
        switch (action) {
        case "newTema":
            NewTemaView temaView = new NewTemaView();
            request.setAttribute(action, temaView);
            view = action;
            break;
        case "removeTema":
            view = "loginToRemoveTema";
            break;
        case "loginToRemoveTema":
            LoginToRemoveTemaView loginToRemoveTemaView = new LoginToRemoveTemaView();
            request.setAttribute(action, loginToRemoveTemaView);
            view = action;
            break;
        case "vote":
            VoteView voteView = new VoteView();
            request.setAttribute(action, voteView);
            view = action;
            break;
        case "report":
            ReportView reportView=new ReportView();
            request.setAttribute(action, reportView);
            view = action;
            break;
        default:
            view = "home";
        }
        this.getServletContext().getRequestDispatcher(PATH_ROOT_VIEW + view + ".jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getPathInfo().substring(1);
        String view = "home";
        switch (action) {
        case "newTema":
            
            NewTemaView temaView = new NewTemaView();
            temaView.setNombre(request.getParameter("nombre"));
            temaView.setPregunta(request.getParameter("pregunta"));
            request.setAttribute(action, temaView);
            view = temaView.process();
            break;
        case "removeTema":
            RemoveTemaView removeTemaView = new RemoveTemaView();
            removeTemaView.setIdTema(request.getParameter("tema"));
            request.setAttribute(action, removeTemaView);
            view = removeTemaView.process();
            break;
        case "loginToRemoveTema":
            LoginToRemoveTemaView loginToRemoveTemaView = new LoginToRemoveTemaView();
            loginToRemoveTemaView.setClave(request.getParameter("clave"));
            request.setAttribute(action, loginToRemoveTemaView);
            view = loginToRemoveTemaView.process();
            if (view.equals("removeTema")) {
                removeTemaView = new RemoveTemaView();
                request.setAttribute("removeTema", removeTemaView);
            }
            break;
        case "vote":
            VoteView voteView = new VoteView();
            voteView.setNivelEstudio(request.getParameter("nivelEstudio"));
            voteView.setIdTema(Integer.parseInt(request.getParameter("idTema")));
            voteView.setValor(Integer.parseInt(request.getParameter("valor")));
            request.setAttribute(action, voteView);
            view = voteView.process();
            break;
        }
        this.getServletContext().getRequestDispatcher(PATH_ROOT_VIEW + view + ".jsp")
                .forward(request, response);
    }

}
