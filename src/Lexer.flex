// Lexer.flex
import java_cup.runtime.Symbol;

%%
// SECCIÓN DE OPCIONES Y DECLARACIONES DE JFLEX
%public
%class Lexer         // Nombre de la clase Java generada
%unicode            // Soporte para Unicode
%cup                // Indica compatibilidad con CUP (asumirá clase de símbolos 'sym' por defecto con JFlex antiguo)
%line               // Habilita la variable yyline (número de línea actual, 0-indexed)
%column             // Habilita la variable yycolumn (número de columna actual, 0-indexed)

%{
    // Código Java que se copia directamente en la clase Lexer generada.
    // Métodos auxiliares para crear objetos Symbol con información de línea/columna.
    private Symbol symbol(int type) {
        // Se suma 1 a yyline y yycolumn porque son 0-indexed, y usualmente se reportan errores 1-indexed.
        return new Symbol(type, yyline + 1, yycolumn + 1);
    }

    private Symbol symbol(int type, Object value) {
        return new Symbol(type, yyline + 1, yycolumn + 1, value);
    }
%}

// DEFINICIÓN DE MACROS DE JFLEX
// Estas macros deben estar en esta sección (entre el primer %% y el segundo %%)
// y fuera de los bloques %{ ... %}

ADD     = "add"
SHOW    = "show"
DELETE  = "delete"
UPDATE  = "update"

// Macro para identificadores: letra o guion bajo, seguido de letras, números o guiones bajos.
ID = [a-zA-Z_][a-zA-Z0-9_]*

// Macro para strings: cualquier cosa entre comillas dobles, con soporte para comillas escapadas.
STRING = \" ( [^\"\\\\] | \\\\. )* \"

// Macro para espacios en blanco (espacio, tab, retorno de carro, nueva línea)
WHITESPACE = [ \t\r\n]+

%%
// SECCIÓN DE REGLAS LÉXICAS DE JFLEX

// Las reglas se prueban en el orden en que aparecen.
// Es importante poner las palabras clave ANTES que la regla para ID genérico.

{ADD}           { return symbol(sym.ADD, yytext()); }       // Usa la macro ADD
{SHOW}          { return symbol(sym.SHOW, yytext()); }      // Usa la macro SHOW
{DELETE}        { return symbol(sym.DELETE, yytext()); }    // Usa la macro DELETE
{UPDATE}        { return symbol(sym.UPDATE, yytext()); }    // Usa la macro UPDATE

{ID}            { return symbol(sym.ID, yytext()); }        // Usa la macro ID
{STRING}        {
                  // Remueve las comillas dobles del inicio y final del string
                  return symbol(sym.STRING, yytext().substring(1, yytext().length()-1));
                } // Usa la macro STRING

{WHITESPACE}    { /* Ignorar espacios en blanco, no hacer nada, no retornar token */ }

/* Manejo de errores léxicos: cualquier otro carácter no reconocido */
. {
    System.err.println("Error Léxico: Carácter no reconocido '" + yytext() + "' en línea " + (yyline + 1) + ", columna " + (yycolumn + 1));
    // Devolver el token especial 'error' que CUP puede entender para recuperación de errores.
    return symbol(sym.error, yytext());
}