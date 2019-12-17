package net.crow31415.cubic_ox_game.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import net.crow31415.cubic_ox_game.R;
import net.crow31415.cubic_ox_game.game.Block;
import net.crow31415.cubic_ox_game.game.Cube;

public class LocalGameActivity extends AppCompatActivity {

    private Cube mCube;
    private int mTurn;
    private int mCircleColor;
    private int mCrossColor;
    private TextView mTextTurnUpside;
    private TextView mTextTurnDownside;
    private Button[][] mBlockButtonList;
    private Button mResetButtonUpside;
    private Button mResetButtonDownside;
    private Button[][] mTurnButtonList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_game);

        mCircleColor = Color.parseColor("#F08080");
        mCrossColor = Color.parseColor("#20B2AA");

        mTextTurnUpside = findViewById(R.id.text_turn_upside);
        mTextTurnDownside = findViewById(R.id.text_turn_downside);

        mBlockButtonList = new Button[3][3];

        mResetButtonUpside = findViewById(R.id.button_reset_upside);
        mResetButtonDownside = findViewById(R.id.button_reset_downside);

        mTurnButtonList = new Button[6][3];

        mBlockButtonList[0][0] = findViewById(R.id.button_LU);
        mBlockButtonList[0][1] = findViewById(R.id.button_CU);
        mBlockButtonList[0][2] = findViewById(R.id.button_RU);
        mBlockButtonList[1][0] = findViewById(R.id.button_LC);
        mBlockButtonList[1][1] = findViewById(R.id.button_CC);
        mBlockButtonList[1][2] = findViewById(R.id.button_RC);
        mBlockButtonList[2][0] = findViewById(R.id.button_LD);
        mBlockButtonList[2][1] = findViewById(R.id.button_CD);
        mBlockButtonList[2][2] = findViewById(R.id.button_RD);

        mTurnButtonList[Cube.DIRECTION_LEFT][0] = findViewById(R.id.button_turn_Left_U);
        mTurnButtonList[Cube.DIRECTION_LEFT][1] = findViewById(R.id.button_turn_Left_C);
        mTurnButtonList[Cube.DIRECTION_LEFT][2] = findViewById(R.id.button_turn_Left_D);

        mTurnButtonList[Cube.DIRECTION_RIGHT][0] = findViewById(R.id.button_turn_Right_U);
        mTurnButtonList[Cube.DIRECTION_RIGHT][1] = findViewById(R.id.button_turn_Right_C);
        mTurnButtonList[Cube.DIRECTION_RIGHT][2] = findViewById(R.id.button_turn_Right_D);

        mTurnButtonList[Cube.DIRECTION_UP][0] = findViewById(R.id.button_turn_Up_L);
        mTurnButtonList[Cube.DIRECTION_UP][1] = findViewById(R.id.button_turn_Up_C);
        mTurnButtonList[Cube.DIRECTION_UP][2] = findViewById(R.id.button_turn_Up_R);

        mTurnButtonList[Cube.DIRECTION_DOWN][0] = findViewById(R.id.button_turn_Down_L);
        mTurnButtonList[Cube.DIRECTION_DOWN][1] = findViewById(R.id.button_turn_Down_C);
        mTurnButtonList[Cube.DIRECTION_DOWN][2] = findViewById(R.id.button_turn_Down_R);

        mTurnButtonList[Cube.DIRECTION_CLOCKWISE][0] = findViewById(R.id.button_turn_clockwise_upside);
        mTurnButtonList[Cube.DIRECTION_CLOCKWISE][1] = findViewById(R.id.button_turn_clockwise_downside);
        mTurnButtonList[Cube.DIRECTION_COUNTERCLOCKWISE][0] = findViewById(R.id.button_turn_counterclockwise_upside);
        mTurnButtonList[Cube.DIRECTION_COUNTERCLOCKWISE][1] = findViewById(R.id.button_turn_counterclockwise_downside);

        //ボタンイベントリスナー設定
        //Reset
        mResetButtonUpside.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                init();
            }
        });

        mResetButtonDownside.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                init();
            }
        });

        //Mark
        mBlockButtonList[0][0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mark(0, 0);
            }
        });

        mBlockButtonList[0][1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mark(1, 0);
            }
        });

        mBlockButtonList[0][2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mark(2, 0);
            }
        });

        mBlockButtonList[1][0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mark(0, 1);
            }
        });

        mBlockButtonList[1][1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mark(1, 1);
            }
        });

        mBlockButtonList[1][2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mark(2, 1);
            }
        });

        mBlockButtonList[2][0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mark(0, 2);
            }
        });

        mBlockButtonList[2][1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mark(1, 2);
            }
        });

        mBlockButtonList[2][2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mark(2, 2);
            }
        });

        //TurnLeft
        mTurnButtonList[Cube.DIRECTION_LEFT][0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                turn(Cube.DIRECTION_LEFT, 0);
            }
        });

        mTurnButtonList[Cube.DIRECTION_LEFT][1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                turn(Cube.DIRECTION_LEFT, 1);
            }
        });

        mTurnButtonList[Cube.DIRECTION_LEFT][2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                turn(Cube.DIRECTION_LEFT, 2);
            }
        });

        //TurnRight
        mTurnButtonList[Cube.DIRECTION_RIGHT][0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                turn(Cube.DIRECTION_RIGHT, 0);
            }
        });

        mTurnButtonList[Cube.DIRECTION_RIGHT][1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                turn(Cube.DIRECTION_RIGHT, 1);
            }
        });

        mTurnButtonList[Cube.DIRECTION_RIGHT][2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                turn(Cube.DIRECTION_RIGHT, 2);
            }
        });

        //TurnUp
        mTurnButtonList[Cube.DIRECTION_UP][0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                turn(Cube.DIRECTION_UP, 0);
            }
        });

        mTurnButtonList[Cube.DIRECTION_UP][1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                turn(Cube.DIRECTION_UP, 1);
            }
        });

        mTurnButtonList[Cube.DIRECTION_UP][2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                turn(Cube.DIRECTION_UP, 2);
            }
        });

        //TurnDown
        mTurnButtonList[Cube.DIRECTION_DOWN][0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                turn(Cube.DIRECTION_DOWN, 0);
            }
        });

        mTurnButtonList[Cube.DIRECTION_DOWN][1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                turn(Cube.DIRECTION_DOWN, 1);
            }
        });

        mTurnButtonList[Cube.DIRECTION_DOWN][2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                turn(Cube.DIRECTION_DOWN, 2);
            }
        });

        //TurnHorizontal
        mTurnButtonList[Cube.DIRECTION_CLOCKWISE][0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                turn(Cube.DIRECTION_CLOCKWISE, 0);
            }
        });

        mTurnButtonList[Cube.DIRECTION_CLOCKWISE][1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                turn(Cube.DIRECTION_CLOCKWISE, 0);
            }
        });

        mTurnButtonList[Cube.DIRECTION_COUNTERCLOCKWISE][0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                turn(Cube.DIRECTION_COUNTERCLOCKWISE, 0);
            }
        });

        mTurnButtonList[Cube.DIRECTION_COUNTERCLOCKWISE][1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                turn(Cube.DIRECTION_COUNTERCLOCKWISE, 0);
            }
        });


        //初期化処理
        init();

    }

    private void init(){
        mCube = new Cube();
        mCube.randomMark();
        mTurn = Block.MARK_CIRCLE;
        mResetButtonUpside.setEnabled(false);
        mResetButtonUpside.setVisibility(View.INVISIBLE);
        mResetButtonDownside.setEnabled(false);
        mResetButtonDownside.setVisibility(View.INVISIBLE);

        for (Button[] buttons : mTurnButtonList) {
            for (Button button : buttons) {
                if (button != null) {
                    button.setEnabled(true);
                }
            }
        }

        for(int row=0; row<mCube.getSize(); row++){
            for(int column=0; column<mCube.getSize(); column++){
                Button button = mBlockButtonList[row][column];
                button.setEnabled(true);
            }
        }
        
        reload();
    }

    private void reload(){
        switch(mTurn){
            case Block.MARK_CIRCLE:
                mTextTurnUpside.setText(R.string.turn_circle);
                mTextTurnDownside.setText(R.string.turn_circle);
                mTextTurnUpside.setTextColor(mCircleColor);
                mTextTurnDownside.setTextColor(mCircleColor);
                break;

            case Block.MARK_CROSS:
                mTextTurnUpside.setText(R.string.turn_cross);
                mTextTurnDownside.setText(R.string.turn_cross);
                mTextTurnUpside.setTextColor(mCrossColor);
                mTextTurnDownside.setTextColor(mCrossColor);
                break;
        }

        for(int row=0; row<mBlockButtonList.length; row++){
            for(int column=0; column<mBlockButtonList[row].length; column++){
                Button button = mBlockButtonList[row][column];
                switch(mCube.getMark(column, row)){
                    case Block.MARK_NONE:
                        button.setBackgroundResource(android.R.drawable.btn_default);
                        button.setText("");
                        break;

                    case Block.MARK_CIRCLE:
                        button.setBackgroundColor(mCircleColor);
                        button.setText(R.string.mark_circle);
                        break;

                    case Block.MARK_CROSS:
                        button.setBackgroundColor(mCrossColor);
                        button.setText(R.string.mark_cross);
                        break;
                }
            }
        }

        int result = mCube.getResult();
        if(result != -1){
            finishGame(result);
        }
    }

    private void mark(int column, int row){
        boolean success = mCube.mark(mTurn, column, row);
        if(success){
            turnChange();
        }
        reload();
    }

    private void turn(int direction, int count){
        boolean success = mCube.turn(direction, count);
        if(success){
            turnChange();
            switch (direction){
                case Cube.DIRECTION_LEFT:
                    mTurnButtonList[Cube.DIRECTION_RIGHT][count].setEnabled(false);
                    break;

                case Cube.DIRECTION_RIGHT:
                    mTurnButtonList[Cube.DIRECTION_LEFT][count].setEnabled(false);
                    break;

                case Cube.DIRECTION_UP:
                    mTurnButtonList[Cube.DIRECTION_DOWN][count].setEnabled(false);
                    break;

                case Cube.DIRECTION_DOWN:
                    mTurnButtonList[Cube.DIRECTION_UP][count].setEnabled(false);
                    break;

                case Cube.DIRECTION_CLOCKWISE:
                    mTurnButtonList[Cube.DIRECTION_COUNTERCLOCKWISE][0].setEnabled(false);
                    mTurnButtonList[Cube.DIRECTION_COUNTERCLOCKWISE][1].setEnabled(false);
                    break;

                case Cube.DIRECTION_COUNTERCLOCKWISE:
                    mTurnButtonList[Cube.DIRECTION_CLOCKWISE][0].setEnabled(false);
                    mTurnButtonList[Cube.DIRECTION_CLOCKWISE][1].setEnabled(false);
                    break;
            }
        }
        reload();
    }

    private void turnChange(){
        switch(mTurn){
            case Block.MARK_CIRCLE:
                mTurn = Block.MARK_CROSS;
                break;

            case Block.MARK_CROSS:
                mTurn = Block.MARK_CIRCLE;
                break;
        }

        for (Button[] buttons : mTurnButtonList) {
            for (Button button : buttons) {
                if (button != null) {
                    button.setEnabled(true);
                }
            }
        }
    }
    private void finishGame(int result){
        mResetButtonUpside.setEnabled(true);
        mResetButtonUpside.setVisibility(View.VISIBLE);
        mResetButtonDownside.setEnabled(true);
        mResetButtonDownside.setVisibility(View.VISIBLE);

        for (Button[] buttons : mTurnButtonList) {
            for (Button button : buttons) {
                if (button != null) {
                    button.setEnabled(false);
                }
            }
        }

        for (Button[] buttons : mBlockButtonList) {
            for (Button button : buttons) {
                button.setEnabled(false);
            }
        }

        switch(result){
            case Block.MARK_CIRCLE:
                mTextTurnUpside.setText(R.string.result_circle);
                mTextTurnDownside.setText(R.string.result_circle);
                mTextTurnUpside.setTextColor(mCircleColor);
                mTextTurnDownside.setTextColor(mCircleColor);
                break;

            case Block.MARK_CROSS:
                mTextTurnUpside.setText(R.string.result_cross);
                mTextTurnDownside.setText(R.string.result_cross);
                mTextTurnUpside.setTextColor(mCrossColor);
                mTextTurnDownside.setTextColor(mCrossColor);
                break;
        }
    }
}
