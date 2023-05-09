package es.ieslavereda.model.GAME;

public interface IDeletePieceManager {

    void addPiece(Pieza p);
    int count(Pieza.PieceType pieceType);
    Pieza getLast();
}
