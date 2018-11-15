import static org.junit.Assert.assertEquals;

import java.sql.SQLException;

import org.junit.Test;

import metier.Requetes;
import model.Apprenant;

public class RequetesTest {
	
	@Test
    public void testgetApprenantByName() throws ClassNotFoundException, SQLException {
		String nom = "Longueville";
		
		Apprenant apprenant = Requetes.getApprenantByName(nom);
		String actual = apprenant.getPrenom();
		assertEquals("Thomas", actual);
    }
}
