
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static java.util.stream.DoubleStream.builder;

import org.json.JSONArray;
import org.json.JSONObject;

import static org.json.JSONPointer.builder;

public class GetInterests {
	
	StringBuffer response = new StringBuffer();
    String responseInString = new String();
    JSONObject jsonResult;
    BufferedReader in;
    String readLine = null;
    List<String> intersts = new ArrayList();
    UserData user = new UserData();
    ArrayList<CompanyData> ListOfcompany = new ArrayList();
    List<CompanyData> FinalCom = new ArrayList();
    List<String> ListCI = new ArrayList();
    UserData Finaluser = new UserData();
    List<QuizData> AllQuizs = new ArrayList();
    
	public void UserGETRequest(String s1) throws IOException {

        URL urlForGetRequest = new URL(s1);
        HttpURLConnection conection = (HttpURLConnection) urlForGetRequest.openConnection();
        conection.setRequestMethod("GET");
        conection.setRequestProperty("userId", "a1bcdef"); // set userId its a sample here
        int responseCode = conection.getResponseCode();
        
        if (responseCode == HttpURLConnection.HTTP_OK) {
            in = new BufferedReader(new InputStreamReader(conection.getInputStream()));
            String line = "";
            while ((line = in.readLine()) != null) 
            {
                responseInString += line;
            }           
        }
        else 
        {
            System.out.println("GET NOT WORKED");
        }              
                                    
	}
	
	//**************Get User interests from API***************
	String readAll(Reader rd) throws IOException {
		   
	    StringBuilder sb = new StringBuilder();
	    int cp;
	    while ((cp = rd.read()) != -1) {
	      sb.append((char) cp);
	    }
	    return sb.toString();
	  }
	public UserData OutUserGETRequest() throws IOException {
		
		InputStream is = new URL("http://www.mocky.io/v2/5cb876444c0000be1ad3d58b").openStream();
	    
	      BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
	      String jsonText = readAll(rd);
	      JSONObject json = new JSONObject(jsonText);
	      jsonResult = new JSONObject(json);
	    
		int userId = json.getInt("Userid");
        user.setID(userId);
        String n = json.getString("name");
        user.setname(n);
        String InterestsInString = json.getString("interests");      
        String items = InterestsInString.replaceAll("\\\"interests\"", "").replaceAll("\\:", "").replaceAll("\\\"", "");
        String[] words = items.split(",");
        List<String> wordList = Arrays.asList(words);
        
        List<String> list = new ArrayList();
        for (String str : wordList) 
        {
        	list.add(str.toUpperCase());
        }
        user.setInterest(list);
        //System.out.println(list);
        return user;
        
	}
	
	//**************Get companies interests from API***************
	public List<CompanyData> OutCompanyGETRequest() throws IOException {
		
		
		UserGETRequest("http://www.mocky.io/v2/5cb8587b4c00000319d3d4d7");
		
		JSONArray JArray =new JSONArray(responseInString);
        
        for(int i=0 ; i<JArray.length() &&  JArray != null ; i++)
        {
            JSONObject  Jobject = (JSONObject) JArray.getJSONObject(i);
            
            CompanyData company = new CompanyData();
            String cname = (String) Jobject.get("name");
            company.setCompName(cname);
            String InterestsInString = Jobject.getString("interests");      
            String items = InterestsInString.replaceAll("\\\"interests\"", "").replaceAll("\\:", "").replaceAll("\\\"", "");
            String[] words = items.split(",");
            List<String> wordList = Arrays.asList(words);
            	company.setInterest(wordList);
            	
            ListOfcompany.add(company);
        }
      //to convert company lists of interests into uppercase 
        for(int j=0 ; j<ListOfcompany.size() ; j++)
        {
                ListCI = ListOfcompany.get(j).getInterest();
                List<String> Listused = new ArrayList();
                CompanyData Com = new CompanyData();
                Com.setCompName(ListOfcompany.get(j).getCompName());
                for (String str : ListCI) 
                {
                	Listused.add(str.toUpperCase());
                }
                Com.setInterest(Listused);
                //System.out.println(Listused);
                FinalCom.add(Com);
        }
        return FinalCom;
	}
	
	//**************Get companies interests from API***************
		public List<QuizData> OutQuizesGETRequest() throws IOException {
			
			
			UserGETRequest("https://quiz-app-select.herokuapp.com/");
			
			JSONArray JArray =new JSONArray(responseInString);
	        
	        for(int i=0 ; i<JArray.length() &&  JArray != null ; i++)
	        {
	            JSONObject  Jobject = (JSONObject) JArray.getJSONObject(i);
	            
	            QuizData Quiz = new QuizData();
	            String QuizTitle = (String) Jobject.get("Title");
	            Quiz.setTitle(QuizTitle);
	            String quizSkillType = (String) Jobject.get("SkillType");
	            Quiz.setSkillType(quizSkillType.toUpperCase());
	            AllQuizs.add(Quiz);
	        }
	      
	        return AllQuizs;
		}
	
}
