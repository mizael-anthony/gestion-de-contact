import com.contact.controllers.ContactController;
import com.contact.models.ContactModel;
import com.contact.models.Model;
import com.contact.views.ContactView;
import com.contact.views.View;

/**
 * 
 */

/**
 * @author mizael
 *
 */
public class Main {

	/**
	 * @param argslery
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
	Model contactModel = new ContactModel();
		
	View contactView = new ContactView();
	
	new ContactController(
			 (ContactModel)contactModel, (ContactView)contactView);
	
	
	}

}
