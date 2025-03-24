package ejercicio;

public interface Operable <E extends Number> {
	public E suma(E operando1, E operando2);
	public E resta(E operando1, E operando2);
	public E producto(E operando1, E operando2);
	public E division(E operando1, E operando2);
	public E potencia(E ope1, int exponente);
	public E raizCuadrada(E ope1 );
	public E raizCubica (E op1);
}