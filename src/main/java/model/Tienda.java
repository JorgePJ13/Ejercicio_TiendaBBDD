package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// Constructor sin parametros
@NoArgsConstructor
// Constructor con todos los parametros
@AllArgsConstructor
// Setter de todos los atributos
@Setter
// Getter de todos los atributos
@Getter
public class Tienda {

	private int idProducto;
	private String nombre;
	private String seccion;
	private double precio;
	private int stock;	

}
