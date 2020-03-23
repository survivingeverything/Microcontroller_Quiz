package com.BVM.quiz;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;
import com.BVM.quiz.QuizContract.*;

public class QuizDbHelper extends SQLiteOpenHelper {
    private static  final String DATABASE_NAME = "MyDatabase.db";
    private static  final int DATABASE_VERSION = 1;

    private SQLiteDatabase db;
    public QuizDbHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;
        final String SQL_CEATE_QUESTION_TABLE = "CREATE TABLE " +
                QuestionTable.TABLE_NAME + "( "  +
                QuestionTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "  +
                QuestionTable.COULMN_QUESTION + "  TEXT, " +
                QuestionTable.COLUMN_OPTION1 + "  TEXT, " +
                QuestionTable.COLUMN_OPTION2 + "  TEXT, " +
                QuestionTable.COLUMN_OPTION3 + "  TEXT, " +
                QuestionTable.COLUMN_ANSWER_NR + "  INTEGER, "  +
                ")";

        db.execSQL(SQL_CEATE_QUESTION_TABLE);




    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS" + QuestionTable.TABLE_NAME);
        onCreate(db);


    }
    private void fillQuestionsTable() {
        Question q1 = new Question("A is correct", "A", "B", "C", 1);
        addQuestion(q1);
        Question q2 = new Question("B is correct", "A", "B", "C", 2);
        addQuestion(q2);
        Question q3 = new Question("C is correct", "A", "B", "C", 3);
        addQuestion(q3);
        Question q4 = new Question("A is correct again", "A", "B", "C", 1);
        addQuestion(q4);
        Question q5 = new Question("B is correct again", "A", "B", "C", 2);
        addQuestion(q5);
    }

    private void addQuestion(Question question) {
        ContentValues cv = new ContentValues();
        cv.put(QuizContract.QuestionsTable.COLUMN_QUESTION, question.getQuestion());
        cv.put(QuizContract.QuestionsTable.COLUMN_OPTION1, question.getOption1());
        cv.put(QuizContract.QuestionsTable.COLUMN_OPTION2, question.getOption2());
        cv.put(QuizContract.QuestionsTable.COLUMN_OPTION3, question.getOption3());
        cv.put(QuizContract.QuestionsTable.COLUMN_ANSWER_NR, question.getAnswerNr());
        db.insert(QuizContract.QuestionsTable.TABLE_NAME, null, cv);
    }
}
