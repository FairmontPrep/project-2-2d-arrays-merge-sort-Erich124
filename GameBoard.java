import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.Comparator;

public class GameBoard extends JFrame {
    public int SIZE = 8;
    private JPanel[][] squares = new JPanel[SIZE][SIZE]; // 2D array for board
    private String[][] piecesArray; //2D array = image::HP::board position

    public GameBoard() {
        setTitle("Poke Board");
        setSize(750, 750);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(SIZE, SIZE)); // Use GridLayout for the board layout

        // Initialize the 2D array of panels
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                squares[row][col] = new JPanel();
                if ((row + col) % 2 == 0) {
                    squares[row][col].setBackground(Color.WHITE); // Color black for even squares
                } else {
                    squares[row][col].setBackground(new Color(101, 67, 33)); // Color dark gray for odd squares
                }
                add(squares[row][col]); // Add each square to the board
            }
        }

        // Initialize Pokémon pieces array
        piecesArray = new String[32][3];  //your array should be at least 2 columns
        loadPieces();
        for (int i = 0; i < piecesArray.length; i++) {
            for (int j = 0; j < piecesArray[i].length; j++) {
                System.out.println("piecesArray[" + i + "][" + j + "] = " + piecesArray[i][j]);
            }
        }

        // Initially populate the board with pieces
        populateBoard();
    }

    public JPanel[][] sortImages(JPanel[][] finalPositions) {
    // Custom merge sort implementation
    mergeSort(piecesArray, 0, piecesArray.length - 1, new Comparator<String[]>() {
        @Override
        public int compare(String[] piece1, String[] piece2) {
            return Integer.compare(Integer.parseInt(piece1[2]), Integer.parseInt(piece2[2]));
        }
    });
    return finalPositions;
}

private void mergeSort(String[][] arr, int left, int right, Comparator<String[]> comparator) {
    if (left < right) {
        int mid = left + (right - left) / 2;
        mergeSort(arr, left, mid, comparator);
        mergeSort(arr, mid + 1, right, comparator);
        merge(arr, left, mid, right, comparator);
    }
}

private void merge(String[][] arr, int left, int mid, int right, Comparator<String[]> comparator) {
    String[][] leftArr = Arrays.copyOfRange(arr, left, mid + 1);
    String[][] rightArr = Arrays.copyOfRange(arr, mid + 1, right + 1);

    int i = 0, j = 0, k = left;
    while (i < leftArr.length && j < rightArr.length) {
        if (comparator.compare(leftArr[i], rightArr[j]) <= 0) {
            arr[k++] = leftArr[i++];
        } else {
            arr[k++] = rightArr[j++];
        }
    }

    while (i < leftArr.length) {
        arr[k++] = leftArr[i++];
    }

    while (j < rightArr.length) {
        arr[k++] = rightArr[j++];
    }
}

    private void populateBoard() {
    // Clear existing components from target rows (0,1,6,7)
    for (int row : new int[]{0, 1, 6, 7}) {
        for (int col = 0; col < SIZE; col++) {
            squares[row][col].removeAll();
        }
    }

    // Place all 32 elements
    for (int i = 0; i < piecesArray.length; i++) {
        String[] piece = piecesArray[i];
        String imagePath = piece[0];
        String hpText = piece[1];

        // Calculate position
        int row = i < 16 
            ? i / SIZE          // First 16: rows 0-1 (0-7 → row 0, 8-15 → row 1)
            : 6 + (i - 16) / SIZE; // Last 16: rows 6-7 (16-23 → row 6, 24-31 → row 7)
        
        int col = i % SIZE;  // Column remains 0-7 for all rows

        // Create image component with larger size
        ImageIcon icon = new ImageIcon(imagePath);
        Image scaledImage = icon.getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH); // Changed to 70x70
        
        // Create layered labels
        JLabel pieceLabel = new JLabel(new ImageIcon(scaledImage));
        JLabel textLabel = new JLabel(hpText, SwingConstants.CENTER);
        textLabel.setForeground(Color.BLACK);
        textLabel.setFont(new Font("Arial", Font.BOLD, 12)); // Optional: Larger font

        // Create transparent panel
        JPanel piecePanel = new JPanel(new BorderLayout());
        piecePanel.setOpaque(false);
        piecePanel.add(pieceLabel, BorderLayout.CENTER);
        piecePanel.add(textLabel, BorderLayout.SOUTH);

        // Add to board
        squares[row][col].setLayout(new BorderLayout());
        squares[row][col].add(piecePanel, BorderLayout.CENTER);
    }

    revalidate();
    repaint();
}
    private void loadPieces() {
        piecesArray[0][0] = "black-rook-chess-o0KQ5D0-600.jpg"; piecesArray[0][1] = "Rook"; piecesArray[0][2]="1";
        piecesArray[1][0] = "images (13).jpeg"; piecesArray[1][1] = "Knight"; piecesArray[1][2]="9";
        piecesArray[2][0] = "chess-black-bishop-chessmen-x70kVL3-600.jpg"; piecesArray[2][1] = "Bishop"; piecesArray[2][2]="17";
        piecesArray[3][0] = "9ba0277979c424fdb498efb66a7b140c.jpg"; piecesArray[3][1] = "Queen"; piecesArray[3][2]="25";
        piecesArray[4][0] = "chess-black-king-lXNzz1D-600.jpg"; piecesArray[4][1] = "King"; piecesArray[4][2]="33";
        piecesArray[5][0] = "chess-black-bishop-chessmen-x70kVL3-600.jpg"; piecesArray[5][1] = "Bishop"; piecesArray[5][2]="41";
        piecesArray[6][0] = "images (13).jpeg"; piecesArray[6][1] = "Knight"; piecesArray[6][2]="2";
        piecesArray[7][0] = "black-rook-chess-o0KQ5D0-600.jpg"; piecesArray[7][1] = "Rook"; piecesArray[7][2]="3";
        piecesArray[8][0] = "black-pawn-d7aeaeC-600.jpg"; piecesArray[8][1] = "Pawn"; piecesArray[8][2]="11";
        piecesArray[9][0] = "black-pawn-d7aeaeC-600.jpg"; piecesArray[9][1] = "Pawn"; piecesArray[9][2]="19";
        piecesArray[10][0] = "black-pawn-d7aeaeC-600.jpg"; piecesArray[10][1] = "Pawn"; piecesArray[10][2]="27";
        piecesArray[11][0] = "black-pawn-d7aeaeC-600.jpg"; piecesArray[11][1] = "Pawn"; piecesArray[11][2]="36";
        piecesArray[12][0] = "black-pawn-d7aeaeC-600.jpg"; piecesArray[12][1] = "Pawn"; piecesArray[12][2]="49";
        piecesArray[13][0] = "black-pawn-d7aeaeC-600.jpg"; piecesArray[13][1] = "Pawn"; piecesArray[13][2]="54";
        piecesArray[14][0] = "black-pawn-d7aeaeC-600.jpg"; piecesArray[14][1] = "Pawn"; piecesArray[14][2]="62";
        piecesArray[15][0] = "black-pawn-d7aeaeC-600.jpg"; piecesArray[15][1] = "Pawn"; piecesArray[15][2]="63";
        piecesArray[16][0] = "white-pawn-J3yAok8-600.jpg"; piecesArray[16][1] = "Pawn"; piecesArray[16][2]="4";
        piecesArray[17][0] = "white-pawn-J3yAok8-600.jpg"; piecesArray[17][1] = "Pawn"; piecesArray[17][2]="37";
        piecesArray[18][0] = "white-pawn-J3yAok8-600.jpg"; piecesArray[18][1] = "Pawn"; piecesArray[18][2]="28";
        piecesArray[19][0] = "white-pawn-J3yAok8-600.jpg"; piecesArray[19][1] = "Pawn"; piecesArray[19][2]="55";
        piecesArray[20][0] = "white-pawn-J3yAok8-600.jpg"; piecesArray[20][1] = "Pawn"; piecesArray[20][2]="12";
        piecesArray[21][0] = "white-pawn-J3yAok8-600.jpg"; piecesArray[21][1] = "Pawn"; piecesArray[21][2]="20";
        piecesArray[22][0] = "white-pawn-J3yAok8-600.jpg"; piecesArray[22][1] = "Pawn"; piecesArray[22][2]="50";
        piecesArray[23][0] = "white-pawn-J3yAok8-600.jpg"; piecesArray[23][1] = "Pawn"; piecesArray[23][2]="10";
        piecesArray[24][0] = "white-rook-chess-chessmen-4GaMZQF-600.jpg"; piecesArray[24][1] = "Rook"; piecesArray[24][2]="8";
        piecesArray[25][0] = "images (12).jpeg"; piecesArray[25][1] = "Knight"; piecesArray[25][2]="16";
        piecesArray[26][0] = "chess-piece-bishop-white-chessmen-WyXz4L4-600.jpg"; piecesArray[26][1] = "Bishop"; piecesArray[26][2]="24";
        piecesArray[27][0] = "white-queen-chess-Gq1PRQF-600.jpg"; piecesArray[27][1] = "Queen"; piecesArray[27][2]="32";
        piecesArray[28][0] = "images (11).jpeg"; piecesArray[28][1] = "King"; piecesArray[28][2]="40";
        piecesArray[29][0] = "chess-piece-bishop-white-chessmen-WyXz4L4-600.jpg"; piecesArray[29][1] = "Bishop"; piecesArray[29][2]="48";
        piecesArray[30][0] = "images (12).jpeg"; piecesArray[30][1] = "Knight"; piecesArray[30][2]="52";
        piecesArray[31][0] = "white-rook-chess-chessmen-4GaMZQF-600.jpg"; piecesArray[31][1] = "Rook"; piecesArray[31][2]="51";
       
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GameBoard board = new GameBoard();
            board.setVisible(true);
        });
    }
}