package com.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.bean.Candidate;
import com.bean.ElectionBean;
import com.sort.AgeCompare;
import com.sort.FirstNameCompare;
import com.sort.LastNameCompare;

@Controller
public class ElectionController {
	@Autowired
	private Candidate candidate;
	
	@RequestMapping("/")
	public ModelAndView onStartUp() {
		// TODO Auto-generated constructor stub
		return new ModelAndView("index", "electionBean", new ElectionBean());
	}

   @RequestMapping(value="/getWinner", method = RequestMethod.GET)
   public String getElectionResult(@ModelAttribute("electionBean")ElectionBean electionBean, BindingResult result, ModelMap model) {
	   String winnerName=null;
	   List<Candidate> candidateList = new ArrayList<Candidate>();
	   String candidateData=electionBean.getCandidateData();
	   String candidateDataArr[]=candidateData.split(":");
	   for(String data :candidateDataArr){
		   String canData[]=data.split(",");
		   candidateList.add(new Candidate(canData[0], canData[1], Integer.valueOf(canData[2]), canData[3]));
	   }
	   winnerName=getResult(candidateList);
	   model.addAttribute("winner", "Winner is: "+winnerName+" !");
	   return "index";
   }
   
   public String getResult(List<Candidate> candidateList){
	   String winner = null;
	   int size = candidateList.size();
	   
	   if(size==1){
		   winner = candidateList.get(0).getFirstName() +" "+ candidateList.get(0).getLastName()
				   +" "+candidateList.get(0).getAge() +" "+ candidateList.get(0).getCountry();
	   } else if (size==2) {
		   String firstNameC1 = candidateList.get(0).getFirstName();
		   String firstNameC2 = candidateList.get(1).getFirstName();
		   String lastNameC1 = candidateList.get(0).getLastName();
		   String lastNameC2 = candidateList.get(1).getLastName();
		   
		   if(firstNameC1.equals(firstNameC2)){
			   Collections.sort(candidateList, new LastNameCompare());
			   winner = candidateList.get(0).getFirstName() +" "+ candidateList.get(0).getLastName()
					   +" "+candidateList.get(0).getAge() +" "+ candidateList.get(0).getCountry();
		   } else {
			   if(lastNameC1.equals(lastNameC2)){
				   Collections.sort(candidateList, new AgeCompare());
				   winner = candidateList.get(0).getFirstName() +" "+ candidateList.get(0).getLastName()
						   +" "+candidateList.get(0).getAge() +" "+ candidateList.get(0).getCountry();
			   } else {
				   Collections.sort(candidateList, new FirstNameCompare());
				   winner = candidateList.get(0).getFirstName() +" "+ candidateList.get(0).getLastName()
						   +" "+candidateList.get(0).getAge() +" "+ candidateList.get(0).getCountry();
			   }
		   }
	   } else {
		   String[] candidateArr = new String[size];
		   Collections.sort(candidateList, new FirstNameCompare());

		   for(int i=0; i<size; i++){
			   Candidate c =(Candidate)candidateList.get(i);
			   candidateArr[i]=c.getFirstName()+" "+c.getLastName()+" "+c.getAge()+" "+c.getCountry();
		   }
		   
		   Map<String,Integer> map = new HashMap<String, Integer>(); 
		   for (String str : candidateArr) 
		   { 
			   if (map.keySet().contains(str)) 
				   map.put(str, map.get(str) + 1); 
			   else
				   map.put(str, 1); 
		   }
		   int maxValueInMap = 0; 
		   for (Map.Entry<String,Integer> entry : map.entrySet()) 
		   {
			   String key  = entry.getKey(); 
			   Integer val = entry.getValue(); 
			   if (val > maxValueInMap) 
			   { 
				   maxValueInMap = val; 
				   winner = key; 
			   } else if (val == maxValueInMap && winner.compareTo(key) > 0) 
				   winner = key; 
		   } 
	   }

	   return winner;
   }
}
