package co.edu.sena.sgac.controller;
import co.edu.sena.sgac.dao.EstudianteDAO; import co.edu.sena.sgac.model.Estudiante;
import jakarta.servlet.*; import jakarta.servlet.annotation.WebServlet; import jakarta.servlet.http.*;
import java.io.IOException; import java.sql.SQLException;
@WebServlet(name="EstudianteServlet",urlPatterns="/estudiantes")
public class EstudianteServlet extends HttpServlet {
 private final EstudianteDAO dao=new EstudianteDAO();
 @Override protected void doGet(HttpServletRequest req,HttpServletResponse resp)throws ServletException,IOException{
  req.setCharacterEncoding("UTF-8"); String accion=req.getParameter("accion");
  try { if("nuevo".equals(accion)){req.getRequestDispatcher("/WEB-INF/views/formulario.jsp").forward(req,resp);}
   else if("editar".equals(accion)){Estudiante e=dao.buscarPorId(Integer.parseInt(req.getParameter("id")));if(e==null){resp.sendError(404,"Estudiante no encontrado");return;}req.setAttribute("estudiante",e);req.getRequestDispatcher("/WEB-INF/views/formulario.jsp").forward(req,resp);}
   else {req.setAttribute("estudiantes",dao.listar());req.getRequestDispatcher("/WEB-INF/views/estudiantes.jsp").forward(req,resp);}
  }catch(SQLException|NumberFormatException ex){throw new ServletException("No se pudo consultar la base de datos. Revise la configuración JDBC.",ex);}
 }
 @Override protected void doPost(HttpServletRequest req,HttpServletResponse resp)throws ServletException,IOException{
  req.setCharacterEncoding("UTF-8");String accion=req.getParameter("accion");
  try {if("eliminar".equals(accion)){dao.eliminar(Integer.parseInt(req.getParameter("id")));resp.sendRedirect(req.getContextPath()+"/estudiantes?mensaje=eliminado");return;}
   Estudiante e=new Estudiante();e.setDocumento(valor(req,"documento"));e.setNombres(valor(req,"nombres"));e.setApellidos(valor(req,"apellidos"));e.setCorreo(valor(req,"correo"));e.setPrograma(valor(req,"programa"));
   if(e.getDocumento().isBlank()||e.getNombres().isBlank()||e.getApellidos().isBlank()||!e.getCorreo().matches("^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$")||e.getPrograma().isBlank()){req.setAttribute("error","Complete todos los campos y escriba un correo válido.");req.setAttribute("estudiante",e);req.getRequestDispatcher("/WEB-INF/views/formulario.jsp").forward(req,resp);return;}
   if("actualizar".equals(accion)){e.setId(Integer.parseInt(req.getParameter("id")));dao.actualizar(e);resp.sendRedirect(req.getContextPath()+"/estudiantes?mensaje=actualizado");}
   else if("guardar".equals(accion)){dao.insertar(e);resp.sendRedirect(req.getContextPath()+"/estudiantes?mensaje=creado");}
   else resp.sendError(400,"Acción no válida");
  }catch(SQLException ex){if(ex.getErrorCode()==1062){req.setAttribute("error","El documento o correo ya está registrado.");req.getRequestDispatcher("/WEB-INF/views/error.jsp").forward(req,resp);}else throw new ServletException("Error en la operación con MySQL.",ex);}
  catch(NumberFormatException ex){resp.sendError(400,"Identificador inválido");}
 }
 private String valor(HttpServletRequest req,String campo){String v=req.getParameter(campo);return v==null?"":v.trim();}
}
