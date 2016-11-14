package models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import play.data.validation.Constraints;
@Entity 
public class Custodio extends com.avaje.ebean.Model {
	
	private static final long serialVersionUID = 1L;
	@Id
    public Long id;
    @Constraints.Required
    public String name;
    @Constraints.Required
    public int edad; 
    @Constraints.Required
    public int años;

    
     
    
}
