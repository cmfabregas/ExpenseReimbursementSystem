import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import com.example.dao.ReimbursementDaoImpl;
import com.example.dao.UserDaoImpl;
import com.example.model.Reimbursement;
import com.example.model.User;
import com.example.model.UserRole;
import com.example.service.UserService;

public class MainDriver 
{
	
	public static void main(String[] args) throws SQLException
	{
		UserDaoImpl userDao = new UserDaoImpl();
		
		User ers_users = new User("jondoe", "password", "jon", "doe", "jondoe@email.com", new UserRole("Employee"));
		
		//List<User> ers_userList = new ArrayList<User>(); 
		
		//ers_userList = uDI.getAllUsers();
		
		//uDI.createAccount(ers_users);
		
		
		//System.out.println(ers_userList);

		
		//User ersUser = userDao.getByUsername("carlosfabregas");
		
		
		
//		UserService uService = new UserService(userDao);
//		
//		User ersUser = uService.getUserVerify("carlosfabregas", "Passw0rd");
//		
//		System.out.println(ersUser);
//		
		
		//ReimbursementDaoImpl reimbDaoImpl = new ReimbursementDaoImpl();
		
		
		//System.out.println(reimbDaoImpl.getAllReimbursements());
		
		System.out.println(userDao.createAccount(ers_users)); 
		
		ReimbursementDaoImpl reimbDaoImpl = new ReimbursementDaoImpl();
    	
    	List<Reimbursement> reimbList = reimbDaoImpl.getAllReimbursements();
    	
    	List<String> dataList = new ArrayList<>();
    	for(int x =0; x<reimbList.size(); x++)
    	{
    		dataList.set(x, null);
    	}
    		
    	//Author:
    	String name =""+reimbList.get(0).getReimbAuthor().getFirst_name()+ " " + reimbList.get(0).getReimbAuthor().getLast_name(); //last name
		reimbList.get(0).getReimbAuthor().getEmail(); //email
		//type
		reimbList.get(0).getReimbType().getType();
		//time submitted
		reimbList.get(0).getReimb_submitted(); //time submitted
		//amount
		reimbList.get(0).getReimb_amount();//amount
		reimbList.get(0).getReimb_resolved(); //time resolved
		//description
		reimbList.get(0).getReimb_description();
		//resolver
		reimbList.get(0).getReimbResolver().getFirst_name();
		reimbList.get(0).getReimbResolver().getLast_name();
		reimbList.get(0).getReimbAuthor().getEmail();
		//status
		reimbList.get(0).getReimbStatus().getStatus();
		
		
		
		
		
		
		
		
	}

}
