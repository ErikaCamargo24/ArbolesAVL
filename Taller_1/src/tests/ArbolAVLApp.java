package tests;
/**
* Erika
*/

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ArbolAVLApp extends JFrame implements ActionListener {
  
	private static final long serialVersionUID = 1L;
	private ArbolAVL arbolAVL;
    private JTextField valorTextField;
    private JTextArea resultadoTextArea;

    public ArbolAVLApp() {
        arbolAVL = new ArbolAVL();
        valorTextField = new JTextField(10);
        resultadoTextArea = new JTextArea(20, 40);
        resultadoTextArea.setEditable(false);

        JButton insertarButton = new JButton("Insertar Valor");
        insertarButton.addActionListener(this);

        JButton eliminarButton = new JButton("Eliminar Valor");
        eliminarButton.addActionListener(this);

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.add(new JLabel("Insertar o Eliminar Valor:"));
        panel.add(valorTextField);
        panel.add(insertarButton);
        panel.add(eliminarButton);

        JScrollPane scrollPane = new JScrollPane(resultadoTextArea);

        getContentPane().add(panel, BorderLayout.NORTH);
        getContentPane().add(scrollPane, BorderLayout.CENTER);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Árbol AVL");
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String valorStr = valorTextField.getText().trim();
        if (!valorStr.isEmpty()) {
            int valor = Integer.parseInt(valorStr);
            if (e.getActionCommand().equals("Insertar Valor")) {
                arbolAVL.raiz = arbolAVL.insertar(arbolAVL.raiz, valor);
            } else if (e.getActionCommand().equals("Eliminar Valor")) {
                arbolAVL.raiz = arbolAVL.eliminar(arbolAVL.raiz, valor);
            }
            valorTextField.setText("");
            mostrarArbol();
        }
    }

    private void mostrarArbol() {
        resultadoTextArea.setText("");
        resultadoTextArea.append("Árbol AVL:\n");
        resultadoTextArea.append("-------------------\n");
        mostrarArbolRecursivamente(arbolAVL.raiz);
    }

    private void mostrarArbolRecursivamente(Nodo nodo) {
        if (nodo != null) {
            resultadoTextArea.append(Integer.toString(nodo.valor) + "\n");
            mostrarArbolRecursivamente(nodo.izquierda);
            mostrarArbolRecursivamente(nodo.derecha);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ArbolAVLApp();
            }
        });
    }
}