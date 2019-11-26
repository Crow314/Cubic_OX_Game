package net.crow31415.cubic_ox_game.game;

public class Cube {

    public static final int ACTION_MARK = 0;
    public static final int ACTION_TURN = 1;

    public static final int DIRECTION_LEFT = 0;
    public static final int DIRECTION_RIGHT = 1;
    public static final int DIRECTION_UP = 2;
    public static final int DIRECTION_DOWN = 3;
    public static final int DIRECTION_CLOCKWISE = 4;
    public static final int DIRECTION_COUNTERCLOCKWISE = 5;

    private Block[][][] mBlocks;
    private int[] mLastAction;

    public Cube(){
        mBlocks = new Block[3][3][3];
        for(int i = 0; i< mBlocks.length; i++){
            for(int j = 0; j< mBlocks[0].length; j++){
                for(int k = 0; k< mBlocks[0].length; k++){
                    mBlocks[i][j][k] = new Block();
                }
            }
        }

        mLastAction = new int[3]; //{action, mark|direction, value}
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
        boolean success = mBlocks[depth][row][column].mark(mark);

        if(success){
            mLastAction[0] = ACTION_MARK;
            mLastAction[1] = mark;
            mLastAction[2] = depth*100 + row*10 + column;
        }

        return success;

    }

    public int getMark(int column, int row){
        return getMark(column, row, 0);
    }

    private int getMark(int column, int row, int depth){
        return mBlocks[depth][row][column].getMark();
    }

    public boolean turn(int direction, int count){
        boolean success = false;

        if(!checkTurnAllow(direction, count)){
            return false;
        }

        switch (direction){
            case DIRECTION_LEFT:
            case DIRECTION_RIGHT:
                success = turnRow(direction, count);
                break;

            case DIRECTION_UP:
            case DIRECTION_DOWN:
                success = turnColumn(direction, count);
                break;

            case DIRECTION_CLOCKWISE:
            case DIRECTION_COUNTERCLOCKWISE:
                success = turnHorizontal(direction, count);
                break;
        }

        if(success){
            mLastAction[0] = ACTION_TURN;
            mLastAction[1] = direction;
            mLastAction[2] = count;
        }

        return success;
    }

    private boolean turnRow(int direction ,int row){
        //Front
        Block tmpBlock0 = mBlocks[0][row][0];
        Block tmpBlock1 = mBlocks[0][row][1];

        switch(direction){
            case DIRECTION_LEFT:
                //Front <- Right
                mBlocks[0][row][0] = mBlocks[0][row][2];
                mBlocks[0][row][1] = mBlocks[1][row][2];

                //Right <- Back
                mBlocks[0][row][2] = mBlocks[2][row][2];
                mBlocks[1][row][2] = mBlocks[2][row][1];

                //Back <- Left
                mBlocks[2][row][2] = mBlocks[2][row][0];
                mBlocks[2][row][1] = mBlocks[1][row][0];

                //Left <- Front(tmp)
                mBlocks[2][row][0] = tmpBlock0;
                mBlocks[1][row][0] = tmpBlock1;

                return true;

            case DIRECTION_RIGHT:
                //Front <- Left
                mBlocks[0][row][0] = mBlocks[2][row][0];
                mBlocks[0][row][1] = mBlocks[1][row][0];

                //Left <- Back
                mBlocks[2][row][0] = mBlocks[2][row][2];
                mBlocks[1][row][0] = mBlocks[2][row][1];

                //Back <- Right
                mBlocks[2][row][2] = mBlocks[0][row][2];
                mBlocks[2][row][1] = mBlocks[1][row][2];

                //Right <- Front(tmp)
                mBlocks[0][row][2] = tmpBlock0;
                mBlocks[1][row][2] = tmpBlock1;

                return true;

            default:
                return false;
        }

    }

    private boolean turnColumn(int direction, int column){
        //Front
        Block tmpBlock0 = mBlocks[0][0][column];
        Block tmpBlock1 = mBlocks[0][1][column];

        switch (direction){
            case DIRECTION_UP:
                //Front <- Down
                mBlocks[0][0][column] = mBlocks[0][2][column];
                mBlocks[0][1][column] = mBlocks[1][2][column];

                //Down <- Back
                mBlocks[0][2][column] = mBlocks[2][2][column];
                mBlocks[1][2][column] = mBlocks[2][1][column];

                //Back <- Up
                mBlocks[2][2][column] = mBlocks[2][0][column];
                mBlocks[2][1][column] = mBlocks[1][0][column];

                //Up <- Front(tmp)
                mBlocks[2][0][column] = tmpBlock0;
                mBlocks[1][0][column] = tmpBlock1;

                return true;

            case DIRECTION_DOWN:
                //Front <- Up
                mBlocks[0][0][column] = mBlocks[2][0][column];
                mBlocks[0][1][column] = mBlocks[1][0][column];

                //Up <- Back
                mBlocks[2][0][column] = mBlocks[2][2][column];
                mBlocks[1][0][column] = mBlocks[2][1][column];

                //Back <- Down
                mBlocks[2][2][column] = mBlocks[0][2][column];
                mBlocks[2][1][column] = mBlocks[1][2][column];

                //Down <- Front(tmp)
                mBlocks[0][2][column] = tmpBlock0;
                mBlocks[1][2][column] = tmpBlock1;

                return true;

            default:
                return false;
        }

    }

    private boolean turnHorizontal(int direction, int depth){
        //Up
        Block tmpBlock0 = mBlocks[depth][0][0];
        Block tmpBlock1 = mBlocks[depth][0][1];

        switch (direction){
            case DIRECTION_CLOCKWISE:
                //Up <- Left
                mBlocks[depth][0][0] = mBlocks[depth][2][0];
                mBlocks[depth][0][1] = mBlocks[depth][1][0];

                //Left <- Down
                mBlocks[depth][2][0] = mBlocks[depth][2][2];
                mBlocks[depth][1][0] = mBlocks[depth][2][1];

                //Down <- Right
                mBlocks[depth][2][2] = mBlocks[depth][0][2];
                mBlocks[depth][2][1] = mBlocks[depth][1][2];

                //Right <- Up(tmp)
                mBlocks[depth][0][2] = tmpBlock0;
                mBlocks[depth][1][2] = tmpBlock1;

                return true;

            case DIRECTION_COUNTERCLOCKWISE:
                //Up <- Right
                mBlocks[depth][0][0] = mBlocks[depth][0][2];
                mBlocks[depth][0][1] = mBlocks[depth][1][2];

                //Right <- Down
                mBlocks[depth][0][2] = mBlocks[depth][2][2];
                mBlocks[depth][1][2] = mBlocks[depth][2][1];

                //Down <- Left
                mBlocks[depth][2][2] = mBlocks[depth][2][0];
                mBlocks[depth][2][1] = mBlocks[depth][1][0];

                //Left <- Up(tmp)
                mBlocks[depth][2][0] = tmpBlock0;
                mBlocks[depth][1][0] = tmpBlock1;

                return true;

            default:
                return false;
        }

    }

    private boolean checkTurnAllow(int direction, int count){
        boolean result = true;
        if(mLastAction[0] == ACTION_TURN){
            switch (direction){
                case DIRECTION_LEFT:
                    if (mLastAction[1] == DIRECTION_RIGHT && mLastAction[2] == count){
                        result = false;
                    }
                    break;

                case DIRECTION_RIGHT:
                    if (mLastAction[1] == DIRECTION_LEFT && mLastAction[2] == count){
                        result = false;
                    }
                    break;

                case DIRECTION_UP:
                    if (mLastAction[1] == DIRECTION_DOWN && mLastAction[2] == count){
                        result = false;
                    }
                    break;

                case DIRECTION_DOWN:
                    if (mLastAction[1] == DIRECTION_UP && mLastAction[2] == count){
                        result = false;
                    }
                    break;

                case DIRECTION_CLOCKWISE:
                    if (mLastAction[1] == DIRECTION_COUNTERCLOCKWISE && mLastAction[2] == count){
                        result = false;
                    }
                    break;

                case DIRECTION_COUNTERCLOCKWISE:
                    if (mLastAction[1] == DIRECTION_CLOCKWISE && mLastAction[2] == count){
                        result = false;
                    }
                    break;
            }
        }
        return result;
    }

    public boolean checkFinish(){
        //Check Row
        row:for(int row = 0; row< mBlocks[0].length; row++){
            int tmpFirstMark = getMark(0, row);
            if(tmpFirstMark == Block.MARK_NONE){
                continue;
            }
            for (int column = 1; column< mBlocks[0][0].length; column++){
                int tmpMark = getMark(column,  row);
                if(tmpMark != tmpFirstMark){
                    continue row;
                }
            }
            return true;
        }

        //Check Column
        column:for(int column = 0; column< mBlocks[0][0].length; column++){
            int tmpFirstMark = getMark(column, 0);
            if(tmpFirstMark == Block.MARK_NONE){
                continue;
            }
            for (int row = 1; row< mBlocks[0].length; row++){
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
        for (int i = 1; i< mBlocks[0][0].length; i++){
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
        tmpFirstMark = getMark(0, mBlocks.length-1);
        if(tmpFirstMark == Block.MARK_NONE){
            checkFlg = false;
        }
        for (int i = 1; i< mBlocks[0][0].length; i++){
            int tmpMark = getMark(i, mBlocks.length-1-i);
            if(tmpMark != tmpFirstMark){
                checkFlg = false;
                break;
            }
        }
        return checkFlg;
    }

    public int getSize(){
        return mBlocks.length;
    }

}
