import java.util.Scanner;

public class Solution {

    static boolean playGame(int arrayLength, int jumpLength, String data ) {
        String[] gameArray = data.split(" ");
        if (gameArray.length != arrayLength) {
            throw new IllegalArgumentException("Mismatch with array length provided: " + arrayLength + " with actual length:  " + gameArray.length);
        }
        return isPossibleToWin(gameArray, arrayLength, jumpLength, 0 , 0);
    }

    private static boolean isPossibleToWin(String[] dataArray, int arrayLength, int jumpLength, int currentPos, int lastJumpPos) {

        boolean didWin = false;

        int range = stepForward(dataArray, arrayLength, currentPos);
        if (range == arrayLength - 1)
            return true;

        currentPos = findPreviousStep(dataArray, jumpLength, lastJumpPos, range);
        for (int i = currentPos; i <= range; i++) {
            if ((i + jumpLength) < arrayLength && dataArray[i + jumpLength].equals("0") && jumpLength != 0) {
                didWin = isPossibleToWin(dataArray, arrayLength, jumpLength, i + jumpLength, i);
            }

            if (didWin || canJumpToGoal(dataArray, jumpLength, i) || canWalkToGoal(dataArray, i)) {
                didWin = true;
                break;
            }
        }

        return didWin;
    }

    private static int stepForward(String[] dataArray, int arrayLength, int currentPos) {
        int i = currentPos;

        while (i < arrayLength - 1 && dataArray[i + 1].equals("0")) {
            i++;
        }
        return i;
    }

    private static int findPreviousStep(String[] gameArray, int jumpLength, int lastJumpPos, int currentPos) {
        int i = currentPos;
        while (i > lastJumpPos && gameArray[i - 1].equals("0") && (i + jumpLength) > currentPos + 1) {
            i--;
        }
        return i;
    }

    private static boolean canWalkToGoal(String[] gameArray, int currentPos) {
        return (currentPos + 1) >= gameArray.length;
    }

    private static boolean canJumpToGoal(String[] gameArray, int jumpLength, int currentPos) {
        return (currentPos + jumpLength) >= gameArray.length;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int numCases = sc.nextInt();
        for (int i = 0; i < numCases; i++) {
            int arraySize = sc.nextInt();
            int jumpLength = sc.nextInt();
            sc.nextLine();
            String[] gameArray = sc.nextLine().split(" ");
            if (isPossibleToWin(gameArray, arraySize, jumpLength, 0, 0)) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        }
        sc.close();
    }
}
