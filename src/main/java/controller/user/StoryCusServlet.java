package controller.user;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import entity.Story;
import model.StoryDAO;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "StoryCusServlet", value = "/StoryCusServlet")
public class StoryCusServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        StoryDAO sd = new StoryDAO();
        String input = request.getParameter("input").trim();
        if(input.equals("all")){
            List<Story> storyList= sd.getAllStory("all");
            request.setAttribute("storyList", storyList);
            request.getRequestDispatcher("storyList.jsp").forward(request, response);
        }else{
            Story story = sd.getStoryByID(input);
            request.setAttribute("story", story);
            request.getRequestDispatcher("storyDetail.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
