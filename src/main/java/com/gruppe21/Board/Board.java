package com.gruppe21.Board;

import com.gruppe21.squares.models.*;

import java.awt.*;

public class Board {

    Square[] square;

    public Board(Square[] square){
        this.square = square;

        square[0] = new PropertySquare(1 , "", "Første felt", Color.white, 0, 2000 );
        square[1] = new PropertySquare(2, "", "Andet felt", Color.yellow, 0, 3000);
        square[2] = new CardSquare(3, "", "Tredje felt",, 0);
        square[3] = new TeleportSquare(4, "", "Fjerde felt", Color.green, 0);
        square[4] = new MoneySquare(5, "", "Femte felt", , 0)



    }
}

    //public CardSquare(int id, String label, String description, Color color, int statusEffect)