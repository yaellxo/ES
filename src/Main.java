// Main.java
import javax.swing.*;
import java.awt.*;
import java.io.PrintStream; // Para redirigir System.out/err
import java.io.OutputStream; // Para redirigir System.out/err
import java.io.StringReader;
import java.util.ArrayList;

public class Main {
    public static ArrayList<Clase> clases = new ArrayList<>(); // Lista estática de clases
    private static JTextArea consoleArea; // Área de texto para la consola

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("EasySchedule IDE");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(800, 700);
            frame.setLocationRelativeTo(null);

            // Área de texto para el código fuente
            JTextArea codeArea = new JTextArea();
            codeArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
            JScrollPane codeScrollPane = new JScrollPane(codeArea);

            // Área de texto para la consola (no editable)
            consoleArea = new JTextArea();
            consoleArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
            consoleArea.setEditable(false);
            consoleArea.setWrapStyleWord(true); // Para que el texto se ajuste
            consoleArea.setLineWrap(true);      // Para que el texto se ajuste
            JScrollPane consoleScrollPane = new JScrollPane(consoleArea);
            consoleScrollPane.setPreferredSize(new Dimension(0, 200)); // Altura preferida para la consola

            // Botón para ejecutar el código
            JButton runButton = new JButton("Ejecutar Código");
            runButton.setFont(new Font("Arial", Font.BOLD, 14));
            runButton.addActionListener(e -> {
                String code = codeArea.getText();
                // Limpiar la consola antes de cada ejecución (opcional)
                // consoleArea.setText(""); // Descomentar si quieres que se limpie

                appendToConsole("--- Ejecutando Código ---\n" + code + "\n-------------------------");

                if (code.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Por favor, ingrese algún comando.", "Entrada Vacía", JOptionPane.WARNING_MESSAGE);
                    appendToConsole("AVISO: Intento de ejecución con entrada vacía.");
                    return;
                }

                try {
                    ejecutarCodigo(code); // Este método ahora usará appendToConsole
                    // El JOptionPane de "Ejecución Completa" ya se muestra dentro de las acciones de CUP si es necesario
                    // o al final de ejecutarCodigo si no hay errores específicos de comandos.
                    // appendToConsole("INFO: Código procesado.\n--- Fin Ejecución ---\n"); // Mensaje general
                } catch (Exception ex) {
                    // Los errores sintácticos y léxicos deberían ser manejados por CUP/JFlex
                    // y mostrados a través de report_error (que ahora usa appendToConsole)
                    // o directamente en la consola.
                    String errorMessage = "ERROR CRÍTICO DURANTE LA EJECUCIÓN: " + ex.getMessage();
                    appendToConsole(errorMessage);
                    ex.printStackTrace(new PrintStream(new TextAreaOutputStream(consoleArea))); // Imprimir stack trace a la consola
                    JOptionPane.showMessageDialog(frame, errorMessage, "Error Crítico", JOptionPane.ERROR_MESSAGE);
                }
            });

            // Botón para limpiar el área de código
            JButton clearCodeButton = new JButton("Limpiar Código");
            clearCodeButton.addActionListener(e -> codeArea.setText(""));

            // Botón para limpiar la consola
            JButton clearConsoleButton = new JButton("Limpiar Consola");
            clearConsoleButton.addActionListener(e -> consoleArea.setText(""));

            // Panel para los botones
            JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
            buttonPanel.add(runButton);
            buttonPanel.add(clearCodeButton);
            buttonPanel.add(clearConsoleButton);

            // Panel principal que divide el área de código y la consola
            JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, codeScrollPane, consoleScrollPane);
            splitPane.setResizeWeight(0.7); // Proporción inicial del espacio (70% para código)

            // Agregar componentes al frame
            frame.getContentPane().add(splitPane, BorderLayout.CENTER);
            frame.getContentPane().add(buttonPanel, BorderLayout.SOUTH);

            // Redirigir System.out y System.err a nuestra consola JTextArea
            PrintStream printStream = new PrintStream(new TextAreaOutputStream(consoleArea));
            System.setOut(printStream);
            System.setErr(printStream);

            frame.setVisible(true);
            appendToConsole("IDE Lista. Ingresa tus comandos y presiona 'Ejecutar Código'.");
        });
    }

    // Método para añadir texto a nuestra consola JTextArea
    private static void appendToConsole(String text) {
        SwingUtilities.invokeLater(() -> { // Asegurar que las actualizaciones de UI se hagan en el EDT
            consoleArea.append(text + "\n");
            consoleArea.setCaretPosition(consoleArea.getDocument().getLength()); // Auto-scroll
        });
    }

    private static void ejecutarCodigo(String codigoFuente) throws Exception {
        // Los System.out.println y System.err.println dentro de JFlex/CUP ahora irán a la consolaArea.
        // También los mensajes de las acciones en parse.cup.

        Lexer lexer = new Lexer(new StringReader(codigoFuente));
        parsito cupParser = new parsito(lexer);

        cupParser.parse(); // Esto ejecutará las acciones y los System.out/err
        // que ahora se redirigen a la consolaArea.

        // Ya no necesitamos un JOptionPane general de "éxito" aquí,
        // ya que las acciones del parser (como SHOW) o los errores
        // ya interactúan con el usuario o escriben en la consola.
        appendToConsole("INFO: Procesamiento de comandos finalizado.\n--- Fin Ejecución ---\n");
    }
}

// Clase auxiliar para redirigir un OutputStream a un JTextArea
class TextAreaOutputStream extends OutputStream {
    private JTextArea textArea;
    private StringBuilder sb = new StringBuilder();

    public TextAreaOutputStream(JTextArea textArea) {
        this.textArea = textArea;
    }

    @Override
    public void flush() {
        // Asegurar que la actualización se haga en el Event Dispatch Thread
        SwingUtilities.invokeLater(() -> {
            textArea.append(sb.toString());
            textArea.setCaretPosition(textArea.getDocument().getLength()); // Auto-scroll
        });
        sb.setLength(0); // Limpiar el buffer
    }

    @Override
    public void close() {
        flush(); // Asegurar que cualquier texto restante se escriba
    }

    @Override
    public void write(int b) {
        // Este método se llama para cada byte.
        // Es más eficiente bufferizar y actualizar en flush() o cuando se encuentra un newline.
        if (b == '\r') // Ignorar retornos de carro si vienen antes de un newline
            return;

        if (b == '\n') {
            sb.append((char)b); // Añadir el newline al buffer
            flush(); // Escribir la línea completa en el JTextArea
            return;
        }
        sb.append((char) b); // Bufferizar el caracter
    }

    @Override
    public void write(byte[] b, int off, int len) {
        // Este método se llama para bloques de bytes.
        // Es más eficiente procesar el bloque y actualizar en flush() o cuando se encuentra un newline.
        String str = new String(b, off, len);
        sb.append(str);
        // Podríamos llamar a flush() aquí si el string contiene newlines o es muy largo,
        // pero para simplicidad, dejamos que el newline individual o el flush() explícito lo manejen.
        // Si el rendimiento es un problema con muchos mensajes cortos sin newline, se puede optimizar.
        if (str.contains("\n")) { // Si hay newlines, mejor hacer flush para ver las líneas completas
            flush();
        }
    }
}