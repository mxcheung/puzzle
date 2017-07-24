package com.maxcheung.arrayjumper;

import java.util.Scanner;

public class Solution {

    private static final String ZERO = "0";

    private Solution(){
        
    }
    static boolean playGame(int arrayLength, int jumpLength, String data ) {
        String[] gameArray = data.split(" ");
        if (gameArray.length != arrayLength) {
            throw new IllegalArgumentException("Mismatch with array length provided: " + arrayLength + " with actual length:  " + gameArray.length);
        }
        return isPossibleToWin(gameArray, arrayLength, jumpLength, 0 , 0);
    }

    private static boolean isPossibleToWin(String[] dataArray, int arrayLength, int jumpLength, int currentPos, int lastJumpPos) {

        boolean didWin = false;

        int highRange = stepForward(dataArray, arrayLength, currentPos);
        if (highRange == arrayLength - 1)
            return true;

        int lowRange = findRange(dataArray, jumpLength, lastJumpPos, highRange);
        for (int i = lowRange; i <= highRange; i++) {
            if ((i + jumpLength) < arrayLength && dataArray[i + jumpLength].equals(ZERO) && jumpLength != 0) {
                didWin = isPossibleToWin(dataArray, arrayLength, jumpLength, i + jumpLength, i);
            }

            if (didWin || canJumpToGoal(arrayLength, jumpLength, i) || canWalkToGoal(arrayLength, i)) {
                didWin = true;
                break;
            }
        }

        return didWin;
    }

    private static int stepForward(String[] dataArray, int arrayLength, int currentPos) {
        int i = currentPos;

        while (i < arrayLength - 1 && ZERO.equals(dataArray[i + 1])) {
            i++;
        }
        return i;
    }

    private static int findRange(String[] gameArray, int jumpLength, int lastJumpPos, int currentPos) {
        int i = currentPos;
        while (i > lastJumpPos && ZERO.equals(gameArray[i - 1]) && (i + jumpLength) > currentPos + 1) {
            i--;
        }
        return i;
    }

    private static boolean canWalkToGoal(int arrayLength, int currentPos) {
        return (currentPos + 1) >= arrayLength;
    }

    private static boolean canJumpToGoal( int arrayLength, int jumpLength, int currentPos) {
        return (currentPos + jumpLength) >= arrayLength;
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
