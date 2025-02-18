package blackjack;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class Blackjack {

    public static void main(String[] args) {
        boolean[] cartasUsadas = new boolean[52]; 
        int[] valores = {
            1,2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10,
            1,2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10,
            1,2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10,
            1,2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10,
        };

        double sueldo = 0;
        boolean sueldoValido = false;

        do {
            sueldo = Double.parseDouble(JOptionPane.showInputDialog(null, "Ingrese su sueldo inicial:"));
            if (sueldo <= 0) {
                JOptionPane.showMessageDialog(null, "Para jugar debes tener saldo en tu cuenta");
            } else {
                sueldoValido = true;
            }
        } while (!sueldoValido);

        boolean seguirJugando = true;

        while (seguirJugando && sueldo > 0) {
            double apuesta = 0;
            boolean apuestaValida = false;

            do {
                apuesta = Double.parseDouble(JOptionPane.showInputDialog(null, "Ingrese su apuesta:"));
                if (apuesta > 0 && apuesta <= sueldo) {
                    apuestaValida = true;
                } else {
                    JOptionPane.showMessageDialog(null, "La apuesta debe ser mayor a 0 y menor o igual a su sueldo.");
                }
            } while (!apuestaValida);

            int sumaHumano = 0;
            int sumaMaquina = 0;

            // Juega el humano
            boolean continuar = true;

            do {
                int cartaHumano;
                do {
                    cartaHumano = (int) (Math.random() * 52);
                } while (cartasUsadas[cartaHumano]);
                cartasUsadas[cartaHumano] = true;

                sumaHumano += valores[cartaHumano];

                
                int palo = cartaHumano / 13 + 1; 
                int numero = cartaHumano % 13 + 1; 

                JOptionPane.showMessageDialog(null, "Esta es la carta del usuario",
                        "Carta del usuario",
                        JOptionPane.PLAIN_MESSAGE,
                        new ImageIcon(Blackjack.class.getResource("/img/" + (palo * 100 + numero) + ".png")));

                if (sumaHumano > 21) {
                    JOptionPane.showMessageDialog(null, "Has superado 21. ¡Perdiste!");
                    
                }

                continuar = JOptionPane.showConfirmDialog(null, "¿Desea otra carta?") == JOptionPane.YES_OPTION;
            } while (continuar && sumaHumano < 22);

            //juega la maquina
            do {
                int cartaMaquina;
                do {
                    cartaMaquina = (int) (Math.random() * 52);
                } while (cartasUsadas[cartaMaquina]);
                cartasUsadas[cartaMaquina] = true;

                sumaMaquina += valores[cartaMaquina];

                int palo = cartaMaquina / 13 + 1; 
                int numero = cartaMaquina % 13 + 1; 
     
                JOptionPane.showMessageDialog(null, "Esta es la carta de la maquina",
                        "Carta máquina",
                        JOptionPane.PLAIN_MESSAGE,
                        new ImageIcon(Blackjack.class.getResource("/img/" + (palo * 100 + numero) + ".png")));
            } while (sumaMaquina < 17);

            String resultado = "Humano: " + sumaHumano + " puntos\n" + "Máquina: " + sumaMaquina + " puntos\n";
            if ((sumaHumano > sumaMaquina && sumaHumano <= 21) || sumaMaquina > 21) {
                resultado += "¡Ganaste!";
                sueldo += apuesta;
            } else if (sumaHumano == sumaMaquina) {
                resultado += "¡Empate!";
                JOptionPane.showMessageDialog(null, "Te devolvemos tu apuesta: " + apuesta);
            } else {
                resultado += "¡Perdiste!";
                sueldo -= apuesta;
            }
            JOptionPane.showMessageDialog(null, resultado);

            JOptionPane.showMessageDialog(null, "Su sueldo actual es: " + sueldo);

            seguirJugando = JOptionPane.showConfirmDialog(null, "¿Desea seguir jugando?", "Continuar jugando",
                    JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;

            
            if (seguirJugando) {
                cartasUsadas = new boolean[52];
            }
        }

        JOptionPane.showMessageDialog(null, "Gracias por jugar. Su sueldo final es: " + sueldo);
    }
}