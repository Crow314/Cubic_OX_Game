package net.crow31415.cubic_ox_game;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

import net.crow31415.cubic_ox_game.game.Block;
import net.crow31415.cubic_ox_game.game.Cube;

public class MainActivity extends AppCompatActivity {

    private TextView mTextTurnUpside;
    private TextView mTextTurnDownside;
    private Button[][] mBlockList;
    private Cube mCube;
    private int mTurn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextTurnUpside = findViewById(R.id.text_turn_upside);
        mTextTurnDownside = findViewById(R.id.text_turn_downside);

        mBlockList = new Button[3][3];

        mBlockList[0][0] = findViewById(R.id.button_LU);
        mBlockList[0][1] = findViewById(R.id.button_CU);
        mBlockList[0][2] = findViewById(R.id.button_RU);
        mBlockList[1][0] = findViewById(R.id.button_LC);
        mBlockList[1][1] = findViewById(R.id.button_CC);
        mBlockList[1][2] = findViewById(R.id.button_RC);
        mBlockList[2][0] = findViewById(R.id.button_LD);
        mBlockList[2][1] = findViewById(R.id.button_CD);
        mBlockList[2][2] = findViewById(R.id.button_RD);

        Button turnButtonLeftU = findViewById(R.id.button_turn_Left_U);
        Button turnButtonLeftC = findViewById(R.id.button_turn_Left_C);
        Button turnButtonLeftD = findViewById(R.id.button_turn_Left_D);

        Button turnButtonRightU = findViewById(R.id.button_turn_Right_U);
        Button turnButtonRightC = findViewById(R.id.button_turn_Right_C);
        Button turnButtonRightD = findViewById(R.id.button_turn_Right_D);

        Button turnButtonUpL = findViewById(R.id.button_turn_Up_L);
        Button turnButtonUpC = findViewById(R.id.button_turn_Up_C);
        Button turnButtonUpR = findViewById(R.id.button_turn_Up_R);

        Button turnButtonDownL = findViewById(R.id.button_turn_Down_L);
        Button turnButtonDownC = findViewById(R.id.button_turn_Down_C);
        Button turnButtonDownR = findViewById(R.id.button_turn_Down_R);

        //ボタンイベントリスナー設定
        //Mark
        mBlockList[0][0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mark(0, 0);
            }
        });

        mBlockList[0][1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mark(1, 0);
            }
        });

        mBlockList[0][2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mark(2, 0);
            }
        });

        mBlockList[1][0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mark(0, 1);
            }
        });

        mBlockList[1][1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mark(1, 1);
            }
        });

        mBlockList[1][2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mark(2, 1);
            }
        });

        mBlockList[2][0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mark(0, 2);
            }
        });

        mBlockList[2][1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mark(1, 2);
            }
        });

        mBlockList[2][2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mark(2, 2);
            }
        });

        //TurnLeft
        turnButtonLeftU.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                turn(Cube.DIRECTION_LEFT, 0);
            }
        });

        turnButtonLeftC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                turn(Cube.DIRECTION_LEFT, 1);
            }
        });

        turnButtonLeftD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                turn(Cube.DIRECTION_LEFT, 2);
            }
        });

        //TurnRight
        turnButtonRightU.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                turn(Cube.DIRECTION_RIGHT, 0);
            }
        });

        turnButtonRightC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                turn(Cube.DIRECTION_RIGHT, 1);
            }
        });

        turnButtonRightD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                turn(Cube.DIRECTION_RIGHT, 2);
            }
        });

        //TurnUp
        turnButtonUpL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                turn(Cube.DIRECTION_UP, 0);
            }
        });

        turnButtonUpC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                turn(Cube.DIRECTION_UP, 1);
            }
        });

        turnButtonUpR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                turn(Cube.DIRECTION_UP, 2);
            }
        });

        //TurnDown
        turnButtonDownL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                turn(Cube.DIRECTION_DOWN, 0);
            }
        });

        turnButtonDownC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                turn(Cube.DIRECTION_DOWN, 1);
            }
        });

        turnButtonDownR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                turn(Cube.DIRECTION_DOWN, 2);
            }
        });


        //初期化処理
        init();

    }

    private void init(){
        mCube = new Cube();
        mCube.randomMark();
        mTurn = Block.MARK_CIRCLE;
        reload();
    }

    private void reload(){
        switch(mTurn){
            case Block.MARK_CIRCLE:
                mTextTurnUpside.setText(R.string.turn_circle);
                mTextTurnDownside.setText(R.string.turn_circle);
                break;

            case Block.MARK_CROSS:
                mTextTurnUpside.setText(R.string.turn_cross);
                mTextTurnDownside.setText(R.string.turn_cross);
                break;
        }

        for(int row=0; row<mCube.getSize(); row++){
            for(int column=0; column<mCube.getSize(); column++){
                Button button = mBlockList[row][column];
                switch(mCube.getMark(column, row)){
                    case Block.MARK_NONE:
                        button.setBackgroundResource(android.R.drawable.btn_default);
                        button.setText("");
                        break;

                    case Block.MARK_CIRCLE:
                        button.setBackgroundColor(Color.parseColor("#FF4444"));
                        button.setText(R.string.mark_circle);
                        break;

                    case Block.MARK_CROSS:
                        button.setBackgroundColor(Color.parseColor("#4444FF"));
                        button.setText(R.string.mark_cross);
                        break;
                }
            }
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
}
