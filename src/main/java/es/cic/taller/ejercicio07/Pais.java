package es.cic.taller.ejercicio07;

public class Pais {
private String NombreCompleto;



@Override
public String toString() {
	return NombreCompleto;
}

public String getNombreCompleto() {
	return NombreCompleto;
}

public void setNombreCompleto(String nombreCompleto) {
	NombreCompleto = nombreCompleto;
}

}
