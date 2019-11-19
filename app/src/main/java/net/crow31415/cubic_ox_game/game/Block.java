package net.crow31415.cubic_ox_game.game;

public class Block {

    public static final int MARK_NONE = 0;
    public static final int MARK_CIRCLE = 1;
    public static final int MARK_CROSS = 2;

    private int mark;

    public Block(){
        this.mark = MARK_NONE;
    }

    public boolean mark(int mark){
        if(this.mark != MARK_NONE){
            return false;
        }

        if(mark != MARK_CIRCLE && mark != MARK_CROSS){
            return false;
        }

        this.mark = mark;
        return true;
    }

    public int getMark() {
        return mark;
    }
}
