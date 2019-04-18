import java.util.ArrayList;
import java.util.List;


public class CompanyData {

	String name;
    List <String> interests = new ArrayList();
    
    public CompanyData(){}
    
    public void setCompName(String cn)
    {
        name = cn;
    }
    public String getCompName()
    {
        return name;
    }
    
    public void setInterest(List<String> interest)
    {
        interests  = interest;
    }
    public List<String> getInterest()
    {
        return interests ;
    }
}
