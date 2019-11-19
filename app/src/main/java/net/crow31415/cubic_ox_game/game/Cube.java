package net.crow31415.cubic_ox_game.game;

import java.util.Scanner;

public class Cube {

    public static final int DIRECTION_LEFT = 0;
    public static final int DIRECTION_RIGHT = 1;
    public static final int DIRECTION_UP = 2;
    public static final int DIRECTION_DOWN = 3;

    private Block[][][] blocks;

    public Cube(){
        blocks = new Block[3][3][3];
        for(int i = 0; i< blocks.length; i++){
            for(int j = 0; j< blocks[0].length; j++){
                for(int k = 0; k< blocks[0].length; k++){
                    blocks[i][j][k] = new Block();
                }
            }
        }
    }

    public void userInterface(int mark){
        Scanner sc = new Scanner(System.in);
        int action;
        while (true){
            System.out.println("1:mark 2:turn");
            System.out.print("action:");
            action = sc.nextInt();
            if(action == 1 || action == 2){
                break;
            }
            System.out.println("Illegal argument.");
        }

        switch (action){
            case 1:
                markInterface(mark);
                break;

            case 2:
                turnInterface();
                break;
        }
    }

    public void markInterface(int mark){
        Scanner sc = new Scanner(System.in);
        while (true){
            int x, y;
            while (true){
                System.out.print("row:");
                y = sc.nextInt();
                if(y>=0 && y< blocks[0].length){
                    break;
                }
                System.out.println("Illegal argument.");
            }

            while (true){
                System.out.print("column:");
                x = sc.nextInt();
                if(x>=0 && x< blocks[0][0].length){
                    break;
                }
                System.out.println("Illegal argument.");
            }

            boolean success = mark(mark, x, y);
            if(success){
               break;
            }
            System.out.println("Illegal argument.");
        }

    }

    public void turnInterface(){
        Scanner sc = new Scanner(System.in);
        while (true){
            int direction, count;
            while (true){
                System.out.println(DIRECTION_LEFT + ":Left " + DIRECTION_RIGHT + ":Right " + DIRECTION_UP + ":Up " + DIRECTION_DOWN + ":Down");
                System.out.print("direction:");
                direction = sc.nextInt();
                if(direction == DIRECTION_LEFT || direction == DIRECTION_RIGHT || direction == DIRECTION_UP || direction == DIRECTION_DOWN){
                    break;
                }
                System.out.println("Illegal argument.");
            }

            while (true){
                String count_str = "";
                switch (direction){
                    case DIRECTION_LEFT:
                    case DIRECTION_RIGHT:
                        count_str = "row";
                        break;

                    case DIRECTION_UP:
                    case DIRECTION_DOWN:
                        count_str = "column";
                        break;
                }

                System.out.print(count_str + ":");
                count = sc.nextInt();
                if(count>=0 && count< blocks[0][0].length){
                    break;
                }
                System.out.println("Illegal argument.");
            }

            boolean success = turn(direction, count);
            if(success){
                break;
            }
            System.out.println("Illegal argument.");
        }

    }

    public void randomMark(){
        int column, row, depth;
        while (true){
            column = (int)(Math.random()*3);
            row = (int)(Math.random()*3);
            depth = (int)(Math.random()*2) + 1;

            boolean success = mark(Block.MARK_CROSS, column, row, depth);

            if(success){
                break;
            }
        }
    }

    public boolean mark(int mark, int column, int row){
        return mark(mark, column, row, 0);
    }

    public boolean mark(int mark, int column, int row, int depth){
        if(column == 1 && row == 1 && depth == 1){
            return false;
        }
        return blocks[depth][row][column].mark(mark);
    }

    public int getMark(int column, int row){
        return getMark(column, row, 0);
    }

    public int getMark(int column, int row, int depth){
        return blocks[depth][row][column].getMark();
    }

    public boolean turn(int direction, int count){
        switch (direction){
            case DIRECTION_LEFT:
            case DIRECTION_RIGHT:
                return turnRow(direction, count);

            case DIRECTION_UP:
            case DIRECTION_DOWN:
                return turnColumn(direction, count);

            default:
                return false;
        }
    }

    public boolean turnRow(int direction ,int row){
        //Front
        Block tmpBlock0 = blocks[0][row][0];
        Block tmpBlock1 = blocks[0][row][1];
        Block tmpBlock2 = blocks[0][row][2];

        switch(direction){
            case DIRECTION_LEFT:
                //Right -> Front
                blocks[0][row][0] = blocks[0][row][2];
                blocks[0][row][1] = blocks[1][row][2];
                blocks[0][row][2] = blocks[2][row][2];

                //Back -> Right
                blocks[0][row][2] = blocks[2][row][2];
                blocks[1][row][2] = blocks[2][row][1];
                blocks[2][row][2] = blocks[2][row][0];

                //Left -> Back
                blocks[2][row][2] = blocks[0][row][0];
                blocks[2][row][1] = blocks[1][row][0];
                blocks[2][row][0] = blocks[2][row][0];

                //Front(tmp) -> Left
                blocks[0][row][0] = tmpBlock0;
                blocks[1][row][0] = tmpBlock1;
                blocks[2][row][0] = tmpBlock2;

                return true;

            case DIRECTION_RIGHT:
                //Left -> Front
                blocks[0][row][0] = blocks[0][row][0];
                blocks[0][row][1] = blocks[1][row][0];
                blocks[0][row][2] = blocks[2][row][0];

                //Back -> Left
                blocks[0][row][0] = blocks[2][row][2];
                blocks[1][row][0] = blocks[2][row][1];
                blocks[2][row][0] = blocks[2][row][0];

                //Right -> Back
                blocks[2][row][2] = blocks[0][row][2];
                blocks[2][row][1] = blocks[1][row][2];
                blocks[2][row][0] = blocks[2][row][2];

                //Front(tmp) -> Right
                blocks[0][row][2] = tmpBlock0;
                blocks[1][row][2] = tmpBlock1;
                blocks[2][row][2] = tmpBlock2;

                return true;

            default:
                return false;
        }

    }

    public boolean turnColumn(int direction, int column){
        //Front
        Block tmpBlock0 = blocks[0][0][column];
        Block tmpBlock1 = blocks[0][1][column];
        Block tmpBlock2 = blocks[0][2][column];

        switch (direction){
            case DIRECTION_UP:
                //Down -> Front
                blocks[0][0][column] = blocks[0][2][column];
                blocks[0][1][column] = blocks[1][2][column];
                blocks[0][2][column] = blocks[2][2][column];

                //Back -> Down
                blocks[0][2][column] = blocks[2][2][column];
                blocks[1][2][column] = blocks[2][1][column];
                blocks[2][2][column] = blocks[2][0][column];

                //Up -> Back
                blocks[2][2][column] = blocks[2][0][column];
                blocks[2][1][column] = blocks[1][0][column];
                blocks[2][0][column] = blocks[0][0][column];

                //Front(tmp) -> Up
                blocks[2][0][column] = tmpBlock0;
                blocks[1][0][column] = tmpBlock1;
                blocks[0][0][column] = tmpBlock2;

                return true;

            case DIRECTION_DOWN:
                //Up -> Front
                blocks[0][0][column] = blocks[2][0][column];
                blocks[0][1][column] = blocks[1][0][column];
                blocks[0][2][column] = blocks[0][0][column];

                //Back -> Up
                blocks[2][0][column] = blocks[2][2][column];
                blocks[1][0][column] = blocks[2][1][column];
                blocks[0][0][column] = blocks[2][0][column];

                //Down -> Back
                blocks[2][2][column] = blocks[0][2][column];
                blocks[2][1][column] = blocks[1][2][column];
                blocks[2][0][column] = blocks[2][2][column];

                //Front(tmp) -> Down
                blocks[0][2][column] = tmpBlock0;
                blocks[1][2][column] = tmpBlock1;
                blocks[2][2][column] = tmpBlock2;

                return true;

            default:
                return false;
        }

    }

    public boolean checkFinish(){
        //Check Row
        row:for(int row = 0; row< blocks[0].length; row++){
            int tmpFirstMark = getMark(0, row);
            if(tmpFirstMark == Block.MARK_NONE){
                continue;
            }
            for (int column = 1; column< blocks[0][0].length; column++){
                int tmpMark = getMark(column,  row);
                if(tmpMark != tmpFirstMark){
                    continue row;
                }
            }
            return true;
        }

        //Check Column
        column:for(int column = 0; column< blocks[0][0].length; column++){
            int tmpFirstMark = getMark(column, 0);
            if(tmpFirstMark == Block.MARK_NONE){
                continue;
            }
            for (int row = 1; row< blocks[0].length; row++){
                int tmpMark = getMark(column,  row);
                if(tmpMark != tmpFirstMark){
                    continue column;
                }
            }
            return true;
        }

        //Check Cross (LeftUp - RightDown)
        boolean checkFlg = true;
        int tmpFirstMark = getMark(0, 0);
        if(tmpFirstMark == Block.MARK_NONE){
            checkFlg = false;
        }
        for (int i = 1; i< blocks[0][0].length; i++){
            int tmpMark = getMark(i, i);
            if(tmpMark != tmpFirstMark){
                checkFlg = false;
                break;
            }
        }
        if (checkFlg){
            return true;
        }

        //Check Cross (LeftDown - RightUp)
        checkFlg = true;
        tmpFirstMark = getMark(0, blocks.length-1);
        if(tmpFirstMark == Block.MARK_NONE){
            checkFlg = false;
        }
        for (int i = 1; i< blocks[0][0].length; i++){
            int tmpMark = getMark(i, blocks.length-1-i);
            if(tmpMark != tmpFirstMark){
                checkFlg = false;
                break;
            }
        }
        return checkFlg;
    }

    public void showField(){
        for(int row = 0; row< blocks[0].length; row++){
            for(int column = 0; column< blocks[0][0].length; column++){
                char markChar;
                switch (getMark(column, row, 0)){
                    case Block.MARK_NONE:
                        markChar = '-';
                        break;
                    case Block.MARK_CIRCLE:
                        markChar = 'o';
                        break;
                    case Block.MARK_CROSS:
                        markChar = 'x';
                        break;
                    default:
                        throw new IllegalStateException();
                }
                System.out.print(markChar);
            }
            System.out.println();
        }
    }

}
