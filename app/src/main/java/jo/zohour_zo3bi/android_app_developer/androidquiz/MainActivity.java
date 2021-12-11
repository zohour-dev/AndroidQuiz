package jo.zohour_zo3bi.android_app_developer.androidquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // Array contains the correct answers for the radio button questions
    private final int[] CORRECT_ANSWER_ID = new int[]{R.id.question1_choice1_radio_button, R.id.question2_choice3_radio_button,
            R.id.question3_choice2_radio_button, R.id.question5_choice3_radio_button};

    //Questions scores
    private boolean scoreQuestion1;
    private boolean scoreQuestion2;
    private boolean scoreQuestion3;
    private boolean scoreQuestion4;
    private boolean scoreQuestion5;
    private boolean scoreQuestion6;

    //Array to store the questions scores
    private boolean[] scoreQuestions = new boolean[6];

    // Question 4 answers
    private boolean answer1IsChecked = false;
    private boolean answer2IsChecked = false;
    private boolean answer3IsChecked = false;

    private int correctQuestions = 0;
    private int incorrectQuestions = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }//end onCreate()

    /**
     * Check the answer of question one -which is of radiobutton type- if it is correct or not!
     */
    public boolean questionOneScore() {
        RadioGroup questionOneRadioGroup = findViewById(R.id.question1_radio_group);
        if (questionOneRadioGroup.getCheckedRadioButtonId() == CORRECT_ANSWER_ID[0]) {
            scoreQuestion1 = true;
        } else {
            scoreQuestion1 = false;
        }//end if-else
        return scoreQuestion1;
    }//end questionOneScore()

    /**
     * Check the answer of question two -which is of radiobutton type- if it is correct or not!
     */
    public boolean questionTwoScore() {
        RadioGroup questionOneRadioGroup = findViewById(R.id.question2_radio_group);
        if (questionOneRadioGroup.getCheckedRadioButtonId() == CORRECT_ANSWER_ID[1]) {
            scoreQuestion2 = true;
        } else {
            scoreQuestion2 = false;
        }//end if-else
        return scoreQuestion2;
    }//end questionTwoScore()

    /**
     * Check the answer of question three -which is of radiobutton type- if it is correct or not!
     */
    public boolean questionThreeScore() {
        RadioGroup questionOneRadioGroup = findViewById(R.id.question3_radio_group);
        if (questionOneRadioGroup.getCheckedRadioButtonId() == CORRECT_ANSWER_ID[2]) {
            scoreQuestion3 = true;
        } else {
            scoreQuestion3 = false;
        }//end if-else
        return scoreQuestion3;
    }//end questionThreeScore()

    /**
     * Check the answer of question four -which is of checkbox type- if it is correct or not!
     */
    public void onClickQuestionFour(View view) {

        switch (view.getId()) {
            case R.id.question4_choice1_checkbox:
                CheckBox answer1CheckBox = findViewById(R.id.question4_choice1_checkbox);
                answer1IsChecked = answer1CheckBox.isChecked();
                break;
            case R.id.question4_choice2_checkbox:
                CheckBox answer2CheckBox = findViewById(R.id.question4_choice2_checkbox);
                answer2IsChecked = answer2CheckBox.isChecked();
                break;
            case R.id.question4_choice3_checkbox:
                CheckBox answer3CheckBox = findViewById(R.id.question4_choice3_checkbox);
                answer3IsChecked = answer3CheckBox.isChecked();
                break;
        }
        if (!answer1IsChecked && answer2IsChecked && answer3IsChecked) {
            scoreQuestion4 = true;
        } else {
            scoreQuestion4 = false;
        }//end if-else
        scoreQuestions[3] = scoreQuestion4;
    }//end onClickQuestionFour()

    /**
     * Check the answer of question five -which is of radiobutton type- if it is correct or not!
     */
    public boolean questionFiveScore() {
        RadioGroup questionOneRadioGroup = findViewById(R.id.question5_radio_group);
        if (questionOneRadioGroup.getCheckedRadioButtonId() == CORRECT_ANSWER_ID[3]) {
            scoreQuestion5 = true;
        } else {
            scoreQuestion5 = false;
        }//end if-else
        return scoreQuestion5;
    }//end questionFiveScore()

    /**
     * Check the answer of question six if it is correct or not!
     */
    public boolean questionSixScore() {
        EditText questionSixAnswerEditText = findViewById(R.id.question6_answer_edit_text);
        if (questionSixAnswerEditText.getText().toString().matches("2008")) {
            scoreQuestion6 = true;
        } else {
            scoreQuestion6 = false;
        }//end if-else
        return scoreQuestion6;
    }//end questionSixScore()

    /**
     * Submit the question answers and view the quiz result as a toast message!
     */
    public void submit(View view) {
        EditText name_class_edit_text = findViewById(R.id.name_class_field);
        // Check if the name and class are entered in the edit text
        if (name_class_edit_text.getText().toString().matches("")) {
            Toast.makeText(this, R.string.enter_name_class_alert_toast_message, Toast.LENGTH_LONG).show();
            return;
        }//end if

        // Store the final score for each question in the array of questions scores
        scoreQuestions[0] = questionOneScore();
        scoreQuestions[1] = questionTwoScore();
        scoreQuestions[2] = questionThreeScore();
        scoreQuestions[4] = questionFiveScore();
        scoreQuestions[5] = questionSixScore();

        for (int i = 0; i < scoreQuestions.length; i++) {
            if (scoreQuestions[i] == true) {
                correctQuestions++;
            } else {
                incorrectQuestions++;
            }//end if-else
        }//end for

        int totalNumberOfQuestions = correctQuestions + incorrectQuestions;
        Toast.makeText(this, getString(R.string.name_class_toast_message) +
                name_class_edit_text.getText().toString() +
                getString(R.string.quiz_result_toast_message) + correctQuestions +
                getString(R.string.forward_slash) + totalNumberOfQuestions, Toast.LENGTH_LONG).show();

        showCorrectAnswers();

        //Reset the quiz result
        correctQuestions = 0;
        incorrectQuestions = 0;
    }//end submit()

    /**
     * Show the correct answers of the questions by changing it's color to green!
     */
    public void showCorrectAnswers() {
        // Change the color of the right answer to green
        // Radio Button questions
        for (int i = 0; i < CORRECT_ANSWER_ID.length; i++) {
            RadioButton questionCorrectAnswer = findViewById(CORRECT_ANSWER_ID[i]);
            questionCorrectAnswer.setTextColor(Color.GREEN);
        }//end for

        // Checkbox question
        CheckBox answer2Checkbox = findViewById(R.id.question4_choice2_checkbox);
        answer2Checkbox.setTextColor(Color.GREEN);
        CheckBox answer3Checkbox = findViewById(R.id.question4_choice3_checkbox);
        answer3Checkbox.setTextColor(Color.GREEN);

        // Edit Text question
        EditText questionSixEditText = findViewById(R.id.question6_answer_edit_text);
        questionSixEditText.setText("2008");
        questionSixEditText.setTextColor(Color.GREEN);
    }//end showCorrectAnswers()
}//end MainActivity
