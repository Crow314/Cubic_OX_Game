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
    private Button mTurnButtonLeftU;
    private Button mTurnButtonLeftC;
    private Button mTurnButtonLeftD;
    private Button mTurnButtonRightU;
    private Button mTurnButtonRightC;
    private Button mTurnButtonRightD;
    private Button mTurnButtonUpL;
    private Button mTurnButtonUpC;
    private Button mTurnButtonUpR;
    private Button mTurnButtonDownL;
    private Button mTurnButtonDownC;
    private Button mTurnButtonDownR;
    private Button mTurnButtonCounterclockwiseU;
    private Button mTurnButtonClockwiseU;
    private Button mTurnButtonClockwiseD;
    private Button mTurnButtonCounterclockwiseD;


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

        mBlockButtonList[0][0] = findViewById(R.id.button_LU);
        mBlockButtonList[0][1] = findViewById(R.id.button_CU);
        mBlockButtonList[0][2] = findViewById(R.id.button_RU);
        mBlockButtonList[1][0] = findViewById(R.id.button_LC);
        mBlockButtonList[1][1] = findViewById(R.id.button_CC);
        mBlockButtonList[1][2] = findViewById(R.id.button_RC);
        mBlockButtonList[2][0] = findViewById(R.id.button_LD);
        mBlockButtonList[2][1] = findViewById(R.id.button_CD);
        mBlockButtonList[2][2] = findViewById(R.id.button_RD);

        mTurnButtonLeftU = findViewById(R.id.button_turn_Left_U);
        mTurnButtonLeftC = findViewById(R.id.button_turn_Left_C);
        mTurnButtonLeftD = findViewById(R.id.button_turn_Left_D);

        mTurnButtonRightU = findViewById(R.id.button_turn_Right_U);
        mTurnButtonRightC = findViewById(R.id.button_turn_Right_C);
        mTurnButtonRightD = findViewById(R.id.button_turn_Right_D);

        mTurnButtonUpL = findViewById(R.id.button_turn_Up_L);
        mTurnButtonUpC = findViewById(R.id.button_turn_Up_C);
        mTurnButtonUpR = findViewById(R.id.button_turn_Up_R);

        mTurnButtonDownL = findViewById(R.id.button_turn_Down_L);
        mTurnButtonDownC = findViewById(R.id.button_turn_Down_C);
        mTurnButtonDownR = findViewById(R.id.button_turn_Down_R);

        mTurnButtonCounterclockwiseU = findViewById(R.id.button_turn_counterclockwise_upside);
        mTurnButtonClockwiseU = findViewById(R.id.button_turn_clockwise_upside);
        mTurnButtonClockwiseD = findViewById(R.id.button_turn_clockwise_downside);
        mTurnButtonCounterclockwiseD = findViewById(R.id.button_turn_counterclockwise_downside);

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
        mTurnButtonLeftU.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                turn(Cube.DIRECTION_LEFT, 0);
            }
        });

        mTurnButtonLeftC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                turn(Cube.DIRECTION_LEFT, 1);
            }
        });

        mTurnButtonLeftD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                turn(Cube.DIRECTION_LEFT, 2);
            }
        });

        //TurnRight
        mTurnButtonRightU.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                turn(Cube.DIRECTION_RIGHT, 0);
            }
        });

        mTurnButtonRightC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                turn(Cube.DIRECTION_RIGHT, 1);
            }
        });

        mTurnButtonRightD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                turn(Cube.DIRECTION_RIGHT, 2);
            }
        });

        //TurnUp
        mTurnButtonUpL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                turn(Cube.DIRECTION_UP, 0);
            }
        });

        mTurnButtonUpC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                turn(Cube.DIRECTION_UP, 1);
            }
        });

        mTurnButtonUpR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                turn(Cube.DIRECTION_UP, 2);
            }
        });

        //TurnDown
        mTurnButtonDownL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                turn(Cube.DIRECTION_DOWN, 0);
            }
        });

        mTurnButtonDownC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                turn(Cube.DIRECTION_DOWN, 1);
            }
        });

        mTurnButtonDownR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                turn(Cube.DIRECTION_DOWN, 2);
            }
        });

        //TurnHorizontal
        mTurnButtonCounterclockwiseU.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                turn(Cube.DIRECTION_COUNTERCLOCKWISE, 0);
            }
        });

        mTurnButtonClockwiseU.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                turn(Cube.DIRECTION_CLOCKWISE, 0);
            }
        });

        mTurnButtonClockwiseD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                turn(Cube.DIRECTION_CLOCKWISE, 0);
            }
        });

        mTurnButtonCounterclockwiseD.setOnClickListener(new View.OnClickListener() {
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

        for(int row=0; row<mCube.getSize(); row++){
            for(int column=0; column<mCube.getSize(); column++){
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
    }
    private void finishGame(int result){
        mResetButtonUpside.setEnabled(true);
        mResetButtonUpside.setVisibility(View.VISIBLE);
        mResetButtonDownside.setEnabled(true);
        mResetButtonDownside.setVisibility(View.VISIBLE);

        mTurnButtonLeftU.setEnabled(false);
        mTurnButtonLeftC.setEnabled(false);
        mTurnButtonLeftD.setEnabled(false);
        mTurnButtonRightU.setEnabled(false);
        mTurnButtonRightC.setEnabled(false);
        mTurnButtonRightD.setEnabled(false);
        mTurnButtonUpL.setEnabled(false);
        mTurnButtonUpC.setEnabled(false);
        mTurnButtonUpR.setEnabled(false);
        mTurnButtonDownL.setEnabled(false);
        mTurnButtonDownC.setEnabled(false);
        mTurnButtonDownR.setEnabled(false);
        mTurnButtonCounterclockwiseU.setEnabled(false);
        mTurnButtonClockwiseU.setEnabled(false);
        mTurnButtonClockwiseD.setEnabled(false);
        mTurnButtonCounterclockwiseD.setEnabled(false);

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
