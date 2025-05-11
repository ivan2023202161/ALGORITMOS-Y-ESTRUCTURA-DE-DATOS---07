package Ejercicio4;

//Importa la clase StackLink del paquete ejercicio1
import Ejercicio1.StackLink;
import Actividad1.ExceptionIsEmpty;

public class Application {

	// Método estático que verifica si los corchetes están balanceados
	public static boolean symbolBalancing(String s) throws ExceptionIsEmpty {
		// Usamos la pila StackLink para verificar el balanceo de los corchetes
		StackLink<Character> stack = new StackLink<>();

		// Recorremos la cadena de caracteres
		for (char c : s.toCharArray()) {
			// Si encontramos un corchete de apertura, lo apilamos
			if (c == '(' || c == '[' || c == '{') {
				stack.push(c);
			}
			
			else if (c == ')' || c == ']' || c == '}') {
				// Si la pila está vacía, significa que no hay un corchete de apertura para emparejar
				if (stack.isEmpty()) {
					return false;
				}

				char top = stack.pop();
				// Verificamos si el corchete de apertura y cierre coinciden
				if (!isMatchingPair(top, c)) {
					return false;
				}
			}
		}

		// Si la pila está vacía, todos los corchetes se han balanceado correctamente
		return stack.isEmpty();
	}

	// Método que verifica si un par de corchetes es válido
	private static boolean isMatchingPair(char open, char close) {
		return (open == '(' && close == ')') || 
				(open == '[' && close == ']') || 
				(open == '{' && close == '}');
	}

	public static void main(String[] args) throws ExceptionIsEmpty {
		
		System.out.println(symbolBalancing("()()()[()]()"));  // true
		System.out.println(symbolBalancing("((()))[]"));      // true
		System.out.println(symbolBalancing("([])[]("));       // false
		System.out.println(symbolBalancing("([{)]}"));        // false
		System.out.println(symbolBalancing("["));             // false
		System.out.println(symbolBalancing("[][][]{{{}}}"));  // true
		
	}
}
