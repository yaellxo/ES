
//----------------------------------------------------
// The following code was generated by CUP v0.11b 20160615 (GIT 4ac7450)
//----------------------------------------------------

import java_cup.runtime.Symbol;
import java_cup.runtime.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import java_cup.runtime.XMLElement;

/** CUP v0.11b 20160615 (GIT 4ac7450) generated parser.
  */
@SuppressWarnings({"rawtypes"})
public class parsito extends java_cup.runtime.lr_parser {

 public final Class getSymbolContainer() {
    return sym.class;
}

  /** Default constructor. */
  @Deprecated
  public parsito() {super();}

  /** Constructor which sets the default scanner. */
  @Deprecated
  public parsito(java_cup.runtime.Scanner s) {super(s);}

  /** Constructor which sets the default scanner. */
  public parsito(java_cup.runtime.Scanner s, java_cup.runtime.SymbolFactory sf) {super(s,sf);}

  /** Production table. */
  protected static final short _production_table[][] = 
    unpackFromStrings(new String[] {
    "\000\013\000\002\002\004\000\002\002\003\000\002\003" +
    "\003\000\002\003\002\000\002\004\004\000\002\004\003" +
    "\000\002\005\007\000\002\005\003\000\002\005\004\000" +
    "\002\005\007\000\002\005\003" });

  /** Access to production table. */
  public short[][] production_table() {return _production_table;}

  /** Parse-action table. */
  protected static final short[][] _action_table = 
    unpackFromStrings(new String[] {
    "\000\025\000\016\002\ufffe\003\012\004\007\005\013\006" +
    "\010\007\005\001\002\000\016\002\ufffc\003\ufffc\004\ufffc" +
    "\005\ufffc\006\ufffc\007\ufffc\001\002\000\004\010\024\001" +
    "\002\000\004\002\023\001\002\000\004\010\017\001\002" +
    "\000\004\010\016\001\002\000\016\002\uffff\003\012\004" +
    "\007\005\013\006\010\007\005\001\002\000\016\002\ufff7" +
    "\003\ufff7\004\ufff7\005\ufff7\006\ufff7\007\ufff7\001\002\000" +
    "\016\002\ufffa\003\ufffa\004\ufffa\005\ufffa\006\ufffa\007\ufffa" +
    "\001\002\000\004\002\000\001\002\000\016\002\ufffd\003" +
    "\ufffd\004\ufffd\005\ufffd\006\ufffd\007\ufffd\001\002\000\016" +
    "\002\ufff9\003\ufff9\004\ufff9\005\ufff9\006\ufff9\007\ufff9\001" +
    "\002\000\004\011\020\001\002\000\004\011\021\001\002" +
    "\000\004\011\022\001\002\000\016\002\ufffb\003\ufffb\004" +
    "\ufffb\005\ufffb\006\ufffb\007\ufffb\001\002\000\004\002\001" +
    "\001\002\000\004\011\025\001\002\000\004\011\026\001" +
    "\002\000\004\011\027\001\002\000\016\002\ufff8\003\ufff8" +
    "\004\ufff8\005\ufff8\006\ufff8\007\ufff8\001\002" });

  /** Access to parse-action table. */
  public short[][] action_table() {return _action_table;}

  /** <code>reduce_goto</code> table. */
  protected static final short[][] _reduce_table = 
    unpackFromStrings(new String[] {
    "\000\025\000\012\002\005\003\013\004\010\005\003\001" +
    "\001\000\002\001\001\000\002\001\001\000\002\001\001" +
    "\000\002\001\001\000\002\001\001\000\004\005\014\001" +
    "\001\000\002\001\001\000\002\001\001\000\002\001\001" +
    "\000\002\001\001\000\002\001\001\000\002\001\001\000" +
    "\002\001\001\000\002\001\001\000\002\001\001\000\002" +
    "\001\001\000\002\001\001\000\002\001\001\000\002\001" +
    "\001\000\002\001\001" });

  /** Access to <code>reduce_goto</code> table. */
  public short[][] reduce_table() {return _reduce_table;}

  /** Instance of action encapsulation class. */
  protected CUP$parsito$actions action_obj;

  /** Action encapsulation object initializer. */
  protected void init_actions()
    {
      action_obj = new CUP$parsito$actions(this);
    }

  /** Invoke a user supplied parse action. */
  public java_cup.runtime.Symbol do_action(
    int                        act_num,
    java_cup.runtime.lr_parser parser,
    java.util.Stack            stack,
    int                        top)
    throws java.lang.Exception
  {
    /* call code in generated class */
    return action_obj.CUP$parsito$do_action(act_num, parser, stack, top);
  }

  /** Indicates start state. */
  public int start_state() {return 0;}
  /** Indicates start production. */
  public int start_production() {return 0;}

  /** <code>EOF</code> Symbol index. */
  public int EOF_sym() {return 0;}

  /** <code>error</code> Symbol index. */
  public int error_sym() {return 1;}



    // Este método se llama automáticamente en caso de un error sintáctico.
    public void report_error(String message, Object info) {
        StringBuilder m = new StringBuilder("Error Sintáctico");
        if (info instanceof java_cup.runtime.Symbol) {
            java_cup.runtime.Symbol s = ((java_cup.runtime.Symbol) info);
            if (s.left >= 1) {
                m.append(" en línea ").append(s.left);
                if (s.right >= 1)
                    m.append(", columna ").append(s.right);
            }
        }
        m.append(" : ").append(message);

        if (info instanceof java_cup.runtime.Symbol) {
            java_cup.runtime.Symbol s = ((java_cup.runtime.Symbol) info);
            if (s.value != null) {
                 m.append(" (token problemático: '").append(s.value).append("')");
            } else {
                // MODIFICADO: usa 'sym' en minúsculas
                String[] terms = sym.terminalNames;
                if (terms != null && s.sym >=0 && s.sym < terms.length && terms[s.sym] != null && !terms[s.sym].equals("error")) {
                     m.append(" (cerca del token tipo '").append(terms[s.sym]).append("')");
                } else if (s.sym == sym.EOF) { // MODIFICADO: usa 'sym' en minúsculas
                     m.append(" (al final del archivo)");
                }
            }
        }
        System.err.println(m.toString());
        JOptionPane.showMessageDialog(null, m.toString(), "Error de Sintaxis", JOptionPane.ERROR_MESSAGE);
    }

    public void report_fatal_error(String message, Object info) {
        report_error(message, info);
        throw new RuntimeException("Error sintáctico fatal: " + message + (info instanceof Symbol ? " cerca del token " + ((Symbol)info).value : ""));
    }


/** Cup generated class to encapsulate user supplied action code.*/
@SuppressWarnings({"rawtypes", "unchecked", "unused"})
class CUP$parsito$actions {
  private final parsito parser;

  /** Constructor */
  CUP$parsito$actions(parsito parser) {
    this.parser = parser;
  }

  /** Method 0 with the actual generated action code for actions 0 to 300. */
  public final java_cup.runtime.Symbol CUP$parsito$do_action_part00000000(
    int                        CUP$parsito$act_num,
    java_cup.runtime.lr_parser CUP$parsito$parser,
    java.util.Stack            CUP$parsito$stack,
    int                        CUP$parsito$top)
    throws java.lang.Exception
    {
      /* Symbol object for return from actions */
      java_cup.runtime.Symbol CUP$parsito$result;

      /* select the action based on the action number */
      switch (CUP$parsito$act_num)
        {
          /*. . . . . . . . . . . . . . . . . . . .*/
          case 0: // $START ::= program EOF 
            {
              Object RESULT =null;
		int start_valleft = ((java_cup.runtime.Symbol)CUP$parsito$stack.elementAt(CUP$parsito$top-1)).left;
		int start_valright = ((java_cup.runtime.Symbol)CUP$parsito$stack.elementAt(CUP$parsito$top-1)).right;
		Object start_val = (Object)((java_cup.runtime.Symbol) CUP$parsito$stack.elementAt(CUP$parsito$top-1)).value;
		RESULT = start_val;
              CUP$parsito$result = parser.getSymbolFactory().newSymbol("$START",0, ((java_cup.runtime.Symbol)CUP$parsito$stack.elementAt(CUP$parsito$top-1)), ((java_cup.runtime.Symbol)CUP$parsito$stack.peek()), RESULT);
            }
          /* ACCEPT */
          CUP$parsito$parser.done_parsing();
          return CUP$parsito$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 1: // program ::= command_list_opt 
            {
              Object RESULT =null;

              CUP$parsito$result = parser.getSymbolFactory().newSymbol("program",0, ((java_cup.runtime.Symbol)CUP$parsito$stack.peek()), ((java_cup.runtime.Symbol)CUP$parsito$stack.peek()), RESULT);
            }
          return CUP$parsito$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 2: // command_list_opt ::= command_list 
            {
              Object RESULT =null;

              CUP$parsito$result = parser.getSymbolFactory().newSymbol("command_list_opt",1, ((java_cup.runtime.Symbol)CUP$parsito$stack.peek()), ((java_cup.runtime.Symbol)CUP$parsito$stack.peek()), RESULT);
            }
          return CUP$parsito$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 3: // command_list_opt ::= 
            {
              Object RESULT =null;

              CUP$parsito$result = parser.getSymbolFactory().newSymbol("command_list_opt",1, ((java_cup.runtime.Symbol)CUP$parsito$stack.peek()), RESULT);
            }
          return CUP$parsito$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 4: // command_list ::= command_list command 
            {
              Object RESULT =null;

              CUP$parsito$result = parser.getSymbolFactory().newSymbol("command_list",2, ((java_cup.runtime.Symbol)CUP$parsito$stack.elementAt(CUP$parsito$top-1)), ((java_cup.runtime.Symbol)CUP$parsito$stack.peek()), RESULT);
            }
          return CUP$parsito$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 5: // command_list ::= command 
            {
              Object RESULT =null;

              CUP$parsito$result = parser.getSymbolFactory().newSymbol("command_list",2, ((java_cup.runtime.Symbol)CUP$parsito$stack.peek()), ((java_cup.runtime.Symbol)CUP$parsito$stack.peek()), RESULT);
            }
          return CUP$parsito$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 6: // command ::= ADD ID STRING STRING STRING 
            {
              Object RESULT =null;
		int nombreleft = ((java_cup.runtime.Symbol)CUP$parsito$stack.elementAt(CUP$parsito$top-3)).left;
		int nombreright = ((java_cup.runtime.Symbol)CUP$parsito$stack.elementAt(CUP$parsito$top-3)).right;
		String nombre = (String)((java_cup.runtime.Symbol) CUP$parsito$stack.elementAt(CUP$parsito$top-3)).value;
		int profesorleft = ((java_cup.runtime.Symbol)CUP$parsito$stack.elementAt(CUP$parsito$top-2)).left;
		int profesorright = ((java_cup.runtime.Symbol)CUP$parsito$stack.elementAt(CUP$parsito$top-2)).right;
		String profesor = (String)((java_cup.runtime.Symbol) CUP$parsito$stack.elementAt(CUP$parsito$top-2)).value;
		int lugarleft = ((java_cup.runtime.Symbol)CUP$parsito$stack.elementAt(CUP$parsito$top-1)).left;
		int lugarright = ((java_cup.runtime.Symbol)CUP$parsito$stack.elementAt(CUP$parsito$top-1)).right;
		String lugar = (String)((java_cup.runtime.Symbol) CUP$parsito$stack.elementAt(CUP$parsito$top-1)).value;
		int fechaleft = ((java_cup.runtime.Symbol)CUP$parsito$stack.peek()).left;
		int fecharight = ((java_cup.runtime.Symbol)CUP$parsito$stack.peek()).right;
		String fecha = (String)((java_cup.runtime.Symbol) CUP$parsito$stack.peek()).value;
		 /* acción */ Main.clases.add(new Clase(nombre, profesor, lugar, fecha)); 
              CUP$parsito$result = parser.getSymbolFactory().newSymbol("command",3, ((java_cup.runtime.Symbol)CUP$parsito$stack.elementAt(CUP$parsito$top-4)), ((java_cup.runtime.Symbol)CUP$parsito$stack.peek()), RESULT);
            }
          return CUP$parsito$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 7: // command ::= SHOW 
            {
              Object RESULT =null;
		 /* acción */
                StringBuilder sb = new StringBuilder("HORARIO ESCOLAR:\n====================\n");
                if (Main.clases.isEmpty()) { sb.append("No hay clases para mostrar."); }
                else { for (Clase clase : Main.clases) { sb.append(clase.toString()).append("\n--------------------\n"); } }
                JOptionPane.showMessageDialog(null, sb.toString(), "Horario Escolar", JOptionPane.INFORMATION_MESSAGE);
            
              CUP$parsito$result = parser.getSymbolFactory().newSymbol("command",3, ((java_cup.runtime.Symbol)CUP$parsito$stack.peek()), ((java_cup.runtime.Symbol)CUP$parsito$stack.peek()), RESULT);
            }
          return CUP$parsito$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 8: // command ::= DELETE ID 
            {
              Object RESULT =null;
		int nombreleft = ((java_cup.runtime.Symbol)CUP$parsito$stack.peek()).left;
		int nombreright = ((java_cup.runtime.Symbol)CUP$parsito$stack.peek()).right;
		String nombre = (String)((java_cup.runtime.Symbol) CUP$parsito$stack.peek()).value;
		 /* acción */
                boolean removed = Main.clases.removeIf(clase -> clase.getNombre().equals(nombre));
                if (!removed) { JOptionPane.showMessageDialog(null, "Clase '" + nombre + "' no encontrada.", "Error al Eliminar", JOptionPane.ERROR_MESSAGE); }
            
              CUP$parsito$result = parser.getSymbolFactory().newSymbol("command",3, ((java_cup.runtime.Symbol)CUP$parsito$stack.elementAt(CUP$parsito$top-1)), ((java_cup.runtime.Symbol)CUP$parsito$stack.peek()), RESULT);
            }
          return CUP$parsito$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 9: // command ::= UPDATE ID STRING STRING STRING 
            {
              Object RESULT =null;
		int nombreleft = ((java_cup.runtime.Symbol)CUP$parsito$stack.elementAt(CUP$parsito$top-3)).left;
		int nombreright = ((java_cup.runtime.Symbol)CUP$parsito$stack.elementAt(CUP$parsito$top-3)).right;
		String nombre = (String)((java_cup.runtime.Symbol) CUP$parsito$stack.elementAt(CUP$parsito$top-3)).value;
		int newProfesorleft = ((java_cup.runtime.Symbol)CUP$parsito$stack.elementAt(CUP$parsito$top-2)).left;
		int newProfesorright = ((java_cup.runtime.Symbol)CUP$parsito$stack.elementAt(CUP$parsito$top-2)).right;
		String newProfesor = (String)((java_cup.runtime.Symbol) CUP$parsito$stack.elementAt(CUP$parsito$top-2)).value;
		int newLugarleft = ((java_cup.runtime.Symbol)CUP$parsito$stack.elementAt(CUP$parsito$top-1)).left;
		int newLugarright = ((java_cup.runtime.Symbol)CUP$parsito$stack.elementAt(CUP$parsito$top-1)).right;
		String newLugar = (String)((java_cup.runtime.Symbol) CUP$parsito$stack.elementAt(CUP$parsito$top-1)).value;
		int newFechaleft = ((java_cup.runtime.Symbol)CUP$parsito$stack.peek()).left;
		int newFecharight = ((java_cup.runtime.Symbol)CUP$parsito$stack.peek()).right;
		String newFecha = (String)((java_cup.runtime.Symbol) CUP$parsito$stack.peek()).value;
		 /* acción */
                boolean updated = false;
                for (Clase clase : Main.clases) {
                    if (clase.getNombre().equals(nombre)) {
                        clase.setProfesor(newProfesor); clase.setLugar(newLugar); clase.setFecha(newFecha);
                        updated = true; break;
                    }
                }
                if (!updated) { JOptionPane.showMessageDialog(null, "Clase '" + nombre + "' no encontrada para actualizar.", "Error al Actualizar", JOptionPane.ERROR_MESSAGE); }
            
              CUP$parsito$result = parser.getSymbolFactory().newSymbol("command",3, ((java_cup.runtime.Symbol)CUP$parsito$stack.elementAt(CUP$parsito$top-4)), ((java_cup.runtime.Symbol)CUP$parsito$stack.peek()), RESULT);
            }
          return CUP$parsito$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 10: // command ::= error 
            {
              Object RESULT =null;
		 /* acción de error (opcional) */ 
              CUP$parsito$result = parser.getSymbolFactory().newSymbol("command",3, ((java_cup.runtime.Symbol)CUP$parsito$stack.peek()), ((java_cup.runtime.Symbol)CUP$parsito$stack.peek()), RESULT);
            }
          return CUP$parsito$result;

          /* . . . . . .*/
          default:
            throw new Exception(
               "Invalid action number "+CUP$parsito$act_num+"found in internal parse table");

        }
    } /* end of method */

  /** Method splitting the generated action code into several parts. */
  public final java_cup.runtime.Symbol CUP$parsito$do_action(
    int                        CUP$parsito$act_num,
    java_cup.runtime.lr_parser CUP$parsito$parser,
    java.util.Stack            CUP$parsito$stack,
    int                        CUP$parsito$top)
    throws java.lang.Exception
    {
              return CUP$parsito$do_action_part00000000(
                               CUP$parsito$act_num,
                               CUP$parsito$parser,
                               CUP$parsito$stack,
                               CUP$parsito$top);
    }
}

}
