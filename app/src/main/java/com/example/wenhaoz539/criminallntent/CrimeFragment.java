package com.example.wenhaoz539.criminallntent;



import java.util.Date;
import java.util.UUID;

import android.app.Activity;

import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;

import android.widget.EditText;

public class CrimeFragment extends Fragment {
	public static final String EXTRA_CRIME_ID = "com.bignerdranch.android.criminalintent.crime_id";
	private Crime mCrime;
	private EditText mTitleField;
	private Button mDateButton;
	private Button mTimeButton;
	private CheckBox mSolvedCheckBox;
	private static final String DIALOG_DATE="date";
	private static final String DIALOG_TIME="time";
	private static final int REQUEST_DATE =0 ;
	private static final int REQUEST_TIME =1 ;

	
	@Override 
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		//UUID crimeId=(UUID)getActivity().getIntent().getSerializableExtra(EXTRA_CRIME_ID);
		UUID crimeId = (UUID)getArguments().getSerializable(EXTRA_CRIME_ID);
		mCrime = CrimeLab.get(getActivity()).getCrime(crimeId);
	}

	private void updateDate(){
		mDateButton.setText(DateFormat.format("EEEE, MMM dd, yyyy", mCrime.getDate()).toString());
	}

	private void updateTime(){
		mTimeButton.setText(DateFormat.format("HH:mm", mCrime.getDate()).toString());
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data){
		if(resultCode!= Activity.RESULT_OK) return;
		if(requestCode==REQUEST_DATE ){
			Date date=(Date)data.getSerializableExtra(DatePickerFragment.EXTRA_DATE);
			mCrime.setDate(date);
			updateDate();
		}
		if(requestCode==REQUEST_TIME ){
			Date date=(Date)data.getSerializableExtra(TimePickerFragment.EXTRA_TIME);
			mCrime.setDate(date);
			updateTime();
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState){
		View v= inflater.inflate(R.layout.fragment_crime, parent, false);
		mTitleField = (EditText)v.findViewById(R.id.crime_title);
		mTitleField.setText(mCrime.getTitle());
		mTitleField.addTextChangedListener(new TextWatcher(){
			public void onTextChanged(
					CharSequence c, int start, int before, int count){
				mCrime.setTitle(c.toString());
			}
			
			public void beforeTextChanged(
					CharSequence c, int start, int count, int after){
				//blank
			}
			public void afterTextChanged(Editable c){
				//blank
			}
		});
		mDateButton = (Button)v.findViewById(R.id.crime_date);
		updateDate();
		mDateButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				android.support.v4.app.FragmentManager fm = getActivity().getSupportFragmentManager();
				DatePickerFragment dialog = DatePickerFragment.newInstance(mCrime.getDate());
				dialog.setTargetFragment(CrimeFragment.this, REQUEST_DATE);
				dialog.show(fm, DIALOG_DATE);


			}
		});

		//button for time picker
		mTimeButton = (Button)v.findViewById(R.id.crime_time);
		updateTime();
		mTimeButton.setOnClickListener(new View.OnClickListener(){
			public void onClick(View v){
				android.support.v4.app.FragmentManager fm=getActivity().getSupportFragmentManager();
				TimePickerFragment time_dialog=TimePickerFragment.newInstance(mCrime.getDate());
				time_dialog.setTargetFragment(CrimeFragment.this, REQUEST_TIME);
				time_dialog.show(fm,DIALOG_TIME);
			}
		});


		mSolvedCheckBox = (CheckBox)v.findViewById(R.id.crime_solved);
		mSolvedCheckBox.setChecked(mCrime.isSolved());
		mSolvedCheckBox.setOnCheckedChangeListener(new OnCheckedChangeListener(){
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
			{
				mCrime.setSolved(isChecked);
			}
		});
		
		
		return v;
		
	}
	
	public static CrimeFragment newInstance(UUID crimeId){
		Bundle args = new Bundle();
		args.putSerializable(EXTRA_CRIME_ID, crimeId);
		
		CrimeFragment fragment = new CrimeFragment();
		fragment.setArguments(args);
		
		return fragment;
		
		
	}

}
