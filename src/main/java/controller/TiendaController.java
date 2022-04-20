package controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import model.Tienda;
import service.TiendaService;

@Controller
public class TiendaController {

	@Autowired
	TiendaService tiendaS;
	
	@GetMapping("Buscador")
	public String buscar(@RequestParam("seccion") String seccion, HttpServletRequest request) {
		List<Tienda> prods = tiendaS.buscar(seccion);
		request.setAttribute("productos", prods);
		return "listado";
	}
	
	@PostMapping("Alta")
	public String alta(@ModelAttribute Tienda t) {
		tiendaS.alta(t);
		return "alta";
	}
	
	@GetMapping("Modificar")
	public String modificar(@RequestParam("nombre") String nomProducto, @RequestParam("precio") double precio, HttpServletRequest request) {
		tiendaS.modificarPrecio(nomProducto, precio);
		return "inicio";
	}
	
	@GetMapping("Eliminar")
	public String eliminar(@RequestParam("nombre") String nomProducto) {
		tiendaS.baja(nomProducto);
		return "inicio";
	}
}
