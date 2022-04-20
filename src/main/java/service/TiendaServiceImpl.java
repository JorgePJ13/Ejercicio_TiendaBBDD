package service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import model.Tienda;

@Service
public class TiendaServiceImpl implements TiendaService {

	@Autowired
	JdbcTemplate template;

	@Override
	public void alta(Tienda t) {
		String sql = "insert into productos(nombre,seccion,precio,stock) values (?,?,?,?)";
		template.update(sql, t.getNombre(), t.getSeccion(), t.getPrecio(), t.getStock());
	}

	@Override
	public void modificarPrecio(String nomProducto, double precio) {
		String sql = "update productos set precio=? where nombre=?";
		template.update(sql, precio, nomProducto);
	}

	@Override
	public void baja(String nomProducto) {
		String sql = "delete from productos where nombre=?";
		template.update(sql, nomProducto);
	}

	// Usamos query para listas
	@Override
	public List<Tienda> buscar(String seccion) {
		String sql = "select * from productos where seccion=?";
		return template.query(sql, (rs, f) -> new Tienda(rs.getInt("idProducto"), rs.getString("nombre"),
				rs.getString("seccion"), rs.getDouble("precio"), rs.getInt("stock")), seccion); // rs=resultset; f=fila
	}

	// Para objetos (Tienda), usamos
	@Override
	public Tienda buscarPorId(int idProducto) {
		String sql = "select * from productos where idProducto=?";
		List<Tienda> productos = template.query(sql, (rs, f) -> new Tienda(rs.getInt("idProducto"),
				rs.getString("nombre"), rs.getString("seccion"), 
				rs.getDouble("precio"), rs.getInt("stock")), idProducto);
		return productos.size() > 0 ? productos.get(0) : null; // devuelve el primer producto si la lista es mayor que
																// 0; si no devuelve nulo
	}

}
