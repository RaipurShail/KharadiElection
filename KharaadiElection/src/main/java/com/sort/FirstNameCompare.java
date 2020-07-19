package com.sort;

import java.util.Comparator;

import com.bean.Candidate;

public class FirstNameCompare implements Comparator<Candidate>{

	@Override
	public int compare(Candidate c1, Candidate c2) {
		// TODO Auto-generated method stub
		return c1.getFirstName().compareTo(c2.getFirstName());
	}
}
