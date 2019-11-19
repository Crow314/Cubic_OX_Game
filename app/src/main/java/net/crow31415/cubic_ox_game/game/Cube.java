package net.crow31415.cubic_ox_game.game;

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

    private boolean mark(int mark, int column, int row, int depth){
        if(column == 1 && row == 1 && depth == 1){
            return false;
        }
        return blocks[depth][row][column].mark(mark);
    }

    public int getMark(int column, int row){
        return getMark(column, row, 0);
    }

    private int getMark(int column, int row, int depth){
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

    private boolean turnRow(int direction ,int row){
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

    private boolean turnColumn(int direction, int column){
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

    public int getSize(){
        return blocks.length;
    }

}
