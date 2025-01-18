import java.util.*;

public class UniqueChessPos {

    public static class ChessPcs {
        char type;
        char col;
        int row;

        public ChessPcs(char type, char col, int row) {
            this.type = type;
            this.col = col;
            this.row = row;
        }

        @Override
        public String toString() {
            return "" + type + col + row;
        }
    }

    public static boolean validPos(char col, int row) {
        return col >= 'A' && col <= 'H' && row >= 1 && row <= 8;
    }

    public static List<String> moves(ChessPcs piece) {
        List<String> moves = new ArrayList<>();
        char col = piece.col;
        int row = piece.row;

        if (piece.type == 'Q' || piece.type == 'R') {
            for (int i = 1; i <= 8; i++) {
                if (i != row) moves.add("" + col + i);
            }
            for (char c = 'A'; c <= 'H'; c++) {
                if (c != col) moves.add("" + c + row);
            }
        }

        if (piece.type == 'Q' || piece.type == 'B') {
            for (int i = -7; i <= 7; i++) {
                if (i != 0) {
                    if (validPos((char) (col + i), row + i)) moves.add("" + (char) (col + i) + (row + i));
                    if (validPos((char) (col + i), row - i)) moves.add("" + (char) (col + i) + (row - i));
                }
            }
        }

        return moves;
    }

    public static int UniquePos(List<ChessPcs> actPieces, int depth) {
        Set<String> uniquestat = new HashSet<>();
        Queue<String> stateq = new LinkedList<>();

        StringBuilder startState = new StringBuilder();
        for (ChessPcs piece : actPieces) {
            startState.append(piece.toString()).append(";");
        }

        stateq.add(startState.toString());
        uniquestat.add(startState.toString());

        for (int level = 0; level < depth; level++) {
            int size = stateq.size();
            for (int i = 0; i < size; i++) {
                String currentState = stateq.poll();
                List<ChessPcs> pieceList = decodeState(currentState);

                for (int idx = 0; idx < pieceList.size(); idx++) {
                    ChessPcs piece = pieceList.get(idx);
                    List<String> possibleMoves = moves(piece);

                    for (String move : possibleMoves) {
                        char newCol = move.charAt(0);
                        int newRow = Integer.parseInt(move.substring(1));

                        ChessPcs movedPcs = new ChessPcs(piece.type, newCol, newRow);
                        List<ChessPcs> updatedPieces = new ArrayList<>(pieceList);
                        updatedPieces.set(idx, movedPcs);

                        String newConfig = encodeState(updatedPieces);
                        if (!uniquestat.contains(newConfig)) {
                            uniquestat.add(newConfig);
                            stateq.add(newConfig);
                        }
                    }
                }
            }
        }

        return uniquestat.size();
    }

    private static List<ChessPcs> decodeState(String state) {
        String[] parts = state.split(";");
        List<ChessPcs> pieces = new ArrayList<>();
        for (String part : parts) {
            if (!part.isEmpty()) {
                char type = part.charAt(0);
                char col = part.charAt(1);
                int row = Integer.parseInt(part.substring(2));
                pieces.add(new ChessPcs(type, col, row));
            }
        }
        return pieces;
    }

    private static String encodeState(List<ChessPcs> pieces) {
        StringBuilder state = new StringBuilder();
        for (ChessPcs piece : pieces) {
            state.append(piece.toString()).append(";");
        }
        return state.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] initialPositions = scanner.nextLine().split(" ");
        int plyDepth = Integer.parseInt(scanner.nextLine());

        List<ChessPcs> boardPieces = new ArrayList<>();
        for (String pieceInfo : initialPositions) {
            char type = pieceInfo.charAt(0);
            char col = pieceInfo.charAt(1);
            int row = Character.getNumericValue(pieceInfo.charAt(2));
            boardPieces.add(new ChessPcs(type, col, row));
        }

        int uniqueConfigurations = UniquePos(boardPieces, plyDepth);
        System.out.println(uniqueConfigurations);
    }
}