package com.sysc.testapp1;

//import android.R;
import java.lang.reflect.Method;

import snippet.VoterClient;
import android.os.Bundle;
import android.os.StrictMode;
import android.R.color;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {
	VoterClient client = new VoterClient(6050,"128.70.50.1");
	boolean DEVELOPER_MODE = true;
	
//	try {
//        Class strictModeClass=Class.forName("android.os.StrictMode");
//        Class strictModeThreadPolicyClass=Class.forName("android.os.StrictMode$ThreadPolicy");
//        Object laxPolicy = strictModeThreadPolicyClass.getField("LAX").get(null);
//        Method method_setThreadPolicy = strictModeClass.getMethod(
//                "setThreadPolicy", strictModeThreadPolicyClass );
//        method_setThreadPolicy.invoke(null,laxPolicy);
//    } catch (Exception e) {
//
//    }
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		if (DEVELOPER_MODE) {
			StrictMode.enableDefaults();
	     }

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		final Button button1 = (Button)findViewById(R.id.button1);
		final EditText pollID  = (EditText)findViewById(R.id.editText1);
		final EditText choice   = (EditText)findViewById(R.id.editText2);
		
		
		pollID.setOnFocusChangeListener(new View.OnFocusChangeListener() {
			
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				pollID.setBackgroundColor(color.white);				
			}
		});
		
		choice.setOnFocusChangeListener(new View.OnFocusChangeListener() {
			
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				choice.setBackgroundColor(color.white);				
			}
		});
		
		button1.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {	
				
				if(submitVote(pollID,choice)){
					//update the text view
					setVoteConfirmationText("Vote Sent!");
				}				
			}
		});	
	}
	
	/**
	 * validate and send vote to server
	 * @param pollIDTextField
	 * @param choiceTextField
	 * @return true if the vote was sent successfully or false otherwise
	 */
	public boolean submitVote(EditText pollIDTextField, EditText choiceTextField){
		boolean validation = true;
		long pollID=0;
		int choice=0;
		
		
		if(pollIDTextField.getText().toString().equals("")){
			pollIDTextField.setText("invalid");
			validation = false;
		}else{
			try{
				pollID = Long.parseLong(pollIDTextField.getText().toString());
			}catch(NumberFormatException nfe){
				validation = false;
			}
		}
		
		if(choiceTextField.getText().toString().equals("")){
			choiceTextField.setText("invalid");
			validation = false;
		}else{
			try{
				choice = Integer.parseInt(choiceTextField.getText().toString());
			}catch(NumberFormatException nfe){
				validation = false;
			}
		}
		
		if(validation){
			//send the vote
			client.vote(pollID,choice);
			return true;
		}
//		String str = choiceTextField.getText().toString();
//		Log.v("EditText choice", choiceTextField.getText().toString());
		return false;		
	}
	
	public void setVoteConfirmationText(String text){
		TextView textView = (TextView) findViewById(R.id.voteConfirmation); 
		textView.setText(text);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
