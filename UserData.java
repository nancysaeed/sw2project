import java.util.List;
import java.util.ArrayList;


public class UserData {
	
	int uId;
    String name;
    int Scores;
    List <String> interests = new ArrayList();
    
    public UserData(){}
    
    public void setID(int id)
    {
        uId = id;
    }
    public int getID()
    {
        return uId;
    }
    public void setname(String Ln)
    {
        name = Ln;
    }
    public String getname()
    {
        return name;
    }
    public void setInterest(List<String> Interest)
    {
        interests = Interest;
    }
    public List<String> getInterest()
    {
        return interests;
    }

}
