package models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.avaje.ebean.PagedList;
import com.avaje.ebean.Model.Find;

import play.data.validation.Constraints;
@Entity 
public class Custodio extends com.avaje.ebean.Model {
	
	  private static final long serialVersionID1 = 1L;
	@Id
    public Long id;
    @Constraints.Required
    public String name;
    @Constraints.Required
    public int edad; 
    @Constraints.Required
    public int computadoras;
    @OneToOne
    public Computer computer;
    
    public static Find<Long,Custodio> find = new Find<Long,Custodio>(){}; 
    public static PagedList<Custodio> page(int page, int pageSize, String sortBy, String order, String filter) {
        return
            find.where()
                .ilike("name", "%" + filter + "%")
                .orderBy(sortBy + " " + order)
                .fetch("computer")
                .findPagedList(page, pageSize);
    }
    
    
}
