package com.patrones.interpreter;

/**
 * Client - Punto de entrada que construye los árboles de expresiones
 * y los evalúa contra diferentes contextos de productos.
 *
 * Participante GoF: Client
 */
public class MainInterpreter {

    public static void main(String[] args) {
        System.out.println("=============================================================");
        System.out.println("  PATRÓN INTERPRETER - Motor de Reglas de Descuentos");
        System.out.println("  Plataforma de Comercio Electrónico");
        System.out.println("=============================================================\n");

        // ---------------------------------------------------------------
        // REGLA 1: "Si precio > 100 AND categoría == Electrónica → 15% dto"
        // ---------------------------------------------------------------
        System.out.println("--- Regla 1: precio > 100 AND categoria == 'Electronica' ---");

        Expresion regla1 = new AndExpresion(
            new MayorQueExpresion(
                new VariableExpresion("precio"),
                new NumeroExpresion(100)
            ),
            new IgualExpresion(
                new VariableExpresion("categoria"),
                new VariableExpresion("categoriaObjetivo")
            )
        );

        // Producto A: Laptop $1200, categoría Electrónica
        Contexto ctxProductoA = new Contexto();
        ctxProductoA.setVariable("precio", 1200.0);
        ctxProductoA.setVariable("categoria", "Electronica");
        ctxProductoA.setVariable("categoriaObjetivo", "Electronica");
        ctxProductoA.setVariable("cantidad", 1);
        ctxProductoA.setVariable("esVIP", false);

        boolean resultadoA = (Boolean) regla1.interpretar(ctxProductoA);
        System.out.println("Producto A (Laptop $1200, Electrónica): " + regla1);
        System.out.println("  Resultado: " + resultadoA + " → " + (resultadoA ? "Aplica 15% descuento" : "No aplica descuento"));

        // Producto B: Camiseta $45, categoría Ropa
        Contexto ctxProductoB = new Contexto();
        ctxProductoB.setVariable("precio", 45.0);
        ctxProductoB.setVariable("categoria", "Ropa");
        ctxProductoB.setVariable("categoriaObjetivo", "Electronica");
        ctxProductoB.setVariable("cantidad", 2);
        ctxProductoB.setVariable("esVIP", true);

        boolean resultadoB = (Boolean) regla1.interpretar(ctxProductoB);
        System.out.println("Producto B (Camiseta $45, Ropa): " + regla1);
        System.out.println("  Resultado: " + resultadoB + " → " + (resultadoB ? "Aplica 15% descuento" : "No aplica descuento"));

        System.out.println();

        // ---------------------------------------------------------------
        // REGLA 2: "Si cantidad > 5 OR esVIP == true → 10% dto"
        // ---------------------------------------------------------------
        System.out.println("--- Regla 2: cantidad > 5 OR esVIP == true ---");

        Expresion regla2 = new OrExpresion(
            new MayorQueExpresion(
                new VariableExpresion("cantidad"),
                new NumeroExpresion(5)
            ),
            new IgualExpresion(
                new VariableExpresion("esVIP"),
                new BooleanExpresion(true)
            )
        );

        // Producto C: Libro $25, cantidad 8, no VIP
        Contexto ctxProductoC = new Contexto();
        ctxProductoC.setVariable("precio", 25.0);
        ctxProductoC.setVariable("categoria", "Libros");
        ctxProductoC.setVariable("cantidad", 8);
        ctxProductoC.setVariable("esVIP", false);

        boolean resultadoC = (Boolean) regla2.interpretar(ctxProductoC);
        System.out.println("Producto C (Libro $25, cantidad=8, noVIP): " + regla2);
        System.out.println("  Resultado: " + resultadoC + " → " + (resultadoC ? "Aplica 10% descuento" : "No aplica descuento"));

        // Reutilizar contexto Producto B (Camiseta $45, cantidad=2, VIP)
        boolean resultadoB2 = (Boolean) regla2.interpretar(ctxProductoB);
        System.out.println("Producto B (Camiseta $45, cantidad=2, VIP): " + regla2);
        System.out.println("  Resultado: " + resultadoB2 + " → " + (resultadoB2 ? "Aplica 10% descuento" : "No aplica descuento"));

        System.out.println();

        // ---------------------------------------------------------------
        // REGLA 3: Compuesta - "Si (precio > 100 AND categoria == Electrónica)
        //           OR (esVIP AND cantidad > 3) → 20% dto"
        // ---------------------------------------------------------------
        System.out.println("--- Regla 3: (precio > 100 AND cat == 'Electronica') OR (esVIP AND cantidad > 3) ---");

        Expresion regla3 = new OrExpresion(
            new AndExpresion(
                new MayorQueExpresion(
                    new VariableExpresion("precio"),
                    new NumeroExpresion(100)
                ),
                new IgualExpresion(
                    new VariableExpresion("categoria"),
                    new VariableExpresion("categoriaObjetivo")
                )
            ),
            new AndExpresion(
                new IgualExpresion(
                    new VariableExpresion("esVIP"),
                    new BooleanExpresion(true)
                ),
                new MayorQueExpresion(
                    new VariableExpresion("cantidad"),
                    new NumeroExpresion(3)
                )
            )
        );

        // Producto D: Auriculares $80, Electrónica, cantidad=4, VIP
        Contexto ctxProductoD = new Contexto();
        ctxProductoD.setVariable("precio", 80.0);
        ctxProductoD.setVariable("categoria", "Electronica");
        ctxProductoD.setVariable("categoriaObjetivo", "Electronica");
        ctxProductoD.setVariable("cantidad", 4);
        ctxProductoD.setVariable("esVIP", true);

        boolean resultadoD = (Boolean) regla3.interpretar(ctxProductoD);
        System.out.println("Producto D (Auriculares $80, Electrónica, cant=4, VIP): " + regla3);
        System.out.println("  Resultado: " + resultadoD + " → " + (resultadoD ? "Aplica 20% descuento" : "No aplica descuento"));

        System.out.println();

        // ---------------------------------------------------------------
        // REGLA 4: Uso de NOT - "Si NOT (categoria == 'Alimentos') AND precio < 50 → 5% dto"
        // ---------------------------------------------------------------
        System.out.println("--- Regla 4: NOT(categoria == 'Alimentos') AND precio < 50 ---");

        Expresion regla4 = new AndExpresion(
            new NotExpresion(
                new IgualExpresion(
                    new VariableExpresion("categoria"),
                    new VariableExpresion("categoriaExcluida")
                )
            ),
            new MenorQueExpresion(
                new VariableExpresion("precio"),
                new NumeroExpresion(50)
            )
        );

        // Producto E: Manzanas $15, Alimentos
        Contexto ctxProductoE = new Contexto();
        ctxProductoE.setVariable("precio", 15.0);
        ctxProductoE.setVariable("categoria", "Alimentos");
        ctxProductoE.setVariable("categoriaExcluida", "Alimentos");
        ctxProductoE.setVariable("cantidad", 10);
        ctxProductoE.setVariable("esVIP", false);

        boolean resultadoE = (Boolean) regla4.interpretar(ctxProductoE);
        System.out.println("Producto E (Manzanas $15, Alimentos): " + regla4);
        System.out.println("  Resultado: " + resultadoE + " → " + (resultadoE ? "Aplica 5% descuento" : "No aplica descuento"));

        // Producto F: Novela $30, Libros
        Contexto ctxProductoF = new Contexto();
        ctxProductoF.setVariable("precio", 30.0);
        ctxProductoF.setVariable("categoria", "Libros");
        ctxProductoF.setVariable("categoriaExcluida", "Alimentos");
        ctxProductoF.setVariable("cantidad", 1);
        ctxProductoF.setVariable("esVIP", false);

        boolean resultadoF = (Boolean) regla4.interpretar(ctxProductoF);
        System.out.println("Producto F (Novela $30, Libros): " + regla4);
        System.out.println("  Resultado: " + resultadoF + " → " + (resultadoF ? "Aplica 5% descuento" : "No aplica descuento"));

        System.out.println("\n=============================================================");
        System.out.println("  Demostración de reutilización: misma regla, distintos contextos");
        System.out.println("=============================================================\n");
        System.out.println("La Regla 1 se evaluó contra Producto A y Producto B sin modificar el árbol.");
        System.out.println("La Regla 2 se evaluó contra Producto C y Producto B reutilizando la misma expresión.");
        System.out.println("Esto demuestra el desacoplamiento entre la gramática y los datos de evaluación.");
    }
}
