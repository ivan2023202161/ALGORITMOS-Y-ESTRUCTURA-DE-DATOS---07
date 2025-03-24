package ejercicio;

public class OperacionesMat<T extends Number> implements Operable<T> {

    @Override
    public T suma(T operando1, T operando2) {
        if (operando1 instanceof Integer && operando2 instanceof Integer) {
            return (T) Integer.valueOf(operando1.intValue() + operando2.intValue());
        } else if (operando1 instanceof Double && operando2 instanceof Double) {
            return (T) Double.valueOf(operando1.doubleValue() + operando2.doubleValue());
        }
        return null;
    }

    @Override
    public T resta(T operando1, T operando2) {
        if (operando1 instanceof Integer && operando2 instanceof Integer) {
            return (T) Integer.valueOf(operando1.intValue() - operando2.intValue());
        } else if (operando1 instanceof Double && operando2 instanceof Double) {
            return (T) Double.valueOf(operando1.doubleValue() - operando2.doubleValue());
        }
        return null;
    }

    @Override
    public T producto(T operando1, T operando2) {
        if (operando1 instanceof Integer && operando2 instanceof Integer) {
            return (T) Integer.valueOf(operando1.intValue() * operando2.intValue());
        } else if (operando1 instanceof Double && operando2 instanceof Double) {
            return (T) Double.valueOf(operando1.doubleValue() * operando2.doubleValue());
        }
        return null;
    }

    @Override
    public T division(T operando1, T operando2) {
        if (operando2.intValue() == 0) {
            System.out.println("no se puede dividir entre 0");
        }
        
        return (T) Double.valueOf(operando1.doubleValue() / operando2.doubleValue());
        
    }

	@Override
	public T potencia(T ope1, int exponente) {
	    // Comprobamos si el tipo es Integer
	    if (ope1 instanceof Integer) {
	        int base = ope1.intValue();
	        int resultado = 1;  // Valor inicial para el cálculo de la potencia
	        
	        
	        for (int i = 0; i < exponente; i++) {
	            resultado *= base;  
	        }
	        
	        return (T) Integer.valueOf(resultado);
	    }
	    
	    // Comprobamos si el tipo es Double
	    else if (ope1 instanceof Double) {
	        double base = ope1.doubleValue();
	        double resultado = 1.0;  
	        
	        // Calculamos la potencia usando un bucle
	        for (int i = 0; i < exponente; i++) {
	            resultado *= base;  
	        }
	        
	        return (T) Double.valueOf(resultado);
	    }
	    
	    return null;  
	}


	@Override
	public T raizCuadrada(T ope1) {

		return (T) Double.valueOf(Math.sqrt(ope1.doubleValue()));
		
	}

	@Override
	public T raizCubica(T ope1) {
		return (T) Double.valueOf(Math.cbrt(ope1.doubleValue()));

	}
}