package ma.petshop;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class OrderServlet
 */
public class OrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrderServlet(){
        super();
        // TODO Auto-generated constructor stub
  }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
    
	}


      protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();
        switch (action) {
            case "/new":
                showNewForm(request, response);
                break;
            case "/insert":
                insertItem(request, response);
                break;
            case "/delete":
               delete(request, response);
                break;
            case "/edit":
                showEditForm(request, response);
                break;
            case "/update":
                updateItem(request, response);
                break;
            case "/list":
                listItem(request, response);
                break;
            case "/userList":
                userList(request, response);
                break;
            case "/checkOut":
                checkOut(request, response);
                break;
        }
    }



    private void listItem(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        String url="";

        request.setAttribute("list", PetService.listAllItems());
        getServletContext().getRequestDispatcher("/list.jsp").forward(request, response);
    }
    private void checkOut(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        int id = Integer.parseInt(request.getParameter("id"));
        Pet pet= PetService.findById(id);

        request.setAttribute("pet",  PetService.findById(id));
        getServletContext().getRequestDispatcher("/userCheckOut.jsp").forward(request, response);
    }


    private void userList(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        String url="/userLogged.jsp";

        request.setAttribute("list", PetService.listAllItems());
        getServletContext().getRequestDispatcher(url).forward(request, response);
    }


    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        Pet pet= PetService.findById(id);

        request.setAttribute("pet",  PetService.findById(id));
        getServletContext().getRequestDispatcher("/petEdit.jsp").forward(request, response);

    }

    private void updateItem(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
       // int id = Integer.parseInt(request.getParameter("id"));
       // Pet pet= PetService.findById(id);
       // PetService.updatePet(pet);
      //  response.sendRedirect("list");


        int id = Integer.valueOf(request.getParameter("id"));
        Pet pet= PetService.findById(id);
        pet.setName( request.getParameter("name"));
        pet.setColor( request.getParameter("color"));
        pet.setPrice(Float.valueOf(request.getParameter("price")));
        pet.setBreed(Breed.valueOf(request.getParameter("breed")));
        // Pet pet = new Pet(petName, Breed.valueOf(petBreed), petColor, petPrice);

        // Pet pet= PetService.findById(id);
        PetService.updatePet(pet);
        response.sendRedirect("list");


    }

    private void delete(HttpServletRequest request, HttpServletResponse response)
            throws IOException,ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        Pet pet= PetService.findById(id);
        PetService.deletePet(pet);
        request.setAttribute("list", PetService.listAllItems());
        getServletContext().getRequestDispatcher("/list.jsp").forward(request, response);


    }
    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url ="";
        url += "/adminLogged.jsp";
        getServletContext().getRequestDispatcher(url);

        RequestDispatcher dispatcher = request.getRequestDispatcher("adminLogged.jsp");
        dispatcher.forward(request, response);
    }


    private void insertItem(HttpServletRequest request, HttpServletResponse response)
            throws IOException,ServletException {
        String petName = request.getParameter("name");
        String petColor = request.getParameter("color");
        Float petPrice = Float.valueOf(request.getParameter("price"));
        String petBreed = request.getParameter("breed");
        Pet newPet = new Pet(petName, Breed.valueOf(petBreed), petColor, petPrice);
        PetService.addPet(newPet);

        request.setAttribute("list", PetService.listAllItems());
        getServletContext().getRequestDispatcher("/list.jsp").forward(request, response);
    }

}
