package mvc.logica;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Contato;
import dao.ContatoDAO;

public class RemoveContatoLogic implements Logica {
	public void executa(HttpServletRequest request,HttpServletResponse response) throws Exception{
		ContatoDAO dao = new ContatoDAO();
		Contato c = new Contato();
		if (!request.getParameter("id").isEmpty()) {
			long id = Long.parseLong(request.getParameter("id"));
			c.setId(id);
			dao.delete(c);
			System.out.println("item removido " + c.getId());
		}
		RequestDispatcher rd = request.getRequestDispatcher("/listacontatos.jsp");
		rd.forward(request, response);
	}
}
